package bw.pdd.bean.homebean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 */

public class Home_tab_nine {

    /**
     * name : 9块9特卖
     * list : [{"tab_id":0,"tab":" 9.9专场","column_num":2,"share_image":"","subject":" 9.9专场","desc":"全场超低价，每天精选百款超值折扣商品，特卖1折起，九块九包邮，省钱好帮手！"},{"tab_id":1,"tab":"居家","column_num":2,"share_image":"","subject":"居家","desc":"9块9特卖 家居日用专场"},{"tab_id":2,"tab":"服饰","column_num":2,"share_image":"","subject":"服饰","desc":"9块9特卖 服饰鞋包专场"},{"tab_id":4,"tab":"美食","column_num":2,"share_image":"","subject":"美食","desc":"9块9特卖 零食水果 专场"},{"tab_id":3,"tab":"母婴","column_num":2,"share_image":"","subject":"母婴","desc":"母婴玩具"},{"tab_id":6,"tab":"电器","column_num":2,"share_image":"","subject":"电器","desc":"电器"},{"tab_id":5,"tab":"美妆饰品","column_num":2,"share_image":"","subject":"美妆饰品","desc":"美妆饰品"}]
     * server_time : 1504612367
     */

    private String name;
    private int server_time;
    private List<ListBean> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getServer_time() {
        return server_time;
    }

    public void setServer_time(int server_time) {
        this.server_time = server_time;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * tab_id : 0
         * tab :  9.9专场
         * column_num : 2
         * share_image :
         * subject :  9.9专场
         * desc : 全场超低价，每天精选百款超值折扣商品，特卖1折起，九块九包邮，省钱好帮手！
         */

        private int tab_id;
        private String tab;
        private int column_num;
        private String share_image;
        private String subject;
        private String desc;

        public int getTab_id() {
            return tab_id;
        }

        public void setTab_id(int tab_id) {
            this.tab_id = tab_id;
        }

        public String getTab() {
            return tab;
        }

        public void setTab(String tab) {
            this.tab = tab;
        }

        public int getColumn_num() {
            return column_num;
        }

        public void setColumn_num(int column_num) {
            this.column_num = column_num;
        }

        public String getShare_image() {
            return share_image;
        }

        public void setShare_image(String share_image) {
            this.share_image = share_image;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
