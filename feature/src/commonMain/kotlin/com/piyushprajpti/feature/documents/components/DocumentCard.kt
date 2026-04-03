package com.piyushprajpti.feature.documents.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.ReceiptLong
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.QueryBuilder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.documents.list.Document
import com.piyushprajpti.feature.documents.list.DocumentStatus
import com.piyushprajpti.feature.documents.list.DocumentType

@Composable
fun DocumentCard(
    document: Document,
    onDownloadClick: () -> Unit,
    onShareClick: () -> Unit,
    onRequestClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val icon = getDocumentIcon(document.type)
    val tint = getDocumentTint(document.type)

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .padding(15.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(tint.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = tint,
                modifier = Modifier.size(24.dp)
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = document.title,
                color = CustomTheme.colors.primaryText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                val statusColor = when (document.status) {
                    DocumentStatus.Available -> CustomTheme.colors.success
                    DocumentStatus.RequestRequired -> CustomTheme.colors.warning
                    DocumentStatus.UnderReview -> CustomTheme.colors.info
                }

                val statusIcon = when (document.status) {
                    DocumentStatus.Available -> Icons.Default.Info
                    DocumentStatus.RequestRequired -> Icons.Default.Info
                    DocumentStatus.UnderReview -> Icons.Default.QueryBuilder
                }

                Icon(
                    imageVector = statusIcon,
                    contentDescription = null,
                    tint = statusColor,
                    modifier = Modifier.size(14.dp)
                )

                Text(
                    text = when (document.status) {
                        DocumentStatus.Available -> "Available"
                        DocumentStatus.RequestRequired -> "Request Required"
                        DocumentStatus.UnderReview -> "Under Review"
                    },
                    color = statusColor,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        when (document.status) {
            DocumentStatus.Available -> {
                Row {
                    IconButton(onClick = onDownloadClick) {
                        Icon(
                            imageVector = Icons.Default.Download,
                            contentDescription = "Download",
                            tint = CustomTheme.colors.tertiaryText,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    IconButton(onClick = onShareClick) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = CustomTheme.colors.tertiaryText,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            DocumentStatus.RequestRequired -> {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(CustomTheme.colors.outline.copy(alpha = 0.5f))
                        .clickable(onClick = onRequestClick)
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "REQUEST",
                        color = CustomTheme.colors.primaryText,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            DocumentStatus.UnderReview -> {
                IconButton(onClick = {}, enabled = false) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Under Review",
                        tint = CustomTheme.colors.tertiaryText,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

private fun getDocumentIcon(type: DocumentType): ImageVector {
    return when (type) {
        DocumentType.ADMISSION_LETTER -> Icons.Default.Description
        DocumentType.MARKSHEET -> Icons.AutoMirrored.Filled.Assignment
        DocumentType.CERTIFICATE -> Icons.AutoMirrored.Filled.Assignment
        DocumentType.FEE_RECEIPT -> Icons.AutoMirrored.Filled.ReceiptLong
        DocumentType.CLEARANCE -> Icons.AutoMirrored.Filled.MenuBook
    }
}

@Composable
private fun getDocumentTint(type: DocumentType): Color {
    return when (type) {
        DocumentType.ADMISSION_LETTER -> CustomTheme.colors.primary
        DocumentType.MARKSHEET -> CustomTheme.colors.success
        DocumentType.CERTIFICATE -> CustomTheme.colors.blue
        DocumentType.FEE_RECEIPT -> CustomTheme.colors.orange
        DocumentType.CLEARANCE -> CustomTheme.colors.purple
    }
}
