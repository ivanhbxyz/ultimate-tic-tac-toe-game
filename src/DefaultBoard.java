/*
    Name: Ivan Huerta-Bernal
    Purpose: Project, CS 2336.002

    Design: A standard, non abstract implementation of a TicTacToe board.
    DefaultBoard implements IBoard

 */

public class DefaultBoard implements IBoard {

    protected Box[] boxes;
    private int boardRowSize;
    private int boardColSize;
    private int prevSlot;
    private int prevBoard;

    public DefaultBoard(){
        this(3,3); // create a standard TTT board
    }

    public DefaultBoard(int rowSize, int colSize){
        setDimensions(rowSize, colSize);
        initBoxes();
    }

    private void initBoxes(){
        boxes = new Box[getColSize() * getRowSize()];
        for (int i = 0; i < boxes.length; i++) { // new array of boxes that will be used to represent the board
            Box b = new Box(0, i, i+"");// a standard TTT board has only 1 board, board 0
            boxes[i] = b;
        }
    }

    public String getMark(int box){
        return boxes [box].print();
    }

    // BEGIN @Overriding IBoard //
    @Override
    public boolean makeMove(int board, int slot, String mark){
        return boxes[slot].setPlaceHolder(mark);
    }

    @Override
    public void setDimensions(int row, int col){
        if(row < 3 || col < 3) {
            System.out.println("min board size 3*3");
        } else {
            this.boardColSize = col;
            this.boardRowSize = row;
        }
    }

    @Override
    public void print(){
        System.out.println("");
        System.out.println("--- MAIN BOARD STANDINGS ---");
        System.out.println("| Main Board |");
        for(int i = 0; i < boxes.length; i++){
            if (i != 0 && i%boardColSize==0)
                System.out.println();
            System.out.printf("%4s",boxes[i].print());
        }
        System.out.println("\n");
    }

    @Override
    public boolean isFull(){
        for(Box b : boxes) {
            if(b.isAvailable())
                return false;
        }
        return true;
    }

    @Override
    public int getColSize(){
        return boardColSize;
    }

    @Override
    public int getRowSize(){
        return boardRowSize;
    }

    @Override
    public int getPrevBoard(){
        return prevBoard;
    }
    @Override
    public int getPrevSlot(){
        return prevSlot;
    }

    @Override
    public void setPrevBoard(int slot){
        prevBoard = slot;
    }

    @Override
    public void setPrevSlot(int slot){
        prevSlot = slot;
    }

    // * END @Overriding IBoard //

}