package by.imix.games.monopoly.card;

/**
 * Created by miha on 16.12.2014.
 */
public abstract class CardDefault implements Card{
    //название карты
    private String name;
    //Изображение карты
    private String image;

    protected CardDefault() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
