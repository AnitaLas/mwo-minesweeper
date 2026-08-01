package pl.edu.agh.mwo.board;

public class MinesweeperBoardReader {

    private final BoardParser parser;

    public MinesweeperBoardReader() {
        this.parser = new BoardParser();
    }

    public MinesweeperBoard read(String textBoard, int width, int height) {
        return parser.parse(textBoard, width, height);
    }
}