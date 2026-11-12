package com.example.skillora.data

import com.example.skillora.R
import com.example.skillora.model.Category
import com.example.skillora.model.FeeBreakdown
import com.example.skillora.model.MarketplaceFilters
import com.example.skillora.model.Provider
import com.example.skillora.model.ServiceItem
import com.example.skillora.model.SortOption
import kotlin.math.ln
import kotlin.math.roundToLong

object SkilloraRepository {

    const val PLATFORM_COMMISSION_RATE = 0.05 // 5% flat fee

    val CATEGORIES = listOf(
        Category("graphic-design", "Graphic Design", "Desain Grafis", "palette"),
        Category("logo-branding", "Logo & Branding", "Logo & Branding", "brush"),
        Category("video-editing", "Video Editing", "Edit Video", "video"),
        Category("photography", "Photography", "Fotografi", "camera"),
        Category("illustration", "Illustration", "Ilustrasi", "draw"),
        Category("web-development", "Web Development", "Pengembangan Web", "web"),
        Category("mobile-apps", "Mobile Apps", "Aplikasi Mobile", "phone"),
        Category("ui-ux", "UI/UX Design", "Desain UI/UX", "design"),
        Category("programming", "Programming", "Pemrograman", "code"),
        Category("ai-services", "AI Services", "Layanan AI", "sparkles"),
        Category("content-writing", "Content Writing", "Penulisan Konten", "article"),
        Category("translation", "Translation", "Penerjemahan", "translate"),
        Category("data-entry", "Data Entry", "Entri Data", "table"),
        Category("virtual-assistance", "Virtual Assistance", "Asistensi Virtual", "support"),
        Category("social-media", "Social Media", "Media Sosial", "share"),
        Category("digital-marketing", "Digital Marketing", "Pemasaran Digital", "trending"),
        Category("seo", "SEO", "SEO & Search", "search"),
        Category("audio-production", "Audio Production", "Produksi Audio", "music"),
        Category("animation", "Animation", "Animasi", "animation"),
        Category("business-consulting", "Business Consulting", "Konsultasi Bisnis", "business"),
        Category("education", "Education & Tutoring", "Pendidikan & Bimbingan", "school"),
        Category("other", "Other Services", "Layanan Lainnya", "extension")
    )

    val PREVIEW_PROVIDERS = listOf(
        Provider("p1", "Amara Okafor", "Brand identity designer", "NG", listOf("en", "fr"), true),
        Provider("p2", "Rizky Pratama", "Full-stack web developer", "ID", listOf("id", "en"), true),
        Provider("p3", "Lucía Fernández", "Video editor & colorist", "AR", listOf("es", "en"), true),
        Provider("p4", "Kenji Watanabe", "Product & UI/UX designer", "JP", listOf("ja", "en"), false),
        Provider("p5", "Priya Raman", "AI & automation engineer", "IN", listOf("en", "hi", "ta"), true),
        Provider("p6", "Jonas Becker", "Translator DE · EN · NL", "DE", listOf("de", "en", "nl"), true),
        Provider("p7", "Siti Nurhaliza", "Social media strategist", "MY", listOf("ms", "en", "id"), false),
        Provider("p8", "Mateo Rossi", "Copywriter & SEO writer", "IT", listOf("it", "en"), true)
    )

