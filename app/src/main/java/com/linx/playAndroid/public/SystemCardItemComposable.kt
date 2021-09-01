package com.linx.playAndroid.public

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linx.playAndroid.model.SystemData

/**
 * 体系页面卡片的内容
 */
@Composable
fun SystemCardItemContent(title: String, list: List<SystemData.Children?>?) {

    Column(
        //内边距
        modifier = Modifier.fillMaxSize()
            .padding(top = 6.dp, bottom = 6.dp, start = 10.dp, end = 10.dp)
    ) {

        Text(
            title,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            color = MaterialTheme.colors.secondaryVariant
        )

        //标签
        LabelCustom(itemGap = FlowBoxGap(6.dp)) {
            list?.forEach { data ->
                Button(onClick = {}) { Text(data?.name ?: "")}
            }
        }


    }

}