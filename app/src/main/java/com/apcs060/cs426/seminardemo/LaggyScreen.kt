package com.apcs060.cs426.seminardemo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import java.util.UUID
import kotlin.random.Random

@Composable
fun LaggyScreen(itemSize: Int) {
    val items = remember {
        mutableStateListOf<Item>().apply {
            addAll(
                List(itemSize) { index ->
                    Item(
                        id = UUID.randomUUID(),
                        title = "Item $index",
                        image = "https://picsum.photos/200/200?random=${Random.nextInt()}"
                    )
                }
            )
        }
    }

    val likes = remember { mutableStateMapOf<UUID, Int>() }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth(),
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
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surfaceContainer,
                    )
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.image)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.placeholder),
                    fallback = painterResource(R.drawable.placeholder),
                    contentDescription = null,
                    filterQuality = FilterQuality.High,
                    modifier = Modifier
                        .size(88.dp)
                        .border(
                            width = 4.dp,
                            color = MaterialTheme.colorScheme.outlineVariant,
                        ),
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    Text(
                        text = item.title,
                        fontSize = 18.sp,
                        lineHeight = 22.sp,
                        letterSpacing = 0.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Likes: $count",
                        fontSize = 16.sp,
                        lineHeight = 20.sp,
                        letterSpacing = 0.sp,
                        fontWeight = FontWeight.Normal,
                    )
                }
                IconButton(
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    onClick = { likes[item.id] = count + 1 }
                ) {
                    Icon(
                        imageVector = Icons.Default.ThumbUp,
                        contentDescription = "Like",
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
