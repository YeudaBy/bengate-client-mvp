package com.yeudaby.bengate.client.ui.screens.home

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import androidx.lifecycle.ViewModel
import com.yeudaby.bengate.client.core.dpm.DeviceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.collections.map

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val deviceManager: DeviceManager,
): ViewModel() {

    fun getDeviceManager(): DeviceManager {
        return deviceManager
    }


    private fun PackageManager.getInstalledPackagesCompat(flags: Long = 0L): List<PackageInfo> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getInstalledPackages(PackageManager.PackageInfoFlags.of(flags))
        } else {
            getInstalledPackages(flags.toInt())
        }
    }


    suspend fun getPackages(context: Context) = withContext(Dispatchers.IO) {
        context.packageManager
            .getInstalledPackagesCompat()
    }

}