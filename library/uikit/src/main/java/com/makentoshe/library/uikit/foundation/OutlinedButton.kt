package com.makentoshe.library.uikit.foundation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.makentoshe.library.uikit.theme.BooruchanTheme

@Composable
fun OutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) = androidx.compose.material3.OutlinedButton(
    onClick = onClick,
    modifier = modifier,
    enabled = enabled,
    shape = RoundedCornerShape(4.dp),
    colors = ButtonDefaults.outlinedButtonColors(
        contentColor = BooruchanTheme.colors.separator,
        disabledContainerColor = BooruchanTheme.colors.opaque,
    ),
    elevation = null,
    border = ButtonDefaults.outlinedButtonBorder,
    contentPadding = ButtonDefaults.ContentPadding,
    interactionSource = remember { MutableInteractionSource() },
    content = content,
)