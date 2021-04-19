/*
    Name: Ivan Huerta-Bernal
    Purpose: Project, CS 2336.002

    Design: The IBoard interface
 */
public interface IBoard {
    void print();
    boolean makeMove(int board, int slot, String playerMark);
    int getColSize();
    int getRowSize();
    void setDimensions(int rows, int cols);
    void setPrevBoard(int board);
    void setPrevSlot(int slot);
    int getPrevBoard();
    int getPrevSlot();
    boolean isFull();
}
