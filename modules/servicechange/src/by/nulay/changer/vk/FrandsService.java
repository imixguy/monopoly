package by.nulay.changer.vk;

/**
 * Created by miha on 31.07.2014.
 */
public interface FrandsService<T extends Frands,ID extends Number> {

    T addFrands(T frands);

    boolean isAddFrands(T frands);

    boolean isAddFrandsToUser(T frands);
}
