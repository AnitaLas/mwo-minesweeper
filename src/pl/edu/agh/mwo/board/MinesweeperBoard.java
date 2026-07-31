package pl.edu.agh.mwo.board;

public class MinesweeperBoard {

    private char[][] mines;

    public MinesweeperBoard(char[][] mines) {
        this.mines = mines;
    }

    public char[][] getMines() {
        return mines;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for(int row = 0; row < mines.length; row++) {
            for(int col = 0; col < mines[row].length; col++) {
                //result += mines[row][col];
                result.append(mines[row][col]);
            }
            result.append("\n");
        }
        return result.toString();
    }
}