import java.util.Arrays;

/*
    Name: Ivan Huerta-Bernal
    Purpose: Project, CS 2336.002

    Extends DefaultBoard. Adds and and overrides methods to ensure data is initialized and displayed correctly.
    BigBoard relies heavily on modular arithmetic for initializing and printing the board.
    Additionally, isSubBoardFull and openBoxes were added to be able to track the next available legal move.

    Use a BoardTracker object to forward all/any wins in the subBoards and having them tracked on the main board.
 */

public class BigBoard extends DefaultBoard {

    protected BoardTracker tracker;

    public BigBoard() {
        super(9,9);
        initTracker();
        initBoxes();
    }


    public BigBoard(int rowSize, int colSize) {
        super(rowSize, colSize);
        initTracker();
        initBoxes();
    }

    private void initTracker(){
        tracker = new BoardTracker(3);
    }

    // Could have been designed better.
    // Used to return whether a given subBoard is full or not
    // This is used by the Driving UTT_Game class to determine
    // if a the option to pick a new board is given.
    // The additional call for printing information could be better placed.
    public boolean isSubBoardFull(int boardNum){
        int [] openBoxesL = openBoxes(boardNum);
        Arrays.sort(openBoxesL);

        System.out.println("Playing on subBoard#" + getPrevSlot());
        System.out.println("You have " + openBoxesL.length + " slots to choose from." );
        System.out.print("Available slots:: ");

        if(openBoxesL.length != 0){
        for(int i = 0; i < openBoxesL.length; i++){
            System.out.print(openBoxesL[i] + "  ");
        }
        }
        System.out.println("");


        if(openBoxesL.length==0){
            return true;}

        else {
            return false;
        }
    }


    // openBoxes was left half done and unoptimized.
    // used to obtain all the open slots in the subBoard passed onto it.
    // was supposed to provide a waayy better version of the "provide legal" moves requirement.
    public int[] openBoxes(int board) {
        int [] openBoxes;
        int boardIndexZero = 0;
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

        int index = 0;
        //  the method first goes through the subBoard and checks the count of the available slots
        for(int i = boardIndexZero; i < boardIndexZero+3; i++){
            if(boxes[i].isAvailable()){
                index++;
            }

            if(boxes[i+getRowSize()].isAvailable()) {
                index++;
            }

            if(boxes[i+2*getColSize()].isAvailable()) {
                index++;
            }
        }

        // This it runs again to copy all the open slots values
        //
        openBoxes = new int[index];
        index = 0;
        for(int i = boardIndexZero; i < boardIndexZero+3; i++){

            if(boxes[i].isAvailable()){
                openBoxes[index] = Integer.parseInt(boxes[i].print());
                index++;
            }

            if(boxes[i+getRowSize()].isAvailable()){
                openBoxes[index] = Integer.parseInt(boxes[i+getRowSize()].print());
                index++;
            }

            if(boxes[i+2*getColSize()].isAvailable()){
                openBoxes[index] = Integer.parseInt(boxes[i+2*getRowSize()].print());
                index++;

            }
        }
        return openBoxes;
    }

        // BEGIN @Overriding parent class DefaultBoard

    // modular arithmetic saves the day again!
    // initiates the boxes with the correct placeHolder data with the help of mod, for loops and if statements
    private void initBoxes() {
        boxes = new Box[getColSize() * getRowSize()];
        for (int i = 0, mult= -1, board= -1; i < boxes.length; i++) { // new array of boxes that will be used to represent the
            if(i%getRowSize() == 0){
                mult++;
                board++;
            }//reset the value of the multiplier every 3rd row

            if(i % (boxes.length/(3)) == 0) {
                mult= 0;
            } // reset the value of the multiplier every 3rd

            Box b = new Box(board, i, ""+(((i % 9) % 3) + ( mult* 3)));
            boxes[i] = b;
        }
    }

    // print() is a bit messy, but the board prints exactly as I wanted it to. simple.
    public void print(){
        System.out.println("\n");
        if(tracker.getMoveCount() != 0) {
         System.out.println("- - O P S   L A S T   M O V E - -");
            printBoardStats();
        }
        System.out.println("");
        System.out.print("- - - - - - N E W   M O V E - - - - -");

        int subBoardIndex = 0;
        for(int i = 0; i < boxes.length; i++){
            if (i != 0 && i%getColSize()==0)
                System.out.println("|");

            if(i % (boxes.length/3) == 0) {
                System.out.println();
                System.out.println("| subBoard#" + subBoardIndex++ + "| " + "subBoard#" + subBoardIndex++ + "| " + "subBoard#" + subBoardIndex++ + "|");
            }
            if(i%3==0)
                System.out.print("|  ");
            System.out.printf("%3s",boxes[i].print() + "  ");
        }
        System.out.println("|\n");
    }

    // a simple method that ecapsulate some import data for the player, what moves were picked by the opponent
    private void printBoardStats() {
        System.out.println("SUBBOARD PLAYED:: " + getPrevBoard());
        System.out.println("SLOT PLAYED:: " + this.getPrevSlot());
    }



    public boolean makeMove(int board, int slot, String mark){

        int boardIndexZero = 0;
        int moveChoice = 0;

        // relies on this switch statement to "translate" the single digit board entry to the the index 0 of the
        // "subBoard" of the subBoard chosen.
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
        // able to target the appropriate boxes with the help of mod!
        if (slot % 3 == 0 && slot != 0) {
            if (slot / 3 == 1) {
                moveChoice = boardIndexZero + 9;
            }
            if (slot / 3 == 2) {
                moveChoice = boardIndexZero + 18;
            }
        }
        if(slot == 0){
            moveChoice = boardIndexZero;
        } else if (slot < 3){
            moveChoice = boardIndexZero + slot;

        } else if(slot < 6 && slot%3 !=0) {

            moveChoice = boardIndexZero + (slot%3) + 9 ;

        } else if (slot < 9 && slot%3 !=0) {
            moveChoice = boardIndexZero + (slot%3) + 18;
        }

        if(!boxes[moveChoice].isAvailable()){
            return false;
        }


        setPrevBoard(board); // both methods are key to being able to enforce the rule of having a player only
        setPrevSlot(slot); // play on the board determined by the previous slot played these method get called by the driving UTTT_Game class

        return boxes[moveChoice].setPlaceHolder(mark);
    }

    // END Overriding parent class DefaultBoard

} // GiantBoard class end

