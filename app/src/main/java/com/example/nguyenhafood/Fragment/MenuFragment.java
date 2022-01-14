package com.example.nguyenhafood.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.nguyenhafood.API.APIClient;
import com.example.nguyenhafood.Activity.ViewCartsAcivity;
import com.example.nguyenhafood.Apdater.Menu.CatoryMenuDTOAdapter;
import com.example.nguyenhafood.Apdater.Menu.CatoryMenuItemAdapter;
import com.example.nguyenhafood.Apdater.Menu.ProductListItemMenuAdapter;
import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Fragment.BaseFragment.BaseFragment;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Interface.UpdateListMenuRecycleView;
import com.example.nguyenhafood.Model.Home.ItemDTO;
import com.example.nguyenhafood.Model.Home.vw_WebItemWebsite;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.Home.LoadMoreProductRequest;
import com.example.nguyenhafood.Request.Home.MenuRequest;
import com.example.nguyenhafood.Response.Home.LoadMoreProductResponse;
import com.example.nguyenhafood.Response.Home.MenuResponse;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.ybq.android.spinkit.SpinKitView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MenuFragment extends BaseFragment implements UpdateListMenuRecycleView {
    private RecyclerView rcl_menuc, list_danhmucsanpham, rcl_danhsachspmenu;
    TextView search_menu, cart_badge;
    ImageView view_cart, background_itemdto;
    CatoryMenuItemAdapter catoryMenuItemAdapter;
    ProductListItemMenuAdapter productListItemMenuAdapter;
    CardView imghaisan;
    private int pagenumber = 1;
    List<ItemDTO> Datalist = new ArrayList<>();
    boolean isScrolling = false;
    DBHelper dbHelper;
    int CatoryIndexMenu = 0;
    int CatoryIndexItem = 0;
    ShimmerFrameLayout shimer_menu;
    LinearLayoutManager manager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    SpinKitView prg_catory_menu, prg_product_menu, prg_catory_picetrue;
    List<vw_WebItemWebsite> vw_webItemWebsites = new ArrayList<>();
    int reselect = 0;
    int reselect1 = 0;
    CatoryMenuDTOAdapter catoryMenuDTOAdapter;
    SwipeRefreshLayout refest_menufragment;
    RelativeLayout ontop;
    boolean finsh=true;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        setRetainInstance(true);
        rcl_menuc = view.findViewById(R.id.rcl_menuc);
        refest_menufragment = view.findViewById(R.id.refest_menufragment);
        ontop=view.findViewById(R.id.ontop);
        dbHelper = new DBHelper(getActivity());
        shimer_menu = view.findViewById(R.id.shimer_menu);
        prg_product_menu = view.findViewById(R.id.prg_product_menu);
        prg_catory_menu = view.findViewById(R.id.prg_catory_menu);
        prg_catory_picetrue = view.findViewById(R.id.prg_catory_picetrue);
        shimer_menu.startShimmer();
        imghaisan = view.findViewById(R.id.imghaisan);
        list_danhmucsanpham = view.findViewById(R.id.list_danhmucsanpham);
        background_itemdto = view.findViewById(R.id.background_itemdto);
        //// menu catory re
        rcl_danhsachspmenu = view.findViewById(R.id.rcl_danhsachspmenu);
        cart_badge = view.findViewById(R.id.cart_badge);
        view_cart = view.findViewById(R.id.view_cart);
        search_menu = view.findViewById(R.id.search_menu);
        search_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(SearchFragment.class);
            }
        });
        view_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(ViewCartsAcivity.class);
            }
        });
        MenuRequest menuRequest = new MenuRequest();
        menuRequest.setUserID(dbHelper.GetDL());
        LoadMenuDTO(menuRequest);
        Gobal.ShowMenu = true;
     // rcl_danhsachspmenu.setOnTouchListener(new AnimationHome(getContext(), imghaisan));
        refest_menufragment.setColorSchemeResources(R.color.maucam, R.color.mauden, R.color.maucam);
        refest_menufragment.setOnRefreshListener(this);
