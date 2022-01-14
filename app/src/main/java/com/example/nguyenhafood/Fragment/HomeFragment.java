package com.example.nguyenhafood.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.nguyenhafood.API.APIClient;
import com.example.nguyenhafood.Activity.Acount_login.ActivityFavoriteproduct;
import com.example.nguyenhafood.Activity.Acount_login.ActivityViewproduct;
import com.example.nguyenhafood.Activity.ViewCartsAcivity;
import com.example.nguyenhafood.Animation.HideView;
import com.example.nguyenhafood.Apdater.Home.ListItemDTOAdapter;
import com.example.nguyenhafood.Apdater.Home.ListPromotionAdapter;
import com.example.nguyenhafood.Apdater.Home.ProductFavoriteAdapter;
import com.example.nguyenhafood.Apdater.Home.ProductViewListAdapter;
import com.example.nguyenhafood.Apdater.Home.SlideAdapter;
import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Fragment.BaseFragment.BaseFragment;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Interface.UpdatePromotion;
import com.example.nguyenhafood.Model.Home.DataProductViewList;
import com.example.nguyenhafood.Model.Home.Item;
import com.example.nguyenhafood.Model.Home.ItemDTO;
import com.example.nguyenhafood.Model.Home.ItemListProMotion;
import com.example.nguyenhafood.Model.Home.ListItemProMotion;
import com.example.nguyenhafood.Model.Home.vw_Favorite;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.Home.HomeRequest;
import com.example.nguyenhafood.Response.Home.HomeResponse;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends BaseFragment implements UpdatePromotion {
    ProductFavoriteAdapter productFavoriteAdapter;
    SwipeRefreshLayout refest_homefragmet;
    List<vw_Favorite> lstfavorite = new ArrayList<>();
    List<ItemListProMotion> itemListProMotions = new ArrayList<>();
    List<ListItemProMotion> listItemProMotions = new ArrayList<>();
    List<ItemDTO> lst_itemDTOS = new ArrayList<>();
    SlideAdapter slideAdapter;
    RecyclerView list_product_promotion_home;
    RecyclerView recycle_favoriteproduct;
    ViewPager carouselView;
    ListPromotionAdapter listPromotionAdapter;
    TextView search, seemore_favoriteproduct, seemore_viewedproduct;
    ImageView viewcard_home;
    RelativeLayout view_sanphamyeuthich, cart_view_sale;
    ShimmerFrameLayout shimer_menu_home;
    LinearLayout indicator;
    private int COLOR_ACTIVE = Color.DKGRAY;
    private int COLOR_INACTIVE = Color.YELLOW;
    RecyclerView recycle_seeproductlst_home;
    ProductViewListAdapter productViewListAdapter;
    List<DataProductViewList> dataProductViewLists = new ArrayList<>();
    NestedScrollView srl_top;
    DBHelper dbHelper;
    TextView cart_badge;
    RelativeLayout rlv_viewfavorite;
    RecyclerView catoryroot_listproduct_home;
    ListItemDTOAdapter listItemDTOAdapter;
    List<ItemDTO> itemDTOS = new ArrayList<>();
    RelativeLayout ontop;
    private boolean doubleBackToExitPressedOnce;
    private Handler mHandler = new Handler();
    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mHandler != null) {
            mHandler.removeCallbacks(mRunnable);
        }
    }
    @SuppressLint("NewApi")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        dbHelper = new DBHelper(getActivity());
        refest_homefragmet = view.findViewById(R.id.refest_homefragmet);
        recycle_favoriteproduct = view.findViewById(R.id.recycle_favoriteproduct);
        list_product_promotion_home = view.findViewById(R.id.list_product_promotion_home);
        ontop = view.findViewById(R.id.ontop);
        catoryroot_listproduct_home = view.findViewById(R.id.catoryroot_listproduct_home);
        srl_top = view.findViewById(R.id.srl_top);
        cart_badge = view.findViewById(R.id.cart_badge);
        rlv_viewfavorite = view.findViewById(R.id.rlv_viewfavorite);
        shimer_menu_home = view.findViewById(R.id.shimer_menu_home);
        seemore_favoriteproduct = view.findViewById(R.id.seemore_favoriteproduct);
        seemore_viewedproduct = view.findViewById(R.id.seemore_viewedproduct);
        carouselView = view.findViewById(R.id.carouselView);
        recycle_seeproductlst_home = view.findViewById(R.id.recycle_seeproductlst_home);
        view_sanphamyeuthich = view.findViewById(R.id.view_sanphamyeuthich);
        indicator = view.findViewById(R.id.indicator);
        search = view.findViewById(R.id.search);
        cart_view_sale = view.findViewById(R.id.cart_view_sale);
        viewcard_home = view.findViewById(R.id.viewcard_home);
        ///
        shimer_menu_home.startShimmer();
        srl_top.setVisibility(View.GONE);
        ///
        seemore_favoriteproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gotoActivity(ActivityFavoriteproduct.class);
            }
        });
        seemore_viewedproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gotoActivity(ActivityViewproduct.class);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gotoActivity(SearchFragment.class);
            }
        });
        viewcard_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(ViewCartsAcivity.class);
            }
        });
        if (Gobal.getLoginStatus() == 0) {
            view_sanphamyeuthich.setVisibility(View.GONE);
        } else {
            view_sanphamyeuthich.setVisibility(View.VISIBLE);
        }
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
        // animate
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                HomeRequest homeRequest = new HomeRequest();
                homeRequest.setUserID(dbHelper.GetDL());
                LoadHomeDTO(homeRequest);
            }
        }, 100);
        //   srl_top.setOnTouchListener(new AnimationHome(getContext(),ontop));
        ///
        refest_homefragmet = view.findViewById(R.id.refest_homefragmet);
        refest_homefragmet.setColorSchemeResources(R.color.maucam, R.color.mauden, R.color.maucam);
        refest_homefragmet.setOnRefreshListener(this);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void onStart() {
        super.onStart();
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
        //

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
        if (Gobal.getLoginStatus() == 0) {
            view_sanphamyeuthich.setVisibility(View.GONE);
        } else {
            view_sanphamyeuthich.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    View createDot(Context context, @ColorInt int color) {
        float a = getResources().getDimension(R.dimen.Margin_padding5);
        float b = getResources().getDimension(R.dimen.Margin_padding8);
        float c = getResources().getDimension(R.dimen.Margin_padding9);
        View dot = new View(context);
        LinearLayout.MarginLayoutParams dotParams = new LinearLayout.MarginLayoutParams((int) a, (int) a);
        dotParams.setMargins((int) b, (int) c, (int) b, 0);
        dot.setLayoutParams(dotParams);
        ShapeDrawable drawable = new ShapeDrawable(new OvalShape());
        drawable.setTint(color);
        dot.setBackground(drawable);
        return dot;
    }

    //search, detail
    public void LoadHomeDTO(HomeRequest homeRequest) {
        Call<HomeResponse> homeResponseCall = APIClient.getHomeDTO().HomeDTO(Gobal.GuiID, homeRequest);
        homeResponseCall.enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                if (response.isSuccessful()) {
                    HomeResponse homeResponse = response.body();
                    if (homeResponse.getStatusID() == 1) {
                        lstfavorite.removeAll(lstfavorite);
                        if (homeResponse.getHomeData().getFavoriteList().size() != 0) {
                            lstfavorite.addAll(homeResponse.getHomeData().getFavoriteList());
                            productFavoriteAdapter = new ProductFavoriteAdapter(lstfavorite, getActivity(), HomeFragment.this);
                            recycle_favoriteproduct.setAdapter(productFavoriteAdapter);
                            if (lstfavorite.size() > 2) {
                                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, RecyclerView.HORIZONTAL, false);
                                recycle_favoriteproduct.setLayoutManager(gridLayoutManager);
                                recycle_favoriteproduct.setHasFixedSize(true);
                            } else {
                                LinearLayoutManager lne = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                                recycle_favoriteproduct.setLayoutManager(lne);
                                recycle_favoriteproduct.setHasFixedSize(true);
                            }

                        } else {
                            rlv_viewfavorite.setVisibility(View.GONE);
                            recycle_favoriteproduct.setVisibility(View.GONE);
                        }
                        listItemDTOAdapter = new ListItemDTOAdapter(homeResponse.getHomeData().getItemDTOList(), getActivity(), HomeFragment.this);
                        catoryroot_listproduct_home.setAdapter(listItemDTOAdapter);
                        LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                        catoryroot_listproduct_home.setLayoutManager(linearLayoutManager4);
                        catoryroot_listproduct_home.setNestedScrollingEnabled(false);
////
                        slideAdapter = new SlideAdapter(homeResponse.getHomeData().getSlideDetailList(), getActivity());
                        carouselView.setPageTransformer(true, new HideView());
                        carouselView.setAdapter(slideAdapter);
                        carouselView.setPadding(0, 0, 0, 0);
                        indicator.removeAllViews();
                        lst_itemDTOS.removeAll(lst_itemDTOS);
                        lst_itemDTOS.addAll(homeResponse.getHomeData().getItemDTOList());
                        lst_itemDTOS.get(0).setiSActive(true);
                        for (int i = 0; i < homeResponse.getHomeData().getSlideDetailList().size(); i++) {
                            @SuppressLint({"NewApi", "LocalSuppress"})
                            View dot = createDot(indicator.getContext(), i == 0 ? COLOR_ACTIVE : COLOR_INACTIVE);
                            indicator.addView(dot);
                        }
                        carouselView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                            }

                            @Override
                            public void onPageSelected(int position) {

                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });
                        carouselView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                                for (int i = 0; i < homeResponse.getHomeData().getSlideDetailList().size(); i++) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        indicator.getChildAt(i).getBackground().mutate().setTint(i == position ? COLOR_ACTIVE : COLOR_INACTIVE);
                                    }
                                }
                            }

                            @Override
                            public void onPageSelected(int position) {

                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });
                        final Handler handler = new Handler();
                        final Runnable Update = new Runnable() {
                            @Override
                            public void run() {
                                if (carouselView.getCurrentItem() == slideAdapter.getCount() - 1) {
                                    carouselView.setCurrentItem(0);
                                } else {
                                    carouselView.setCurrentItem(carouselView.getCurrentItem() + 1, true);
                                }
                            }
                        };
                        Timer timer;
                        timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                handler.post(Update);
                            }
                        }, 5000, 15000);
                        /// sản phẩm đã xem ----------------------------------------------------------------------------------
                        dataProductViewLists.addAll(homeResponse.getHomeData().getProductViewList());
                        productViewListAdapter = new ProductViewListAdapter(dataProductViewLists, getActivity(), HomeFragment.this);
                        recycle_seeproductlst_home.setAdapter(productViewListAdapter);
                        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getActivity(), 2, RecyclerView.HORIZONTAL, false);
                        recycle_seeproductlst_home.setLayoutManager(gridLayoutManager1);
                        recycle_seeproductlst_home.setHasFixedSize(true);
                        // sản phẩm ưa thích nè -----------------------------------------------------
                        if (homeResponse.getHomeData().getWebItemPromotionDTOList() != null) {
                            listItemProMotions.removeAll(listItemProMotions);
                            listItemProMotions.addAll(homeResponse.getHomeData().getWebItemPromotionDTOList());
                            itemListProMotions.removeAll(itemListProMotions);
                            if (homeResponse.getHomeData().getWebItemPromotionDTOList().size() != 0) {
                                for (int i = 0; i <= homeResponse.getHomeData().getWebItemPromotionDTOList().size(); i++) {
                                    itemListProMotions.addAll(homeResponse.getHomeData().getWebItemPromotionDTOList().get(i).getItemList());
                                    break;
                                }
                            } else {
                                cart_view_sale.setVisibility(View.GONE);
                            }
                            listPromotionAdapter = new ListPromotionAdapter(itemListProMotions, getActivity(), HomeFragment.this);
                            LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                            list_product_promotion_home.setAdapter(listPromotionAdapter);
                            list_product_promotion_home.setLayoutManager(linearLayoutManager3);
                        }

                        srl_top.setVisibility(View.VISIBLE);
                        shimer_menu_home.stopShimmer();
                        shimer_menu_home.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {
                Model.Dialog(getContext());
            }
        });
    }

    @Override
    public void CallBackPromotion(int position, List<ItemListProMotion> datalist) {

    }

    @Override
    public void CallBackProductID(int position, String ProductID, String PageIndex) {

    }

    @Override
    public void CallBackAdapter(int position, List<Item> items) {

    }

    @Override
    public void CallBackView(int position2, String CatoryRootID) {
        listItemDTOAdapter = new ListItemDTOAdapter(itemDTOS, getActivity(), HomeFragment.this);
        listItemDTOAdapter.notifyDataSetChanged();
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
    }


    @Override
    public void UpdateSize(int position1) {
        productFavoriteAdapter = new ProductFavoriteAdapter(lstfavorite, getActivity(), HomeFragment.this);
        productFavoriteAdapter.notifyDataSetChanged();
        productViewListAdapter = new ProductViewListAdapter(dataProductViewLists, getActivity(), HomeFragment.this);
        productViewListAdapter.notifyDataSetChanged();
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                HomeRequest homeRequest = new HomeRequest();
                homeRequest.setUserID(dbHelper.GetDL());
                LoadHomeDTO(homeRequest);
                refest_homefragmet.setRefreshing(false);
            }
        }, 1000);
    }

}