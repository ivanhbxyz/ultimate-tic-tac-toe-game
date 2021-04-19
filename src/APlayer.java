/*
    Name: Ivan Huerta-Bernal
    Purpose: Project, CS 2336.002

    Design: Abstract Player class serves as the superclass to the CPUPlayer and HumanPlayer classes.
    Because both players have different ways of performing certain tasks an abstract class works best model.
 */
public abstract class APlayer {
    private String name;
    private String mark;

    public APlayer(String name, String mark){
        setName(name);
        setMark(mark);
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getMark(){
        return mark;
    }
    public void setMark(String mark){
        this.mark = mark;
    }

    public abstract int selectBoardValue(int range);
    public abstract int selectBoardValue(int range, int board);
    public abstract int selectSlotValue(int range);
}
