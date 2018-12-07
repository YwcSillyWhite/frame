package com.purewhite.ywc.purewhite.ui.activity.mine.friend;

import com.purewhite.ywc.purewhite.mvp.presenter.BasePresenter;
import com.purewhite.ywc.purewhite.mvp.view.BaseView;
import com.purewhite.ywc.purewhite.ui.activity.mine.friend.adapter.FriendAdapter;

public class FriendContract {

    public interface View extends BaseView
    {
        FriendAdapter getAdapter();
    }

    public interface Presenter extends BasePresenter<View>
    {
        void getData();
    }
}
