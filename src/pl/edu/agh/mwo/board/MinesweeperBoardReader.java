package pl.edu.agh.mwo.board;

public class MinesweeperBoardReader {

    private final StringBuilder currentText = new StringBuilder();

    private final BoardParser parser;

    public MinesweeperBoardReader() {
        this.parser = new BoardParser();
    }

    public MinesweeperBoard read(String textBoard, int width, int height) {
        return parser.parse(textBoard, width, height);
    }

    public String getCurrentText() {
        return currentText.toString();
    }

    public void addRow(String row) {
        currentText.append("\n").append(row);
    }

    public void clear() {
        currentText.setLength(0);
    }

    public int changeToNumber(String numberText) {
        try {
            return Integer.parseInt(numberText);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[v13] Invalid number: " + numberText);
        }
    }
}