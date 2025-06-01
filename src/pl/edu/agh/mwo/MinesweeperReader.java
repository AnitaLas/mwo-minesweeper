package pl.edu.agh.mwo;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperReader {

    protected String currentText = "";
    private String textToPrint = "";
    protected int rowNumber;
    protected int colNumber;

    public MinesweeperBoard read(String textBoard, int width, int height ) {
        textBoard = textBoard.replaceAll("\\s", "");
        char[][] board = new char[height][width];

        if(textBoard.length() != height*width) {
            throw new IllegalArgumentException(
                    String.format("Board does not have the same length %d x %d",
                            textBoard.length(), height*width));
        }

        assert textBoard.length() == width * height;
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                char c = textBoard.charAt(x*width + y);
                if(c != '*' && c != '.'){
                    throw new IllegalArgumentException("Given board has invalid character:" + c);
                }
                board[x][y] = textBoard.charAt(x*width + y);
            }
        }
        return new MinesweeperBoard(board);
    }

    public int setRowNumber(int rowNumber) {
        return this.rowNumber = rowNumber;
    }

    public int setColNumber(int colNumber) {
        return this.colNumber = colNumber;
    }

    protected String getCurrentText(){
        return this.currentText;
    }

    private boolean isTextContainsForbiddenCharacters1(String newRowText){

        for(int i = 0; i < newRowText.length(); i++){
            char c = newRowText.charAt(i);
            if(c != '*' && c != '.'){
                throw new IllegalArgumentException("[v14] Wprowdzony tekst może zawierać tylko \"*\" lub \".\".\n"
                        + "> wprowadzony tekst: " + newRowText);
            }
        }
        return true;
    }


    public String createTextBlock1(String newRowText) throws Exception{

                if (newRowText.length() == colNumber) {
                    isTextContainsForbiddenCharacters1(newRowText);
                    currentText = currentText + "\n" + newRowText;
                }
                else {
                    throw new IllegalArgumentException (String.format("[v17] Wprowdzony tekst ma błędną długość.\n"
                            + "> wprowadzony tekst: " + newRowText + ""));
                }

        return currentText;
    }

    private List<String> defaultRowText(){
        List<String> currentText = new ArrayList<>();

        for(int i = 0; i< colNumber; i++){
            currentText.add(".");
        }
        return currentText;
    }

    private boolean isTextContainsForbiddenCharacters2(String newRowText){

        for(int i = 0; i < newRowText.length(); i++){
            char c = newRowText.charAt(i);
            if(!Character.isDigit(newRowText.charAt(i)) && c != ' '){
                throw new IllegalArgumentException("[v11] Wprowdzony tekst może zawierać tylko liczby.\n"
                        + "> wprowadzony tekst: " + newRowText);
            }
        }
        return true;
    }

    protected String createTextBlock2(String newRowText) throws Exception{

        isTextContainsForbiddenCharacters2(newRowText);
        List<String> symbolsList = defaultRowText();
        String previousSymbol = "";

        for(int i = 0; i < newRowText.length(); i++){
            char c = newRowText.charAt(i);
            String s13 = Character.toString(c);

            if(Character.isDigit(c)){
                if(previousSymbol.equals("")){// wytestowąć isEmpty()

                    int number = Integer.parseInt(s13);
                    if(number != 0){
                        previousSymbol = Character.toString(c);
                    }
                    else {
                        textToPrint = previousSymbol + Character.toString(c);;
                        throw new IllegalArgumentException("[v9] Cyfra niemoże zaczynać się od zera.\n"
                                + "> błąd: " +  textToPrint + "\n"
                                + "> wprowadzony tekst: " + newRowText);
                    }
                }
                else {
                   previousSymbol += Character.toString(c);
                }
            }

            if(Character.isWhitespace(c) || i == newRowText.length() - 1){

                if(!previousSymbol.equals("")){
                    String s2 = previousSymbol;
                    int index = Integer.parseInt(s2) - 1; // 00013 = 13

                    if(index >= 0 && index < colNumber){
                        symbolsList.set(index, "*");
                    }
                    else{
                        textToPrint = previousSymbol + Character.toString(c);;
                        throw new IllegalArgumentException("[v10] Jeden z podanych nr kolumn jest większy niż szerokość wiersza(" + colNumber+ ").\n"
                                + "> błąd: " +  textToPrint + "\n"
                                + "> wprowadzony tekst: " + newRowText);
                    }

                    previousSymbol = "";
                }
            }
        }

        for(int i = 0; i < colNumber; i++){
            String c = symbolsList.get(i);
            currentText += c;
        }

        return currentText;
    }


    private boolean isTextContainsForbiddenCharacters3(String newRowText) {

        for (int i = 0; i < newRowText.length(); i++) {
            char c = newRowText.charAt(i);
            String s13 = Character.toString(c);

            if (!Character.isDigit(newRowText.charAt(i)) && c != ' ' && !s13.equals("-")) {
                throw new IllegalArgumentException("[v12] Wprowdzony tekst może zawierać tylko liczby i znak \"-\".\n"
                        + "> wprowadzony tekst: " + newRowText);
            }
        }
        return true;
    }

    protected String createTextBlock3( String newRowText) throws Exception{

        isTextContainsForbiddenCharacters3(newRowText);
        List<String> symbolsList = defaultRowText();

        String previousSymbol = "";

        for(int i = 0; i < newRowText.length(); i++){
            char c = newRowText.charAt(i);
            String s13 = Character.toString(c);

            if(Character.isDigit(c)){
                if(previousSymbol.equals("")){// wytestowąć isEmpty()

                    int number = Integer.parseInt(s13);
                    if(number != 0){
                        previousSymbol = Character.toString(c);
                    }
                    else {
                        textToPrint = previousSymbol + Character.toString(c);;
                        throw new IllegalArgumentException("[v2] Cyfra niemoże zaczynać się od zera.\n"
                                + "> błąd: " +  textToPrint + "\n"
                                + "> wprowadzony tekst: " + newRowText);
                    }
                }
                else {

                    if(previousSymbol.lastIndexOf("-") == previousSymbol.length()-1 || Character.isDigit(c)){ // || chr ??

                        int number = Integer.parseInt(s13);
                        if(number != 0){
                            previousSymbol += Character.toString(c);
                        }
                        else{

                            if(previousSymbol.lastIndexOf("-") == previousSymbol.length()-1 ){

                                textToPrint = previousSymbol + Character.toString(c);;
                                throw new IllegalArgumentException("[v1] Cyfra niemoże zaczynać się od zera.\n"
                                        + "> błąd: " +  textToPrint + "\n"
                                + "> wprowadzony tekst: " + newRowText);
                            }
                            else{
                                previousSymbol += Character.toString(c);
                            }
                        }
                    }
                }
            }

            if(s13.equals("-")){

                 if(previousSymbol.contains("-")){
                     textToPrint = previousSymbol + Character.toString(c);;
                     throw new IllegalArgumentException("[v3] Przed znakiem \"-\" został już dodany znak \"-\". Błędna konfiguracja przedziału.\n"
                             + "> błąd: " +  textToPrint + "\n"
                             + "> wprowadzony tekst: " + newRowText);
                 }
                 else {
                     if(previousSymbol.equals("")){
                         textToPrint = previousSymbol + Character.toString(c);;
                         throw new IllegalArgumentException("[v4] Przed znakiem \"-\" nie ma cyfry. Błędna konfiguracja przedziału.\n"
                                 + "> błąd: " +  textToPrint + "\n"
                                 + "> wprowadzony tekst: " + newRowText);
                     }
                     else{
                         if(previousSymbol.lastIndexOf("-") ==  previousSymbol.length()-1){
                             textToPrint = previousSymbol + Character.toString(c);;
                             throw new IllegalArgumentException("[v5] Za znakiem \"-\" nie ma cyfry. Błędna konfiguracja przedziału.\n"
                                     + "> błąd: " +  textToPrint + "\n"
                                     + "> wprowadzony tekst: " + newRowText);
                         }
                         else {
                             previousSymbol += Character.toString(c);
                         }
                     }
                 }
            }

            if(Character.isWhitespace(c) || i == newRowText.length() - 1){

                if(!previousSymbol.equals("")){

                    if(previousSymbol.lastIndexOf("-") ==  previousSymbol.length()-1){
                            textToPrint = previousSymbol;// + Character.toString(c);;
                            throw new IllegalArgumentException("[v6] Za znakiem \"-\" nie ma cyfry. ???????.\n"
                                    + "> błąd: " +  textToPrint + "\n"
                                    + "> wprowadzony tekst: " + newRowText);
                    }
                    else {
                        String s2 = previousSymbol;

                        if(!s2.contains("-")){
                            int index = Integer.parseInt(s2) - 1; // 00013 = 13
                            if(index >= 0 && index < colNumber){
                                symbolsList.set(index, "*");
                            }
                            else{
                                textToPrint = previousSymbol + Character.toString(c);;
                                throw new IllegalArgumentException("[v7] Jeden z podanych nr kolumn jest większy niż szerokość wiersza (" + colNumber+ ").\n"
                                        + "> błąd: " +  textToPrint + "\n"
                                        + "> wprowadzony tekst: " + newRowText);
                            }
                        }
                        else {
                            int cutIndex = previousSymbol.indexOf("-");
                            int startIndex = Integer.parseInt(previousSymbol.substring(0, cutIndex)) - 1;
                            int endIndex = Integer.parseInt(previousSymbol.substring(cutIndex + 1, previousSymbol.length())) - 1;

                            if(startIndex <= endIndex){
                                if(endIndex >= 0 && endIndex < colNumber){
                                    if(startIndex >= 0 && startIndex < colNumber){

                                        for(int z = startIndex - 0; z<= endIndex; z++){
                                            symbolsList.set(z, "*");
                                        }
                                    }
                                    else{
                                        textToPrint = previousSymbol + Character.toString(c);;
                                        throw new IllegalArgumentException("[v2] Początkowa wartość przedziału jest większa niż szerokość tablicy (" + colNumber+ ").\n"
                                                + "> błąd: " +  textToPrint + "\n"
                                                + "> wprowadzony tekst: " + newRowText);
                                    }
                                }
                            }
                            else {
                                textToPrint = previousSymbol + Character.toString(c);;
                                throw new IllegalArgumentException("[v8] Pierwszy nr przedziału (" + startIndex + ") jest większy niż drugi (" + endIndex+ ").\n"
                                        + "> błąd: " +  textToPrint + "\n"
                                        + "> wprowadzony tekst: " + newRowText);
                            }
                        }
                    }
                }

                previousSymbol = "";
            }
        }

        for(int i = 0; i < colNumber; i++){
            String c = symbolsList.get(i);
            currentText += c;
        }

        return currentText;
    }

    protected int changeToNumber(String numberText){
        try{
            int number = Integer.valueOf(numberText) ;
        }
        catch(NumberFormatException e){
            throw new IllegalArgumentException("[v13] Wprowadzony tekst \"" + numberText + "\" nie jest liczbą lub zawiera niedozwolone znaki.");
        }
        return Integer.parseInt(numberText);
    }


}
