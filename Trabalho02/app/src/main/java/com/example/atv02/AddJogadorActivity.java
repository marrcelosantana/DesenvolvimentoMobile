package com.example.atv02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddJogadorActivity extends AppCompatActivity {

    EditText editNome;
    EditText editClube;
    EditText editNacionalidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoas);

        editNome = findViewById(R.id.EditTextNome);
        editClube = findViewById(R.id.EditTextClube);
        editNacionalidade = findViewById(R.id.EditTextNacionalidade);

    }

    public void cancelar(View view){
        finish();
    }

    public void addJogador(View view){
        Intent intent = new Intent();

        String nome = editNome.getText().toString();
        String clube = editClube.getText().toString();
        String nacionalidade = editNacionalidade.getText().toString();

        intent.putExtra("nome", nome);
        intent.putExtra("clube", clube);
        intent.putExtra("nacionalidade", nacionalidade);

        finish();
    }
}