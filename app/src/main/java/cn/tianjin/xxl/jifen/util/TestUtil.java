package cn.tianjin.xxl.jifen.util;

import java.util.ArrayList;
import java.util.List;

import cn.tianjin.xxl.jifen.model.User;

/**仅测试用，图片地址可能会失效
 * @author Lemon
 */

public class TestUtil {

    public static List<User> getUserList() {
        return getUserList(0);
    }
    /**
     * @param pageNum 页码
     * @return
     */
    public static List<User> getUserList(int pageNum) {
        return getUserList(pageNum, 10);
    }
    /**
     * @param pageNum 页码
     * @param maxLength 最大一页数量
     * @return
     */
    public static List<User> getUserList(int pageNum, int maxLength) {
        List<User> list = new ArrayList<User>();
        long userId;
        User user;
        int length = (maxLength <= 0 || maxLength > URLS.length ? URLS.length : maxLength);
        int index;
        for (int i = 0; i < length ; i++) {
            userId = i + pageNum*length + 1;
            index = i + pageNum*length;
            while (index >= URLS.length) {
                index -= URLS.length;
            }
            if (index < 0) {
                index = 0;
            }

            user = new User();
            user.setId(userId);
            user.setSex(i%3);
            user.setHead(URLS[index]);
            user.setName("Name" + userId);
            user.setPhone(String.valueOf(1 + (i + userId)*(pageNum + userId)));
            user.setStarred(i%2 == 0);
            list.add(user);
        }
        return list;
    }

    /**
     * 图片地址，仅供测试用
     */
    public static String[] URLS = {
            "http://static.oschina.net/uploads/user/1218/2437072_100.jpg?t=1461076033000",
            "http://common.cnblogs.com/images/icon_weibo_24.png",
            "http://static.oschina.net/uploads/user/585/1170143_50.jpg?t=1390226446000",
            "http://static.oschina.net/uploads/user/1174/2348263_50.png?t=1439773471000",
            "http://common.cnblogs.com/images/wechat.png",
            "http://my.oschina.net/img/portrait.gif?t=1451961935000",
            "http://static.oschina.net/uploads/user/998/1997902_50.jpg?t=1407806577000",
            "http://static.oschina.net/uploads/user/1/3064_50.jpg?t=1449566001000",
            "http://static.oschina.net/uploads/user/51/102723_50.jpg?t=1449212504000",
            "http://static.oschina.net/uploads/user/48/96331_50.jpg",
            "http://static.oschina.net/uploads/user/48/97721_50.jpg?t=1451544779000",
            "http://static.oschina.net/uploads/user/48/96289_50.jpg?t=1452751699000",
            "http://static.oschina.net/uploads/user/19/39085_50.jpg",
            "http://static.oschina.net/uploads/user/1332/2664107_50.jpg?t=1457405500000",
            "http://static.oschina.net/uploads/user/1385/2770216_50.jpg?t=1464405516000",
            "http://static.oschina.net/uploads/user/427/855532_50.jpg?t=1435030876000",
            "http://static.oschina.net/uploads/user/629/1258821_50.jpg?t=1378063141000",
            "http://static.oschina.net/uploads/user/1200/2400261_50.png?t=1439638750000",
            "http://my.oschina.net/img/portrait.gif?t=1451961935000"
    };

    public static String getPicture(int index) {
        return index < 0 || index >= URLS.length ? null : URLS[index];
    }
}
