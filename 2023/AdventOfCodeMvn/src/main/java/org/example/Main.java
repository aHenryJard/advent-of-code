package org.example;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        day1Twoo();
    }

    static void day1() throws IOException {
        Path path = Paths.get("C:\\Users\\AngeliqueJard\\IdeaProjects\\AdventOfCodeMvn\\src\\main\\resources\\01-input.txt");
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

        int theSum = 0;
        for(int i=0; i<lines.size(); i++) {
            //System.out.println("" + lines.get(i));
            String rawLine = lines.get(i);

            String preprocess = lines.get(i).replaceAll("one", "1");
            preprocess = preprocess.replaceAll("two", "2");
            preprocess = preprocess.replaceAll("three", "3");
            preprocess = preprocess.replaceAll("four", "4");
            preprocess = preprocess.replaceAll("five", "5");
            preprocess = preprocess.replaceAll("six", "6");
            preprocess = preprocess.replaceAll("seven", "7");
            preprocess = preprocess.replaceAll("eight", "8");
            preprocess = preprocess.replaceAll("nine", "9");

            String numberOnly= preprocess.replaceAll("[^0-9]", "");

            if(numberOnly.length() > 1) {
                int theNumber = Integer.parseInt(numberOnly.substring(0,1)+""+numberOnly.substring(numberOnly.length()-1,numberOnly.length()));
                System.out.println("" + lines.get(i) + "=> " + preprocess +  "=> " +theNumber);
                theSum = theSum + theNumber;
            } else {
                int theDigit = Integer.parseInt(numberOnly.substring(0,1)+""+numberOnly.substring(0,1));
                System.out.println("" + lines.get(i) + "=> " + preprocess +  "=> " +theDigit);
                theSum = theSum + theDigit;
            }
        }

        System.out.println("Day 1 result:"+theSum);
    }

    static void day1Twoo() throws IOException {

        Path path = Paths.get("C:\\Users\\AngeliqueJard\\IdeaProjects\\AdventOfCodeMvn\\src\\main\\resources\\01-input.txt");
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

        //lines = List.of("feightwo4twofivefour");

        int theSum = 0;
        for(int i=0; i<lines.size(); i++) {
            System.out.println(";WORD:" + lines.get(i));
            String rawLine = lines.get(i);

            String numberLeft = searchFromStart(rawLine);
            String numberRight = searchFromEnd(rawLine);
            System.out.println(";" + lines.get(i) + "=> " + numberLeft +" | " + numberRight);

            theSum = theSum + Integer.parseInt(""+numberLeft+""+numberRight);
        }
        System.out.println("");
        System.out.println("Day 1 result:"+theSum);
    }

    private static String searchFromStart(String rawLine) {
        System.out.println(";searchFromStart");
        String result = "";
        int i = 0;
        boolean loopAgain = true;
        while (loopAgain) {
            if (Character.isDigit((rawLine.charAt(i)))) {
                result = ""+rawLine.charAt(i);
            } else {
                List<String> potentialsNumbers = isStartOfDigitWord(rawLine.charAt(i));
                if ( ! potentialsNumbers.isEmpty() ) {
                    for (String numberToCheck:potentialsNumbers) {
                        if(i + numberToCheck.length() <= rawLine.length()) {
                            if(numberToCheck.equals(rawLine.substring(i, i + numberToCheck.length()))) {
                                result = ""+getDigitWord(rawLine.substring(i, i + numberToCheck.length()));
                            }
                        }
                    }
                }
            }
            i++;
            loopAgain = (i<rawLine.toCharArray().length) && "".equals(result);
        }
        return result;
    }

    private static String searchFromEnd(String rawLine) {
        System.out.println(";searchFromEnd");
        String result = "";
        int i = rawLine.length()-1;
        boolean loopAgain = true;
        while (loopAgain) {
            System.out.println(";Char at "+ i +" => "+rawLine.charAt(i));

            if (Character.isDigit((rawLine.charAt(i)))) {
                result = ""+rawLine.charAt(i);
            } else {
                List<String> potentialsNumbers = isStartOfDigitWord(rawLine.charAt(i));
                if (!potentialsNumbers.isEmpty()) {
                    for (String numberToCheck:potentialsNumbers) {
                        if(i + numberToCheck.length() <= rawLine.length()) {
                            if (numberToCheck.equals(rawLine.substring(i, i + numberToCheck.length()))) {
                                result = "" + getDigitWord(rawLine.substring(i, i + numberToCheck.length()));
                            }
                        }
                    }
                }
            }
            i--;
            loopAgain = (i>=0) && "".equals(result);
        }
        return result;
    }

    private static int getDigitWord(String potentialDigitWord) {
        System.out.println(";Get digit from ["+potentialDigitWord+"]");

        if (potentialDigitWord.contains("one")) {
            return 1;
        } else if (potentialDigitWord.contains("two")) {
            return 2;
        } else if (potentialDigitWord.contains("three")) {
            return 3;
        } else if (potentialDigitWord.contains("four")) {
            return 4;
        } else if (potentialDigitWord.contains("five")) {
            return 5;
        } else if (potentialDigitWord.contains("six")) {
            return 6;
        } else if (potentialDigitWord.contains("seven")) {
            return 7;
        } else if (potentialDigitWord.contains("eight")) {
            return 8;
        } else if (potentialDigitWord.contains("nine")) {
            return 9;
        }
        return 0;
    }

    private static List<String> isStartOfDigitWord(char c) {
        System.out.println(";isStartOfDigitWord => "+c);
        if(c == 'o') {
            return List.of("one");
        } else if (c == 't') {
            return List.of("two", "three");
        } else if (c == 'e') {
            return List.of("eight");
        } else if (c == 'f') {
            return List.of("four", "five");
        } else if (c == 's') {
            return List.of("six", "seven");
        } else if (c == 'n') {
            return List.of("nine");
        }
        return List.of();
    }

}