# Bengate Client MVP

## Plan
For the MVP version I'll want to follow the following plan, and then continue to the abstract original plan
- [ ] Allow the following options to be set and remove with local management:
  - [-] Set DNS without the user ability to change it back
  - [-] Permit the user from remove the client from its device
  - [-] Hide and show apps by feature
  - [ ] Install new apps
  - [ ] Manage other DPM options
- [ ] Communicate with a remove CNC server
  - [ ] Registration
  - [ ] Manage connection and commands
  - [ ] Keep connection alive
- [ ] Complete installation flow without the user able to interrupt
  - [ ] QR Code Provisioning // TODO: read the docs
  - [ ] Full registration and manager choosing process
  - [ ] Receive client updates from the remote server
  - [ ] *?* Custom DNS server
    - Can I recognize the user and choose the right dns filtering process for him?
    - Can I be enough quick so the user UX won't interrupt?
- [ ] Create the admin dashboard
  - [ ] Manage one device
  - [ ] Manage *"plan"* - multi device configuration
  - [ ] Receive requests from the user
    - Especially fixed requests - manage apps, etc.
- [ ] Create the plugins ecosystem
  - Developer environment
  - Permissions manager
  - Integrity 
  - UX/UI scheme
  - Manage updates

## API Scheme
> I Think i'll use protobuf for this

## Commands
> Every time you install a new update to the app, you should remove the active admin from the app with the following command, and update the installation you need to give it back.
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

> P.S: I really should build a gradle commands for that 

## TODO
1. [ ] In network manager, add fallback domains logic: if the domain answer with the correct behavior - we assume that the main domain is not available.
2. [ ] Allow updates for the client from the client, but ensure the signature from the gh action
3. [ ] Make a room for plugins: When a registered plugin asks for certain delegation, and it should be allowed (by the logic in the server side) delegate it only if the plugin is signed with familiar signature. 