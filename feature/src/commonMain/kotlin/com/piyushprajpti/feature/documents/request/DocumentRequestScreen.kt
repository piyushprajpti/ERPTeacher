package com.piyushprajpti.feature.documents.request

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.CustomTextField
import com.piyushprajpti.components.DropDown
import com.piyushprajpti.components.PrimaryActionButton
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.documents.components.Counter
import com.piyushprajpti.feature.documents.components.DeliveryMethodCard
import com.piyushprajpti.feature.documents.components.InfoCard
import com.piyushprajpti.feature.documents.components.RecentRequestItem
import com.piyushprajpti.feature.documents.components.SectionHeader
import com.piyushprajpti.feature.documents.components.UploadPlaceholder
import com.piyushprajpti.feature.documents.components.UrgencySelector
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DocumentRequestScreen(
    onBackClick: () -> Unit,
    viewModel: DocumentRequestViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoadingWidget(viewModel.loadingHelper.state.value)
    AlertDialog(viewModel.messageHelper)
    ToastWidget(viewModel.messageHelper)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        TopBar(
            onBackClick = onBackClick,
            title = "Request Document"
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(15.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                InfoCard()
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
                    SectionHeader(title = "Document Type")
                    DropDown(
                        label = "Select Document",
                        itemList = state.documents,
                        selectedItem = state.selectedDocument.takeIf { it.isNotEmpty() },
                        itemToString = { it },
                        onItemSelected = { viewModel.onEvent(DocumentRequestEvent.OnDocumentSelect(it)) }
                    )

                    SectionHeader(title = "Purpose of Request")
                    CustomTextField(
                        value = state.purpose,
                        onValueChange = { viewModel.onEvent(DocumentRequestEvent.OnPurposeChange(it)) },
                        placeholder = "e.g., Internship Application",
                        singleLine = false,
                        textFieldModifier = Modifier.heightIn(min = 100.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            SectionHeader(title = "No. of Copies")
                            Counter(
                                value = state.copies,
                                onIncrement = { viewModel.onEvent(DocumentRequestEvent.OnIncrementCopies) },
                                onDecrement = { viewModel.onEvent(DocumentRequestEvent.OnDecrementCopies) }
                            )
                        }
                        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            SectionHeader(title = "Urgency")
                            UrgencySelector(
                                selectedUrgency = state.urgency,
                                onUrgencyChange = {
                                    viewModel.onEvent(
                                        DocumentRequestEvent.OnUrgencyChange(
                                            it
                                        )
                                    )
                                }
                            )
                        }
                    }

                    SectionHeader(title = "Delivery Method")
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        DeliveryMethodCard(
                            modifier = Modifier.weight(1f),
                            title = "Digital Copy",
                            icon = Icons.Default.CloudDownload,
                            isSelected = state.deliveryMethod == DeliveryMethod.DIGITAL,
                            onClick = {
                                viewModel.onEvent(
                                    DocumentRequestEvent.OnDeliveryMethodChange(
                                        DeliveryMethod.DIGITAL
                                    )
                                )
                            }
                        )
                        DeliveryMethodCard(
                            modifier = Modifier.weight(1f),
                            title = "Physical Pickup",
                            icon = Icons.Default.LocalShipping,
                            isSelected = state.deliveryMethod == DeliveryMethod.PHYSICAL,
                            onClick = {
                                viewModel.onEvent(
                                    DocumentRequestEvent.OnDeliveryMethodChange(
                                        DeliveryMethod.PHYSICAL
                                    )
                                )
                            }
                        )
                    }

                    SectionHeader(title = "Supporting Documents")
                    UploadPlaceholder(
                        onClick = { viewModel.onEvent(DocumentRequestEvent.OnUploadClick) }
                    )

                    PrimaryActionButton(
                        text = "Submit Request",
                        onClick = { viewModel.onEvent(DocumentRequestEvent.OnSubmitRequest) },
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Recent Requests",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.primaryText
                    )
                    Text(
                        text = "View All",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = CustomTheme.colors.primary,
                        modifier = Modifier.clickable { viewModel.onEvent(DocumentRequestEvent.OnViewAllRequests) }
                    )
                }
            }

            items(state.recentRequests) { request ->
                RecentRequestItem(
                    request = request,
                    onClick = { viewModel.onEvent(DocumentRequestEvent.OnRecentRequestClick(request.id)) }
                )
            }
        }
    }
}
