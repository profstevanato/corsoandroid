# AM_009_Fragments

In questa esercitazione opereremo la comunicazione fra due `Fragment` di un'`Activity`: notiamo che la comunicazione diretta non può avvenire quindi useremo un **design pattern** che viene di norma utilizzato.
- Il `MainActivity` implementa un `Listener` (interfaccia che definiremo altrove) che potrà operare, in forza di un evento, sul secondo `Fragment1` (vedi figura). Questa idea non è nuova e la si può utilizare anche altrove.
- `Fragment1` recupererà tale `Listener` per poterlo utilizzare a fronte dell'evento (nel nostro esempio in realtà è un click e non sarebbe stato necessario definire ex novo l'interfaccia).
```
Fragment1 (Event)                 Fragment2 (Effect)
    \                             /
     \                           /
      \                         /
       \                       /
         MainActivity (Listener) 
```
- Sotto questa ipotesi di progetto possiamo recuperare il Listener all'interno di `Fragment1` solo quando e non appena `Fragment1` è in grado di accedere a `MainActivity`. Pertanto faremo riferimento al metodo `onAttach(Context context)` per la documentazione rimandiamo al seguente [link](https://developer.android.com/reference/android/app/Fragment.html#onAttach(android.app.Activity)).
```
@Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = context instanceof Activity ? (Activity) context : null;

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            listener = (OnItemSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
```
L'esempio datato si trova anche in Android Developers: [qui](https://developer.android.com/training/basics/fragments/communicating.html). 
- Nel nostro esercizio abbiamo riportato anche la soluzione obsoleta (sopprimendo il warning).
- All'interno di `Fragment1` abbiamo definito l'interfaccia
```
public interface OnItemSelectedListener {
        public void onItemSelected(String link);
    }
```
implementata da `MainActivity`
```
 @Override
    public void onItemSelected(String txt) {
        DetailFragment fragment = (DetailFragment) getFragmentManager()
                .findFragmentById(R.id.detailFragment);
        fragment.setText(txt);
    }
```
in modo da operare in `Fragment2` col metodo
```
public void setText(String txt) {
        // getView() : Get the root view for the fragment's layout (fragment must have a layout)
        TextView view = (TextView) getView().findViewById(R.id.detailsText);
        view.setText(txt);
    }
```
- I nomi nel nostro esercizio riprendono quelli dell'esempio in ANnroid Developers, i nomi dei `Fragment` (non sono `Fragment1` e `Fragment2` evidentemente) richiamano ad un possibile quasi immediato svoluppo!