package pl.edu.agh.mwo.console;

import pl.edu.agh.mwo.board.BoardTextBuilder;
import pl.edu.agh.mwo.board.MinesweeperAnalyser;
import pl.edu.agh.mwo.board.MinesweeperBoard;
import pl.edu.agh.mwo.board.MinesweeperBoardReader;
import pl.edu.agh.mwo.board.NumberParser;
import pl.edu.agh.mwo.builder.MinesweeperBoardBuilder;
import pl.edu.agh.mwo.messages.Messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MinesweeperConsole {

    private final BufferedReader br;
    private final MinesweeperAnalyser analyser;
    private final NumberParser numberParser;

    public MinesweeperConsole() {
        this(new BufferedReader(new InputStreamReader(System.in)));
    }

    public MinesweeperConsole(BufferedReader br) {
        this.br = br;
        this.analyser = new MinesweeperAnalyser();
        this.numberParser = new NumberParser();
    }

    public void start() {
        String input = "";
        do {
            try {
                playGame();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(Messages.INPUT_ERROR + e.getMessage());
            }
            printSeparator();
            System.out.println(Messages.NEW_GAME_MESSAGE);
            try {
                input = br.readLine();
            } catch (IOException e) {
                System.out.println(Messages.INPUT_ERROR_SHORT);
                input = "end";
            }
        } while (!input.equals("end"));
        System.out.println(Messages.GOODBYE_MESSAGE);
    }

    private void playGame() throws IOException {
        MinesweeperBoardReader reader = new MinesweeperBoardReader();
        BoardTextBuilder textBuilder = new BoardTextBuilder();
        printSeparator();
        System.out.println(Messages.START_MESSAGE);
        int height = readNumber(Messages.ENTER_ROWS);
        int width = readNumber(Messages.ENTER_COLUMNS);
        MinesweeperBoardBuilder builder = new MinesweeperBoardBuilder(br);
        builder.fillBoard(textBuilder, height, width);
        MinesweeperBoard board = reader.read(textBuilder.getCurrentText(), width, height);
        printBoard(board);
        analyser.fillIn(board);
        printBoard(board);
    }

    private int readNumber(String message) throws IOException {
        System.out.println(message);
        return numberParser.parse(br.readLine());
    }

    private void printBoard(MinesweeperBoard board) {
        printSeparator();
        System.out.println(board);
    }

    private void printSeparator() {
        System.out.println(Messages.SEPARATOR);
    }
}