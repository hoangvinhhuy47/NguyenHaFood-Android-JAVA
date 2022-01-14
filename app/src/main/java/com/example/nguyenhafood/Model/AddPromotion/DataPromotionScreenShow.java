package com.example.nguyenhafood.Model.AddPromotion;

public class DataPromotionScreenShow {
    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getQuanliti() {
        return Quanliti;
    }

    public void setQuanliti(int quanliti) {
        Quanliti = quanliti;
    }

    private String ProductName;
    private String Image;
    private int Quanliti;

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    private double Price;
}