//        rcl_danhsachspmenu.setOnTouchListener(new AnimationHome(getContext(),imghaisan));
//        rcl_danhsachspmenu.setOnTouchListener(new AnimationHome(getContext(),ontop));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onStart() {
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onPause() {
        super.onPause();
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
    }

    @Override
    public void onResume() {
        super.onResume();
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
    }

    public void LoadMenuDTO(MenuRequest menuRequest) {
        Call<MenuResponse> menuResponseCall = APIClient.getMenuDTO().MenuDTO(Gobal.GuiID, menuRequest);
        menuResponseCall.enqueue(new Callback<MenuResponse>() {
            @Override
            public void onResponse(Call<MenuResponse> call, Response<MenuResponse> response) {
                if (response.isSuccessful()) {
                    MenuResponse menuResponse = response.body();
                    if (menuResponse.getStatusID() == 1) {
                        for (int i = 0; i < menuResponse.getHomeData().getItemDTOList().size(); i++) {
                            Datalist.removeAll(Datalist);
                            vw_webItemWebsites.removeAll(vw_webItemWebsites);
                            Datalist.addAll(menuResponse.getHomeData().getItemDTOList());
                            if (Gobal.IDProduct.equals("") == false) {
                                for (int j = 0; j < Datalist.size(); j++) {
                                    if (Gobal.IDProduct.equals(Datalist.get(j).getCategoryRoot().getProductCategoryID()) == true) {
                                        Datalist.get(j).setPagemenu(0);
                                        Datalist.get(j).setiSActive(true);
                                        Picasso.with(getContext())
                                                .load(Gobal.IDImage + menuResponse.getHomeData().getItemDTOList().get(j).getCategoryRoot().getPhoto())
                                                .fit()
                                                .centerCrop()
                                                .error(R.drawable.imageerro)
                                                .into(background_itemdto);
                                        MenuGobal.setProductCategoryID(Gobal.IDCatoryProduct);
                                        catoryMenuDTOAdapter = new CatoryMenuDTOAdapter(Datalist, getActivity(), MenuFragment.this);
                                        rcl_menuc.setAdapter(catoryMenuDTOAdapter);
                                        LinearLayoutManager manager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                                        rcl_menuc.setLayoutManager(manager1);
                                        Datalist.get(j).getItemList().get(0).setActive(true);
                                        rcl_menuc.scrollToPosition(j);
                                        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                                        catoryMenuItemAdapter = new CatoryMenuItemAdapter(Datalist.get(j).getItemList(), getActivity(), MenuFragment.this);
                                        list_danhmucsanpham.setAdapter(catoryMenuItemAdapter);
                                        // Datalist.get(CatoryIndexMenu).getItemList().get(i).setPageindex(0);
                                        list_danhmucsanpham.setLayoutManager(manager);
                                        list_danhmucsanpham.setHasFixedSize(true);
                                        LoadMoreProductRequest loadMoreProductRequest = new LoadMoreProductRequest();
                                        loadMoreProductRequest.setProductCategoryID(Gobal.IDCatoryProduct);
                                        loadMoreProductRequest.setUserID(dbHelper.GetDL());
                                        loadMoreProductRequest.setPageIndex(String.valueOf(pagenumber));
                                        LoadNextCateroryRoot(loadMoreProductRequest, CatoryIndexMenu);
                                        break;
                                    }
                                }
                            } else {
                                Datalist.get(0).setPagemenu(0);
                                Datalist.get(0).setiSActive(true);
                                Picasso.with(getContext())
                                        .load(Gobal.IDImage + menuResponse.getHomeData().getItemDTOList().get(0).getCategoryRoot().getPhoto())
                                        .fit()
                                        .centerCrop()
                                        .error(R.drawable.imageerro)
                                        .into(background_itemdto);
                                CatoryMenuDTOAdapter catoryMenuDTOAdapter = new CatoryMenuDTOAdapter(Datalist, getActivity(), MenuFragment.this);
                                rcl_menuc.setAdapter(catoryMenuDTOAdapter);
                                LinearLayoutManager manager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                                rcl_menuc.setLayoutManager(manager1);
                                //// menuitem
                                LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                                catoryMenuItemAdapter = new CatoryMenuItemAdapter(Datalist.get(CatoryIndexMenu).getItemList(), getActivity(), MenuFragment.this);
                                list_danhmucsanpham.setAdapter(catoryMenuItemAdapter);
                                Datalist.get(CatoryIndexMenu).getItemList().get(CatoryIndexMenu).setPageindex(0);
                                list_danhmucsanpham.setLayoutManager(manager);
                                list_danhmucsanpham.setHasFixedSize(true);
                                /// sp
                                Datalist.get(0).getItemList().get(0).setActive(true);
                                ////
                                vw_webItemWebsites.removeAll(vw_webItemWebsites);
                                vw_webItemWebsites.addAll(menuResponse.getHomeData().getItemDTOList().get(0).getItemList().get(0).getProductList());
                                productListItemMenuAdapter = new ProductListItemMenuAdapter(vw_webItemWebsites, getActivity(), MenuFragment.this);
                                rcl_danhsachspmenu.setLayoutManager(manager2);
                                rcl_danhsachspmenu.setAdapter(productListItemMenuAdapter);
                                rcl_danhsachspmenu.setHasFixedSize(true);
                                MenuGobal.setProductCategoryID(Datalist.get(CatoryIndexMenu).getItemList().get(0).getCategory().getProductCategoryID());
                                rcl_danhsachspmenu.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                    @Override
                                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                        super.onScrollStateChanged(recyclerView, newState);
                                        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                                            if (isScrolling == false) {
                                                if (manager2 != null && manager2.findLastVisibleItemPosition() == vw_webItemWebsites.size() - 1) {
                                                    pagenumber = pagenumber + 1;
                                                    loadMore(MenuGobal.getProductCategoryID(), String.valueOf(pagenumber));
                                                }
                                            } else {
                                                isScrolling = true;
                                            }
                                        }
                                    }
                                });
                            }
                            break;
                        }
                        shimer_menu.stopShimmer();
                        shimer_menu.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<MenuResponse> call, Throwable t) {
                Model.Dialog(getContext());
            }
        });
    }

    ///item
    @Override
    public void UpdateCatoryMenu(int position, String CatoryItem, String ID) {
        isScrolling = false;
        prg_product_menu.setVisibility(View.VISIBLE);
        pagenumber = 1;
        if (position == 0 || position == Datalist.get(CatoryIndexMenu).getItemList().size()) {
            list_danhmucsanpham.scrollToPosition(position);
            reselect = position;
        } else if (position > reselect) {
            list_danhmucsanpham.scrollToPosition(position + 1);
            reselect = position;
        } else {
            list_danhmucsanpham.scrollToPosition(position - 1);
            reselect = position;
        }
        vw_webItemWebsites.removeAll(vw_webItemWebsites);
        CountDownTimer countDownTimer = new CountDownTimer(400, 350) {
            @Override
            public void onTick(long millisUntilFinished) {
                cart_badge.setText(String.valueOf(Gobal.SizeProduct));
                CatoryIndexItem = position;
                catoryMenuItemAdapter = new CatoryMenuItemAdapter(Datalist.get(CatoryIndexMenu).getItemList(), getActivity(), MenuFragment.this);
                catoryMenuItemAdapter.notifyDataSetChanged();
                MenuGobal.setProductCategoryID(ID);
                try {
                    Datalist.get(CatoryIndexMenu).getItemList().get(position).setPageindex(0);
                    LoadMoreProductRequest loadMoreProductRequest = new LoadMoreProductRequest();
                    loadMoreProductRequest.setProductCategoryID(CatoryItem);
                    loadMoreProductRequest.setUserID(dbHelper.GetDL());
                    loadMoreProductRequest.setPageIndex(String.valueOf(pagenumber));
                    LoadNextCateroryRoot(loadMoreProductRequest, CatoryIndexMenu);
                } catch (Exception exception) {

                }
            }

            @Override
            public void onFinish() {
                rcl_danhsachspmenu.setVisibility(View.VISIBLE);
                prg_product_menu.setVisibility(View.GONE);

            }
        }.start();
    }
    private void showview() {
        if (ontop == null || ontop.getVisibility() == View.VISIBLE) {

        } else {
            Animation animationup = AnimationUtils.loadAnimation(ontop.getContext(), R.anim.slide_in_show);
            animationup.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    ontop.setVisibility(View.VISIBLE);
                    finsh = false;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    finsh = true;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            if (finsh == true) {
                ontop.startAnimation(animationup);
            }
        }
    }
    private void hidenview() {
        if (ontop == null || ontop.getVisibility() == View.GONE) {
            return;
        } else {
            Animation animationdown = AnimationUtils.loadAnimation(ontop.getContext(), R.anim.slide_in_hide);
            animationdown.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    ontop.setVisibility(View.VISIBLE);
                    finsh = false;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    ontop.setVisibility(View.GONE);
                    finsh = true;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            if (finsh == true) {
                ontop.startAnimation(animationdown);
            }
        }
    }
    ///menuitem_item
    @Override
    public void UpdateCatoryItemMenu(int position, String CatoryMenuDTO, String ID, String IMG) {
        isScrolling = false;
        prg_catory_menu.setVisibility(View.VISIBLE);
        prg_product_menu.setVisibility(View.VISIBLE);
        prg_catory_picetrue.setVisibility(View.VISIBLE);
        if (ID !=""){
        if (position == 0 || position == Datalist.get(CatoryIndexMenu).getItemList().size()) {
            rcl_menuc.scrollToPosition(position);
            reselect1 = position;
        } else if (position > reselect1) {
            rcl_menuc.scrollToPosition(position + 1);
            reselect1 = position;
        } else {
            rcl_menuc.scrollToPosition(position - 1);
            reselect1 = position;
        }
        pagenumber = 1;
        CountDownTimer countDownTimer = new CountDownTimer(400, 350) {
            @Override
            public void onTick(long millisUntilFinished) {
                try {
                    cart_badge.setText(String.valueOf(Gobal.SizeProduct));
                    CatoryMenuDTOAdapter catoryMenuDTOAdapter = new CatoryMenuDTOAdapter(Datalist, getActivity(), MenuFragment.this);
                    catoryMenuDTOAdapter.notifyDataSetChanged();
                    CatoryIndexMenu = position;
                    MenuGobal.setProductCategoryID(ID);
                    Picasso.with(getContext())
                            .load(Gobal.IDImage + IMG)
                            .fit()
                            .centerCrop()
                            .error(R.drawable.imageerro)
                            .into(background_itemdto);
                    for (int i = 0; i < Datalist.size(); i++) {
                        if (i == 0) {
                            Datalist.get(CatoryIndexMenu).getItemList().get(0).setActive(true);
                        } else {
                            try {
                                Datalist.get(CatoryIndexMenu).getItemList().get(i).setActive(false);
                            }
                            catch (Exception exception){
                            }
                        }
                    }
                    //////
                    LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                    catoryMenuItemAdapter = new CatoryMenuItemAdapter(Datalist.get(CatoryIndexMenu).getItemList(), getActivity(), MenuFragment.this);
                    list_danhmucsanpham.setAdapter(catoryMenuItemAdapter);
                    Datalist.get(CatoryIndexMenu).getItemList().get(0).setPageindex(0);
                    list_danhmucsanpham.setLayoutManager(manager);
                    list_danhmucsanpham.setHasFixedSize(true);
                    ////
                    Datalist.get(CatoryIndexMenu).setPagemenu(0);
                    LoadMoreProductRequest loadMoreProductRequest = new LoadMoreProductRequest();
                    loadMoreProductRequest.setProductCategoryID(CatoryMenuDTO);
                    loadMoreProductRequest.setUserID(dbHelper.GetDL());
                    loadMoreProductRequest.setPageIndex(String.valueOf(pagenumber));
                    LoadNextCateroryRoot(loadMoreProductRequest, CatoryIndexMenu);
                } catch (Exception exception) {

                }
            }

            @Override
            public void onFinish() {
                list_danhmucsanpham.setVisibility(View.VISIBLE);
                rcl_danhsachspmenu.setVisibility(View.VISIBLE);
                prg_catory_menu.setVisibility(View.GONE);
                prg_product_menu.setVisibility(View.GONE);
                background_itemdto.setVisibility(View.VISIBLE);
                prg_catory_picetrue.setVisibility(View.GONE);
            }
        }.start();
        } else {
            list_danhmucsanpham.setVisibility(View.GONE);
            rcl_danhsachspmenu.setVisibility(View.GONE);
            background_itemdto.setVisibility(View.GONE);
        }

    }


    public void loadMore(String ID, String pagenumber) {
        LoadMoreProductRequest loadMoreProductRequest = new LoadMoreProductRequest();
        loadMoreProductRequest.setPageIndex(pagenumber);
        loadMoreProductRequest.setProductCategoryID(ID);
        loadMoreProductRequest.setUserID(dbHelper.GetDL());
        LoadMoreItem(loadMoreProductRequest);
    }

    public void LoadNextCateroryRoot(LoadMoreProductRequest loadMoreProductRequest, int CatoryIndex) {
        Call<LoadMoreProductResponse> loadMoreProductResponseCall = APIClient.getLoadMoreProductSerVice().LoadMore(Gobal.GuiID, loadMoreProductRequest);
        loadMoreProductResponseCall.enqueue(new Callback<LoadMoreProductResponse>() {
            @Override
            public void onResponse(Call<LoadMoreProductResponse> call, Response<LoadMoreProductResponse> response) {
                if (response.isSuccessful()) ;
                {
                    LoadMoreProductResponse loadMoreProductResponse = response.body();
                    if (loadMoreProductResponse.getStatusID() == 1) {
                        try {
                            Gobal.IDProduct = "";
                            vw_webItemWebsites.removeAll(vw_webItemWebsites);
                            vw_webItemWebsites.addAll(loadMoreProductResponse.getHomeData().getProductList());
                            productListItemMenuAdapter = new ProductListItemMenuAdapter(vw_webItemWebsites, getActivity(), MenuFragment.this);
                            rcl_danhsachspmenu.setLayoutManager(manager2);
                            rcl_danhsachspmenu.setAdapter(productListItemMenuAdapter);
                            rcl_danhsachspmenu.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                @Override
                                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                    super.onScrollStateChanged(recyclerView, newState);
                                    if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                                        if (isScrolling == false) {
                                            if (manager2 != null || manager2.findLastCompletelyVisibleItemPosition() == vw_webItemWebsites.size() - 1) {
                                                pagenumber = pagenumber + 1;
                                                loadMore(MenuGobal.getProductCategoryID(), String.valueOf(pagenumber));
                                            }
                                        } else {
                                            isScrolling = true;

                                        }
                                    }
                                }
                            });
                        } catch (Exception exception) {

                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<LoadMoreProductResponse> call, Throwable t) {
                Model.Dialog(getContext());
            }
        });
    }

    public void LoadMoreItem(LoadMoreProductRequest loadMoreProductRequest) {
        Call<LoadMoreProductResponse> loadMoreProductResponseCall = APIClient.getLoadMoreProductSerVice().LoadMore(Gobal.GuiID, loadMoreProductRequest);
        loadMoreProductResponseCall.enqueue(new Callback<LoadMoreProductResponse>() {
            @Override
            public void onResponse(Call<LoadMoreProductResponse> call, Response<LoadMoreProductResponse> response) {
                if (response.isSuccessful()) ;
                {
                    LoadMoreProductResponse loadMoreProductResponse = response.body();
                    if (loadMoreProductResponse.getStatusID() == 1) {
                        if (loadMoreProductResponse.getHomeData().getProductList().size() > 0) {
                            productListItemMenuAdapter.LoadMore(loadMoreProductResponse.getHomeData().getProductList());
                            vw_webItemWebsites.addAll(loadMoreProductResponse.getHomeData().getProductList());
//                            productListItemMenuAdapter.notifyItemInserted(vw_webItemWebsites.size() - 1);
//                            vw_webItemWebsites.remove(vw_webItemWebsites.size() - 1);
//                            productListItemMenuAdapter.notifyDataSetChanged();
//                            vw_webItemWebsites.addAll(loadMoreProductResponse.getHomeData().getProductList());
//                            productListItemMenuAdapter = new ProductListItemMenuAdapter(vw_webItemWebsites, getActivity(), MenuFragment.this);
//                            rcl_danhsachspmenu.setAdapter(productListItemMenuAdapter);
//                            rcl_danhsachspmenu.setLayoutManager(manager2);

                        } else {
                            isScrolling = true;
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<LoadMoreProductResponse> call, Throwable t) {
                Model.Dialog(getContext());
                isScrolling = true;
            }
        });
    }

    @Override
    public void UpdateSize(int position1) {
        productListItemMenuAdapter = new ProductListItemMenuAdapter(vw_webItemWebsites, getActivity(), MenuFragment.this);
        productListItemMenuAdapter.notifyDataSetChanged();
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                CatoryIndexMenu = 0;
//                CatoryIndexItem = 0;
//                Datalist = new ArrayList<>();
//                Gobal.IDProduct = "";
//                MenuRequest menuRequest = new MenuRequest();
//                menuRequest.setUserID(dbHelper.GetDL());
//                LoadMenuDTO(menuRequest);
                refest_menufragment.setRefreshing(false);
            }
        }, 1000);
    }

    public static class MenuGobal {
        public static String getProductCategoryID() {
            return ProductCategoryID;
        }

        public static void setProductCategoryID(String productCategoryID) {
            ProductCategoryID = productCategoryID;
        }

        public static String ProductCategoryID;
    }

}