package com.linx.playAndroid.composable

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.linx.common.baseData.CommonConstant
import com.linx.common.baseData.refreshUserMessageData
import com.linx.common.ext.toast
import com.linx.common.widget.SpUtilsMMKV
import com.linx.playAndroid.KeyNavigationRoute
import com.linx.playAndroid.R
import com.linx.playAndroid.ui.theme.c_80F
import com.linx.playAndroid.ui.theme.c_B3F
import com.linx.playAndroid.viewModel.LoginViewModel

/**
 * 登录页面
 */
@Composable
fun LoginCompose(navHostController: NavHostController) {

    val loginViewModel: LoginViewModel = viewModel()

    val context = LocalContext.current

    loginViewModel.apply {
        //toast
        mToast.observeAsState().value?.toast(context)

        val userLogin= userLoginData.observeAsState()
        //登录成功
        LaunchedEffect(userLogin.value) {
            if (userLogin.value == "登录成功") {
                mToast.value = "登录成功"
                //保存登录数据
                SpUtilsMMKV.put(CommonConstant.IS_LOGIN, true)
                //通知刷新个人信息
                refreshUserMessageData.value = System.currentTimeMillis().toString()
                navHostController.navigateUp()
            }
        }
    }

    //登录页面布局
    LoginScreen(navHostController, loginViewModel)

}

/**
 * 登录页面 布局
 */
@Composable
private fun LoginScreen(navHostController: NavHostController, loginViewModel: LoginViewModel) {

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

                loginViewModel.apply {
                    //用户名和密码
                    UserNameAndPasswordComposable(userName, passWord, passwordVisible)
                }

                //登录按钮  用户注册  作者登录
                LoginBtnAndTextComposable(loginClick = {
                    //登录
                    loginViewModel.getUserLoginData()
                }, registerClick = {
                    //跳转到注册页面
                    navHostController.navigate(KeyNavigationRoute.REGISTER.route)
                })
                //第三方登录
                OtherLoginComposable()

                //用户协议
                AgreementComposable()
            }
        }
    }

}

/**
 * 用户协议
 */
@Composable
private fun AgreementComposable() {
    val context = LocalContext.current
    Spacer(
        modifier = Modifier.padding(top = 30.dp).background(color = c_80F).height(2.dp)
            .fillMaxWidth()
    )

    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 15.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "登录或注册即同意开通",
            color = Color.LightGray,
            fontSize = 12.sp
        )
        Text(
            "用户服务协议",
            color = Color.White,
            fontSize = 17.sp,
            modifier = Modifier.clickable {
                Toast.makeText(context, "点击用户服务协议", Toast.LENGTH_SHORT).show()
            }.padding(4.dp)
        )
    }
}

/**
 * 第三方登录
 */
@Composable
private fun OtherLoginComposable() {
    val modifier =
        Modifier.clip(CircleShape).background(MaterialTheme.colors.background).padding(10.dp)
            .size(30.dp)
    Row(
        modifier = Modifier.padding(start = 40.dp, end = 40.dp, top = 90.dp)
            .fillMaxWidth(),
        //平分控件
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        //微信登录
        Icon(
            painterResource(R.mipmap.ic_action_share_wechat_grey),
            contentDescription = null,
            modifier = modifier,
            tint = MaterialTheme.colors.secondaryVariant
        )
        //微博登录
        Icon(
            painterResource(R.mipmap.ic_action_share_weibo_grey),
            contentDescription = null,
            modifier = modifier,
            tint = MaterialTheme.colors.secondaryVariant
        )
        //QQ登录
        Icon(
            painterResource(R.mipmap.ic_action_share_qq_grey),
            contentDescription = null,
            modifier = modifier,
            tint = MaterialTheme.colors.secondaryVariant
        )
    }
}

/**
 * 登录按钮
 * 用户注册 作者登录
 */
@Composable
private fun LoginBtnAndTextComposable(loginClick: () -> Unit, registerClick: () -> Unit) {

    //登录
    Button(
        onClick = loginClick,
        modifier = Modifier
            .padding(start = 50.dp, end = 50.dp, top = 30.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            //透明的背景
            backgroundColor = c_B3F,
            contentColor = Color.Black
        )
    ) {
        Text("登录", fontSize = 14.sp)
    }

    //用户注册 作者登录
    Row(
        modifier = Modifier.padding(start = 50.dp, end = 50.dp, top = 30.dp).fillMaxWidth(),
        //平分控件
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text("用户注册", color = Color.White, modifier = Modifier.clickable(onClick = registerClick).padding(10.dp))
        Text("找回密码", color = Color.White, modifier = Modifier.padding(10.dp))
    }
}

/**
 * 用户名和密码
 */
@Composable
fun UserNameAndPasswordComposable(
    //用户名
    userName: MutableState<TextFieldValue>,
    //密码
    passWord: MutableState<TextFieldValue>,
    //密码是否可见
    passWordVisible: MutableState<Boolean>
) {

    val userNameModifier = Modifier.padding(start = 50.dp, end = 50.dp, top = 50.dp).fillMaxWidth()
        .height(56.dp)

    val passWordModifier =
        Modifier.padding(start = 50.dp, end = 50.dp, top = 20.dp).fillMaxWidth().height(56.dp)

    //用户名
    OutlinedTextField(
        userName.value,
        onValueChange = { newValue ->
            userName.value = newValue
        },
        label = {
            Text("请输入手机号/邮箱地址", fontSize = 13.sp)
        },
        leadingIcon = {
            Icon(
                painterResource(R.mipmap.ic_account_login_user_name),
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            unfocusedLabelColor = Color.White,
            textColor = Color.White,
            backgroundColor = Color.Transparent,
            focusedLabelColor = Color.White,
            leadingIconColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
            //光标
            cursorColor = Color.White
        ),
        singleLine = true,
        modifier = userNameModifier,
        textStyle = TextStyle(
            fontSize = 13.sp
        )
    )

    //密码
    OutlinedTextField(
        passWord.value, onValueChange = { newValue ->
            passWord.value = newValue
        },
        label = {
            Text("请输入密码", fontSize = 13.sp)
        },
        leadingIcon = {
            Icon(
                painterResource(R.mipmap.ic_account_login_password),
                contentDescription = null
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                passWordVisible.value = !passWordVisible.value
            }) {
                Icon(
                    painter = if (passWordVisible.value) painterResource(R.mipmap.ic_password_view) else painterResource(
                        R.mipmap.ic_password_view_off
                    ), contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color.White
                )
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            unfocusedLabelColor = Color.White,
            textColor = Color.White,
            backgroundColor = Color.Transparent,
            focusedLabelColor = Color.White,
            leadingIconColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
            //光标
            cursorColor = Color.White
        ),
        singleLine = true,
        modifier = passWordModifier,
        textStyle = TextStyle(
            fontSize = 13.sp
        ),
        //输入类型设置为密码
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        //转化为不可见的密码
        visualTransformation = if (!passWordVisible.value) PasswordVisualTransformation() else VisualTransformation.None
    )
}

/**
 * 返回上个页面和跳过
 */
@Composable
fun BackAndFinPassWordComposable(
    back: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 40.dp),
        //第一个和最后一个按钮分别靠左边界和右边界
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(R.mipmap.ic_tab_strip_icon_follow),
            contentDescription = null,
            modifier = Modifier.clickable(onClick = back).padding(10.dp),
            tint = Color.White
        )

        Text(
            text = "跳过",
            fontSize = 13.sp,
            color = Color.White,
            modifier = Modifier.padding(10.dp)
        )
    }
}