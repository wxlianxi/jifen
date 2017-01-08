package cn.tianjin.xxl.jifen.view;

import android.app.Activity;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import cn.tianjin.xxl.jifen.R;
import cn.tianjin.xxl.jifen.model.News;
import zuo.biao.library.base.BaseModel;
import zuo.biao.library.base.BaseView;
import zuo.biao.library.util.ImageLoaderUtil;
import zuo.biao.library.util.Log;
import zuo.biao.library.util.StringUtil;

/**
 * Created by xxl on 2017/1/6.
 */

public class NewsView extends BaseView<News> implements View.OnClickListener {
    private static final String TAG = "NewsView";

    public NewsView(Activity context, Resources resources) {
        super(context, resources);
    }

    public ImageView ivNewsViewImage;

    public TextView tvNewsViewTitle;
    public TextView tvNewsViewDate;



    @Override
    public View createView(LayoutInflater inflater) {
        convertView=inflater.inflate(R.layout.news_view,null);
        ivNewsViewImage=findViewById(R.id.ivNewsViewImage,this);
        tvNewsViewTitle=findViewById(R.id.tvNewsViewTitle,this);
        tvNewsViewDate=findViewById(R.id.tvNewsViewDate,this);
        return convertView;
    }

    @Override
    public void bindView(News data) {
        if (data == null) {
            Log.e(TAG, "bindView data == null >> data = new News(); ");
            data = new News();
        }
        this.data = data;
        ImageLoaderUtil.loadImage(ivNewsViewImage,data.getImage(),ImageLoaderUtil.TYPE_OVAL);
        tvNewsViewTitle.setText(StringUtil.getTrimedString(data.getTitle()));
        tvNewsViewDate.setText(StringUtil.getTrimedString(data.getDate()));

    }

    @Override
    public void onClick(View view) {
        if (BaseModel.isCorrect(data) == false) {
            return;
        }
    }
}
