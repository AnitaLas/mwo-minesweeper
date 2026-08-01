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
    public static final String INPUT_ERROR = "Input error: ";
    public static final String INPUT_ERROR_SHORT = "Input error.";
    public static final String GOODBYE_MESSAGE = "See you next time?";

    // MinesweeperBoardBuilder
    public static final String CHOOSE_MODE = "Choose board filling mode:";
    public static final String SMALL_BOARD = "Small board - Rabbit Mode selected automatically.";
    public static final String RABBIT_MODE = "1 - Rabbit Mode  (example: **..*.*.)";
    public static final String TURTLE_MODE = "2 - Turtle Mode  (example: 1 3 5 8)";
    public static final String SLOTH_MODE = "3 - Sloth Mode   (example: 1-3 6 8-10)";
    public static final String ENTER_ROW_DATA = "Enter row data according to selected mode:";
    public static final String ENTER_ROW = "Enter row ";
    public static final String UNKNOWN_MODE = "Unknown mode: ";
    public static final String SLOTH_NOT_AVAILABLE = "Sloth Mode is available only for boards wider than %d columns";

    // MinesweeperBoardReader
    public static final String INVALID_NUMBER = "Invalid number: ";

    // BoardValidator
    public static final String BOARD_SIZE_MISMATCH = "The board size does not match %d x %d";
    public static final String INVALID_BOARD_CHARACTER = "The board contains an invalid character: ";

    // RabbitRowBuilder
    public static final String ROW_NULL = "Row cannot be null";
    public static final String ROW_LENGTH = "Row length must be ";
    public static final String ROW_INVALID_CHARACTERS = "Row can contain only '*' and '.'";

    // TurtleRowBuilder / SlothRowBuilder
    public static final String COLUMN_LIST_EMPTY = "Column list cannot be empty";
    public static final String INVALID_COLUMN_NUMBER = "Invalid column number: ";
    public static final String COLUMN_OUTSIDE_BOARD = "Column outside board: ";

    // SlothRowBuilder
    public static final String INVALID_RANGE = "Invalid range: ";
    public static final String RANGE_OUTSIDE_BOARD = "Range outside board: ";
    public static final String RANGE_START_GREATER_THAN_END = "Invalid range: start is greater than end";
    public static final String INVALID_NUMBER_IN_INPUT = "Invalid number in input: ";
}