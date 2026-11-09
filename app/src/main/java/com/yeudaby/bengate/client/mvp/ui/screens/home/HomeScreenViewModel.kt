package com.yeudaby.bengate.client.mvp.ui.screens.home

import androidx.lifecycle.ViewModel
import com.yeudaby.bengate.client.mvp.core.dpm.DeviceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val deviceManager: DeviceManager
): ViewModel() {

    fun getDeviceManager(): DeviceManager {
        return deviceManager
    }

}