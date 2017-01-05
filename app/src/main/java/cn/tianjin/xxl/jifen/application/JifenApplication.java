package cn.tianjin.xxl.jifen.application;

import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;

import cn.tianjin.xxl.jifen.manager.DataManager;
import cn.tianjin.xxl.jifen.model.User;
import zuo.biao.library.base.BaseApplication;
import zuo.biao.library.util.StringUtil;

/**
 * Created by jack on 2016/12/9.
 */

public class JifenApplication extends BaseApplication {
    private static final String TAG = "DemoApplication";

    private static JifenApplication context;
    public static JifenApplication getInstance() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Fresco.initialize(this);

    }


    /**获取当前用户id
     * @return
     */
    public long getCurrentUserId() {
        currentUser = getCurrentUser();
        Log.d(TAG, "getCurrentUserId  currentUserId = " + (currentUser == null ? "null" : currentUser.getId()));
        return currentUser == null ? 0 : currentUser.getId();
    }


    private static User currentUser = null;
    public User getCurrentUser() {
        if (currentUser == null) {
            currentUser = DataManager.getInstance().getCurrentUser();
        }
        return currentUser;
    }

    public void saveCurrentUser(User user) {
        if (user == null) {
            Log.e(TAG, "saveCurrentUser  currentUser == null >> return;");
            return;
        }
        if (user.getId() <= 0 && StringUtil.isNotEmpty(user.getName(), true) == false) {
            Log.e(TAG, "saveCurrentUser  user.getId() <= 0" +
                    " && StringUtil.isNotEmpty(user.getName(), true) == false >> return;");
            return;
        }

        currentUser = user;
        DataManager.getInstance().saveCurrentUser(currentUser);
    }

    /**判断是否为当前用户
     * @param userId
     * @return
     */
    public boolean isCurrentUser(long userId) {
        return DataManager.getInstance().isCurrentUser(userId);
    }
}
