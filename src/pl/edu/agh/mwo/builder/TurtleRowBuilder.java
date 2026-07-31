package pl.edu.agh.mwo.builder;

public class TurtleRowBuilder implements RowBuilder {

    @Override
    public String build(String input, int width) {
        validateInput(input);
        char[] row = createEmptyRow(width);
        String[] columns = input.trim().split("\\s+");
        for (String column : columns) {
            int index = parseColumn(column);
            validateColumn(index, width, column);
            row[index] = '*';
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

    private int parseColumn(String column) {
        try {
            return Integer.parseInt(column) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid column number: " + column);
        }
    }

    private void validateColumn(int index, int width, String column) {
        if (index < 0 || index >= width) {
            throw new IllegalArgumentException("Column outside board: " + column);
        }
    }
}