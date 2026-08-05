package pl.edu.agh.mwo.builder;

import pl.edu.agh.mwo.board.BoardSize;
import pl.edu.agh.mwo.board.BoardTextBuilder;
import pl.edu.agh.mwo.parser.NumberParser;
import pl.edu.agh.mwo.messages.Messages;

import java.io.BufferedReader;
import java.io.IOException;

public class MinesweeperBoardBuilder {

    private final BufferedReader br;
    private final NumberParser numberParser;

    public MinesweeperBoardBuilder(BufferedReader br) {
        this.br = br;
        this.numberParser = new NumberParser();
    }

    public void fillBoard(BoardTextBuilder textBuilder, int height, int width) throws IOException {
        RowBuilder builder = chooseBuilder(width);
        createRows(textBuilder, builder, height, width);
    }

    private RowBuilder chooseBuilder(int width) throws IOException {
        if (width <= BoardSize.SMALL.getValue()) {
            System.out.println(Messages.SMALL_BOARD);
            return new RabbitRowBuilder();
        }
        printAvailableModes(width);
        BoardFillingMode mode = BoardFillingMode.fromNumber(numberParser.parse(br.readLine()));
        return createBuilder(mode, width);
    }

    private void printAvailableModes(int width) {
        System.out.println(Messages.CHOOSE_MODE);
        System.out.println(Messages.RABBIT_MODE);
        System.out.println(Messages.TURTLE_MODE);
        if (width > BoardSize.MEDIUM.getValue()) System.out.println(Messages.SLOTH_MODE);
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
                if (width > BoardSize.MEDIUM.getValue()) return new SlothRowBuilder();
                throw new IllegalArgumentException(String.format(Messages.SLOTH_NOT_AVAILABLE, BoardSize.MEDIUM.getValue()));
            default:
                throw new IllegalArgumentException(Messages.UNKNOWN_MODE);
        }
    }

    private void createRows(BoardTextBuilder textBuilder, RowBuilder builder, int height, int width) throws IOException {
        for (int i = 1; i <= height; i++) {
            System.out.println(Messages.ENTER_ROW + i + ":");
            String input = br.readLine();
            String row = builder.build(input, width);
            textBuilder.addRow(row);
        }
    }
}