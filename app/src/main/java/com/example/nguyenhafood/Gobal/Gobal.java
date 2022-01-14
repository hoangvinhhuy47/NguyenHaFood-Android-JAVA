package com.example.nguyenhafood.Gobal;

import com.example.nguyenhafood.Model.Orders.DataOrder;

public class Gobal {

    public static int getLoginStatus() {
        return LoginStatus;
    }

    public static void setLoginStatus(int loginStatus) {
        LoginStatus = loginStatus;
    }

    public static int getSiteID() {
        return SiteID;
    }

    public static void setSiteID(int siteID) {
        SiteID = siteID;
    }

    private static int SiteID;
    public static String GuiID = "MjQ7VHJ1bmd0YW1QaHVjO0tCTF8yNDs2MzcwNzM3NTMxOTYwNzAwMDA=";

    public static String getUserName() {
        return UserName;
    }

    public static void setUserName(String userName) {
        UserName = userName;
    }

    public static boolean ShowMenu = false;
    public static String UserName;
    private static int LoginStatus = 0;
    public static String IDImage = "https://demobanhang.softwareviet.com";
    public static String WebDomain = "https://nguyenhafood.vn/";

    public static String getID() {
        return ID;
    }

    public static void setID(String ID) {
        Gobal.ID = ID;
    }

    public static String ID;
    public static String UserID = "";

    public static String getGuiID() {
        return GuiID;
    }

    public static void setGuiID(String guiID) {
        GuiID = guiID;
    }

    public static String getIDImage() {
        return IDImage;
    }

    public static void setIDImage(String IDImage) {
        Gobal.IDImage = IDImage;
    }


    public static String getCardID() {
        return CardID;
    }

    public static void setCardID(String cardID) {
        CardID = cardID;
    }

    public static String CardID;
    public static String AddCartSTT = null;
    public static int SizeProduct=0;

    public static boolean isShowMenu() {
        return ShowMenu;
    }

    public static void setShowMenu(boolean showMenu) {
        ShowMenu = showMenu;
    }

    public static String getWebDomain() {
        return WebDomain;
    }

    public static void setWebDomain(String webDomain) {
        WebDomain = webDomain;
    }

    public static String getAddCartSTT() {
        return AddCartSTT;
    }

    public static void setAddCartSTT(String addCartSTT) {
        AddCartSTT = addCartSTT;
    }

    public static int getSizeProduct() {
        return SizeProduct;
    }
    public static String ShowFragment="";
    public  static  DataOrder dataOrders;
    public static String ShowAddress ="";
    public static String CellPhone;
    public static String Gmail;
    public static String ToTalPrice;
    public static String IDProduct="";
    public static String IDCatoryProduct="";
    public static int Electricbill=1;
    public static String AdressAcount="";
    public static String CreateDay="";
    public static int PriceOrder;
    public static String IDORDER ;
    public static String PromotionCode="";
    public static String TitlePromotionCode="";


}

