package cn.tianjin.xxl.jifen.model;

import zuo.biao.library.base.BaseModel;

/**
 * Created by jack on 2016/12/26.
 */

public class News extends BaseModel {
    private static final long serialVersionUID = 1L;

    public static final int SEX_MAIL = 0;
    public static final int SEX_FEMAIL = 1;
    public static final int SEX_UNKNOW = 2;

    String image; //图片
    String title; //标题
    String date; //日期



    /**默认构造方法，JSON等解析时必须要有
     */
    public News() {
        //default
    }
    public News(long id) {
        this(id, null);
    }
    public News(String title) {
        this(-1, title);
    }
    public News(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean isCorrect() {
        return true;
    }
}
