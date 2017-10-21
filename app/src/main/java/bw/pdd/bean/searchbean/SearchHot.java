package bw.pdd.bean.searchbean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 */

public class SearchHot {

    /**
     * server_time : 1504612389
     * items : ["鍐澶栧濂�","椹《鍨�","姒磋幉楗�","鑻规灉","绛峰瓙濂楄","琛ｅ附鏋�","姘存灉鍒\u20ac","鐨瀛�","鑳屽甫瑁ゅ瑁�","宸у厠鍔�"]
     */

    private int server_time;
    private List<String> items;

    public int getServer_time() {
        return server_time;
    }

    public void setServer_time(int server_time) {
        this.server_time = server_time;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
