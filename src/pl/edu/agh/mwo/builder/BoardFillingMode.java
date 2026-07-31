package pl.edu.agh.mwo.builder;

import pl.edu.agh.mwo.messages.Messages;

public enum BoardFillingMode {

    RABBIT(1),
    TURTLE(2),
    SLOTH(3);

    private final int number;

    BoardFillingMode(int number) {
        this.number = number;
    }

    public static BoardFillingMode fromNumber(int number) {
        for (BoardFillingMode mode : values()) {
            if (mode.number == number) {
                return mode;
            }
        }
        throw new IllegalArgumentException(Messages.UNKNOWN_MODE + number);
    }

    public int getNumber() {
        return number;
    }
}