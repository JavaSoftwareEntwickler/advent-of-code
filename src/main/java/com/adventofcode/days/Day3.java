package main.java.com.adventofcode.days;

import main.java.com.adventofcode.utils.AdventOfCode;
import main.java.com.adventofcode.utils.IAdventOfCode;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 extends AdventOfCode implements IAdventOfCode {
    public Day3(String nomeFile) {
        super(nomeFile);
    }

    @Override
    public void stampaRisultatoPartOne() {
        String content = getInputString();
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        Matcher matcher = pattern.matcher(content);
        int somma = 0;
        while(matcher.find()){
            int primo = Integer.parseInt(matcher.group(1));
            int secondo = Integer.parseInt(matcher.group(2));
            somma += primo * secondo;
        }
        System.out.println(somma);

    }

    @Override
    public void stampaRisultatoPartTwo() {
        String content = getInputString();
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        Matcher matcher = pattern.matcher(content);
        int somma = 0;
        int i = 0;
        boolean isAbilitataMoltiplicazione = true;
        while(i < content.length()){
            if(content.startsWith("do()", i)){
                isAbilitataMoltiplicazione = true;
                i += 4;
            }
            else if(content.startsWith("don't()", i)){
                isAbilitataMoltiplicazione = false;
                i += 7;
            }
            else if(matcher.find(i) && matcher.start() == i){
                if(isAbilitataMoltiplicazione){
                    int primo = Integer.parseInt(matcher.group(1));
                    int secondo = Integer.parseInt(matcher.group(2));
                    somma += primo * secondo;
                }
                i = matcher.end();
            }
            else{
                i++;
            }
        }
        System.out.println(somma);
    }
}
