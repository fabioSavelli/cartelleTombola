package com.example.cartellatombola;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cartellatombola.model.Cartella;
import com.example.cartellatombola.model.MyButton;
import com.example.cartellatombola.utility.Costanti;
import com.example.cartellatombola.utility.SaveUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MostraActivity extends AppCompatActivity {

    private List<List<String>> numeriSegnati;

    private ListView listaCartella;
    private TextView messaggioErrore;

    private View confermaCancellazione, messaggioConfermaNuovoGioco, messaggioConfermaIndietro;
    private TextView messaggioCancellazione;
    private Button confermaCancella, annullaCancella, resetta, confermaNuovoGioco, annullaNuovoGioco, confermaIndietro, annullaIndietro;
    private View confermaCancellaView, annullaCancellaView, resettaView, confermaNuovoGiocoView, annullaNuovoGiocoView, confermaIndietroView, annullaIndietroView;
    private int positionCancella;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra);

        List<Cartella> cartelle = findCartelle();

        String rTitolo[] = new String[cartelle.size()];
        int rRiga1[][] = new int[cartelle.size()][9];
        int rRiga2[][] = new int[cartelle.size()][9];
        int rRiga3[][] = new int[cartelle.size()][9];

        int i=0;
        for(Cartella c : cartelle){
            rTitolo[i] = Costanti.TITOLO_CARTELLA+(i+1);
            rRiga1[i] = c.getRiga1();
            rRiga2[i] = c.getRiga2();
            rRiga3[i] = c.getRiga3();
            i++;
        }
        positionCancella = -1;

        messaggioErrore = findViewById(R.id.txt_messaggio);
        listaCartella = findViewById(R.id.listaCartelle);

        View header = getLayoutInflater().inflate(R.layout.header_mostra, null);
        View footer = getLayoutInflater().inflate(R.layout.footer_mostra, null);
        listaCartella.addHeaderView(header);
        listaCartella.addFooterView(footer);

        numeriSegnati = new ArrayList<>();
        for(Cartella c : cartelle){
            numeriSegnati.add(new ArrayList<>());
        }

        MyAdapter adapter = new MyAdapter(this, rTitolo, rRiga1, rRiga2, rRiga3);
        listaCartella.setAdapter(adapter);

        if(cartelle.isEmpty()){
            messaggioErrore.setVisibility(View.VISIBLE);
            messaggioErrore.setText(Costanti.MessaggiErrore.NESSUNA_CARTELLA_PRESENTE);
        }

        confermaCancellazione = findViewById(R.id.messaggio_conferma);
        messaggioConfermaNuovoGioco = findViewById(R.id.messaggio_conferma_nuovo_gioco);
        messaggioConfermaIndietro = findViewById(R.id.messaggio_conferma_indietro);
        messaggioCancellazione = findViewById(R.id.txt_messaggio_cancella);
        confermaCancella = findViewById(R.id.conferma_cancella);
        annullaCancella = findViewById(R.id.annulla_cancella);
        confermaNuovoGioco = findViewById(R.id.conferma_nuovo_gioco);
        annullaNuovoGioco = findViewById(R.id.annulla_nuovo_gioco);
        confermaIndietro = findViewById(R.id.conferma_indietro);
        annullaIndietro = findViewById(R.id.annulla_indietro);

        confermaCancellaView = findViewById(R.id.conferma_cancella_view);
        annullaCancellaView = findViewById(R.id.annulla_cancella_view);
        confermaNuovoGiocoView = findViewById(R.id.conferma_nuovo_gioco_view);
        annullaNuovoGiocoView = findViewById(R.id.annulla_nuovo_gioco_view);
        confermaIndietroView = findViewById(R.id.conferma_indietro_view);
        annullaIndietroView = findViewById(R.id.annulla_indietro_view);

        resetta = findViewById(R.id.resetta);
        resettaView = findViewById(R.id.resetta_view);

        confermaCancella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confermaCancellazione();
            }
        });

        annullaCancella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                annullaCancellazione();
            }
        });

        confermaNuovoGioco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confermaNuovoGioco();
            }
        });

        annullaNuovoGioco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                annullaNuovoGioco();
            }
        });

        confermaIndietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confermaIndietro();
            }
        });

        annullaIndietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                annullaIndietro();
            }
        });

        resetta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetta();
            }
        });

        // VIEW
        confermaCancellaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confermaCancellazione();
            }
        });

        annullaCancellaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                annullaCancellazione();
            }
        });

        confermaNuovoGiocoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confermaNuovoGioco();
            }
        });

        annullaNuovoGiocoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                annullaNuovoGioco();
            }
        });

        confermaIndietroView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confermaIndietro();
            }
        });

        annullaIndietroView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                annullaIndietro();
            }
        });

        resettaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetta();
            }
        });
    }

    private void confermaCancellazione(){
        List<Cartella> cartelle = findCartelle();
        cartelle.remove(positionCancella);

        String stringCartelle = SaveUtility.cartelleToString(cartelle);
        salvaCartelle(stringCartelle);

        cartelle = findCartelle();
        if(cartelle.isEmpty()){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Intent intent = new Intent(this, MostraActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void annullaCancellazione(){
        confermaCancellazione.setVisibility(View.GONE);
        positionCancella = -1;
        resetta.setVisibility(View.VISIBLE);
    }

    private void confermaNuovoGioco(){
        Intent intent = new Intent(this, MostraActivity.class);
        startActivity(intent);
        finish();
    }

    private void annullaNuovoGioco(){
        messaggioConfermaNuovoGioco.setVisibility(View.GONE);
        resetta.setVisibility(View.VISIBLE);
    }

    private void resetta() {
        messaggioConfermaNuovoGioco.setVisibility(View.VISIBLE);
        resetta.setVisibility(View.GONE);
    }

    private void confermaIndietro(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void annullaIndietro(){
        messaggioConfermaIndietro.setVisibility(View.GONE);
        resetta.setVisibility(View.VISIBLE);
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

    @Override
    public void onBackPressed() {
        messaggioConfermaIndietro.setVisibility(View.VISIBLE);
        resetta.setVisibility(View.GONE);
    }

    class MyAdapter extends ArrayAdapter<String>{
        Context context;
        String rTitolo[];
        int rRiga1[][];
        int rRiga2[][];
        int rRiga3[][];

        MyAdapter (Context c, String titolo[], int riga1[][], int riga2[][], int riga3[][]){
            super(c, R.layout.cartella, R.id.txt_titolo, titolo);
            this.context = c;
            this.rTitolo = titolo;
            this.rRiga1 = riga1;
            this.rRiga2 = riga2;
            this.rRiga3 = riga3;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.cartella, parent, false);

            TextView titolo = row.findViewById(R.id.txt_titolo);
            Button cancella = row.findViewById(R.id.cancella);
            MyButton numA1 = row.findViewById(R.id.numA1);
            MyButton numA2 = row.findViewById(R.id.numA2);
            MyButton numA3 = row.findViewById(R.id.numA3);
            MyButton numA4 = row.findViewById(R.id.numA4);
            MyButton numA5 = row.findViewById(R.id.numA5);
            MyButton numA6 = row.findViewById(R.id.numA6);
            MyButton numA7 = row.findViewById(R.id.numA7);
            MyButton numA8 = row.findViewById(R.id.numA8);
            MyButton numA9 = row.findViewById(R.id.numA9);
            MyButton numB1 = row.findViewById(R.id.numB1);
            MyButton numB2 = row.findViewById(R.id.numB2);
            MyButton numB3 = row.findViewById(R.id.numB3);
            MyButton numB4 = row.findViewById(R.id.numB4);
            MyButton numB5 = row.findViewById(R.id.numB5);
            MyButton numB6 = row.findViewById(R.id.numB6);
            MyButton numB7 = row.findViewById(R.id.numB7);
            MyButton numB8 = row.findViewById(R.id.numB8);
            MyButton numB9 = row.findViewById(R.id.numB9);
            MyButton numC1 = row.findViewById(R.id.numC1);
            MyButton numC2 = row.findViewById(R.id.numC2);
            MyButton numC3 = row.findViewById(R.id.numC3);
            MyButton numC4 = row.findViewById(R.id.numC4);
            MyButton numC5 = row.findViewById(R.id.numC5);
            MyButton numC6 = row.findViewById(R.id.numC6);
            MyButton numC7 = row.findViewById(R.id.numC7);
            MyButton numC8 = row.findViewById(R.id.numC8);
            MyButton numC9 = row.findViewById(R.id.numC9);

            titolo.setText(Costanti.TITOLO_CARTELLA+(position+1));
            numA1.setText(rRiga1[position][0]==0?"":""+rRiga1[position][0]);
            numA2.setText(rRiga1[position][1]==0?"":""+rRiga1[position][1]);
            numA3.setText(rRiga1[position][2]==0?"":""+rRiga1[position][2]);
            numA4.setText(rRiga1[position][3]==0?"":""+rRiga1[position][3]);
            numA5.setText(rRiga1[position][4]==0?"":""+rRiga1[position][4]);
            numA6.setText(rRiga1[position][5]==0?"":""+rRiga1[position][5]);
            numA7.setText(rRiga1[position][6]==0?"":""+rRiga1[position][6]);
            numA8.setText(rRiga1[position][7]==0?"":""+rRiga1[position][7]);
            numA9.setText(rRiga1[position][8]==0?"":""+rRiga1[position][8]);
            numB1.setText(rRiga2[position][0]==0?"":""+rRiga2[position][0]);
            numB2.setText(rRiga2[position][1]==0?"":""+rRiga2[position][1]);
            numB3.setText(rRiga2[position][2]==0?"":""+rRiga2[position][2]);
            numB4.setText(rRiga2[position][3]==0?"":""+rRiga2[position][3]);
            numB5.setText(rRiga2[position][4]==0?"":""+rRiga2[position][4]);
            numB6.setText(rRiga2[position][5]==0?"":""+rRiga2[position][5]);
            numB7.setText(rRiga2[position][6]==0?"":""+rRiga2[position][6]);
            numB8.setText(rRiga2[position][7]==0?"":""+rRiga2[position][7]);
            numB9.setText(rRiga2[position][8]==0?"":""+rRiga2[position][8]);
            numC1.setText(rRiga3[position][0]==0?"":""+rRiga3[position][0]);
            numC2.setText(rRiga3[position][1]==0?"":""+rRiga3[position][1]);
            numC3.setText(rRiga3[position][2]==0?"":""+rRiga3[position][2]);
            numC4.setText(rRiga3[position][3]==0?"":""+rRiga3[position][3]);
            numC5.setText(rRiga3[position][4]==0?"":""+rRiga3[position][4]);
            numC6.setText(rRiga3[position][5]==0?"":""+rRiga3[position][5]);
            numC7.setText(rRiga3[position][6]==0?"":""+rRiga3[position][6]);
            numC8.setText(rRiga3[position][7]==0?"":""+rRiga3[position][7]);
            numC9.setText(rRiga3[position][8]==0?"":""+rRiga3[position][8]);

            numA1.setBackgroundResource(numeriSegnati.get(position).contains(numA1.getText().toString())?R.drawable.background_casella_selected_view:R.drawable.background_casella_view);
            numA2.setBackgroundResource(numeriSegnati.get(position).contains(numA2.getText().toString())?R.drawable.background_casella_selected_view:R.drawable.background_casella_view);
            numA3.setBackgroundResource(numeriSegnati.get(position).contains(numA3.getText().toString())?R.drawable.background_casella_selected_view:R.drawable.background_casella_view);
            numA4.setBackgroundResource(numeriSegnati.get(position).contains(numA4.getText().toString())?R.drawable.background_casella_selected_view:R.drawable.background_casella_view);
            numA5.setBackgroundResource(numeriSegnati.get(position).contains(numA5.getText().toString())?R.drawable.background_casella_selected_view:R.drawable.background_casella_view);
            numA6.setBackgroundResource(numeriSegnati.get(position).contains(numA6.getText().toString())?R.drawable.background_casella_selected_view:R.drawable.background_casella_view);
            numA7.setBackgroundResource(numeriSegnati.get(position).contains(numA7.getText().toString())?R.drawable.background_casella_selected_view:R.drawable.background_casella_view);
            numA8.setBackgroundResource(numeriSegnati.get(position).contains(numA8.getText().toString())?R.drawable.background_casella_selected_view:R.drawable.background_casella_view);
            numA9.setBackgroundResource(numeriSegnati.get(position).contains(numA9.getText().toString())?R.drawable.background_casella_selected_view:R.drawable.background_casella_view);
            numB1.setBackgroundResource(numeriSegnati.get(position).contains(numB1.getText().toString())?R.drawable.background_casella_selected_view:R.drawable.background_casella_view);
            numB2.setBackgroundResource(numeriSegnati.get(position).contains(numB2.getText().toString())?R.drawable.background_casella_selected_view:R.drawable.background_casella_view);
            numB3.setBackgroundResource(numeriSegnati.get(position).contains(numB3.getText().toString())?R.drawable.background_casella_selected_view:R.drawable.background_casella_view);
            numB4.setBackgroundResource(numeriSegnati.get(position).contains(numB4.getText().toString())?R.drawable.background_casella_selected_view:R.drawable.background_casella_view);
            numB5.setBackgroundResource(numeriSegnati.get(position).contains(numB5.getText().toString())?R.drawable.background_casella_selected_view:R.drawable.background_casella_view);
            numB6.setBackgroundResource(numeriSegnati.get(position).contains(numB6.getText().toString())?R.drawable.background_casella_selected_view:R.drawable.background_casella_view);
            numB7.setBackgroundResource(numeriSegnati.get(position).contains(numB7.getText().toString())?R.drawable.background_casella_selected_view:R.drawable.background_casella_view);
            numB8.setBackgroundResource(numeriSegnati.get(position).contains(numB8.getText().toString())?R.drawable.background_casella_selected_view:R.drawable.background_casella_view);
            numB9.setBackgroundResource(numeriSegnati.get(position).contains(numB9.getText().toString())?R.drawable.background_casella_selected_view:R.drawable.background_casella_view);
            numC1.setBackgroundResource(numeriSegnati.get(position).contains(numC1.getText().toString())?R.drawable.background_casella_selected_view:R.drawable.background_casella_view);
            numC2.setBackgroundResource(numeriSegnati.get(position).contains(numC2.getText().toString())?R.drawable.background_casella_selected_view:R.drawable.background_casella_view);
            numC3.setBackgroundResource(numeriSegnati.get(position).contains(numC3.getText().toString())?R.drawable.background_casella_selected_view:R.drawable.background_casella_view);
            numC4.setBackgroundResource(numeriSegnati.get(position).contains(numC4.getText().toString())?R.drawable.background_casella_selected_view:R.drawable.background_casella_view);
            numC5.setBackgroundResource(numeriSegnati.get(position).contains(numC5.getText().toString())?R.drawable.background_casella_selected_view:R.drawable.background_casella_view);
            numC6.setBackgroundResource(numeriSegnati.get(position).contains(numC6.getText().toString())?R.drawable.background_casella_selected_view:R.drawable.background_casella_view);
            numC7.setBackgroundResource(numeriSegnati.get(position).contains(numC7.getText().toString())?R.drawable.background_casella_selected_view:R.drawable.background_casella_view);
            numC8.setBackgroundResource(numeriSegnati.get(position).contains(numC8.getText().toString())?R.drawable.background_casella_selected_view:R.drawable.background_casella_view);
            numC9.setBackgroundResource(numeriSegnati.get(position).contains(numC9.getText().toString())?R.drawable.background_casella_selected_view:R.drawable.background_casella_view);

            cancella.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancellaCartella(position);
                }
            });

            numA1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {cartellaOnClickListener(numA1, position);}
            });
            numA2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {cartellaOnClickListener(numA2, position);}
            });
            numA3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {cartellaOnClickListener(numA3, position);}
            });
            numA4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {cartellaOnClickListener(numA4, position);}
            });
            numA5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {cartellaOnClickListener(numA5, position);}
            });
            numA6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {cartellaOnClickListener(numA6, position);}
            });
            numA7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {cartellaOnClickListener(numA7, position);}
            });
            numA8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {cartellaOnClickListener(numA8, position);}
            });
            numA9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {cartellaOnClickListener(numA9, position);}
            });

            numB1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {cartellaOnClickListener(numB1, position);}
            });
            numB2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {cartellaOnClickListener(numB2, position);}
            });
            numB3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {cartellaOnClickListener(numB3, position);}
            });
            numB4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {cartellaOnClickListener(numB4, position);}
            });
            numB5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {cartellaOnClickListener(numB5, position);}
            });
            numB6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {cartellaOnClickListener(numB6, position);}
            });
            numB7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {cartellaOnClickListener(numB7, position);}
            });
            numB8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {cartellaOnClickListener(numB8, position);}
            });
            numB9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {cartellaOnClickListener(numB9, position);}
            });

            numC1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {cartellaOnClickListener(numC1, position);}
            });
            numC2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {cartellaOnClickListener(numC2, position);}
            });
            numC3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {cartellaOnClickListener(numC3, position);}
            });
            numC4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {cartellaOnClickListener(numC4, position);}
            });
            numC5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {cartellaOnClickListener(numC5, position);}
            });
            numC6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {cartellaOnClickListener(numC6, position);}
            });
            numC7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {cartellaOnClickListener(numC7, position);}
            });
            numC8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {cartellaOnClickListener(numC8, position);}
            });
            numC9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {cartellaOnClickListener(numC9, position);}
            });
            return row;
        }

        private void cancellaCartella(int position) {
            positionCancella = position;
            confermaCancellazione.setVisibility(View.VISIBLE);
            resetta.setVisibility(View.GONE);
            messaggioCancellazione.setText(Costanti.MessaggiErrore.CONFERMA_CANCELLAZIONE+(position+1)+"?");
        }

        public void cartellaOnClickListener(MyButton button, int position){
            if(!button.getText().toString().trim().isEmpty()) {
                if (button.getBackgroundId() == R.drawable.background_casella_selected_view ){
                    numeriSegnati.get(position).remove(button.getText().toString());
                    button.setBackgroundResource(R.drawable.background_casella_view);
                }
                else{
                    numeriSegnati.get(position).add(button.getText().toString());
                    button.setBackgroundResource(R.drawable.background_casella_selected_view);
                }
            }
        }
    }

}