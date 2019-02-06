package es.javy.pruebas_sqlite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;

import es.javy.pruebas_sqlite.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javy on 3/8/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "juegos.sqlite";
    public static final String DBLOCATION = "/data/data/es.javy.pruebas_sqlite/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    public List<Product> getListProduct(String sentencia) {
        Product product = null;
        List<Product> productList = new ArrayList<>();
        openDatabase();
        //sentencia="EXPANSIONES.MINIMO >= 4";
        Cursor cursor = mDatabase.rawQuery("SELECT DISTINCT EXPANSIONES.ID, JUEGOS.NOMBRE, EXPANSIONES.NOMBRE, EXPANSIONES.TIEMPO, EXPANSIONES.MINIMO, EXPANSIONES.MAXIMO, EXPANSIONES.OPTIMO, EXPANSIONES.REGLAS, EXPANSIONES.FAQ, EXPANSIONES.IMAGEN, JUEGOS.DESCRIPCION FROM JUEGOS INNER JOIN EXPANSIONES ON EXPANSIONES.JUEGO = JUEGOS.ID INNER JOIN TIPOJUEGO ON JUEGOS.ID = TIPOJUEGO.JUEGO INNER JOIN TIPOS ON TIPOS.ID = TIPOJUEGO.ESTILO WHERE " + sentencia + " ORDER BY JUEGOS.NOMBRE ASC, EXPANSIONES.NOMBRE DESC", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            product = new Product(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10));
            productList.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return productList;
    }

    public Product getProductById(int id) {
        Product product = null;
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT DISTINCT EXPANSIONES.ID, JUEGOS.NOMBRE, EXPANSIONES.NOMBRE, EXPAN\n" +
                "SIONES.TIEMPO, EXPANSIONES.MINIMO, EXPANSIONES.MAXIMO, EXPANSIONES.OPTIMO, EXPANSIONES.REGLAS, EXPANSIONES.FAQ, EXPANSIONES.IMAGEN, JUEGOS.DESCRIPCION FROM JUEGOS INNER JOIN EXPANSIONES ON EXPANSIONES.JUEGO = JUEGOS.ID INNER JOIN TIPOJUEGO ON JUEGOS.ID = TIPOJUEGO.JUEGO INNER JOIN TIPOS ON TIPOS.ID = TIPOJUEGO.ESTILO WHERE ID = ?", new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        product = new Product(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10));
        //Only 1 resul
        cursor.close();
        closeDatabase();
        return product;
    }
    public long updateProduct(Product product) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", product.getJuegoNombre());
        contentValues.put("PRICE", product.getExpansionTiempo());
        contentValues.put("DESCRIPTION", product.getJuegoDescripcion());
        String[] whereArgs = {Integer.toString(product.getId())};
        openDatabase();
        long returnValue = mDatabase.update("PRODUCT",contentValues, "ID=?", whereArgs);
        closeDatabase();
        return returnValue;
    }

    public long addProduct(Product product) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", product.getId());
        contentValues.put("NAME", product.getJuegoNombre());
        contentValues.put("PRICE", product.getExpansionTiempo());
        contentValues.put("DESCRIPTION", product.getJuegoDescripcion());
        openDatabase();
        long returnValue = mDatabase.insert("PRODUCT", null, contentValues);
        closeDatabase();
        return returnValue;
    }
    public boolean deleteProductById(int id) {
        openDatabase();
        int result = mDatabase.delete("PRODUCT",  "ID =?", new String[]{String.valueOf(id)});
        closeDatabase();
        return result !=0;
    }
}