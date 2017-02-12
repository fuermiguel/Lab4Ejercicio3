package android.miguel.lab4ejercicio3;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BajaCodigo extends AppCompatActivity {
    private Button btn_borrar;
    private EditText edt_codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baja_codigo);

        setTitle("Baja por coódigo");

        btn_borrar = (Button) findViewById(R.id.btn_borrar);
        edt_codigo = (EditText) findViewById(R.id.edt_codigo);


    }

    public void bajaporcodigo(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod= edt_codigo.getText().toString();
        int cant = bd.delete("articulos", "codigo=" + cod, null);
        bd.close();
        edt_codigo.setText("");
        if (cant == 1)
            Toast.makeText(this, "Se borró el artículo con dicho código",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe un artículo con dicho código",
                    Toast.LENGTH_SHORT).show();
    }
}
