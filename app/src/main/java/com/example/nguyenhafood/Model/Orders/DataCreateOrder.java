package com.example.nguyenhafood.Model.Orders;

public class DataCreateOrder {
    public String OrderID;
    public String OrderCode;
    public String CustomerID;
    public String CustomerName;
    public String CustomerPhone;
    public String Notes;

    public String getShipAddress() {
        return ShipAddress;
    }

    public void setShipAddress(String shipAddress) {
        ShipAddress = shipAddress;
    }

    public String ShipAddress;
    public int PaymentMethodID;

    public int getTransportTypeID() {
        return TransportTypeID;
    }

    public void setTransportTypeID(int transportTypeID) {
        TransportTypeID = transportTypeID;
    }

    public int TransportTypeID;

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

    public String getTaxNotes() {
        return TaxNotes;
    }

    public void setTaxNotes(String taxNotes) {
        TaxNotes = taxNotes;
    }

    public String TaxNotes;
    public int VATMethodID;
    public String TaxCode;
    public String TaxCompanyName;
    public String TaxCompanyAddress;
    public String TaxCompanyEmail;



}
