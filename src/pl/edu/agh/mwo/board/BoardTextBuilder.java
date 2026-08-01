package pl.edu.agh.mwo.board;

public class BoardTextBuilder {

    private final StringBuilder currentText = new StringBuilder();

    public String getCurrentText() {
        return currentText.toString();
    }

    public void addRow(String row) {
        currentText.append("\n").append(row);
    }

    public void clear() {
        currentText.setLength(0);
    }
}