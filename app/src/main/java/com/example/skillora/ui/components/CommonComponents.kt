package com.example.skillora.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Animation
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.DesignServices
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material.icons.filled.TableChart
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material.icons.filled.Web
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skillora.data.I18nStrings
import com.example.skillora.data.SkilloraRepository
import com.example.skillora.model.AppLanguage
import com.example.skillora.model.FeeBreakdown
import com.example.skillora.model.Provider
import com.example.skillora.model.ServiceItem
import com.example.skillora.ui.theme.AmberStar
import com.example.skillora.ui.theme.CyanGlow
import com.example.skillora.ui.theme.DeepBlue
import com.example.skillora.ui.theme.ElectricBlue
import com.example.skillora.ui.theme.MidnightNavy
import java.util.Locale

@Composable
fun PreviewBadge(
    modifier: Modifier = Modifier,
    withNote: Boolean = false,
    lang: AppLanguage = AppLanguage.EN
) {
    Column(modifier = modifier) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.8f),
            modifier = Modifier.testTag("preview_badge")
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(13.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = I18nStrings.previewBadge(lang),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        if (withNote) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = I18nStrings.previewNote(lang),
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun ProviderAvatar(
    name: String,
    modifier: Modifier = Modifier,
    size: Dp = 28.dp
) {
    val initials = name.split(" ")
        .mapNotNull { it.firstOrNull()?.toString() }
        .take(2)
        .joinToString("")
        .ifEmpty { "P" }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(ElectricBlue, CyanGlow)
                )
            )
    ) {
        Text(
            text = initials,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = (size.value * 0.4f).sp
        )
    }
}

@Composable
fun ServiceCard(
    service: ServiceItem,
    provider: Provider?,
    lang: AppLanguage,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val category = SkilloraRepository.getCategory(service.category)
    val categoryName = if (lang == AppLanguage.ID) category?.nameId else category?.nameEn

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag("service_card_${service.id}")
    ) {
        Column {
            // Cover Image with Category Tag
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 10f)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            ) {
                Image(
                    painter = painterResource(id = service.coverRes),
                    contentDescription = service.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
                // Category Chip Overlay
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MidnightNavy.copy(alpha = 0.82f),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(10.dp)
                ) {
                    Text(
                        text = categoryName ?: service.category,
                        color = Color.White,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Column(modifier = Modifier.padding(14.dp)) {
                // Provider Info
                if (provider != null) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ProviderAvatar(name = provider.name, size = 24.dp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = provider.name,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f)
                        )
                        if (provider.verified) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = I18nStrings.verified(lang),
                                tint = ElectricBlue,
                                modifier = Modifier.size(15.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                // Title
                Text(
                    text = service.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 19.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Rating & Reviews
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = AmberStar,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = String.format(Locale.US, "%.1f", service.rating),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "(${service.reviewCount})",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Bottom row: Delivery & Starting Price
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AccessTime,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = I18nStrings.deliveryInDays(service.deliveryDays, lang),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = I18nStrings.fromPrice(lang).uppercase(),
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "$" + String.format(Locale.US, "%.0f", service.priceInDollars),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TransparentFeeCard(
    fees: FeeBreakdown,
    lang: AppLanguage,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = DeepBlue
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = I18nStrings.transparentFeeTitle(lang),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = I18nStrings.transparentFeeSubtitle(lang),
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFCBD5E1)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MidnightNavy.copy(alpha = 0.5f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = if (lang == AppLanguage.ID) "Total Pesanan" else "Order Total",
                            color = Color(0xFF94A3B8),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "$" + String.format(Locale.US, "%.2f", fees.subtotalDollars),
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = if (lang == AppLanguage.ID) "Komisi Platform (5%)" else "Platform Fee (5%)",
                            color = Color(0xFF94A3B8),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "- $" + String.format(Locale.US, "%.2f", fees.commissionDollars),
                            color = CyanGlow,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color(0xFF334155))
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (lang == AppLanguage.ID) "Penyedia Menerima" else "Provider Receives",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "$" + String.format(Locale.US, "%.2f", fees.providerNetDollars),
                            color = CyanGlow,
                            fontWeight = FontWeight.ExtraBold,
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                }
            }
        }
    }
}

fun getCategoryIcon(slug: String): ImageVector {
    return when (slug) {
        "graphic-design" -> Icons.Default.Palette
        "logo-branding" -> Icons.Default.Brush
        "video-editing" -> Icons.Default.Videocam
        "photography" -> Icons.Default.CameraAlt
        "illustration" -> Icons.Default.DesignServices
        "web-development" -> Icons.Default.Web
        "mobile-apps" -> Icons.Default.Smartphone
        "ui-ux" -> Icons.Default.DesignServices
        "programming" -> Icons.Default.Code
        "ai-services" -> Icons.Default.AutoAwesome
        "content-writing" -> Icons.Default.Article
        "translation" -> Icons.Default.Translate
        "data-entry" -> Icons.Default.TableChart
        "virtual-assistance" -> Icons.Default.SupportAgent
        "social-media" -> Icons.Default.Share
        "digital-marketing" -> Icons.Default.TrendingUp
        "seo" -> Icons.Default.Search
        "audio-production" -> Icons.Default.Headphones
        "animation" -> Icons.Default.Animation
        "business-consulting" -> Icons.Default.BusinessCenter
        "education" -> Icons.Default.School
        else -> Icons.Default.Extension
    }
}
