package com.aleexalvz.cashwise.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.aleexalvz.cashwise.ui.theme.Green

@Composable
fun OutlinedTextFieldWithValidation(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    leadingIconImageVector: ImageVector,
    contentDescription: String,
    isPassword: Boolean = false,
    errorMessage: String? = null
) {
    val outlinedTextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Green,
        unfocusedBorderColor = Green,
        cursorColor = Color.White,
        focusedLabelColor = Color.White,
        unfocusedLabelColor = Color.White,
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White,
        focusedLeadingIconColor = Color.White,
        unfocusedLeadingIconColor = Color.White,
        focusedTrailingIconColor = Color.White,
        unfocusedTrailingIconColor = Color.White,
        errorLeadingIconColor = Color.White,
        errorTextColor = Color.White,
        errorLabelColor = Color.White,
        errorSupportingTextColor = MaterialTheme.colorScheme.error,
        errorTrailingIconColor = if (isPassword) Color.White else MaterialTheme.colorScheme.error
    )

    val passwordHidden = rememberSaveable { mutableStateOf(isPassword) }
    val visualTransformation =
        if (passwordHidden.value) PasswordVisualTransformation()
        else VisualTransformation.None

    OutlinedTextField(
        modifier = modifier.wrapContentHeight(),
        value = text,
        visualTransformation = visualTransformation,
        trailingIcon = {
            if (!isPassword && errorMessage != null) {
                Icon(imageVector = Icons.Filled.Warning, "Error icon")
            }
            if (isPassword) {
                IconButton(onClick = { passwordHidden.value = !passwordHidden.value }) {
                    val visibilityIcon =
                        if (passwordHidden.value) Icons.Default.Visibility
                        else Icons.Filled.VisibilityOff
                    val description = if (passwordHidden.value) "Show password" else "Hide password"
                    Icon(
                        imageVector = visibilityIcon,
                        contentDescription = description
                    )
                }
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
        onValueChange = onValueChange,
        singleLine = true,
        label = {
            Text(
                text = labelText,
                fontSize = 16.sp,
            )
        },
        leadingIcon = {
            Icon(
                leadingIconImageVector,
                contentDescription = contentDescription
            )
        },
        colors = outlinedTextFieldColors
    )
}