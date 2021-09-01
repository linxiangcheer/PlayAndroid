package com.linx.playAndroid.public

import android.graphics.Point
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * 标签布局
 */
@Composable
fun LabelCustom(
    //基础样式配置
    modifier: Modifier = Modifier,
    //上下左右的间隔
    itemGap: FlowBoxGap = DefaultFlowBoxGap,
    content: @Composable () -> Unit
) {
    //自定义组件，需要传入content和measurePolicy
    Layout(
        content = content,
        measurePolicy = flowBoxMeasurePolicy(itemGap),
        modifier = modifier
    )
}

/**
 * 返回一个MeasurePolicy，用于告诉自定义组件怎么布局
 * [measurables] 子组件的内容
 * [constraints] 父布局的限制
 */
fun flowBoxMeasurePolicy(itemGap: FlowBoxGap) = MeasurePolicy { measurables, constraints ->

    //储存子组件的位置
    val positions = arrayListOf<Point>()
    //默认当前组件的x点的位置
    var xPosition = 0
    //默认当前组件的y点的位置
    var yPosition = 0
    //当前行高度最大的组件的高度，用于换行时设置下一行的起始yPosition
    var currentLineMaxHeight = 0

    //批量拿到子组件的信息
    val placeables = measurables.map { placeable ->
        placeable.measure(constraints = constraints)
    }

    //对每个子组件进行操作
    placeables.forEach { placeable ->
        //计算间隔 横向
        val horizontalGap = itemGap.start.roundToPx() + itemGap.end.roundToPx()
        //计算间隔 纵向
        val verticalGap = itemGap.top.roundToPx() + itemGap.bottom.roundToPx()
        //如果 当前组件的宽度 加上左右间隔 加上起始x位置 大于父布局的最大宽度，则换行
        if (placeable.width + horizontalGap + xPosition > constraints.maxWidth) {
            //初始化x轴位置
            xPosition = 0
            //更新y轴位置
            yPosition += currentLineMaxHeight
        }
        //添加子组件布局位置(起始值)
        positions.add(
            Point(
                xPosition + itemGap.start.roundToPx(),
                yPosition + itemGap.top.roundToPx()
            )
        )
        //记录下一个组件的起始X位置 （x点位置 + 布局宽度 + 横向间隔）
        xPosition += placeable.width + horizontalGap
        /**
         * 记录当前行最大高度
         * [coerceAtLeast] 确保currentLineMaxHeight不小于指定的minimumValue
         * (组件高度 + 纵向间隔)
         */
        /**
         * 记录当前行最大高度
         * [coerceAtLeast] 确保currentLineMaxHeight不小于指定的minimumValue
         * (组件高度 + 纵向间隔)
         */
        currentLineMaxHeight = currentLineMaxHeight.coerceAtLeast(placeable.height + verticalGap)
    }
    //拿到所有子组件加起来的高度
    val height = yPosition + currentLineMaxHeight

    //设置FlowBox的宽高
    layout(constraints.maxWidth, height) {
        /**
         * 遍历位置列表，设置子组件的位置
         * [zip]返回两个集合构建的对列表
         */
        /**
         * 遍历位置列表，设置子组件的位置
         * [zip]返回两个集合构建的对列表
         */
        positions.zip(placeables).forEach { (point, placeable) ->
            //在其父布局的坐标系中放置一个位于(x, y)处的可选变量
            placeable.placeRelative(point.x, point.y)
        }
    }
}

//默认Button之间间隔是0.dp
val DefaultFlowBoxGap = FlowBoxGap(0.dp)

//定义一个Button Item间隔数据类, 存储上下左右的间隔
data class FlowBoxGap(val start: Dp, val top: Dp, val end: Dp, val bottom: Dp) {
    constructor(gap: Dp): this(gap, gap, gap, gap)
}