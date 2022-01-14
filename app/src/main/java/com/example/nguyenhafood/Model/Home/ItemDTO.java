package com.example.nguyenhafood.Model.Home;

import java.util.List;

public class ItemDTO {
    public WebProductCategory getCategoryRoot() {
        return CategoryRoot;
    }

    WebProductCategory CategoryRoot;
    List<Item> ItemList;


    public void setCategoryRoot(WebProductCategory categoryRoot) {
        CategoryRoot = categoryRoot;
    }

    public List<Item> getItemList() {
        return ItemList;
    }

    public void setItemList(List<Item> itemList) {
        ItemList = itemList;
    }

    public List<vw_WebItemWebsite> getRelationList() {
        return RelationList;
    }

    public void setRelationList(List<vw_WebItemWebsite> relationList) {
        RelationList = relationList;
    }

    public List<vw_WebItemWebsite> getSuggestList() {
        return SuggestList;
    }

    public void setSuggestList(List<vw_WebItemWebsite> suggestList) {
        SuggestList = suggestList;
    }

    List<vw_WebItemWebsite> RelationList;
    List<vw_WebItemWebsite> SuggestList;

    public boolean getiSActive() {
        return iSActive;
    }

    public void setiSActive(boolean iSActive) {
        this.iSActive = iSActive;
    }

    public boolean iSActive =false;

    public boolean isiSActive() {
        return iSActive;
    }

    public int getPagemenu() {
        return pagemenu;
    }

    public void setPagemenu(int pagemenu) {
        this.pagemenu = pagemenu;
    }

    public int pagemenu = -1;
}
