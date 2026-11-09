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

## TODO
1. [ ] In network manager, add fallback domains logic: if the domain answer with the correct behavior - we assume that the main domain is not available.
2. [ ] Allow updates for the client from the client, but ensure the signature from the gh action
3. [ ] Make a room for plugins: When a registered plugin asks for certain delegation, and it should be allowed (by the logic in the server side) delegate it only if the plugin is signed with familiar signature. 