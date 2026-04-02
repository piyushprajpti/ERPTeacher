package com.piyushprajpti.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.piyushprajpti.college_erp_system.theme.CustomTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropDown(
    modifier: Modifier = Modifier,
    label: String,
    itemList: List<T>,
    selectedItem: T?,
    itemToString: (T) -> String,
    itemToLogoUrl: (T) -> String? = { null },
    onItemSelected: (T) -> Unit,
    enabled: Boolean = true,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = CustomTheme.colors.primaryText,
        unfocusedTextColor = CustomTheme.colors.primaryText,
        disabledTextColor = CustomTheme.colors.tertiaryText,
        errorTextColor = CustomTheme.colors.error,
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        disabledContainerColor = CustomTheme.colors.primaryBackground.copy(0.2f),
        errorContainerColor = CustomTheme.colors.error.copy(0.1f),
        cursorColor = CustomTheme.colors.primary,
        errorCursorColor = CustomTheme.colors.error,
        focusedBorderColor = CustomTheme.colors.primary,
        unfocusedBorderColor = CustomTheme.colors.secondaryText,
        disabledBorderColor = CustomTheme.colors.primaryText.copy(alpha = 0.5f),
        errorBorderColor = CustomTheme.colors.error,
        focusedLeadingIconColor = CustomTheme.colors.primary,
        unfocusedLeadingIconColor = CustomTheme.colors.secondaryText,
        disabledLeadingIconColor = CustomTheme.colors.secondaryText.copy(alpha = 0.5f),
        errorLeadingIconColor = CustomTheme.colors.error,
        focusedTrailingIconColor = CustomTheme.colors.primary,
        unfocusedTrailingIconColor = CustomTheme.colors.secondaryText,
        disabledTrailingIconColor = CustomTheme.colors.secondaryText.copy(alpha = 0.5f),
        errorTrailingIconColor = CustomTheme.colors.error,
        focusedLabelColor = CustomTheme.colors.primary,
        unfocusedLabelColor = CustomTheme.colors.secondaryText,
        disabledLabelColor = CustomTheme.colors.secondaryText.copy(alpha = 0.5f),
        errorLabelColor = CustomTheme.colors.error,
        focusedPlaceholderColor = CustomTheme.colors.secondaryText,
        unfocusedPlaceholderColor = CustomTheme.colors.secondaryText,
        disabledPlaceholderColor = CustomTheme.colors.secondaryText.copy(alpha = 0.5f),
        errorPlaceholderColor = CustomTheme.colors.error,
        focusedSupportingTextColor = CustomTheme.colors.primary,
        unfocusedSupportingTextColor = CustomTheme.colors.secondaryText,
        disabledSupportingTextColor = CustomTheme.colors.secondaryText.copy(alpha = 0.5f),
        errorSupportingTextColor = CustomTheme.colors.error,
        focusedPrefixColor = CustomTheme.colors.primaryText,
        unfocusedPrefixColor = CustomTheme.colors.primaryText,
        disabledPrefixColor = CustomTheme.colors.tertiaryText,
        errorPrefixColor = CustomTheme.colors.error,
        focusedSuffixColor = CustomTheme.colors.primaryText,
        unfocusedSuffixColor = CustomTheme.colors.primaryText,
        disabledSuffixColor = CustomTheme.colors.tertiaryText,
        errorSuffixColor = CustomTheme.colors.error
    )
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { if (enabled) expanded = !expanded },
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedItem?.let { itemToString(it) } ?: "",
            onValueChange = {},
            readOnly = true,
            enabled = enabled,
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable, true)
                .fillMaxWidth(),
            colors = colors
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },

            ) {
            itemList.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(text = itemToString(item))
                    },
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    },
                    leadingIcon = {
                        LogoBox(
                            imageUrl = itemToLogoUrl(item),
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    colors = MenuDefaults.itemColors(
                        textColor = CustomTheme.colors.primaryText,
                        leadingIconColor = CustomTheme.colors.primary,
                        trailingIconColor = CustomTheme.colors.secondaryText,
                        disabledTextColor = CustomTheme.colors.secondaryText.copy(alpha = 0.5f),
                        disabledLeadingIconColor = CustomTheme.colors.secondaryText.copy(alpha = 0.5f),
                        disabledTrailingIconColor = CustomTheme.colors.secondaryText.copy(alpha = 0.5f),
                    ),
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}
