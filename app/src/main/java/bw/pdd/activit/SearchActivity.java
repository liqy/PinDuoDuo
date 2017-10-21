package bw.pdd.activit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bw.pdd.Base.BaseActivity;
import bw.pdd.R;
import bw.pdd.adapter.commonadapter.TagRecyclerViewAdapter;
import bw.pdd.adapter.commonadapter.TwoColumnRecyclerViewAdapter;
import bw.pdd.app.AppConstants;
import bw.pdd.bean.searchbean.SearchGoods;
import bw.pdd.bean.searchbean.SearchHot;
import bw.pdd.bean.searchbean.SearchSuggest;
import bw.pdd.util.HttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * Created by Administrator on 2017/9/5.
 */

public class SearchActivity extends BaseActivity {

    @BindView(R.id.search_delete)
    ImageView search_delete;
    @BindView(R.id.hot_search)
    TextView hot_search;
    @BindView(R.id.search_input_text)
    SearchView searchInputText;
    @BindView(R.id.search_text)
    TextView searchButton;
    @BindView(R.id.search_tag_history)
    RecyclerView searchTagHistory;
    @BindView(R.id.search_tag_hot)
    RecyclerView searchTagHot;
    @BindView(R.id.history_layout)
    LinearLayout historyLayout;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private ArrayList<String> list;
    private ArrayList<String> historylist;
    private ArrayAdapter<String> adapter;
    private TagRecyclerViewAdapter tagRecyclerViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        historyTag();
        listSuggest();
        search();
        sendHttpRequest(AppConstants.base_url + AppConstants.search_hot,1);
    }
    @OnClick(R.id.search_delete)
    public void deleteHistoryTag(){
        historylist.clear();
        tagRecyclerViewAdapter.notifyDataSetChanged();
    }
    private void historyTag() {
        historylist = new ArrayList<>();//历史标签集合
        tagRecyclerViewAdapter = new TagRecyclerViewAdapter(R.layout.tag, historylist);
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager();
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setAlignItems(AlignItems.STRETCH);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        searchTagHistory.setLayoutManager(layoutManager);
        searchTagHistory.setAdapter(tagRecyclerViewAdapter);
        tagRecyclerViewAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                addUrl(historylist.get(position));
                searchInputText.setQuery(historylist.get(position), true);
            }
        });
    }

    private void listSuggest() {
        list = new ArrayList<>();//搜索的提示的条目集合
        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s = list.get(i);
                addUrl(s);
                historylist.add(s);
            }
        });
    }

    public void sendHttpRequest(String url, final int i) {
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //异常处理操作
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //得到服务器返回的具体数据 //切换会主线程更新UI
                String responseData = response.body().string();
                Gson gson = new Gson();
                if (i == 1) {
                    final SearchHot searchHot = gson.fromJson(responseData, SearchHot.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TagRecyclerViewAdapter tagRecyclerViewAdapter = new TagRecyclerViewAdapter(R.layout.tag, searchHot.getItems());
                            FlexboxLayoutManager layoutManager = new FlexboxLayoutManager();
                            layoutManager.setFlexWrap(FlexWrap.WRAP);
                            layoutManager.setFlexDirection(FlexDirection.ROW);
                            layoutManager.setAlignItems(AlignItems.STRETCH);
                            layoutManager.setJustifyContent(JustifyContent.FLEX_START);
                            searchTagHot.setLayoutManager(layoutManager);
                            searchTagHot.setAdapter(tagRecyclerViewAdapter);
                            tagRecyclerViewAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                @Override
                                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                    addUrl(searchHot.getItems().get(position));
                                    searchInputText.setQuery(searchHot.getItems().get(position), true);
                                    historylist.add(searchHot.getItems().get(position));
                                }
                            });
                        }
                    });
                } else if (i == 2) {
                    final SearchGoods searchGoods = gson.fromJson(responseData, SearchGoods.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (searchGoods.getItems().size() < 1) {
                                toast("没有找到相关信息");
                                return;
                            }
                            TwoColumnRecyclerViewAdapter twoColumnRecyclerViewAdapter = new TwoColumnRecyclerViewAdapter(SearchActivity.this, R.layout.new_fragment_recycler_item, searchGoods.getItems());
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(SearchActivity.this, 2);
                            recyclerView.setLayoutManager(gridLayoutManager);
                            recyclerView.setAdapter(twoColumnRecyclerViewAdapter);
                            recyclerView.setVisibility(View.VISIBLE);
                            listView.setVisibility(View.GONE);
                        }
                    });
                }else if (i==3){
                    SearchSuggest searchSuggest = gson.fromJson(responseData, SearchSuggest.class);
                    List<String> suggest = searchSuggest.getSuggest();
                    list.clear();
                    list.addAll(suggest);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                            listView.clearTextFilter();
                        }
                    });
                }

            }
        });
    }

    public void search() {
        searchInputText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (TextUtils.isEmpty(query)) {
                    toast("搜索内容不能为空");
                } else {
                    addUrl(query);
                    historylist.add(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    tagRecyclerViewAdapter.notifyDataSetChanged();
                    listView.setFilterText(newText);
                    list.clear();
                    adapter.notifyDataSetChanged();
                    listView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    searchTagHot.setVisibility(View.VISIBLE);
                    searchTagHistory.setVisibility(View.VISIBLE);
                    historyLayout.setVisibility(View.VISIBLE);
                    hot_search.setVisibility(View.VISIBLE);
                } else {
                    hot_search.setVisibility(View.GONE);
                    searchTagHot.setVisibility(View.GONE);
                    searchTagHistory.setVisibility(View.GONE);
                    historyLayout.setVisibility(View.GONE);
                    sendHttpRequest(AppConstants.base_url+AppConstants.search_suugetion_q+newText+AppConstants.search_suugetion_h,3);
                }
                return false;
            }
        });
    }

    public void addUrl(String s) {
        sendHttpRequest(AppConstants.base_url + AppConstants.search_good_q + s + AppConstants.search_good_h, 2);
    }
}
