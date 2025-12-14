package com.alex.advent.of.code.day3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.alex.advent.of.code.commons.ProblemsBase;

public class Day3Problem1 implements ProblemsBase {

    private static final String FILENAME = "src/main/java/com/alex/advent/of/code/day3/day3_input.txt";

    @Override
    public String run() {
        long totalOutput = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                int maxForBank = 0;
                int lineLength = line.length();
                for (int i = 0; i < lineLength - 1; i++) {
                    int tens = Character.getNumericValue(line.charAt(i));
                    for (int j = i + 1; j < lineLength; j++) {
                        int units = Character.getNumericValue(line.charAt(j));
                        int value = tens * 10 + units;
                        if (value > maxForBank) {
                            maxForBank = value;
                        }
                    }
                }

                System.out.println("Bank " + line + " -> max " + maxForBank);
                totalOutput += maxForBank;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Missing file :" + FILENAME);
        } catch (IOException ex) {
            System.out.println("Something went wrong");
        }

        return String.valueOf(totalOutput);
    }
}