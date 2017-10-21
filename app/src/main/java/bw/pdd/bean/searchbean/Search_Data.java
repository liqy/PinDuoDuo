package bw.pdd.bean.searchbean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/1.
 */

public class Search_Data {


    /**
     * is_highlight : 0
     * id : 14
     * children : [{"is_highlight":1,"id":45,"man_priority":0,"opt_id_3":0,"opt_id":45,"opt_id_2":45,"opt_name":"女装","opt_desc":"服饰箱包二级","opt_id_1":14,"priority":1,"image_url":"http://omsproductionimg.yangkeduo.com/images/2017-07-06/002216dbb20fb111c63c7b7d4af5319f.png"},{"is_highlight":0,"id":1689,"man_priority":2,"opt_id_3":0,"opt_id":1689,"opt_id_2":1689,"opt_name":"秋装","opt_desc":"服饰箱包二级","opt_id_1":14,"priority":2,"image_url":"http://omsproductionimg.yangkeduo.com/images/2017-08-28/531c4112d10c518a9e5241ba7171224b.jpeg"},{"is_highlight":0,"id":48,"man_priority":0,"opt_id_3":0,"opt_id":48,"opt_id_2":48,"opt_name":"女鞋","opt_desc":"服饰箱包二级","opt_id_1":14,"priority":3,"image_url":"http://omsproductionimg.yangkeduo.com/images/2017-08-23/9fac1a5eee380360d60dca8c893f7159.png"},{"is_highlight":0,"id":557,"man_priority":0,"opt_id_3":0,"opt_id":557,"opt_id_2":557,"opt_name":"连衣裙","opt_desc":"服饰箱包二级","opt_id_1":14,"priority":3,"image_url":"http://omsproductionimg.yangkeduo.com/images/2017-08-25/21f2c7d7a557b8b977fdb33ad9a13c7e.png"},{"is_highlight":0,"id":52,"man_priority":0,"opt_id_3":0,"opt_id":52,"opt_id_2":52,"opt_name":"女裤","opt_desc":"服饰箱包二级","opt_id_1":14,"priority":4,"image_url":"http://omsproductionimg.yangkeduo.com/images/2017-08-25/95e9f63138325ade3ed893d535c34c68.png"},{"is_highlight":0,"id":307,"man_priority":0,"opt_id_3":0,"opt_id":307,"opt_id_2":307,"opt_name":"裙子","opt_desc":"服饰箱包二级","opt_id_1":14,"priority":5,"image_url":"http://omsproductionimg.yangkeduo.com/images/2017-08-23/c4b3106bd83311c845f60eeaecc4b496.png"},{"is_highlight":0,"id":1688,"man_priority":1,"opt_id_3":0,"opt_id":1688,"opt_id_2":1688,"opt_name":"单鞋","opt_desc":"服饰箱包二级","opt_id_1":14,"priority":6,"image_url":"http://omsproductionimg.yangkeduo.com/images/2017-08-11/1cea2f6b39d0b4bbd7b152c8a962ec4f.jpeg"},{"is_highlight":0,"id":305,"man_priority":0,"opt_id_3":0,"opt_id":305,"opt_id_2":305,"opt_name":"套装","opt_desc":"服饰箱包二级","opt_id_1":14,"priority":7,"image_url":"http://omsproductionimg.yangkeduo.com/images/2017-08-23/01c58d2c5f94c556bbe9d6d0b82735e6.png"},{"is_highlight":0,"id":487,"man_priority":0,"opt_id_3":0,"opt_id":487,"opt_id_2":487,"opt_name":"T恤","opt_desc":"服饰箱包二级","opt_id_1":14,"priority":8,"image_url":"http://omsproductionimg.yangkeduo.com/images/2017-08-25/7ee59a14f7b6f40e2c2fdd980114b71f.png"},{"is_highlight":0,"id":485,"man_priority":0,"opt_id_3":0,"opt_id":485,"opt_id_2":485,"opt_name":"大码女装","opt_desc":"服饰箱包二级","opt_id_1":14,"priority":9,"image_url":"http://omsproductionimg.yangkeduo.com/images/2017-07-06/7c25deaf9dc6722d1dd3e7d7e78d19d5.png"},{"is_highlight":0,"id":51,"man_priority":0,"opt_id_3":0,"opt_id":51,"opt_id_2":51,"opt_name":"箱包","opt_desc":"服饰箱包二级","opt_id_1":14,"priority":10,"image_url":"http://omsproductionimg.yangkeduo.com/images/2017-08-23/bee5b5ee4611ce722924ef2346774a6a.png"},{"is_highlight":0,"id":610,"man_priority":0,"opt_id_3":0,"opt_id":610,"opt_id_2":610,"opt_name":"内衣裤袜","opt_desc":"服饰箱包二级","opt_id_1":14,"priority":11,"image_url":"http://omsproductionimg.yangkeduo.com/images/2017-07-06/772ce30f0fc3d1994aa8c77f692f36ce.png"}]
     * opt_id_2 : 0
     * opt_id_3 : 0
     * man_priority : 4
     * selected_url : http://omsproductionimg.yangkeduo.com/images/2017-06-01/834a8cc3c7ae5f87aa4f23c310352412.png
     * opt_id : 14
     * unselected_url : http://omsproductionimg.yangkeduo.com/images/2017-06-04/da66477f08f39bbb5ae6d245176e3799.png
     * opt_name : 服饰
     * opt_desc : 一级
     * opt_id_1 : 14
     * priority : 1
     * image_url : http://omsproductionimg.yangkeduo.com/goods/5635bbe762/730/100/6bce5c6db8e6bf1a41b2544d453a255c.jpg
     */

