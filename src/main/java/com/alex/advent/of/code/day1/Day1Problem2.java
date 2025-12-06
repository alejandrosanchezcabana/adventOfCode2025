package com.alex.advent.of.code.day1;

import com.alex.advent.of.code.commons.ProblemsBase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day1Problem2 implements ProblemsBase {

  private static final String FILENAME = "src/main/java/alex/advent/of/code/day1/day1_input.txt";

  @Override
  public String run() {
    List<String> movements = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
      for (String line; (line = reader.readLine()) != null; ) {
        movements.add(line.trim());
      }
    } catch (FileNotFoundException ex) {
      System.out.println("Missing file :" + FILENAME);
    } catch (IOException ex) {
      System.out.println("Something went wrong");
    }

    int numberOfZeros = GetNumberOfZeros(movements);

    return String.valueOf(numberOfZeros);
  }

  private static int GetNumberOfZeros(List<String> movements) {
    int numberOfZeros = 0;
    int currentPosition = 50;

    for (String movement : movements) {
      String side = movement.substring(0, 1);
      int clicks = Integer.parseInt(movement.substring(1));

      if (side.equals("R")) {
        int countToZero = 100 - currentPosition;
        if (clicks >= countToZero) {
          numberOfZeros += ((clicks - countToZero) / 100) + 1;
        }
        currentPosition = (currentPosition + clicks) % 100;
      } else if (side.equals("L")) {
        int countToZero = currentPosition;
        if (currentPosition == 0) {
          countToZero = 100;
        }
        if (clicks >= countToZero) {
          numberOfZeros += ((clicks - countToZero) / 100) + 1;
        }
        currentPosition = ((currentPosition - clicks) % 100 + 100) % 100;
      }
    }
    return numberOfZeros;
  }
}