    val PREVIEW_SERVICES = listOf(
        ServiceItem(
            id = "s1",
            title = "Complete brand identity with logo, palette and guidelines",
            category = "logo-branding",
            providerId = "p1",
            coverRes = R.drawable.cover_branding,
            priceFromMinor = 18000,
            deliveryDays = 5,
            rating = 4.9,
            reviewCount = 214,
            tags = listOf("logo", "branding", "brand guidelines", "identity")
        ),
        ServiceItem(
            id = "s2",
            title = "Responsive business website built with React & Next.js",
            category = "web-development",
            providerId = "p2",
            coverRes = R.drawable.cover_webdev,
            priceFromMinor = 35000,
            deliveryDays = 10,
            rating = 4.8,
            reviewCount = 132,
            tags = listOf("website", "react", "landing page", "frontend")
        ),
        ServiceItem(
            id = "s3",
            title = "Cinematic YouTube and reel video editing with color grading",
            category = "video-editing",
            providerId = "p3",
            coverRes = R.drawable.cover_video,
            priceFromMinor = 6000,
            deliveryDays = 3,
            rating = 4.9,
            reviewCount = 388,
            tags = listOf("youtube", "reels", "color grading", "video")
        ),
        ServiceItem(
            id = "s4",
            title = "Mobile app UI/UX design in Figma with interactive prototype",
            category = "ui-ux",
            providerId = "p4",
            coverRes = R.drawable.cover_uiux,
            priceFromMinor = 25000,
            deliveryDays = 7,
            rating = 4.7,
            reviewCount = 76,
            tags = listOf("figma", "app design", "wireframes", "prototype")
        ),
        ServiceItem(
            id = "s5",
            title = "Custom AI chatbot trained on your documents & knowledge base",
            category = "ai-services",
            providerId = "p5",
            coverRes = R.drawable.cover_ai,
            priceFromMinor = 40000,
            deliveryDays = 7,
            rating = 4.8,
            reviewCount = 58,
            tags = listOf("ai", "chatbot", "automation", "llm")
        ),
        ServiceItem(
            id = "s6",
            title = "Professional German ⇄ English technical translation",
            category = "translation",
            providerId = "p6",
            coverRes = R.drawable.cover_writing,
            priceFromMinor = 3000,
            deliveryDays = 2,
            rating = 5.0,
            reviewCount = 241,
            tags = listOf("german", "english", "translation", "localization")
        ),
        ServiceItem(
            id = "s7",
            title = "Monthly Instagram & TikTok viral content calendar & scripts",
            category = "social-media",
            providerId = "p7",
            coverRes = R.drawable.cover_uiux,
            priceFromMinor = 15000,
            deliveryDays = 5,
            rating = 4.6,
            reviewCount = 41,
            tags = listOf("instagram", "tiktok", "content calendar", "social")
        ),
        ServiceItem(
            id = "s8",
            title = "High-converting SEO blog articles that rank on Google page 1",
            category = "content-writing",
            providerId = "p8",
            coverRes = R.drawable.cover_writing,
            priceFromMinor = 5000,
            deliveryDays = 3,
            rating = 4.8,
            reviewCount = 167,
            tags = listOf("blog", "seo", "copywriting", "articles")
        ),
        ServiceItem(
            id = "s9",
            title = "Minimalist logo design package with unlimited revisions",
            category = "logo-branding",
            providerId = "p1",
            coverRes = R.drawable.cover_branding,
            priceFromMinor = 7500,
            deliveryDays = 2,
            rating = 4.9,
            reviewCount = 97,
            tags = listOf("logo", "minimal", "branding")
        ),
        ServiceItem(
            id = "s10",
            title = "Bug fixing and performance optimization for web applications",
            category = "programming",
            providerId = "p2",
            coverRes = R.drawable.cover_webdev,
            priceFromMinor = 4500,
            deliveryDays = 1,
            rating = 4.7,
            reviewCount = 203,
            tags = listOf("bug fix", "javascript", "performance", "typescript")
        ),
        ServiceItem(
            id = "s11",
            title = "In-depth Technical SEO audit with prioritized action plan",
            category = "seo",
            providerId = "p8",
            coverRes = R.drawable.cover_ai,
            priceFromMinor = 12000,
            deliveryDays = 4,
            rating = 4.6,
            reviewCount = 39,
            tags = listOf("seo audit", "technical seo", "google")
        ),
        ServiceItem(
            id = "s12",
            title = "Short-form promotional video ads for product launches",
            category = "video-editing",
            providerId = "p3",
            coverRes = R.drawable.cover_video,
            priceFromMinor = 9000,
            deliveryDays = 4,
            rating = 4.8,
            reviewCount = 122,
            tags = listOf("promo", "motion", "ads", "video")
        ),
        ServiceItem(
            id = "s13",
            title = "Enterprise workflow automation with AI agents & Zapier",
            category = "ai-services",
            providerId = "p5",
            coverRes = R.drawable.cover_ai,
            priceFromMinor = 22000,
            deliveryDays = 6,
            rating = 4.9,
            reviewCount = 64,
            tags = listOf("automation", "ai agents", "zapier", "integration")
        ),
        ServiceItem(
            id = "s14",
            title = "Website localization and cultural adaptation (Dutch & German)",
            category = "translation",
            providerId = "p6",
            coverRes = R.drawable.cover_writing,
            priceFromMinor = 8000,
            deliveryDays = 5,
            rating = 4.9,
            reviewCount = 58,
            tags = listOf("dutch", "german", "localization", "website")
        )
    )

