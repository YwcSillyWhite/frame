package com.purewhite.ywc.purewhite.adapter.callback;

/**
 * @author yuwenchao
 */
public abstract   class OnLoadListenerImp implements OnLoadListener{

    protected boolean judge()
    {
        return true;
    }

    //加载更多
    public abstract void pullUp();

    @Override
    public void loadMore() {
        if (judge())
        {
            pullUp();
        }
    }

}
