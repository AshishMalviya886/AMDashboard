# AM-Portfolio

A lightweight Android app in **Kotlin** demonstrating a clean tabbed layout with JSON consumption.

- Splash screen with a centered logo
- Main screen with **2 tabs** (ViewPager2 + TabLayout)
  - **Tab 1 — Links List:** rows with a **logo** and **optional** platform links (Android/iOS)
  - **Tab 2 — Posts Feed:** pulls JSON and shows **title**, **body**, **tags** (chips), and metrics (**likes, dislikes, views**)
---

## ✨ Features

- Kotlin + Coroutines
- ViewBinding (no `findViewById`)
- ViewPager2 + TabLayout
- RecyclerView with adapters
- Material Components (CardView, ChipGroup)
- Retrofit + Kotlinx Serialization + OkHttp
- Emoji-based metrics (👍 👎 👁) — no icon drawables required

---

## 🧱 Tech Stack

- **Language:** Kotlin (JVM 17)
- **UI:** AndroidX, Material Components, RecyclerView, ViewPager2, TabLayout
- **Networking:** Retrofit, OkHttp, Kotlinx Serialization
- **Async:** Kotlin Coroutines
- **Min SDK:** 24
- **Compile/Target SDK:** 34

---

## 📂 Structure

```
app/
 ├─ src/main/java/com/app/am_porfolio/
 │   ├─ data/
 │   │   ├─ ApiClient.kt
 │   │   ├─ ApiService.kt
 │   │   ├─ Post.kt                # PostsResponse, Post, Reactions
 │   │   └─ LinkItem.kt            # For Tab 1 rows
 │   └─ ui/
 │       ├─ SplashActivity.kt
 │       ├─ MainActivity.kt
 │       ├─ MainPagerAdapter.kt
 │       └─ tabs/
 │           ├─ Tab1Fragment.kt    # Uses LinkItemAdapter
 │           ├─ Tab2Fragment.kt    # Uses PostAdapter
 │           ├─ LinkItemAdapter.kt
 │           └─ PostAdapter.kt
 │
 └─ src/main/res/
     ├─ layout/
     │   ├─ activity_main.xml
     │   ├─ activity_splash.xml
     │   ├─ fragment_tab1.xml
     │   ├─ fragment_tab2.xml
     │   ├─ link_item.xml
     │   └─ item_post.xml
     ├─ drawable/
     │   ├─ splash_background.xml
     │   └─ logo.png               # add your logo
     └─ values/
         └─ themes.xml
```

---

## ⚙️ Setup

1. Open in **Android Studio** (Koala/Iguana or newer) with **JDK 17**.
2. Place your logo at `app/src/main/res/drawable/logo.png`.
3. Ensure internet permission exists in `AndroidManifest.xml`:
   ```xml
   <uses-permission android:name="android.permission.INTERNET" />
   ```
4. Verify Gradle (Kotlin DSL) dependencies include:
   ```kotlin
   implementation("com.google.android.material:material:1.12.0")
   implementation("androidx.viewpager2:viewpager2:1.1.0")
   implementation("androidx.recyclerview:recyclerview:1.3.2")
   implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
   implementation("com.squareup.retrofit2:retrofit:2.11.0")
   implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
   implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
   implementation("com.squareup.okhttp3:okhttp:4.12.0")
   implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.14")
   ```

---

## 🔌 API Configuration (Tab 2)

- Base URL in `ApiClient.kt`:
  ```kotlin
  .baseUrl("https://dummyjson.com/")
  ```
- Endpoint in `ApiService.kt`:
  ```kotlin
  @GET("posts")
  suspend fun getPosts(): PostsResponse
  ```

### Expected JSON schema
```json
{
  "posts": [
    {
      "id": 1,
      "title": "…",
      "body": "…",
      "tags": ["history","american","crime"],
      "reactions": { "likes": 192, "dislikes": 25 },
      "views": 305,
      "userId": 121
    }
  ],
  "total": 251,
  "skip": 0,
  "limit": 30
}
```

---

## 🧭 Tab Details

### Tab 1 — Links List
- Row layout: `link_item.xml`
- Data class: `LinkItem.kt`
- Adapter: `LinkItemAdapter.kt`
- **Optional** links auto-hide:
  - Android: `https://play.google.com/store/apps/details?id=<PACKAGE>`  
    or `market://details?id=<PACKAGE>` (or shorthand `"package:<id>"` in adapter)
  - iOS: `https://apps.apple.com/app/id<APP_ID>`

Configure sample items in `Tab1Fragment.kt` via:
```kotlin
adapter.submit(
    listOf(
        LinkItem(R.drawable.logo,
                 androidUrl = "https://play.google.com/store/apps/details?id=com.example.myapp",
                 iosUrl = "https://apps.apple.com/app/id1234567890")
    )
)
```

### Tab 2 — Posts Feed
- Card layout: `item_post.xml` (MaterialCard + ChipGroup + emoji metrics)
- Adapter: `PostAdapter.kt` binds title, body, tags, and metrics (**likes/dislikes/views**).

---

## 🏃‍♀️ Run

1. Select a device/emulator.
2. **Run** the app from Android Studio.
3. Flow:
   - Splash ➜ Main with 2 tabs
   - **Tab 1** shows link rows (optional links hidden)
   - **Tab 2** fetches and renders posts list

---

## 🛠 Tips & Customization

- Change tab titles in `MainActivity.kt` (`tabTitles`).
- Replace emoji metrics with vector icons later via Vector Asset Studio if desired.
- Add paging (`skip/limit`), pull-to-refresh, error/empty states, DiffUtil as needed.
- Theme with Material (light/dark) for brand consistency.

---

## 📜 License

Use and modify freely within your projects. Add a LICENSE file if you plan to redistribute.

---

## 🙌 Credits

- Data endpoint: https://dummyjson.com/
- Android Material Components
- Kotlinx Serialization, Retrofit, OkHttp
