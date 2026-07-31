package pl.edu.agh.mwo.board;

public class BoardValidator {

    public void validateSize(String textBoard, int width, int height) {
        if (textBoard.length() != width * height) {
            throw new IllegalArgumentException(String.format("The board size does not match %d x %d", textBoard.length(), width * height));
        }
    }

    public void validateCharacter(char c) {
        if (c != '*' && c != '.') {
            throw new IllegalArgumentException("The board contains an invalid character: " + c);
        }
    }
}
