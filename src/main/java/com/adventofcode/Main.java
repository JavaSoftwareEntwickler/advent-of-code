package main.java.com.adventofcode;
import main.java.com.adventofcode.days.Day1;
import main.java.com.adventofcode.days.Day2;
import main.java.com.adventofcode.days.Day3;
import main.java.com.adventofcode.days.Day4;

public class Main {
    public static void main(String[] args) {

        Day1 day1PartOne = new Day1("day1-1.txt");
        Day1 day1PartTwo = new Day1("day1-2.txt");
        Day2 day2PartOne = new Day2("day2-1.txt");
        Day2 day2PartTwo = new Day2("day2-2.txt");
        Day3 day3PartOne = new Day3("day3-1.txt");
        Day3 day3PartTwo = new Day3("day3-2.txt");
        Day4 day4PartOne = new Day4("day4-1.txt");
        Day4 day4PartTwo = new Day4("day4-2.txt");
       // Day4 day4PartTwoTest = new Day4("day4-2_test.txt");

        day1PartOne.stampaRisultatoPartOne();
        day1PartTwo.stampaRisultatoPartTwo();

        day2PartOne.stampaRisultatoPartOne();
        day2PartTwo.stampaRisultatoPartTwo();

        day3PartOne.stampaRisultatoPartOne();
        day3PartTwo.stampaRisultatoPartTwo();

        day4PartOne.stampaRisultatoPartOne();
        //day4PartTwoTest.stampaRisultatoPartTwo();
        day4PartTwo.stampaRisultatoPartTwo();
    }
}