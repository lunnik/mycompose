package com.sura.im.sources.presentation.common


import android.app.Activity
import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.toComposeRect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

/**
 * Opinionated set of viewport breakpoints
 *     - Compact: Most phones in portrait mode
 *     - Medium: Most foldables and tablets in portrait mode
 *     - Expanded: Most tablets in landscape mode
 *
 * More info: https://material.io/archive/guidelines/layout/responsive-ui.html
 */
enum class WindowSize { Small, Compact, Medium, Expanded }

/**
 * Remembers the [WindowSize] class for the window corresponding to the current window metrics.
 */

/**
 * Remembers the [Size] in pixels of the window corresponding to the current window metrics.
 */

/**
 * Partitions a [DpSize] into a enumerated [WindowSize] class.
 */
@VisibleForTesting
fun getWindowSizeClass(windowDpSize: DpSize): WindowSize = when {
    windowDpSize.width < 0.dp -> throw IllegalArgumentException("Dp value cannot be negative")
    windowDpSize.width <= 320.dp || windowDpSize.height <= 550.dp -> WindowSize.Small
    windowDpSize.width < 600.dp -> WindowSize.Compact
    windowDpSize.width < 840.dp -> WindowSize.Medium
    else -> WindowSize.Expanded

}

/** */
fun WindowSize.isTablet() = (this == WindowSize.Medium || this == WindowSize.Expanded)

/** */
fun WindowSize.isSmall() = (this == WindowSize.Small)

/** */
fun WindowSize.isCompact() = (this == WindowSize.Compact)

/** */
fun WindowSize.isNotCompact() = (this != WindowSize.Compact)

fun WindowSize.isNotSmall() = (this != WindowSize.Small)




