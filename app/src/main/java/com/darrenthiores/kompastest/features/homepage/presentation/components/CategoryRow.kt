package com.darrenthiores.kompastest.features.homepage.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.darrenthiores.kompastest.app.theme.GreenRegular
import com.darrenthiores.kompastest.features.homepage.presentation.models.HomeCategory

@Composable
fun CategoryRow(
    modifier: Modifier = Modifier,
    selectedCategory: HomeCategory?,
    onSelectCategory: (HomeCategory) -> Unit
) {
    val listState = rememberLazyListState()

    LaunchedEffect(true) {
        listState.animateScrollToItem(
            index = 1
        )
    }

    LazyRow (
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Top,
        state = listState
    ) {
        itemsIndexed(
            items = HomeCategory.entries.toTypedArray(),
            key = { _, category -> category.name }
        ) { index, category ->
            Column(
                modifier = Modifier
                    .width(IntrinsicSize.Min)
            ) {
                Text(
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .clickable {
                            onSelectCategory(category)
                        }
                        .padding(16.dp),
                    text = category.displayText(),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.White
                    )
                )

                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth(),
                    thickness = 5.dp,
                    color = if (selectedCategory == category) GreenRegular else Color.Transparent
                )
            }
        }
    }
}