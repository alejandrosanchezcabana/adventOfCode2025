// java
package com.alex.advent.of.code.day3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

import com.alex.advent.of.code.commons.ProblemsBase;

public class Day3Problem2 implements ProblemsBase {

  private static final String FILENAME = "src/main/java/com/alex/advent/of/code/day3/day3_input.txt";
  private static final int PICK = 12;

  @Override
  public String run() {
    BigInteger total = BigInteger.ZERO;

    try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
      String line;
      while ((line = reader.readLine()) != null) {
        line = line.trim();
        if (line.isEmpty()) continue;

        String best = maxSubsequence(line);
        System.out.println("Bank " + line + " -> max " + best);
        total = total.add(new BigInteger(best));
      }
    } catch (FileNotFoundException ex) {
      System.out.println("Missing file :" + FILENAME);
    } catch (IOException ex) {
      System.out.println("Something went wrong");
    }

    return total.toString();
  }

  private String maxSubsequence(String subsequence) {
    int subsequenceLength = subsequence.length();
    if (subsequenceLength <= PICK) return subsequence;

    StringBuilder stack = new StringBuilder();
    int toRemove = subsequenceLength - PICK;

    for (int i = 0; i < subsequenceLength; i++) {
      char c = subsequence.charAt(i);
      while (toRemove > 0 && !stack.isEmpty() && stack.charAt(stack.length() - 1) < c) {
        stack.deleteCharAt(stack.length() - 1);
        toRemove--;
      }
      stack.append(c);
    }

    // If still too long, truncate to 12
    if (stack.length() > PICK) {
      return stack.substring(0, PICK);
    }
    return stack.toString();
  }
}
