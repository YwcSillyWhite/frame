package com.purewhite.ywc.purewhite.ui.activity.web;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.config.OnSingleListener;
import com.purewhite.ywc.purewhite.config.TagUtils;
import com.purewhite.ywc.purewhite.config.ToastUtils;
import com.purewhite.ywc.purewhite.databinding.ActivityWebBinding;
import com.purewhite.ywc.purewhite.mvp.activity.MvpActivity;

/**
 * web就是这么简单
 * @author yuwenchao
 */
public class WebActivity extends MvpActivity<ActivityWebBinding,WebPresenter> implements WebContract.View {

    private OnSingleListener onSingleListener=new OnSingleListener() {
        @Override
        public void onSingleClick(View v) {
            switch (v.getId())
            {
                case R.id.action_left:
                    rollBack();
                    break;
            }
        }
    };


    private WebViewClient webViewClient=new WebViewClient()
    {
        //加载开始
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            mDataBinding.progressBar.setVisibility(View.VISIBLE);
            mDataBinding.showImg.setVisibility(View.GONE);
        }

        //加载结束
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            String title = view.getTitle();
            action_center.setText(TextUtils.isEmpty(title)?"纯白":title);
            if (view.getProgress()==100)
            {
                mDataBinding.progressBar.setVisibility(View.GONE);
                if (title.endsWith("- 天猫Tmall.com"))
                {
                    mDataBinding.showImg.setVisibility(View.VISIBLE);
                }
            }
        }

        //加载错误
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            Log.d("ywc","onReceivedError:");
        }

        //加载错误 https:// 这里的ssl，表示接口通过ssl加密过后的接口
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
            Log.d("ywc","onReceivedSslError:");
        }
    };

    private WebChromeClient webChromeClient=new WebChromeClient()
    {
        //webview加载进度
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            mDataBinding.progressBar.setProgress(newProgress);
            Log.d("ywc","onProgressChanged"+newProgress);
        }

    };

    private TextView action_center;
    //默认链接
    private String baseUri="";

    @Override
    protected WebPresenter creartPresenter() {
        return new WebPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {
        Bundle bundle = getIntent().getExtras();
        baseUri=bundle.getString(TagUtils.uri,null);
        if (TextUtils.isEmpty(baseUri))
        {
            finish();
            return;
        }

        mDataBinding.actionBar.findViewById(R.id.action_left).setOnClickListener(onSingleListener);
        action_center = mDataBinding.actionBar.findViewById(R.id.action_center);
        action_center.setText("纯白");
        initWeb();
    }

    @SuppressLint("JavascriptInterface")
    private void initWeb() {
        mDataBinding.webView.setWebChromeClient(webChromeClient);
        mDataBinding.webView.setWebViewClient(webViewClient);

        /**
         * 参数 1  是帮助对象，任意都行
         * 参数 2  是5代码里面的一个"对象"类型
         * 我这采用的是互利联盟里面一个链接
         */
        mDataBinding.webView.addJavascriptInterface(new WebHelp(),"layer");
        //webview加载uri
        mDataBinding.webView.loadUrl(baseUri);
        WebSettings webSettings = mDataBinding.webView.getSettings();
        //webView自适应手机屏幕
        webSettings.setUseWideViewPort(true);
        //支持js
        webSettings.setJavaScriptEnabled(true);


        //设置视图是否加载概览模式的网页
//        webSettings.setLoadWithOverviewMode(true);


////        //https图片不显示
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
////        }
//
//        webSettings.setDomStorageEnabled(true);
//        webSettings.setJavaScriptEnabled(true);
//        webSettings.setBlockNetworkImage(false);
//
//
//        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
////        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
//        //支持手势缩放，并且隐藏缩放按钮
//        webSettings.setDisplayZoomControls(false);



//        //设置显示缩放按钮
//        webSettings.setBuiltInZoomControls(true);
//        //使页面支持缩放
//        webSettings.setSupportZoom(true);
//        //不使用缓存
//        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK)
        {
            rollBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void rollBack()
    {
        //webview是不是可以回退
        if (mDataBinding.webView.canGoBack())
        {
            //webview回退
            mDataBinding.webView.goBack();
        }
        else
        {
            finish();
        }

    }


    //h5页面调用app的方法
    class WebHelp
    {
        @JavascriptInterface
        public void msg(String content)
        {
            ToastUtils.show(content);
        }
    }
}
