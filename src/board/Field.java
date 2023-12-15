package board;

public abstract class Field {
    private String name;
    private  int index; //dodalem indeks aby moc rozrozniac pole w metodzie gracza
    public Field(String name, int index) {
        this.name = name;
        this.index = index;
    }
    public Field() {
        this.name = "Berlin";
    }
    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
