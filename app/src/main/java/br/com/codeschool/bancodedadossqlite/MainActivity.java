package br.com.codeschool.bancodedadossqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        try {
            //criar o banco de dados
            SQLiteDatabase bancoDados = openOrCreateDatabase("app" , MODE_PRIVATE, null);

            //criar tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas(nome VARCHAR, idade INT(3))");

            //inserir dados
            bancoDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES('Thais', 27)");
            bancoDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES('Josué', 24)");

            //recuperar pessoas
            Cursor cursor = bancoDados.rawQuery("SELECT nome, idade FROM pessoas",null);

            //indices
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();

            while(cursor != null){
                Log.i("Resultado - nome: ", cursor.getString(indiceNome));
                Log.i("Resultado - idade: ", cursor.getString(indiceIdade));
                cursor.moveToNext();
            }

        } catch (Exception e){
            e.printStackTrace();

        }

    }
}