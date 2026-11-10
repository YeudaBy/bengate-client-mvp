package com.yeudaby.bengate.client.core.dpm

interface DeviceManager {
    fun blockApps(names: List<String>)
    fun unblockApps(names: List<String>)
    fun hideApp(name: String)
    fun unhideApp(name: String)

    fun installApp() // TODO

    suspend fun setPrivateDNS(address: String)
    fun getPrivateDNS(): String?
    fun removePrivateDNS()
    fun blockAppFromUninstall(packageName: String)

    fun isDeviceManaged(): Boolean
    fun activate()
    fun deactivate()
}