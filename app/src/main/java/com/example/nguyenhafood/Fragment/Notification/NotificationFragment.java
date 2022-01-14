package com.example.nguyenhafood.Fragment.Notification;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenhafood.API.APIClient;
import com.example.nguyenhafood.Activity.ViewCartsAcivity;
import com.example.nguyenhafood.Apdater.NOtification.NotificationAdapter;
import com.example.nguyenhafood.Fragment.BaseFragment.BaseFragment;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Model.Notification.DataNotification;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.Home.GetNewsListNotificationRequest;
import com.example.nguyenhafood.Response.Home.GetNewsListNotificationResponse;
import com.example.nguyenhafood.Untils.Model;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationFragment extends BaseFragment {
    RelativeLayout btn_notification, btn_notification_news;
    RecyclerView recy_notification;
    RelativeLayout see_cart_newsdetail;
    List<DataNotification> datalist = new ArrayList<>();
    NotificationAdapter noticationAdapter;
    LinearLayoutManager manager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    boolean select1 = true;
    int pagenumber = 1;
    boolean isScrolling = false;
    String Type = "1";
    TextView cart_badge, txt_TBmenu, txt_TTmenu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_notification, container, false);
        recy_notification = view.findViewById(R.id.recy_notification);
        btn_notification = view.findViewById(R.id.btn_notification);
        see_cart_newsdetail = view.findViewById(R.id.see_cart_newsdetail);
        btn_notification_news = view.findViewById(R.id.btn_notification_news);
        txt_TTmenu = view.findViewById(R.id.txt_TTmenu);
        txt_TBmenu = view.findViewById(R.id.txt_TBmenu);
        GetNewsListNotificationRequest getNewsListNotificationRequest = new GetNewsListNotificationRequest();
        getNewsListNotificationRequest.setNewsType(Type);
        getNewsListNotificationRequest.setPageIndex(String.valueOf(pagenumber));
        GetNewsList(getNewsListNotificationRequest);
        cart_badge = view.findViewById(R.id.cart_badge);
        btn_notification.setBackground(btn_notification.getContext().getResources().getDrawable(R.drawable.buttonactiveitem));
        txt_TBmenu.setTextColor(Color.parseColor("#ffffff"));
        see_cart_newsdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(ViewCartsAcivity.class);
            }
        });
        btn_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagenumber = 1;
                Type = "1";
                btn_notification.setBackground(btn_notification.getContext().getResources().getDrawable(R.drawable.buttonactiveitem));
                txt_TBmenu.setTextColor(Color.parseColor("#ffffff"));
                txt_TTmenu.setTextColor(Color.parseColor("#111111"));
                btn_notification_news.setBackgroundColor(Color.parseColor("#FBF6EF"));
                GetNewsListNotificationRequest getNewsListNotificationRequest = new GetNewsListNotificationRequest();
                getNewsListNotificationRequest.setNewsType(Type);
                getNewsListNotificationRequest.setPageIndex(String.valueOf(pagenumber));
                GetNewsList(getNewsListNotificationRequest);
            }
        });
        btn_notification_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagenumber = 1;
                Type = "2";
                btn_notification.setBackgroundColor(Color.parseColor("#FBF6EF"));
                btn_notification_news.setBackground(btn_notification_news.getContext().getResources().getDrawable(R.drawable.buttonactiveitem));
                txt_TBmenu.setTextColor(Color.parseColor("#111111"));
                txt_TTmenu.setTextColor(Color.parseColor("#ffffff"));
                GetNewsListNotificationRequest getNewsListNotificationRequest = new GetNewsListNotificationRequest();
                getNewsListNotificationRequest.setNewsType(Type);
                getNewsListNotificationRequest.setPageIndex(String.valueOf(pagenumber));
                GetNewsList(getNewsListNotificationRequest);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
    }

    public void GetNewsList(GetNewsListNotificationRequest getNewsListNotificationRequest) {
        Call<GetNewsListNotificationResponse> getNewsListNotificationResponseCall = APIClient.getNewsListNotificationService().GetNewsList(Gobal.GuiID, getNewsListNotificationRequest);
        getNewsListNotificationResponseCall.enqueue(new Callback<GetNewsListNotificationResponse>() {
            @Override
            public void onResponse(Call<GetNewsListNotificationResponse> call, Response<GetNewsListNotificationResponse> response) {
                if (response.isSuccessful()) {
                    GetNewsListNotificationResponse getNewsListNotificationResponse = response.body();
                    if (getNewsListNotificationResponse.getStatusID() == 1) {
                        datalist.removeAll(datalist);
                        datalist.addAll(getNewsListNotificationResponse.getNewsList());
                        try {
                            noticationAdapter = new NotificationAdapter(getActivity(), datalist);
                            recy_notification.setAdapter(noticationAdapter);
                            recy_notification.setLayoutManager(manager2);
                            recy_notification.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                @Override
                                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                    super.onScrollStateChanged(recyclerView, newState);
                                    if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                                        if (isScrolling == false) {
                                            if (manager2 != null || manager2.findLastCompletelyVisibleItemPosition() == datalist.size() - 1) {
                                                pagenumber = pagenumber + 1;
                                                GetNewsListNotificationRequest getNewsListNotificationRequest = new GetNewsListNotificationRequest();
                                                getNewsListNotificationRequest.setNewsType(Type);
                                                getNewsListNotificationRequest.setPageIndex(String.valueOf(pagenumber));
                                                GetNewsListMore(getNewsListNotificationRequest);
                                            }


                                        } else {
                                            isScrolling = true;

                                        }
                                    }
                                }
                            });

                        } catch (Exception ex) {

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GetNewsListNotificationResponse> call, Throwable t) {
                Model.Dialog(getContext());
            }
        });
    }

    public void GetNewsListMore(GetNewsListNotificationRequest getNewsListNotificationRequest) {
        Call<GetNewsListNotificationResponse> getNewsListNotificationResponseCall = APIClient.getNewsListNotificationService().GetNewsList(Gobal.GuiID, getNewsListNotificationRequest);
        getNewsListNotificationResponseCall.enqueue(new Callback<GetNewsListNotificationResponse>() {
            @Override
            public void onResponse(Call<GetNewsListNotificationResponse> call, Response<GetNewsListNotificationResponse> response) {
                if (response.isSuccessful()) {
                    GetNewsListNotificationResponse getNewsListNotificationResponse = response.body();
                    if (getNewsListNotificationResponse.getStatusID() == 1 && getNewsListNotificationResponse.getNewsList().size() != 0) {
                        datalist.removeAll(datalist);
                        datalist.addAll(getNewsListNotificationResponse.getNewsList());
                        noticationAdapter.loadmore(getNewsListNotificationResponse.getNewsList());
                    } else {
                        isScrolling = false;

                    }
                }
            }

            @Override
            public void onFailure(Call<GetNewsListNotificationResponse> call, Throwable t) {
                Model.Dialog(getContext());
            }
        });
    }
}


