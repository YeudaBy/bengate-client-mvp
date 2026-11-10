package com.yeudaby.bengate.client.core.dpm

import android.content.Context
import android.os.Build
import android.os.UserManager
import androidx.annotation.RequiresApi
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeviceManagerImpl @Inject constructor(
    private var context: Context
): DeviceManager {

    private val dpm = DeviceManagerUtils.getDpm(context)
    private val componentName = DeviceManagerUtils.getComponentName(context)

    override fun blockApps(names: List<String>) {
        TODO("Not yet implemented")
    }

    override fun unblockApps(names: List<String>) {
        TODO("Not yet implemented")
    }

    override fun hideApp(name: String) {
        dpm.setApplicationHidden(componentName, name, true)
    }

    override fun unhideApp(name: String) {
        dpm.setApplicationHidden(componentName, name, false)
    }

    override fun installApp() {
        TODO("Not yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override suspend fun setPrivateDNS(address: String) {
        withContext(Dispatchers.IO) {
            dpm.setGlobalPrivateDnsModeSpecifiedHost(componentName, address)
            dpm.addUserRestriction(componentName, UserManager.DISALLOW_CONFIG_PRIVATE_DNS)
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun getPrivateDNS(): String? {
        return dpm.getGlobalPrivateDnsHost(componentName)
    }

    override fun removePrivateDNS() {
        TODO("Not yet implemented")
    }

    override fun isDeviceManaged(): Boolean {
        return dpm.isDeviceOwnerApp(context.packageName)
    }

    override fun activate() {
        dpm.setUninstallBlocked(componentName, context.packageName, true)
        dpm.setDeviceOwnerLockScreenInfo(componentName, "Device Managed By Bengate.org")
        dpm.addUserRestriction(componentName, UserManager.DISALLOW_ADD_USER)
        dpm.addUserRestriction(componentName, UserManager.DISALLOW_APPS_CONTROL)
        dpm.addUserRestriction(componentName, UserManager.DISALLOW_FUN)
        dpm.addUserRestriction(componentName, UserManager.DISALLOW_FACTORY_RESET)
        dpm.addUserRestriction(componentName, UserManager.DISALLOW_FACTORY_RESET)
        dpm.addUserRestriction(componentName, UserManager.DISALLOW_CONFIG_TETHERING) // to prevet hotspot
        dpm.addUserRestriction(componentName, UserManager.DISALLOW_CONFIG_VPN)
        dpm.addUserRestriction(componentName, UserManager.DISALLOW_SAFE_BOOT)
//        dpm.addUserRestriction(componentName, UserManager.DISALLOW_DEBUGGING_FEATURES)  TODO: enable it when can be reversed


    }

    override fun deactivate() {
        dpm.setUninstallBlocked(componentName, context.packageName, false)
        dpm.setDeviceOwnerLockScreenInfo(componentName, null)
    }
}