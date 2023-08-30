package com.ivansan.newsapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.ivansan.newsapp.data.AppDatabase
import com.ivansan.newsapp.data.DB_NAME
import com.ivansan.newsapp.data.dao.ResultDao
import com.ivansan.newsapp.data.repository.NewsRepository
import com.ivansan.newsapp.service.NewsService
import com.ivansan.newsapp.ui.settings.PREFERENCES_SETTINGS
import com.ivansan.newsapp.utils.TokenInterceptor
import com.lembergsolutions.retrofitretry.implementation.RetrofitRetryCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    //Logging interceptor
    @Provides
    fun provideLogginInterceptor(): HttpLoggingInterceptor {
        //תופס את כל הבקשות ומדפיס ללוג
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY // BODY == FULL
        return logger
    }


    //Shared Preferences
    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences =
        application.getSharedPreferences(
            PREFERENCES_SETTINGS,Context.MODE_PRIVATE
        )


    //Token interceptor
    @Provides
    fun provideTokenInterceptor(
        sharedPreferences: SharedPreferences
    ): TokenInterceptor = TokenInterceptor(sharedPreferences)


    //Okhttp client
    @Provides
    fun provideHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        tokenInterceptor: TokenInterceptor): OkHttpClient {

        // תופס את כל הבקשות ומוסיף Api Key
        return OkHttpClient.Builder()
            .connectTimeout(2,TimeUnit.MINUTES) // connect timeout
            .readTimeout(2,TimeUnit.MINUTES) // read timeout
            .addInterceptor(tokenInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

    }


    //Retrofit
    @Provides
    fun provideNewsService(
        client: OkHttpClient
    ): NewsService {

        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://newsdata.io/")
            .addCallAdapterFactory(RetrofitRetryCallAdapterFactory.createCoroutineAdapter())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(NewsService::class.java)
    }

    //Database
    @Provides
    fun provideAppDatabase( @ApplicationContext
                            context: Context
    ) =
        Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()


    //Dao
    @Provides
    fun provideNewsDao(db:AppDatabase) = db.resultDao()


    //Repository
    @Provides
    fun provideRepository(resultDao: ResultDao,
                          newsService:NewsService,
                          sharedPreferences: SharedPreferences
                          )
            = NewsRepository(resultDao,newsService,sharedPreferences)

}