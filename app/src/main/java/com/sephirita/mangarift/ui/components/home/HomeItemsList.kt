package com.sephirita.mangarift.ui.components.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sephirita.mangarift.R
import com.sephirita.mangarift.domain.Manga

@Composable
fun HomeItemsList(
    modifier: Modifier = Modifier,
    listTitle: String,
    items: List<Manga>,
    detailNavigation: (String) -> Unit,
    searchNavigation: () -> Unit
) {
    Box(
        modifier = modifier.clipToBounds()
    ) {
        Column(
            modifier = Modifier
                .clipToBounds(),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = listTitle,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 8.dp, bottom = 6.dp)
                )
                Icon(
                    modifier = Modifier.clickable(onClick = searchNavigation),
                    painter = painterResource(id = R.drawable.ic_round_arrow_forward),
                    contentDescription = "Icone para avançar na navegação"
                )
            }
            LazyRow(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.Start),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                items(
                    items = items,
                    itemContent = {
                        HomeListItem(item = it, onClick = { detailNavigation(it.id) })
                    }
                )
            }
        }
    }
}