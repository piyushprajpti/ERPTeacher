package com.piyushprajpti.feature.documents.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.documents.components.DocumentCard
import com.piyushprajpti.feature.documents.components.StudentIdCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DocumentListScreen(
    onBackClick: () -> Unit,
    onRequestDocumentClick: () -> Unit,
    viewModel: DocumentListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(CustomTheme.colors.primaryBackground)
                .statusBarsPadding()
        ) {
            TopBar(
                onBackClick = onBackClick,
                title = "Documents"
            )

            LoadingWidget(viewModel.loadingHelper.state.value)
            AlertDialog(viewModel.messageHelper)
            ToastWidget(viewModel.messageHelper)

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                item {
                    StudentIdCard(
                        info = state.studentInfo,
                        onShowScannerClick = { viewModel.onEvent(DocumentListEvent.ShowScanner) }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Your Documents",
                        color = CustomTheme.colors.primaryText,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                items(state.documents) { document ->
                    DocumentCard(
                        document = document,
                        onDownloadClick = { viewModel.onEvent(DocumentListEvent.DownloadDocument(document.id)) },
                        onShareClick = { viewModel.onEvent(DocumentListEvent.ShareDocument(document.id)) },
                        onRequestClick = { viewModel.onEvent(DocumentListEvent.RequestDocument(document.id)) }
                    )
                }

                item {
                    Spacer(modifier = Modifier.navigationBarsPadding())
                }
            }
        }

        FloatingActionButton(
            onClick = onRequestDocumentClick,
            containerColor = CustomTheme.colors.primary,
            contentColor = Color.White,
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(25.dp)
                .navigationBarsPadding()
        ) {
            Icon(
                imageVector = Icons.Default.PostAdd,
                contentDescription = "Add Document",
                modifier = Modifier.size(28.dp)
            )
        }
    }
}
