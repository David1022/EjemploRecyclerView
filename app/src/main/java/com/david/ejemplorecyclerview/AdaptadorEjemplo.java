package com.david.ejemplorecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class AdaptadorEjemplo extends RecyclerView.Adapter <AdaptadorEjemplo.EjemploViewHolder> {
    private LayoutInflater inflador;
    protected ArrayList<String> arrayTexto;
    private Context contexto;

    // El constructor inicializará el inflador del layout
    public AdaptadorEjemplo(Context contexto, ArrayList<String> arrayTexto) {
        inflador = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arrayTexto = arrayTexto;
        this.contexto = contexto;
    }

    // Creamos el ViewHolder con los tipos de elementos a modificar
    public static class EjemploViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagen;
        public TextView textoRevision;

        public EjemploViewHolder (View itemView){
            super(itemView);
            imagen = (ImageView) itemView.findViewById(R.id.imagen_selector);
            imagen.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            textoRevision = (TextView) itemView.findViewById(R.id.texto_selector);
        }
    }

    // Inflamos el elemento en la vista sin personalizar
    @Override
    public EjemploViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflador.inflate(R.layout.elemento_selector, null);
        return new EjemploViewHolder(v);
    }

    // Vinculamos los elemento al Holder, lo personalizamos
    @Override
    public void onBindViewHolder(EjemploViewHolder holder, int position) {
        String revision = arrayTexto.get(position);
        holder.imagen.setImageResource(R.drawable.logo_nipsa);
        holder.textoRevision.setText(revision);
    }

    // Indicamos cuantos elementos hay que visualizar
    @Override
    public int getItemCount() {
        return arrayTexto.size();
    }
}
