package cn.tianjin.xxl.jifen.adapter;

/**
 * Created by jack on 2016/12/10.
 */

/** 使用方法：复制>粘贴>改名>改代码  */

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cn.tianjin.xxl.jifen.R;
import zuo.biao.library.base.BaseAdapter;
import zuo.biao.library.model.Entry;
import zuo.biao.library.util.StringUtil;

/**adapter模板，最灵活且性能最好，但如果有setOnClickListener等事件就不方便了
 * *适用于listView,gridView
 * @author Lemon
 * @use new DemoAdapter(...),具体参考.DemoActivity(setList方法内)
 */
public class DemoAdapter extends BaseAdapter<Entry<String, String>> {
//	private static final String TAG = "DemoAdapter";


    public DemoAdapter(Activity context) {
        super(context);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //示例代码<<<<<<<<<<<<<<<<
        ViewHolder holder = convertView == null ? null : (ViewHolder) convertView.getTag();
        if (holder == null) {
            //TODO demo_item改为你所需要的layout文件
            convertView = inflater.inflate(R.layout.demo_item, parent, false);
            holder = new ViewHolder();

            holder.ivDemoItemHead = (ImageView) convertView.findViewById(R.id.ivDemoItemHead);
            holder.tvDemoItemName = (TextView) convertView.findViewById(R.id.tvDemoItemName);
            holder.tvDemoItemNumber = (TextView) convertView.findViewById(R.id.tvDemoItemNumber);

            convertView.setTag(holder);
        }

        final Entry<String, String> data = getItem(position);

        holder.tvDemoItemName.setText(StringUtil.getTrimedString(data.getValue()));
        holder.tvDemoItemNumber.setText(StringUtil.getNoBlankString(data.getKey()));

        return super.getView(position, convertView, parent);
        //示例代码>>>>>>>>>>>>>>>>
    }

    static class ViewHolder {
        //示例代码<<<<<<<<<<<<<<<<
        public ImageView ivDemoItemHead;
        public TextView tvDemoItemName;
        public TextView tvDemoItemNumber;
        //示例代码>>>>>>>>>>>>>>>>
    }


}
