# Weather
Fetch weather daily forecast based on search term from input and display in the UI
<br/>

<h2>Project architecture</h2> This project is built on Kotlin language with architecture as below detail:
<br/>
 - Single Activity + Navigation component
<br/>
 - MVVM + Clean Architecture
<br/>
 - Use Hilt for Dependency Injection
<br/>
 - Use best practices suggested by google: Kotlin Flow, ListAdapter, ...
<h2>Source code folder structure</h2>
Include 6 library modules and 1 app module:
<br/>
 - config: contains configs, constants, ...
<br/>
 - utility: contains utilities
<br/>
 - extension: contains extension (ViewExt, NavControllerExt, ...)
<br/>
 - network: contains some common network classes (interceptors, authenticators, ...), network dependencies (Dispatcher, Cache, OkHttpClient, Retrofit, ...)
<br/>
 - utility: contains utilities (CryptoUtil, DateUtil, ...)
 <br/>
 - common: base classes (BaseActivity, BaseFragment, BaseViewModel), widget, dialog, ...
 - forecast: feature module corresponding to forecast feature
 <br/>
 - app: contains MainActivity, application class
<h2>Used libraries</h2>
Retrofit/OkHttp, Hilt, Kotlin coroutines, sdp/ssp, 
<h2>Steps to launch app</h2>
<br/>
 - Download APK from app/release/apk-release.apk
<br/>
 - Install that APK
<h2>Done items</h2>All candidate items
