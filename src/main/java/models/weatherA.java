package models;

public class weatherA {
    int id;
    String main;
    String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public weatherA(int id, String main, String description) {
        this.id = id;
        this.main = main;
        this.description = description;
    }
}
