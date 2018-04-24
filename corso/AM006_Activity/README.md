## AM006_Activity

Questa è un'applicazione pensata per vedere come e quando operano i metodi che abbiamo visto nel ciclo di vita delle Activity.

Useremo spesso il test 
```
if (BuildConfig.DEBUG) {
            ...
        }
```
tale test risulta vero almeno che generiamo (vedi Build) non generiamo il `Signed APK`. QUi lo presentiamo per mostrare come gestire i LOG, utilissimi per un debug iniziale (da usare senza tema). A tal proposito abbimo definito un nostro TAG:
```
ublic interface MyLOG {
    String TAG = "MyLOG";
}
```


Su come gestire la factory class per le notifiche vai [qui](https://developer.android.com/training/notify-user/build-notification.html).
```
NotificationCompat.Builder mBuilder =
    new NotificationCompat.Builder(this)
    .setSmallIcon(R.drawable.notification_icon)
    .setContentTitle("My notification")
    .setContentText("Hello World!");
```
Grazie al `NotificationManager` possiamo gestire le notifiche.
```
// Sets an ID for the notification
int mNotificationId = 001;
// Gets an instance of the NotificationManager service
NotificationManager mNotifyMgr =
        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
// Builds the notification and issues it.
mNotifyMgr.notify(mNotificationId, mBuilder.build());
```
Invito a sperimentare con le notifiche.