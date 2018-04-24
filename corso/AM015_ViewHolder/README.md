# AM015_ViewHolder

Questa esercitazione costituisce un importante punto di arrivo: in essa prsentiamo il pattern del `ViewHolder` che permette a `LayoutInflater`
```
LayoutInflater inflater = (LayoutInflater)context.getSystemService
      (Context.LAYOUT_INFLATER_SERVICE);
```
di lavorare in modo efficinete.

## La classe ProductsListFragment

Proseguendo la precedente esercitazione in questa classe usiamo per la prima volta, nelle nostre esercitazioni, il metodo `onActivityCreated()' che viene chiamato subito dopo `onCreate()` e che quindi ha lo scopo di **finalizzare** la creazione dell'interfaccia dell'Activity iniziata so on. 
```
@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // initialize products array

        products = new ArrayList<>();
        products.add
        ...

        ProductsArrayAdapter adapter = new ProductsArrayAdapter(getActivity(), products);
        //use this below for a correct initialization
        setListAdapter(adapter);

        // connect to
        getListView().setOnItemClickListener(this);

    }
```
Per il resto non cìè nulla di nuovo.

## La classe ProductsArrayAdapter

Estende la classe `ArrayAdapter<>`: [qui](https://developer.android.com/reference/android/widget/ArrayAdapter.html) per la documentazione. QUesto è il primo `Adapter` customizzato che creiamo. Il metodo che dovremo riscrivere è il seguente
```
ArrayAdapter (Context context, 
                int resource, 
                List<T> objects)
```
L'altro metodo che riscriveremo è
```
View getView (int position, 
                View convertView, 
                ViewGroup parent)
```
Qui implementeremo il pattern del **ViEWHolder**: chiameremo `LayoutInflater solo nel caso serva costruire il widget necessari a riempire il layout che va a costituire la singola riga - row - della `ListView`.

### ViewHolder
`ViewHolder` è una classe interna da noi creata
```
static class ViewHolder {
        public ImageView image;
        public TextView name;
        public TextView description;
    }
```
Analizziamo nel dettaglio il metodo

```
@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder viewHolder;

        Product p = (Product)products.get(position);
```
i) recuperiamo un `Product` quello che a noi interessa (`position`), quindi diamo inizio al pattern.
```
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.row_layout, null);
            // configure view holder
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) rowView.findViewById(R.id.name);
            viewHolder.description = (TextView) rowView.findViewById(R.id.description);
            viewHolder.image = (ImageView) rowView
                    .findViewById(R.id.icon);
            // take memory of the view
            rowView.setTag(viewHolder);
        } 
```
Vengono fatte due cose: il `Layout` corrispondente all item viene riempito chiamando `LayoutInflater` solo nel caso in cui non si abbia in cache una viù già pronta (questo codice illustra concretamente il lavoro di `LayoutInflater`).
```
else {
            // reuse the object
            viewHolder = (ViewHolder) rowView.getTag();
        }
        viewHolder.image.setImageResource(R.mipmap.ic_launcher);
        viewHolder.name.setText(p.getName());
        viewHolder.description.setText(p.getDescription());
        return rowView;
    }
```
Nell'API di View, [qui](https://developer.android.com/reference/android/view/View.html), vediamo cosa viene scritto dei TAG

### Tags

Tratto dalle API di View.

Unlike IDs, tags are not used to identify views. Tags are essentially an extra piece of information that can be associated with a view. They are most often used as a convenience to store data related to views in the views themselves rather than by putting them in a separate structure.

Tags may be specified with character sequence values in layout XML as either a single tag using the android:tag attribute or multiple tags using the <tag> child element:
```
     <View ...
           android:tag="@string/mytag_value" />
     <View ...>
         <tag android:id="@+id/mytag"
              android:value="@string/mytag_value" />
     </View>
```
Tags may also be specified with arbitrary objects from code using `setTag(Object)` or `setTag(int, Object)`.

Nel nostro caso, scrivendo codice,
```
rowView.setTag(viewHolder); 
```
Cioè resta anonimo , non è necessario nominarlo essendo solo, il tag cui resterà associato un value corrispondente all'oggetto!

## Product

Tale classe costituisce quello che chiameremo il **model** (vedasi patern MVC).








