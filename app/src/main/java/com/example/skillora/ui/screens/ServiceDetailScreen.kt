package com.example.skillora.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skillora.data.I18nStrings
import com.example.skillora.data.SkilloraRepository
import com.example.skillora.model.AppLanguage
import com.example.skillora.model.ServiceItem
import com.example.skillora.ui.components.PreviewBadge
import com.example.skillora.ui.components.ProviderAvatar
import com.example.skillora.ui.components.TransparentFeeCard
import com.example.skillora.ui.theme.AmberStar
import com.example.skillora.ui.theme.ElectricBlue
import com.example.skillora.ui.theme.MidnightNavy
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ServiceDetailScreen(
    service: ServiceItem,
    lang: AppLanguage,
    onBack: () -> Unit,
    onSubmitOrder: (ServiceItem, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val provider = SkilloraRepository.getProvider(service.providerId)
    val category = SkilloraRepository.getCategory(service.category)
    val categoryName = if (lang == AppLanguage.ID) category?.nameId else category?.nameEn
    val fees = SkilloraRepository.calculateOrderFees(service.priceFromMinor)

    var showOrderDialog by remember { mutableStateOf(false) }
    var orderRequirements by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = categoryName ?: "Service Details", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 8.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = I18nStrings.fromPrice(lang).uppercase(),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "$" + String.format(Locale.US, "%.0f", service.priceInDollars),
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Button(
                        onClick = { showOrderDialog = true },
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = ElectricBlue),
                        modifier = Modifier
                            .height(48.dp)
                            .testTag("order_now_button")
                    ) {
                        Text(
                            text = I18nStrings.orderNow(lang),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }
                }
            }
        },
        modifier = modifier.testTag("service_detail_screen")
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 24.dp)
        ) {
            // Cover Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 10f)
            ) {
                Image(
                    painter = painterResource(id = service.coverRes),
                    contentDescription = service.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MidnightNavy.copy(alpha = 0.82f),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(16.dp)
                ) {
                    Text(
                        text = categoryName ?: service.category,
                        color = Color.White,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    )
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                PreviewBadge(lang = lang, withNote = true)

                Spacer(modifier = Modifier.height(12.dp))

                // Service Title
                Text(
                    text = service.title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 28.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Rating & Delivery Stats
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = AmberStar,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = String.format(Locale.US, "%.1f", service.rating),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "(${service.reviewCount} ${if (lang == AppLanguage.ID) "ulasan" else "reviews"})",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = I18nStrings.deliveryInDays(service.deliveryDays, lang),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Provider Profile Card
                if (provider != null) {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ProviderAvatar(name = provider.name, size = 52.dp)
                            Spacer(modifier = Modifier.width(14.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = provider.name,
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Bold
                                    )
                                    if (provider.verified) {
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Icon(
                                            imageVector = Icons.Default.CheckCircle,
                                            contentDescription = "Verified",
                                            tint = ElectricBlue,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                }
                                Text(
                                    text = provider.headline,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Public,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.size(13.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "${provider.country} · " + provider.languages.joinToString(", ").uppercase(),
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Deliverables & What's Included
                Text(
                    text = if (lang == AppLanguage.ID) "Yang Termasuk Dalam Paket" else "What's Included in Package",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))

                val deliverables = if (lang == AppLanguage.ID) listOf(
                    "Hasil karya beresolusi tinggi & file sumber lengkap",
                    "Hak penggunaan komersial penuh",
                    "Komunikasi langsung dengan freelancer di platform",
                    "Garansi revisi terstruktur dengan persetujuan bertahap"
                ) else listOf(
                    "High-resolution deliverables & complete editable source files",
                    "Full commercial usage rights included",
                    "Direct on-platform chat and milestone coordination",
                    "Structured revision cycles with final approval control"
                )

                deliverables.forEach { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape)
                                .background(ElectricBlue.copy(alpha = 0.15f))
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = ElectricBlue,
                                modifier = Modifier.size(13.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = item,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Tags
                Text(
                    text = if (lang == AppLanguage.ID) "Tag Layanan" else "Tags",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    service.tags.forEach { tag ->
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant
                        ) {
                            Text(
                                text = "#$tag",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Transparent Order Fee Breakdown
                TransparentFeeCard(fees = fees, lang = lang)
            }
        }
    }

    // Order Inquiry Confirmation Dialog
    if (showOrderDialog) {
        AlertDialog(
            onDismissRequest = { showOrderDialog = false },
            title = {
                Text(
                    text = if (lang == AppLanguage.ID) "Konfirmasi Pesanan" else "Confirm Order Inquiry",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column {
                    Text(
                        text = if (lang == AppLanguage.ID)
                            "Anda akan memesan: ${service.title} seharga $${service.priceInDollars.toInt()} USD."
                        else
                            "You are placing an order for: ${service.title} at $${service.priceInDollars.toInt()} USD."
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = orderRequirements,
                        onValueChange = { orderRequirements = it },
                        label = {
                            Text(if (lang == AppLanguage.ID) "Kebutuhan Proyek" else "Project Requirements / Instructions")
                        },
                        placeholder = {
                            Text(if (lang == AppLanguage.ID) "Jelaskan detail yang Anda butuhkan..." else "Briefly describe your specifications...")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 4
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onSubmitOrder(service, orderRequirements)
                        showOrderDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = ElectricBlue)
                ) {
                    Text(if (lang == AppLanguage.ID) "Kirim Pesanan" else "Submit Order")
                }
            },
            dismissButton = {
                TextButton(onClick = { showOrderDialog = false }) {
                    Text(if (lang == AppLanguage.ID) "Batal" else "Cancel")
                }
            }
        )
    }
}
