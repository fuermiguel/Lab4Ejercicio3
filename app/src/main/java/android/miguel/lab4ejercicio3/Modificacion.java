package android.miguel.lab4ejercicio3;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Modificacion extends AppCompatActivity {
    private EditText edt_codigo;
    private EditText edt_descripcion;
    private EditText edt_precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificacion);

        setTitle("Modificación");

        edt_codigo = (EditText) findViewById(R.id.edt_codigo);
        edt_descripcion = (EditText) findViewById(R.id.edt_descripcion);
        edt_precio = (EditText) findViewById(R.id.edt_precio);



    }

    public void modificacion(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = edt_codigo.getText().toString();
        String descri = edt_descripcion.getText().toString();
        String pre = edt_precio.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("codigo", cod);
        registro.put("descripcion", descri);
        registro.put("precio", pre);
        int cant = bd.update("articulos", registro, "codigo=" + cod, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "no existe un artículo con el código ingresado",
                    Toast.LENGTH_SHORT).show();
    }

}
