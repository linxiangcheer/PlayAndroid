package com.linx.playAndroid.composable

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.linx.common.baseData.themeTypeState
import com.linx.playAndroid.R
import com.linx.playAndroid.public.BaseScreen
import com.linx.playAndroid.public.ColorPicker
import com.linx.playAndroid.public.SubtitleText
import com.linx.playAndroid.ui.theme.*
import com.linx.playAndroid.ui.theme.CustomThemeManager.getThemeColor

@Composable
fun AnimationCompose(navHostController: NavHostController) {
    BaseScreen {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(text = "Animation")
                }, navigationIcon = {
                    IconButton(onClick = {
                        navHostController.navigateUp()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }, backgroundColor = getThemeColor(themeType = themeTypeState.value).primary)
            },
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                item {
                    AnimationScreen()
                }
            }
        }
    }
}

@Composable
private fun AnimationScreen() {
    SubtitleText(subtitle = "?????????????????? - animateColorAsState")
    SimpleColorStateAnimation()
    Divider()

    SubtitleText(subtitle = "????????????Button?????? - animateDpAsState")
    SimpleDpStateAnimation()
    Divider()

    SubtitleText(subtitle = "????????????????????? - animateFloatAsState")
    SimpleFloatStateAnimation()
    Divider()

    SubtitleText(subtitle = "???????????????????????? - animateOffsetAsState")
    SimpleOffsetStateAnimation()
    Divider()

    SubtitleText(subtitle = "???????????????")
    SimpleAnimateCustomState()
    Divider()

    SubtitleText(subtitle = "???????????? - ????????????")
    DrawLayerWithAnimation()
    Divider()

    SubtitleText(subtitle = "??????????????? - AnimatedVisibility")
    AnimatedVisibilityAnimated()
    Divider()

    SubtitleText(subtitle = "?????????????????????")
    MultiStateColorPositionAnimation()
    Divider()

    SubtitleText(subtitle = "????????????????????? - ?????? rememberInfiniteTransition")
    MultiStateInfiniteTransition()
    Divider()

    SubtitleText(subtitle = "Canvas????????? ???????????? - ????????????")
    MultiStateAnimateCircleFilledCanvas()
    Divider()

    SubtitleText(subtitle = "Canvas???????????? ???????????? - ??????")
    MultiStateAnimationCircleStrokeCanvas()
    Divider()

    SubtitleText(subtitle = "???????????????")
    Spacer(modifier = Modifier.size(20.dp))
    ColorPicker { color: Color ->
        Log.i("xxx", "?????????????????? $color")
    }
    Spacer(modifier = Modifier.size(20.dp))
    Divider()
}

/**
 * ??????????????????
 */
@Composable
private fun SimpleColorStateAnimation() {

    var enabled by remember {
        mutableStateOf(true)
    }

    val themeColor = getThemeColor(themeTypeState.value)

    val animatedColor =
        animateColorAsState(targetValue = if (enabled) themeColor.primary else c_b66731)

    val buttonColors = ButtonDefaults.buttonColors(
        backgroundColor = animatedColor.value
    )

    Button(
        onClick = { enabled = !enabled },
        colors = buttonColors,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(text = "??????????????????")
    }
}

/**
 * ??????????????????
 */
@Composable
private fun SimpleDpStateAnimation() {

    var enabled by remember {
        mutableStateOf(true)
    }

    val animaHeight = animateDpAsState(targetValue = if (enabled) 40.dp else 60.dp)
    val animaWidth = animateDpAsState(targetValue = if (enabled) 150.dp else 300.dp)

    Button(
        onClick = { enabled = !enabled },
        modifier = Modifier
            .padding(16.dp)
            .height(animaHeight.value)
            .width(animaWidth.value)
    ) {
        Text(text = "??????????????????")
    }
}

/**
 * ?????????????????????
 */
@Composable
private fun SimpleFloatStateAnimation() {

    var enable by remember {
        mutableStateOf(true)
    }

    val animaFloat = animateFloatAsState(targetValue = if (enable) 1f else 0.2f)

    Button(
        onClick = { enable = !enable },
        modifier = Modifier
            .padding(16.dp)
            .alpha(animaFloat.value)
    ) {
        Text(text = "?????????????????????")
    }

}

/**
 * ????????????????????????
 */
