package com.fetchrewards.fetchrewardscodetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.fetchrewards.fetchrewardscodetest.ui.theme.FetchRewardsCodeTestTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main activity for the app where Composable UI is displayed.
 */
@AndroidEntryPoint
class FetchRewardsCodeTestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchRewardsCodeTestTheme {
                FetchRewardsCodeTestScreen()
            }
        }
    }
}