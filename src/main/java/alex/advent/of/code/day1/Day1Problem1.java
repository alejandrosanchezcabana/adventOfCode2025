package alex.advent.of.code.day1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import alex.advent.of.code.commons.ProblemsBase;

public class Day1Problem1 implements ProblemsBase {

  private static final String FILENAME = "src/main/java/alex/advent/of/code/day1/day1_input.txt";

  @Override
  public String run() {
    List<String> movements = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
      for (String line; (line = reader.readLine()) != null; ) {
        movements.add(line);
      }
    } catch (FileNotFoundException ex) {
      System.out.println("Missing file :" + FILENAME);
    } catch (IOException ex) {
      System.out.println("Something went wrong");
    }

    int numberOfZeros = getNumberOfZeros(movements);


    return String.valueOf(numberOfZeros);
  }

  private static int getNumberOfZeros(List<String> movements) {
    int numberOfZeros = 0;
    int currentPosition = 50;

    for (String movement : movements) {
      String side = movement.substring(0, 1);
      int clicks = Integer.parseInt(movement.substring(1));

      System.out.println("Current position: " + currentPosition + ", movement: " + movement);

      if (side.equals("R")) {
        currentPosition += clicks;
      } else if (side.equals("L")) {
        currentPosition -= clicks;
      }

      System.out.println("New position: " + currentPosition);

      while (currentPosition > 99 || currentPosition < 0) {
        if (currentPosition > 99) {
          currentPosition -= 100;
        } else {
          currentPosition += 100;
        }
      }

      System.out.println("Adjusted position: " + currentPosition);

      if (currentPosition == 0) {
        numberOfZeros++;
      }
    }
    return numberOfZeros;
  }
}
