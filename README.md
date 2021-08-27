#### PagingDemo

A playground project using [Android Paging 3.0](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)

The app fetch `Repos` from the [github api](https://api.github.com/)

Based in this [Android codelab.](https://developer.android.com/codelabs/android-paging#0)

Also there is a version with manual paging (`ghrepos`).

Use:
- Kotlin
- MVVM.
- UseCases
  - FetchDataUseCase
  - FetchNextDataUseCase
  - DeleteAllUseCase
  - DeleteAllCacheUseCase
- Repositories:
  - CacheRepository (memory)
  - LocalRepository (Room)
  - RemoteRepository (Retrofit)
- Coroutines
- DataBinding
- Navigation
- Hilt
- Leak Canary
- Timber
- Unit Test
- Espresso Test (Barista)
