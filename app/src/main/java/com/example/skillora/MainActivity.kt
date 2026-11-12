package com.example.skillora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.skillora.data.SkilloraRepository
import com.example.skillora.model.AppLanguage
import com.example.skillora.ui.screens.FeeCalculatorScreen
import com.example.skillora.ui.screens.HomeScreen
import com.example.skillora.ui.screens.MarketplaceScreen
import com.example.skillora.ui.screens.ProfileScreen
import com.example.skillora.ui.screens.ServiceDetailScreen
import com.example.skillora.ui.theme.ElectricBlue
import com.example.skillora.ui.theme.SkilloraTheme
import com.example.skillora.viewmodel.SkilloraViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: SkilloraViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val uiState by viewModel.uiState.collectAsState()

            SkilloraTheme(darkTheme = uiState.isDarkMode) {
                MainApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun MainApp(viewModel: SkilloraViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.orderSuccessMessage) {
        uiState.orderSuccessMessage?.let { msg ->
            snackbarHostState.showSnackbar(msg)
            viewModel.dismissOrderSuccess()
        }
    }

    // Hardware back navigation handler
    BackHandler(enabled = uiState.selectedServiceId != null || uiState.currentTab != 0) {
        if (uiState.selectedServiceId != null) {
            viewModel.closeServiceDetail()
        } else if (uiState.currentTab != 0) {
            viewModel.selectTab(0)
        }
    }

    val selectedService = uiState.selectedServiceId?.let { SkilloraRepository.getService(it) }

    if (selectedService != null) {
        ServiceDetailScreen(
            service = selectedService,
            lang = uiState.language,
            onBack = { viewModel.closeServiceDetail() },
            onSubmitOrder = { service, req ->
                viewModel.submitOrder(service, req)
                viewModel.closeServiceDetail()
                viewModel.selectTab(3) // Switch to profile/orders tab
            }
        )
    } else {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            bottomBar = {
                NavigationBar(
                    modifier = Modifier.testTag("bottom_nav_bar")
                ) {
                    val lang = uiState.language
                    val items = listOf(
                        Triple(0, if (lang == AppLanguage.ID) "Beranda" else "Home", Icons.Default.Home),
                        Triple(1, if (lang == AppLanguage.ID) "Jelajahi" else "Explore", Icons.Default.Explore),
                        Triple(2, if (lang == AppLanguage.ID) "Kalkulator" else "Fee Calc", Icons.Default.Calculate),
                        Triple(3, if (lang == AppLanguage.ID) "Akun" else "Profile", Icons.Default.Person)
                    )

                    items.forEach { (index, label, icon) ->
                        NavigationBarItem(
                            selected = uiState.currentTab == index,
                            onClick = { viewModel.selectTab(index) },
                            icon = { Icon(imageVector = icon, contentDescription = label) },
                            label = { Text(text = label) },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = ElectricBlue.copy(alpha = 0.2f)
                            ),
                            modifier = Modifier.testTag("nav_tab_$index")
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Crossfade(
                targetState = uiState.currentTab,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                label = "tab_crossfade"
            ) { tab ->
                when (tab) {
                    0 -> HomeScreen(
                        lang = uiState.language,
                        onNavigateToMarketplace = { queryOrCategory ->
                            if (queryOrCategory != null) {
                                val isCategory = SkilloraRepository.getCategory(queryOrCategory) != null
                                if (isCategory) {
                                    viewModel.selectCategory(queryOrCategory)
                                } else {
                                    viewModel.setQuery(queryOrCategory)
                                }
                            }
                            viewModel.selectTab(1)
                        },
                        onServiceClick = { serviceId ->
                            viewModel.openServiceDetail(serviceId)
                        },
                        onNavigateToCalculator = {
                            viewModel.selectTab(2)
                        }
                    )
                    1 -> MarketplaceScreen(
                        services = uiState.filteredServices,
                        filters = uiState.filters,
                        lang = uiState.language,
                        onSearchChange = { viewModel.setQuery(it) },
                        onCategorySelect = { viewModel.selectCategory(it) },
                        onSortChange = { viewModel.setSort(it) },
                        onUpdateFilters = { cat, minP, maxP, maxD, minR ->
                            viewModel.updateFilters(cat, minP, maxP, maxD, minR)
                        },
                        onClearFilters = { viewModel.clearFilters() },
                        onServiceClick = { serviceId ->
                            viewModel.openServiceDetail(serviceId)
                        }
                    )
                    2 -> FeeCalculatorScreen(
                        currentFees = uiState.calculatorFees,
                        lang = uiState.language,
                        onAmountChange = { viewModel.setCalculatorAmount(it) }
                    )
                    3 -> ProfileScreen(
                        currentLanguage = uiState.language,
                        isDarkMode = uiState.isDarkMode,
                        orders = uiState.userOrders,
                        onLanguageChange = { viewModel.setLanguage(it) },
                        onToggleDarkMode = { viewModel.toggleDarkMode() }
                    )
                }
            }
        }
    }
}
