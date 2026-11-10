package com.yeudaby.bengate.client.repositories

import android.util.Log
import com.yeudaby.bengate.client.core.dpm.DeviceManager
import com.yeudaby.bengate.client.data.network.ApiService
import com.yeudaby.bengate.client.data.network.model.Command
import com.yeudaby.bengate.client.data.network.model.CommandType
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class CommandRepository @Inject constructor(
    private val apiService: ApiService,
    private val deviceManager: DeviceManager,
    ) {

    suspend fun getCommands() = apiService.getCommands()

    suspend fun handleCommands(commands: List<Command>) {
        Log.w(CommandRepository::class.java.name, commands.toString())
        commands.forEach { command ->
            when (command.key) {

                CommandType.BLOCK_APP_FROM_UNINSTALL ->
                    deviceManager.blockAppFromUninstall(command.value)

                CommandType.SET_PRIVATE_DNS ->
                    deviceManager.setPrivateDNS(command.value)

                CommandType.CLEAR_PRIVATE_DNS ->
                    deviceManager.removePrivateDNS()

                CommandType.HIDE_APP ->
                    deviceManager.hideApp(command.value)

                CommandType.SHOW_APP ->
                    deviceManager.unhideApp(command.value)

                CommandType.INSTALL_APP -> TODO()
            }
        }
    }
}