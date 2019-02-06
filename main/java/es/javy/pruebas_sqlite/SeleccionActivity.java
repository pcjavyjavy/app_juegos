package es.javy.pruebas_sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.Activity;

public class SeleccionActivity extends AppCompatActivity {
    private String losjugadores, quejugadores, lostiempos, quetiempo, losjuegos, quejuegos, cadena;
    private int nlosjugadores, nquejugadores, nlostiempos, nquetiempo, nlosjuegos, nquejuegos;
    public final static String EXTRA_NOMBRE = "es.javy.pruebas_sqlite.NOMBRE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion);

        final Spinner njugadores = (Spinner) findViewById(R.id.spinnerjugadores);
        ArrayAdapter<CharSequence> njugadap = ArrayAdapter.createFromResource(this, R.array.arraynumerojugadores, android.R.layout.simple_spinner_item);
        njugadap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        njugadores.setAdapter(njugadap);
        njugadores.setSelection(3);

        final Spinner ntiempo = (Spinner) findViewById(R.id.spinnertiempo);
        final ArrayAdapter<CharSequence> ntimeadap = ArrayAdapter.createFromResource(this, R.array.arraytiempos, android.R.layout.simple_spinner_item);
        ntimeadap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ntiempo.setAdapter(ntimeadap);
        ntiempo.setSelection(5);

        final TextView pulsado = (TextView) findViewById(R.id.debug);
        final Intent[] ventana = new Intent[1];

        Button btnresultado = (Button) findViewById(R.id.resultados);


        //TODO primer Radio Group declarar
        final RadioButton joptmo = (RadioButton) findViewById(R.id.radiojugadoresoptimos);
        final RadioButton jcualq = (RadioButton) findViewById(R.id.radiojugadorescualquiera);
        final RadioButton jnoopt = (RadioButton) findViewById(R.id.radiojugadoresnooptimos);

        //TODO segundo Radio Group declarar
        final RadioButton tmaximo = (RadioButton) findViewById(R.id.radiotiempomaximo);
        final RadioButton[] tminimo = {(RadioButton) findViewById(R.id.radiotiempominimo)};
        RadioButton texacto = (RadioButton) findViewById(R.id.radiotiemopexacto);

        //TODO tercer radio Group declarar
        final RadioButton tiptodos = (RadioButton) findViewById(R.id.radiotipotodos);
        RadioButton tipalgun = (RadioButton) findViewById(R.id.radiotipoalguno);
        final RadioButton tipningn = (RadioButton) findViewById(R.id.radiotiponinguno);

        //TODO checkboxes declarar
        final CheckBox col11 = (CheckBox) findViewById(R.id.checkboxa1);
        final CheckBox col12 = (CheckBox) findViewById(R.id.checkboxa2);
        final CheckBox col13 = (CheckBox) findViewById(R.id.checkboxa3);
        final CheckBox col14 = (CheckBox) findViewById(R.id.checkboxa4);
        final CheckBox col21 = (CheckBox) findViewById(R.id.checkboxb1);
        final CheckBox col22 = (CheckBox) findViewById(R.id.checkboxb2);
        final CheckBox col23 = (CheckBox) findViewById(R.id.checkboxb3);
        final CheckBox col24 = (CheckBox) findViewById(R.id.checkboxb4);

        btnresultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadena="";
                //TODO if-elseif-else/case primer radio group
                if (joptmo.isChecked()) {
                    quejugadores = "optimo para ";
                    nquejugadores=3;
                }else if (jnoopt.isChecked()){
                    quejugadores="valido pero no optimo para ";
                    nquejugadores=1;
                }else{
                    quejugadores="para ";
                    nquejugadores=2;
                }
                //al implementarse despues lo paso al final
                nlosjugadores = njugadores.getSelectedItemPosition();
                if (nlosjugadores < 10){
                    cadena=cadena+"0";
                }
                cadena=cadena+String.valueOf(nlosjugadores);
                losjugadores = String.valueOf(njugadores.getItemAtPosition(njugadores.getSelectedItemPosition()));
                nlostiempos = ntiempo.getSelectedItemPosition();
                if (nlostiempos < 10){
                    cadena=cadena+"0";
                }
                cadena=cadena+String.valueOf(nlostiempos);
                lostiempos = String.valueOf(ntiempo.getItemAtPosition(ntiempo.getSelectedItemPosition()));
                //TODO if-elseif-else/case segundo radio group
                if (tmaximo.isChecked()) {
                    quetiempo = "Maximo";
                    nquetiempo=3;
                }else if (tminimo[0].isChecked()){
                    quetiempo="Minimo";
                    nquetiempo=1;
                }else{
                    quetiempo="Exacto";
                    nquetiempo=2;
                }
                cadena=cadena+nquetiempo;
                //TODO if-elseif-else/case tercer radio group
                if (tiptodos.isChecked()) {
                    losjuegos = "Todos";
                    nlosjuegos=3;
                }else if (tipningn.isChecked()){
                    losjuegos="Ninguno";
                    nlosjuegos=1;
                }else{
                    losjuegos="Alguno";
                    nlosjuegos=2;
                }
                cadena=cadena+nlosjuegos;
                //TODO ifs checkboxes
                quejuegos="";
                if (col11.isChecked()){
                    quejuegos=quejuegos+"1";
                }
                else{
                    quejuegos=quejuegos+"0";
                }
                if (col12.isChecked()){
                    quejuegos=quejuegos+"1";
                }
                else{
                    quejuegos=quejuegos+"0";
                }
                if (col13.isChecked()){
                    quejuegos=quejuegos+"1";
                }
                else{
                    quejuegos=quejuegos+"0";
                }
                if (col14.isChecked()){
                    quejuegos=quejuegos+"1";
                }
                else{
                    quejuegos=quejuegos+"0";
                }
                if (col21.isChecked()){
                    quejuegos=quejuegos+"1";
                }
                else{
                    quejuegos=quejuegos+"0";
                }
                if (col22.isChecked()){
                    quejuegos=quejuegos+"1";
                }
                else{
                    quejuegos=quejuegos+"0";
                }
                if (col23.isChecked()){
                    quejuegos=quejuegos+"1";
                }
                else{
                    quejuegos=quejuegos+"0";
                }
                if (col24.isChecked()){
                    quejuegos=quejuegos+"1";
                }
                else{
                    quejuegos=quejuegos+"0";
                }
                cadena=cadena+quejuegos;
                cadena=cadena+nquejugadores;
                pulsado.setText("Ha elegido juego " + quejugadores + losjugadores + " jugadores " + lostiempos + " " + quetiempo);
                //TODO nueva ventana
                ventana[0] = new Intent(view.getContext(), MainActivity.class);
                //ventana = new Intent(MainActivity.class, ResultadoActivity.class);

                //TODO nueva ventana enviando datos necesarios
                ventana[0].putExtra(EXTRA_NOMBRE,cadena);


                //TODO abrir ventana
                //startActivity(ventana);
                startActivityForResult(ventana[0],0);
            }
        });



    }
}
