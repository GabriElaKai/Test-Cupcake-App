package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.cupcake.R
import com.example.cupcake.ui.SelectOptionScreen // Falls dein Pfad leicht abweicht, passt Android Studio das autom. an
import org.junit.Rule
import org.junit.Test

class CupcakeOrderScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun selectOptionScreen_verifyContent() {
        // Given: Eine Liste von Optionen und ein Zwischenbetrag
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subtotal = "$100"

        // When: Der SelectOptionScreen wird direkt geladen
        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subtotal, options = flavors)
        }

        // Then: Alle Optionen müssen auf dem Bildschirm sichtbar sein
        flavors.forEach { flavor ->
            composeTestRule.onNodeWithText(flavor).assertIsDisplayed()
        }

        // Und der Zwischenbetrag muss korrekt formatiert angezeigt werden
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.subtotal_price,
                subtotal
            )
        ).assertIsDisplayed()

        // Und der "Next"-Button muss standardmäßig deaktiviert sein (da noch nichts ausgewählt wurde)
        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
    }
}