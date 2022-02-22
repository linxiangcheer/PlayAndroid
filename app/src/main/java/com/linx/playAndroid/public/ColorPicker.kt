package com.linx.playAndroid.public

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt
import kotlin.random.Random

/**
 * 颜色选择器
 */
@Composable
fun ColorPicker(
    onColorSelected: (Color) -> Unit
) {
    //屏幕宽度 - dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    //屏幕宽度 - px
    val screenWidthInPx = with(LocalDensity.current) { screenWidth.toPx() }
    //当前颜色
    var activeColor by remember {
        mutableStateOf(Red)
    }

    val thisPadding = 8.dp

    //减去左右两边间隙的宽度
    val max = screenWidth - (thisPadding * 2)
    val min = 0.dp
    val (minPx, maxPx) = with(LocalDensity.current) { min.toPx() to max.toPx() }

    //偏移距离
    var dragOffset by remember {
        mutableStateOf(0f)
    }

    Box(modifier = Modifier.padding(thisPadding)) {
        Spacer(
            modifier = Modifier
                .height(10.dp)
                .clip(RoundedCornerShape(5.dp))
                .fillMaxWidth()
                .background(brush = colorMapGradient(screenWidthInPx))
                .align(Alignment.Center)
        )
        Icon(
            imageVector = Icons.Filled.FiberManualRecord,
            contentDescription = null,
            tint = activeColor,
            modifier = Modifier
                .offset { IntOffset(dragOffset.roundToInt(), 0) }
                .border(
                    border = BorderStroke(4.dp, MaterialTheme.colors.onSurface),
                    shape = CircleShape
                )
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        val newValue = dragOffset + delta
                        //确保该值位于minPx - maxPx内，小于等于minPx就是minPx，大于等于maxPx就是maxPx
                        dragOffset = newValue.coerceIn(minPx, maxPx)
                        activeColor = getActiveColor(dragOffset, screenWidthInPx)
                        onColorSelected(activeColor)
                    }
                )
        )
    }

}

/**
 * 创建颜色值List
 */
private fun createColorMap(): List<Color> {
    val colorList = mutableListOf<Color>()

    for (i in 0..360 step 2) {
        val randomSaturation = 90 + Random.nextFloat() * 10
        val randomLightness = 50 + Random.nextFloat() * 10
        //https://blog.csdn.net/caobin_study/article/details/81627102
        val hsv = android.graphics.Color.HSVToColor(
            floatArrayOf(
                //色调范围 0 - 360
                i.toFloat(),
                //饱和度 0-1 超过更亮
                randomSaturation,
                //亮度 0-1
                randomLightness
            )
        )
        colorList += Color(hsv)
    }

    return colorList
}

/**
 * 颜色Border
 */
private fun colorMapGradient(screenWidthInPx: Float) = Brush.horizontalGradient(
    colors = createColorMap(),
    startX = 0f,
    endX = screenWidthInPx
)

/**
 * 获取当前颜色条的偏移距离，以解码成当前颜色值
 */
private fun getActiveColor(dragPosition: Float, screenWidth: Float): Color {
    val hue = (dragPosition / screenWidth) * 360f
    val randomSaturation = 90 + Random.nextFloat() * 10
    val randomLightness = 50 + Random.nextFloat() * 10
    return Color(
        android.graphics.Color.HSVToColor(
            floatArrayOf(
                hue,
                randomSaturation,
                randomLightness
            )
        )
    )
}

//圆
val Icons.Filled.FiberManualRecord: ImageVector
    get() {
        if (_fiberManualRecord != null) {
            return _fiberManualRecord!!
        }
        _fiberManualRecord = materialIcon(name = "Filled.FiberManualRecord") {
            materialPath {
                moveTo(12.0f, 12.0f)
                moveToRelative(-8.0f, 0.0f)
                arcToRelative(
                    8.0f, 8.0f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = true,
                    dx1 = 16.0f,
                    dy1 = 0.0f
                )
                arcToRelative(
                    8.0f, 8.0f, 0.0f,
                    isMoreThanHalf = true,
                    isPositiveArc = true,
                    dx1 = -16.0f,
                    dy1 = 0.0f
                )
            }
        }
        return _fiberManualRecord!!
    }

private var _fiberManualRecord: ImageVector? = null