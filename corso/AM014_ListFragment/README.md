# AM014_ListFragment

Anche in alternativa al precedente. Esiste anche `ListActivity` oggetto di una precedente esercitazione, che comunque abbiamo abbandonato preferendo presentare in prima istanza l'oggetti `ListView`.
```
...
 // create the adapter
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.products, android.R.layout.simple_list_item_1);
        // connect the adapter
        setListAdapter(adapter);
        // connect the listener
        getListView().setOnItemClickListener(this);
```
QUi sopra la parte chiave del codice; si noti il metodo `setListAdapter()` messaggio all'oggetto `this` ma soprattutto `getListView()`. Il `Fragment` implementa ...
```
... implements AdapterView.OnItemClickListener 
```
in mood da poter scrivere il listener
```
  @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }
```