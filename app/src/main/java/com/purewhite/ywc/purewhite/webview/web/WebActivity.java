package com.purewhite.ywc.purewhite.webview.web;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.config.OnSingleListener;
import com.purewhite.ywc.purewhite.config.TagUtils;
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


    @Override
    protected void beforeView() {
        super.beforeView();
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
    }

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
        mDataBinding.action.actionLeft.setOnClickListener(onSingleListener);
        mDataBinding.action.actionCenter.setText("纯白");
        initWeb();
    }


    @SuppressLint("JavascriptInterface")
    private void initWeb() {
        WebView webView = mDataBinding.webLayout.getWebView();
        webView.setWebChromeClient(webChromeClient);
        webView.setWebViewClient(webViewClient);
        //将定义的 js 接口类添加到 WebView 中 参数2代码对象名
        webView.addJavascriptInterface(new WebHelp(),"android");
        //webview加载uri
        webView.loadUrl(baseUri);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDataBinding.webLayout.onDestroy();
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
        WebView webView = mDataBinding.webLayout.getWebView();
        //webview是不是可以回退
        if (webView.canGoBack())
        {
            //webview回退
            webView.goBack();
        }
        else
        {
            finish();
        }

    }


    //h5页面调用app的方法
    class WebHelp
    {

    }


    /**
     * - onPageFinished 页面请求完成
     * - onPageStarted 页面开始加载
     * - shouldOverrideUrlLoading 拦截url
     * - onReceivedError 访问错误时回调，例如访问网页时报错404，在这个方法回调的时候可以加载错误页面。
     */
    private WebViewClient webViewClient=new WebViewClient()
    {
        //加载开始
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            mDataBinding.progressBar.setVisibility(View.VISIBLE);
            mDataBinding.showImg.setVisibility(View.GONE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mDataBinding.progressBar.setVisibility(View.GONE);
        }


        //拦截非http的接口
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            String url = request.getUrl().toString();
            if( url.startsWith("http:") || url.startsWith("https:") ) {
                return super.shouldOverrideUrlLoading(view, request);
            }
            //如果不需要其他对点击链接事件的处理返回true，否则返回false
            return true;
        }


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if( url.startsWith("http:") || url.startsWith("https:") ) {
                return super.shouldOverrideUrlLoading(view, url);
            }
            //如果不需要其他对点击链接事件的处理返回true，否则返回false
            return true;
        }
    };


    /**
     * - onJsAlert webview不支持js的alert弹窗，需要自己监听然后通过dialog弹窗
     * - onReceivedTitle 获取网页标题
     * - onReceivedIcon 获取网页icon
     * - onProgressChanged 加载进度回调
     */
    private WebChromeClient webChromeClient=new WebChromeClient()
    {
        private CustomViewCallback customViewCallback;
        private View mCustomView;
        //webview加载进度
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            mDataBinding.progressBar.setProgress(newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            mDataBinding.action.actionCenter.setText(TextUtils.isEmpty(title)?"纯白":title);
            if ((title!=null&&title.endsWith("- 天猫Tmall.com"))||title.equals("商品详情"))
            {
                mDataBinding.showImg.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            super.onShowCustomView(view, callback);
            if (mCustomView != null) {
                customViewCallback.onCustomViewHidden();
                return;
            }
            WebView webView = mDataBinding.webLayout.getWebView();
            String url = webView.getUrl();
            if (url.startsWith("http://m.iqiyi.com"))
            {
                mDataBinding.webTop.setVisibility(View.GONE);
                setOrientation(false);
                setFullScreen(true);
            }
            webView.setVisibility(View.GONE);
            mCustomView = view;
            mDataBinding.webLayout.addView(mCustomView);
            this.customViewCallback = callback;
        }




        @Override
        public void onHideCustomView() {
            super.onHideCustomView();
            if (mCustomView == null) {
                return;
            }
            WebView webView = mDataBinding.webLayout.getWebView();
            String url = webView.getUrl();
            if (url.startsWith("http://m.iqiyi.com"))
            {
                mDataBinding.webTop.setVisibility(View.VISIBLE);
                setOrientation(true);
                setFullScreen(false);
            }
            mDataBinding.webLayout.getWebView().setVisibility(View.VISIBLE);
            mDataBinding.webLayout.removeView(mCustomView);
            customViewCallback.onCustomViewHidden();
            mCustomView = null;
        }
    };

}
