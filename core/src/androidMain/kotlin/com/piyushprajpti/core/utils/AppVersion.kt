package com.piyushprajpti.core.utils

import android.content.Context
import android.content.pm.PackageManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AndroidAppVersion : KoinComponent {
    val context: Context by inject()

    fun getVersion(): String {
        return try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            packageInfo.versionName ?: "Unknown"
        } catch (e: PackageManager.NameNotFoundException) {
            "Unknown"
        }
    }
}

actual fun getAppVersion(): String = AndroidAppVersion().getVersion()
