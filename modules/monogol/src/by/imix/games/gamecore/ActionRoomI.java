package by.imix.games.gamecore;

/**
 * Created by miha on 06.01.2015.
 * All available action for room
 */
public enum ActionRoomI implements ActionRoom {
    CREATE_ROOM ("createRoom");

    private String key="";

    ActionRoomI(String key) {
        this.key=key;
    }

    public String getKey() {
        return key;
    }

}
