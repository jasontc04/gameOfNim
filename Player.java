public class Player {
    
    private String name;
    private int points;

    Player() {
        name = "";
        points = 0;
    }

    public String getName() {
        return name;
    }
    public int getPoints() {
        return points;
    }
    public void setName(String inputName) {
        name = inputName;
    }
    public void setPoints() {
        points += 1;
    }


}
