package main.java.com.adventofcode.days;

import main.java.com.adventofcode.utils.AdventOfCode;
import main.java.com.adventofcode.utils.IAdventOfCode;

public class Day2 extends AdventOfCode implements IAdventOfCode {
    public Day2(String nomeFile) {
        super(nomeFile);
    }

    @Override
    public void stampaRisultatoPartOne() {
        String content = getInputString();
        String[] lines = content.split("\r\n");
        int reportSicuri = 0;
        for (String line : lines) {
            String[] parts = line.split("\\s+");
            int[] report = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                report[i] = Integer.parseInt(parts[i]);
            }
            if (isSicuro(report)) {
                reportSicuri++;
            }
        }
        System.out.println("Numero di report sicuri: " + reportSicuri);
    }
    private boolean isSicuro(int[] report) {
        boolean eInAumento = report[0] < report[1];
        boolean eIndiminuzione = report[0] > report[1]; 
        boolean eValido = true;

        for (int i = 1; i < report.length; i++) {
            int diff = Math.abs(report[i] - report[i - 1]);
            if (diff < 1 || diff > 3) {
                eValido = false;
                break;
            }
            if (eInAumento && report[i] < report[i - 1]) {
                eValido = false;
                break;
            }
            if (eIndiminuzione && report[i] > report[i - 1]) {
                eValido = false;
                break;
            }
        }
        return eValido;
    }

    @Override
    public void stampaRisultatoPartTwo() {
        String content = getInputString();
        String[] lines = content.split("\r\n");
        int reportSicuri = 0;
        for (String line : lines) {
            String[] parts = line.split("\\s+");
            int[] report = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                report[i] = Integer.parseInt(parts[i]);
            }
            if (isSicuro(report)) {
                reportSicuri++;
            } else if (eSicuroDopoRimozioneDiUno(report)) {
                reportSicuri++;
            }
        }
        System.out.println("Numero di report sicuri: " + reportSicuri);
    }

    private boolean eSicuroDopoRimozioneDiUno(int[] report) {
        for (int i = 0; i < report.length; i++) {
            int[] modifiedReport = new int[report.length - 1];
            int index = 0;
            for (int j = 0; j < report.length; j++) {
                if (j != i) {
                    modifiedReport[index++] = report[j];
                }
            }
            if (isSicuro(modifiedReport)) {
                return true;
            }
        }
        return false;
    }
}
