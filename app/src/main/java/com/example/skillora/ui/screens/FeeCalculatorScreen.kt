package com.example.skillora.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skillora.data.I18nStrings
import com.example.skillora.model.AppLanguage
import com.example.skillora.model.FeeBreakdown
import com.example.skillora.ui.components.TransparentFeeCard
import com.example.skillora.ui.theme.ElectricBlue

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FeeCalculatorScreen(
    currentFees: FeeBreakdown,
    lang: AppLanguage,
    onAmountChange: (Double) -> Unit,
    modifier: Modifier = Modifier
) {
    var inputAmountText by remember(currentFees.subtotalDollars) {
        mutableStateOf(currentFees.subtotalDollars.toInt().toString())
    }

    val presetAmounts = listOf(50.0, 100.0, 200.0, 350.0, 500.0, 1000.0)

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
            .testTag("fee_calculator_screen")
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(ElectricBlue.copy(alpha = 0.15f))
            ) {
                Icon(
                    imageVector = Icons.Default.Calculate,
                    contentDescription = null,
                    tint = ElectricBlue,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = if (lang == AppLanguage.ID) "Kalkulator Komisi Platform" else "Platform Fee Calculator",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (lang == AppLanguage.ID) "Transparansi 100% sebelum memesan" else "100% upfront transparency on every order",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Preset Chips
        Text(
            text = if (lang == AppLanguage.ID) "Pilih Jumlah Sampel:" else "Select Sample Order Amount:",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(10.dp))

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            presetAmounts.forEach { amount ->
                val isSelected = currentFees.subtotalDollars == amount
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = if (isSelected) ElectricBlue else MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.clickable {
                        inputAmountText = amount.toInt().toString()
                        onAmountChange(amount)
                    }
                ) {
                    Text(
                        text = "$${amount.toInt()}",
                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Custom Input
        OutlinedTextField(
            value = inputAmountText,
            onValueChange = { newVal ->
                if (newVal.all { it.isDigit() || it == '.' }) {
                    inputAmountText = newVal
                    val parsed = newVal.toDoubleOrNull() ?: 0.0
                    onAmountChange(parsed)
                }
            },
            label = {
                Text(if (lang == AppLanguage.ID) "Jumlah Pesanan Kustom ($ USD)" else "Custom Order Amount ($ USD)")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("calculator_amount_input"),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // The Breakdown Card
        TransparentFeeCard(fees = currentFees, lang = lang)

        Spacer(modifier = Modifier.height(24.dp))

        // Key Principles
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text(
                    text = if (lang == AppLanguage.ID) "Mengapa Skillora Berbeda" else "Why Skillora Fee Model is Different",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                val points = if (lang == AppLanguage.ID) listOf(
                    "Komisi tetap 5% adalah yang terendah di industri pasar digital global.",
                    "Tidak ada potongan tak terduga, biaya penarikan terselubung, atau komisi berjenjang.",
                    "Perhitungan dilakukan dalam satuan minor bilangan bulat (sen integer) untuk mencegah pembulatan mengambang (float drift).",
                    "Freelancer menerima 95% penuh dari hasil kerja mereka tanpa penundaan semena-mena."
                ) else listOf(
                    "Flat 5% platform fee is among the lowest rates in the global freelance industry.",
                    "Zero hidden transaction penalties, withdrawal traps, or opaque tiered markups.",
                    "Calculated in integer minor units (cents) to mathematically eliminate floating point drift.",
                    "Freelancers keep 95% net of their hard-earned labor with clear milestone escrows."
                )

                points.forEach { pt ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = ElectricBlue,
                            modifier = Modifier
                                .size(18.dp)
                                .padding(top = 2.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = pt,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}
