/*
    Name: Ivan Huerta-Bernal
    Purpose: Project, CS 2336.002

    Design: CPUPlayer extends APlayer. randomNumber() is used to generate the integer that will be passed to the
    Overridden selectRowValue and selectColValue methods so the computer can generate a next move.
 */
public class CPUPlayer extends APlayer{

    public CPUPlayer(String name, String mark) {
        super(name, mark);
    }
    private int randomNumber(int range){
        return (int)(Math.random()*(range));
    }

    @Override
    public int selectSlotValue(int range) {
        return  randomNumber(range);
    }

    @Override
    public int selectBoardValue(int range) {
        return randomNumber(range);
    }

    public int selectBoardValue(int range, int boardNum) {
    	System.out.println("");
        return boardNum;
    }


}