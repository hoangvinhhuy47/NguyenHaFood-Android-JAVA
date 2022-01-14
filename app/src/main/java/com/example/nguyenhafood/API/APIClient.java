package com.example.nguyenhafood.API;

import com.example.nguyenhafood.Service.AddAddressBookSerVice;
import com.example.nguyenhafood.Service.AddProductLaterBuySerVice;
import com.example.nguyenhafood.Service.AddRemoveFavoriteSerVice;
import com.example.nguyenhafood.Service.AddToCardSerVice;
import com.example.nguyenhafood.Service.CancelOrderSerVice;
import com.example.nguyenhafood.Service.ChangeProductLaterBuyToCartService;
import com.example.nguyenhafood.Service.CreateOrderSerVice;
import com.example.nguyenhafood.Service.DeleteAddressBookSerVice;
import com.example.nguyenhafood.Service.DeleteCartSerVice;
import com.example.nguyenhafood.Service.GetAddressBookService;
import com.example.nguyenhafood.Service.GetAllCitySerViceSerVice;
import com.example.nguyenhafood.Service.GetAllDistrictSerVice;
import com.example.nguyenhafood.Service.GetAllWardSerVice;
import com.example.nguyenhafood.Service.GetCardSerVice;
import com.example.nguyenhafood.Service.GetMoreProductOnCategoryService;
import com.example.nguyenhafood.Service.GetMoreWebItemRelationService;
import com.example.nguyenhafood.Service.GetNewsDetailService;
import com.example.nguyenhafood.Service.GetNewsListNotificationService;
import com.example.nguyenhafood.Service.GetOderService;
import com.example.nguyenhafood.Service.GetOrderDetailService;
import com.example.nguyenhafood.Service.GetProductBuySerVice;
import com.example.nguyenhafood.Service.GetProductDetailService;
import com.example.nguyenhafood.Service.GetProductFavoriteSerVice;
import com.example.nguyenhafood.Service.GetProductLaterBuyService;
import com.example.nguyenhafood.Service.GetProductReViewSerVice;
import com.example.nguyenhafood.Service.GetProductViewSerVice;
import com.example.nguyenhafood.Service.GetPromotionCodeSerVice;
import com.example.nguyenhafood.Service.HomeDTOSerVice;
import com.example.nguyenhafood.Service.LoadMoreProductSerVice;
import com.example.nguyenhafood.Service.MenuDTOSerVice;
import com.example.nguyenhafood.Service.ReBuyService;
import com.example.nguyenhafood.Service.ReViewProductService;
import com.example.nguyenhafood.Service.RemoveSerVice;
import com.example.nguyenhafood.Service.SearchDTOSerVice;
import com.example.nguyenhafood.Service.UpdateAddressBookSerVice;
import com.example.nguyenhafood.Service.UpdateQuantityToCartSerVice;
import com.example.nguyenhafood.Service.UpdateUserInCartSerVice;
import com.example.nguyenhafood.Service.UserService;
import com.example.nguyenhafood.Service.WriteReviewService;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    public static Retrofit getRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl("https://demobanhang.softwareviet.com/api/Retail/")
                .build();
        return retrofit;
    }
    public static UserService getUserService() {
        UserService userService = getRetrofit().create(UserService.class);
        return userService;
    }
    public static HomeDTOSerVice getHomeDTO() {
        HomeDTOSerVice homeDTOSerVice = getRetrofit().create(HomeDTOSerVice.class);
        return homeDTOSerVice;
    }
    public static MenuDTOSerVice getMenuDTO() {
        MenuDTOSerVice menuDTOSerVice = getRetrofit().create(MenuDTOSerVice.class);
        return menuDTOSerVice;
    }
    public static LoadMoreProductSerVice getLoadMoreProductSerVice(){
        LoadMoreProductSerVice loadMoreProductSerVice = getRetrofit().create(LoadMoreProductSerVice.class);
        return  loadMoreProductSerVice;
    }
    public static AddToCardSerVice addToCardSerVice(){
        AddToCardSerVice addToCardSerVice = getRetrofit().create(AddToCardSerVice.class);
        return addToCardSerVice;
    }
    public static GetCardSerVice getCardSerVice(){
        GetCardSerVice getCardSerVice = getRetrofit().create(GetCardSerVice.class);
        return getCardSerVice;
    }
    public static RemoveSerVice removeSerVice(){
        RemoveSerVice removeSerVice = getRetrofit().create(RemoveSerVice.class);
        return  removeSerVice;
    }
    public static UpdateQuantityToCartSerVice updateQuantityToCartSerVice(){
        UpdateQuantityToCartSerVice updateQuantityToCartSerVice = getRetrofit().create(UpdateQuantityToCartSerVice.class);
        return  updateQuantityToCartSerVice;
    }
    public static UpdateUserInCartSerVice updateUserInCartSerVice(){
        UpdateUserInCartSerVice updateUserInCartSerVice = getRetrofit().create(UpdateUserInCartSerVice.class);
        return updateUserInCartSerVice;
    }
    public static DeleteCartSerVice deleteCartSerVice(){
        DeleteCartSerVice deleteCartSerVice = getRetrofit().create(DeleteCartSerVice.class);
        return deleteCartSerVice;
    }
    public static AddRemoveFavoriteSerVice addRemoveFavoriteSerVice(){
        AddRemoveFavoriteSerVice  addRemoveFavoriteSerVice = getRetrofit().create(AddRemoveFavoriteSerVice.class);
        return addRemoveFavoriteSerVice;
    }
    public static SearchDTOSerVice getSearchDTO(){
        SearchDTOSerVice searchDTOSerVice = getRetrofit().create(SearchDTOSerVice.class);
        return searchDTOSerVice;
    }
    public static GetProductDetailService getProductDetailService(){
        GetProductDetailService getProductDetailService =getRetrofit().create(GetProductDetailService.class);
        return getProductDetailService;
    }
    public static GetProductViewSerVice getProductViewSerVice(){
        GetProductViewSerVice getProductViewSerVice=getRetrofit().create(GetProductViewSerVice.class);
        return getProductViewSerVice;
    }
    public static GetAddressBookService getAddressBookService(){
        GetAddressBookService getAddressBookService = getRetrofit().create(GetAddressBookService.class);
        return  getAddressBookService;
    }
    public static GetAllCitySerViceSerVice getAllCitySerViceSerVice(){
        GetAllCitySerViceSerVice getAllCitySerViceSerVice = getRetrofit().create(GetAllCitySerViceSerVice.class);
        return  getAllCitySerViceSerVice;
    }
    public static UpdateAddressBookSerVice updateAddressBookSerVice(){
        UpdateAddressBookSerVice updateAddressBookSerVice = getRetrofit().create(UpdateAddressBookSerVice.class);
        return updateAddressBookSerVice;
    }
    public static GetAllDistrictSerVice getAllDistrictSerVice(){
        GetAllDistrictSerVice getAllDistrictSerVice =getRetrofit().create(GetAllDistrictSerVice.class);
        return  getAllDistrictSerVice;
    }
    public static DeleteAddressBookSerVice deleteAddressBookSerVice(){
        DeleteAddressBookSerVice deleteAddressBookSerVice = getRetrofit().create(DeleteAddressBookSerVice.class);
        return deleteAddressBookSerVice;
    }
    public static AddAddressBookSerVice addAddressBookSerVice(){
        AddAddressBookSerVice addAddressBookSerVice = getRetrofit().create(AddAddressBookSerVice.class);
        return addAddressBookSerVice;
    }
    public static ReViewProductService reViewProductService(){
        ReViewProductService reViewProductService = getRetrofit().create(ReViewProductService.class);
        return  reViewProductService;
    }
    public static WriteReviewService getWriteReviewService(){
        WriteReviewService writeReviewService =getRetrofit().create(WriteReviewService.class);
        return writeReviewService;
    }
    public static GetProductFavoriteSerVice getProductFavoriteSerVice(){
        GetProductFavoriteSerVice getProductFavoriteSerVice =getRetrofit().create(GetProductFavoriteSerVice.class);
        return getProductFavoriteSerVice;
    }
    public static AddProductLaterBuySerVice addProductLaterBuySerVice(){
        AddProductLaterBuySerVice addProductLaterBuySerVice = getRetrofit().create(AddProductLaterBuySerVice.class);
        return  addProductLaterBuySerVice;
    }
    public static GetProductLaterBuyService getProductLaterBuyService(){
        GetProductLaterBuyService getProductLaterBuyService = getRetrofit().create(GetProductLaterBuyService.class);
        return  getProductLaterBuyService;
    }
    public static ChangeProductLaterBuyToCartService changeProductLaterBuyToCartService(){
        ChangeProductLaterBuyToCartService changeProductLaterBuyToCartService = getRetrofit().create(ChangeProductLaterBuyToCartService.class);
        return   changeProductLaterBuyToCartService;
    }
    public static CreateOrderSerVice createOrderSerVice(){
        CreateOrderSerVice createOrderSerVice = getRetrofit().create(CreateOrderSerVice.class);
        return  createOrderSerVice;
    }
    public static GetOderService getOderService(){
        GetOderService getOderService = getRetrofit().create(GetOderService.class);
        return  getOderService;
    }
    public static GetOrderDetailService getOrderDetailService(){
        GetOrderDetailService getOrderDetailService = getRetrofit().create(GetOrderDetailService.class);
        return  getOrderDetailService;
    }
    public static ReBuyService reBuyService(){
        ReBuyService reBuyService = getRetrofit().create(ReBuyService.class);
        return  reBuyService;
    }
    public static CancelOrderSerVice cancelOrderSerVice(){
        CancelOrderSerVice cancelOrderSerVice = getRetrofit().create(CancelOrderSerVice.class);
        return  cancelOrderSerVice;
    }
    public static GetMoreProductOnCategoryService getMoreProductOnCategoryService(){
        GetMoreProductOnCategoryService getMoreProductOnCategoryService = getRetrofit().create(GetMoreProductOnCategoryService.class);
        return  getMoreProductOnCategoryService;
    }
    public static GetMoreWebItemRelationService getMoreWebItemRelationService(){
        GetMoreWebItemRelationService getMoreWebItemRelationService = getRetrofit().create(GetMoreWebItemRelationService.class);
        return  getMoreWebItemRelationService;
    }
    public static GetProductReViewSerVice getProductReViewSerVice(){
        GetProductReViewSerVice getProductReViewSerVice = getRetrofit().create(GetProductReViewSerVice.class);
        return getProductReViewSerVice;
    }
    public static GetProductBuySerVice getProductBuySerVice(){
        GetProductBuySerVice getProductBuySerVice = getRetrofit().create(GetProductBuySerVice.class);
        return  getProductBuySerVice;
    }
    public static GetNewsListNotificationService getNewsListNotificationService(){
        GetNewsListNotificationService getNewsListNotificationService =getRetrofit().create(GetNewsListNotificationService.class);
        return getNewsListNotificationService;
    }
    public static GetNewsDetailService getNewsDetailService(){
        GetNewsDetailService getNewsDetailService =getRetrofit().create(GetNewsDetailService.class);
        return getNewsDetailService;
    }
    public static GetPromotionCodeSerVice getPromotionCodeSerVice(){
        GetPromotionCodeSerVice getPromotionCodeSerVice = getRetrofit().create(GetPromotionCodeSerVice.class);
        return  getPromotionCodeSerVice;
    }
    public static GetAllWardSerVice getAllWardSerVice(){
        GetAllWardSerVice getAllWardSerVice = getRetrofit().create(GetAllWardSerVice.class);
        return  getAllWardSerVice;

    }


}
