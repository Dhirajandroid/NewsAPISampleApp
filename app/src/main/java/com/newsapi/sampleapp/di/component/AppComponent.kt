package com.newsapi.sampleapp.di.component

import com.newsapi.sampleapp.NewsAPISampleApplication
import com.newsapi.sampleapp.di.modules.AppModule
import com.newsapi.sampleapp.di.modules.BuildersModule
import com.newsapi.sampleapp.di.modules.NetModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(AndroidInjectionModule::class, BuildersModule::class, AppModule::class,
        NetModule::class)
)
interface AppComponent {
  fun inject(app: NewsAPISampleApplication)
}