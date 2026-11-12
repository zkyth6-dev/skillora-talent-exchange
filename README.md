# Skillora — Android Application

**"Your Skills. Your Opportunity."**

Skillora is a modern, production-grade native Android application for a global digital services and freelance talent exchange marketplace. Built with **Kotlin** and **Jetpack Compose**, adhering to **Material Design 3 (M3)** guidelines.

---

## 🌟 Key Features

### 1. Global Digital Services Marketplace
- **22 Core Categories**: Ranging from Logo & Branding, Web Development, Mobile Apps, UI/UX Design, to AI Services, Video Editing, Translation, and Technical SEO.
- **Preview Talent Catalog**: 8 verified global freelancers with multi-language capabilities and credentials.
- **Handcrafted Service Packages**: Real pricing, delivery turnarounds, rating & review breakdowns, and commercial usage deliverables.

### 2. Pure Marketplace Search & Filter Engine
- Instant multi-token search across service titles, provider names, category names, and keyword tags.
- Flexible filtering sheet:
  - Category selector
  - Budget / Price ceiling slider ($20 – $500+)
  - Delivery turnaround filter (1 day, ≤3 days, ≤7 days, ≤14 days)
  - Minimum seller rating threshold (Any, 4.5+, 4.8+)
- Multi-dimensional sorting:
  - Recommended (relevance + Bayesian rating score)
  - Price: Low to High
  - Price: High to Low
  - Highest Rated
  - Fastest Delivery

### 3. Transparent 5% Fee Architecture & Live Calculator
- **Zero Hidden Fees**: A flat 5% platform commission shown transparently upfront before every order.
- **Integer Cents Precision**: All fee calculations (`calculateOrderFees`) operate on minor units (integer cents) to eliminate floating-point drift.
- **Interactive Calculator**: Live sliders and preset amounts ($50, $100, $250, $500, $1000) allowing clients and freelancers to test any transaction amount in USD and calculate net payout.

### 4. Service Ordering & Inquiry Flow
- Complete service package view with included deliverables, milestones, and provider profiles.
- Interactive order submission form with requirements capture, generating tracked order inquiries.

### 5. Multi-Language & Personalization
- Full localization for **English** and **Bahasa Indonesia** with zero hardcoded UI strings.
- Light and Dark Material 3 theme switching.
- Dual-role account switcher: "One account, two roles: Hire & Sell".

---

## 🏗️ Technical Stack & Architecture

- **Language**: Kotlin 2.0+
- **UI Framework**: Jetpack Compose (Material Design 3)
- **Architecture**: MVVM with unidirectional data flow (UDF)
- **State Management**: Kotlin Coroutines & `StateFlow` / `collectAsState`
- **Navigation**: Type-safe composable routing with hardware `BackHandler`
- **Design System**:
  - **Midnight Navy** (`#0B1220`) & **Deep Blue** (`#142C50`)
  - **Electric Blue** (`#3478F6`) Primary
  - **Cyan Glow** (`#42D9F5`) Highlight
- **Launcher Icon**: Custom Material adaptive icon with circular mipmaps generated across mdpi, hdpi, xhdpi, xxhdpi, and xxxhdpi.

---

## 📂 Project Structure

```
├── app/
│   ├── build.gradle.kts
│   ├── proguard-rules.pro
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/example/skillora/
│       │   ├── MainActivity.kt
│       │   ├── data/
│       │   │   ├── SkilloraRepository.kt
│       │   │   └── I18nStrings.kt
│       │   ├── model/
│       │   │   └── Models.kt
│       │   ├── ui/
│       │   │   ├── components/CommonComponents.kt
│       │   │   ├── screens/
│       │   │   │   ├── HomeScreen.kt
│       │   │   │   ├── MarketplaceScreen.kt
│       │   │   │   ├── ServiceDetailScreen.kt
│       │   │   │   ├── FeeCalculatorScreen.kt
│       │   │   │   └── ProfileScreen.kt
│       │   │   └── theme/
│       │   │       ├── Color.kt
│       │   │       ├── Theme.kt
│       │   │       └── Type.kt
│       │   └── viewmodel/
│       │       └── SkilloraViewModel.kt
│       └── res/
│           ├── drawable/
│           ├── mipmap-*/
│           └── values/strings.xml
├── gradle/
│   └── libs.versions.toml
├── build.gradle.kts
├── settings.gradle.kts
└── metadata.json
```
