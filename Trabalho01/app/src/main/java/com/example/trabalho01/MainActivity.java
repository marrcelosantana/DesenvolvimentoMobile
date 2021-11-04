package com.example.trabalho01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> pessoas = new ArrayList<>();
    AutoCompleteTextView autoCompleteTextView;
    TextView textView2;
    String [] numeros = {"Zero","Um", "Dois", "Três", "Quatro", "Cinco", "Seis", "Sete", "Oito", "Nove", "Dez"};
    ArrayAdapter<String> adapter;
    Spinner spnr;
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //AutoComplete Init
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        textView2 = findViewById(R.id.textView2);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, numeros);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView2.setText(adapter.getItem(position));
            }
        }); //AutoComplete End


        //Spinner Init
        spnr = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, numeros);

        spnr.setAdapter(adapter);
        spnr.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                        int position = spnr.getSelectedItemPosition();
                        Toast.makeText(getApplicationContext(),"You have selected "+numeros[+position],Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                    }

                }
        ); //Spinner End

        //Init RadioButton
        radioGroup = findViewById(R.id.radioGroup);

        executarSom();

        //Init LongPress
        TextView txtView = (TextView) findViewById(R.id.textView3);
        txtView.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(),
                        "Você apertou por um longo tempo!! :)", 2000).show();
                return true;
            }
        });
        txtView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "Você não pressionou tempo suficiente :(",
                        1000).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private void executarSom() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.music);
        mediaPlayer.start();
    }

    public void add(View view) {
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        String input = editText.getText().toString();
        editText.setText("");
        pessoas.add(input);
        TextView textView = findViewById(R.id.textView);
        textView.setText(pessoas.toString());
    }

    public void onDefaultToggleClick(View view){
        Toast.makeText(this, "DefaultToggle", Toast.LENGTH_SHORT).show();
    }

    public void checkButton(View view){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, "Selecione o botão" + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }

    public void abrirDropdown(View view) {
        Button button = (Button) findViewById(R.id.button4);
        //Creating the instance of PopupMenu
        PopupMenu popup = new PopupMenu(MainActivity.this, button);
        //Inflating the Popup using xml file
        popup.getMenuInflater()
                .inflate(R.menu.dropdown, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(
                        MainActivity.this,
                        "You Clicked : " + item.getTitle(),
                        Toast.LENGTH_SHORT
                ).show();
                return true;
            }
        });
        popup.show();
    }
}