package com.linx.playAndroid.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.linx.common.ext.toast
import com.linx.playAndroid.R
import com.linx.playAndroid.ui.theme.c_B3F
import com.linx.playAndroid.viewModel.RegisterViewModel

/**
 * 注册界面
 */
@Composable
fun RegisterCompose(navHostController: NavHostController) {

    val registerViewModel: RegisterViewModel = viewModel()

    val context = LocalContext.current

    registerViewModel.apply {
        //toast
        mToast.observeAsState().value?.toast(context)

        //注册成功后执行的代码块
        val userRegisterData = userRegisterData.observeAsState()
        LaunchedEffect(userRegisterData.value) {
            if (userRegisterData.value != null) {
                mToast.value = "注册成功"
                navHostController.navigateUp()
            }
        }
    }

    //页面布局
    RegisterScreen(navHostController, registerViewModel)
}

/**
 * 注册页面布局
 */
@Composable
private fun RegisterScreen(navHostController: NavHostController, registerViewModel: RegisterViewModel) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        //背景图
        Image(
            painter = painterResource(R.mipmap.login_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {

                //返回上个页面和找回密码
                BackAndFinPassWordComposable() {
                    //关闭页面
                    navHostController.navigateUp()
                }

                Image(
                    modifier = Modifier.padding(top = 50.dp).size(100.dp)
                        .align(Alignment.Center),
                    painter = painterResource(R.mipmap.ic_account),
                    contentDescription = null
                )

                registerViewModel.apply {
                    //用户名和密码
                    UserNameAndPasswordComposable(userName, passWord, passwordVisible)
                }

                //用户注册
                RegisterButtom() {
                    registerViewModel.getUserRegisterData()
                }

            }
        }
    }

}

/**
 * 注册按钮
 */
@Composable
private fun RegisterButtom(registerClick: () -> Unit) {
    //注册
    Button(
        onClick = registerClick,
        modifier = Modifier
            .padding(start = 50.dp, end = 50.dp, top = 30.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            //透明的背景
            backgroundColor = c_B3F,
            contentColor = Color.Black
        )
    ) {
        Text("注册", fontSize = 14.sp)
    }
}