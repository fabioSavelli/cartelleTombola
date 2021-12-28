package com.example.cartellatombola.utility;

import com.example.cartellatombola.model.Cartella;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SaveUtility {

    public static Cartella stringToCartella(String stringa){
        String riga1 = getTagValues(stringa, Costanti.Save.TagNameCartelle.RIGA1).get(0).trim();
        String riga2 = getTagValues(stringa, Costanti.Save.TagNameCartelle.RIGA2).get(0).trim();
        String riga3 = getTagValues(stringa, Costanti.Save.TagNameCartelle.RIGA3).get(0).trim();

        String[] riga1Array = riga1.split(",");
        String[] riga2Array = riga2.split(",");
        String[] riga3Array = riga3.split(",");

        int[] riga1int = new int[9];
        int[] riga2int = new int[9];
        int[] riga3int = new int[9];

        int i=0;
        for(String s : riga1Array){
            riga1int[i] = Integer.parseInt(s.trim());
            i++;
        }
        i=0;
        for(String s : riga2Array){
            riga2int[i] = Integer.parseInt(s.trim());
            i++;
        }
        i=0;
        for(String s : riga3Array){
            riga3int[i] = Integer.parseInt(s.trim());
            i++;
        }

        Cartella cartella = new Cartella();
        cartella.setRiga1(riga1int);
        cartella.setRiga2(riga2int);
        cartella.setRiga3(riga3int);
        return cartella;
    }

    public static String cartellaToString(Cartella cartella) {
        String cartellaString = "";
        if (cartella != null) {
            cartellaString = "<" + Costanti.Save.TagNameCartelle.CARTELLA + ">\n";
            cartellaString += "    <" + Costanti.Save.TagNameCartelle.RIGA1 + ">\n        ";
            boolean first = true;
            for (int i : cartella.getRiga1()) {
                if(!first)
                    cartellaString += ", "+i;
                else
                    cartellaString += ""+i;
                first = false;
            }
            cartellaString += "\n    </" + Costanti.Save.TagNameCartelle.RIGA1 + ">\n";

            cartellaString += "    <" + Costanti.Save.TagNameCartelle.RIGA2 + ">\n        ";
            first = true;
            for (int i : cartella.getRiga2()) {
                if(!first)
                    cartellaString += ", "+i;
                else
                    cartellaString += ""+i;
                first = false;
            }
            cartellaString += "\n    </" + Costanti.Save.TagNameCartelle.RIGA2 + ">\n";

            cartellaString += "    <" + Costanti.Save.TagNameCartelle.RIGA3 + ">\n        ";
            first = true;
            for (int i : cartella.getRiga3()) {
                if(!first)
                    cartellaString += ", "+i;
                else
                    cartellaString += ""+i;
                first = false;
            }
            cartellaString += "\n    </" + Costanti.Save.TagNameCartelle.RIGA3 + ">\n";


            cartellaString += "</" + Costanti.Save.TagNameCartelle.CARTELLA + ">\n";
        }
        return cartellaString;
    }

    public static String cartelleToString(List<Cartella> cartelle) {
        String ret = "";
        if(cartelle!=null){
            for(Cartella c : cartelle){
                ret += cartellaToString(c);
            }
        }
        return ret;
    }

    public static List<Cartella> getCartelleSalvateFromString(String stringCartelle){
        List<Cartella> ret = new ArrayList<>();
        if(stringCartelle==null)
            return ret;

        List<String> listaCartelle = getTagValues(stringCartelle, Costanti.Save.TagNameCartelle.CARTELLA);
        for(String s : listaCartelle){
            Cartella c = stringToCartella(s);
            ret.add(c);
        }
        return ret;
    }


    public static List<String> getTagValues(final String str, final String tagName) {

        final Pattern TAG_REGEX = Pattern.compile("<"+tagName+">(.+?)</"+tagName+">", Pattern.DOTALL);
        final List<String> tagValues = new ArrayList<String>();
        final Matcher matcher = TAG_REGEX.matcher(str);
        while (matcher.find()) {
            tagValues.add(matcher.group(1));
        }
        return tagValues;
    }

    public static String getInTag(final String str, final String tagName){
        return "<" + tagName + ">" + str + "</" + tagName + ">\n";
    }
}
