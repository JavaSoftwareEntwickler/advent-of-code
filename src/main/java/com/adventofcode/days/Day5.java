package main.java.com.adventofcode.days;

import main.java.com.adventofcode.utils.AdventOfCode;
import main.java.com.adventofcode.utils.IAdventOfCode;

import java.util.ArrayList;
import java.util.List;

public class Day5 extends AdventOfCode implements IAdventOfCode {
int sommaPart1;
int sommaPart2;

    public Day5(String nomeFile) {
        super(nomeFile);
    }

    @Override
    public void stampaRisultatoPartOne() {
        String content = getInputString();
        String[] linee = content.split("\r\n");

        int ordinamentoLengt = 0;
        List<String> aggiornamenti = new ArrayList<>();
        for(int i = 0; i< linee.length; i++){
            String linea = linee[i];
            if("".equals(linea))
                ordinamentoLengt = i;
            if(linea.contains(","))
                aggiornamenti.add(linea);
        }

        String ordinamento[] = new String[ordinamentoLengt];
        for(int i = 0; i < ordinamento.length; i++){
            String linea = linee[i];
            ordinamento[i] = linea;
        }

        for (String agg: aggiornamenti){
            int indicePrima = 0;
            int indiceDopo = 0;
            String[] aggiornamentiSingoli = agg.split(",");
            for(int i = 0; i < ordinamento.length; i++){
                String prima = ordinamento[i].substring(0,2);
                String dopo = ordinamento[i].substring(3);
                if(agg.contains(prima) && agg.contains(dopo)){
                    for(int j = 0 ; j<  aggiornamentiSingoli.length; j++){
                        String aggSing = aggiornamentiSingoli[j];
                        if(aggSing.equals(prima)) indicePrima = j;
                        if(aggSing.equals(dopo)) indiceDopo = j;
                    }
                    // verifico se agg è oridinato correttamente
                    if(indicePrima>indiceDopo){
                        indicePrima = 0;
                        indiceDopo = 0;
                        break;
                    }
                }
            }
            // Se ordinato sommo il centrale
            if(indicePrima<indiceDopo){
                sommaPart1 += Integer.valueOf(aggiornamentiSingoli[aggiornamentiSingoli.length/2]);
            }
        }
        System.out.println(sommaPart1);
    }

    @Override
    public void stampaRisultatoPartTwo() {
        String content = getInputString();
        String[] linee = content.split("\r\n");

        System.out.println(sommaPart2);
    }
}
