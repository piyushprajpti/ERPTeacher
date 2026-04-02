package com.piyushprajpti.feature.common.support

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
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
fun SupportScreen(
    onBackClick: () -> Unit,
    viewModel: SupportViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    LoadingWidget(viewModel.loadingHelper.state.value)
    ToastWidget(viewModel.messageHelper)
    AlertDialog(viewModel.messageHelper)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground)
            .statusBarsPadding()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopBar(
            onBackClick = onBackClick,
            title = "Contact Support"
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { focusManager.clearFocus() })
                }
                .verticalScroll(scrollState)
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            Text(
                text = "Get in touch",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.white
            )

            CustomTextField(
                value = state.name,
                onValueChange = { viewModel.onEvent(SupportEvent.OnNameChange(it)) },
                label = "Full Name",
                placeholder = "John Doe",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

            CustomTextField(
                value = state.email,
                onValueChange = { viewModel.onEvent(SupportEvent.OnEmailChange(it)) },
                label = "Email Address",
                placeholder = "example@email.com",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )

            CustomTextField(
                value = state.subject,
                onValueChange = { viewModel.onEvent(SupportEvent.OnSubjectChange(it)) },
                label = "Subject",
                placeholder = "What is this regarding?",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

            CustomTextField(
                value = state.description,
                onValueChange = { viewModel.onEvent(SupportEvent.OnDescriptionChange(it)) },
                label = "Description",
                placeholder = "Describe your issue in detail",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                        viewModel.onEvent(SupportEvent.OnSubmitClick)
                    }
                ),
                textFieldModifier = Modifier.height(150.dp).fillMaxWidth(),
                singleLine = false
            )

            Spacer(modifier = Modifier.height(8.dp))

            PrimaryActionButton(
                text = "Submit Request",
                onClick = {
                    focusManager.clearFocus()
                    viewModel.onEvent(SupportEvent.OnSubmitClick)
                }
            )
        }
    }
}
