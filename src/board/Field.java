package board;

public abstract class Field {
    private String name;
    public Field(String name) {
        this.name = name;
    }
    public Field() {
        this.name = "Start";
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String toString() {
        return "Name: " + this.name + "\n";
    }
}


