// java
package com.alex.advent.of.code.day4;

import com.alex.advent.of.code.commons.ProblemsBase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day4Problem1 implements ProblemsBase {

  private static final String FILENAME = "src/main/java/com/alex/advent/of/code/day4/day4_input.txt";

  @Override
  public String run() {
    List<String> lines = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
      String line;
      while ((line = reader.readLine()) != null) {
        line = line.trim();
        if (line.isEmpty()) continue;
        lines.add(line);
      }
    } catch (FileNotFoundException ex) {
      System.out.println("Missing file :" + FILENAME);
      return "0";
    } catch (IOException ex) {
      System.out.println("Something went wrong");
      return "0";
    }

    if (lines.isEmpty()) {
      return "0";
    }

    int rows = lines.size();
    int cols = 0;
    for (String l : lines) {
      if (l.length() > cols) cols = l.length();
    }

    String[][] grid = new String[rows][cols];

    for (int i = 0; i < rows; i++) {
      String l = lines.get(i);
      for (int j = 0; j < cols; j++) {
        char ch = j < l.length() ? l.charAt(j) : '.';
        grid[i][j] = String.valueOf(ch);
      }
    }

    int countLessThanFour = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if ("@".equals(grid[i][j])) {
          int adj = countAdjacentAt(grid, i, j, rows, cols);
          if (adj < 4) {
            countLessThanFour++;
          }
        }
      }
    }

    return String.valueOf(countLessThanFour);
  }

  private int countAdjacentAt(String[][] grid, int i, int j, int rows, int cols) {
    int cnt = 0;
    for (int di = -1; di <= 1; di++) {
      for (int dj = -1; dj <= 1; dj++) {
        if (di == 0 && dj == 0) {
          continue;
        }
        int ni = i + di;
        int nj = j + dj;
        if (ni >= 0 && ni < rows && nj >= 0 && nj < cols) {
          if ("@".equals(grid[ni][nj])) cnt++;
        }
      }
    }
    return cnt;
  }
}