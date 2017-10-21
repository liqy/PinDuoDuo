package bw.pdd.fragmen;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import bw.pdd.Base.BaseFragment;
import bw.pdd.R;
import bw.pdd.activit.SearchActivity;
import bw.pdd.adapter.searchrecycleradapter.ItemHeaderDecoration;
import bw.pdd.adapter.searchrecycleradapter.SearchLeftRecyclerViewAdapter;
import bw.pdd.adapter.searchrecycleradapter.SearchRightRecyclerAdapter;
import bw.pdd.app.AppConstants;
import bw.pdd.bean.searchbean.Search_Data;
import bw.pdd.bean.searchbean.Search_Replace;
import bw.pdd.util.HttpUtil;
import bw.pdd.util.onClick.CheckListener;
import bw.pdd.util.onClick.RvListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * Created by zhouwei on 17/4/23.
 */

public class SearchFragment extends BaseFragment {


    @BindView(R.id.public_toolbar_tv)
    TextView publicToolbarTv;
    @BindView(R.id.public_toolbar_image)
    ImageView publicToolbarImage;
    @BindView(R.id.search_left_recycler)
    RecyclerView searchLeftRecycler;
    @BindView(R.id.search_right_recycler)
    RecyclerView searchRightRecycler;
    @BindView(R.id.search_button)
    CardView searchButton;
    private String mFrom;
    private Unbinder bind;

    private SearchLeftRecyclerViewAdapter lrvAdapter;
    private SearchRightRecyclerAdapter rrvAdapter;
    private List<Search_Data> search_data_list;
    private ArrayList<Search_Replace> search_data_replace;
    private int targetPosition = 0;//点击左边某一个具体的item的位置
    private boolean isMoved = false;
    private GridLayoutManager gridLayoutManager;
    private LinearLayoutManager linearLayoutManager;
    private boolean right_move = true;
    private int mIndex = 0;


