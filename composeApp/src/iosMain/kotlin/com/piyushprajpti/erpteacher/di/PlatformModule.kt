package com.piyushprajpti.erpteacher.di

import com.piyushprajpti.core.datastore.createDataStore
import com.piyushprajpti.core.datastore.dataStoreFileName
import com.piyushprajpti.erpteacher.db.RoomDBFactory
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual val platformModule: Module = module {
    single { RoomDBFactory().createDatabase() }
    single {
        createDataStore {
            val directory = documentDirectory()
            "$directory/$dataStoreFileName"
        }
    }
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}
