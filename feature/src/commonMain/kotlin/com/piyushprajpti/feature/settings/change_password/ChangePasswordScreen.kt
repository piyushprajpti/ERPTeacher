package com.piyushprajpti.feature.settings.change_password

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.CustomTextField
import com.piyushprajpti.components.PrimaryActionButton
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ChangePasswordScreen(
    onBackClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onSuccess: () -> Unit,
    viewModel: ChangePasswordViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current

    LoadingWidget(viewModel.loadingHelper.state.value)
    AlertDialog(viewModel.messageHelper)
    ToastWidget(viewModel.messageHelper)

    fun onChangePasswordClick() {
        viewModel.onEvent(
            ChangePasswordEvent.OnChangePasswordClick(
                onSuccess = onSuccess
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .background(CustomTheme.colors.primaryBackground)
    ) {
        TopBar(
            onBackClick = onBackClick,
            title = "Change Password"
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
                .verticalScroll(rememberScrollState())
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { focusManager.clearFocus() })
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Spacer(modifier = Modifier.height(30.dp))

            CustomTextField(
                value = state.currentPassword,
                onValueChange = { viewModel.onEvent(ChangePasswordEvent.OnCurrentPasswordChange(it)) },
                label = "Current Password",
                placeholder = "Enter current password",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                isPasswordField = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField(
                value = state.newPassword,
                onValueChange = { viewModel.onEvent(ChangePasswordEvent.OnNewPasswordChange(it)) },
                label = "New Password",
                placeholder = "Enter new password",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                isPasswordField = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField(
                value = state.confirmPassword,
                onValueChange = { viewModel.onEvent(ChangePasswordEvent.OnConfirmPasswordChange(it)) },
                label = "Re-enter New Password",
                placeholder = "Re-enter new password",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                isPasswordField = true
            )

            TextButton(
                onClick = onForgotPasswordClick,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = "Forgot Password?",
                    color = CustomTheme.colors.primary,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            PrimaryActionButton(
                text = "Change Password",
                onClick = { onChangePasswordClick() }
            )
        }
    }
}