package com.purewhite.ywc.purewhite.adapter.recyclerview.loadview.io;

/**
 * @author yuwenchao
 */
public abstract class OnLoadListenerImp implements OnLoadListener{

    @Override
    public void loadback() {
        if (judge())
        {
            loadSuccess();
        }
    }

    protected boolean judge()
    {
        return true;
    }

    //加载更多
    public abstract void loadSuccess();

    //重新加载
    public abstract void loadAgain();

}
