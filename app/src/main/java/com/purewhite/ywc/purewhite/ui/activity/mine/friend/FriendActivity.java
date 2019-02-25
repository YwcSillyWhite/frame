package com.purewhite.ywc.purewhite.ui.activity.mine.friend;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.fullview.FullView;
import com.purewhite.ywc.purewhite.adapter.viewholder.BaseViewHolder;
import com.purewhite.ywc.purewhite.bean.main.OnSingleListener;
import com.purewhite.ywc.purewhite.databinding.ActivityFriendBinding;
import com.purewhite.ywc.purewhite.mvp.activity.MvpActivity;
import com.purewhite.ywc.purewhite.ui.activity.mine.friend.adapter.FriendAdapter;
import com.purewhite.ywc.purewhite.view.recyclerview.card.help.CardHelperCallBack;
import com.purewhite.ywc.purewhite.view.recyclerview.card.io.CardScrollListener;

/**
 * @author yuwenchao
 */
public class FriendActivity extends MvpActivity<ActivityFriendBinding,FriendPresenter>
        implements FriendContract.View {

    private FriendAdapter friendAdapter;
    private CardScrollListener cardScrollListener=new CardScrollListener() {
        private TextView friend_tb;
        @Override
        public void call(RecyclerView.ViewHolder viewHolder, int type) {
            friendAdapter.removeFlush(viewHolder.getLayoutPosition());
            if (friendAdapter.getDataCount()<=8)
            {
                mPresenter.autoPage();
                mPresenter.requestData();
            }
            //解决重复
            viewHolder.itemView.setRotation(0);
            if (friendAdapter.dataType(viewHolder) &&viewHolder instanceof BaseViewHolder)
            {
                friend_tb= ((BaseViewHolder) viewHolder).findViewId(R.id.friend_tb);
                friend_tb.setVisibility(View.GONE);
            }
        }

        @Override
        public void scrollChange(RecyclerView.ViewHolder viewHolder, float scale) {
            //弯曲角度
            viewHolder.itemView.setRotation(scale*15);
            if (friendAdapter.dataType(viewHolder) &&viewHolder instanceof BaseViewHolder) {
                friend_tb = ((BaseViewHolder) viewHolder).findViewId(R.id.friend_tb);
                friend_tb.setText(scale < 0 ? "我喜欢" : "我不喜欢");
                if (scale < 0)
                    scale = -scale;
                friend_tb.getBackground().setAlpha((int)(scale*255));
                friend_tb.setVisibility(scale == 0 ? View.GONE : View.VISIBLE);
            }
        }
    };

    private OnSingleListener onSingleListener=new OnSingleListener() {
        @Override
        public void onSingleClick(View v) {
            switch (v.getId())
            {
                case R.id.action_left:
                    finish();
                    break;
            }
        }
    };

    private ItemTouchHelper itemTouchHelper;

    @Override
    protected FriendPresenter creartPresenter() {
        return new FriendPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_friend;
    }

    @Override
    public FriendAdapter getAdapter() {
        return friendAdapter;
    }

    @Override
    protected void initView() {
        mDataBinding.action.actionLeft.setOnClickListener(onSingleListener);
        mDataBinding.action.actionCenter.setText("添加好友");
        initRecycler();
        mPresenter.requestData();
    }

    private void initRecycler() {
        CardHelperCallBack cardHelperCallBack = new CardHelperCallBack(cardScrollListener);
        mDataBinding.recyclerView.setLayoutManager(cardHelperCallBack.getCardLayoutManager());
        friendAdapter = new FriendAdapter();
        //设置开始fullview加载状态
        friendAdapter.getFullView().setFullState(FullView.LODA,false);
        mDataBinding.recyclerView.setAdapter(friendAdapter);
        itemTouchHelper = new ItemTouchHelper(cardHelperCallBack);
        itemTouchHelper.attachToRecyclerView(mDataBinding.recyclerView);
    }


}
