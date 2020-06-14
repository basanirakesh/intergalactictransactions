package com.brsolutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InterGalacticTransaction {
    // Map to hold Roman symbols and respective values
    private static Map<Character, Integer> romanSymbols = defineRomanSymbols();
    // List to hold question sentences like, how much, how many etc.
    private static List<String> questionStrings = defineQuestionStrs();
    private static String defaultOutput = "I have no idea what you are talking about";

    /**
     * Method to define input lines
     *
     * @return
     */
    private static List<String> defineInput() {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("glob is I");
        inputLines.add("prok is V");
        inputLines.add("pish is X");
        inputLines.add("tegj is L");
        inputLines.add("glob glob Silver is 34");
        inputLines.add("Credits glob prok Gold is 57800");
        inputLines.add("Credits pish pish Iron is 3910");
        inputLines.add("Credits how much is pish tegj glob glob ?");
        inputLines.add("how many Credits is glob prok Silver?");
        inputLines.add("how many Credits is glob prok Gold ?");
        inputLines.add("how many Credits is glob prok Iron ?");
        inputLines.add("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
        return inputLines;
    }

    /**
     * Program starts from here
     *
     * @param args
     */
    public static void main(String args[]) {
        List<String> inputLines = defineInput();
        List<String> outputLines = new ArrayList<>();
        // Map to hold input string for Roman Symbol and respective Roman symbol
        Map<String, String> romanStrings = new HashMap<>();
        // Map to hold Unit and respective value
        Map<String, Long> units = new HashMap<>();

        for (String line : inputLines) {
            // Splitting input line based on word "is"
            String[] lineChunks = line.split(" is ");
            if (lineChunks.length > 1) {
                // Case 1: if right side word of "is" a Roman symbol, then adding left and right side words to a Map
                if (romanSymbols.keySet().contains(lineChunks[1].charAt(0))) {
                    romanStrings.put(lineChunks[0], lineChunks[1]);
                } else if (isNumber(lineChunks[1])) { // Case 2: if right side word of "is" is a number, then determining the Unit value
                    // Value given in input line
                    int inputValue = Integer.parseInt(lineChunks[1]);
                    // Splitting left side string of "is" into words so that Unit value can be calculated
                    String[] words = lineChunks[0].split(" ");
                    String unit = "";
                    StringBuilder romanNumber = new StringBuilder();
                    // Looping through each word and separating Roman strings and Units
                    for (String word : words) {
                        if (romanStrings.containsKey(word)) {
                            romanNumber.append(romanStrings.get(word));
                        } else {
                            unit = word;
                        }
                    }
                    // Determining the Unit value - divide input value by a number which is equivalent of Roman strings given in input
                    long unitValue = inputValue / romanToNumber(romanNumber.toString());
                    // Storing the Unit and respective value in to pre-defined Map
                    units.put(unit, unitValue);
                } else if (isQuestion(lineChunks[0])) { // Case 3: If left side string of word "is" is a question
                    // Splitting right side string of "is" into words so that output value can be calculated
                    String[] words = lineChunks[1].split(" ");
                    StringBuilder romanNumber = new StringBuilder();
                    // Question may or may not contain Units. To handle this, initializing unitValue by 1
                    long unitValue = 1;
                    // Looping through each word and separating Roman strings and Units
                    for (String word : words) {
                        if (romanStrings.containsKey(word)) {
                            romanNumber.append(romanStrings.get(word));
                        } else if (word.trim().equals("?") == false) { // Ignoring ? symbol in question
                            // Units may end with question marks, so removing them.
                            unitValue = units.get(word.replace("?", ""));
                        }
                    }
                    // Determining the output value - multiply unit value by a number which is equivalent of Roman strings given in input
                    long outputValue = romanToNumber(romanNumber.toString()) * unitValue;
                    // Defining output string to be printed on console
                    outputLines.add(lineChunks[1].replace("?", "").trim() + " is " + outputValue);
                } else {
                    // Adding default output string in other cases
                    outputLines.add(defaultOutput);
                }
            } else {
                // Adding default output string in other cases
                outputLines.add(defaultOutput);
            }
        }
        // Printing the output lines on console
        printOutput(outputLines);
    }

    /**
     * Method to convert Roman string to number
     * @param str
     * @return
     */
    private static long romanToNumber(String str) {
        // Initialize result
        long result = 0;

        for (int i = 0; i < str.length(); i++) {
            // Getting value of symbol s[i]
            int s1 = romanSymbols.get(str.charAt(i));

            if (i + 1 < str.length()) {
                int s2 = romanSymbols.get(str.charAt(i + 1));

                // Comparing both values
                if (s1 >= s2) {
                    // Value of current symbol is greater or equal to the next symbol
                    result = result + s1;
                } else {
                    // Value of current symbol is less than the next symbol
                    result = result + s2 - s1;
                    i++;
                }
            } else {
                result = result + s1;
            }
        }
        return result;
    }


    /**
     * Utility method to define Roman symbols
     *
     * @return
     */
    private static Map<Character, Integer> defineRomanSymbols() {
        Map<Character, Integer> romanSymbols = new HashMap<>();
        romanSymbols.put('I', 1);
        romanSymbols.put('V', 5);
        romanSymbols.put('X', 10);
        romanSymbols.put('L', 50);
        romanSymbols.put('C', 100);
        romanSymbols.put('D', 500);
        romanSymbols.put('M', 1000);
        return romanSymbols;
    }

    /**
     * Utility method to print the output on console
     *
     * @param outputLines
     */
    private static void printOutput(List<String> outputLines) {
        for (String output : outputLines) {
            System.out.println(output);
        }
    }

    /**
     * Utility method to check if the given string is a number of not
     *
     * @param str
     * @return
     */
    private static boolean isNumber(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Utility method to check if the given string has any question related words
     *
     * @param str
     * @return
     */
    private static boolean isQuestion(String str) {
        for (String questionStr : questionStrings) {
            if (str.contains(questionStr)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Utility method to define question strings
     *
     * @return
     */
    private static List<String> defineQuestionStrs() {
        List<String> questionStrs = new ArrayList<>();
        questionStrs.add("how much");
        questionStrs.add("How much");
        questionStrs.add("how many");
        questionStrs.add("How many");
        return questionStrs;
    }
}
