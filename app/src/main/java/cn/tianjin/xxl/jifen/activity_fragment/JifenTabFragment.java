package cn.tianjin.xxl.jifen.activity_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.tianjin.xxl.jifen.R;
import cn.tianjin.xxl.jifen.adapter.GlideImageLoader;
import zuo.biao.library.base.BaseTabFragment;
import zuo.biao.library.ui.PlacePickerWindow;
import zuo.biao.library.util.PlaceUtil;
import zuo.biao.library.util.StringUtil;

/**使用方法：复制>粘贴>改名>改代码  */
/**带标签的Fragment示例
 * @author Lemon
 * @use new DemoTabFragment(),具体参考.DemoFragmentActivity(initData方法内)
 */

public class JifenTabFragment extends BaseTabFragment implements View.OnClickListener{
    //	private static final String TAG = "DemoTabFragment";

    //与Activity通信<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public static final String ARGUMENT_CITY = "ARGUMENT_CITY";


    List<?> images;
    List<String> titles;
    Banner banner;

    /**创建一个Fragment实例
     * @return
     */
    public static JifenTabFragment createInstance(String city) {
        JifenTabFragment fragment = new JifenTabFragment();

        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT_CITY, city);

        fragment.setArguments(bundle);
        return fragment;
    }

    //与Activity通信>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    private String city;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState, R.layout.jifen_tab_fragment);
        //		needReload = true;

        argument = getArguments();
        if (argument != null) {
            city = argument.getString(ARGUMENT_CITY, city);
        }

        //功能归类分区方法，必须调用<<<<<<<<<<
        initView();
        initData();
        initEvent();
        //功能归类分区方法，必须调用>>>>>>>>>>

        return view;
    }



    //UI显示区(操作UI，但不存在数据获取或处理代码，也不存在事件监听代码)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    private TextView tvDemoTabLeft;
    @Override
    public void initView() {//必须在onCreate方法内调用
        super.initView();

        tvDemoTabLeft = (TextView) findViewById(R.id.tvDemoTabLeft);
    }

    /**一行代码没必要新建方法，这里是为了给DemoBottomTabActivity调用
     */
    public void selectPlace() {
        toActivity(PlacePickerWindow.createIntent(context, context.getPackageName(), 2)
                , REQUEST_TO_PLACE_PICKER, false);
    }
    /**一行代码没必要新建方法，这里是为了给DemoBottomTabActivity调用
     */
    public void selectMan() {
        toActivity(JifenListActivity.createIntent(context, 0).putExtra(JifenTabActivity.INTENT_TITLE, "筛选"));
    }

    //UI显示区(操作UI，但不存在数据获取或处理代码，也不存在事件监听代码)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










    //Data数据区(存在数据获取或处理代码，但不存在事件监听代码)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Override
    public void initData() {//必须在onCreate方法内调用
        super.initData();

        tvDemoTabLeft.setText(StringUtil.isNotEmpty(city, true) ? StringUtil.getTrimedString(city) : "杭州");
    }

    @Override
    @Nullable
    public String getTitleName() {
        return null;
    }

    @Override
    @Nullable
    public String getReturnName() {
        return null;
    }

    @Override
    protected String[] getTabNames() {
        return new String[] {"附近", "热门"};
    }

    @Override
    protected Fragment getFragment(int position) {

        JifenListFragment fragment = JifenListFragment.createInstance();
        Bundle bundle = fragment.getArguments();
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt(JifenListFragment.ARGUMENT_POSITION, position);
        fragment.setArguments(bundle);

        return fragment;
    }



    //Data数据区(存在数据获取或处理代码，但不存在事件监听代码)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








    //Event事件区(只要存在事件监听代码就是)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Override
    public void initEvent() {//必须在onCreate方法内调用
        super.initEvent();

        tvDemoTabLeft.setOnClickListener(this);
        findViewById(R.id.tvDemoTabRight).setOnClickListener(this);
    }

    //系统自带监听<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tvDemoTabLeft:
                selectPlace();
                break;
            case R.id.tvDemoTabRight:
                selectMan();
                break;
            default:
        }
    }


    //类相关监听<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    private static final int REQUEST_TO_PLACE_PICKER = 10;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUEST_TO_PLACE_PICKER:
                if (data != null) {
                    ArrayList<String> placeList = data.getStringArrayListExtra(PlacePickerWindow.RESULT_PLACE_LIST);
                    if (placeList != null && placeList.size() > PlaceUtil.LEVEL_CITY) {
                        tvDemoTabLeft.setText(StringUtil.getTrimedString(placeList.get(PlaceUtil.LEVEL_CITY)));
                    }
                }
                break;
            default:
                break;
        }
    }


    //类相关监听>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //系统自带监听>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //Event事件区(只要存在事件监听代码就是)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








    //内部类,尽量少用<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



    //内部类,尽量少用>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    /*@Override
    public void onStart() {
        super.onStart();
        banner = (Banner) getView().findViewById(R.id.banner);
        String[] urls = getResources().getStringArray(R.array.url4);
        String[] tips = getResources().getStringArray(R.array.title);
        List list = Arrays.asList(urls);
        images = new ArrayList(list);
        List list1 = Arrays.asList(tips);
        titles= new ArrayList(list1);
        System.out.println("lllllll:"+images);
        System.out.println("lllllll:"+BannerConfig.CIRCLE_INDICATOR_TITLE);
        System.out.println("lllllll:"+banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }*/
}
