package com.example.skillora.viewmodel

import androidx.lifecycle.ViewModel
import com.example.skillora.data.SkilloraRepository
import com.example.skillora.model.AppLanguage
import com.example.skillora.model.FeeBreakdown
import com.example.skillora.model.MarketplaceFilters
import com.example.skillora.model.OrderInquiry
import com.example.skillora.model.ServiceItem
import com.example.skillora.model.SortOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

data class SkilloraUiState(
    val language: AppLanguage = AppLanguage.EN,
    val isDarkMode: Boolean = false,
    val currentTab: Int = 0, // 0: Home, 1: Marketplace, 2: Fee Calculator, 3: Profile/Orders
    val selectedServiceId: String? = null,
    val filters: MarketplaceFilters = MarketplaceFilters(),
    val filteredServices: List<ServiceItem> = SkilloraRepository.PREVIEW_SERVICES,
    val calculatorSubtotalMinor: Long = 10000L, // $100.00
    val calculatorFees: FeeBreakdown = SkilloraRepository.calculateOrderFees(10000L),
    val userOrders: List<OrderInquiry> = listOf(
        OrderInquiry(
            id = "ord-preview-1",
            serviceId = "s1",
            serviceTitle = "Complete brand identity with logo, palette and guidelines",
            providerName = "Amara Okafor",
            amountDollars = 180.0,
            requirements = "Tech startup logo and brand book for Skillora project",
            status = "In Progress"
        )
    ),
    val orderSuccessMessage: String? = null
)

class SkilloraViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SkilloraUiState())
    val uiState: StateFlow<SkilloraUiState> = _uiState.asStateFlow()

    fun setLanguage(lang: AppLanguage) {
        _uiState.update { it.copy(language = lang) }
    }

    fun toggleDarkMode() {
        _uiState.update { it.copy(isDarkMode = !it.isDarkMode) }
    }

    fun selectTab(tab: Int) {
        _uiState.update { it.copy(currentTab = tab, selectedServiceId = null) }
    }

    fun openServiceDetail(serviceId: String) {
        _uiState.update { it.copy(selectedServiceId = serviceId) }
    }

    fun closeServiceDetail() {
        _uiState.update { it.copy(selectedServiceId = null) }
    }

    fun setQuery(query: String) {
        val newFilters = _uiState.value.filters.copy(query = query)
        applyFilters(newFilters)
    }

    fun selectCategory(categorySlug: String?) {
        val newFilters = _uiState.value.filters.copy(category = categorySlug)
        applyFilters(newFilters)
    }

    fun setSort(sortOption: SortOption) {
        val newFilters = _uiState.value.filters.copy(sort = sortOption)
        applyFilters(newFilters)
    }

    fun updateFilters(
        category: String?,
        minPrice: Double?,
        maxPrice: Double?,
        maxDelivery: Int?,
        minRating: Double?
    ) {
        val newFilters = _uiState.value.filters.copy(
            category = category,
            minPrice = minPrice,
            maxPrice = maxPrice,
            maxDelivery = maxDelivery,
            minRating = minRating
        )
        applyFilters(newFilters)
    }

    fun clearFilters() {
        val newFilters = MarketplaceFilters(query = _uiState.value.filters.query)
        applyFilters(newFilters)
    }

    fun clearAllSearchAndFilters() {
        applyFilters(MarketplaceFilters())
    }

    private fun applyFilters(filters: MarketplaceFilters) {
        val filtered = SkilloraRepository.filterServices(
            SkilloraRepository.PREVIEW_SERVICES,
            filters
        )
        _uiState.update {
            it.copy(
                filters = filters,
                filteredServices = filtered
            )
        }
    }

    fun setCalculatorAmount(amountDollars: Double) {
        val cents = (amountDollars * 100.0).toLong().coerceAtLeast(0L)
        val feeBreakdown = SkilloraRepository.calculateOrderFees(cents)
        _uiState.update {
            it.copy(
                calculatorSubtotalMinor = cents,
                calculatorFees = feeBreakdown
            )
        }
    }

    fun submitOrder(service: ServiceItem, requirements: String) {
        val provider = SkilloraRepository.getProvider(service.providerId)
        val newOrder = OrderInquiry(
            id = "ord-" + UUID.randomUUID().toString().take(8),
            serviceId = service.id,
            serviceTitle = service.title,
            providerName = provider?.name ?: "Freelancer",
            amountDollars = service.priceInDollars,
            requirements = requirements.ifBlank { "Standard package delivery requested." },
            status = "Order Placed"
        )
        _uiState.update {
            it.copy(
                userOrders = listOf(newOrder) + it.userOrders,
                orderSuccessMessage = "Order inquiry #${newOrder.id} successfully submitted!"
            )
        }
    }

    fun dismissOrderSuccess() {
        _uiState.update { it.copy(orderSuccessMessage = null) }
    }
}
