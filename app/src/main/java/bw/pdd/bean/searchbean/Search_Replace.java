package bw.pdd.bean.searchbean;

/**
 * Created by Administrator on 2017/9/1.
 *
 *
 * 多条目修改过的数据
 * 添加一个字段TAG用来记录是哪个Title  0就是服饰  1就是男装
 * 用数字值做还可以确定哪个position
 */

public class Search_Replace {
    public int is_highlight;
    public int id;
    public int opt_id_2;
    public int opt_id_3;
    public int man_priority;
    public String selected_url;
    public int opt_id;
    public String unselected_url;
    public String opt_name;
    public String opt_desc;
    public int opt_id_1;
    public int priority;
    public String image_url;
    public String tag;


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


    public Search_Replace(int is_highlight, int id, int opt_id_2, int opt_id_3, int man_priority, int opt_id, String opt_name, String opt_desc, int opt_id_1, int priority, String image_url) {
        this.is_highlight = is_highlight;
        this.id = id;
        this.opt_id_2 = opt_id_2;
        this.opt_id_3 = opt_id_3;
        this.man_priority = man_priority;
        this.opt_id = opt_id;
        this.opt_name = opt_name;
        this.opt_desc = opt_desc;
        this.opt_id_1 = opt_id_1;
        this.priority = priority;
        this.image_url = image_url;
    }

    public Search_Replace(int is_highlight, int id, int opt_id_2, int opt_id_3, int man_priority, String selected_url, int opt_id, String unselected_url, String opt_name, String opt_desc, int opt_id_1, int priority, String image_url) {
        this.is_highlight = is_highlight;
        this.id = id;
        this.opt_id_2 = opt_id_2;
        this.opt_id_3 = opt_id_3;
        this.man_priority = man_priority;
        this.selected_url = selected_url;
        this.opt_id = opt_id;
        this.unselected_url = unselected_url;
        this.opt_name = opt_name;
        this.opt_desc = opt_desc;
        this.opt_id_1 = opt_id_1;
        this.priority = priority;
        this.image_url = image_url;
    }
}
