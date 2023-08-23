package com.aleexalvz.cashwise.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.aleexalvz.cashwise.ui.theme.Green

@Composable
fun outlinedTextFieldWithValidation(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    leadingIconImageVector: ImageVector,
    contentDescription: String,
    isPassword: Boolean = false
) {
    //TODO validation and field error
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
        unfocusedTrailingIconColor = Color.White
    )

    val passwordHidden = rememberSaveable { mutableStateOf(isPassword) }
    val visualTransformation =
        if (passwordHidden.value) PasswordVisualTransformation() else VisualTransformation.None
    OutlinedTextField(
        modifier = modifier,
        value = text,
        visualTransformation = visualTransformation,
        trailingIcon = {
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
        onValueChange = onValueChange,
        singleLine = true,
        label = {
            Text(
                text = labelText,
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