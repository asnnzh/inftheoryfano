package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<ShannonAlgorithm> symbols = new ArrayList<>();
        ShannonAlgorithm shannonAlgorithm = new ShannonAlgorithm();
        int counter = 0;

        String savetext = "";

        Scanner scanner = null;

        try {
            scanner = new Scanner(new File("text.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
        while (scanner.hasNextLine()) {
            String words = scanner.nextLine();
            if (scanner.hasNextLine())
                words += '\n';
            savetext += words;
            char[] arr = words.toCharArray();
            for (char c : arr) {
                counter++;
                ShannonAlgorithm s = shannonAlgorithm.contain(symbols, c);
                if (s != null) {
                    s.incQuantity();
                } else {
                    symbols.add(new ShannonAlgorithm(c));
                }
            }
        }

        int finalcounter = counter;
        symbols.forEach(s -> s.setProbability((double) s.getQuantity() / finalcounter));
        System.out.println("||Character  ||       Probability        ||   Code      ||");
        System.out.print("-----------------------------------------------------------");
        symbols.sort(Comparator.comparingDouble(ShannonAlgorithm::getProbability).reversed());
        System.out.println();
        shannonAlgorithm.codeSymbol(symbols, 0.5f);

        symbols.forEach(s ->System.out.println("|| " + s.getCharacter() + "         ||     " + s.getProbability()  + "  ||   " + s.getCode()));
        try {
            PrintWriter writer = new PrintWriter("encodedText.txt", "UTF-8");
            String text = shannonAlgorithm.encode(savetext, symbols);
            System.out.println("Result of encoded text: \n" + text + '\n');
            writer.print(text);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
