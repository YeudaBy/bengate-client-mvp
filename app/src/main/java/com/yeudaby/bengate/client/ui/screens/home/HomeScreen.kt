package com.yeudaby.bengate.client.ui.screens.home

import android.content.pm.PackageInfo
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.yeudaby.bengate.client.core.dpm.DeviceManager
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun AppsManagement(viewModel: HomeScreenViewModel, deviceManager: DeviceManager) {
    var apps by remember {
        mutableStateOf(listOf<PackageInfo>())
    }
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        apps = viewModel.getPackages(context)
    }
    LazyColumn(modifier = Modifier.padding(16.dp)) {

        items(apps) { packageInfo ->

            Column() {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    packageInfo.applicationInfo?.let {
                        val icon: Drawable = context.packageManager.getApplicationIcon(it)
                        Image(
                            bitmap = icon.toBitmap().asImageBitmap(),
                            contentDescription = "App icon",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                    Column() {
                        Text(packageInfo.packageName)
                        Row() {
                            packageInfo.versionName?.let { Text(it) }

                            IconButton(onClick = {
                                deviceManager.hideApp(packageInfo.packageName)
                            }) {
                                Icon(Icons.Default.Clear, contentDescription = "hide")
                            }
                            IconButton(onClick = {
                                deviceManager.hideApp(packageInfo.packageName)
                            }) {
                                Icon(Icons.Default.Add, contentDescription = "hide")
                            }
                        }
                    }
                }

                packageInfo.signingInfo?.publicKeys?.let {
                    Text("Public Keys: ${it.size}")
                }
            }

        }
    }
}

@Composable
fun DnsManagement(deviceManager: DeviceManager) {
    Column(modifier = Modifier.padding(16.dp)) {

        var dnsHost by remember { mutableStateOf("") }
        val scope = rememberCoroutineScope()


        Button(onClick = {
            dnsHost = deviceManager.getPrivateDNS() ?: "Empty"
        }) {
            Text("Get DNS")
        }
        Text("DNS: ${dnsHost}")

        Button(onClick = {
            scope.launch {
                deviceManager.setPrivateDNS("dns.adguard.com")
            }
        }) {
            Text("Set DNS to google's")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val colors =
        listOf(
            Color(0xFFffd7d7.toInt()),
            Color(0xFFffe9d6.toInt()),
            Color(0xFFfffbd0.toInt()),
            Color(0xFFe3ffd9.toInt()),
            Color(0xFFd0fff8.toInt()),
        )

    Scaffold(
        content = { innerPadding ->
            ;
            Column(
                modifier = Modifier.consumeWindowInsets(innerPadding),
            ) {

                Spacer(Modifier.height(50.dp))

                Button(onClick = viewModel.getDeviceManager()::activate) {
                    Text("Activate")
                }

                Button(onClick = viewModel.getDeviceManager()::deactivate) {
                    Text("Deactivate")
                }

                Text("Is device owned: ${viewModel.getDeviceManager().isDeviceManaged()}")

                AppsManagement(viewModel, viewModel.getDeviceManager())
                DnsManagement(viewModel.getDeviceManager())
            }
        },
    )
}