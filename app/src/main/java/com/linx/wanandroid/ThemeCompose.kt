package com.linx.wanandroid

import android.text.style.TtsSpan
import android.widget.Toast
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.linx.wanandroid.ui.theme.ThemeType
import com.linx.wanandroid.viewModel.MainViewModel
import kotlinx.coroutines.launch

/**
 * 主题
 */
@Composable
fun ThemeCompose() {

    val viewModel: MainViewModel = viewModel()

    LazyColumn(
        modifier = Modifier.background(MaterialTheme.colors.background).fillMaxSize()
    ) {

        item {
            MainCard("默认") {
                viewModel.themeTypeState.value = ThemeType.Default
            }
        }

        item {
            MainCard("主题1") {
                viewModel.themeTypeState.value = ThemeType.Theme1
            }
        }

        item {
            MainCard("主题2") {
                viewModel.themeTypeState.value = ThemeType.Theme2
            }
        }

        item {
            MainCard("主题3") {
                viewModel.themeTypeState.value = ThemeType.Theme3
            }
        }

        item {
            MainCard("主题4") {
                viewModel.themeTypeState.value = ThemeType.Theme4
            }
        }

        item {
            MainCard("主题5") {
                viewModel.themeTypeState.value = ThemeType.Theme5
            }
        }

    }

}

@Composable
private fun MainCard(str: String, click: () -> Unit) {

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