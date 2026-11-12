package com.example.skillora.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skillora.R
import com.example.skillora.data.I18nStrings
import com.example.skillora.data.SkilloraRepository
import com.example.skillora.model.AppLanguage
import com.example.skillora.model.Category
import com.example.skillora.model.Provider
import com.example.skillora.model.ServiceItem
import com.example.skillora.ui.components.PreviewBadge
import com.example.skillora.ui.components.ProviderAvatar
import com.example.skillora.ui.components.ServiceCard
import com.example.skillora.ui.components.TransparentFeeCard
import com.example.skillora.ui.components.getCategoryIcon
import com.example.skillora.ui.theme.CyanGlow
import com.example.skillora.ui.theme.DeepBlue
import com.example.skillora.ui.theme.ElectricBlue
import com.example.skillora.ui.theme.MidnightNavy

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    lang: AppLanguage,
    onNavigateToMarketplace: (String?) -> Unit,
    onServiceClick: (String) -> Unit,
    onNavigateToCalculator: () -> Unit,
    modifier: Modifier = Modifier
) {
    var searchInput by remember { mutableStateOf("") }
    var howItWorksTab by remember { mutableIntStateOf(0) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("home_screen"),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        // Hero Section
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(MidnightNavy, DeepBlue)
                        )
                    )
                    .padding(20.dp)
            ) {
                Column {
                    PreviewBadge(lang = lang, withNote = false)
                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = I18nStrings.heroEyebrow(lang).uppercase(),
                        color = CyanGlow,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = if (lang == AppLanguage.ID) "Keahlian Anda.\nPeluang Nyata." else "Your Skills.\nYour Opportunity.",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 36.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = I18nStrings.heroSubtitle(lang),
                        color = Color(0xFFCBD5E1),
                        style = MaterialTheme.typography.bodyLarge,
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    // Hero Search Box
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            OutlinedTextField(
                                value = searchInput,
                                onValueChange = { searchInput = it },
                                placeholder = {
                                    Text(
                                        text = I18nStrings.searchPlaceholder(lang),
                                        fontSize = 13.sp,
                                        color = Color(0xFF64748B)
                                    )
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = null,
                                        tint = ElectricBlue
                                    )
                                },
                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("hero_search_input"),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = ElectricBlue,
                                    unfocusedBorderColor = Color(0xFFE2E8F0),
                                    focusedTextColor = Color.Black,
                                    unfocusedTextColor = Color.Black
                                )
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Button(
                                onClick = {
                                    onNavigateToMarketplace(searchInput.ifBlank { null })
                                },
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = ElectricBlue),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(46.dp)
                                    .testTag("hero_search_button")
                            ) {
                                Text(
                                    text = if (lang == AppLanguage.ID) "Cari Layanan" else "Search Services",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Popular Chips
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = if (lang == AppLanguage.ID) "Populer:" else "Popular:",
                            color = Color(0xFF94A3B8),
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                        I18nStrings.popularTags(lang).forEach { tag ->
                            Surface(
                                shape = RoundedCornerShape(14.dp),
                                color = Color.White.copy(alpha = 0.12f),
                                modifier = Modifier.clickable {
                                    onNavigateToMarketplace(tag)
                                }
                            ) {
                                Text(
                                    text = tag,
                                    color = Color.White,
                                    style = MaterialTheme.typography.labelSmall,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Value Props (One account, 5% fee, Safe delivery)
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ValuePropCard(
                    icon = Icons.Default.Person,
                    title = if (lang == AppLanguage.ID) "Satu Akun" else "One Account",
                    subtitle = if (lang == AppLanguage.ID) "Beli & Jual" else "Hire & Sell",
                    modifier = Modifier.weight(1f)
                )
                ValuePropCard(
                    icon = Icons.Default.CheckCircle,
                    title = "5% Fee",
                    subtitle = if (lang == AppLanguage.ID) "Biaya Transparan" else "Transparent",
                    modifier = Modifier.weight(1f)
                )
                ValuePropCard(
                    icon = Icons.Default.Security,
                    title = if (lang == AppLanguage.ID) "Aman" else "Protected",
                    subtitle = if (lang == AppLanguage.ID) "Tonggak Pesanan" else "Milestones",
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Categories Grid
        item {
            Column(modifier = Modifier.padding(top = 10.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = I18nStrings.categoriesTitle(lang),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = I18nStrings.categoriesSubtitle(lang),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(SkilloraRepository.CATEGORIES) { category ->
                        CategoryPill(
                            category = category,
                            lang = lang,
                            onClick = { onNavigateToMarketplace(category.slug) }
                        )
                    }
                }
            }
        }

        // Featured Services
        item {
            Column(modifier = Modifier.padding(top = 28.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = I18nStrings.featuredServicesTitle(lang),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = if (lang == AppLanguage.ID) "Layanan pilihan dari freelancer global" else "Hand-picked work from top providers",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Text(
                        text = if (lang == AppLanguage.ID) "Lihat Semua" else "See All",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { onNavigateToMarketplace(null) }
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    val featured = SkilloraRepository.PREVIEW_SERVICES.take(6)
                    items(featured) { service ->
                        ServiceCard(
                            service = service,
                            provider = SkilloraRepository.getProvider(service.providerId),
                            lang = lang,
                            onClick = { onServiceClick(service.id) },
                            modifier = Modifier.width(280.dp)
                        )
                    }
                }
            }
        }

        // Featured Freelancers
        item {
            Column(modifier = Modifier.padding(top = 28.dp, start = 16.dp, end = 16.dp)) {
                Text(
                    text = I18nStrings.featuredFreelancersTitle(lang),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (lang == AppLanguage.ID) "Bakat independen terpercaya dari berbagai negara" else "Independent talent with skills across borders",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(14.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(SkilloraRepository.PREVIEW_PROVIDERS) { provider ->
                        FreelancerCard(provider = provider, lang = lang)
                    }
                }
            }
        }

        // How Skillora Works
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 28.dp, start = 16.dp, end = 16.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = I18nStrings.howItWorksTitle(lang),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = I18nStrings.howItWorksSubtitle(lang),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    TabRow(
                        selectedTabIndex = howItWorksTab,
                        containerColor = Color.Transparent,
                        contentColor = MaterialTheme.colorScheme.primary
                    ) {
                        Tab(
                            selected = howItWorksTab == 0,
                            onClick = { howItWorksTab = 0 },
                            text = {
                                Text(
                                    text = if (lang == AppLanguage.ID) "Untuk Klien" else "For Clients",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        )
                        Tab(
                            selected = howItWorksTab == 1,
                            onClick = { howItWorksTab = 1 },
                            text = {
                                Text(
                                    text = if (lang == AppLanguage.ID) "Untuk Freelancer" else "For Providers",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    if (howItWorksTab == 0) {
                        StepItem(
                            stepNumber = "1",
                            title = if (lang == AppLanguage.ID) "Jelajahi & Temukan" else "Discover",
                            desc = if (lang == AppLanguage.ID) "Cari layanan berdasarkan kategori, anggaran, waktu pengiriman, dan rating." else "Search & filter services by category, budget, delivery time, and rating."
                        )
                        StepItem(
                            stepNumber = "2",
                            title = if (lang == AppLanguage.ID) "Sepakati & Pesan" else "Agree & Order",
                            desc = if (lang == AppLanguage.ID) "Bandingkan paket, lihat rincian biaya di muka, dan kirim kebutuhan Anda." else "Compare packages, view every fee upfront, and share your requirements."
                        )
                        StepItem(
                            stepNumber = "3",
                            title = if (lang == AppLanguage.ID) "Setujui & Beri Ulasan" else "Approve & Review",
                            desc = if (lang == AppLanguage.ID) "Minta revisi, setujui hasil akhir, dan berikan ulasan jujur." else "Request revisions, approve the delivery, and leave a genuine review."
                        )
                    } else {
                        StepItem(
                            stepNumber = "1",
                            title = if (lang == AppLanguage.ID) "Bangun Profil Anda" else "Build Your Profile",
                            desc = if (lang == AppLanguage.ID) "Tampilkan keahlian, bahasa, portofolio, dan status verifikasi." else "Showcase your skills, languages, portfolio, and verified identity."
                        )
                        StepItem(
                            stepNumber = "2",
                            title = if (lang == AppLanguage.ID) "Publikasikan Layanan" else "Publish a Service",
                            desc = if (lang == AppLanguage.ID) "Tentukan paket, harga transparan, dan waktu pengiriman Anda." else "Set clear packages, honest prices, and delivery turnarounds."
                        )
                        StepItem(
                            stepNumber = "3",
                            title = if (lang == AppLanguage.ID) "Kerjakan & Berkembang" else "Deliver & Grow",
                            desc = if (lang == AppLanguage.ID) "Selesaikan pesanan, kumpulkan ulasan, dan bangun reputasi global." else "Complete client orders, earn reviews, and build compounding reputation."
                        )
                    }
                }
            }
        }

        // Live Interactive Fee Calculator Teaser
        item {
            Box(modifier = Modifier.padding(top = 28.dp, start = 16.dp, end = 16.dp)) {
                Column {
                    TransparentFeeCard(
                        fees = SkilloraRepository.calculateOrderFees(10000L),
                        lang = lang
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = onNavigateToCalculator,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    ) {
                        Text(
                            text = if (lang == AppLanguage.ID) "Buka Kalkulator Biaya Penuh" else "Open Full Interactive Fee Calculator",
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                    }
                }
            }
        }

        // Trust & Safety Highlights
        item {
            Column(
                modifier = Modifier.padding(top = 28.dp, start = 16.dp, end = 16.dp)
            ) {
                Text(
                    text = if (lang == AppLanguage.ID) "Kepercayaan & Keamanan Terintegrasi" else "Trust & Safety, Built-In",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (lang == AppLanguage.ID) "Perlindungan adalah bagian arsitektur produk, bukan tambahan." else "Protections are part of the core product architecture.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    TrustItem(
                        icon = Icons.Default.VerifiedUser,
                        title = if (lang == AppLanguage.ID) "Identitas Terverifikasi" else "Verified IDs",
                        desc = if (lang == AppLanguage.ID) "Tanda centang resmi pada profil" else "Visible verified badge on talent",
                        modifier = Modifier.weight(1f)
                    )
                    TrustItem(
                        icon = Icons.Default.Lock,
                        title = if (lang == AppLanguage.ID) "Pesan Terlindungi" else "Protected Chat",
                        desc = if (lang == AppLanguage.ID) "Pencegahan spam & scam" else "Safe on-platform communication",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun ValuePropCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = ElectricBlue,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 11.sp
            )
        }
    }
}

@Composable
private fun CategoryPill(
    category: Category,
    lang: AppLanguage,
    onClick: () -> Unit
) {
    val name = if (lang == AppLanguage.ID) category.nameId else category.nameEn
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier
            .width(130.dp)
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(LightBlueSubtleFallback)
            ) {
                Icon(
                    imageVector = getCategoryIcon(category.slug),
                    contentDescription = name,
                    tint = ElectricBlue,
                    modifier = Modifier.size(22.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun FreelancerCard(
    provider: Provider,
    lang: AppLanguage
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.width(180.dp)
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProviderAvatar(name = provider.name, size = 46.dp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = provider.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (provider.verified) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Verified",
                        tint = ElectricBlue,
                        modifier = Modifier.size(15.dp)
                    )
                }
            }
            Text(
                text = provider.headline,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Text(
                    text = "${provider.country} · " + provider.languages.joinToString(", ").uppercase(),
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                )
            }
        }
    }
}

@Composable
private fun StepItem(
    stepNumber: String,
    title: String,
    desc: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(ElectricBlue)
        ) {
            Text(
                text = stepNumber,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = desc,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun TrustItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    desc: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = ElectricBlue,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = desc,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private val LightBlueSubtleFallback = Color(0xFFEFF6FF)
