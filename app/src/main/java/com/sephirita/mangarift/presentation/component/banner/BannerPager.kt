package com.sephirita.mangarift.presentation.component.banner

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sephirita.mangarift.domain.model.Manga

@Composable
fun BannerPager(
    items: List<Manga>,
    detailNavigation: (String) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { items.size })
    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = if (items.isNotEmpty()) 0.dp else 80.dp)
            .height(if (items.isNotEmpty()) 360.dp else 0.dp)

    ) {
        val currentItem = items[it]
        Banner(item = currentItem, onClick = { detailNavigation(currentItem.id) })
    }
}

