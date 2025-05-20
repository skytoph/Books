package com.skytoph.books.ui.nav.auth

import kotlinx.serialization.Serializable

@Serializable
sealed interface AuthRoutes {

    @Serializable
    object Splash
}