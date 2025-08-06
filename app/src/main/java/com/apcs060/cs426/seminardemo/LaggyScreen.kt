package com.apcs060.cs426.seminardemo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import java.util.UUID

@Composable
fun LaggyScreen(itemSize: Int) {
    val items = List(itemSize) { index ->
        Item(
            id = UUID.randomUUID(),
            title = "Item $index",
            image = "https://picsum.photos/500/500?random=$index"
        )
    }

    val likes = remember { mutableStateMapOf<UUID, Int>() }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        Text(
            text = "Laggy Screen",
            fontSize = 22.sp,
            lineHeight = 26.sp,
            fontWeight = FontWeight.Medium,
        )

        items.forEach { item ->
            val count = likes[item.id] ?: 0

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column {
                    Text(
                        text = item.title,
                        fontSize = 18.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight.Normal,
                    )
                    Text(
                        text = "Likes: $count",
                        fontSize = 18.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight.Normal,
                    )
                }
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.image)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.placeholder),
                    fallback = painterResource(R.drawable.placeholder),
                    contentDescription = null,
                    modifier = Modifier.weight(1f),
                )
                IconButton(
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                    ), onClick = {
                        likes[item.id] = count + 1
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ThumbUp,
                        contentDescription = "Like",
                    )
                }
            }
        }
    }
}
