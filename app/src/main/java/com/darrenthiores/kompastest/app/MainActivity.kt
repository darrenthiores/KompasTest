package com.darrenthiores.kompastest.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.darrenthiores.kompastest.app.theme.KompasTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KompasTestTheme {

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