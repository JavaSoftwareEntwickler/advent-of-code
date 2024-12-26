package main.java.com.adventofcode.days;

import main.java.com.adventofcode.utils.AdventOfCode;
import main.java.com.adventofcode.utils.IAdventOfCode;

import java.sql.Array;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 extends AdventOfCode implements IAdventOfCode {
int sommaPart1;
int sommaPart2;

    public Day4(String nomeFile) {
        super(nomeFile);
    }

    @Override
    public void stampaRisultatoPartOne() {
        String content = getInputString();
        String[] linee = content.split("\r\n");
        String target = "XMAS";
        String[][] tabella = new String[140][140];

        for(int i = 0; i< 140; i++){
            for (int j =0; j < 140; j++){
                tabella[i][j] = String.valueOf(linee[i].charAt(j));

            }
        }

        for(int i = 0; i< 140; i++){
            for (int j =0; j < 140; j++){
                // Verifica se trovo la parola "XMAS" o "SAMX" in ogni direzione
                if (tabella[i][j].equals("X")) {
                    searchWord(tabella, i, j, target);
                }

            }
        }
        System.out.println(sommaPart1);

    }

    @Override
    public void stampaRisultatoPartTwo() {
        String content = getInputString();
        String[] linee = content.split("\r\n");
        int dimensione = 140;
        String[][] tabella = new String[dimensione][dimensione];

        // Riempire la tabella con i caratteri
        for (int i = 0; i < dimensione; i++) {
            for (int j = 0; j < dimensione; j++) {
                tabella[i][j] = String.valueOf(linee[i].charAt(j));
            }
        }

        // Cerca il pattern di "X-MAS"
        for (int i = 1; i < dimensione-1; i++) {  // Esamina solo le celle interne
            for (int j = 1; j < dimensione-1; j++) {
                String carattere = tabella[i][j];
                if (carattere.equals("A")) {
                    // Ora possiamo fare direttamente la ricerca dei pattern
                    searchPatterns(tabella, i, j);
                }
            }
        }
        System.out.println(sommaPart2);
    }

    // Funzione per cercare una parola nelle direzioni orizzontale, verticale e diagonale
    public void searchWord(String[][] tabella, int startRow, int startCol, String word) {
        int wordLength = word.length();
        int numRows = tabella.length;
        int numCols = tabella[0].length;

        // Orizzontale (destra)
        if (startCol + wordLength <= numCols) {
            boolean match = true;
            for (int i = 0; i < wordLength; i++) {
                if (!tabella[startRow][startCol + i].equals(String.valueOf(word.charAt(i)))) {
                    match = false;
                    break;
                }
            }
            if (match) sommaPart1 += 1;
        }

        // Orizzontale (sinistra)
        if (startCol - wordLength + 1 >= 0){
            boolean match = true;
            for (int i = 0; i < wordLength; i++) {
                if (!tabella[startRow][startCol - i].equals(String.valueOf(word.charAt(i)))) {
                    match = false;
                    break;
                }
            }
            if (match) sommaPart1 += 1;
        }

        // Verticale (giù)
        if (startRow + wordLength <= numRows) {
            boolean match = true;
            for (int i = 0; i < wordLength; i++) {
                if (!tabella[startRow + i][startCol].equals(String.valueOf(word.charAt(i)))) {
                    match = false;
                    break;
                }
            }
            if (match) sommaPart1 += 1;
        }

        // Verticale (su)
        if (startRow - wordLength + 1 >= 0) {
            boolean match = true;
            for (int i = 0; i < wordLength; i++) {
                if (!tabella[startRow - i][startCol].equals(String.valueOf(word.charAt(i)))) {
                    match = false;
                    break;
                }
            }
            if (match) sommaPart1 += 1;
        }

        // Diagonale Destra-giù
        if (startRow + wordLength <= numRows && startCol + wordLength <= numCols) {
            boolean match = true;
            for (int i = 0; i < wordLength; i++) {
                if (!tabella[startRow + i][startCol + i].equals(String.valueOf(word.charAt(i)))) {
                    match = false;
                    break;
                }
            }
            if (match) sommaPart1 += 1;
        }

        // Diagonale Sinistra-giù
        if (startRow + wordLength <= numRows && startCol - wordLength + 1 >= 0) {
            boolean match = true;
            for (int i = 0; i < wordLength; i++) {
                if (!tabella[startRow + i][startCol - i].equals(String.valueOf(word.charAt(i)))) {
                    match = false;
                    break;
                }
            }
            if (match) sommaPart1 += 1;
        }

        // Diagonale Destra-su
        if (startRow - wordLength + 1 >= 0 && startCol + wordLength <= numCols) {
            boolean match = true;
            for (int i = 0; i < wordLength; i++) {
                if (!tabella[startRow - i][startCol + i].equals(String.valueOf(word.charAt(i)))) {
                    match = false;
                    break;
                }
            }
            if (match) sommaPart1 += 1;
        }

        // Diagonale Sinistra-su
        if (startRow - wordLength + 1 >= 0 && startCol - wordLength + 1 >= 0) {
            boolean match = true;
            for (int i = 0; i < wordLength; i++) {
                if (!tabella[startRow - i][startCol - i].equals(String.valueOf(word.charAt(i)))) {
                    match = false;
                    break;
                }
            }
            if (match) sommaPart1 += 1;
        }
    }

    // Metodo per cercare i 4 pattern attorno ad una "A"
    public void searchPatterns(String[][] tabella, int row, int col) {
        String cellaSuperioreSx = tabella[row - 1][col - 1];
        String cellaSuperioreDx = tabella[row - 1][col + 1];
        String cellaInferioreSx = tabella[row + 1][col - 1];
        String cellaInferioreDx = tabella[row + 1][col + 1];
        // Pattern 1:
        // M.M
        // .A.
        // S.S
        if (cellaSuperioreSx.equals("M") &&
            cellaInferioreSx.equals("S") &&
            cellaSuperioreDx.equals("M") &&
            cellaInferioreDx.equals("S")) {
            sommaPart2 += 1;
        }

        // Pattern 2:
        // M.S
        // .A.
        // M.S
        if (cellaSuperioreSx.equals("M") &&
            cellaInferioreSx.equals("M") &&
            cellaSuperioreDx.equals("S") &&
            cellaInferioreDx.equals("S")) {
            sommaPart2 += 1;
        }

        // Pattern 3:
        // S.S
        // .A.
        // M.M
        // supposto che tabella[row][col] = "A"
        if (cellaSuperioreSx.equals("S") &&
                cellaInferioreSx.equals("M") &&
                cellaSuperioreDx.equals("S") &&
                cellaInferioreDx.equals("M")) {
            sommaPart2 += 1;
        }

        // Pattern 4:
        // S.M
        // .A.
        // S.M
        if (cellaSuperioreSx.equals("S") &&
                cellaInferioreSx.equals("S") &&
                cellaSuperioreDx.equals("M") &&
                cellaInferioreDx.equals("M")) {
            sommaPart2 += 1;
        }
    }
}
