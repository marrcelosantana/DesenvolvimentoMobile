package com.example.atv02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Jogador> jogadores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String resultado = "Resultado: "+ requestCode + " - " + resultCode;

        if(data.getExtras() != null){
            String nome = (String)data.getExtras().get("nome");
            String clube = (String)data.getExtras().get("clube");
            String nacionalidade = (String)data.getExtras().get("nacionalidade");
            resultado += " - "+ nome + " - "+ clube + " -  "+nacionalidade;

            Log.d("MainActivity", resultado);

        }
    }

    public void adicionar(View view){
        Log.d("MainActivity", "Cliquei!!!");
        Intent intent = new Intent(this, AddJogadorActivity.class);
        intent.putExtra("nome", "Marcelo");
        intent.putExtra("clube", "Ceará");
        intent.putExtra("nacionalidade", "Brasileiro");
        startActivity(intent);
    }

    public void editar(View view){
        Intent intent = new Intent(this, AddJogadorActivity.class);
        intent.putExtra("nome", "Marcelo");
        intent.putExtra("clube", "Ceará");
        intent.putExtra("nacionalidade", "Brasileiro");
        startActivity(intent);
    }
}