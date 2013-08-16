/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package allbinary.business.advertisement;

import allbinary.business.advertisement.image.AdvertisementImageInterface;
import allbinary.business.advertisement.product.AdvertisementProductInterface;
import allbinary.business.advertisement.thumbnail.AdvertisementThumbnailInterface;
import allbinary.business.user.commerce.money.Money;
import allbinary.logic.control.contraints.display.browser.DisplayInBrowserContraintsInterface;

/**
 *
 * @author user
 */
public class Advertisement 
        implements AdvertisementInterface
{

    private String artist;
    private String artistLink;
    private String title;
    private String dateAdded;
    private String directory;
    private String framedLink;
    private int rank;

    private Money listPrice;
    private Money ourPrice;
    private AdvertisementImageInterface image;
    private AdvertisementProductInterface product;
    private AdvertisementThumbnailInterface thumbnail;
    private final DisplayInBrowserContraintsInterface displayInBrowserContraintsInterface;

    public Advertisement()
    {
        this.displayInBrowserContraintsInterface = null;
    }

    /**
     * @return the artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * @param artist the artist to set
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * @return the artistLink
     */
    public String getArtistLink() {
        return artistLink;
    }

    /**
     * @param artistLink the artistLink to set
     */
    public void setArtistLink(String artistLink) {
        this.artistLink = artistLink;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the dateAdded
     */
    public String getDateAdded() {
        return dateAdded;
    }

    /**
     * @param dateAdded the dateAdded to set
     */
    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    /**
     * @return the directory
     */
    public String getDirectory() {
        return directory;
    }

    /**
     * @param directory the directory to set
     */
    public void setDirectory(String directory) {
        this.directory = directory;
    }

    /**
     * @return the framedLink
     */
    public String getFramedLink() {
        return framedLink;
    }

    /**
     * @param framedLink the framedLink to set
     */
    public void setFramedLink(String framedLink) {
        this.framedLink = framedLink;
    }

    /**
     * @return the listPrice
     */
    public Money getListPrice() {
        return listPrice;
    }

    /**
     * @param listPrice the listPrice to set
     */
    public void setListPrice(Money listPrice) {
        this.listPrice = listPrice;
    }

    /**
     * @return the ourPrice
     */
    public Money getOurPrice() {
        return ourPrice;
    }

    /**
     * @param ourPrice the ourPrice to set
     */
    public void setOurPrice(Money ourPrice) {
        this.ourPrice = ourPrice;
    }

    /**
     * @return the rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * @param rank the rank to set
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * @return the image
     */
    public AdvertisementImageInterface getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(AdvertisementImageInterface image) {
        this.image = image;
    }

    /**
     * @return the product
     */
    public AdvertisementProductInterface getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(AdvertisementProductInterface product) {
        this.product = product;
    }

    /**
     * @return the thumbnail
     */
    public AdvertisementThumbnailInterface getThumbnail() {
        return thumbnail;
    }

    /**
     * @param thumbnail the thumbnail to set
     */
    public void setThumbnail(AdvertisementThumbnailInterface thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * @return the displayInBrowserContraintsInterface
     */
    public DisplayInBrowserContraintsInterface getDisplayInBrowserContraintsInterface() {
        return displayInBrowserContraintsInterface;
    }
}
