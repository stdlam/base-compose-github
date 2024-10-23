package com.practices.githubusers.internal.core.util

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable

typealias EmptyCompose = @Composable () -> Unit
typealias Param1Compose<T> = @Composable (T) -> Unit
typealias Param2Compose<I1, I2> = @Composable (I1, I2) -> Unit
typealias Param3Compose<I1, I2, I3> = @Composable (I1, I2, I3) -> Unit

typealias ColCompose = @Composable ColumnScope.() -> Unit

typealias EmptyFunc = () -> Unit
typealias ParamFunc<T> = (T) -> Unit
typealias ParamFunc2<T, T2> = (T, T2) -> Unit