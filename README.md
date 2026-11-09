## Commands
- Activate the app:
```
adb shell dpm set-device-owner com.yeudaby.bengate.client.mvp/.core.receivers.BengateDeviceAdminReceiver 
```
- Deactivate:
```
adb shell dpm remove-active-admin com.yeudaby.bengate.client.mvp/.core.receivers.BengateDeviceAdminReceiver
```
- Dump:
```
adb shell dumpsys device_policy  
```