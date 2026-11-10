package com.yeudaby.bengate.client.ui.screens.home

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yeudaby.bengate.client.core.dpm.DeviceManager
import com.yeudaby.bengate.client.repositories.CommandRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val deviceManager: DeviceManager,
    private val commandRepository: CommandRepository
): ViewModel() {

    enum class Status {
        LOADING,
        ERROR
    }

    private val _state = MutableStateFlow<Status?>(null)
    val state = _state.asStateFlow()


    fun handleCommands() {
        try {
            _state.value = Status.LOADING
            viewModelScope.launch {
                val commands = commandRepository.getCommands()
                commandRepository.handleCommands(commands)
                _state.value = null
            }
        } catch (_: Exception) {
            _state.value = Status.ERROR
        }
    }

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