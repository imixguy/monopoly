package by.imix.games.gamecore;

/**
 * Created by miha on 25.12.2014.
 */
public class ActionRoomC implements AvailableAction{
    private boolean createRoom;

    public boolean isCreateRoom() {
        return createRoom;
    }

    public void setCreateRoom(boolean createRoom) {
        this.createRoom = createRoom;
    }

    @Override
    public void clearAction() {
        createRoom=false;
    }
}
