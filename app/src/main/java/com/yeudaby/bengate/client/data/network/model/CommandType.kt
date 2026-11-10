package com.yeudaby.bengate.client.data.network.model

import com.google.gson.annotations.SerializedName


enum class CommandType {
    @SerializedName("set_private_dns")
    SET_PRIVATE_DNS,
    @SerializedName("clear_private_dns")
    CLEAR_PRIVATE_DNS,
    @SerializedName("hide_app")
    HIDE_APP,
    @SerializedName("show_app")
    SHOW_APP,
    @SerializedName("install")
    INSTALL_APP,
    @SerializedName("block_app_from_uninstall")
    BLOCK_APP_FROM_UNINSTALL,

    //    GET_APPS("get_apps"),
//    GET_PRIVATE_DNS("get_private_dns"),
//    GET_DEVICE_OWNER_STATUS("get_device_owner_status"),
}