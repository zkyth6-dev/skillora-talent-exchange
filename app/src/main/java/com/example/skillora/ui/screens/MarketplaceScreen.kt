package com.example.skillora.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skillora.data.SkilloraRepository
import com.example.skillora.model.AppLanguage
import com.example.skillora.model.MarketplaceFilters
import com.example.skillora.model.ServiceItem
import com.example.skillora.model.SortOption
import com.example.skillora.ui.components.PreviewBadge
import com.example.skillora.ui.components.ServiceCard
import com.example.skillora.ui.theme.ElectricBlue
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MarketplaceScreen(
    services: List<ServiceItem>,
    filters: MarketplaceFilters,
    lang: AppLanguage,
    onSearchChange: (String) -> Unit,
    onCategorySelect: (String?) -> Unit,
    onSortChange: (SortOption) -> Unit,
    onUpdateFilters: (category: String?, minPrice: Double?, maxPrice: Double?, maxDelivery: Int?, minRating: Double?) -> Unit,
    onClearFilters: () -> Unit,
    onServiceClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var showFilterSheet by remember { mutableStateOf(false) }
    var showSortMenu by remember { mutableStateOf(false) }

    // Filter Sheet state
    var sheetCategory by remember(filters.category) { mutableStateOf(filters.category) }
    var sheetMaxPrice by remember(filters.maxPrice) { mutableDoubleStateOf(filters.maxPrice ?: 500.0) }
    var sheetMaxDelivery by remember(filters.maxDelivery) { mutableIntStateOf(filters.maxDelivery ?: 14) }
    var sheetMinRating by remember(filters.minRating) { mutableDoubleStateOf(filters.minRating ?: 0.0) }

    val activeFilterCount = listOfNotNull(
        filters.category,
        filters.maxPrice,
        filters.maxDelivery,
        filters.minRating
    ).size

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("marketplace_screen")
    ) {
        // Top Search Bar
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = filters.query,
                        onValueChange = onSearchChange,
                        placeholder = {
                            Text(
                                text = if (lang == AppLanguage.ID) "Cari layanan, keahlian, atau penyedia..." else "Search services, skills, or talent...",
                                fontSize = 13.sp
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                tint = ElectricBlue
                            )
                        },
                        trailingIcon = {
                            if (filters.query.isNotEmpty()) {
                                IconButton(onClick = { onSearchChange("") }) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Clear",
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("marketplace_search_field")
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    // Filter Button
                    OutlinedButton(
                        onClick = { showFilterSheet = true },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.testTag("filter_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.FilterList,
                            contentDescription = "Filter",
                            modifier = Modifier.size(18.dp)
                        )
                        if (activeFilterCount > 0) {
                            Spacer(modifier = Modifier.width(4.dp))
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(18.dp)
                                    .clip(CircleShape)
                                    .background(ElectricBlue)
                            ) {
                                Text(
                                    text = activeFilterCount.toString(),
                                    color = Color.White,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Category Quick Filter Row
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        FilterChip(
                            selected = filters.category == null,
                            onClick = { onCategorySelect(null) },
                            label = {
                                Text(if (lang == AppLanguage.ID) "Semua" else "All")
                            }
                        )
                    }
                    items(SkilloraRepository.CATEGORIES) { cat ->
                        val name = if (lang == AppLanguage.ID) cat.nameId else cat.nameEn
                        FilterChip(
                            selected = filters.category == cat.slug,
                            onClick = {
                                if (filters.category == cat.slug) {
                                    onCategorySelect(null)
                                } else {
                                    onCategorySelect(cat.slug)
                                }
                            },
                            label = { Text(name) }
                        )
                    }
                }
            }
        }

        // Subheader: Result Count & Sort Selector
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val resultsText = if (lang == AppLanguage.ID) {
                "${services.size} hasil ditemukan"
            } else {
                "${services.size} services found"
            }
            Text(
                text = resultsText,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Box {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.clickable { showSortMenu = true }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Sort,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (lang == AppLanguage.ID) filters.sort.labelId else filters.sort.labelEn,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                DropdownMenu(
                    expanded = showSortMenu,
                    onDismissRequest = { showSortMenu = false }
                ) {
                    SortOption.entries.forEach { option ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = if (lang == AppLanguage.ID) option.labelId else option.labelEn,
                                    fontWeight = if (filters.sort == option) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            onClick = {
                                onSortChange(option)
                                showSortMenu = false
                            }
                        )
                    }
                }
            }
        }

        // Active filters list
        if (activeFilterCount > 0) {
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                if (filters.category != null) {
                    val catName = SkilloraRepository.getCategory(filters.category)?.let {
                        if (lang == AppLanguage.ID) it.nameId else it.nameEn
                    } ?: filters.category
                    ActiveFilterChip(label = catName) { onCategorySelect(null) }
                }
                if (filters.maxPrice != null) {
                    ActiveFilterChip(label = "Up to $${filters.maxPrice.toInt()}") {
                        onUpdateFilters(filters.category, filters.minPrice, null, filters.maxDelivery, filters.minRating)
                    }
                }
                if (filters.maxDelivery != null) {
                    ActiveFilterChip(label = "≤ ${filters.maxDelivery} days") {
                        onUpdateFilters(filters.category, filters.minPrice, filters.maxPrice, null, filters.minRating)
                    }
                }
                if (filters.minRating != null) {
                    ActiveFilterChip(label = "★ ${filters.minRating}+") {
                        onUpdateFilters(filters.category, filters.minPrice, filters.maxPrice, filters.maxDelivery, null)
                    }
                }
                Text(
                    text = if (lang == AppLanguage.ID) "Hapus semua" else "Clear all",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable { onClearFilters() }
                )
            }
        }

        // Services List or Empty State
        if (services.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Icon(
                            imageVector = Icons.Default.SearchOff,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = if (lang == AppLanguage.ID) "Tidak ada layanan yang cocok" else "No services match your filters",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = if (lang == AppLanguage.ID) "Coba gunakan kata kunci berbeda atau bersihkan filter." else "Try a broader search or clear current filters.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    Button(
                        onClick = onClearFilters,
                        colors = ButtonDefaults.buttonColors(containerColor = ElectricBlue)
                    ) {
                        Text(if (lang == AppLanguage.ID) "Bersihkan Filter" else "Clear Filters")
                    }
                }
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 32.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    PreviewBadge(lang = lang, withNote = true)
                }
                items(services, key = { it.id }) { service ->
                    ServiceCard(
                        service = service,
                        provider = SkilloraRepository.getProvider(service.providerId),
                        lang = lang,
                        onClick = { onServiceClick(service.id) }
                    )
                }
            }
        }
    }

    // Modal Bottom Sheet for Filters
    if (showFilterSheet) {
        ModalBottomSheet(
            onDismissRequest = { showFilterSheet = false },
            sheetState = rememberModalBottomSheetState()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (lang == AppLanguage.ID) "Filter Pencarian" else "Filter Services",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = if (lang == AppLanguage.ID) "Reset" else "Reset",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            sheetCategory = null
                            sheetMaxPrice = 500.0
                            sheetMaxDelivery = 14
                            sheetMinRating = 0.0
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Max Price Slider
                Text(
                    text = if (lang == AppLanguage.ID) "Maksimal Harga: $${sheetMaxPrice.toInt()}" else "Max Price: $${sheetMaxPrice.toInt()}",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleMedium
                )
                Slider(
                    value = sheetMaxPrice.toFloat(),
                    onValueChange = { sheetMaxPrice = it.toDouble() },
                    valueRange = 20f..500f,
                    steps = 19
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Max Delivery Days
                Text(
                    text = if (lang == AppLanguage.ID) "Waktu Pengiriman: Maksimal $sheetMaxDelivery hari" else "Delivery Time: Up to $sheetMaxDelivery days",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleMedium
                )
                Slider(
                    value = sheetMaxDelivery.toFloat(),
                    onValueChange = { sheetMaxDelivery = it.toInt() },
                    valueRange = 1f..14f,
                    steps = 13
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Min Rating
                Text(
                    text = if (lang == AppLanguage.ID) "Rating Penjual Minimal: ${if (sheetMinRating == 0.0) "Semua" else String.format(Locale.US, "%.1f & ke atas", sheetMinRating)}"
                    else "Minimum Seller Rating: ${if (sheetMinRating == 0.0) "Any" else String.format(Locale.US, "%.1f & up", sheetMinRating)}",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleMedium
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    listOf(0.0 to "Any", 4.5 to "4.5+", 4.8 to "4.8+").forEach { (r, label) ->
                        FilterChip(
                            selected = sheetMinRating == r,
                            onClick = { sheetMinRating = r },
                            label = { Text(label) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Apply Button
                Button(
                    onClick = {
                        onUpdateFilters(
                            sheetCategory,
                            null,
                            if (sheetMaxPrice < 500.0) sheetMaxPrice else null,
                            if (sheetMaxDelivery < 14) sheetMaxDelivery else null,
                            if (sheetMinRating > 0.0) sheetMinRating else null
                        )
                        showFilterSheet = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = ElectricBlue),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = if (lang == AppLanguage.ID) "Terapkan Filter" else "Apply Filters",
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))
            }
        }
    }
}

@Composable
private fun ActiveFilterChip(
    label: String,
    onRemove: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Remove",
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier
                    .size(13.dp)
                    .clickable(onClick = onRemove)
            )
        }
    }
}
