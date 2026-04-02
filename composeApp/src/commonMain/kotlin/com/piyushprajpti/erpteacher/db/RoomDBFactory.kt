package com.piyushprajpti.erpteacher.db

import com.piyushprajpti.data.local.db.LocalDatabase

expect class RoomDBFactory {
    fun createDatabase(): LocalDatabase
}