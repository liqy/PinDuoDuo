package bw.pdd.bean.homebean;

/**
 * Created by Administrator on 2017/9/3.
 */

public class HomethreeBean {
    private  String title;
    private Integer pic;

    public HomethreeBean(String title, Integer pic) {
        this.title = title;
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPic() {
        return pic;
    }

    public void setPic(Integer pic) {
        this.pic = pic;
    }
}
