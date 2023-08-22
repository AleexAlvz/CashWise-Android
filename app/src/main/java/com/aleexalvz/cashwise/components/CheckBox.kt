package com.aleexalvz.cashwise.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aleexalvz.cashwise.ui.theme.GrayDefault
import com.aleexalvz.cashwise.ui.theme.Green

@Composable
fun checkBox(
    selected: MutableState<Boolean>,
    text: String? = null,
    modifier: Modifier = Modifier,
) {

    Row(
        modifier = modifier
            .padding(4.dp)
            .toggleable(
                value = selected.value,
                onValueChange = { selected.value = !selected.value },
                role = Role.Checkbox
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val checkBoxColors = CheckboxDefaults.colors(
            checkedColor = GrayDefault,
            checkmarkColor = Green,
            uncheckedColor = Green
        )

        Checkbox(
            checked = selected.value,
            onCheckedChange = { selected.value = !selected.value },
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

@Composable
@Preview
fun checkBoxPreview() {
    val selectedState = rememberSaveable { mutableStateOf(true) }
    checkBox(
        selected = selectedState,
        text = "Remember me"
    )
}