package pl.edu.agh.mwo.console;

import pl.edu.agh.mwo.board.MinesweeperAnalyser;
import pl.edu.agh.mwo.board.MinesweeperBoard;
import pl.edu.agh.mwo.board.MinesweeperBoardReader;
import pl.edu.agh.mwo.builder.MinesweeperBoardBuilder;
import pl.edu.agh.mwo.messages.Messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MinesweeperConsole {

    private final BufferedReader br;

    private final MinesweeperAnalyser analyser;

    public MinesweeperConsole() {
        this(new BufferedReader(new InputStreamReader(System.in)));
    }

    public MinesweeperConsole(BufferedReader br) {
        this.br = br;
        this.analyser = new MinesweeperAnalyser();
    }

    public void start() {
        String input = "";
        do {
            try {
                playGame();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("Input error: " + e.getMessage());
            }
            printSeparator();
            System.out.println(Messages.NEW_GAME_MESSAGE);
            try {
                input = br.readLine();
            } catch (IOException e) {
                System.out.println("Input error.");
                input = "end";
            }
        } while (!input.equals("end"));
        System.out.println("See you next time!");
    }

    private void playGame() throws IOException {
        MinesweeperBoardReader reader = new MinesweeperBoardReader();
        printSeparator();
        System.out.println(Messages.START_MESSAGE);
        int height = readNumber(reader, Messages.ENTER_ROWS);
        int width = readNumber(reader, Messages.ENTER_COLUMNS);
        MinesweeperBoardBuilder builder = new MinesweeperBoardBuilder(br);
        builder.fillBoard(reader, height, width);
        MinesweeperBoard board = reader.read(reader.getCurrentText(), width, height);
        printBoard(board);
        analyser.fillIn(board);
        printBoard(board);
    }

    private int readNumber(MinesweeperBoardReader reader, String message) throws IOException {
        System.out.println(message);
        return reader.changeToNumber(br.readLine());
    }

    private void printBoard(MinesweeperBoard board) {
        printSeparator();
        System.out.println(board);
    }

    private void printSeparator() {
        System.out.println(Messages.SEPARATOR);
    }
}