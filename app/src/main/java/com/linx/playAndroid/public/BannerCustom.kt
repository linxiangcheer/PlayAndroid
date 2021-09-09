package com.linx.playAndroid.public

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.linx.playAndroid.R
import com.linx.playAndroid.ui.theme.c_B3F
import kotlinx.coroutines.delay

/**
 * 轮播图
 * [timeMillis] 停留时间
 * [loadImage] 加载中显示的布局
 * [indicatorAlignment] 指示点的的位置,默认是轮播图下方的中间,带一点padding
 * [onClick] 轮播图点击事件
 */
@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
fun Banner(
    list: List<BannerData>?,
    timeMillis: Long = 3000,
    @DrawableRes loadImage: Int = R.mipmap.ic_web,
    indicatorAlignment: Alignment = Alignment.BottomCenter,
    onClick: (link: String) -> Unit = {}
) {

    Box(
        modifier = Modifier.background(MaterialTheme.colors.background).fillMaxWidth()
            .height(220.dp)
    ) {

        if (list == null) {
            //加载中的图片
            Image(
                painterResource(loadImage),
                modifier = Modifier.fillMaxSize(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        } else {
            val pagerState = rememberPagerState(
                //总页数
                pageCount = list.size,
                //预加载的个数
                initialOffscreenLimit = 1,
                //是否无限循环
                infiniteLoop = true,
                //初始页面
                initialPage = 0
            )

            //自动滚动
            LaunchedEffect(pagerState.currentPage) {
                if (pagerState.pageCount > 0) {
                    delay(timeMillis)
                    //这里直接+1就可以循环，前提是infiniteLoop == true
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.clickable(onClick = { onClick(list[pagerState.currentPage].linkUrl) })
                    .fillMaxSize(),
            ) { page ->
                Image(
                    painter = rememberImagePainter(list[page].imageUrl),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }

            Box(
                modifier = Modifier.align(indicatorAlignment)
                    .padding(bottom = 6.dp, start = 6.dp, end = 6.dp)
            ) {

                //指示点
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (i in list.indices) {
                        //大小
                        var size by remember { mutableStateOf(5.dp) }
                        size = if (pagerState.currentPage == i)  7.dp else 5.dp

                        //颜色
                        val color =
                            if (pagerState.currentPage == i) MaterialTheme.colors.primary else Color.Gray

                        Box(
                            modifier = Modifier.clip(CircleShape).background(color)
                                    //当size改变的时候以动画的形式改变
                                .animateContentSize().size(size)
                        )
                        //指示点间的间隔
                        if (i != list.lastIndex) Spacer(
                            modifier = Modifier.height(0.dp).width(4.dp)
                        )
                    }
                }

            }
        }

    }

}

/**
 * 轮播图数据
 */
data class BannerData(
    val imageUrl: String,
    val linkUrl: String
)