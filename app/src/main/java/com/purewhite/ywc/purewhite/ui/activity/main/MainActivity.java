package com.purewhite.ywc.purewhite.ui.activity.main;

import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.recyclerview.bean.BindBean;
import com.purewhite.ywc.purewhite.adapter.recyclerview.loadview.io.OnLoadListenerImp;
import com.purewhite.ywc.purewhite.databinding.ActivityMainBinding;
import com.purewhite.ywc.purewhite.mvp.activity.MvpActivity;
import com.purewhite.ywc.purewhite.network.retrofit.request.http.HttpService;
import com.purewhite.ywc.purewhite.network.retrofit.request.http.HttpUtils;
import com.purewhite.ywc.purewhite.network.rxjava.HttpObserver;
import com.purewhite.ywc.purewhite.network.rxjava.RxSchedulers;
import com.purewhite.ywc.purewhite.ptr.io.PtrCallBack;
import com.purewhite.ywc.purewhite.ui.activity.main.adapter.MainAdapter;
import com.purewhite.ywc.purewhite.view.popupwindow.DialogPopup;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;

/**
 * @author yuwenchao
 */
public class MainActivity extends MvpActivity<ActivityMainBinding,MainPresenter> implements MainContract.View {

    private MainAdapter mainAdapter;
    private DialogPopup dialogPopup;

    @Override
    protected MainPresenter creartPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        mDataBinding.ptrLayout.setEnabled(true);
        mDataBinding.ptrLayout.setPtrHandler(new PtrCallBack() {
            @Override
            public void onPullDown() {
            }
        });
        mainAdapter = new MainAdapter();
        mDataBinding.recycler.setLayoutManager(new GridLayoutManager(this,2));
        mDataBinding.recycler.setAdapter(mainAdapter);
        mainAdapter.setOnLoadListenerImp(new OnLoadListenerImp() {
            @Override
            public void onPullUp() {

            }

            @Override
            public void loadAgain() {

            }
        });
        mPresenter.getData();
        dialogPopup = new DialogPopup(this);



//        WindowManager mWindowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
//// 创建一个新的布局
//        WindowManager.LayoutParams param = new WindowManager.LayoutParams();
//// 设置窗口属性
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){//6.0
//            param.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
//        }else {
//            param.type =  WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
//        }
//        param.format = PixelFormat.TRANSLUCENT; // 支持透明
//        param.flags = WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN // 可在全屏幕布局, 不受状态栏影响
//                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE; // 最初不可获取焦点, 这样不影响底层应用接收触摸事件
////         param.alpha = 0.9f; // 悬浮窗的透明度
//        param.gravity = Gravity.LEFT | Gravity.TOP; // 悬浮窗的重力效果
//        param.width =100; // 悬浮窗宽度
//        param.height =100; // 悬浮窗高度

//// 以下将悬浮穿定位在屏幕中央
//        int screenWidth = mWindowManager.getDefaultDisplay().getWidth();
//        int screenHeight = mWindowManager.getDefaultDisplay().getHeight();
//        param.x = (screenWidth - param.width) / 2;
//        param.y = (screenHeight - param.height) / 2;
//// 创建悬浮窗view
//        View inflate = View.inflate(this, R.layout.popup_dialog, null);
//
//// 添加到屏幕
//        mWindowManager.addView(inflate, param);




        View head = LayoutInflater.from(this).inflate(R.layout.head, mDataBinding.recycler, false);
        mainAdapter.addHeadView(head);

        View foot = LayoutInflater.from(this).inflate(R.layout.foot, mDataBinding.recycler, false);
        mainAdapter.addFootView(foot);


        mPresenter.getRequest();
    }


    @Override
    public MainAdapter getAdapter() {
        return mainAdapter;
    }
}
