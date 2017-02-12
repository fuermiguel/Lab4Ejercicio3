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

import static android.miguel.lab4ejercicio3.R.id.lst_registros;

public class ConsultaCodigo extends AppCompatActivity {
    private Button btn_consultar;
    private EditText edt_codigo;
    private ListView lstv_porCodigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_codigo);

        setTitle("Consulta por código");

        btn_consultar = (Button) findViewById(R.id.btn_consultar);
        edt_codigo = (EditText) findViewById(R.id.edt_codigo);
        lstv_porCodigo  =(ListView) findViewById(R.id.lstv_porCodigo);

    }



    public void consultaporcodigo(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();
        String cod = edt_codigo.getText().toString();
        Cursor fila = bd.rawQuery(
                "select descripcion,precio from articulos where codigo=" + cod, null);

        ArrayList registros = new ArrayList();


        if (fila.moveToFirst()) {
            do {
                registros.add(fila.getString(0) + " ---------- " +fila.getString(1) + " Euros" );
            } while (fila.moveToNext());

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, registros);
            lstv_porCodigo.setAdapter(adapter);

        } else
            Toast.makeText(this, "No existe un artículo con dicho código",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }
}
