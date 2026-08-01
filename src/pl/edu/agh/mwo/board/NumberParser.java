package pl.edu.agh.mwo.board;

import pl.edu.agh.mwo.messages.Messages;

public class NumberParser {

    public int parse(String numberText) {
        try {
            return Integer.parseInt(numberText);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Messages.INVALID_NUMBER + numberText);
        }
    }
}