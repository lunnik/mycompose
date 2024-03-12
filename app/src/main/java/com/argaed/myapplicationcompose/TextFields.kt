package com.argaed.myapplicationcompose

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.argaed.myapplicationcompose.ui.theme.ErrorColor
import com.argaed.myapplicationcompose.ui.theme.MyApplicationComposeTheme
import com.argaed.myapplicationcompose.ui.theme.blueSteel
import com.argaed.myapplicationcompose.ui.theme.textColor
import com.sura.im.sources.presentation.common.ViewStatus
import com.sura.im.sources.presentation.extension.getColorStatusView

@Preview
@Composable
private fun PreviewDefault() {
    MyApplicationComposeTheme {
        CustomTextFieldStyle()
    }
}



@Composable
fun TextFieldStyleDateOfBirth(
    onClick: () -> Unit = {},
    viewStatus: ViewStatus = ViewStatus.Error
) {
    Row(modifier = Modifier
        .height(IntrinsicSize.Min)
        .clickable { onClick() }) {
        Text(
            text = "05",
            Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.W400,
            color = Color.getColorStatusView(viewStatus),
            fontSize = 24.sp
        )
        Text(
            text = "/",
            Modifier.weight(1f),
            textAlign = TextAlign.Center,
            color = Color.getColorStatusView(viewStatus),
            fontSize = 24.sp
        )
        Text(
            text = "10",
            Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.W400,
            color = Color.getColorStatusView(viewStatus),
            fontSize = 24.sp
        )
        Text(
            text = "/",
            Modifier.weight(1f),
            textAlign = TextAlign.Center,
            color = Color.getColorStatusView(viewStatus),
            fontSize = 24.sp
        )
        Text(
            text = "2020",
            Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.W400,
            color = Color.getColorStatusView(viewStatus),
            fontSize = 24.sp
        )

    }
    //TabRowDefaults.Divider(color = Color.getColorStatusView(viewStatus), thickness = 1.dp)
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CustomTextFieldStyle(
    modifier: Modifier = Modifier,
    inputValue: MutableState<String> = mutableStateOf(""),
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    assistant: String = "",
    isError: Boolean = false
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column {
        /*TEXT ASSISTANT TOP*/
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = assistant,
            color = blueSteel,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.padding(top = 4.dp))
        TextField(
            modifier = modifier.border(
                brush = if (isError) Brush.horizontalGradient(listOf(ErrorColor, ErrorColor))
                else Brush.horizontalGradient(listOf(blueSteel, blueSteel)),
                width = 2.dp,
                shape = CircleShape
            ),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ), textStyle = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = textColor
            ),
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(onDone = {keyboardController?.hide()}),
            placeholder = {
                Text(
                    text = placeholder,
                    color = blueSteel,
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp
                )
            },
            singleLine = true,
            value = inputValue.value,
            onValueChange = {
                inputValue.value = it
            }
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextFieldStyle(
    modifier: Modifier = Modifier,
    inputValue: MutableState<String> = mutableStateOf(""),
    label: String = "label",
) {
    OutlinedTextField(
        modifier = modifier
            .border(
                brush = Brush.horizontalGradient(listOf(blueSteel, blueSteel)),
                width = 2.dp,
                shape = CircleShape
            ),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        label = { Text(text = label) },
        value = inputValue.value,
        onValueChange = {
            inputValue.value = it
        }
    )
}

@Preview(widthDp = 400, heightDp = 550)
@Composable
private fun PreviewDefaultSmallMoreOther() {
    MyApplicationComposeTheme() {
        Column {
            CustomOutlinedTextFieldStyle()
            CustomTextFieldStyle()
        }

    }

}