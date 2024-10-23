package com.practices.githubusers.internal.core.navigation.compositionLocal

fun noLocalProvidedFor(name: String): Nothing {
    error("CompositionLocal $name not present")
}