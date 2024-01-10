package com.example.myapplication.feature.presentation.media.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gallery.feature.presentation.media.SortOrder
import com.example.myapplication.R

@Composable
fun SortBar(
    sortOrder: SortOrder,
    onSortOrderChange: (SortOrder) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Box {
            Spacer(modifier = Modifier.width(4.dp))
            IconButton(onClick = {
                onSortOrderChange(
                    when (sortOrder) {
                        SortOrder.Grid -> SortOrder.Linear
                        SortOrder.Linear -> SortOrder.Grid
                    }
                )
            }, modifier = Modifier.size(18.dp)) {
                Icon(
                    when (sortOrder) {
                        SortOrder.Grid -> painterResource(R.drawable.ic_grid)
                        SortOrder.Linear -> painterResource(R.drawable.ic_linear)
                    },
                    contentDescription = null,
                    tint = Color.Gray,
                )
            }
        }
    }
}