package com.piyushprajpti.feature.common.reset_password

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
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
fun ResetPasswordScreen(
    onBackClick: () -> Unit,
    onSuccess: () -> Unit,
    viewModel: ResetPasswordViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current

    LoadingWidget(viewModel.loadingHelper.state.value)
    ToastWidget(viewModel.messageHelper)
    AlertDialog(viewModel.messageHelper)

    fun onActionClick() {
        viewModel.onEvent(ResetPasswordEvent.OnActionClick(onSuccess))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .background(CustomTheme.colors.primaryBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(
            onBackClick = onBackClick,
            title = "Reset Password"
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    focusManager.clearFocus()
                }
                .padding(15.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = if (state.resetToken.isNotBlank()) "Set New Password" else if (state.isOtpSent) "Verify OTP" else "Forgot Password",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primaryText,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = if (state.resetToken.isNotBlank()) "Enter your new password" else if (state.isOtpSent) "Enter the OTP sent to ${state.email}" else "Enter your email to receive an OTP",
                fontSize = 15.sp,
                color = CustomTheme.colors.secondaryText,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(40.dp))

            if (!state.isOtpSent && state.resetToken.isBlank()) {
                CustomTextField(
                    value = state.email,
                    onValueChange = { viewModel.onEvent(ResetPasswordEvent.OnEmailChange(it)) },
                    label = "Email Address",
                    placeholder = "example@email.com",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { onActionClick() })
                )
            }

            if (state.isOtpSent && state.resetToken.isBlank()) {
                CustomTextField(
                    value = state.otp,
                    onValueChange = { viewModel.onEvent(ResetPasswordEvent.OnOtpChange(it)) },
                    label = "OTP",
                    placeholder = "Enter 6-digit OTP",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { onActionClick() })
                )
            }

            if (state.resetToken.isNotBlank()) {
                CustomTextField(
                    value = state.password,
                    onValueChange = { viewModel.onEvent(ResetPasswordEvent.OnPasswordChange(it)) },
                    label = "New Password",
                    placeholder = "Enter new password",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    isPasswordField = true
                )

                Spacer(modifier = Modifier.height(24.dp))

                CustomTextField(
                    value = state.confirmPassword,
                    onValueChange = { viewModel.onEvent(ResetPasswordEvent.OnConfirmPasswordChange(it)) },
                    label = "Confirm Password",
                    placeholder = "Confirm new password",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    isPasswordField = true,
                    keyboardActions = KeyboardActions(onDone = { onActionClick() })
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            PrimaryActionButton(
                text = if (state.resetToken.isNotBlank()) "Reset Password" else if (state.isOtpSent) "Verify OTP" else "Send OTP",
                onClick = {
                    focusManager.clearFocus()
                    onActionClick()
                }
            )
        }
    }
}
