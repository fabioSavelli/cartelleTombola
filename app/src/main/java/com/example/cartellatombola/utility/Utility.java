package com.example.cartellatombola.utility;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cartellatombola.R;
import com.example.cartellatombola.model.Cartella;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Utility {

    public static boolean controllaCartella(Cartella cartella){
        //Controllo numeri
        for(int i : cartella.getRiga1()){
            if(i!=0 && (i>90 || i<0))
                return false;
        }
        for(int i : cartella.getRiga2()){
            if(i!=0 && (i>90 || i<0))
                return false;
        }
        for(int i : cartella.getRiga3()){
            if(i!=0 && (i>90 || i<0))
                return false;
        }

        //Controllo elementi per riga
        List<Integer> elementi = new ArrayList<>();
        int numeroElementiRiga1 = 0;
        int numeroElementiRiga2 = 0;
        int numeroElementiRiga3 = 0;
        for(int i : cartella.getRiga1()){
            if(i!=0){
                elementi.add(i);
                numeroElementiRiga1++;
            }
        }
        for(int i : cartella.getRiga2()){
            if(i!=0){
                elementi.add(i);
                numeroElementiRiga2++;
            }
        }
        for(int i : cartella.getRiga3()){
            if(i!=0){
                elementi.add(i);
                numeroElementiRiga3++;
            }
        }

        //Controllo elementi per colonna
        boolean controlloColonna = true;
        for(int i=0; i<9; i++){
            if( !(i==8 && cartella.getRiga1()[i]==90) && cartella.getRiga1()[i]!=0 ){
                if( !(cartella.getRiga1()[i]-(i*10) <= 9 &&  cartella.getRiga1()[i]-(i*10) >= 0 )){
                    controlloColonna = false;
                    System.out.println("riga1 "+i);
                    break;
                }
            }

            if( !(i==8 && cartella.getRiga2()[i]==90) && cartella.getRiga2()[i]!=0){
                if( !(cartella.getRiga2()[i]-(i*10) <= 9 &&  cartella.getRiga2()[i]-(i*10) >= 0 )){
                    controlloColonna = false;
                    System.out.println("riga2 "+i);
                    break;
                }
            }

            if( !(i==8 && cartella.getRiga3()[i]==90) && cartella.getRiga3()[i]!=0){
                if(!(cartella.getRiga3()[i]-(i*10) <= 9 &&  cartella.getRiga3()[i]-(i*10) >= 0 )){
                    controlloColonna = false;
                    System.out.println("riga3 "+i);
                    break;
                }
            }
        }

        //Controllo duplicati
        boolean controlloNumeroElementi = numeroElementiRiga1==5 && numeroElementiRiga2==5 && numeroElementiRiga3==5;
        Set<Integer> set = new HashSet<Integer>(elementi);
        boolean controlloElementiUguali = set.size() == elementi.size();

        return controlloElementiUguali && controlloColonna && controlloNumeroElementi;
    }

    public static Cartella generaCartellaVuota(){
        return new Cartella();
    }

    public static Cartella generaCartellaCasuale(){
        Cartella ret = new Cartella();
        List<Integer> riga1 = new ArrayList<>();
        List<Integer> riga2 = new ArrayList<>();
        List<Integer> riga3 = new ArrayList<>();
        List<Integer> numeriRiga1 = new ArrayList<>();
        List<Integer> numeriRiga2 = new ArrayList<>();
        List<Integer> numeriRiga3 = new ArrayList<>();

        for(int i=1; i<=90; i++){
            numeriRiga1.add(i);
        }
        Random rand = new Random();
        for(int i=0; i<5; i++) {
            int indice = rand.nextInt(numeriRiga1.size());
            int randomElement = numeriRiga1.get(indice);
            riga1.add(randomElement);
            if(randomElement==90)
                numeriRiga1 = rimuoviDecine(numeriRiga1, 8);
            else
                numeriRiga1 = rimuoviDecine(numeriRiga1, randomElement/10);
        }

        for(int i=1; i<=90; i++){
            if(!riga1.contains(i))
                numeriRiga2.add(i);
        }
        for(int i=0; i<5; i++) {
            int indice = rand.nextInt(numeriRiga2.size());
            int randomElement = numeriRiga2.get(indice);
            riga2.add(randomElement);
            if(randomElement==90)
                numeriRiga2 = rimuoviDecine(numeriRiga2, 8);
            else
                numeriRiga2 = rimuoviDecine(numeriRiga2, randomElement/10);
        }

        for(int i=1; i<=90; i++){
            if(!riga1.contains(i) && !riga2.contains(i))
                numeriRiga3.add(i);
        }
        for(int i=0; i<5; i++) {
            int indice = rand.nextInt(numeriRiga3.size());
            int randomElement = numeriRiga3.get(indice);
            riga3.add(randomElement);
            if(randomElement==90)
                numeriRiga3 = rimuoviDecine(numeriRiga3, 8);
            else
                numeriRiga3 = rimuoviDecine(numeriRiga3, randomElement/10);
        }

        for(int i : riga1){
            int indice = i/10;
            if(i==90)
                indice = 8;
            ret.getRiga1()[indice] = i;
        }
        for(int i : riga2){
            int indice = i/10;
            if(i==90)
                indice = 8;
            ret.getRiga2()[indice] = i;
        }
        for(int i : riga3){
            int indice = i/10;
            if(i==90)
                indice = 8;
            ret.getRiga3()[indice] = i;
        }

        return ret;
    }

    private static List<Integer> rimuoviDecine(List<Integer> lista, int decina){
        List<Integer> newList = new ArrayList<>();
        for(int i : lista){
            int decinaLista = i/10;
            if(i==90)
                decinaLista = 8;
            if(decinaLista != decina)
                newList.add(i);
        }
        return newList;
    }

    public static List<List<TextView>> riempiCartella(Cartella cartella, List<List<TextView>> grigliaCartella){
        int i=0;
        for(int num : cartella.getRiga1()){
            if(num==0)
                grigliaCartella.get(0).get(i).setText("");
            else
                grigliaCartella.get(0).get(i).setText(""+num);
            i++;
        }

        i=0;
        for(int num : cartella.getRiga2()){
            if(num==0)
                grigliaCartella.get(1).get(i).setText("");
            else
                grigliaCartella.get(1).get(i).setText(""+num);
            i++;
        }

        i=0;
        for(int num : cartella.getRiga3()){
            if(num==0)
                grigliaCartella.get(2).get(i).setText("");
            else
                grigliaCartella.get(2).get(i).setText(""+num);
            i++;
        }

        return grigliaCartella;
    }

    public static Cartella generaCartella(List<List<TextView>> grigliaCartella){
        Cartella cartella = new Cartella();

        int i=0;
        for (List<TextView> riga : grigliaCartella){
            int j=0;
            for (TextView elem : riga){
                if(i==0)
                    cartella.getRiga1()[j] = Integer.parseInt("0"+elem.getText());
                else if(i==1)
                    cartella.getRiga2()[j] = Integer.parseInt("0"+elem.getText());
                else
                    cartella.getRiga3()[j] = Integer.parseInt("0"+elem.getText());
                j++;
            }
            i++;
        }
        return cartella;
    }
}
