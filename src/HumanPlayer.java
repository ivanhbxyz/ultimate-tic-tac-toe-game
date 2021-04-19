import java.util.Scanner;

/*
 *
 * HumanPlayer extends APlayer.
 *
 * Design: A scanner object is created and used to obtain a human player's row and col picks.
 * selectRowValue and selectColValue are overridden to allow the human player to
 * pass a next move where the input must be between 0 and the (the number of columns - 1).
 */

public class HumanPlayer extends APlayer {

    Scanner input = new Scanner(System.in);
    public HumanPlayer(String name, String mark) {
        super(name, mark);
    }

    @Override
    public int selectSlotValue(int range) {

        System.out.print("Please enter a valid slot number (0 to " + (range-1) + ")" + ":: ");
        int slot;
        do{
            slot = input.nextInt();
           if(slot < 0 || slot > range-1) {
                System.out.print("Invalid slot number! " +"Please choose another (0 to " + (range-1) + ")"  + ":: ");
            }
        } while( slot < 0 || slot > range-1);
        System.out.println("");
        return slot;
    }


    @Override
    public int selectBoardValue(int range) {

      System.out.print(this.getName()+" Please enter a valid subBoard number (0 to " + (range-1) + ")"  + ":: ");
        int board;
        do{
            board = input.nextInt();
            if(board < 0 || board > range-1) {
                System.out.print("Invalid subBoard number!" +" Please choose another (0 to " + (range-1) + ")"  + ":: ");
            }

        } while( board < 0 || board > range-1);
        return board;
    }

    public int selectBoardValue(int range, int boardNum) {

        System.out.println(getName() + " Choose an open slot from subBoard# " + boardNum);
        return boardNum;
    }





}
