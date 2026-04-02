package com.piyushprajpti.erpteacher.db

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.piyushprajpti.data.local.db.LocalDatabase
import com.piyushprajpti.data.local.db.dbFileName
import kotlinx.coroutines.Dispatchers

actual class RoomDBFactory(private val context: Context) {
    actual fun createDatabase(): LocalDatabase {
        val dbFile = context.getDatabasePath(dbFileName)
        return Room.databaseBuilder<LocalDatabase>(context, dbFile.absolutePath)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .fallbackToDestructiveMigration(true)
            .build()
    }
}
