package main.java.com.adventofcode.days;

import main.java.com.adventofcode.utils.AdventOfCode;
import main.java.com.adventofcode.utils.IAdventOfCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day1 extends AdventOfCode implements IAdventOfCode {

    public Day1(String nomeFile) {
        super(nomeFile);
    }

    @Override
    public void stampaRisultatoPartOne() {
        String content = getInputString();
        String[] lines = content.split("\r\n");
        Long[] colonna1 = new Long[lines.length];
        Long[] colonna2 = new Long[lines.length];
        for (int i = 0; i < lines.length; i++) {
            String[] numeri = lines[i].split("\\s+");
            colonna1[i] = Long.valueOf(numeri[0]);
            colonna2[i] = Long.valueOf(numeri[1]);
        }
        Arrays.sort(colonna1);
        Arrays.sort(colonna2);
        long distanzaTotale = 0L;
        for (int i = 0; i < colonna1.length; i++) {
            long distanza = Math.abs(colonna2[i] - colonna1[i]);
            distanzaTotale += distanza;
        }
        System.out.println("\nDistanza Totale: " + distanzaTotale);
    }

    @Override
    public void stampaRisultatoPartTwo() {
        String content;
        try {
            content = new String(Files.readAllBytes(Paths.get(super.getPath())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] lines = content.split("\r\n");
        Long[] colonna1 = new Long[lines.length];
        Long[] colonna2 = new Long[lines.length];
        for (int i = 0; i < lines.length; i++) {
            String[] numeri = lines[i].split("\\s+");
            colonna1[i] = Long.valueOf(numeri[0]);
            colonna2[i] = Long.valueOf(numeri[1]);
        }
        Arrays.sort(colonna1);
        Arrays.sort(colonna2);

        long punteggioSimilarita = 0L;
        for (int i = 0; i < colonna1.length; i++) {
            long numeroSinistra = colonna1[i];
            long count = 0;
            for (int j = 0; j < colonna2.length; j++) {
                if (colonna2[j] == numeroSinistra) {
                    count++;
                }
            }
            punteggioSimilarita += numeroSinistra * count;
        }
        System.out.println("\nPunteggio di Similarità: " + punteggioSimilarita);
    }

}
