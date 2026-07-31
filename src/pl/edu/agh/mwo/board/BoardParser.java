package pl.edu.agh.mwo.board;

public class BoardParser {

    private final BoardValidator validator;

    public BoardParser() {
        this.validator = new BoardValidator();
    }

    public MinesweeperBoard parse(String textBoard, int width, int height) {
        String cleanText = removeSpaces(textBoard);
        validator.validateSize(cleanText, width, height);
        char[][] board = createBoard(cleanText, width, height);
        return new MinesweeperBoard(board);
    }

    private String removeSpaces(String textBoard) {
        return textBoard.replaceAll("\\s", "");
    }

    private char[][] createBoard(String textBoard, int width, int height) {
        char[][] board = new char[height][width];
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                char value = textBoard.charAt(row * width + column);
                validator.validateCharacter(value);
                board[row][column] = value;
            }
        }
        return board;
    }
}