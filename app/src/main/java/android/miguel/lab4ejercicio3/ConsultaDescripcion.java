package android.miguel.lab4ejercicio3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ConsultaDescripcion extends AppCompatActivity {
    private EditText edt_descripcion;
    private Button btn_consultar;
    private ListView lstv_porDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_descripcion);

        setTitle("Consulta por descripción");

        edt_descripcion = (EditText) findViewById(R.id.edt_descripcion);
        btn_consultar = (Button) findViewById(R.id.btn_consultar);
        lstv_porDescripcion = (ListView) findViewById(R.id.lstv_porDescripcion);

    }

    public void consultapordescripcion(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();
        String descri = edt_descripcion.getText().toString();
        Cursor fila = bd.rawQuery(
                "select codigo,precio from articulos where descripcion='" + descri +"'", null);

        ArrayList registros = new ArrayList();


        if (fila.moveToFirst()) {
            do {
                registros.add(fila.getString(0) + " ---------- " +fila.getString(1) + " Euros" );
            } while (fila.moveToNext());

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, registros);
            lstv_porDescripcion.setAdapter(adapter);
        } else
            Toast.makeText(this, "No existe un artículo con dicha descripción",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }
}
