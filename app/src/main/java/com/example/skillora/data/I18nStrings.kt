package com.example.skillora.data

import com.example.skillora.model.AppLanguage

object I18nStrings {

    fun brand(lang: AppLanguage) = "Skillora"
    fun tagline(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Your Skills. Your Opportunity."
        AppLanguage.ID -> "Keahlian Anda. Peluang Anda."
    }

    fun heroEyebrow(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "The global marketplace for digital services"
        AppLanguage.ID -> "Pasar global untuk layanan digital"
    }

    fun heroSubtitle(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Skillora connects people who have skills with people and businesses who need them — anywhere in the world. Hire trusted talent, or turn what you know into real work."
        AppLanguage.ID -> "Skillora menghubungkan orang yang memiliki keterampilan dengan orang dan bisnis yang membutuhkannya — di mana saja di seluruh dunia."
    }

    fun searchPlaceholder(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Try “logo design”, “video editing”, “translation”…"
        AppLanguage.ID -> "Coba “desain logo”, “edit video”, “penerjemahan”…"
    }

    fun popularTags(lang: AppLanguage) = listOf("Logo design", "Website", "Video editing", "AI chatbot", "Translation")

    fun previewBadge(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Preview data"
        AppLanguage.ID -> "Data Pratinjau"
    }

    fun previewNote(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Sample listings for development. Real services appear here once providers publish them."
        AppLanguage.ID -> "Daftar sampel untuk pengembangan. Layanan nyata akan muncul setelah penyedia mempublikasikannya."
    }

    fun categoriesTitle(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Explore by category"
        AppLanguage.ID -> "Jelajahi berdasarkan kategori"
    }

    fun categoriesSubtitle(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "From design to data — 22 categories built to grow with the community."
        AppLanguage.ID -> "Dari desain hingga data — 22 kategori yang siap berkembang bersama komunitas."
    }

    fun featuredServicesTitle(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Featured services"
        AppLanguage.ID -> "Layanan unggulan"
    }

    fun featuredFreelancersTitle(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Featured freelancers"
        AppLanguage.ID -> "Freelancer unggulan"
    }

    fun transparentFeeTitle(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "No hidden fees. Ever."
        AppLanguage.ID -> "Tanpa biaya tersembunyi. Selamanya."
    }

    fun transparentFeeSubtitle(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "A flat 5% commission applies only when an order is successfully completed."
        AppLanguage.ID -> "Komisi tetap 5% hanya berlaku saat pesanan berhasil diselesaikan."
    }

    fun howItWorksTitle(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "How Skillora works"
        AppLanguage.ID -> "Cara kerja Skillora"
    }

    fun howItWorksSubtitle(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "A clear path from first message to finished work — for both sides."
        AppLanguage.ID -> "Alur yang jelas dari pesan pertama hingga hasil kerja selesai — untuk kedua belah pihak."
    }

    fun deliveryInDays(days: Int, lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> if (days == 1) "1-day delivery" else "$days-day delivery"
        AppLanguage.ID -> if (days == 1) "Pengiriman 1 hari" else "Pengiriman $days hari"
    }

    fun fromPrice(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "From"
        AppLanguage.ID -> "Mulai dari"
    }

    fun verified(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Verified"
        AppLanguage.ID -> "Terverifikasi"
    }

    fun reviewsCount(count: Int, lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "$count reviews"
        AppLanguage.ID -> "$count ulasan"
    }

    fun orderNow(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Continue to Order"
        AppLanguage.ID -> "Lanjutkan Pemesanan"
    }

    fun contactProvider(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Contact Freelancer"
        AppLanguage.ID -> "Hubungi Freelancer"
    }
}
