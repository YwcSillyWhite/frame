package com.purewhite.ywc.purewhite.ui.fragment.mine;

import com.purewhite.ywc.purewhite.config.file.FileUtils;
import com.purewhite.ywc.purewhite.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhite.network.rxjava.HttpObserver;
import com.purewhite.ywc.purewhite.network.rxjava.RxSchedulers;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class MinePresenter extends PresenterImp<MineContract.View> implements MineContract.Presenter {
    @Override
    public void getCache() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext(FileUtils.getFileSizeShift(FileUtils.getFileSize(mView.getContext().
                        getCacheDir())));
            }
        }).compose(RxSchedulers.<String>compose())
                .subscribe(new HttpObserver<String>() {
                    @Override
                    public void onSuccess(String s) {
                        mView.ObtainCache(s);
                    }
                });
    }
}