    fun getProvider(providerId: String): Provider? {
        return PREVIEW_PROVIDERS.find { it.id == providerId }
    }

    fun getService(serviceId: String): ServiceItem? {
        return PREVIEW_SERVICES.find { it.id == serviceId }
    }

    fun getCategory(slug: String): Category? {
        return CATEGORIES.find { it.slug == slug }
    }

    /**
     * Transparent commission calculation for a completed order.
     * In integer cents to avoid floating point issues.
     */
    fun calculateOrderFees(
        subtotalMinor: Long,
        rate: Double = PLATFORM_COMMISSION_RATE
    ): FeeBreakdown {
        val safeSubtotal = if (subtotalMinor < 0) 0L else subtotalMinor
        val commissionMinor = (safeSubtotal * rate).roundToLong()
        val providerNetMinor = safeSubtotal - commissionMinor
        return FeeBreakdown(
            subtotalMinor = safeSubtotal,
            commissionMinor = commissionMinor,
            providerNetMinor = providerNetMinor,
            rate = rate
        )
    }

    /**
     * Pure search + filter + sort matching the marketplace filter logic
     */
    fun filterServices(
        services: List<ServiceItem>,
        filters: MarketplaceFilters
    ): List<ServiceItem> {
        val tokens = if (filters.query.isNotBlank()) {
            filters.query.lowercase().trim().split("\\s+".toRegex()).filter { it.isNotEmpty() }
        } else {
            emptyList()
        }

        val filtered = services.filter { s ->
            // Category filter
            if (filters.category != null && s.category != filters.category) {
                return@filter false
            }
            // Price bounds in dollars
            val price = s.priceInDollars
            if (filters.minPrice != null && price < filters.minPrice) {
                return@filter false
            }
            if (filters.maxPrice != null && price > filters.maxPrice) {
                return@filter false
            }
            // Delivery days
            if (filters.maxDelivery != null && s.deliveryDays > filters.maxDelivery) {
                return@filter false
            }
            // Minimum rating
            if (filters.minRating != null && s.rating < filters.minRating) {
                return@filter false
            }
            // Search query tokens
            if (tokens.isNotEmpty()) {
                val providerName = getProvider(s.providerId)?.name.orEmpty()
                val categoryName = getCategory(s.category)?.nameEn.orEmpty()
                val hay = (s.title + " " + categoryName + " " + s.tags.joinToString(" ") + " " + providerName).lowercase()
                if (!tokens.all { hay.contains(it) }) {
                    return@filter false
                }
            }
            true
        }

        return when (filters.sort) {
            SortOption.PRICE_ASC -> filtered.sortedBy { it.priceFromMinor }
            SortOption.PRICE_DESC -> filtered.sortedByDescending { it.priceFromMinor }
            SortOption.RATING -> filtered.sortedWith(
                compareByDescending<ServiceItem> { it.rating }
                    .thenByDescending { it.reviewCount }
            )
            SortOption.DELIVERY -> filtered.sortedBy { it.deliveryDays }
            SortOption.RECOMMENDED -> filtered.sortedWith { a, b ->
                val rDiff = calculateRelevance(b, tokens) - calculateRelevance(a, tokens)
                if (rDiff != 0) {
                    rDiff
                } else {
                    val scoreA = a.rating * ln(a.reviewCount.toDouble() + 10.0)
                    val scoreB = b.rating * ln(b.reviewCount.toDouble() + 10.0)
                    scoreB.compareTo(scoreA)
                }
            }
        }
    }

    private fun calculateRelevance(service: ServiceItem, tokens: List<String>): Int {
        if (tokens.isEmpty()) return 0
        val title = service.title.lowercase()
        val tags = service.tags.joinToString(" ").lowercase()
        return tokens.sumOf { t ->
            (if (title.contains(t)) 3 else 0) + (if (tags.contains(t)) 2 else 0)
        }
    }
}
