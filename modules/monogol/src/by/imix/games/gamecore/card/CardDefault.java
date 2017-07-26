package by.imix.games.gamecore.card;

/**
 * Created by miha on 16.12.2014.
 * Class for any card to game, abstract class for minimum necessary functions
 */
public abstract class CardDefault implements Card {
    //card name
    private String name;
    //path to image card
    private String image;

    /**
     * Default constructor for card
     */
    protected CardDefault() {
    }

    /**
     * Method return Name card
     * @return name card
     */
    public String getName() {
        return name;
    }

    /**
     * Method set name card
     * @param name - name card
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method return path to image card
     * @return path to image card
     */
    public String getImage() {
        return image;
    }

    /**
     * Method set for path to image card
     * @param image path to image card
     */
    public void setImage(String image) {
        this.image = image;
    }
}
