package pl.edu.agh.mwo.board;

import pl.edu.agh.mwo.messages.Messages;

public class BoardValidator {

    public void validateSize(String textBoard, int width, int height) {
        if (textBoard.length() != width * height)
            throw new IllegalArgumentException(String.format(Messages.BOARD_SIZE_MISMATCH, textBoard.length(), width * height));
    }

    public void validateCharacter(char c) {
        if (c != '*' && c != '.') throw new IllegalArgumentException(Messages.INVALID_BOARD_CHARACTER + c);
    }
}