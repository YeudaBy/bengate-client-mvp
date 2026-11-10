package com.yeudaby.bengate.client.data.network

import com.yeudaby.bengate.client.data.network.model.Command
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("/commands")
    suspend fun getCommands(): List<Command>
}