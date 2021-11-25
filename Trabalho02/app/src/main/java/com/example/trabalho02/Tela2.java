package com.example.trabalho02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class Tela2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);
    }

    public void cancelar(View view){
        finish();
    }

    public void addJogador(View view){
        Intent intent = new Intent(this, MainActivity.class);

        EditText editNome = (EditText)findViewById(R.id.EditTextNome);
        EditText editClube = (EditText)findViewById(R.id.EditTextClube);
        EditText editNacionalidade = (EditText)findViewById(R.id.EditTextNacionalidade);

        String nome = editNome.getText().toString();
        String clube = editClube.getText().toString();
        String nacionalidade = editNacionalidade.getText().toString();

        Jogador jogador = new Jogador(nome, clube, nacionalidade);
        ArrayList<Jogador> jogadores = getIntentJogadores();
        jogadores.add(jogador);

        intent.putExtra(MainActivity.JOGADORES, jogadores);

        startActivity(intent);
        finish();
    }

    public ArrayList<Jogador> getIntentJogadores(){
        return (ArrayList<Jogador>) getIntent().getSerializableExtra(MainActivity.JOGADORES);
    }
}