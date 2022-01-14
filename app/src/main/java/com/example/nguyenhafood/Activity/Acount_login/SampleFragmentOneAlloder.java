package com.example.nguyenhafood.Activity.Acount_login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenhafood.API.APIClient;
import com.example.nguyenhafood.Apdater.Acount_Login.OderAdapter;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Model.Acount_Login.DataOder;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.Order.GetOderRequest;
import com.example.nguyenhafood.Response.Order.GetOderResponse;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SampleFragmentOneAlloder extends Fragment {
    int status;
    RecyclerView rcl_ViewOder;
    OderAdapter oderAdapter;
    boolean isScrolling = false;
    private int pagenumber = 1;
    ShimmerFrameLayout shimer_Allorder_item;
    List<DataOder> oderList = new ArrayList<>();
    ProgressBar processbar_viewallorder;
    //   SpinKitView spin_kit_activity_oder;
    LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_oder, container, false);
        rcl_ViewOder = view.findViewById(R.id.rcl_ViewOder);
        processbar_viewallorder = view.findViewById(R.id.processbar_viewallorder);
        // spin_kit_activity_oder = view.findViewById(R.id.spin_kit_activity_oder);
        Bundle bundle = getArguments();
        assert bundle != null;
        status = bundle.getInt("key");
        rcl_ViewOder.setVisibility(View.GONE);
        processbar_viewallorder.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setUserVisibleHint(true);
            }
        }, 700);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isScrolling = false;
        pagenumber = 1;
        GetOderRequest getOderRequest = new GetOderRequest();
        getOderRequest.setUserID(Gobal.UserID);
        getOderRequest.setPageIndex(String.valueOf(pagenumber));
        if (isVisibleToUser) {
            getOderRequest.setOrderStatus(String.valueOf(status + 1));
            LoadOder(getOderRequest);
        } else {

            getOderRequest.setOrderStatus(String.valueOf(status + 2));
            LoadOder(getOderRequest);

        }
    }

    public void LoadOder(GetOderRequest getOderRequest) {
        Call<GetOderResponse> getOderResponseCall = APIClient.getOderService().GetOder(Gobal.GuiID, getOderRequest);
        getOderResponseCall.enqueue(new Callback<GetOderResponse>() {
            @Override
            public void onResponse(Call<GetOderResponse> call, Response<GetOderResponse> response) {
                if (response.isSuccessful()) {
                    GetOderResponse getOderResponse = response.body();
                    if (getOderResponse.getStatusID() == 1) {
                        isScrolling = false;
                        pagenumber = 1;
                        oderList = new ArrayList<>();
                        oderList.addAll(getOderResponse.getOrderList());
                        oderAdapter = new OderAdapter(getOderResponse.getOrderList(), getActivity());
                        try {
                            rcl_ViewOder.setLayoutManager(linearLayoutManager3);
                        }
                        catch (Exception exception){
                            linearLayoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                            rcl_ViewOder.setLayoutManager(linearLayoutManager3);
                        }


                        CountDownTimer countDownTimer = new CountDownTimer(800, 400) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                rcl_ViewOder.setAdapter(oderAdapter);
                            }

                            @Override
                            public void onFinish() {
                                processbar_viewallorder.setVisibility(View.GONE);
                                rcl_ViewOder.setVisibility(View.VISIBLE);
                            }
                        }.start();
                        rcl_ViewOder.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                super.onScrollStateChanged(recyclerView, newState);
                                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                                    if (isScrolling == false) {
                                        if (linearLayoutManager3 != null && linearLayoutManager3.findLastVisibleItemPosition() == oderList.size() - 1) {
                                            pagenumber = pagenumber + 1;
                                            Loadmoreoder(String.valueOf(pagenumber));
                                        }
                                    } else {
                                        pagenumber=1;
                                    }
                                }
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<GetOderResponse> call, Throwable t) {
                Model.Dialog(getContext());
            }
        });
    }

    public void Loadmoreoder(String pagenumer) {
        GetOderRequest getOderRequest = new GetOderRequest();
        getOderRequest.setPageIndex(pagenumer);
        getOderRequest.setUserID(Gobal.UserID);
        if (status == 0) {
            getOderRequest.setOrderStatus("1");
            LoadmoreOder(getOderRequest);
        }
        if (status == 1) {
            getOderRequest.setOrderStatus("2");
            LoadmoreOder(getOderRequest);
        }
        if (status == 2) {
            getOderRequest.setOrderStatus("3");
            LoadmoreOder(getOderRequest);
        }
        if (status == 3) {
            getOderRequest.setOrderStatus("4");
            LoadmoreOder(getOderRequest);
        }
    }

    public void LoadmoreOder(GetOderRequest getOderRequest) {
        Call<GetOderResponse> getOderResponseCall = APIClient.getOderService().GetOder(Gobal.GuiID, getOderRequest);
        getOderResponseCall.enqueue(new Callback<GetOderResponse>() {
            @Override
            public void onResponse(Call<GetOderResponse> call, Response<GetOderResponse> response) {
                if (response.isSuccessful()) {
                    GetOderResponse getOderResponse = response.body();
                    if (getOderResponse.getStatusID() == 1) {
                        try {
                            if (getOderResponse.getOrderList().size() != 0) {
                                oderAdapter.LoadMore(getOderResponse.getOrderList());
                                oderList.addAll(getOderResponse.getOrderList());
//                                oderList.add(null);
//                                oderAdapter.notifyItemInserted(oderList.size() - 1);
//                                oderList.remove(oderList.size() - 1);
//                                oderList.addAll(getOderResponse.getOrderList());
//                                oderAdapter = new OderAdapter(oderList, getActivity());
//                                rcl_ViewOder.setAdapter(oderAdapter);
//                                rcl_ViewOder.setLayoutManager(linearLayoutManager3);
//                                rcl_ViewOder.setHasFixedSize(true);
                            } else {
                                isScrolling = true;
                                pagenumber = 1;
                            }
                        } catch (Exception exception) {
                        }

                    } else {
                        Model.Dialog(getContext());
                        isScrolling = true;
                    }
                }
            }

            @Override
            public void onFailure(Call<GetOderResponse> call, Throwable t) {
                Model.Dialog(getContext());
                isScrolling = true;
            }
        });
    }
}