@Composable
private fun SimpleOffsetStateAnimation() {

    var enable by remember {
        mutableStateOf(true)
    }

    val animaOffset by
    animateOffsetAsState(targetValue = if (enable) Offset(0f, 0f) else Offset(50f, 40f))

    Row {
        Image(
            painter = painterResource(id = R.mipmap.ic_shuoshuo_1),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .padding(16.dp)
                .offset(x = Dp(animaOffset.x), y = Dp(animaOffset.y))
                .clickable { enable = !enable })
        Image(
            painter = painterResource(id = R.mipmap.ic_shuoshuo_2),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .padding(16.dp)
                .offset(x = -Dp(animaOffset.x), y = -Dp(animaOffset.y))
                .clickable { enable = !enable })
    }

}

data class CustomAnimationState(val width: Dp, val height: Dp, val rotation: Float)

/**
 * ???????????????
 */
@Composable
private fun SimpleAnimateCustomState() {

    var enabled by remember {
        mutableStateOf(true)
    }

    val initUiState = CustomAnimationState(200.dp, 40.dp, 0f)
    val targetUiState = CustomAnimationState(300.dp, 60.dp, 15f)

    val uiState = if (enabled) initUiState else targetUiState

    val animatedUiState by animateValueAsState(
        targetValue = uiState,
        typeConverter = TwoWayConverter(convertToVector = {
            AnimationVector3D(it.width.value, it.height.value, it.rotation)
        }, convertFromVector = {
            CustomAnimationState(it.v1.dp, it.v2.dp, it.v3)
        }),
        animationSpec = tween(600)
    )

    Button(
        onClick = { enabled = !enabled },
        modifier = Modifier
            .padding(16.dp)
            .width(animatedUiState.width)
            .height(animatedUiState.height)
            .rotate(animatedUiState.rotation)
    ) {
        Text(text = "??????????????????")
    }

}

/**
 * ???????????? - ????????????
 */
@Composable
private fun DrawLayerWithAnimation() {

    var draw2 by remember {
        mutableStateOf(false)
    }

    Spacer(modifier = Modifier.padding(10.dp))

    Box {
        Image(
            painter = painterResource(id = R.mipmap.ic_shuoshuo_1),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer(
                    translationY = animateFloatAsState(targetValue = if (draw2) 0f else -20f).value,
                    //?????????
                    translationX = animateFloatAsState(targetValue = if (draw2) 300.dp.value else 0f).value,
                    //????????????
                    shadowElevation = animateFloatAsState(targetValue = if (draw2) 30f else 5f).value
                )
                .clickable {
                    draw2 = !draw2
                }
        )

        Image(
            painter = painterResource(id = R.mipmap.ic_shuoshuo_2),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer(
                    translationY = 0f,
                    shadowElevation = animateFloatAsState(targetValue = if (draw2) 20f else 5f).value,
                    translationX = 0f
                )
                .clickable { draw2 = !draw2 }
        )

        Image(
            painter = painterResource(id = R.mipmap.ic_shuoshuo_3),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer(
                    translationY = animateFloatAsState(targetValue = if (draw2) 0f else 20f).value,
                    //?????????
                    translationX = animateFloatAsState(targetValue = if (draw2) -300.dp.value else 0f).value,
                    //????????????
                    shadowElevation = animateFloatAsState(targetValue = if (draw2) 10f else 5f).value
                )
                .clickable {
                    draw2 = !draw2
                }
        )
    }

    Spacer(modifier = Modifier.padding(20.dp))

    Box {
        Image(
            painter = painterResource(id = R.mipmap.ic_shuoshuo_1),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer(
                    translationY = animateFloatAsState(targetValue = if (draw2) 0f else -20f).value,
                    //?????????
                    translationX = animateFloatAsState(targetValue = if (draw2) 300.dp.value else 0f).value,
                    //????????????
                    shadowElevation = animateFloatAsState(targetValue = if (draw2) 30f else 3f).value,
                    rotationY = animateFloatAsState(targetValue = if (draw2) 45f else 0f).value
                )
                .clickable {
                    draw2 = !draw2
                }
        )

        Image(
            painter = painterResource(id = R.mipmap.ic_shuoshuo_2),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer(
                    translationY = 0f,
                    shadowElevation = animateFloatAsState(targetValue = if (draw2) 20f else 5f).value,
                    translationX = 0f,
                    rotationY = animateFloatAsState(targetValue = if (draw2) 45f else 0f).value
                )
                .clickable { draw2 = !draw2 }
        )

        Image(
            painter = painterResource(id = R.mipmap.ic_shuoshuo_3),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer(
                    translationY = animateFloatAsState(targetValue = if (draw2) 0f else 20f).value,
                    //?????????
                    translationX = animateFloatAsState(targetValue = if (draw2) -300.dp.value else 0f).value,
                    //????????????
                    shadowElevation = animateFloatAsState(targetValue = if (draw2) 10f else 5f).value,
                    rotationY = animateFloatAsState(targetValue = if (draw2) 45f else 0f).value
                )
                .clickable {
                    draw2 = !draw2
                }
        )
    }

    Spacer(modifier = Modifier.padding(20.dp))

    Box {
        Image(
            painter = painterResource(id = R.mipmap.ic_shuoshuo_1),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer(
                    translationY = animateFloatAsState(targetValue = if (draw2) 0f else -20f).value,
                    //?????????
                    translationX = animateFloatAsState(targetValue = if (draw2) 300.dp.value else 0f).value,
                    //????????????
                    shadowElevation = animateFloatAsState(targetValue = if (draw2) 30f else 5f).value,
                    rotationZ = animateFloatAsState(targetValue = if (draw2) 45f else 0f).value
                )
                .clickable {
                    draw2 = !draw2
                }
        )

        Image(
            painter = painterResource(id = R.mipmap.ic_shuoshuo_2),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer(
                    translationY = 0f,
                    shadowElevation = animateFloatAsState(targetValue = if (draw2) 20f else 5f).value,
                    translationX = 0f,
                    rotationZ = animateFloatAsState(targetValue = if (draw2) 45f else 0f).value
                )
                .clickable { draw2 = !draw2 }
        )

        Image(
            painter = painterResource(id = R.mipmap.ic_shuoshuo_3),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer(
                    translationY = animateFloatAsState(targetValue = if (draw2) 0f else 20f).value,
                    //?????????
                    translationX = animateFloatAsState(targetValue = if (draw2) -300.dp.value else 0f).value,
                    //????????????
                    shadowElevation = animateFloatAsState(targetValue = if (draw2) 10f else 5f).value,
                    rotationZ = animateFloatAsState(targetValue = if (draw2) 45f else 0f).value
                )
                .clickable {
                    draw2 = !draw2
                }
        )
    }

    Spacer(modifier = Modifier.padding(20.dp))

}

