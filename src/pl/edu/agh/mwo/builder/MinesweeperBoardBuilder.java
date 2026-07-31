package pl.edu.agh.mwo.builder;

import pl.edu.agh.mwo.board.BoardSize;
import pl.edu.agh.mwo.board.MinesweeperBoardReader;
import pl.edu.agh.mwo.messages.Messages;

import java.io.BufferedReader;
import java.io.IOException;

public class MinesweeperBoardBuilder {

    private final BufferedReader br;

    public MinesweeperBoardBuilder(BufferedReader br) {
        this.br = br;
    }

    public void fillBoard(MinesweeperBoardReader reader, int height, int width) throws IOException {
        RowBuilder builder = chooseBuilder(reader, width);
        createRows(reader, builder, height, width);
    }

    private RowBuilder chooseBuilder(MinesweeperBoardReader reader, int width) throws IOException {
        if (width <= BoardSize.SMALL.getValue()) {
            System.out.println(Messages.SMALL_BOARD);
            return new RabbitRowBuilder();
        }
        printAvailableModes(width);
        BoardFillingMode mode = BoardFillingMode.fromNumber(reader.changeToNumber(br.readLine()));
        return createBuilder(mode, width);
    }

    private void printAvailableModes(int width) {
        System.out.println(Messages.CHOOSE_MODE);
        System.out.println(Messages.RABBIT_MODE);
        System.out.println(Messages.TURTLE_MODE);
        if (width > BoardSize.MEDIUM.getValue()) {
            System.out.println(Messages.SLOTH_MODE);
        }
        System.out.println();
        System.out.println(Messages.ENTER_ROW_DATA);
    }

    private RowBuilder createBuilder(BoardFillingMode mode, int width) {
        switch (mode) {
            case RABBIT:
                return new RabbitRowBuilder();
            case TURTLE:
                return new TurtleRowBuilder();
            case SLOTH:
                if (width > BoardSize.MEDIUM.getValue()) {
                    return new SlothRowBuilder();
                }
                throw new IllegalArgumentException(Messages.SLOTH_NOT_AVAILABLE);
            default:
                throw new IllegalArgumentException(Messages.UNKNOWN_MODE);
        }
    }

    private void createRows(MinesweeperBoardReader reader, RowBuilder builder, int height, int width) throws IOException {
        for (int i = 1; i <= height; i++) {
            System.out.println(Messages.ENTER_ROW + i + ":");
            String input = br.readLine();
            String row = builder.build(input, width);
            reader.addRow(row);
        }
    }
}