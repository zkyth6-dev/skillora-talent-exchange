package com.example.skillora.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.skillora.model.AppLanguage
import com.example.skillora.model.OrderInquiry
import com.example.skillora.ui.components.PreviewBadge
import com.example.skillora.ui.theme.ElectricBlue
import java.util.Locale

@Composable
fun ProfileScreen(
    currentLanguage: AppLanguage,
    isDarkMode: Boolean,
    orders: List<OrderInquiry>,
    onLanguageChange: (AppLanguage) -> Unit,
    onToggleDarkMode: () -> Unit,
    modifier: Modifier = Modifier
) {
    var userMode by remember { mutableStateOf("Client") } // Client or Provider

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
            .testTag("profile_screen")
    ) {
        // User Profile Header Card
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(ElectricBlue)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Alex Morgan",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = "Verified",
                                tint = ElectricBlue,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Text(
                            text = if (currentLanguage == AppLanguage.ID) "Satu akun, dua peran: Pembeli & Penjual" else "One account, two roles: Hire & Sell",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Role Toggle
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.SwapHoriz,
                                contentDescription = null,
                                tint = ElectricBlue
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (currentLanguage == AppLanguage.ID) "Peran Aktif: $userMode" else "Active Profile: $userMode Mode",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            FilterChip(
                                selected = userMode == "Client",
                                onClick = { userMode = "Client" },
                                label = { Text(if (currentLanguage == AppLanguage.ID) "Klien" else "Client") }
                            )
                            FilterChip(
                                selected = userMode == "Provider",
                                onClick = { userMode = "Provider" },
                                label = { Text(if (currentLanguage == AppLanguage.ID) "Penyedia" else "Seller") }
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Language & Preferences Card
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text(
                    text = if (currentLanguage == AppLanguage.ID) "Pengaturan Bahasa & Tampilan" else "Language & Display Settings",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Language
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Language,
                            contentDescription = null,
                            tint = ElectricBlue
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = if (currentLanguage == AppLanguage.ID) "Bahasa Aplikasi" else "App Language",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        FilterChip(
                            selected = currentLanguage == AppLanguage.EN,
                            onClick = { onLanguageChange(AppLanguage.EN) },
                            label = { Text("English") }
                        )
                        FilterChip(
                            selected = currentLanguage == AppLanguage.ID,
                            onClick = { onLanguageChange(AppLanguage.ID) },
                            label = { Text("Indonesia") }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Dark Theme Toggle
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Brightness4,
                            contentDescription = null,
                            tint = ElectricBlue
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = if (currentLanguage == AppLanguage.ID) "Mode Gelap (Dark Mode)" else "Dark Theme",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Switch(
                        checked = isDarkMode,
                        onCheckedChange = { onToggleDarkMode() }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // My Orders / Inquiries
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.ReceiptLong,
                        contentDescription = null,
                        tint = ElectricBlue
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = if (currentLanguage == AppLanguage.ID) "Pesanan & Permintaan Saya (${orders.size})" else "My Orders & Inquiries (${orders.size})",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                if (orders.isEmpty()) {
                    Text(
                        text = if (currentLanguage == AppLanguage.ID) "Belum ada pesanan aktif." else "No active orders yet.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                } else {
                    orders.forEach { order ->
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = order.serviceTitle,
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.weight(1f)
                                    )
                                    Surface(
                                        shape = RoundedCornerShape(8.dp),
                                        color = ElectricBlue.copy(alpha = 0.2f)
                                    ) {
                                        Text(
                                            text = order.status,
                                            color = ElectricBlue,
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Provider: ${order.providerName} · Total: $${order.amountDollars.toInt()} USD",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                if (order.requirements.isNotBlank()) {
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Notes: ${order.requirements}",
                                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // About & Preview Note
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = null, tint = ElectricBlue)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Skillora v1.0 — Talent Exchange",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                PreviewBadge(lang = currentLanguage, withNote = true)
            }
        }
    }
}
