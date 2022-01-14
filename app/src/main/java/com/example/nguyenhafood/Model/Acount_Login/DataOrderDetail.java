package com.example.nguyenhafood.Model.Acount_Login;


public class DataOrderDetail {
    public String OrderID;
    public String OrderCode;
    public double Amount;

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        TotalAmount = totalAmount;
    }

    public double TotalAmount;

    public String getShipAddress() {
        return ShipAddress;
    }

    public void setShipAddress(String shipAddress) {
        ShipAddress = shipAddress;
    }

    public String ShipAddress;

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String CreatedDate;

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

    public String getTaxNotes() {
        return TaxNotes;
    }

    public void setTaxNotes(String taxNotes) {
        TaxNotes = taxNotes;
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

    public int getTransportTypeID() {
        return TransportTypeID;
    }

    public void setTransportTypeID(int transportTypeID) {
        TransportTypeID = transportTypeID;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getPaymentMethodID() {
        return PaymentMethodID;
    }

    public void setPaymentMethodID(int paymentMethodID) {
        PaymentMethodID = paymentMethodID;
    }

    public String CustomerID;
    public String CustomerName;
    public String CustomerPhone;
    public String Notes;
    public String TaxNotes;
    public int VATMethodID;
    public String TaxCode;
    public String TaxCompanyName;
    public String TaxCompanyAddress;
    public String TaxCompanyEmail;
    public int TransportTypeID;
    public int Status;
    public int PaymentMethodID;

    public String getCancelReason() {
        return CancelReason;
    }

    public void setCancelReason(String cancelReason) {
        CancelReason = cancelReason;
    }

    public String CancelReason;
}

