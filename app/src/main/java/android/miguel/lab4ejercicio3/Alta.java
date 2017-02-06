package android.miguel.lab4ejercicio3;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Alta extends AppCompatActivity {
    private EditText et_codigo;
    private EditText et_descripcion;
    private EditText et_precio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta);

        setTitle("Alta");

        et_codigo = (EditText) findViewById(R.id.et_codigo);
        et_descripcion = (EditText) findViewById(R.id.et_descripcion);
        et_precio= (EditText) findViewById(R.id.et_precio);

    }

    public void aceptar(View v){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        String cod= et_codigo.getText().toString();
        String descri = et_descripcion.getText().toString();
        String pre = et_precio.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("codigo", cod);
        registro.put("descripcion", descri);
        registro.put("precio", pre);

        bd.insert("articulos", null, registro);
        bd.close();

        et_codigo.setText("");
        et_descripcion.setText("");
        et_precio.setText("");

        Toast.makeText(this, "Se cargaron los datos del artículo",
                Toast.LENGTH_SHORT).show();
        finish();
    }

    public void cancelar(View v){
        finish();
    }

}
