package com.example.nguyenhafood.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.nguyenhafood.Activity.Acount_login.ShareAppActivity;
import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Fragment.AcountFragment;
import com.example.nguyenhafood.Fragment.Acount_LoginFragment;
import com.example.nguyenhafood.Fragment.MenuFragment;
import com.example.nguyenhafood.Fragment.Notification.NotificationFragment;
import com.example.nguyenhafood.Fragment.SearchFragment;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Fragment.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class MainActivity extends AppCompatActivity {
    SpaceNavigationView spaceNavigationView;
    FloatingActionButton floatingActionButton, website, linkmess, linkzalo, shareapp, linkfacebook, searchacti, close;
    boolean show = false;
    private static String linkmesstext = "https://www.facebook.com/messages/t/118528615500419";
    private static String linkwebsite = "https://nguyenhafood.vn/";
    private static String linkface = "https://www.facebook.com/thucphamnguyenha/";
    private static String linkzalotext = "https://chat.zalo.me/?c=8220160791292897639";
    DBHelper db;
    RelativeLayout mainrl;
    FrameLayout nav_item;
    private Handler mHandler = new Handler();
    HomeFragment homeFragment = new HomeFragment();
    MenuFragment menuFragment = new MenuFragment();
    NotificationFragment notificationFragment = new NotificationFragment();
    Acount_LoginFragment acount_loginFragment = new Acount_LoginFragment();
    AcountFragment acountFragment = new AcountFragment();
    final FragmentManager fm = getSupportFragmentManager();
    SpaceNavigationView space;
    Fragment active = homeFragment;
    private boolean doubleBackToExitPressedOnce;
    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mHandler != null) {
            mHandler.removeCallbacks(mRunnable);
        }
    }

    @Override
    public void onBackPressed() {

    }
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed();
//            return;
//        }
//        this.doubleBackToExitPressedOnce = true;
//        mHandler.postDelayed(mRunnable, 200);
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setCancelable(false);
//        builder.setMessage("Bạn Có Muốn Thoát App Không?");
//        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //if user pressed "yes", then he is allowed to exit from application
//                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
//                homeIntent.addCategory( Intent.CATEGORY_HOME );
//                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(homeIntent);
//            }
//        });
//        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //if user select "No", just cancel this dialog and continue with app
//                dialog.cancel();
//            }
//        });
//        AlertDialog alert = builder.create();
//        alert.show();


    @Override
    protected void onStart() {
        super.onStart();
        if (Gobal.ShowFragment.equals("") == false) {
            if (Gobal.ShowFragment.equals("Home") == true) {
                spaceNavigationView.changeCurrentItem(0);
                Gobal.ShowFragment = "";
                fm.beginTransaction().hide(active).show(homeFragment).commit();
                active = homeFragment;
            }
            if (Gobal.ShowFragment.equals("Menu") == true) {
                Gobal.ShowFragment = "";
                spaceNavigationView.changeCurrentItem(1);
                fm.beginTransaction().hide(active).show(menuFragment).commit();
                active = menuFragment;
            }
            if (Gobal.ShowFragment.equals("Acount") == true) {
                Gobal.ShowFragment = "";
                spaceNavigationView.changeCurrentItem(3);
                if (Gobal.getLoginStatus() == 0) {
                    fm.beginTransaction().hide(active).show(acountFragment).commit();
                    active = acountFragment;
                } else if (Gobal.getLoginStatus() == 1) {
                    fm.beginTransaction().hide(active).show(acount_loginFragment).commit();
                    active = acount_loginFragment;
                }
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DBHelper(getApplication());

        ////
        nav_item = findViewById(R.id.nav_item);
        setContentView(R.layout.activity_main);
        website = findViewById(R.id.website);
        linkmess = findViewById(R.id.linkmess);
        linkzalo = findViewById(R.id.linkzalo);
        linkfacebook = findViewById(R.id.linkfacebook);
        shareapp = findViewById(R.id.shareapp);
        searchacti = findViewById(R.id.searchacti);
        spaceNavigationView = findViewById(R.id.space);
        Hide();
        mainrl = findViewById(R.id.mainrl);
        space = findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("Trang Chủ", R.drawable.home1));
        spaceNavigationView.addSpaceItem(new SpaceItem("Danh Mục", R.drawable.menu));
        spaceNavigationView.addSpaceItem(new SpaceItem("Thông Báo", R.drawable.notification1));
        spaceNavigationView.addSpaceItem(new SpaceItem("Tài Khoản", R.drawable.user));
        spaceNavigationView.setCentreButtonIcon(R.drawable.sendnew);
        if (Gobal.getLoginStatus() == 1) {
            fm.beginTransaction().add(R.id.nav_item, acount_loginFragment, "4").hide(acount_loginFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();

        } else {
            fm.beginTransaction().add(R.id.nav_item, acountFragment, "4").hide(acountFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).commit();

        }
        fm.beginTransaction().add(R.id.nav_item, notificationFragment, "3").hide(notificationFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).commit();
        fm.beginTransaction().add(R.id.nav_item, menuFragment, "2").hide(menuFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).commit();
        fm.beginTransaction().add(R.id.nav_item, homeFragment, "1").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).commit();
        nav_item = findViewById(R.id.nav_item);
        spaceNavigationView.showIconOnly();


        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                spaceNavigationView.setCentreButtonSelectable(true);

                if (show == false) {
                    Show();
                    show = true;
                } else if (show == true) {
                    Hide();
                    show = false;
                }
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                if (itemName.equals("Trang Chủ") == true) {
                    fm.beginTransaction().hide(active).show(homeFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).commit();
                    active = homeFragment;
                    homeFragment.onStart();
                    homeFragment.onResume();
                    Hide();

                } else if (itemName.equals("Danh Mục") == true) {
                    fm.beginTransaction().hide(active).show(menuFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).commit();
                    active = menuFragment;
                    menuFragment.onStart();
                    menuFragment.onResume();
                    Hide();
                } else if (itemName.equals("Thông Báo") == true) {
                    fm.beginTransaction().hide(active).show(notificationFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).commit();
                    active = notificationFragment;
                    notificationFragment.onStart();
                    notificationFragment.onResume();
                    Hide();
                } else if (itemName.equals("Tài Khoản") == true) {
                    if (Gobal.getLoginStatus() == 0) {
                        fm.beginTransaction().hide(active).show(acountFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).commit();
                        active = acountFragment;
                        acountFragment.onStart();
                        acountFragment.onResume();
                        Hide();
                    } else if (Gobal.getLoginStatus() == 1) {
                        fm.beginTransaction().hide(active).show(acount_loginFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).commit();
                        active = acount_loginFragment;
                        acount_loginFragment.onStart();
                        acount_loginFragment.onResume();
                        Hide();
                    }
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
            }
        });

    }

    private void Hide() {
        linkmess.hide();
        linkzalo.hide();
        linkfacebook.hide();
        shareapp.hide();
        searchacti.hide();
        website.hide();
    }

    private void Show() {
        website.show();
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkwebsite));
                startActivity(intent);
                Hide();
            }
        });
        linkmess.show();
        linkmess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkmesstext));
                startActivity(intent);
                Hide();
            }
        });
        linkfacebook.show();
        linkfacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkface));
                startActivity(intent);
                Hide();
            }
        });
        linkzalo.show();
        linkzalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkzalotext));
                startActivity(intent);
                Hide();
            }
        });
        shareapp.show();
        shareapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), ShareAppActivity.class);
                startActivity(intent);
                Hide();
            }
        });
        searchacti.show();
        searchacti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SearchFragment.class);
                startActivity(intent);
                Hide();
            }
        });
    }
}
