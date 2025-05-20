package com.skytoph.books.ui.auth.client

import android.content.Context
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.NoCredentialException
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.skytoph.books.core.key.KeyProvider
import com.skytoph.books.core.util.NetworkExceptionCheck
import com.skytoph.books.domain.auth.SignInClient
import com.skytoph.books.domain.usecase.SignInResult
import kotlinx.coroutines.tasks.await

class SignInWithGoogleClient(
    private val context: Context,
    private val auth: FirebaseAuth,
    private val networkMapper: NetworkExceptionCheck,
    private val key: KeyProvider
) : SignInClient {

    private val googleIdOption: GetSignInWithGoogleOption by lazy {
        GetSignInWithGoogleOption.Builder(key.authWebClientId).build()
    }

    override fun isSignedIn(): Boolean = auth.currentUser != null

    override suspend fun signIn(): SignInResult = try {
        signInWithGoogle()?.let { credentials ->
            signInWithFirebase(idTokenCredential = credentials.idToken)
            if (isSignedIn()) SignInResult.Success
            else null
        } ?: SignInResult.ErrorGeneral
    } catch (_: NoCredentialException) {
        SignInResult.NoCredentialsAvailable
    } catch (_: GetCredentialCancellationException) {
        SignInResult.Canceled
    } catch (exception: Exception) {
        if (networkMapper.isNetworkUnavailable(exception)) SignInResult.NoConnection
        else SignInResult.ErrorGeneral
    }

    suspend fun signInWithGoogle(): GoogleIdTokenCredential? {
        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
        val credentialManager = CredentialManager.Companion.create(context)
        val result = credentialManager.getCredential(request = request, context = context)
        return getIdTokenCredential(credential = result.credential)
    }

    private fun getIdTokenCredential(credential: Credential): GoogleIdTokenCredential? =
        if (credential is CustomCredential
            && credential.type == GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
        ) GoogleIdTokenCredential.Companion.createFrom(credential.data)
        else null

    suspend fun signInWithFirebase(idTokenCredential: String) {
        val authCredentials = GoogleAuthProvider.getCredential(idTokenCredential, null)
        auth.signInWithCredential(authCredentials).await()
    }
}