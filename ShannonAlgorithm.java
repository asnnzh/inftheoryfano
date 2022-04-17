package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShannonAlgorithm {
    private char character;
    private double probability;
    private int quantity;
    private String code;
    private int length;
    private boolean coded;

    public ShannonAlgorithm(){
    }
    public ShannonAlgorithm(char character) {

        this.character = character;
        this.quantity = 1;
        this.code = "";

    }
    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void incQuantity() {
        this.quantity++;
    }

    public boolean isCoded() {
        return coded;
    }

    public void setCoded(boolean coded) {
        this.coded = coded;
    }

    static ShannonAlgorithm contain(List<ShannonAlgorithm> list, Character character) {
        for (ShannonAlgorithm s : list) {
            if (s.getCharacter() == character)
                return s;
        }
        return null;
    }

    static void codeSymbol(List<ShannonAlgorithm> list, double milestone) {
        if (list.size() == 1) {
            list.get(0).setCoded(true);
            return;
        }
        double sum = 0.0;
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).isCoded()) {
                if (sum < milestone) {
                    sum += list.get(i).getProbability();
                } else {
                    List<ShannonAlgorithm> list1 = new ArrayList<>();
                    List<ShannonAlgorithm> list2 = new ArrayList<>();
                    for (int x = 0; x < i; x++) {
                        list.get(x).setCode(list.get(x).getCode() + "1");
                        list1.add(list.get(x));
                    }
                    for (int y = i; y < list.size(); y++) {
                        list.get(y).setCode(list.get(y).getCode() + "0");
                        list2.add(list.get(y));
                    }
                    delegate(list1);
                    delegate(list2);
                }}}}

    private static void delegate(List<ShannonAlgorithm> list) {
        if (list.size() != 0) {
            double sum = 0f;
            for (ShannonAlgorithm s : list) {
                sum += s.getProbability();
            }
            codeSymbol(list, sum / 2);
        }
    }

    static String encode(String text, List<ShannonAlgorithm> symbolList) {
        Scanner scanner = new Scanner(text);
        String encodedText = "";

        while (scanner.hasNextLine()) {
            String word = scanner.nextLine();
            if (scanner.hasNextLine())
                word += '\n';

            char[] array = word.toCharArray();
            for (char c : array) {
                ShannonAlgorithm s = contain(symbolList, c);
                encodedText += s.getCode();
            }
        }
        return encodedText;
    }
}


