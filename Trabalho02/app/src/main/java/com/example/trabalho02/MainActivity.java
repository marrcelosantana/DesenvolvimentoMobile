package com.example.trabalho02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Jogador> jogadores = new ArrayList<>();
    public static final String JOGADORES = "JOGADORES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mostrarJogadores();
    }

    public void adicionar(View view){
        Intent intent = new Intent(this, Tela2.class);
        intent.putExtra(JOGADORES, jogadores);

        startActivity(intent);
    }

    public ArrayList<Jogador> getIntentJogadores(){
        return (ArrayList<Jogador>) getIntent().getSerializableExtra(JOGADORES);
    }

    public void mostrarJogadores(){
        String info = "";
        if(getIntentJogadores() != null){
            jogadores = getIntentJogadores();
            TextView listaJogadores = (TextView) findViewById(R.id.ListaJogadores);
            for(int i = 0; i < jogadores.size(); i++){
                info += "Id: "+i+" => ";
                info += jogadores.get(i).toString();
            }
            listaJogadores.setText(info);
        }
    }

    public void editar(View view){
        Intent intent = new Intent(this, TelaEdit.class);
        intent.putExtra(JOGADORES, jogadores);
        startActivity(intent);
    }

}