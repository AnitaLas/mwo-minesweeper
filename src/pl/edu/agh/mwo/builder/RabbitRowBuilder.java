package pl.edu.agh.mwo.builder;

public class RabbitRowBuilder implements RowBuilder {

    @Override
    public String build(String input, int width) {
        validateLength(input, width);
        validateCharacters(input);
        return input;
    }

    private void validateLength(String input, int width) {
        if (input == null) {
            throw new IllegalArgumentException("Row cannot be null");
        }

        if (input.length() != width) {
            throw new IllegalArgumentException("Row length must be " + width);
        }
    }

    private void validateCharacters(String input) {
        for (char c : input.toCharArray()) {
            if (c != '*' && c != '.') {
                throw new IllegalArgumentException("Row can contain only '*' and '.'");
            }
        }
    }
}