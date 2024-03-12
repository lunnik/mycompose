package com.sura.im.sources.presentation.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.argaed.myapplicationcompose.ui.theme.*
import com.sura.im.sources.presentation.common.ViewStatus

/**
 *
 * */
@Composable
fun Color.Companion.getColorStatusView(viewStatus: ViewStatus): Color =
    when (viewStatus) {
        ViewStatus.Error -> ErrorColor
        ViewStatus.Inactive -> hintColor
        ViewStatus.IsFocused -> blueSteel
        ViewStatus.Done -> blueSteel
    }