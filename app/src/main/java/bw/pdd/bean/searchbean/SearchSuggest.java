package bw.pdd.bean.searchbean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/6.
 */

public class SearchSuggest {

    /**
     * query : 閮�
     * server_time : 1504658203
     * suggest : ["閮藉競涓戒汉鍐呰。","閮藉競涓戒汉","閮藉競涓戒汉鏂囪兏","閮藉競鐗у満","閮藉競涓戒汉鍐呰￥","閮藉競涓戒汉鐫¤。","閮戒箰鑿犺悵","閮藉競鐗у満鐖藉彛鍚墖","閮戒箰","閮藉競涓戒汉鑳哥僵"]
     */

    private String query;
    private int server_time;
    private List<String> suggest;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getServer_time() {
        return server_time;
    }

    public void setServer_time(int server_time) {
        this.server_time = server_time;
    }

    public List<String> getSuggest() {
        return suggest;
    }

    public void setSuggest(List<String> suggest) {
        this.suggest = suggest;
    }
}
