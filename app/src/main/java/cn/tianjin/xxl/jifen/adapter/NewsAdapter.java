package cn.tianjin.xxl.jifen.adapter;

import android.app.Activity;
import android.view.ViewGroup;

import cn.tianjin.xxl.jifen.model.News;
import cn.tianjin.xxl.jifen.view.NewsView;
import zuo.biao.library.base.BaseViewAdapter;

/**
 * Created by xxl on 2017/1/6.
 */

public class NewsAdapter extends BaseViewAdapter<News, NewsView> {
    //	private static final String TAG = "UserAdapter";

    public NewsAdapter(Activity context) {
        super(context);
    }

    @Override
    public NewsView createView(int position, ViewGroup parent) {
        return new NewsView(context, resources);
    }
}
