package board;

public abstract class Field {
    private String name;
    public Field(String name) {
        this.name = name;
    }
    public Field() {
        this.name = "Berlin";
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}


