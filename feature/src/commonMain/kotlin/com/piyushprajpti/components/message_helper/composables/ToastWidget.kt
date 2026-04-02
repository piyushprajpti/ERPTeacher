package com.piyushprajpti.components.message_helper.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.message_helper.MessageHelper
import com.piyushprajpti.components.message_helper.model.Toast
import com.piyushprajpti.components.message_helper.model.ToastType

@Composable
fun ToastWidget(messageHelper: MessageHelper) {
    ToastWidgetBase(messageHelper.state.value.toast) {
        messageHelper.setToast(null)
    }
}

@Composable
private fun ToastWidgetBase(
    toast: Toast? = null,
    onDismissRequest: () -> Unit
) {
    if (toast == null) return
    
    val (backgroundColor, icon) = when (toast.type) {
        ToastType.SUCCESS -> CustomTheme.colors.success to Icons.Default.Check
        ToastType.WARNING -> CustomTheme.colors.warning to Icons.Default.Warning
        ToastType.ERROR -> CustomTheme.colors.error to Icons.Default.Close
        ToastType.INFO -> CustomTheme.colors.info to Icons.Default.Info
    }

    Popup(
        alignment = Alignment.TopCenter,
        onDismissRequest = onDismissRequest,
        properties = PopupProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp)
                .background(backgroundColor, RoundedCornerShape(16.dp))
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Text(
                text = toast.text,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
