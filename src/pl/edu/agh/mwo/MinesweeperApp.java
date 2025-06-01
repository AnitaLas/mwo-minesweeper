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
                System.out.println("---------- ZACZYNAMY ----------");

                MinesweeperReader reader = new MinesweeperReader();

                System.out.println("Podaj liczbę wierszy (wysokość): "); // 4
                str = br.readLine();
                int height = reader.changeToNumber(str);

                System.out.println("Podaj liczbę kolumn (szerokość): "); // 5
                str = br.readLine();
                int width = reader.changeToNumber(str);
                //System.out.println("-------------------------------");
                System.out.println("");

                reader.setRowNumber(height);
                reader.setColNumber(width);

                if (width > 20) {
                    System.out.println("długość wiersza jest większ niż 20. Wybierz tryb uzupełniania tablicy:\n" +
                            "> [t1] klepacza - kliknij 1 - ręcznie uzupełnianie tablicy\n" +
                            "> [t2] tryb lenia 1 - kliknij 2 - uzupełnienie tablicy symbolem \"*\" na podstawie podnych nr kolumn\n" +
                            "> [t3] tryb lenia 2 - kliknij 3 - uzupełnienie tablicy symbolem \"*\" na podstawie podnych nr kolumn lub przpedziałów kolumn\n");

                    str = br.readLine();
                    int tryb = reader.changeToNumber(str);

                    //System.out.println("-------------------------------");
                    if (tryb == 1) {
                        System.out.println("Wprowdzony tekst może zawierać tylko \"*\" lub \".\".");
                        for (int i = 1; i <= height; i++) {
                            System.out.println("Podaj wiersz numer " + i + " : ");
                            str = br.readLine();
                            reader.createTextBlock1(str);
                        }
                    }

                    if (tryb == 2) {
                        System.out.println("Podaj nr kolumn, kolejne nr należy oddzielić spacją, przykład: 5 25 14 ");

                        for (int i = 1; i <= height; i++) {
                            System.out.println("Podaj nr kolumn dla wiersza " + i + " : ");
                            str = br.readLine();
                            reader.createTextBlock2(str);
                        }
                    }

                    if (tryb == 3) {
                        System.out.println("Podaj nr kolumn lub przedziały, kolejne nr kolumn lub przedziały należy oddzielić spacją,\n" +
                                "przykładowa definicja przedziału 14-50, przykład: 5 14-50 60-61 9");

                        for (int i = 1; i <= height; i++) {
                            System.out.println("Podaj nr kolumn/y lub przedziału odzielone spalcją dla wiersza " + i);
                            str = br.readLine();
                            reader.createTextBlock3(str);
                        }
                    }
                } else if (width > 5 && width <= 20) {
                    System.out.println("długość wiersza jest większ niż 20. Wybierz tryb uzupełniania tablicy:\n" +
                            "> [t1] tryb klepacza - kliknij 1- ręcznie uzupełnianie tablicy \n" +
                            "> [t2] tryb lenia - kliknij 2 - uzupełnienie tablicy symbolem \"*\" na podstawie podnych nr kolumn");

                    str = br.readLine();
                    int tryb = reader.changeToNumber(str);

                    //System.out.println("-------------------------------");

                    if (tryb == 1) {
                        System.out.println("Wprowdzony tekst może zawierać tylko \"*\" lub \".\".");
                        for (int i = 1; i <= height; i++) {
                            System.out.println("Podaj wiersz numer " + i + " : ");
                            str = br.readLine();
                            reader.createTextBlock1(str);
                        }
                    }

                    if (tryb == 2) {
                        System.out.println("Podaj nr kolumn, kolejne nr należy oddzielić spacją, przykład: 5 25 14 ");

                        for (int i = 1; i <= height; i++) {
                            System.out.println("Podaj nr kolumn dla wiersza " + i + " : ");
                            str = br.readLine();
                            reader.createTextBlock2(str);
                        }
                    }

                } else { // else if (width <= 5) {
                    System.out.println("Wprowdzony tekst może zawierać tylko \"*\" lub \".\".");
                    for (int i = 1; i <= height; i++) {
                        System.out.println("Podaj wiersz numer " + i + " : "); // 5
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
            System.out.println("Chcesz stworzyć nową tablicę wpisz co kolwiek. Wpisz \"koniec\" jeśli chcesz zakończyć działanie programu");

            try {
                str = br.readLine();
            } catch (IOException e) {
                System.out.println("a to ciekawe");
            }

            System.out.println("-------------------------------");
        } while (!str.equals("koniec"));
        System.out.println("Do zobaczenia!");

    }
}
