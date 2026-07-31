package pl.edu.agh.mwo.board;

public enum BoardSize {

    SMALL(5),
    MEDIUM(20);

    private final int value;

    BoardSize(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}