# AM010_Fragment

Qui per la prima lavoriamo dinamicamente modificando il layout dell'Activity utilizzando i Fragment. Il **Fragment** viene posizionato in un apposito spazio definito nel layout dell'activity.
```
<FrameLayout
        ...
        android:id="@+id/fragment_container"/>

```
Ad ogni fragment associamo un layout come se fosse un Activity.
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#00ffc4">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textAppearance="?android:attr/textAppearanceSmall"
        android:text="Uellaa"
        android:id="@+id/textView3"
        android:layout_gravity="center_horizontal"
        android:layout_weight="1" />
        ...
</LinearLayout>
```
Definiamo le classi 
```
public class FragmentOne extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout for this fragment

        return inflater.inflate(
                R.layout.fragment_one, container, false);
    }
}
```
A differenza di un Activity entrano in gioco due nuovi oggetti
- LayoutInflater
- ViewGroup container
per il secondo il significato è chiaro, per il primo ... andare alla documentazione: [qui](https://developer.android.com/reference/android/view/LayoutInflater.html).


In questa esercitazione posizioneremo in modo programmatico i fragment. In fase di inizializzazione procediamo come segue
```
FragmentOne fr = new FragmentOne();
FragmentManager fm = getFragmentManager();
FragmentTransaction fragmentTransaction = fm.beginTransaction();
fragmentTransaction.add(R.id.fragment_container, fr);
fragmentTransaction.commit();
``` 
Anche qui due oggetti
- FragmentManager: [api](https://developer.android.com/reference/android/app/FragmentManager.html).
- FragmentTransaction: [api](https://developer.android.com/reference/android/app/FragmentTransaction.html).

Nella documentazione il capitolo sui Fragment è [qui](https://developer.android.com/guide/components/fragments.html).