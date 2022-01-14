package com.example.nguyenhafood.Model.Orders;

public class DataOrder {
    public static String OrderID = "";
    public String OrderCode;
    public String CustomerID;
    public String CustomerName = "";
    public String CustomerPhone = "";
    public String Notes;

    public static String getShipAddress() {
        return ShipAddress;
    }

    public static void setShipAddress(String shipAddress) {
        ShipAddress = shipAddress;
    }

    public static String ShipAddress = "";
    public static int PaymentMethodID;

    public int getTransportTypeID() {
        return TransportTypeID;
    }

    public void setTransportTypeID(int transportTypeID) {
        TransportTypeID = transportTypeID;
    }

    public static int TransportTypeID;

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getOrderCode() {
        return OrderCode;
    }

    public void setOrderCode(String orderCode) {
        OrderCode = orderCode;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerPhone() {
        return CustomerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        CustomerPhone = customerPhone;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }


    public int getPaymentMethodID() {
        return PaymentMethodID;
    }

    public void setPaymentMethodID(int paymentMethodID) {
        PaymentMethodID = paymentMethodID;
    }

    public int getVATMethodID() {
        return VATMethodID;
    }

    public void setVATMethodID(int VATMethodID) {
        this.VATMethodID = VATMethodID;
    }

    public String getTaxCode() {
        return TaxCode;
    }

    public void setTaxCode(String taxCode) {
        TaxCode = taxCode;
    }

    public String getTaxCompanyName() {
        return TaxCompanyName;
    }

    public void setTaxCompanyName(String taxCompanyName) {
        TaxCompanyName = taxCompanyName;
    }

    public String getTaxCompanyAddress() {
        return TaxCompanyAddress;
    }

    public void setTaxCompanyAddress(String taxCompanyAddress) {
        TaxCompanyAddress = taxCompanyAddress;
    }

    public String getTaxCompanyEmail() {
        return TaxCompanyEmail;
    }

    public void setTaxCompanyEmail(String taxCompanyEmail) {
        TaxCompanyEmail = taxCompanyEmail;
    }

    public static String getTaxNotes() {
        return TaxNotes;
    }

    public static void setTaxNotes(String taxNotes) {
        TaxNotes = taxNotes;
    }

    public static String TaxNotes="";
    public static int VATMethodID;
    public static String TaxCode="";
    public static String TaxCompanyName="";
    public static String TaxCompanyAddress="";
    public static String TaxCompanyEmail="";
}
