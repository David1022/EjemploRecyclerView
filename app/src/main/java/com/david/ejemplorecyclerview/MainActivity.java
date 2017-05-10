package com.david.ejemplorecyclerview;

import android.content.Context;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TableLayout;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

/*
 * Ejemplo de RecyclerView y TabsLayout
 * Para poder ejecutar se debe incluir
 * ------ compile 'com.android.support:recyclerview-v7:25.3.1' (para el Recycler View) ----------
 * ------ compile 'com.android.support:design:25.3.1' (para el TabLayout) -----------------------
 * en el build.gradle (Module:app)
 * En Android Manifest se ha cambiado el tema por un NoTitleBar. Se han incluido nuevos temas en @styles
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> revisiones;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Cargamos la actividad
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        revisiones = Ejemplos.getPendientes();

        // Inicializamos los elementos de la AppBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Añadimos y configuramos las pestañas
        TabLayout tabs = (TabLayout) findViewById(R.id.tablayout);
        tabs.addTab(tabs.newTab().setText("Pendientes"));
        tabs.addTab(tabs.newTab().setText("En curso"));
        tabs.addTab(tabs.newTab().setText("Finalizadas"));
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE); // Las pestañas se deslizaran si no caben en la pantalla
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        revisiones = Ejemplos.getPendientes();
                        recyclerView.setAdapter(new AdaptadorEjemplo(context, revisiones));
                        break;
                    case 1:
                        revisiones = Ejemplos.getEnCurso();
                        recyclerView.setAdapter(new AdaptadorEjemplo(context, revisiones));
                        break;
                    case 2:
                        revisiones = Ejemplos.getFinalizadas();
                        recyclerView.setAdapter(new AdaptadorEjemplo(context, revisiones));
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // Configuramos el RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view); // Asociamos el RecyclerView a su elemento en el contenedor
        recyclerView.setAdapter(new AdaptadorEjemplo(this, revisiones)); // Asociamos un adaptador de elementos al RecyclerView
        layoutManager = new GridLayoutManager(this, 5); // Creamos un layoutManager para RecyclerView (Linear o Grid)...
        recyclerView.setLayoutManager(layoutManager); //...y lo asociamos al RecyclerView

    }

    /*
     * Listará los ficheros que hay en el directorio
     */
    public ArrayList<String> listarFicheros(){

        // Vector TEXTO donde guardaremos los nombres de los ficheros
        ArrayList<String> listaFicheros = new ArrayList<>();
        try {
            //Definimos la ruta donde buscar los ficheros
            File f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/RPR/");
            if (!f.exists()) { // Si el directorio no existe lo creamos
                f.mkdirs();
            }
            //Creamos el vector de tipo File con el contenido de la carpeta
            File[] files = f.listFiles();
            //Si hay algun fichero en la carpeta iteramos para extraer el nombre de cada fichero
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    //Sacamos del array files un fichero
                    File file = files[i];
                    //Si es fichero (no es directorio)...
                    if (file.isFile())
                        listaFicheros.add(file.getName());
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error listando ficheros", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

        return listaFicheros;
    }
}
