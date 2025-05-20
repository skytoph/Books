package com.skytoph.books.di

import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.skytoph.books.core.key.KeyProvider
import com.skytoph.books.core.util.NetworkExceptionCheck
import com.skytoph.books.domain.auth.IsSignedIn
import com.skytoph.books.domain.auth.SignInClient
import com.skytoph.books.ui.feature_sign_in.client.SignInWithGoogleClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun client(
        @ApplicationContext context: Context,
        mapper: NetworkExceptionCheck,
        key: KeyProvider
    ): SignInWithGoogleClient =
        SignInWithGoogleClient(context = context, auth = Firebase.auth, networkMapper = mapper, key = key)

    @Provides
    @IsSignedInClient
    fun signIn(client: SignInWithGoogleClient): SignInClient = client

    @Provides
    @IsSignedInClient
    fun isSignedIn(client: SignInWithGoogleClient): IsSignedIn = client
}