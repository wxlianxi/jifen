package cn.tianjin.xxl.jifen.adapter;

import android.app.Activity;
import android.view.ViewGroup;

import cn.tianjin.xxl.jifen.model.User;
import cn.tianjin.xxl.jifen.view.UserView;
import zuo.biao.library.base.BaseViewAdapter;

/**用户adapter
 * @author Lemon
 */
public class UserAdapter extends BaseViewAdapter<User, UserView> {
    //	private static final String TAG = "UserAdapter";

    public UserAdapter(Activity context) {
        super(context);
    }

    @Override
    public UserView createView(int position, ViewGroup parent) {
        return new UserView(context, resources);
    }
}


