package com.makentoshe.booruchan.library.database.di

import android.content.Context
import androidx.room.Room
import com.makentoshe.booruchan.library.database.ApplicationDatabase
import com.makentoshe.booruchan.library.database.SearchSnapshotDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule {
    companion object {

        private const val ApplicationDatabaseName = "ApplicationDatabase"
        private const val NavigationDatabaseName = "NavigationDatabase"

        @Singleton
        @Provides
        fun provideApplicationDatabase(
            @ApplicationContext applicationContext: Context,
        ) = Room.databaseBuilder(
            /* context = */ applicationContext,
            /* klass = */ ApplicationDatabase::class.java,
            /* name = */ ApplicationDatabaseName,
        ).fallbackToDestructiveMigration().build()

        @Singleton
        @Provides
        fun provideNavigationDatabase(
            @ApplicationContext applicationContext: Context,
        ) = Room.databaseBuilder(
            /* context = */ applicationContext,
            /* klass = */ SearchSnapshotDatabase::class.java,
            /* name = */ NavigationDatabaseName,
        ).fallbackToDestructiveMigration().build()
    }
}
