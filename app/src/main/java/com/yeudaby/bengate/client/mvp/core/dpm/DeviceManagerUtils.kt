package com.yeudaby.bengate.client.mvp.core.dpm

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import com.yeudaby.bengate.client.mvp.core.receivers.BengateDeviceAdminReceiver

object DeviceManagerUtils {

    fun getComponentName(context: Context): ComponentName {
        return ComponentName(
            context,
            BengateDeviceAdminReceiver::class.java
        )
    }

    fun getDpm(context: Context) : DevicePolicyManager {
        return context.getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
    }
}