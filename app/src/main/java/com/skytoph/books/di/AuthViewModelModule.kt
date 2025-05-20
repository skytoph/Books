package com.skytoph.books.di

import com.skytoph.books.domain.auth.IsSignedIn
import com.skytoph.books.domain.auth.SignInClient
import com.skytoph.books.domain.usecase.CheckAuthStateUseCase
import com.skytoph.books.domain.usecase.CheckAuthStateUseCaseImpl
import com.skytoph.books.domain.usecase.SignInUseCase
import com.skytoph.books.domain.usecase.SignInUseCaseImpl
import com.skytoph.books.ui.feature_sign_in.viewmodel.AuthViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Qualifier

@Qualifier
@Retention(value = AnnotationRetention.RUNTIME)
annotation class IsSignedInClient

@Qualifier
@Retention(value = AnnotationRetention.RUNTIME)
annotation class IsSignedInStateProvider

@Module
@InstallIn(ViewModelComponent::class)
object AuthViewModelModule {

    @Provides
    @ViewModelScoped
    fun signIn(
        @DispatcherIo dispatcher: CoroutineDispatcher,
        @IsSignedInClient client: SignInClient,
    ): SignInUseCase =
        SignInUseCaseImpl(dispatcher = dispatcher, client = client)

    @Provides
    @ViewModelScoped
    fun isSignedIn(@IsSignedInClient client: IsSignedIn): CheckAuthStateUseCase =
        CheckAuthStateUseCaseImpl(client = client)

    @Provides
    @IsSignedInStateProvider
    fun stateProvider(viewModel: AuthViewModel): IsSignedIn = viewModel
}