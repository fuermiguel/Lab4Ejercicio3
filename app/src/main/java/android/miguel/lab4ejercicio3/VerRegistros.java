package android.miguel.lab4ejercicio3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class VerRegistros extends AppCompatActivity {

    private ListView lst_registros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_registros);

        setTitle("Ver registros");

        lst_registros = (ListView) findViewById(R.id.lst_registros);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Cursor fila = bd.rawQuery("select descripcion,precio from articulos", null);

        ArrayList registros = new ArrayList();


        if (fila.moveToFirst()) {
            do {
                registros.add(fila.getString(0) + " ---------- " +fila.getString(1) + " Euros" );
            } while (fila.moveToNext());

        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, registros);
        lst_registros.setAdapter(adapter);

        bd.close();
    }

    private void salir(View v){
        finish();
    }
}
