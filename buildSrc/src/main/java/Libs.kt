object Libs {

    //Libs android
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinStdLibVersion}"
    const val core = "androidx.core:core-ktx:${Versions.androidCoreVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val material = "com.google.android.material:material:${Versions.materialVersion}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintVersion}"
    const val jUnit = "junit:junit:${Versions.jUnitVersion}"
    const val jUnitExt = "androidx.test.ext:junit:${Versions.jUnitExtVersion}"

    //Test Lib
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"

    //Biblioteca para adicionar animacoes no projeto
    const val lottie = "com.airbnb.android:lottie:${Versions.lottieVersion}"

    //Biblioteca para adicionar ler QRCode
    const val zxing = "me.dm7.barcodescanner:zxing:${Versions.zxingVersion}"

    //Biblioteca para adicionar lista performatica ao aplicativo
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerVersion}"

    //Biblioteca para adicionar cards ao aplicativo
    const val cardView = "androidx.cardview:cardview:${Versions.cardViewVersion}"

    //Biblioteca para consumir webservice
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"

    //Biblioteca para realizar o parse json para objeto/objeto para json
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.gsonConverterVersion}"
    const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"

    //Biblioteca para auxiliar o carregamento de imagens no aplicativo
    const val picasso = "com.squareup.picasso:picasso:${Versions.picassoVersion}"

    //Biblioteca AAC
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensionsVersion}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewModelVersion}"

    //Injecao de dependencia KOIN
    const val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.koinViewModelVersion}"
    const val koin = "org.koin:koin-android:${Versions.koinVersion}"

    //Coroutines
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCoreVersion}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroidVersion}"
}