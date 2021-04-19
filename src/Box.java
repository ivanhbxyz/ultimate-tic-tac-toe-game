/*
    Name: Ivan Huerta-Bernal
    Purpose: Project, CS 2336.002

    Design: The Box class serves as a simple object that contains the players' mark, boxIndex and boardIndex members
    This class is relied upon by the DefaultBoard and BigBoard classes which comprise of an array
    of either nine or eighty-one Box objects.
    Opting to view the boards as being made up of Box objects allow for better decoupling and organization.
 */

public class Box {
    private int boxIndex;
    private int boardIndex;
    private String placeHolder;

    // Constructor. Every

    public Box(int boardIndex, int boxIndex, String placeHolder){
        this.boardIndex = boardIndex;
        this.boxIndex = boxIndex;
        initPlaceHolder(placeHolder);
    }
    public String print() {
        return this.placeHolder;
    }

    public boolean setPlaceHolder(String placeHolder){
        if(isAvailable()){
            this.placeHolder = placeHolder;
            return true;
        }
        return false;
    }

    private void initPlaceHolder(String placeHolder) {this.placeHolder = placeHolder; }
    protected boolean isAvailable(){ return Character.isDigit(this.placeHolder.charAt(0));}

    // used to access the box's board and index data if ever required by the board
    public int getBoardIndex() {
        return boardIndex;
    }
    public int getBoxIndex() {
        return boxIndex;
    }

}