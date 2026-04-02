package com.piyushprajpti.components.message_helper.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.zIndex
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.message_helper.MessageHelper
import com.piyushprajpti.components.message_helper.model.Alert

@Composable
fun AlertDialog(messageHelper: MessageHelper) {
    AlertDialogBase(messageHelper.state.value.alert) {
        messageHelper.setAlert(null)
    }
}

@Composable
fun AlertDialogBase(alert: Alert?, onDismissRequest: () -> Unit) {
    if (alert == null) return
    val label = alert.label
    val message = alert.message

    Surface(
        Modifier
            .fillMaxSize()
            .zIndex(10f),
        color = Color.Black.copy(alpha = 0.6f)
    ) {
        Popup(
            alignment = Alignment.Center,
            onDismissRequest = onDismissRequest,
            properties = PopupProperties(
                dismissOnBackPress = alert.cancelable,
                dismissOnClickOutside = alert.cancelable
            )
        ) {
            Column(
                Modifier
                    .padding(horizontal = 30.dp)
                    .width(IntrinsicSize.Max)
                    .widthIn(min = 280.dp)
                    .background(CustomTheme.colors.secondaryBackground, RoundedCornerShape(16.dp))
            ) {
                Text(
                    text = label,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, start = 20.dp, end = 20.dp, bottom = 12.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = CustomTheme.colors.primaryText
                )

                Text(
                    text = message,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 12.dp),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = CustomTheme.colors.secondaryText
                )

                Spacer(Modifier.height(24.dp))

                if (alert.requireAction) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .clip(RoundedCornerShape(bottomStart = 16.dp))
                                .background(CustomTheme.colors.outline.copy(alpha = 0.5f))
                                .clickable {
                                    alert.negativeAction()
                                    onDismissRequest()
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Cancel",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = CustomTheme.colors.primaryText
                            )
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .clip(RoundedCornerShape(bottomEnd = 16.dp))
                                .background(CustomTheme.colors.primary)
                                .clickable {
                                    alert.positiveAction()
                                    onDismissRequest()
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "OK",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = CustomTheme.colors.white
                            )
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                            .background(CustomTheme.colors.primary)
                            .clickable {
                                alert.neutralAction()
                                onDismissRequest()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "OK",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = CustomTheme.colors.white
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Spacer(modifier: Modifier) {
    Box(modifier = modifier)
}
