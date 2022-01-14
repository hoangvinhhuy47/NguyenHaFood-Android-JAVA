package com.example.nguyenhafood.Activity.BaseActivity;

import android.app.Activity;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity  extends AppCompatActivity {
    public void goToNewActivity(Activity currentActivity, Class newActivity) {
        Intent intent = new Intent(currentActivity, newActivity);
        currentActivity.startActivity(intent);
    }
    public void goToNewActivityAndClosePreviousActivities(Class newActivity) {
        Intent intent = new Intent(getApplicationContext(), newActivity);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void goToActivity(Class newActivity) {
        Intent intent = new Intent(getApplicationContext(), newActivity);
        intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}
