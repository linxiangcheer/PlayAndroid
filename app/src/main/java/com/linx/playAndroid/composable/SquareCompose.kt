package com.linx.playAndroid.composable

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.linx.common.base.BaseViewModel
import com.linx.playAndroid.viewModel.SquareViewModel

/**
 * 广场页面
 */
@Composable
fun SquareCompose(navController: NavController) {

    Column(modifier = Modifier.background(MaterialTheme.colors.background).fillMaxSize()) {
        val squareViewModel: SquareViewModel = viewModel()
    }

}