package com.darrenthiores.kompastest.features.homepage.presentation.components.blocks

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil3.compose.AsyncImage
import com.darrenthiores.kompastest.app.theme.GrayRegular
import com.darrenthiores.kompastest.core.models.advertisement.Ads

@Composable
fun AdsBlockView(
    modifier: Modifier = Modifier,
    ads: Ads
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 24.dp,
                horizontal = 32.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surface
                )
                .padding(
                    vertical = 2.dp,
                    horizontal = 8.dp
                ),
            text = "Iklan - Gulir ke bawah untuk melanjutkan",
            style = MaterialTheme.typography.labelLarge.copy(
                color = GrayRegular,
                textAlign = TextAlign.Center
            )
        )

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, ads.url.toUri())
                    context.startActivity(intent)
                },
            model = ads.imagePath,
            contentDescription = "Ads Image",
            contentScale = ContentScale.FillWidth
        )
    }
}