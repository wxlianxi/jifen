package cn.tianjin.xxl.jifen.activity_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.tianjin.xxl.jifen.R;
import zuo.biao.library.base.BaseFragment;
import zuo.biao.library.base.BaseTabFragment;
import zuo.biao.library.ui.AlertDialog;
import zuo.biao.library.ui.PlacePickerWindow;
import zuo.biao.library.util.PlaceUtil;
import zuo.biao.library.util.StringUtil;

/**设置fragment
 * @author Lemon
 * @use new SettingFragment(),详细使用见.DemoFragmentActivity(initData方法内)
 */
public class SettleFragment extends BaseFragment implements View.OnClickListener, AlertDialog.OnDialogButtonClickListener {
//	private static final String TAG = "SettleFragment";

    //与Activity通信<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    public static final String ARGUMENT_CITY = "ARGUMENT_CITY";
    /**创建一个Fragment实例
     * @return
     */
    public static SettleFragment createInstance(String city) {
        SettleFragment fragment = new SettleFragment();

        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT_CITY, city);

        fragment.setArguments(bundle);
        return fragment;
    }

    //与Activity通信>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    private String city;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //类相关初始化，必须使用<<<<<<<<<<<<<<<<
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.settle_fragment);
        //类相关初始化，必须使用>>>>>>>>>>>>>>>>

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



    //UI显示区(操作UI，但不存在数据获取或处理代码，也不存在事件监听代码)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    private ImageView ivSettingHead;
    private TextView tvSettleLeft;
    @Override
    public void initView() {//必须调用

        ivSettingHead = (ImageView) findViewById(R.id.ivSettingHead);

        tvSettleLeft = (TextView) findViewById(R.id.tvSettleLeft);
    }


    /**一行代码没必要新建方法，这里是为了给DemoBottomTabActivity调用
     */
    public void selectPlace() {
        toActivity(PlacePickerWindow.createIntent(context, context.getPackageName(), 2)
                , REQUEST_TO_PLACE_PICKER, false);
    }

    //UI显示区(操作UI，但不存在数据获取或处理代码，也不存在事件监听代码)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










    //Data数据区(存在数据获取或处理代码，但不存在事件监听代码)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Override
    public void initData() {//必须调用

        tvSettleLeft.setText(StringUtil.isNotEmpty(city, true) ? StringUtil.getTrimedString(city) : "杭州");

    }


    private void logout() {
        context.finish();
    }


    //Data数据区(存在数据获取或处理代码，但不存在事件监听代码)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








    //Event事件区(只要存在事件监听代码就是)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Override
    public void initEvent() {//必须调用
        tvSettleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPlace();
            }
        });
        //ivSettingHead.setOnClickListener(this);

        //findViewById(R.id.llSettingSetting).setOnClickListener(this);
        //findViewById(R.id.llSettingAbout).setOnClickListener(this);
        //findViewById(R.id.llSettingLogout).setOnClickListener(this);
    }




    @Override
    public void onDialogButtonClick(int requestCode, boolean isPositive) {
        if (! isPositive) {
            return;
        }

        switch (requestCode) {
            case 0:
                logout();
                break;
            default:
                break;
        }
    }


    //系统自带监听方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Override
    public void onClick(View v) {//直接调用不会显示v被点击效果
        switch (v.getId()) {
            case R.id.ivSettingHead:
                showShortToast("onClick  ivSettingHead");
                break;
            case R.id.llSettingSetting:
                //toActivity(SettingActivity.createIntent(context));
                break;
            case R.id.llSettingAbout:
                //toActivity(AboutActivity.createIntent(context));
                break;
            case R.id.llSettingLogout:
                new AlertDialog(context, "退出登录", "确定退出登录？", true, 0, this).show();
                break;
            default:
                break;
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
                        tvSettleLeft.setText(StringUtil.getTrimedString(placeList.get(PlaceUtil.LEVEL_CITY)));
                    }
                }
                break;
            default:
                break;
        }
    }



    //类相关监听>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //系统自带监听方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //Event事件区(只要存在事件监听代码就是)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>









    //内部类,尽量少用<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



    //内部类,尽量少用>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}

