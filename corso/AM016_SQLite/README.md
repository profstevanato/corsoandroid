# AM016_SQLite

Il seguente esercizio, ampiamente commentato nel codice, presenta le seguenti classi.

## MySQLHelper

E' il motore interno con cui Android gestisce il db SQLite, si esamini con dettaglio le API: [qui](https://developer.android.com/reference/android/database/sqlite/SQLiteOpenHelper.html).	Qui abbiamo fatto l'override dei metodi
```
onCreate()
onUpgrade()
```
oltre a riscrivere il costruttore.

## Product

E' il **model** detto alla **MVC** (pattern); con metodi get e set e costruttore.

## ProductArrayAdapter

E' il nostro custom `Adapter` creato alla solita maniera usando il pattern del **ViewHoledr**.

## ProductListFragment

E' il `Fragment` per la lista.

## ProductsDataSource

E' un'altra classe di aiuto (poteva essere creata con il pattern **singleton**). Ecco i campi
```
private SQLiteDatabase database;
private MySQLiteHelper dbHelper;
private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_NAME,  MySQLiteHelper.COLUMN_DESCRIPTION};
```
il primo ed terzo di questi hanno un valore puramente pratico. Con i metodi
```
public void open()
public void close()
```
apriamo in modalità di **lettura e scrittura** il db e lo chiudiamo utilizzando l'helper. Abbiamo quindi i metodi **CRUD** (hanno un valore puramente esemplificativo)
```
void createProduct(Product p)
void deleteProduct(Product p)
void deleteProduct(String name)
public List<Product> getAllProducts()
public void deleteAll()
```
per implementare i quali abbiamo fatto riferimento alle API di SQLiteDatabase: [qui](https://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html). Il codice è ampiamente commentato e si invita, come al solito, a leggere con attenzione le API.
Il metodo
```
public void initialize()
```
serve a popolare il db con un elenco di prodotti.

## InputDialog extends DialogFragment

Qui proponiamo la soluzione suggerita da moli sviluppatori, al posto di un `Dialog` costruito su `OperateActivity` (vedi `OLD solution`) definiamo il nostro `DialogFragment` che comunicherà con l'altro `Fragment`, un `ListFragment` attraverso il pattern ben noto (vedi precedente esercitazione) di **comunicazione fra Fragment**. 
```
AddListener mCallback;

// Container Activity must implement this interface
public interface AddListener {
    void addProduct(Product product);
}
```

## OperateActivity extends AppCompatActivity implements InputDialog.AddListener 

Questa è l'activity di esercizio. Se si vuole lavorare alla vecchia si decommenti
`/* OLD solution*/ e non si richiami il `Fragment`
```
FragmentManager fm = getFragmentManager();
                InputDialog dialogFragment = new InputDialog();
                dialogFragment.show(fm, "MyInputDialog");
```

## MainActivity

E' l'Activity di partenza, con una serie di bottoni possiamo popolare o cancellare il db.







