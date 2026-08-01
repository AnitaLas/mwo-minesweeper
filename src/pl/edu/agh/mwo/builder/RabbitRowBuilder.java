package pl.edu.agh.mwo.builder;

import pl.edu.agh.mwo.messages.Messages;

public class RabbitRowBuilder implements RowBuilder {

    @Override
    public String build(String input, int width) {
        validateLength(input, width);
        validateCharacters(input);
        return input;
    }

    private void validateLength(String input, int width) {
        if (input == null) throw new IllegalArgumentException(Messages.ROW_NULL);
        if (input.length() != width) throw new IllegalArgumentException(Messages.ROW_LENGTH + width);
    }

    private void validateCharacters(String input) {
        for (char c : input.toCharArray()) {
            if (c != '*' && c != '.') throw new IllegalArgumentException(Messages.ROW_INVALID_CHARACTERS);
        }
    }
}