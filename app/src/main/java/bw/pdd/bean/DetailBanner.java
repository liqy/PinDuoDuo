package bw.pdd.bean;

import java.util.List;

/**
 * Created by 郭鑫 on 2017/9/20.
 */

public class DetailBanner {

    /**
     * server_time : 1505913168
     * list : [{"goods_id":71901609,"goods_name":"【买2送香水】防水防汗不晕染眼线笔 五角星星眼线笔印章持久眼线液膏初学者硬头软头苾旎大牌正品1.2ml","short_name":"【买2送香水】防水防汗不晕染眼线笔 五角星星眼线笔印章持久眼线液膏初学者硬头软头苾旎大牌正品1.2ml","thumb_url":"http://omsproductionimg.yangkeduo.com/images/2017-09-04/b4835c7312cefe93ffccddd194a7b16c.jpeg","hd_thumb_url":"http://omsproductionimg.yangkeduo.com/images/2017-09-04/741fe1ac82c211b9a3e120de40110c41.jpeg","is_app":0,"event_type":12,"goods_type":1,"customer_num":2,"sales":16,"price":990,"normal_price":1990,"market_price":4900,"min_on_sale_group_price":990,"p_rec":{"t":"default","g":"OLD","bk":"0","m":null}},{"goods_id":74410780,"goods_name":"【送神秘礼物】【2折只抢一天】韩式哑光口红持久保湿不易脱色不掉色不沾杯防水滋润唇膏豆沙姨妈色酒红色唇彩唇膏口红咬唇妆","short_name":"【送神秘礼物】【2折只抢一天】韩式哑光口红持久保湿不易脱色不掉色不沾杯防水滋润唇膏豆沙姨妈色酒红色唇彩唇膏口红咬唇妆","thumb_url":"http://omsproductionimg.yangkeduo.com/images/2017-09-07/090d6324a69980adf938dce57ef3e542.jpeg","hd_thumb_url":"http://omsproductionimg.yangkeduo.com/images/2017-09-07/862190eecefdad2245db3bd73a6ea117.jpeg","is_app":0,"event_type":12,"goods_type":1,"customer_num":2,"sales":522,"price":1690,"normal_price":0,"market_price":3900,"min_on_sale_group_price":1690,"p_rec":{"t":"default","g":"OLD","bk":"0","m":null}},{"goods_id":71680378,"goods_name":"【24小时水润保湿】正品涂抹式水光针乳液 保湿补水美白祛斑收缩毛孔 玻尿酸面膜面部精华滋润紧致提亮祛黄10ml","short_name":"【24小时水润保湿】正品涂抹式水光针乳液 保湿补水美白祛斑收缩毛孔 玻尿酸面膜面部精华滋润紧致提亮祛黄10ml","thumb_url":"http://omsproductionimg.yangkeduo.com/images/2017-09-04/1a0aaa81c02b8906397f5a8dd1c2131a.jpeg","hd_thumb_url":"http://omsproductionimg.yangkeduo.com/images/2017-09-04/4a6b59574ee9d05e62e1c62589c7c842.jpeg","is_app":0,"event_type":0,"goods_type":1,"customer_num":2,"sales":31,"price":590,"normal_price":0,"market_price":3600,"min_on_sale_group_price":590,"p_rec":{"t":"default","g":"OLD","bk":"0","m":null}}]
     */

    private int server_time;
    private List<ListBean> list;

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
         * goods_id : 71901609
         * goods_name : 【买2送香水】防水防汗不晕染眼线笔 五角星星眼线笔印章持久眼线液膏初学者硬头软头苾旎大牌正品1.2ml
         * short_name : 【买2送香水】防水防汗不晕染眼线笔 五角星星眼线笔印章持久眼线液膏初学者硬头软头苾旎大牌正品1.2ml
         * thumb_url : http://omsproductionimg.yangkeduo.com/images/2017-09-04/b4835c7312cefe93ffccddd194a7b16c.jpeg
         * hd_thumb_url : http://omsproductionimg.yangkeduo.com/images/2017-09-04/741fe1ac82c211b9a3e120de40110c41.jpeg
         * is_app : 0
         * event_type : 12
         * goods_type : 1
         * customer_num : 2
         * sales : 16
         * price : 990
         * normal_price : 1990
         * market_price : 4900
         * min_on_sale_group_price : 990
         * p_rec : {"t":"default","g":"OLD","bk":"0","m":null}
         */

        private int goods_id;
        private String goods_name;
        private String short_name;
        private String thumb_url;
        private String hd_thumb_url;
        private int is_app;
        private int event_type;
        private int goods_type;
        private int customer_num;
        private int sales;
        private int price;
        private int normal_price;
        private int market_price;
        private int min_on_sale_group_price;
        private PRecBean p_rec;

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getShort_name() {
            return short_name;
        }

        public void setShort_name(String short_name) {
            this.short_name = short_name;
        }

        public String getThumb_url() {
            return thumb_url;
        }

        public void setThumb_url(String thumb_url) {
            this.thumb_url = thumb_url;
        }

        public String getHd_thumb_url() {
            return hd_thumb_url;
        }

        public void setHd_thumb_url(String hd_thumb_url) {
            this.hd_thumb_url = hd_thumb_url;
        }

        public int getIs_app() {
            return is_app;
        }

        public void setIs_app(int is_app) {
            this.is_app = is_app;
        }

        public int getEvent_type() {
            return event_type;
        }

        public void setEvent_type(int event_type) {
            this.event_type = event_type;
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

        public int getSales() {
            return sales;
        }

        public void setSales(int sales) {
            this.sales = sales;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getNormal_price() {
            return normal_price;
        }

        public void setNormal_price(int normal_price) {
            this.normal_price = normal_price;
        }

        public int getMarket_price() {
            return market_price;
        }

        public void setMarket_price(int market_price) {
            this.market_price = market_price;
        }

        public int getMin_on_sale_group_price() {
            return min_on_sale_group_price;
        }

        public void setMin_on_sale_group_price(int min_on_sale_group_price) {
            this.min_on_sale_group_price = min_on_sale_group_price;
        }

        public PRecBean getP_rec() {
            return p_rec;
        }

        public void setP_rec(PRecBean p_rec) {
            this.p_rec = p_rec;
        }

        public static class PRecBean {
            /**
             * t : default
             * g : OLD
             * bk : 0
             * m : null
             */

            private String t;
            private String g;
            private String bk;
            private Object m;

            public String getT() {
                return t;
            }

            public void setT(String t) {
                this.t = t;
            }

            public String getG() {
                return g;
            }

            public void setG(String g) {
                this.g = g;
            }

            public String getBk() {
                return bk;
            }

            public void setBk(String bk) {
                this.bk = bk;
            }

            public Object getM() {
                return m;
            }

            public void setM(Object m) {
                this.m = m;
            }
        }
    }
}
