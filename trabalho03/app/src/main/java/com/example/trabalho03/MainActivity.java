package com.example.trabalho03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.trabalho03.model.Jogador;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    EditText editNome;
    EditText editClube;
    EditText editNacionalidade;
    ListView list_dados;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private List<Jogador> jogadores = new ArrayList<Jogador>();
    private ArrayAdapter<Jogador> arrayAdapterJogador;

    Jogador jogadorSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNome = (EditText)findViewById(R.id.editTextName);
        editClube = (EditText)findViewById(R.id.editTextClub);
        editNacionalidade = (EditText)findViewById(R.id.editTextNac);
        list_dados = (ListView)findViewById(R.id.listV_dados);

        iniciarFirebase();
        eventoDatabase();
        selecionarJogador();

    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(MainActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

    private void eventoDatabase(){ //Função para listar jogadores na tela.
        databaseReference.child("Jogador").addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                jogadores.clear();
                for(DataSnapshot objetoSnapshot: dataSnapshot.getChildren()){
                    Jogador jogador = objetoSnapshot.getValue(Jogador.class);
                    jogadores.add(jogador);
                }
                arrayAdapterJogador = new ArrayAdapter<Jogador>(MainActivity.this, android.R.layout.simple_list_item_1, jogadores);
                list_dados.setAdapter(arrayAdapterJogador);
            }

            @Override
            public void onCancelled(DatabaseError databaseError){
                System.out.println(databaseError);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.menu_novo){ //Criar novo jogador.
            Jogador jogador = new Jogador();
            jogador.setUid(UUID.randomUUID().toString());
            jogador.setNome(editNome.getText().toString());
            jogador.setClube(editClube.getText().toString());
            jogador.setNacionalidade(editNacionalidade.getText().toString());
            databaseReference.child("Jogador").child(jogador.getUid()).setValue(jogador); //Aqui criamos a tabela e definimos a chave primária.
            limparCampos();
        }

        if(id == R.id.menu_editar){ //Editar jogador.
            Jogador jogador = new Jogador();
            jogador.setUid(jogadorSelecionado.getUid());
            jogador.setNome(editNome.getText().toString().trim());
            jogador.setClube(editClube.getText().toString().trim());
            jogador.setNacionalidade(editNacionalidade.getText().toString().trim());
            databaseReference.child("Jogador").child(jogador.getUid()).setValue(jogador);
            limparCampos();
        }

        if(id == R.id.menu_deletar){ //Deletar jogador.
            Jogador jogador = new Jogador();
            jogador.setUid(jogadorSelecionado.getUid());
            databaseReference.child("Jogador").child(jogador.getUid()).removeValue();
            limparCampos();
        }
        return true;
    }

    private void limparCampos(){
        editNome.setText("");
        editClube.setText("");
        editNacionalidade.setText("");
    }

    private void selecionarJogador(){
        list_dados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                jogadorSelecionado = (Jogador)parent.getItemAtPosition(position);
                editNome.setText(jogadorSelecionado.getNome());
                editClube.setText(jogadorSelecionado.getClube());
                editNacionalidade.setText(jogadorSelecionado.getNacionalidade());
            }
        });
    }
}