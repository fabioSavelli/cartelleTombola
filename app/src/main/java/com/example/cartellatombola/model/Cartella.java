package com.example.cartellatombola.model;

import java.util.Arrays;

public class Cartella {

    private int[] riga1;
    private int[] riga2;
    private int[] riga3;


    public Cartella() {
        this.riga1 = new int[9];
        this.riga2 = new int[9];
        this.riga3 = new int[9];
    }

    public int[] getRiga1() {
        return riga1;
    }

    public void setRiga1(int[] riga1) {
        this.riga1 = riga1;
    }

    public int[] getRiga2() {
        return riga2;
    }

    public void setRiga2(int[] riga2) {
        this.riga2 = riga2;
    }

    public int[] getRiga3() {
        return riga3;
    }

    public void setRiga3(int[] riga3) {
        this.riga3 = riga3;
    }

    @Override
    public String toString() {
        return "Cartella:" +
                "\n    riga1 = " + Arrays.toString(riga1) +
                "\n    riga2 = " + Arrays.toString(riga2) +
                "\n    riga3 = " + Arrays.toString(riga3);
    }
}
