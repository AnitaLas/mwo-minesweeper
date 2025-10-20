package pl.edu.agh.mwo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MinesweeperApp {

    public static void main(String[] args) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";

        do {
            try {
                System.out.println("---------- LET'S START ----------");

                MinesweeperReader reader = new MinesweeperReader();

                System.out.println("Enter the number of rows (height): ");
                str = br.readLine();
                int height = reader.changeToNumber(str);

                System.out.println("Enter the number of columns (width): ");
                str = br.readLine();
                int width = reader.changeToNumber(str);
                System.out.println("");

                reader.setRowNumber(height);
                reader.setColNumber(width);

                if (width > 20) {
                    System.out.println("The row length is greater than 20. Choose a board filling mode:\n" +
                            "> Rabbit Mode - press 1 - manually fill the board\n" +
                            "> Turtle Mode - press 2 - fill the board with \"*\" symbols based on entered column numbers\n" +
                            "> Sloth Mode - press 3 - fill the board with \"*\" symbols based on entered column numbers or column ranges\n");

                    str = br.readLine();
                    int mode = reader.changeToNumber(str);

                    if (mode == 1) {
                        System.out.println("The entered text can contain only \"*\" or \".\".");
                        for (int i = 1; i <= height; i++) {
                            System.out.println("Enter row number " + i + " : ");
                            str = br.readLine();
                            reader.createTextBlock1(str);
                        }
                    }

                    if (mode == 2) {
                        System.out.println("Enter column numbers separated by spaces, e.g.: 5 25 14 ");

                        for (int i = 1; i <= height; i++) {
                            System.out.println("Enter column numbers for row " + i + " : ");
                            str = br.readLine();
                            reader.createTextBlock2(str);
                        }
                    }

                    if (mode == 3) {
                        System.out.println("Enter column numbers or ranges separated by spaces.\n" +
                                "Example of a range: 14-50, e.g.: 5 14-50 60-61 9");

                        for (int i = 1; i <= height; i++) {
                            System.out.println("Enter column numbers or ranges separated by spaces for row " + i + " : ");
                            str = br.readLine();
                            reader.createTextBlock3(str);
                        }
                    }
                } else if (width > 5 && width <= 20) {
                    System.out.println("The row length is greater than 20. Choose a board filling mode:\n" +
                            "> Rabbit Mode - press 1 - manually fill the board\n" +
                            "> Turtle Mode - press 2 - fill the board with \"*\" symbols based on entered column numbers");

                    str = br.readLine();
                    int mode = reader.changeToNumber(str);

                    if (mode == 1) {
                        System.out.println("The entered text can contain only \"*\" or \".\".");
                        for (int i = 1; i <= height; i++) {
                            System.out.println("Enter row number " + i + " : ");
                            str = br.readLine();
                            reader.createTextBlock1(str);
                        }
                    }

                    if (mode == 2) {
                        System.out.println("Enter column numbers separated by spaces, e.g.: 5 25 14 ");

                        for (int i = 1; i <= height; i++) {
                            System.out.println("Enter column numbers for row " + i + " : ");
                            str = br.readLine();
                            reader.createTextBlock2(str);
                        }
                    }

                } else {
                    System.out.println("The entered text can contain only \"*\" or \".\".");
                    for (int i = 1; i <= height; i++) {
                        System.out.println("Enter row number " + i + " : ");
                        str = br.readLine();
                        reader.createTextBlock1(str);
                    }
                }

                String b1 = reader.getCurrentText();
                MinesweeperBoard board = reader.read(b1, width, height);

                System.out.println("-------------------------------");
                System.out.println("");
                System.out.println(board);
                MinesweeperAnalyser analyser = new MinesweeperAnalyser();

                analyser.fillIn(board);
                System.out.println("-------------------------------");
                System.out.println("");
                System.out.println(board);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("--------------------------------");
            System.out.println("Do you want to create a new board? Type anything. Type \"end\" to close the program.");

            try {
                str = br.readLine();
            } catch (IOException e) {
                System.out.println("Well, that's interesting...");
            }

            System.out.println("-------------------------------");
        } while (!str.equals("end"));
        System.out.println("See you next time!");
    }
}
