package com.promecarus.mycomposetesting

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.promecarus.mycomposetesting.ui.theme.MyComposeTestingTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorAppTest {
    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        rule.setContent { MyComposeTestingTheme { CalculatorApp() } }
    }

    @Test
    fun calculate_area_of_rectangle_correct() {
        rule.apply {
            onNodeWithText(activity.getString(R.string.enter_length)).performTextInput("3")
            onNodeWithText(activity.getString(R.string.enter_width)).performTextInput("4")
            onNodeWithText(activity.getString(R.string.count)).performClick()
            onNodeWithText(activity.getString(R.string.result, 12.0)).assertExists()
        }
    }

    @Test
    fun wrong_input_not_calculated() {
        rule.apply {
            onNodeWithText(activity.getString(R.string.enter_length)).performTextInput("..3")
            onNodeWithText(activity.getString(R.string.enter_width)).performTextInput("4")
            onNodeWithText(activity.getString(R.string.count)).performClick()
            onNodeWithText(activity.getString(R.string.result, 0.0)).assertExists()
        }
    }
}
