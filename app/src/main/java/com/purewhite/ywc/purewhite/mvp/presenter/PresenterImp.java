package com.purewhite.ywc.purewhite.mvp.presenter;

import com.purewhite.ywc.purewhite.mvp.view.BaseView;

import java.lang.ref.WeakReference;

/**
 *
 * @author yuwenchao
 * @date 2018/11/5
 * Presenter实现类
 */

public class PresenterImp<V extends BaseView> implements BasePresenter<V> {

    protected V mView;
    private WeakReference<V> vWeakReference;

    @Override
    public void addView(V view) {
        vWeakReference = new WeakReference<>(view);
        mView=vWeakReference.get();
    }

    @Override
    public void deleteView() {
        if (vWeakReference!=null)
        {
            vWeakReference.clear();
            mView=null;

        }
    }
}
