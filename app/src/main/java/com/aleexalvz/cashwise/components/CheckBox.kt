package com.aleexalvz.cashwise.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aleexalvz.cashwise.ui.theme.GraySuperLight
import com.aleexalvz.cashwise.ui.theme.Green

@Composable
fun CheckBox(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onStateChanged: (Boolean) -> Unit,
    text: String? = null
) {
    val state = mutableStateOf(selected)

    Row(
        modifier = modifier
            .toggleable(
                value = state.value,
                onValueChange = {
                    state.value = !state.value
                    onStateChanged(state.value)
                },
                role = Role.Checkbox
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val checkBoxColors = CheckboxDefaults.colors(
            checkedColor = GraySuperLight,
            uncheckedColor = GraySuperLight,
            checkmarkColor = Green,
        )

        Checkbox(
            checked = state.value,
            onCheckedChange = {
                state.value = !state.value
                onStateChanged(state.value)
            },
            colors = checkBoxColors
        )
        text?.let {
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = text,
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Center

            )
        }
    }
}
