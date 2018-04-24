## AM005_Map

Qui ritorniamo sugli Intent impliciti
```
public void onClick(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("geo:45.495403,12.257509"));
        startActivity(i);
    }
```
Sono evidenti **action** e **data** rimandiamo alla documentazione: [qui](https://developer.android.com/reference/android/content/Intent.html). Notiamo che i ati vanno impostati come URI (Uniform Resource Identifier).