    public static SearchFragment newInstance(String from) {
        SearchFragment fragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putString("from", from);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFrom = getArguments().getString("from");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment_layout, null);
        bind = ButterKnife.bind(this, view);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sendHttpRequest(AppConstants.base_url + AppConstants.search_two_recycler_url);
        publicToolbarTv.setText("搜索");
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(getActivity().getResources().getColor(R.color.colorBlack));
        }

    }

    /**
     * 网络请求
     *
     * @param url
     */
    public void sendHttpRequest(String url) {
        HttpUtil.sendOkHttpRequest(url, new Callback() {

            @Override
            public void onFailure(final Call call, final IOException e) {
                //异常处理操作
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "网络异常" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                getData(response);
                settingAdapter();
                updateUi();
            }

            /**
             * 数据、适配器、Recycler、监听
             * 绑定RecyclerView
             */
            private void updateUi() {
                if (getActivity() == null) return;

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (searchLeftRecycler == null | searchRightRecycler == null) return;
                        //leftRecyclerView
                        linearLayoutManager = new LinearLayoutManager(getActivity());
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        searchLeftRecycler.setLayoutManager(linearLayoutManager);
                        searchLeftRecycler.setAdapter(lrvAdapter);

                        //RightRecyclerView
                        gridLayoutManager = new GridLayoutManager(getActivity(), 3);
                        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                            @Override
                            public int getSpanSize(int position) {
                                int itemViewType = rrvAdapter.getItemViewType(position);
                                switch (itemViewType) {
                                    case 0:
                                        return 1;
                                    case 1:
                                        return 3;
                                    default:
                                        return 1;
                                }
                            }
                        });
                        searchRightRecycler.setLayoutManager(gridLayoutManager);
                        searchRightRecycler.setAdapter(rrvAdapter);
                        ItemHeaderDecoration itemHeaderDecoration = new ItemHeaderDecoration(getActivity(), search_data_replace);
                        itemHeaderDecoration.setCheckListener(new CheckListener() {
                            @Override
                            public void check(int position, boolean isScroll) {
                                setChecked(position, isScroll);
                            }
                        });
                        searchRightRecycler.addItemDecoration(itemHeaderDecoration);
                        searchRightRecycler.addOnScrollListener(new RecyclerViewListener());
                    }
                });
            }

            /**
             * 设置适配器对象和监听
             */
            private void settingAdapter() {
                //创建leftRecyclerView适配器对象
                lrvAdapter = new SearchLeftRecyclerViewAdapter(getActivity(), search_data_list, new RvListener() {
                    @Override
                    public void onItemClick(int id, int position) {
                        isMoved = true;
                        setChecked(position, true);
                        targetPosition = position;
                    }
                });
                //创建RightRecyclerView适配器对象
                rrvAdapter = new SearchRightRecyclerAdapter(search_data_replace, getActivity(), new RvListener() {
                    @Override
                    public void onItemClick(int id, int position) {
                        toast(search_data_replace.get(position).opt_name + "___" + position);
                    }
                });
            }

            /**
             *  解析json 获取两个RecyclerView的数据集合
             * @param response
             * @throws IOException
             */
            private void getData(Response response) throws IOException {
                String responseData = response.body().string();
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<Search_Data>>() {
                }.getType();
                search_data_list = gson.fromJson(responseData, type);
                //search_Data集合转换成Search_Replace集合
                search_data_replace = new ArrayList<Search_Replace>();
                for (int i = 0; i < search_data_list.size(); i++) {
                    Search_Data search_data = search_data_list.get(i);
                    List<Search_Data.ChildrenBean> children = search_data.getChildren();
                    Search_Replace search_replace = SearchDataToSearchReplace(search_data);
                    search_replace.setTag(String.valueOf(i));
                    search_data_replace.add(search_replace);
                    for (int j = 0; j < children.size(); j++) {
                        Search_Data.ChildrenBean childrenBean = children.get(j);
                        Search_Replace search_replace1 = SearchDataToSearchReplace(childrenBean);
                        search_replace1.setTag(String.valueOf(i));
                        search_data_replace.add(search_replace1);
                    }
                }
            }

        });
    }

    /**
     * @param position 选中的左边的下标
     * @param isLeft   根据第二个参数操作
     *                 如果是true 就更新左边的RecyclerView，滑动右边的RecyclerView
     *                 如果是false 就是更新左边的RecyclerView 改变背景颜色
     */
    private void setChecked(int position, boolean isLeft) {
        Log.d("p-------->", String.valueOf(position));
        if (isLeft) {
            lrvAdapter.setCheckedPosition(position);//刷新left布局的颜色和选中的位置
            int count = 0;//此处的位置需要根据每个分类的集合来进行计算
            for (int i = 0; i < position; i++) {
                count += search_data_list.get(i).getChildren().size();
            }
            count += position;
            mIndex = count;
            searchRightRecycler.stopScroll();
            smoothMoveToPosition(count); //右边的进行滑动的方法
            ItemHeaderDecoration.setCurrentTag(String.valueOf(position));
        } else {
            //点击的是右边的就刷新左边的
            if (isMoved) {
                isMoved = false;
            } else
                lrvAdapter.setCheckedPosition(position);
            ItemHeaderDecoration.setCurrentTag(String.valueOf(position));
        }
        //然后滑动到中间
        moveToCenter(position);
    }

    /**
     * 右面滑动，左边也往上滑动
     * 移动左面的 Recycler居中
     * 假如条目特别多的话就有效果
     *
     * @param position
     */
    private void moveToCenter(int position) {
        //将点击的position转换为当前屏幕上可见的item的位置以便于计算距离顶部的高度，从而进行移动居中
        View childAt = searchLeftRecycler.getChildAt(position - linearLayoutManager.findFirstVisibleItemPosition());
        if (childAt != null) {
            int y = (childAt.getTop() - searchLeftRecycler.getHeight() / 2);
            searchLeftRecycler.smoothScrollBy(0, y);
        }
    }

    /**
     * 右边的RecyclerView
     * 滑动的方法
     *
     * @param n 判断3总类型
     */
    private void smoothMoveToPosition(int n) {
        int firstItem = gridLayoutManager.findFirstVisibleItemPosition();
        int lastItem = gridLayoutManager.findLastVisibleItemPosition();
        Log.d("first--->", String.valueOf(firstItem));
        Log.d("last--->", String.valueOf(lastItem));
        if (n <= firstItem) {
            searchRightRecycler.scrollToPosition(n);
        } else if (n <= lastItem) {
            Log.d("pos---->", String.valueOf(n) + "VS" + firstItem);
            int top = searchRightRecycler.getChildAt(n - firstItem).getTop();
            Log.d("top---->", String.valueOf(top));
            searchRightRecycler.scrollBy(0, top);
        } else {
            searchRightRecycler.scrollToPosition(n);
            right_move = true;
        }
    }

    /**
     * 对象的转换方法，用于多条目展示
     *
     * @param childrenBean
     * @return Search_Replace
     */
    private Search_Replace SearchDataToSearchReplace(Search_Data.ChildrenBean childrenBean) {
        Search_Replace search_replace = new Search_Replace(childrenBean.getIs_highlight(), childrenBean.getId(),
                childrenBean.getOpt_id_2(), childrenBean.getOpt_id_3(), childrenBean.getMan_priority(),
                childrenBean.getOpt_id(), childrenBean.getOpt_name(), childrenBean.getOpt_desc(),
                childrenBean.getOpt_id_1(), childrenBean.getPriority(), childrenBean.getImage_url());
        return search_replace;
    }

    /**
     * 对象的转换方法，用于多条目展示
     *
     * @param search_data
     * @return Search_Replace
     */
    private Search_Replace SearchDataToSearchReplace(Search_Data search_data) {
        int id = search_data.getId();
        String image_url = search_data.getImage_url();
        int is_highlight = search_data.getIs_highlight();
        int man_priority = search_data.getMan_priority();
        String opt_desc = search_data.getOpt_desc();
        int opt_id = search_data.getOpt_id();
        int opt_id_1 = search_data.getOpt_id_1();
        int opt_id_2 = search_data.getOpt_id_2();
        int opt_id_3 = search_data.getOpt_id_3();
        String opt_name = search_data.getOpt_name();
        int priority = search_data.getPriority();
        String selected_url = search_data.getSelected_url();
        String unselected_url = search_data.getUnselected_url();
        Search_Replace search_replace = new Search_Replace(is_highlight, id, opt_id_2, opt_id_3,
                man_priority, selected_url, opt_id, unselected_url, opt_name,
                opt_desc, opt_id_1, priority, image_url);
        return search_replace;
    }

    /**
     * 销毁释放内存资源
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (bind != null) {
            bind.unbind();
        }
    }

    private class RecyclerViewListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            //滑动完成之后最后复位
            if (right_move && newState == RecyclerView.SCROLL_STATE_IDLE) {
                right_move = false;
                int n = mIndex - gridLayoutManager.findFirstVisibleItemPosition();
                Log.d("n---->", String.valueOf(n));
                if (0 <= n && n < searchRightRecycler.getChildCount()) {
                    int top = searchRightRecycler.getChildAt(n).getTop();
                    Log.d("top--->", String.valueOf(top));
                    searchRightRecycler.smoothScrollBy(0, top);
                }
            }
        }


        /**
         * 当前的方法会一直执行
         * 要判断第一个可见条目是否改变，
         * 如果改变了就添加到list里面
         * 用来记录那个是选中状态
         */
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (right_move) {   //滑动的三种情况
                right_move = false;
                int n = mIndex - gridLayoutManager.findFirstVisibleItemPosition();
                if (0 <= n && n < searchRightRecycler.getChildCount()) {
                    int top = searchRightRecycler.getChildAt(n).getTop();
                    searchRightRecycler.scrollBy(0, top);
                }
            }
        }
    }
}
