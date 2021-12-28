package com.example.cartellatombola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cartellatombola.model.Cartella;
import com.example.cartellatombola.utility.Costanti;
import com.example.cartellatombola.utility.SaveUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button goToAggiungi, goToMostra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goToAggiungi = findViewById(R.id.btn_aggiungi);
        goToAggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAggiungiPage();
            }
        });

        goToMostra = findViewById(R.id.btn_mostra);
        goToMostra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMostraPage();
            }
        });
    }

    private void openAggiungiPage(){
        Intent intent = new Intent(this, AggiungiActivity.class);
        startActivity(intent);
    }

    private void openMostraPage(){
        List<Cartella> cartelle = findCartelle();
        if(cartelle.isEmpty()){
            Toast.makeText(getApplicationContext(), "Non sono presenti cartelle!\nCreane almeno una per poter giocare", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent = new Intent(this, MostraActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    private List<Cartella> findCartelle(){
        try {
            FileInputStream fIn = openFileInput(Costanti.Save.NOME_FILE_CARTELLE);

            InputStreamReader isr = new InputStreamReader(fIn);

            char[] inputBuffer = new char[Costanti.Save.READ_BLOCK_SIZE];
            String s = "";

            int charRead;

            while ((charRead = isr.read(inputBuffer))>0){
                String readString = String.copyValueOf(inputBuffer, 0,charRead);
                s += readString;
                inputBuffer = new char[Costanti.Save.READ_BLOCK_SIZE];
            }

            if(s==null || s.isEmpty()){
                s = "0";
            }

            return SaveUtility.getCartelleSalvateFromString(s);
        }
        catch (IOException ioe) {
            return new ArrayList<>();
        }
    }
}