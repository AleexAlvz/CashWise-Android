package com.aleexalvz.cashwise.components.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.aleexalvz.cashwise.components.defaultTextFieldColor

@Composable
fun DefaultOutlinedTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    errorMessage: String? = null,
    readOnly: Boolean? = false
) {

    OutlinedTextField(
        modifier = modifier.wrapContentHeight(),
        value = text,
        trailingIcon = {
            trailingIcon ?: errorMessage?.let {
                Icon(imageVector = Icons.Filled.Warning, "Error icon")
            }
        },
        isError = (errorMessage != null),
        supportingText = {
            if (errorMessage != null) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = errorMessage,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        onValueChange = {
            onValueChange(it)
        },
        singleLine = true,
        label = {
            Text(
                text = labelText,
                fontSize = 16.sp,
            )
        },
        leadingIcon = leadingIcon,
        readOnly = readOnly ?: false,
        colors = defaultTextFieldColor()
    )
}