# Books
Simple Android app built using the [New York Times Books API](https://developer.nytimes.com/docs/books-product/1/overview).
User can sign in with Google, browse book categories and see books with details in each category. 
Data is fetched using Retrofit and book lists are saved locally using Room database.
The app follows MVVM and Clean Architecture.

<img src="https://github.com/user-attachments/assets/ced75288-fb7a-4dc9-8f65-cca995c77026" width="250">
<img src="https://github.com/user-attachments/assets/0b5d948f-3ebe-4c91-8e16-769b1e7bd63e" width="250">
<img src="https://github.com/user-attachments/assets/2607feca-645d-46b5-b511-1f01c3339595" width="250">


## Technologies
- [Jetpack Compose](https://developer.android.com/jetpack/compose): UI toolkit
- [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html): Lightweight concurrency for async work
- [Kotlin Flow](https://kotlinlang.org/docs/flow.html): Asynchronous data stream library
- [Retrofit](https://square.github.io/retrofit/): HTTP client for network requests
- [Gson](https://github.com/google/gson): JSON parser for mapping network data
- [Room](https://developer.android.com/training/data-storage/room): Local database for offline caching
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android): Dependency injection library
- [Coil](https://coil-kt.github.io/coil/compose/): Image loading library
- [Firebase Authentication](https://firebase.google.com/products/auth): Sign-in with Google
- [WebView](https://developer.android.com/reference/android/webkit/WebView): Displays purchase links inside the app

## Setup
- Clone the repo:
```
git clone https://github.com/your-username/books.git
```


- In your `local.properties` file, add:
```
BOOKS_API_KEY = your_api_key
WEB_CLIENT_ID = your_web_client_id
```

`BOOKS_API_KEY` - get it from [New York Times Books API](https://developer.nytimes.com/docs/books-product/1/overview)

`WEB_CLIENT_ID` - get it from your Firebase project (OAuth 2.0 Web client ID)
