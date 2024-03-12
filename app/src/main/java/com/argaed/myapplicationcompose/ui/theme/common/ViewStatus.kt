package com.sura.im.sources.presentation.common


/**
 *
 *
 * */
sealed class ViewStatus {
    /* */
    object IsFocused : ViewStatus()

    /* */
    object Inactive : ViewStatus()

    /* */
    object Error : ViewStatus()

    /* */
    object Done : ViewStatus()

}

/** */
fun ViewStatus.isError() = this == ViewStatus.Error