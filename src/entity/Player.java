package entity;

public class Player {
    private final String name;

    private final String color;

    private final boolean isLocalPlayer;

    Player(String name, String color, boolean isLocalPlayer) {
        this.name = name;
        this.color = color;
        this.isLocalPlayer = isLocalPlayer;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public boolean getIsLocalPlayer() {
        return isLocalPlayer;
    }

}
