package cn.tianjin.xxl.jifen.activity_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;


import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.tianjin.xxl.jifen.R;
import cn.tianjin.xxl.jifen.adapter.GlideImageLoader;
import cn.tianjin.xxl.jifen.adapter.NewsAdapter;
import cn.tianjin.xxl.jifen.model.News;
import cn.tianjin.xxl.jifen.util.HttpRequest;
import zuo.biao.library.base.BaseHttpListFragment;
import zuo.biao.library.base.BaseModel;
import zuo.biao.library.interfaces.AdapterCallBack;
import zuo.biao.library.interfaces.CacheCallBack;
import zuo.biao.library.util.Json;

/**用户列表界面fragment
 * @author Lemon
 * @use new UserListFragment(),详细使用见.DemoFragmentActivity(initData方法内)
 * @must 查看 .HttpManager 中的@must和@warn
 *       查看 .SettingUtil 中的@must和@warn
 */
public class UserListFragment extends BaseHttpListFragment<News, NewsAdapter>//CacheAdapter<User, UserView, UserAdapter>>//
        implements AdapterView.OnItemClickListener, CacheCallBack<News> {
    //	private static final String TAG = "UserListFragment";

    //与Activity通信<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public static final String ARGUMENT_RANGE = "ARGUMENT_RANGE";

    List<?> images;
    List<String> titles;
    Banner banner;

    public static UserListFragment createInstance(int range) {
        UserListFragment fragment = new UserListFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(ARGUMENT_RANGE, range);

        fragment.setArguments(bundle);
        return fragment;
    }

    //与Activity通信>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



    public static final int RANGE_ALL = HttpRequest.USER_LIST_RANGE_ALL;
    public static final int RANGE_RECOMMEND = HttpRequest.USER_LIST_RANGE_RECOMMEND;

    private int range = RANGE_ALL;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        argument = getArguments();
        if (argument != null) {
            range = argument.getInt(ARGUMENT_RANGE, range);
        }

        Toast.makeText(context, "服务器配置有误111，请查看这个类的@must", Toast.LENGTH_LONG).show();

        initCache(this);

        //功能归类分区方法，必须调用<<<<<<<<<<
        initView();
        initData();
        initEvent();
        //功能归类分区方法，必须调用>>>>>>>>>>



        lvBaseList.onRefresh();

        return view;
    }


    //UI显示区(操作UI，但不存在数据获取或处理代码，也不存在事件监听代码)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Override
    public void initView() {//必须调用



        super.initView();

    }

    @Override
    public void setList(final List<News> list) {
        setList(new AdapterCallBack<NewsAdapter>() {

            @Override
            public NewsAdapter createAdapter() {
                return new NewsAdapter(context);
            }

            @Override
            public void refreshAdapter() {
                adapter.refresh(list);
            }
        });

        //		//ListView内容太复杂（比如微信朋友圈）时容易卡顿，可使用以下方式异步加载
        //		setListAsync(list, new OnResultListener<List<String>>() {
        //
        //			@Override
        //			public void onResult(final List<String> result) {
        //				setList(new AdapterCallBack<CacheAdapter<User, NewsView, UserAdapter>>() {
        //
        //					@Override
        //					public CacheAdapter<User, UserView, UserAdapter> createAdapter() {
        //						return new CacheAdapter<User, UserView, UserAdapter>(
        //								new UserAdapter(context), UserListFragment.this);
        //					}
        //
        //					@Override
        //					public void refreshAdapter() {
        //						adapter.refresh(result);
        //					}
        //				});
        //			}
        //		});

    }



    //UI显示区(操作UI，但不存在数据获取或处理代码，也不存在事件监听代码)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










    //Data数据区(存在数据获取或处理代码，但不存在事件监听代码)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Override
    public void initData() {//必须调用

        super.initData();

    }

    @Override
    public void getListAsync(final int pageNum) {
        //实际使用时用这个，需要配置服务器地址		HttpRequest.getUserList(range, pageNum, 0, this);
        HttpRequest.getNewsList(range, pageNum, 0, this);
        //仅测试用<<<<<<<<<<<
        /*new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                System.out.println("这是中文startjson:"+Json.toJSONString(TestUtil.getUserList(pageNum, getCachePageSize())));
                onHttpResponse(0, Json.toJSONString(TestUtil.getUserList(pageNum, getCachePageSize())), null);
            }
        }, 1000);*/
        //仅测试用>>>>>>>>>>>>
    }

    @Override
    public List<News> parseArray(String json) {
        return Json.parseArray(json, News.class);
    }

    @Override
    public Class<News> getCacheClass() {
        return News.class;
    }
    @Override
    public String getCacheGroup() {
        return "range=" + range;
    }
    @Override
    public String getCacheId(News data) {
        return data == null ? null : "" + data.getId();
    }


    //Data数据区(存在数据获取或处理代码，但不存在事件监听代码)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








    //Event事件区(只要存在事件监听代码就是)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


    @Override
    public void initEvent() {//必须调用
        super.initEvent();
        lvBaseList.addHeaderView(headerView());
        lvBaseList.setOnItemClickListener(this);
    }



    //系统自带监听方法 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        position -= lvBaseList.getHeaderViewsCount();
        if (position < 0 || adapter == null || position >= adapter.getCount()) {
            return;
        }

        News news = list.get(position);//adapter.getItem(position);
        if (BaseModel.isCorrect(news)) {//相当于 user != null && user.getId() > 0
            toActivity(UserActivity.createIntent(context, news.getId()));
        }
    }


    //类相关监听<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


    //类相关监听>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



    //系统自带监听方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //Event事件区(只要存在事件监听代码就是)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








    //内部类,尽量少用<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


    //内部类,尽量少用>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


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
        System.out.println("lllllll:"+lvBaseList);
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

    private View headerView(){
        View headerView=LayoutInflater.from(context).inflate(R.layout.lv_header, null);
        Banner headerBanner = (Banner) headerView.findViewById(R.id.header_banner);
        String[] urls = getResources().getStringArray(R.array.url4);
        String[] tips = getResources().getStringArray(R.array.title);
        List list = Arrays.asList(urls);
        images = new ArrayList(list);
        List list1 = Arrays.asList(tips);
        titles= new ArrayList(list1);
        System.out.println("lllllll:"+images);
        System.out.println("lllllll:"+BannerConfig.CIRCLE_INDICATOR_TITLE);
        System.out.println("lllllll:"+headerBanner);
        System.out.println("lllllll:"+lvBaseList);
        //设置banner样式
        headerBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        headerBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        headerBanner.setImages(images);
        //设置banner动画效果
        headerBanner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        headerBanner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        headerBanner.isAutoPlay(true);
        //设置轮播时间
        headerBanner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        headerBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        headerBanner.start();
        headerBanner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });
        return headerBanner;
    }
}