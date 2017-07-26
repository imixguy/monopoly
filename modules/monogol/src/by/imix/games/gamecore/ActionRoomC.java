package by.imix.games.gamecore;

/**
 * Created by miha on 25.12.2014.
 * All available action for room
 */
public class ActionRoomC implements AvailableAction{
    private boolean createRoom;

    /**
     * Check that room created
     * @return true - created
     */
    public boolean isCreateRoom() {
        return createRoom;
    }

    /**
     * set created room
     */
    public void setCreateRoom(boolean createRoom) {
        this.createRoom = createRoom;
    }


    /**{@inheritDoc}*/
    @Override
    public void clearAction() {
        createRoom=false;
    }
}
