package com.linx.wanandroid.composable

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linx.common.baseData.themeTypeState
import com.linx.common.model.StatusBarTitleData
import com.linx.common.model.ThemeType

/**
 * 主题页面
 */
@Composable
fun ThemeCompose() {

    LazyColumn(
        modifier = Modifier.background(MaterialTheme.colors.background).fillMaxSize()
    ) {

        item {
            ThemeCard("默认") {
                themeTypeState.value = ThemeType.Default
            }
        }

        item {
            ThemeCard("主题1") {
                themeTypeState.value = ThemeType.Theme1
            }
        }

        item {
            ThemeCard("主题2") {
                themeTypeState.value = ThemeType.Theme2
            }
        }

        item {
            ThemeCard("主题3") {
                themeTypeState.value = ThemeType.Theme3
            }
        }

        item {
            ThemeCard("主题4") {
                themeTypeState.value = ThemeType.Theme4
            }
        }

        item {
            ThemeCard("主题5") {
                themeTypeState.value = ThemeType.Theme5
            }
        }

    }

}

/**
 * 卡片
 */
@Composable
private fun ThemeCard(str: String, click: () -> Unit) {

    Card(
        modifier = Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp).fillMaxWidth()
            .height(60.dp),
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 2.dp,
        contentColor = MaterialTheme.colors.background
    ) {

        Text(str, fontSize = 20.sp, modifier = Modifier.clickable(onClick = {
            click()
        }).fillMaxSize(), textAlign = TextAlign.Center)

    }

}