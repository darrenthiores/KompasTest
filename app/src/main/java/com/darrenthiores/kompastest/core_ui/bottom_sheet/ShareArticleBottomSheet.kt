package com.darrenthiores.kompastest.core_ui.bottom_sheet

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material.icons.outlined.IosShare
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.darrenthiores.kompastest.R
import com.darrenthiores.kompastest.app.theme.GrayDark
import com.darrenthiores.kompastest.app.theme.GrayLight
import com.darrenthiores.kompastest.app.theme.GrayRegular
import com.darrenthiores.kompastest.core.models.articles.Article

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareArticleBottomSheet(
    modifier: Modifier = Modifier,
    article: Article,
    onDismiss: () -> Unit
) {
    val shareUrl = remember(article) {
        "https://www.kompas.id/artikel/${article.title.lowercase().replace(" ", "-")}"
    }

    AppBottomSheet(
        modifier = modifier,
        title = "Bagikan",
        onDismiss = onDismiss,
        withIcon = true
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 8.dp,
                )
                .padding(
                    top = 16.dp,
                    bottom = 48.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            ArticlePreview(
                modifier = Modifier,
                article = article
            )

            ShareRow(
                url = shareUrl
            )
        }
    }
}

@Composable
private fun ColumnScope.ArticlePreview(
    modifier: Modifier = Modifier,
    article: Article
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .weight(1f),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
                .background(
                    color = GrayLight
                )
                .blur(10.dp),
            model = article.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(
                    color = MaterialTheme.colorScheme.surface
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.End)
                    .height(24.dp),
                painter = painterResource(R.drawable.logo_kompas),
                contentDescription = "Kompas Logo"
            )

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        color = GrayLight
                    ),
                model = article.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            article.label?.let { label ->
                Text(
                    text = label.uppercase(),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium,
                        color = GrayRegular
                    )
                )
            }

            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}

@Composable
private fun ShareRow(
    modifier: Modifier = Modifier,
    url: String
) {
    val context = LocalContext.current

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        verticalAlignment = Alignment.Top
    ) {
        ShareCircle(
            modifier = Modifier,
            icon = Icons.Default.Link,
            title = "Salin Tautan",
            onClick = {
                val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clipData = ClipData.newPlainText("Article Link", url)
                clipboardManager.setPrimaryClip(clipData)
                Toast.makeText(context, "Tautan disalin", Toast.LENGTH_SHORT).show()
            }
        )

        ShareCircle(
            modifier = Modifier,
            icon = Icons.Default.Whatsapp,
            title = "WhatsApp",
            onClick = {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, url)
                    type = "text/plain"
                    setPackage("com.whatsapp")
                }
                try {
                    context.startActivity(sendIntent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(context, "WhatsApp tidak terpasang", Toast.LENGTH_SHORT).show()
                }
            }
        )

        ShareCircle(
            modifier = Modifier,
            icon = Icons.Outlined.PhotoCamera,
            title = "Instagram Stories",
            onClick = {
                val intent = Intent("com.instagram.share.ADD_TO_STORY").apply {
                    type = "text/plain"
                    putExtra("source_application", context.packageName)
                    putExtra(Intent.EXTRA_TEXT, url)
                }
                try {
                    context.startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(context, "Instagram tidak terpasang", Toast.LENGTH_SHORT).show()
                }
            }
        )

        ShareCircle(
            modifier = Modifier,
            icon = Icons.Outlined.IosShare,
            title = "Bagikan Via...",
            onClick = {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, url)
                }
                val chooser = Intent.createChooser(intent, "Bagikan Artikel")
                context.startActivity(chooser)
            }
        )
    }
}

@Composable
private fun RowScope.ShareCircle(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .weight(1f),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(CircleShape)
                .clickable {
                    onClick.invoke()
                }
                .background(
                    color = if (isSystemInDarkTheme()) GrayDark
                    else GrayLight
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp),
                imageVector = icon,
                contentDescription = title,
                tint = MaterialTheme.colorScheme.onSurface
            )
        }

        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium.copy(
                textAlign = TextAlign.Center
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}