/**
 * ??????????????? - AnimatedVisibility
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimatedVisibilityAnimated() {

    var expanded by remember {
        mutableStateOf(true)
    }

    //????????????
    FloatingActionButton(onClick = { expanded = !expanded }, modifier = Modifier.padding(16.dp)) {
        Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            Icon(imageVector = Icons.Default.Star, contentDescription = null)
            AnimatedVisibility(visible = expanded) {
                Text(text = "??????", modifier = Modifier.padding(start = 8.dp))
            }
        }
    }

    Spacer(modifier = Modifier.size(20.dp))

    //??????????????????/??????????????????
    Row(modifier = Modifier
        .background(Default200)
        .width(200.dp)
        .height(60.dp)
        .clickable { expanded = !expanded }) {
        AnimatedVisibility(
            visible = expanded,
            //??????
            enter = slideIn(
                {
                    IntOffset(0, 60.dp.value.toInt())
                },
                tween(
                    easing = LinearOutSlowInEasing,
                    durationMillis = 500
                )
            ),
            //??????
            exit = slideOut({
                IntOffset(0, 60.dp.value.toInt())
            }, tween(easing = FastOutSlowInEasing, durationMillis = 500))
        ) {
            Text(text = "slideIn/ slideOut", fontSize = 12.sp)
        }
    }

    Spacer(modifier = Modifier.size(20.dp))

    //????????????/??????
    Row(
        modifier = Modifier
            .background(Default200)
            .width(200.dp)
            .height(60.dp)
            .clickable { expanded = !expanded },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedVisibility(
            visible = expanded,
            enter = expandIn(expandFrom = Alignment.CenterStart),
            exit = shrinkOut(shrinkTowards = Alignment.CenterEnd)
        ) {
            Text(text = "expandIn/ shrinkOut", fontSize = 12.sp)
        }
    }

    Spacer(modifier = Modifier.size(20.dp))

    //??????????????????
    var count by remember {
        mutableStateOf(1)
    }

    Row(
        modifier = Modifier
            .animateContentSize()
            .clickable {
                if (count < 10) count += 3 else count = 1
            }
    ) {
        (0..count).forEach { _ ->
            Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null)
        }
    }

    Spacer(modifier = Modifier.size(20.dp))

}

enum class MyAnimationState {
    START, MID, END
}

/**
 * ?????????????????????
 */
