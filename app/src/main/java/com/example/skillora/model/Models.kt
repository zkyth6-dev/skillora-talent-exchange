package com.example.skillora.model

import androidx.annotation.DrawableRes

enum class AppLanguage(val code: String, val displayName: String) {
    EN("en", "English"),
    ID("id", "Bahasa Indonesia")
}

data class Category(
    val slug: String,
    val nameEn: String,
    val nameId: String,
    val iconName: String
)

data class Provider(
    val id: String,
    val name: String,
    val headline: String,
    val country: String,
    val languages: List<String>,
    val verified: Boolean
)

data class ServiceItem(
    val id: String,
    val title: String,
    val category: String,
    val providerId: String,
    @DrawableRes val coverRes: Int,
    val priceFromMinor: Long, // in cents USD (e.g. 18000 = $180.00)
    val currency: String = "USD",
    val deliveryDays: Int,
    val rating: Double,
    val reviewCount: Int,
    val tags: List<String>
) {
    val priceInDollars: Double get() = priceFromMinor / 100.0
}

data class FeeBreakdown(
    val subtotalMinor: Long,
    val commissionMinor: Long,
    val providerNetMinor: Long,
    val rate: Double = 0.05
) {
    val subtotalDollars: Double get() = subtotalMinor / 100.0
    val commissionDollars: Double get() = commissionMinor / 100.0
    val providerNetDollars: Double get() = providerNetMinor / 100.0
}

enum class SortOption(val labelEn: String, val labelId: String) {
    RECOMMENDED("Recommended", "Direkomendasikan"),
    PRICE_ASC("Price: Low to High", "Harga: Rendah ke Tinggi"),
    PRICE_DESC("Price: High to Low", "Harga: Tinggi ke Rendah"),
    RATING("Highest Rated", "Rating Tertinggi"),
    DELIVERY("Fastest Delivery", "Pengiriman Tercepat")
}

data class MarketplaceFilters(
    val query: String = "",
    val category: String? = null,
    val minPrice: Double? = null,
    val maxPrice: Double? = null,
    val maxDelivery: Int? = null,
    val minRating: Double? = null,
    val sort: SortOption = SortOption.RECOMMENDED
)

data class OrderInquiry(
    val id: String,
    val serviceId: String,
    val serviceTitle: String,
    val providerName: String,
    val amountDollars: Double,
    val requirements: String,
    val status: String, // "Submitted", "In Review", "Delivered"
    val timestamp: Long = System.currentTimeMillis()
)
