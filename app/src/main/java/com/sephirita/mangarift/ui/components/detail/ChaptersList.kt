package com.sephirita.mangarift.ui.components.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sephirita.mangarift.ui.components.sohprateste.Chapter

@Composable
fun ChaptersList(
    modifier: Modifier = Modifier,
    chaptersList: Map<Float, List<Chapter>>,
    expandedChapterList: Map<Float, Boolean>,
//    sortChaptersCallback: (Boolean) -> Unit,
    expandChapterCallback: (Float) -> Unit
) {
//    var sortInAscending by rememberSaveable { mutableStateOf(true) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 8.dp)
    ) {
        Column {
//            TODO COMES IN V2
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.End
//            ) {
//                Box(
//                    modifier = Modifier.clickable {
//                        sortInAscending = true
//                        sortChaptersCallback(true)
//                    }
//                ) {
//                    Text(
//                        text = "Crescente",
//                        color = if (sortInAscending)
//                            Color.White
//                        else
//                            Color.Gray,
//                        fontSize = 12.sp
//                    )
//                }
//                Spacer(modifier = Modifier.width(4.dp))
//                Box(
//                    modifier = Modifier.clickable {
//                        sortInAscending = false
//                        sortChaptersCallback(false)
//                    }
//                ) {
//                    Text(
//                        text = "Decrescente",
//                        color = if (!sortInAscending)
//                            Color.White
//                        else
//                            Color.Gray,
//                        fontSize = 12.sp
//                    )
//                }
//            }

            chaptersList.forEach { (chapterNumber, chapterList) ->
                val isExpanded = expandedChapterList[chapterNumber] ?: false
                HorizontalDivider()
                ChapterListItem(
                    chapterNumber = chapterNumber,
                    chapters = chapterList,
                    isExpanded = isExpanded,
                    onClick = {
                        expandChapterCallback(chapterNumber)
                    }
                )
            }
        }
    }
}