package com.example.yashnanavati.catiescloset.Model;

/**
 * Created by Game of Threads
 */


// Donation navagation class that creates the page between item and monetary donations that sends to firebase
public class DonationNavPage {
    public String typeOfDonation;
    public String descDonation;
    public int photoCard;

    public DonationNavPage(String typeOfDonation, String descDonation, int photoCard) {
        this.typeOfDonation = typeOfDonation;
        this.descDonation = descDonation;
        this.photoCard = photoCard;
    }

}
