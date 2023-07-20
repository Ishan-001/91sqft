package com.example.radius.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.radius.R
import com.example.radius.data.network.model.DataItem

@Composable
fun ListItem(
    dataItem: DataItem,
    onClick: (DataItem) -> Unit,
    isDownloaded: Boolean,
    isDownloading: Boolean,
    progress: Int = 0
) {
    Column {
        Row(
            modifier = Modifier
                .padding(
                    horizontal = 20.dp,
                    vertical = 3.dp
                )
                .clickable {
                    onClick(dataItem)
                }
                .fillMaxWidth()
                .wrapContentHeight()
                .border(BorderStroke(1.dp, Color(0xFFE4E7EC)))
                .clip(RoundedCornerShape(7.dp))
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_pdf),
                contentDescription = null
            )

            Column {
                Text(
                    text = dataItem.name.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(3.dp))
                Row {
                    Text(
                        modifier = Modifier
                            .background(Color(0xFFFFE3E4))
                            .padding(2.dp),
                        text = "V${dataItem.version}",
                        color = Color(0xFFEC2227),
                        fontWeight = FontWeight.Bold,
                        fontSize = 8.sp
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        text = dataItem.uploadedAt.toString(),
                        fontSize = 10.sp,
                        color = Color(0xFF667085)
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(
                    id = if (isDownloading) R.drawable.ic_done else R.drawable.ic_menu
                ),
                contentDescription = null
            )
        }

        AnimatedVisibility(visible = isDownloading) {
            LinearProgressIndicator(
                progress = progress * 0.01f,
                color = Color.Green
            )
        }
    }
}