
/*
    Name: Ivan Huerta-Bernal
    Purpose: Project, CS 2336.002

    Design: BoardTracker takes care of all the functionality for scanning the BigBoard for wins.
    The tracker relies on a DefaultBoard object to keep track of the current standing of the main board.
    Part of the functionality for enforcing the game rules are delegated to BoardTracker, mainly managing the wins
    of the main board.
 */

public class BoardTracker {
    private int scoreToWin;
    private int moveCount;
    private DefaultBoard trackBoard;

    public BoardTracker() {
        this(3);
    }

    public BoardTracker(int maxScore){
        setScoreToWin(maxScore);
        this.moveCount = 0;
        setTrackingBoard();
    }

    private void setTrackingBoard() {
        trackBoard = new DefaultBoard();
    }


    public void logSubBoardWon(int boardNum, String mark) {

        if(trackBoard.boxes[boardNum].isAvailable()) {
            trackBoard.makeMove(0, boardNum, mark);
            trackBoard.print();
        } else {
            trackBoard.print();
        }
    }

    public boolean isGameWon(){

        if(checkDiagRL() || checkRows() || checkCols() ||checkDiagLR() || checkDiagRL()) {
            return true;
        }

        return false;
    }

    public boolean isTrackBoardFull(){
        return trackBoard.isFull();
    }

    // START scanning tracking board for winning combinations
    private boolean checkCols() {

        for (int col = 0; col < trackBoard.getColSize(); col++) {
            if (checkCol(col)) {
                System.out.println("Game Won via a Column Win!");
                return true;
            }
        }
        return false;
    }

    private boolean checkCol(int col){
        int count = 0;

        if(!Character.isDigit(trackBoard.getMark(col).charAt(0))) {
            String tempMark = trackBoard.getMark(col);
            for (int row = 0; row < trackBoard.getColSize(); row++)
                if (tempMark.equals(trackBoard.getMark(col + (3 * row))))
                    count++;
            if (count == getScoreToWin()) {

                return true;
            }
        }
        return false;
    }

    private boolean checkRows(){
        for(int row = 0, col = 0; col < trackBoard.getRowSize(); col++){
            if(checkRow(row + (3*col))){
                System.out.println("Game Won via a Row Win!");
                return true;
            }
        }
        return false;
    }

    private boolean checkRow(int row) {

        int count = 0;
        if(!Character.isDigit(trackBoard.getMark(row).charAt(0))) {
            String tempMark = trackBoard.getMark(row);
            for (int col = 0; col < trackBoard.getColSize(); col++)
                if (tempMark.equals(trackBoard.getMark(row + col)))
                    count++;
        }

        if(count == getScoreToWin()) return true;
        return false;
    }

    private boolean checkDiagLR (){
        int count = 0;
            
        if(!Character.isDigit(trackBoard.getMark(0).charAt(0))) {
            String tempMark = trackBoard.getMark(0);
            for (int row = 0, col = 0; row < trackBoard.getRowSize() && col < trackBoard.getColSize(); row++, col++) {
       
                if (tempMark.equals(trackBoard.getMark(col + (3 * row))))
                    count++;
            }
                
            if (count == getScoreToWin()) {
                System.out.println("Game Won via a LR Diag Win!");
                return true;
            }
        }

        return false;
    }

        private boolean checkDiagRL (){
            int count = 0;
            
            if(!Character.isDigit(trackBoard.getMark(2).charAt(0))) {
                String tempMark = trackBoard.getMark(2);
                
                for (int row = 0, col = trackBoard.getColSize()-1; col > -1 && row < trackBoard.getRowSize(); col--, row++) {     	
                    if (tempMark.equals(trackBoard.getMark(col + (3 * row))))
                        count++;
                   
                }

                if (count == getScoreToWin()) {
                    System.out.println("Game Won via a RL Diag Win!");
                    return true;
                }
            }
            return false;
        }
        // END Checking tracking board for winning combos



    // Accessors
    public void setScoreToWin(int maxScore){
        this.scoreToWin = maxScore;
    }
    public int getMoveCount() {
        return this.moveCount;
    }
    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }
    public int getScoreToWin() {
        return scoreToWin;
    }
}
