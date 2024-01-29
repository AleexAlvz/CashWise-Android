package com.aleexalvz.cashwise.components.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.aleexalvz.cashwise.components.passwordTextFielColor

@Composable
fun PasswordOutlinedTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    contentDescription: String,
    errorMessage: String? = null
) {
    val passwordHidden = rememberSaveable { mutableStateOf(true) }
    val visualTransformation =
        if (passwordHidden.value) PasswordVisualTransformation()
        else VisualTransformation.None

    OutlinedTextField(
        modifier = modifier.wrapContentHeight(),
        value = text,
        textStyle = TextStyle(fontSize = 14.sp),
        visualTransformation = visualTransformation,
        trailingIcon = {
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
        },
        isError = (errorMessage != null),
        supportingText = {
            if (errorMessage != null) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = errorMessage,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        onValueChange = onValueChange,
        singleLine = true,
        label = {
            Text(
                text = labelText,
                fontSize = 14.sp,
            )
        },
        leadingIcon = {
            Icon(
                Icons.Default.Lock,
                contentDescription = contentDescription
            )
        },
        colors = passwordTextFielColor()
    )
}