package com.example.cartellatombola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cartellatombola.model.Cartella;
import com.example.cartellatombola.utility.Costanti;
import com.example.cartellatombola.utility.SaveUtility;
import com.example.cartellatombola.utility.Utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class AggiungiActivity extends AppCompatActivity {

    private TextView messaggioErrore;
    private Button btnAzzera, btnGeneraRandom, btnConferma;
    private List<List<TextView>> grigliaCartella;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi);

        messaggioErrore = findViewById(R.id.txt_messaggio);
        btnAzzera = findViewById(R.id.btn_azzera);
        btnGeneraRandom = findViewById(R.id.btn_random);
        btnConferma = findViewById(R.id.btn_conferma);

        grigliaCartella = new ArrayList<>();
        for(int i=0; i<3; i++){
            List<TextView> lista = new ArrayList<>();
            for(int j=0; j<9; j++){
                lista.add(cercaTextView(i, j));
            }
            grigliaCartella.add(lista);
        }

        setOnClickListener();
    }

    private void setOnClickListener(){
        btnAzzera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                azzeraGriglia();
            }
        });

        btnGeneraRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generaRandom();
            }
        });

        btnConferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conferma();
            }
        });
    }

    private void azzeraGriglia() {
        messaggioErrore.setVisibility(View.GONE);
        Cartella cartella = Utility.generaCartellaVuota();
        grigliaCartella = Utility.riempiCartella(cartella, grigliaCartella);
    }

    private void generaRandom(){
        messaggioErrore.setVisibility(View.GONE);
        Cartella cartella = Utility.generaCartellaCasuale();
        grigliaCartella = Utility.riempiCartella(cartella, grigliaCartella);
    }

    private void conferma(){
        Cartella cartella = Utility.generaCartella(grigliaCartella);

        if(Utility.controllaCartella(cartella)){
            messaggioErrore.setVisibility(View.GONE);

            List<Cartella> cartelle = findCartelle();
            if(cartelle.size()>= Costanti.NUMERO_MASSIMO_CARTELLE){
                messaggioErrore.setVisibility(View.VISIBLE);
                messaggioErrore.setText(Costanti.MessaggiErrore.NUMERO_MASSIMO_CARTELLE);
                return;
            }

            cartelle.add(cartella);
            String stringToSave = SaveUtility.cartelleToString(cartelle);

            //List<Cartella> cartelles = SaveUtility.getCartelleSalvateFromString(stringToSave);
            //System.out.println(cartelles);

            salvaCartelle(stringToSave);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            messaggioErrore.setVisibility(View.VISIBLE);
            messaggioErrore.setText(Costanti.MessaggiErrore.CARTELLA_NON_VALIDA);
        }
    }

    private void salvaCartelle(String cartelle){
        try{
            FileOutputStream fOut = openFileOutput(Costanti.Save.NOME_FILE_CARTELLE, MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);

            String recordString = ""+cartelle;

            osw.write(""+recordString);
            osw.flush();
            osw.close();
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
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

    private TextView cercaTextView(int i, int j){
        TextView ret = null;
        int numeroImmagine = i*9+j+1;
        switch (numeroImmagine){
            case 1: ret = findViewById(R.id.numA1); break;
            case 2: ret = findViewById(R.id.numA2); break;
            case 3: ret = findViewById(R.id.numA3); break;
            case 4: ret = findViewById(R.id.numA4); break;
            case 5: ret = findViewById(R.id.numA5); break;
            case 6: ret = findViewById(R.id.numA6); break;
            case 7: ret = findViewById(R.id.numA7); break;
            case 8: ret = findViewById(R.id.numA8); break;
            case 9: ret = findViewById(R.id.numA9); break;

            case 10: ret = findViewById(R.id.numB1); break;
            case 11: ret = findViewById(R.id.numB2); break;
            case 12: ret = findViewById(R.id.numB3); break;
            case 13: ret = findViewById(R.id.numB4); break;
            case 14: ret = findViewById(R.id.numB5); break;
            case 15: ret = findViewById(R.id.numB6); break;
            case 16: ret = findViewById(R.id.numB7); break;
            case 17: ret = findViewById(R.id.numB8); break;
            case 18: ret = findViewById(R.id.numB9); break;

            case 19: ret = findViewById(R.id.numC1); break;
            case 20: ret = findViewById(R.id.numC2); break;
            case 21: ret = findViewById(R.id.numC3); break;
            case 22: ret = findViewById(R.id.numC4); break;
            case 23: ret = findViewById(R.id.numC5); break;
            case 24: ret = findViewById(R.id.numC6); break;
            case 25: ret = findViewById(R.id.numC7); break;
            case 26: ret = findViewById(R.id.numC8); break;
            case 27: ret = findViewById(R.id.numC9); break;
        }

        return ret;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}