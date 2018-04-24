# Android Studio: speed up

Con macchine da 8GB è possibile aumentare la velocità di Android Studio 2. Ecco alcuni accorgimenti. Uno dei riferimenti principali per la configrazione di Android Studio lo si trova [qui](https://developer.android.com/studio/intro/studio-config.html).
i) Settings → Build ... → Compiler: check "compile idependent module ..." (leggere le avvertenze, vedi anche quanto scritto qui di seguito).

ii) Nella cartella di installazione si trovano il file di impostazioni `studio.vmoptions` impostato nel modo seguente
```
#
# *DO NOT* modify this file directly. If there is a value that you would like to override,
# please add it to your user specific configuration file.
#
# See http://tools.android.com/tech-docs/configuration
#
-server
-Xms256m
-Xmx750m
-XX:MaxPermSize=350m
-XX:ReservedCodeCacheSize=240m
-XX:+UseConcMarkSweepGC
-XX:SoftRefLRUPolicyMSPerMB=50
-da
-Djna.nosys=true
-Djna.boot.library.path=

-Djna.debug_load=true
-Djna.debug_load.jna=true
-Dsun.io.useCanonCaches=false
-Djava.net.preferIPv4Stack=true
-Dawt.useSystemAAFontSettings=lcd
```
questo file non andrebbe toccato ma vi è la possibilità di andare su Help→Edit Custom Properties, creare se non già esiste un file di configurazione personale ed apportare modifiche. Con macchine che hanno più di 4GB possiamo ad esempio
```
-Xms1024m
-Xmx2048m
```
-Xms: Specifies the initial size, in bytes, of the memory allocation pool.
-Xmx: Specifies the maximum size, in bytes, of the memory allocation pool.
avendo ritoccato le dimensioni dell'heap dovremmo senza tema sciegliere lìopzione i) ed anche
```
-XX:MaxPermSize=1024m
-XX:ReservedCodeCacheSize=256m
```
-XX:MaxPermSize: Size of the Permanent Generation.
-XX:ReservedCodeCacheSize: Reserved code cache size

iii) Settings → Build ... → Gradle: check offline se si lavora a scuola soprattutto. Col nuovo Android Studio siamo già oltre la versione 2.10! Quando si dovranno scaricare nuove librerie si dovrà tornare online per poi ritornare offline. Nel file `gradle.properties` si può decommentare l'opzione
```
org.gradle.parallel=true
```
ed usare gradle come demone
```
org.gradle.daemon=true
```
si possono mettere le due impostazioni nella cartella `.gradle` nel file `gradle.properties` (se non esiste, crearlo`
```
org.gradle.parallel=true
org.gradle.daemon=true 
```
iv) Ultimo ma non ultimo, installare Genymotion: [qui](https://www.genymotion.com/) (la versione gratuita può essere scaricata solo dopo essersi registrati).