@Composable
private fun MultiStateColorPositionAnimation() {

    Spacer(modifier = Modifier.size(100.dp))

    var animationState by remember {
        mutableStateOf(MyAnimationState.START)
    }

    val transition = updateTransition(targetState = animationState, label = "transition")

    val animatedColor by transition.animateColor(
        transitionSpec = { tween(500) },
        label = "animatedColor"
    ) { state ->
        when (state) {
            MyAnimationState.START -> Default200
            MyAnimationState.MID -> Theme1_200
            MyAnimationState.END -> Theme2_200
        }
    }

    val position by transition.animateDp(
        label = "position",
        transitionSpec = { tween(500) }) { state ->
        when (state) {
            MyAnimationState.START -> 0.dp
            MyAnimationState.MID -> 80.dp
            MyAnimationState.END -> (-80).dp
        }
    }

    FloatingActionButton(onClick = {
        animationState = when (animationState) {
            MyAnimationState.START -> MyAnimationState.MID
            MyAnimationState.MID -> MyAnimationState.END
            MyAnimationState.END -> MyAnimationState.START
        }
    }, modifier = Modifier.offset(x = position, y = position), backgroundColor = animatedColor) {
        Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null)
    }

    Spacer(modifier = Modifier.size(100.dp))

}

/**
 * ????????????????????? - ??????
 */
@Composable
private fun MultiStateInfiniteTransition() {

    //???????????????????????????
    val transition = rememberInfiniteTransition()

    val animatedColor by transition.animateColor(
        initialValue = Default200,
        targetValue = Theme2_200,
        animationSpec = infiniteRepeatable(tween(800), RepeatMode.Reverse)
    )

    val position by transition.animateFloat(
        initialValue = -80f,
        targetValue = 80f,
        animationSpec = infiniteRepeatable(tween(800), RepeatMode.Reverse)
    )

    FloatingActionButton(
        modifier = Modifier.offset(x = position.dp),
        backgroundColor = animatedColor,
        onClick = {}
    ) {
        Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null)
    }

}

/**
 * Canvas????????? ???????????? - ????????????
 */
@Composable
private fun MultiStateAnimateCircleFilledCanvas(
    color: Color = Theme2_500,
    radiusEnd: Float = 200f
) {

    val transition = rememberInfiniteTransition()

    val floatAnim by transition.animateFloat(
        initialValue = 10f,
        targetValue = radiusEnd,
        animationSpec = infiniteRepeatable(tween(1200), RepeatMode.Reverse)
    )

    val centerOffset = Offset(0f, 10f)

    Canvas(
        modifier = Modifier
            .padding(100f.dp)
    ) {
        drawCircle(
            color = color.copy(alpha = 0.9f),
            center = centerOffset,
            radius = floatAnim
        )
        drawCircle(
            color = color.copy(alpha = 0.5f),
            center = centerOffset,
            radius = floatAnim / 2
        )
        drawCircle(
            color = color.copy(alpha = 0.2f),
            center = centerOffset,
            radius = floatAnim / 4
        )
    }
}

/**
 * Canvas???????????? ???????????? - ??????
 */
@Composable
private fun MultiStateAnimationCircleStrokeCanvas() {

    val transition = rememberInfiniteTransition()

    val animatedFloat by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(800), repeatMode = RepeatMode.Restart)
    )

    val stroke = Stroke(8f)
    val primary = getThemeColor(themeType = themeTypeState.value).primary

    Canvas(modifier = Modifier
        .padding(16.dp)
        .size(100.dp)) {
        //????????????????????????????????????
        val diameter = size.minDimension
        val radius = diameter / 2
        val insideRadius = radius - stroke.width
        val topLeftOffset = Offset(0f, 10f)
        val size = Size(insideRadius * 2, insideRadius * 2)

        drawArc(
            primary,
            //??????
            size = size,
            //???????????????
            useCenter = false,
            style = stroke,
            //??????
            sweepAngle = 160f,
            topLeft = topLeftOffset,
            //????????????
            startAngle = animatedFloat
        )
    }

}