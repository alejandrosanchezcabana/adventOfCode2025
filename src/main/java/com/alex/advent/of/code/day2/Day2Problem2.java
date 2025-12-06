package com.alex.advent.of.code.day2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alex.advent.of.code.commons.ProblemsBase;

public class Day2Problem2 implements ProblemsBase {

  private static final String FILENAME = "src/main/java/com/alex/advent/of/code/day2/day2_input.txt";

  @Override
  public String run() {
    List<String> ranges = new ArrayList<>();
    long sumOfAllInvalidIDs = 0;

    try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
      for (String line; (line = reader.readLine()) != null;) {
        String[] parts = line.split(",");
        for (String part : parts) {
          ranges.add(part);
        }
      }
    } catch (FileNotFoundException ex) {
      System.out.println("Missing file :" + FILENAME);
    } catch (IOException ex) {
      System.out.println("Something went wrong");
    }

    for (String range : ranges) {
      String[] bounds = range.split("-");
      long lowerBound = Long.parseLong(bounds[0]);
      long upperBound = Long.parseLong(bounds[1]);

      for (long id = lowerBound; id <= upperBound; id++) {
        // sum all the id that are just a sequence of numbers repited (for example
        // 123123)
        String idStr = String.valueOf(id);

        int stringLength = idStr.length();
        int minSectionLenght = 1;
        int maxSectionLenght = stringLength / 2;

        for (int sectionLength = minSectionLenght; sectionLength <= maxSectionLenght; sectionLength++) {
          String part1 = idStr.substring(0, sectionLength);
          StringBuilder sb = new StringBuilder();
          int repetitions = stringLength / sectionLength;
          for (int r = 0; r < repetitions; r++) {
            sb.append(part1);
          }
          if (sb.toString().equals(idStr)) {
            sumOfAllInvalidIDs += id;
            break;
          }
        }
      }
    }

    return String.valueOf(sumOfAllInvalidIDs);
  }
}
