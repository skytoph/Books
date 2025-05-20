package com.skytoph.books.ui.nav.main

import kotlinx.serialization.Serializable

@Serializable
sealed interface Graph {

    @Serializable
    object Auth : Graph

    @Serializable
    object Books : Graph
}