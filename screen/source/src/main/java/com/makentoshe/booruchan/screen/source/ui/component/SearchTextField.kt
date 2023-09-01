package com.makentoshe.booruchan.screen.source.ui.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.makentoshe.library.uikit.theme.BooruchanTheme
import com.makentoshe.library.uikit.theme.transparent

@Composable
internal fun SearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
    shape: Shape = RoundedCornerShape(28.dp),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Search,
    ),
    colors: TextFieldColors = TextFieldDefaults.colors(
        focusedIndicatorColor = BooruchanTheme.colors.transparent,
        disabledIndicatorColor = BooruchanTheme.colors.transparent,
        unfocusedIndicatorColor = BooruchanTheme.colors.transparent,
        unfocusedContainerColor = BooruchanTheme.colors.separator,
        focusedContainerColor = BooruchanTheme.colors.separator,
    ),
) = TextField(
    modifier = modifier,
    value = value,
    onValueChange = onValueChange,
    singleLine = true,
    shape = shape,
    colors = colors,
    label = label,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
)