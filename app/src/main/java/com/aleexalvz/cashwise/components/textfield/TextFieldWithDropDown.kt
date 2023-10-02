package com.aleexalvz.cashwise.components.textfield

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithDropDown(
    modifier: Modifier,
    dropDownValues: List<String>,
    labelText: String? = "",
    onSelectedItem: ((String) -> Unit)? = null
) {
    val expanded = remember {
        mutableStateOf(false)
    }

    val currentText = remember {
        mutableStateOf("")
    }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded.value,
        onExpandedChange = { expanded.value = !expanded.value }
    ) {
        DefaultOutlinedTextField(
            modifier = Modifier.menuAnchor(),
            text = currentText.value,
            onValueChange = {},
            labelText = labelText.orEmpty(),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded.value
                )
            },
            readOnly = true
        )

        ExposedDropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }) {

            dropDownValues.forEach { value ->
                DropdownMenuItem(
                    text = { Text(text = value) },
                    onClick = {
                        expanded.value = false
                        currentText.value = value
                        onSelectedItem?.invoke(value)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun TextFieldWithDropDownPreview() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        TextFieldWithDropDown(
            Modifier
                .fillMaxWidth()
                .height(30.dp),
            listOf("Item 1", "Item 2", "Item 3")
        )
    }
}