package com.piyushprajpti.erpteacher.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.piyushprajpti.core.datastore.createDataStore
import com.piyushprajpti.core.datastore.dataStoreFileName
import com.piyushprajpti.erpteacher.db.RoomDBFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single { RoomDBFactory(androidContext()).createDatabase() }
    single<DataStore<Preferences>> {
        createDataStore {
            androidContext().filesDir.resolve(dataStoreFileName).absolutePath
        }
    }
}
