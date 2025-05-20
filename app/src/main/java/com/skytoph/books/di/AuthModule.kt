package com.skytoph.books.di

import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.skytoph.books.core.util.NetworkExceptionCheck
import com.skytoph.books.domain.auth.SignIn
import com.skytoph.books.ui.auth.client.SignInWithGoogleClient
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
    fun client(@ApplicationContext context: Context, mapper: NetworkExceptionCheck): SignInWithGoogleClient =
        SignInWithGoogleClient(context = context, auth = Firebase.auth, networkMapper = mapper)

    @Provides
    fun signIn(client: SignInWithGoogleClient): SignIn = client
}