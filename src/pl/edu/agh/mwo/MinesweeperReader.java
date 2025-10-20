package pl.edu.agh.mwo;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperReader {

    protected String currentText = "";
    private String textToPrint = "";
    protected int rowNumber;
    protected int colNumber;

    public MinesweeperBoard read(String textBoard, int width, int height) {
        textBoard = textBoard.replaceAll("\\s", "");
        char[][] board = new char[height][width];

        if (textBoard.length() != height * width) {
            throw new IllegalArgumentException(
                    String.format("The board size does not match %d x %d",
                            textBoard.length(), height * width));
        }

        assert textBoard.length() == width * height;
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                char c = textBoard.charAt(x * width + y);
                if (c != '*' && c != '.') {
                    throw new IllegalArgumentException("The board contains an invalid character: " + c);
                }
                board[x][y] = textBoard.charAt(x * width + y);
            }
        }
        return new MinesweeperBoard(board);
    }

    public int setRowNumber(int rowNumber) {
        return this.rowNumber = rowNumber;
    }

    public int setColNumber(int colNumber) {
        return this.colNumber = colNumber;
    }

    protected String getCurrentText() {
        return this.currentText;
    }

    private boolean isTextContainsForbiddenCharacters1(String newRowText) {

        for (int i = 0; i < newRowText.length(); i++) {
            char c = newRowText.charAt(i);
            if (c != '*' && c != '.') {
                throw new IllegalArgumentException("[v14] The entered text can only contain \"*\" or \".\".\n"
                        + "> entered text: " + newRowText);
            }
        }
        return true;
    }

    public String createTextBlock1(String newRowText) throws Exception {

        if (newRowText.length() == colNumber) {
            isTextContainsForbiddenCharacters1(newRowText);
            currentText = currentText + "\n" + newRowText;
        } else {
            throw new IllegalArgumentException(String.format("[v17] The entered text has an invalid length.\n"
                    + "> entered text: " + newRowText + ""));
        }

        return currentText;
    }

    private List<String> defaultRowText() {
        List<String> currentText = new ArrayList<>();

        for (int i = 0; i < colNumber; i++) {
            currentText.add(".");
        }
        return currentText;
    }

    private boolean isTextContainsForbiddenCharacters2(String newRowText) {

        for (int i = 0; i < newRowText.length(); i++) {
            char c = newRowText.charAt(i);
            if (!Character.isDigit(newRowText.charAt(i)) && c != ' ') {
                throw new IllegalArgumentException("[v11] The entered text can only contain numbers.\n"
                        + "> entered text: " + newRowText);
            }
        }
        return true;
    }

    protected String createTextBlock2(String newRowText) throws Exception {

        isTextContainsForbiddenCharacters2(newRowText);
        List<String> symbolsList = defaultRowText();
        String previousSymbol = "";

        for (int i = 0; i < newRowText.length(); i++) {
            char c = newRowText.charAt(i);
            String s13 = Character.toString(c);

            if (Character.isDigit(c)) {
                if (previousSymbol.equals("")) {

                    int number = Integer.parseInt(s13);
                    if (number != 0) {
                        previousSymbol = Character.toString(c);
                    } else {
                        textToPrint = previousSymbol + Character.toString(c);
                        throw new IllegalArgumentException("[v9] A number cannot start with zero.\n"
                                + "> error: " + textToPrint + "\n"
                                + "> entered text: " + newRowText);
                    }
                } else {
                    previousSymbol += Character.toString(c);
                }
            }

            if (Character.isWhitespace(c) || i == newRowText.length() - 1) {

                if (!previousSymbol.equals("")) {
                    String s2 = previousSymbol;
                    int index = Integer.parseInt(s2) - 1;

                    if (index >= 0 && index < colNumber) {
                        symbolsList.set(index, "*");
                    } else {
                        textToPrint = previousSymbol + Character.toString(c);
                        throw new IllegalArgumentException("[v10] One of the given column numbers is larger than the row width (" + colNumber + ").\n"
                                + "> error: " + textToPrint + "\n"
                                + "> entered text: " + newRowText);
                    }

                    previousSymbol = "";
                }
            }
        }

        for (int i = 0; i < colNumber; i++) {
            String c = symbolsList.get(i);
            currentText += c;
        }

        return currentText;
    }

    private boolean isTextContainsForbiddenCharacters3(String newRowText) {

        for (int i = 0; i < newRowText.length(); i++) {
            char c = newRowText.charAt(i);
            String s13 = Character.toString(c);

            if (!Character.isDigit(newRowText.charAt(i)) && c != ' ' && !s13.equals("-")) {
                throw new IllegalArgumentException("[v12] The entered text can only contain numbers and the \"-\" sign.\n"
                        + "> entered text: " + newRowText);
            }
        }
        return true;
    }

    protected String createTextBlock3(String newRowText) throws Exception {

        isTextContainsForbiddenCharacters3(newRowText);
        List<String> symbolsList = defaultRowText();

        String previousSymbol = "";

        for (int i = 0; i < newRowText.length(); i++) {
            char c = newRowText.charAt(i);
            String s13 = Character.toString(c);

            if (Character.isDigit(c)) {
                if (previousSymbol.equals("")) {

                    int number = Integer.parseInt(s13);
                    if (number != 0) {
                        previousSymbol = Character.toString(c);
                    } else {
                        textToPrint = previousSymbol + Character.toString(c);
                        throw new IllegalArgumentException("[v2] A number cannot start with zero.\n"
                                + "> error: " + textToPrint + "\n"
                                + "> entered text: " + newRowText);
                    }
                } else {

                    if (previousSymbol.lastIndexOf("-") == previousSymbol.length() - 1 || Character.isDigit(c)) {

                        int number = Integer.parseInt(s13);
                        if (number != 0) {
                            previousSymbol += Character.toString(c);
                        } else {

                            if (previousSymbol.lastIndexOf("-") == previousSymbol.length() - 1) {

                                textToPrint = previousSymbol + Character.toString(c);
                                throw new IllegalArgumentException("[v1] A number cannot start with zero.\n"
                                        + "> error: " + textToPrint + "\n"
                                        + "> entered text: " + newRowText);
                            } else {
                                previousSymbol += Character.toString(c);
                            }
                        }
                    }
                }
            }

            if (s13.equals("-")) {

                if (previousSymbol.contains("-")) {
                    textToPrint = previousSymbol + Character.toString(c);
                    throw new IllegalArgumentException("[v3] A \"-\" sign was already added before. Invalid range configuration.\n"
                            + "> error: " + textToPrint + "\n"
                            + "> entered text: " + newRowText);
                } else {
                    if (previousSymbol.equals("")) {
                        textToPrint = previousSymbol + Character.toString(c);
                        throw new IllegalArgumentException("[v4] There is no number before the \"-\" sign. Invalid range configuration.\n"
                                + "> error: " + textToPrint + "\n"
                                + "> entered text: " + newRowText);
                    } else {
                        if (previousSymbol.lastIndexOf("-") == previousSymbol.length() - 1) {
                            textToPrint = previousSymbol + Character.toString(c);
                            throw new IllegalArgumentException("[v5] There is no number after the \"-\" sign. Invalid range configuration.\n"
                                    + "> error: " + textToPrint + "\n"
                                    + "> entered text: " + newRowText);
                        } else {
                            previousSymbol += Character.toString(c);
                        }
                    }
                }
            }

            if (Character.isWhitespace(c) || i == newRowText.length() - 1) {

                if (!previousSymbol.equals("")) {

                    if (previousSymbol.lastIndexOf("-") == previousSymbol.length() - 1) {
                        textToPrint = previousSymbol;
                        throw new IllegalArgumentException("[v6] There is no number after the \"-\" sign.\n"
                                + "> error: " + textToPrint + "\n"
                                + "> entered text: " + newRowText);
                    } else {
                        String s2 = previousSymbol;

                        if (!s2.contains("-")) {
                            int index = Integer.parseInt(s2) - 1;
                            if (index >= 0 && index < colNumber) {
                                symbolsList.set(index, "*");
                            } else {
                                textToPrint = previousSymbol + Character.toString(c);
                                throw new IllegalArgumentException("[v7] One of the column numbers is larger than the row width (" + colNumber + ").\n"
                                        + "> error: " + textToPrint + "\n"
                                        + "> entered text: " + newRowText);
                            }
                        } else {
                            int cutIndex = previousSymbol.indexOf("-");
                            int startIndex = Integer.parseInt(previousSymbol.substring(0, cutIndex)) - 1;
                            int endIndex = Integer.parseInt(previousSymbol.substring(cutIndex + 1, previousSymbol.length())) - 1;

                            if (startIndex <= endIndex) {
                                if (endIndex >= 0 && endIndex < colNumber) {
                                    if (startIndex >= 0 && startIndex < colNumber) {

                                        for (int z = startIndex; z <= endIndex; z++) {
                                            symbolsList.set(z, "*");
                                        }
                                    } else {
                                        textToPrint = previousSymbol + Character.toString(c);
                                        throw new IllegalArgumentException("[v2] The starting value of the range is larger than the board width (" + colNumber + ").\n"
                                                + "> error: " + textToPrint + "\n"
                                                + "> entered text: " + newRowText);
                                    }
                                }
                            } else {
                                textToPrint = previousSymbol + Character.toString(c);
                                throw new IllegalArgumentException("[v8] The first number in the range (" + startIndex + ") is larger than the second (" + endIndex + ").\n"
                                        + "> error: " + textToPrint + "\n"
                                        + "> entered text: " + newRowText);
                            }
                        }
                    }
                }

                previousSymbol = "";
            }
        }

        for (int i = 0; i < colNumber; i++) {
            String c = symbolsList.get(i);
            currentText += c;
        }

        return currentText;
    }

    protected int changeToNumber(String numberText) {
        try {
            int number = Integer.valueOf(numberText);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[v13] The entered text \"" + numberText + "\" is not a valid number or contains invalid characters.");
        }
        return Integer.parseInt(numberText);
    }
}