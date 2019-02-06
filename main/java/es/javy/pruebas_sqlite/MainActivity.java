package es.javy.pruebas_sqlite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.app.Activity;

import es.javy.pruebas_sqlite.adapter.ListProductAdapter;
import es.javy.pruebas_sqlite.database.DatabaseHelper;
import es.javy.pruebas_sqlite.model.Product;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by javy on 3/8/18.
 */

public class MainActivity extends Activity {
    private ListView lvProduct;
    private ListProductAdapter adapter;
    private List<Product> mProductList;
    private DatabaseHelper mDBHelper;
    String SentenciaWhere = "";
    String SentenciaWhereB = "";
    String Depurar="";
    int multiplicar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        String CaracteresEntreTipos = " AND !";
        String Auxiliar="";
        TextView sentwhere = (TextView)findViewById(R.id.info_product);

        lvProduct = (ListView)findViewById(R.id.listview_product);
        mDBHelper = new DatabaseHelper(this);
        //Check exists database
        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if(false == database.exists()) {
            mDBHelper.getReadableDatabase();
            //Copy db
            if(copyDatabase(this)) {
                Toast.makeText(this, "Copy database succes", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Copy data error", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        //Obteniendo la instancia del Intent
        Intent recibiendo = getIntent();
        //Extrayendo el extra de tipo cadena
        String cadenarecibida = recibiendo.getStringExtra(SeleccionActivity.EXTRA_NOMBRE);
        //CADENAS WHERE
        SentenciaWhere="";
        SentenciaWhereB="WHERE";


        //2 primeros caracteres son jugadores
        Auxiliar=getResources().getStringArray(R.array.arraynumerojugadores)[Integer.parseInt(cadenarecibida.substring(0,2))];


        //ULTIMO CARACTER FILTRO TIPO JUGADORES
        //OPTIMO//CUALQUIERA//EXCEPTO OPTIMO -- 3//2//1

        switch (Integer.parseInt(cadenarecibida.substring(14,15))){
            case 1: SentenciaWhere = "EXPANSIONES.OPTIMO != " + Auxiliar;
                break;
            case 2: SentenciaWhere = "(EXPANSIONES.MINIMO <= " + Auxiliar + " AND EXPANSIONES.MAXIMO >= " + Auxiliar + ")";
                break;
            case 3: SentenciaWhere = SentenciaWhere + " EXPANSIONES.OPTIMO = " +  Auxiliar;
                break;
            default: SentenciaWhere = SentenciaWhere + " EXPANSIONES.OPTIMO = " +  Auxiliar;
                break;
        }

        //2 Siguientes caracteres son tiempo
        //Siguiente caracter es Minimo/Exacto/Maximo (1/2/3) tiempo (t>recibido, t==recibido, t<recibido)
        //Primero necesito si maximo,minimo,exacto y despues uso el tiempo
        SentenciaWhere=SentenciaWhere+ " AND EXPANSIONES.TIEMPO ";
        SentenciaWhereB=SentenciaWhereB+ " AND EXPANSIONES.TIEMPO ";
        switch (Integer.parseInt(cadenarecibida.substring(4,5))){
            case 1: SentenciaWhere=SentenciaWhere + " >= " + getResources().getStringArray(R.array.arrayminutosdw)[Integer.parseInt(cadenarecibida.substring(2,4))];
                SentenciaWhereB=SentenciaWhereB + " >= " + getResources().getStringArray(R.array.arrayminutosdw)[Integer.parseInt(cadenarecibida.substring(2,4))];
                break;
            case 2: SentenciaWhere=SentenciaWhere + " >= " + getResources().getStringArray(R.array.arrayminutosdw)[Integer.parseInt(cadenarecibida.substring(2,4))] + " AND EXPANSIONES.TIEMPO <= " + getResources().getStringArray(R.array.arrayminutosup)[Integer.parseInt(cadenarecibida.substring(2,4))];
                SentenciaWhereB=SentenciaWhereB + " >= " + getResources().getStringArray(R.array.arrayminutosdw)[Integer.parseInt(cadenarecibida.substring(2,4))] + " AND EXPANSIONES.TIEMPO <= " + getResources().getStringArray(R.array.arrayminutosup)[Integer.parseInt(cadenarecibida.substring(2,4))];
                break;
            case 3: SentenciaWhere=SentenciaWhere + " <= " + getResources().getStringArray(R.array.arrayminutosup)[Integer.parseInt(cadenarecibida.substring(2,4))];
                SentenciaWhereB=SentenciaWhereB + " <= " + getResources().getStringArray(R.array.arrayminutosup)[Integer.parseInt(cadenarecibida.substring(2,4))];
                break;
            default: SentenciaWhere=SentenciaWhere + " >= " + getResources().getStringArray(R.array.arrayminutosdw)[Integer.parseInt(cadenarecibida.substring(2,4))] + " AND EXPANSIONES.TIEMPO <= " + getResources().getStringArray(R.array.arrayminutosup)[Integer.parseInt(cadenarecibida.substring(2,4))];
                SentenciaWhereB=SentenciaWhereB + " >= " + getResources().getStringArray(R.array.arrayminutosdw)[Integer.parseInt(cadenarecibida.substring(2,4))] + " AND EXPANSIONES.TIEMPO <= " + getResources().getStringArray(R.array.arrayminutosup)[Integer.parseInt(cadenarecibida.substring(2,4))];
                break;
        }

        //Siguiente caracter es Ninguno/Alguno/Todos (1/2/3) tipo (!x && !y / x || y / x && y)
        switch (Integer.parseInt(cadenarecibida.substring(5,6))){
            case 1: CaracteresEntreTipos=" AND TIPOS.ESTILO != ";
                break;
            case 2: CaracteresEntreTipos=" OR TIPOS.ESTILO = ";
                Auxiliar="1";
                SentenciaWhere=SentenciaWhere+" AND (TIPOS.ESTILO = 'NULL' ";
                SentenciaWhereB=SentenciaWhereB+" AND (TIPOS.ESTILO = 'NULL' ";
                break;
            case 3: CaracteresEntreTipos=" AND TIPOS.ESTILO = ";
                if (Integer.parseInt(cadenarecibida.substring(6))==0){
                    SentenciaWhere=SentenciaWhere+" AND TIPOS.ESTILO = 'NULL' ";
                    SentenciaWhereB=SentenciaWhereB+" AND TIPOS.ESTILO = 'NULL' ";
                }
                break;
            default: CaracteresEntreTipos=" AND TIPOS.ESTILO != ";
                break;
        }

        //TODO PONER TIPOS CORRECTOS DE JUEGOS

        //tipos del checkbox indivdualizar
        if (Integer.parseInt(cadenarecibida.substring(6,7))==1){
            SentenciaWhere=SentenciaWhere+CaracteresEntreTipos+" 'Rol' ";
            SentenciaWhereB=SentenciaWhereB+CaracteresEntreTipos+" 'Rol' ";
        }
        if (Integer.parseInt(cadenarecibida.substring(7,8))==1){
            SentenciaWhere=SentenciaWhere+CaracteresEntreTipos+" 'Tablero' ";
            SentenciaWhereB=SentenciaWhereB+CaracteresEntreTipos+" 'Tablero' ";
        }
        if (Integer.parseInt(cadenarecibida.substring(8,9))==1){
            SentenciaWhere=SentenciaWhere+CaracteresEntreTipos+" 'Cartas' ";
            SentenciaWhereB=SentenciaWhereB+CaracteresEntreTipos+" 'Cartas' ";
        }
        if (Integer.parseInt(cadenarecibida.substring(9,10))==1){
            SentenciaWhere=SentenciaWhere+CaracteresEntreTipos+" 'Roles ocultos' ";
            SentenciaWhereB=SentenciaWhereB+CaracteresEntreTipos+" 'Roles ocultos' ";
        }
        if (Integer.parseInt(cadenarecibida.substring(10,11))==1){
            SentenciaWhere=SentenciaWhere+CaracteresEntreTipos+" 'Investigacion' ";
            SentenciaWhereB=SentenciaWhereB+CaracteresEntreTipos+" 'Investigacion' ";
        }
        if (Integer.parseInt(cadenarecibida.substring(11,12))==1){
            SentenciaWhere=SentenciaWhere+CaracteresEntreTipos+" 'Party' ";
            SentenciaWhereB=SentenciaWhereB+CaracteresEntreTipos+" 'Party' ";
        }
        if (Integer.parseInt(cadenarecibida.substring(12,13))==1){
            SentenciaWhere=SentenciaWhere+CaracteresEntreTipos+" 'Cooperativo' ";
            SentenciaWhereB=SentenciaWhereB+CaracteresEntreTipos+" 'Cooperativo' ";
        }
        if (Integer.parseInt(cadenarecibida.substring(13,14))==1){
            SentenciaWhere=SentenciaWhere+CaracteresEntreTipos+" 'Todos contra uno' ";
            SentenciaWhereB=SentenciaWhereB+CaracteresEntreTipos+" 'Todos contra uno' ";
        }

        if (Integer.parseInt(Auxiliar)==1){
            SentenciaWhere=SentenciaWhere+")";
            SentenciaWhereB=SentenciaWhereB+")";
        }




        //TODO IMPORTANTE ELIMINAR ESTAs LINEAs DE DEBUG
        Auxiliar=getResources().getStringArray(R.array.arraynumerojugadores)[Integer.parseInt(cadenarecibida.substring(0,2))];
        //Depurar="EXPANSIONES.MINIMO >= " + Auxiliar + " OR EXPANSIONES.MAXIMO <= " + Auxiliar;
        //sentwhere.setText(SentenciaWhere);
        //SentenciaWhere=Depurar;
        //sentwhere.setText(SentenciaWhere);


        //Get product li0st in db when db exists
        mProductList = mDBHelper.getListProduct(SentenciaWhere);
        //Init adapter
        adapter = new ListProductAdapter(this, mProductList);
        //Set adapter for listview
        lvProduct.setAdapter(adapter);


        registerForContextMenu(lvProduct
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        mProductList = mDBHelper.getListProduct(SentenciaWhere);
        //Init adapter
        adapter.updateList(mProductList);
    }

    private boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity","DB copied");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



}
