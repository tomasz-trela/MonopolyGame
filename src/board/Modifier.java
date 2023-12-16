package board;

public class Modifier {
    private int actualchanges;
    private Change[] modifiers = new Change[];
    public Modifier(){
        actualchanges=0;
        modifiers=null;
    }
    public int getActualchanges(){
        return actualchanges;
    }
    public void setActualchanges(int actualchanges){
        this.actualchanges=actualchanges;
    }
    public void addChange(Change a){
        modifiers[actualchanges]=a;
        actualchanges++;
    }
    public Change getChange(int number){
        return modifiers[number];
    }
    
}
