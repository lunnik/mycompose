package com.argaed.myapplicationcompose.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sura.im.sources.presentation.common.ViewStatus
import com.sura.im.sources.presentation.extension.getColorStatusView

@Preview
@Composable
fun PinViewPreview() {
    val (pinValue, onPinValueChange) = remember {
        mutableStateOf("")
    }
    PinView(
        pinText = pinValue,
        onPinTextChange = onPinValueChange,
        type = PIN_VIEW_TYPE_UNDERLINE,
        digitCount = 5,
        digitSize = 34.sp
    )
}

/* */
const val PIN_VIEW_TYPE_UNDERLINE = 0

/* */
const val PIN_VIEW_TYPE_BORDER = 1

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PinView(
    modifier: Modifier = Modifier,
    pinText: String,
    onPinTextChange: (String) -> Unit,
    digitColor: Color = darkBlueSteel,
    digitSize: TextUnit = 16.sp,
    containerSize: Dp = digitSize.value.dp,
    digitCount: Int = 5,
    type: Int = PIN_VIEW_TYPE_UNDERLINE,
    viewStatusMutableState: MutableState<ViewStatus> = mutableStateOf(ViewStatus.Inactive)
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    BasicTextField(
        modifier = modifier.onFocusChanged { focusState ->
            viewStatusMutableState.value = when {
                focusState.isFocused -> ViewStatus.IsFocused
                else -> if (pinText.isEmpty()) ViewStatus.Inactive else ViewStatus.Done
            }
        },
        value = pinText,
        onValueChange = { it ->
            if (it.length <= digitCount) {
                viewStatusMutableState.value = ViewStatus.Done
                onPinTextChange(it.filter { it.isDigit() })
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                repeat(digitCount) { index ->
                    DigitView(
                        index = index,
                        pinText = pinText,
                        digitColor = digitColor,
                        digitSize = digitSize,
                        containerSize = containerSize,
                        type = type,
                        viewStatusMutableState = viewStatusMutableState
                    )

                    Spacer(modifier = Modifier.width(5.dp))
                }
            }
        })
}


@Composable
private fun DigitView(
    index: Int,
    pinText: String,
    digitColor: Color,
    digitSize: TextUnit,
    containerSize: Dp,
    type: Int = PIN_VIEW_TYPE_UNDERLINE,
    viewStatusMutableState: MutableState<ViewStatus> = mutableStateOf(ViewStatus.Inactive)
) {
    val modifier = if (type == PIN_VIEW_TYPE_BORDER) {
        Modifier
            .width(containerSize)
            .border(
                width = 1.dp,
                color = Color.getColorStatusView(viewStatusMutableState.value),
                shape = MaterialTheme.shapes.medium
            )
            .padding(bottom = 3.dp)
    } else Modifier.width(containerSize)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val digitIsEmpty = (index >= pinText.length)
        Text(
            text = if (digitIsEmpty) "" else pinText[index].toString(),
            color = digitColor,
            modifier = modifier,
            fontSize = digitSize,
            textAlign = TextAlign.Center
        )
        if (type == PIN_VIEW_TYPE_UNDERLINE) {
            Spacer(modifier = Modifier.height(2.dp))
            val colorStatus = when (viewStatusMutableState.value) {
                ViewStatus.Done -> if (digitIsEmpty) colorInactiveFill else greenOpaque
                ViewStatus.Error ->
                    if (digitIsEmpty) ErrorColor
                    else if (!digitIsEmpty) {
                        greenOpaque
                    } else colorInactiveFill
                ViewStatus.Inactive -> colorInactiveFill
                ViewStatus.IsFocused -> blueSteel
            }
            Box(
                modifier = Modifier
                    .background(colorStatus)
                    .height(1.dp)
                    .width(containerSize)
            )
        }
    }
}