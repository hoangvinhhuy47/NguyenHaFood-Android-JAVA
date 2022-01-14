package com.example.nguyenhafood.Fragment.BaseFragment;

import android.content.Intent;
import android.net.Uri;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.nguyenhafood.Gobal.Gobal;

public class BaseFragment extends Fragment {

    public void goToNewActivityAndClosePreviousActivities(Class newActivity) {
        Intent intent = new Intent(getActivity(), newActivity);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void gotoActivity(Class newActivity) {
        Intent intent = new Intent(getActivity(), newActivity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Gobal.PromotionCode="";
        startActivity(intent);
    }

    public void goToNewAcvity_Key(Class newActivity, String key, String ID) {
        Intent intent = new Intent(getActivity(), newActivity);
        intent.putExtra(key, ID);
        startActivity(intent);
    }

    public void goToView_Link(String key) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(key));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
