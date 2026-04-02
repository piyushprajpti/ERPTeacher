package com.piyushprajpti.erpteacher

import androidx.compose.ui.window.ComposeUIViewController
import com.piyushprajpti.erpteacher.db.RoomDBFactory

fun MainViewController() = ComposeUIViewController {
    val db = RoomDBFactory().createDatabase()
    App()
}