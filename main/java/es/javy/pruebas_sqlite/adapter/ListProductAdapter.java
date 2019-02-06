package es.javy.pruebas_sqlite.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import es.javy.pruebas_sqlite.R;
import es.javy.pruebas_sqlite.model.Product;

import java.util.List;

/**
 * Created by javy on 3/8/18.
 */

public class ListProductAdapter extends BaseAdapter {
    private Context mContext;
    private List<Product> mProductList;

    public ListProductAdapter(Context mContext, List<Product> mProductList) {
        this.mContext = mContext;
        this.mProductList = mProductList;
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mProductList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext, R.layout.item_listview, null);
        TextView tvNombreJuego = (TextView)v.findViewById(R.id.tv_nombre_juego);
        TextView tvNombreExpansion = (TextView)v.findViewById(R.id.tv_nombre_expansion);
        TextView tvTiempo = (TextView)v.findViewById(R.id.tv_tiempo);
        TextView tvMinJugadores = (TextView)v.findViewById(R.id.tv_minjugadores);
        TextView tvMaxJugadores = (TextView)v.findViewById(R.id.tv_maxjugadores);
        TextView tvOptimoJugadores = (TextView)v.findViewById(R.id.tv_optimojugadores);
        TextView tvReglas = (TextView)v.findViewById(R.id.tv_reglas);
        TextView tvFaqs = (TextView)v.findViewById(R.id.tv_faqs);
        TextView tvDescripcion = (TextView)v.findViewById(R.id.tv_descripcion);
        //TODO productos

        tvNombreJuego.setText(mProductList.get(position).getJuegoNombre());
        tvNombreExpansion.setText(mProductList.get(position).getJuegoExpansion());
        tvTiempo.setText(String.valueOf(mProductList.get(position).getExpansionTiempo()));
        tvMinJugadores.setText(String.valueOf(mProductList.get(position).getExpansionMinJugadores()));
        tvMaxJugadores.setText(String.valueOf(mProductList.get(position).getExpansionMaxJugadores()));
        tvOptimoJugadores.setText(String.valueOf(mProductList.get(position).getExpansionOptimoJugadores()));
        tvReglas.setText(mProductList.get(position).getExpansionReglas());
        tvFaqs.setText(mProductList.get(position).getExpansionFaqs());
        tvDescripcion.setText(mProductList.get(position).getJuegoDescripcion());
        v.setTag( mProductList.get(position).getId());

        return v;
    }

    public void updateList(List<Product> lstItem) {
        mProductList.clear();
        mProductList.addAll(lstItem);
        this.notifyDataSetChanged();
    }
}
