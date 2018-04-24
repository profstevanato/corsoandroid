 <!-- Main Activity-->
    <activity android:name="YourActivityName" >
        <intent-filter>
      <!-- MAIN represents that it is the Main Activity-->
            <action android:name="android.intent.action.MAIN" />
      <!-- Launcher Denotes that it will be the first launching activity-->
            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
    </activity>
 <!--Add Other activities like this-->
    <activity android:name="YourActivityName2" >
 <!--Default Intent Filter-->
        <intent-filter>
            <action android:name="android.intent.action.DEFAULT" />
        </intent-filter>
    </activity>
 <!--OR Other activities like this And  intent filter is not necessary in other activites-->
    <activity android:name="YourActivityName3" >
    </activity>
 <!--OR Add Other activities like this-->
    <activity android:name="YourActivityName4" />