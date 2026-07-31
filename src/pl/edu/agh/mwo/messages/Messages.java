package pl.edu.agh.mwo.messages;

public final class Messages {

    private Messages() {
    }

    // MinesweeperConsole
    public static final String START_MESSAGE = "---------- LET'S START ----------";
    public static final String NEW_GAME_MESSAGE = "Create another board? Type anything or \"end\".";
    public static final String SEPARATOR = "-------------------------------";
    public static final String ENTER_ROWS = "Enter number of rows:";
    public static final String ENTER_COLUMNS = "Enter number of columns:";

    // MinesweeperBoardBuilder
    public static final String CHOOSE_MODE = "Choose board filling mode:";
    public static final String SMALL_BOARD = "Small board - Rabbit Mode selected automatically.";
    public static final String RABBIT_MODE = "1 - Rabbit Mode  (example: **..*.*.)";
    public static final String TURTLE_MODE = "2 - Turtle Mode  (example: 1 3 5 8)";
    public static final String SLOTH_MODE = "3 - Sloth Mode   (example: 1-3 6 8-10)";
    public static final String ENTER_ROW_DATA = "Enter row data according to selected mode:";
    public static final String ENTER_ROW = "Enter row ";
    public static final String UNKNOWN_MODE = "Unknown mode: ";
    public static final String SLOTH_NOT_AVAILABLE = "Sloth Mode is available only for boards wider than 20 columns";
}