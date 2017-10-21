package bw.pdd.bean.newbean;

import java.util.List;

/**
 * Created by 郭鑫 on 2017/9/22.
 */

public class NewLeftImage {

    /**
     * server_time : 1506065317
     * mall_collect_goods : []
     * goods_list : [{"normal_price":3080,"thumb_url":"http://omsproductionimg.yangkeduo.com/images/2017-07-30/ef39f2773a7a526a385b1ac7eeeca89b.jpeg","event_type":0,"sales":5556,"goods_id":37058399,"short_name":"做最好的自己全套8册父母不是我的佣人我能管好我自己原来我是最棒的小学生课外阅读书籍校园励志文学小说儿童成长读物7-15岁","logo":"","goods_sn":"1707303613363973","image_url":"http://omsproductionimg.yangkeduo.com/images/2017-07-30/198429bfd70a30155b849ed6e7814b0c.jpeg","price":2980,"market_price":18400,"goods_name":"做最好的自己全套8册父母不是我的佣人我能管好我自己原来我是最棒的小学生课外阅读书籍校园励志文学小说儿童成长读物7-15岁","hd_thumb_url":"http://omsproductionimg.yangkeduo.com/images/2017-07-30/5654bbce99f0592c1af465f2eca45560.jpeg","goods_type":1,"customer_num":2,"country":"","is_app":0,"is_onsale":1},{"normal_price":4900,"thumb_url":"http://omsproductionimg.yangkeduo.com/images/goods/1606288179756992/F2ZhyplhxHkd3F6rgJtzLVz6N2Ae20en.jpg","event_type":0,"sales":64282,"goods_id":276097,"short_name":"甘油白醋嫩白护肤套装","logo":"","goods_sn":"1606288179756992","image_url":"http://omsproductionimg.yangkeduo.com/images/goods/461233/IMXKQicYkhI8cS6eg17YP0XskXRIilwa.jpg","price":2580,"market_price":12800,"goods_name":"【超值两件套】朵拉朵尚马来西亚甘油美容白醋护肤正品套装嫩白补水保湿滋润收缩毛孔爽肤水乳甘油纯套装180ml+180ml","hd_thumb_url":"http://omsproductionimg.yangkeduo.com/images/2017-09-17/fb886ff3d9e7c135cb2f018987493483.jpeg","goods_type":1,"customer_num":2,"country":"","is_app":0,"is_onsale":1}]
     * avatars : ["http://pinduoduoimg.yangkeduo.com/base/avatar/target/u_38.jpg","http://pinduoduoimg.yangkeduo.com/base/avatar/target/u_42.jpg"]
     */

    private int server_time;
    private List<?> mall_collect_goods;
    private List<GoodsListBean> goods_list;
    private List<String> avatars;

    public int getServer_time() {
        return server_time;
    }

    public void setServer_time(int server_time) {
        this.server_time = server_time;
    }

    public List<?> getMall_collect_goods() {
        return mall_collect_goods;
    }

    public void setMall_collect_goods(List<?> mall_collect_goods) {
        this.mall_collect_goods = mall_collect_goods;
    }

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public List<String> getAvatars() {
        return avatars;
    }

    public void setAvatars(List<String> avatars) {
        this.avatars = avatars;
    }

    public static class GoodsListBean {
        /**
         * normal_price : 3080
         * thumb_url : http://omsproductionimg.yangkeduo.com/images/2017-07-30/ef39f2773a7a526a385b1ac7eeeca89b.jpeg
         * event_type : 0
         * sales : 5556
         * goods_id : 37058399
         * short_name : 做最好的自己全套8册父母不是我的佣人我能管好我自己原来我是最棒的小学生课外阅读书籍校园励志文学小说儿童成长读物7-15岁
         * logo :
         * goods_sn : 1707303613363973
         * image_url : http://omsproductionimg.yangkeduo.com/images/2017-07-30/198429bfd70a30155b849ed6e7814b0c.jpeg
         * price : 2980
         * market_price : 18400
         * goods_name : 做最好的自己全套8册父母不是我的佣人我能管好我自己原来我是最棒的小学生课外阅读书籍校园励志文学小说儿童成长读物7-15岁
         * hd_thumb_url : http://omsproductionimg.yangkeduo.com/images/2017-07-30/5654bbce99f0592c1af465f2eca45560.jpeg
         * goods_type : 1
         * customer_num : 2
         * country :
         * is_app : 0
         * is_onsale : 1
         */

        private int normal_price;
        private String thumb_url;
        private int event_type;
        private int sales;
        private int goods_id;
        private String short_name;
        private String logo;
        private String goods_sn;
        private String image_url;
        private int price;
        private int market_price;
        private String goods_name;
        private String hd_thumb_url;
        private int goods_type;
        private int customer_num;
        private String country;
        private int is_app;
        private int is_onsale;

        public int getNormal_price() {
            return normal_price;
        }

        public void setNormal_price(int normal_price) {
            this.normal_price = normal_price;
        }

        public String getThumb_url() {
            return thumb_url;
        }

        public void setThumb_url(String thumb_url) {
            this.thumb_url = thumb_url;
        }

        public int getEvent_type() {
            return event_type;
        }

        public void setEvent_type(int event_type) {
            this.event_type = event_type;
        }

        public int getSales() {
            return sales;
        }

        public void setSales(int sales) {
            this.sales = sales;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getShort_name() {
            return short_name;
        }

        public void setShort_name(String short_name) {
            this.short_name = short_name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getGoods_sn() {
            return goods_sn;
        }

        public void setGoods_sn(String goods_sn) {
            this.goods_sn = goods_sn;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getMarket_price() {
            return market_price;
        }

        public void setMarket_price(int market_price) {
            this.market_price = market_price;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getHd_thumb_url() {
            return hd_thumb_url;
        }

        public void setHd_thumb_url(String hd_thumb_url) {
            this.hd_thumb_url = hd_thumb_url;
        }

        public int getGoods_type() {
            return goods_type;
        }

        public void setGoods_type(int goods_type) {
            this.goods_type = goods_type;
        }

        public int getCustomer_num() {
            return customer_num;
        }

        public void setCustomer_num(int customer_num) {
            this.customer_num = customer_num;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getIs_app() {
            return is_app;
        }

        public void setIs_app(int is_app) {
            this.is_app = is_app;
        }

        public int getIs_onsale() {
            return is_onsale;
        }

        public void setIs_onsale(int is_onsale) {
            this.is_onsale = is_onsale;
        }
    }
}
