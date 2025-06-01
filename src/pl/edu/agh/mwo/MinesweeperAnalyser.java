package pl.edu.agh.mwo;

public class MinesweeperAnalyser {

    public void fillIn(MinesweeperBoard board) {
        char[][] mines = board.getMines();

        for (int i = 0; i < mines.length; i++) {
            for (int j = 0; j < mines[i].length; j++) {
                if (mines[i][j] != '*') {
                    int value = 0;
                    for(int row= i-1; row<= i+1; row++){
                        if(row >= 0 && row < mines.length){
                            for(int col=j-1; col<=j+1; col++){
                                if(col >= 0 && col < mines[row].length){

                                    if(mines[row][col] == '*'){
                                        value++;
                                    }
                                }
                            }
                        }
                    }
                    mines[i][j] = Character.forDigit(value,10);
                }
            }
        }





    }
}
