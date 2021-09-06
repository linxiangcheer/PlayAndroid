package com.linx.playAndroid.public

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

/**
 * 简单的提示框
 * [confirmClick] 确定按钮的点击事件
 */
@Composable
fun SimpleAlertDialog(
    title: String,
    contextStr: String,
    dismissStr: String = "取消",
    confirmStr: String = "确定",
    confirmClick: () -> Unit = {},
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(title)
        },
        text = {
            Text(contextStr)
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(dismissStr)
            }
        },
        confirmButton = {
            TextButton(onClick = {
                confirmClick()
                onDismiss()
            }) {
                Text(confirmStr)
            }
        }
    )
}

/**
 * 内容自定义的弹窗
 */
@Composable
fun ContentCustomAlertDialog(
    title: String,
    contextStr: String = "",
    textCompose: @Composable (() -> Unit)? = null,
    dismissStr: String? = null,
    confirmStr: String? = null,
    confirmClick: () -> Unit = {},
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(title)
        },
        text = {
            if (textCompose == null)
                Text(contextStr)
            else textCompose()
        },
        dismissButton = {
            if (dismissStr != null) {
                TextButton(onClick = onDismiss) {
                    Text(dismissStr)
                }
            }
        },
        confirmButton = {
            if (confirmStr != null) {
                TextButton(onClick = {
                    confirmClick()
                    onDismiss()
                }) {
                    Text(confirmStr)
                }
            }
        }
    )
}