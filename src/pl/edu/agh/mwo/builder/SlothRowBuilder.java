package pl.edu.agh.mwo.builder;

public class SlothRowBuilder implements RowBuilder {

    @Override
    public String build(String input, int width) {
        validateInput(input);
        char[] row = createEmptyRow(width);
        String[] values = input.trim().split("\\s+");
        for (String value : values) {
            if (value.contains("-")) {
                createRange(row, value, width);
            } else {
                createColumn(row, value, width);
            }
        }
        return new String(row);
    }

    private void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Column list cannot be empty");
        }
    }

    private char[] createEmptyRow(int width) {
        return ".".repeat(width).toCharArray();
    }

    private void createRange(char[] row, String value, int width) {
        String[] range = value.split("-");
        if (range.length != 2) {
            throw new IllegalArgumentException("Invalid range: " + value);
        }
        int start = parseNumber(range[0], value) - 1;
        int end = parseNumber(range[1], value) - 1;
        validateRange(start, end, width, value);
        for (int i = start; i <= end; i++) {
            row[i] = '*';
        }
    }

    private void createColumn(char[] row, String value, int width) {
        int index = parseNumber(value, value) - 1;
        if (index < 0 || index >= width) {
            throw new IllegalArgumentException("Column outside board: " + value);
        }
        row[index] = '*';
    }

    private int parseNumber(String value, String originalInput) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number in input: " + originalInput);
        }
    }

    private void validateRange(int start, int end, int width, String value) {
        if (start < 0 || end >= width) {
            throw new IllegalArgumentException("Range outside board: " + value);
        }
        if (start > end) {
            throw new IllegalArgumentException("Invalid range: start is greater than end");
        }
    }
}