    private int is_highlight;
    private int id;
    private int opt_id_2;
    private int opt_id_3;
    private int man_priority;
    private String selected_url;
    private int opt_id;
    private String unselected_url;
    private String opt_name;
    private String opt_desc;
    private int opt_id_1;
    private int priority;
    private String image_url;
    private List<ChildrenBean> children;

    public int getIs_highlight() {
        return is_highlight;
    }

    public void setIs_highlight(int is_highlight) {
        this.is_highlight = is_highlight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOpt_id_2() {
        return opt_id_2;
    }

    public void setOpt_id_2(int opt_id_2) {
        this.opt_id_2 = opt_id_2;
    }

    public int getOpt_id_3() {
        return opt_id_3;
    }

    public void setOpt_id_3(int opt_id_3) {
        this.opt_id_3 = opt_id_3;
    }

    public int getMan_priority() {
        return man_priority;
    }

    public void setMan_priority(int man_priority) {
        this.man_priority = man_priority;
    }

    public String getSelected_url() {
        return selected_url;
    }

    public void setSelected_url(String selected_url) {
        this.selected_url = selected_url;
    }

    public int getOpt_id() {
        return opt_id;
    }

    public void setOpt_id(int opt_id) {
        this.opt_id = opt_id;
    }

    public String getUnselected_url() {
        return unselected_url;
    }

    public void setUnselected_url(String unselected_url) {
        this.unselected_url = unselected_url;
    }

    public String getOpt_name() {
        return opt_name;
    }

    public void setOpt_name(String opt_name) {
        this.opt_name = opt_name;
    }

    public String getOpt_desc() {
        return opt_desc;
    }

    public void setOpt_desc(String opt_desc) {
        this.opt_desc = opt_desc;
    }

    public int getOpt_id_1() {
        return opt_id_1;
    }

    public void setOpt_id_1(int opt_id_1) {
        this.opt_id_1 = opt_id_1;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    public static class ChildrenBean {
        /**
         * is_highlight : 1
         * id : 45
         * man_priority : 0
         * opt_id_3 : 0
         * opt_id : 45
         * opt_id_2 : 45
         * opt_name : 女装
         * opt_desc : 服饰箱包二级
         * opt_id_1 : 14
         * priority : 1
         * image_url : http://omsproductionimg.yangkeduo.com/images/2017-07-06/002216dbb20fb111c63c7b7d4af5319f.png
         */

        private int is_highlight;
        private int id;
        private int man_priority;
        private int opt_id_3;
        private int opt_id;
        private int opt_id_2;
        private String opt_name;
        private String opt_desc;
        private int opt_id_1;
        private int priority;
        private String image_url;

        public int getIs_highlight() {
            return is_highlight;
        }

        public void setIs_highlight(int is_highlight) {
            this.is_highlight = is_highlight;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMan_priority() {
            return man_priority;
        }

        public void setMan_priority(int man_priority) {
            this.man_priority = man_priority;
        }

        public int getOpt_id_3() {
            return opt_id_3;
        }

        public void setOpt_id_3(int opt_id_3) {
            this.opt_id_3 = opt_id_3;
        }

        public int getOpt_id() {
            return opt_id;
        }

        public void setOpt_id(int opt_id) {
            this.opt_id = opt_id;
        }

        public int getOpt_id_2() {
            return opt_id_2;
        }

        public void setOpt_id_2(int opt_id_2) {
            this.opt_id_2 = opt_id_2;
        }

        public String getOpt_name() {
            return opt_name;
        }

        public void setOpt_name(String opt_name) {
            this.opt_name = opt_name;
        }

        public String getOpt_desc() {
            return opt_desc;
        }

        public void setOpt_desc(String opt_desc) {
            this.opt_desc = opt_desc;
        }

        public int getOpt_id_1() {
            return opt_id_1;
        }

        public void setOpt_id_1(int opt_id_1) {
            this.opt_id_1 = opt_id_1;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }
    }
}
