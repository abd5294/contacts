package com.abdur.contacts.di

import android.content.Context
import androidx.room.Room
import com.abdur.contacts.data.ContactDatabase
import com.abdur.contacts.data.ContactRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRoomDatabase(
        @ApplicationContext context : Context
    ) : ContactDatabase{
        return Room.databaseBuilder(
            context,
            ContactDatabase::class.java,
            "contacts_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesContactRepository(db : ContactDatabase) : ContactRepository{
        return ContactRepository(db.dao)
    }
}