package com.darrenthiores.kompastest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.darrenthiores.kompastest.app.theme.KompasTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KompasTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                }
            }
        }
    }
}

/**
 * Requirement:
 * - Homepage
 * - Article Detail
 *   -> bookmark (local)
 * - Audio (plus)
 *
 * Tech Stack:
 * - KSerializer to decode json V
 * - Room for bookmark V
 * - Coil for image V
 * - Coroutines V
 * - Hilt for DI V
 *
 */