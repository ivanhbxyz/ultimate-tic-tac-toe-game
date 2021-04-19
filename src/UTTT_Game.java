/*
    Name: Ivan Huerta-Bernal
    Purpose: Project, CS 2336.002
    The UTT_Game class brings all the components together.
    UTTT_Game takes of the actual mechanics for ultimate tic tac toe. It Instantiates and runs APlayer and BigBoard objects.
 */
public class UTTT_Game {
    private APlayer [] players = new APlayer[2]; // Sets two players. These 2 could be Human or CPU player objects
    private int currentPlayerIndex = -1;
    private final int STD_TTT_SIZE = 3; // the math used to calculate winning combos rely on using the col/row size of a standard TTT board
    private BigBoard gameBoard;

    public UTTT_Game() {
        setBoard();
    }
    private void setBoard(){
        this.gameBoard = new BigBoard(9,9);
    }

    // set players
    public void setPlayers(APlayer player1, APlayer player2) {
        players[0] = player1;
        players[1] = player2;
    }

    public void start() { // method that controls the actual mechanics of the game.
        System.out.println("Welcome to Ultimate Tic-Tac-Toe!");
        System.out.println("The game has started...");
        gameBoard.print();
        do {
            switchPlayer();

            if(gameBoard.tracker.getMoveCount() == 0) {
                gameBoard.tracker.setMoveCount(1);
                while (!gameBoard.makeMove(players[currentPlayerIndex].selectBoardValue(gameBoard.getRowSize()),
                        players[currentPlayerIndex].selectSlotValue(gameBoard.getRowSize()), players[this.currentPlayerIndex].getMark())) ;


            } else if (gameBoard.isSubBoardFull(gameBoard.getPrevSlot())) {
                System.out.println("The subBoard Selected is full.\nPlease choose another subBoard.");

                while (!gameBoard.makeMove(players[currentPlayerIndex].selectBoardValue(gameBoard.getRowSize()),
                        players[currentPlayerIndex].selectSlotValue(gameBoard.getRowSize()), players[this.currentPlayerIndex].getMark())) ;
            } else {

                while (!gameBoard.makeMove(players[currentPlayerIndex].selectBoardValue(gameBoard.getRowSize(), gameBoard.getPrevSlot()),
                        players[currentPlayerIndex].selectSlotValue(gameBoard.getRowSize()), players[this.currentPlayerIndex].getMark())) ;
            }
            gameBoard.print();
        }while(!gameOverCheck());
    }


    // ---------------------- ALL GAME RULE/LOGIC BELOW --------- //

    private void switchPlayer() {
        if(this.currentPlayerIndex != 0)
            this.currentPlayerIndex = 0;
        else
            this.currentPlayerIndex = 1;
    }

    private boolean gameOverCheck() { // will be ran after every move

        subBoardWon(); // local method that a winner if found in the one of the 9 "subBoards"

        if(gameBoard.tracker.isGameWon()) { // will check if the the game tracker has found a winning combo on the "main board"
            System.out.println(players[currentPlayerIndex].getName()+ " is WINNER!");
            return true;
        } else if (gameBoard.isFull() || gameBoard.tracker.isTrackBoardFull()){
            System.out.println("Tie!");
            return true;
        }
        return false;
    }

    private void subBoardWon(){
        // call methods that check is winning combo is found in the "subBoard" that was just played on
        if(checkSubBoardRows(defaultToBig(gameBoard.getPrevBoard())) || checkSubBoardCols(defaultToBig(gameBoard.getPrevBoard())) ||
                checkSubBoardDiagLB(defaultToBig(gameBoard.getPrevBoard())) || checkSubBoardDiagRL(defaultToBig(gameBoard.getPrevBoard()))) {

            // if a winner is found then. log the subBoard won by sending its index to the BoardTracker object
            gameBoard.tracker.logSubBoardWon(gameBoard.getPrevBoard(), players[currentPlayerIndex].getMark());
        }
    }

    // Used to "translate" the "gameBoard.getPrevBoard" into an index value that can be used by the checkSubBoard[row, col, diag] methods
    private int defaultToBig(int board){
        int boardIndexZero=0;
        switch (board) { // IDE suggested an enhanced switch
            case 1 -> boardIndexZero = 3;
            case 2 -> boardIndexZero = 6;
            case 3 -> boardIndexZero = 27;
            case 4 -> boardIndexZero = 30;
            case 5 -> boardIndexZero = 33;
            case 6 -> boardIndexZero = 54;
            case 7 -> boardIndexZero = 57;
            case 8 -> boardIndexZero = 60;
        }

        return boardIndexZero;
    }

    /// ALL METHODS BELOW CHECK SUB BOARDS //
    private boolean checkSubBoardCols(int startOfCol) {
        for (int col = 0; col < STD_TTT_SIZE; col++) {
            if (checkSubBoardCol(startOfCol+col)) {
                System.out.println("subBoard#" + gameBoard.getPrevBoard() + " is captured via a COLUMN WIN.");
                return true;
            }
        }
        return false;
    }

    private boolean checkSubBoardCol(int col) {
        int count = 0;
        for(int row = 0; row < STD_TTT_SIZE; row++){
            if (gameBoard.getMark(col+(9*row)).equals(players[currentPlayerIndex].getMark()))
                count++;
        }

        if(count == gameBoard.tracker.getScoreToWin()){
            return true;
        }
        return false;
    }

    private boolean checkSubBoardRows(int startOfRow){
        for(int row = 0; row < STD_TTT_SIZE; row++){ // When calling gameTracker.getGameRowSize should it be 9 or 3?
            if(checkSubBoardRow(startOfRow+(row*9))) {
                System.out.println("subBoard#" + gameBoard.getPrevBoard() + " is captured via a ROW WIN.");
                return true;
            }
        }
        return false;
    }
    private boolean checkSubBoardRow(int row) { // This those the actual checking of rows
        int count = 0;
        for (int col = 0; col < STD_TTT_SIZE; col++){
            if(gameBoard.getMark(row+col).equals(players[currentPlayerIndex].getMark()))
                count++;
        }

        if(count == gameBoard.tracker.getScoreToWin()) return true;
        return false;
    }

    private boolean checkSubBoardDiagLB(int boardZeroIndex){
        int count = 0;

        for(int row = 0,  col = STD_TTT_SIZE-1; row < STD_TTT_SIZE && col > -1; col--, row++){
            if(gameBoard.getMark(boardZeroIndex+col+(row*9)).equals(players[currentPlayerIndex].getMark())){
                count++;
            }
        }
        if(count == gameBoard.tracker.getScoreToWin()) {
            System.out.println("subBoard#" + gameBoard.getPrevBoard() + " is captured via a LEFT-RIGHT DIAG WIN.");
            return true;
        }
        return false;
    }

    private boolean checkSubBoardDiagRL(int boardZeroIndex){
        int count = 0;
        for (int row = 0, col = 0; col < STD_TTT_SIZE && row < STD_TTT_SIZE; row++, col++)
            if(gameBoard.getMark((boardZeroIndex+(9*row))+col).equals(players[currentPlayerIndex].getMark()))
                count++;

        if (count == gameBoard.tracker.getScoreToWin()) {
            System.out.println("subBoard#" + gameBoard.getPrevBoard() + " is captured via a RIGHT-LEFT DIAG WIN.");
            return true;
        }
        return false;
    }
    /// ALL METHODS ABOVE CHECK SUB BOARDS //

} //class end
