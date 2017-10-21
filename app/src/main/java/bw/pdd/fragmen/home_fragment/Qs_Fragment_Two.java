package bw.pdd.fragmen.home_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import bw.pdd.R;
import bw.pdd.activit.DetailActivity;
import bw.pdd.adapter.homeadapter.Homefragemnttwoitem;
import bw.pdd.app.AppConstants;
import bw.pdd.bean.homebean.HomeTwoBean;
import bw.pdd.bean.homebean.TwoBean;
import bw.pdd.bean.searchbean.Search_Data;
import bw.pdd.bean.searchbean.Search_Replace;
import bw.pdd.util.HttpUtil;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/9/1.
 */

public class Qs_Fragment_Two extends Fragment implements Homefragemnttwoitem.OnItemClickLitener {
    @BindView(R.id.qs_twofragment_recycler)
    RecyclerView qsTwofragmentRecycler;
    Unbinder unbinder;
    private List<TwoBean.ChildrenBean> twoBeen;
    private List<HomeTwoBean.GoodsListBean> list;
    private Homefragemnttwoitem homefragemnttwoitem;
    private List<Search_Data> search_data_list;
    private ArrayList<Search_Replace> search_data_replace;
    public static Qs_Fragment_Two getnewInstance(int id) {
        Qs_Fragment_Two fragment = new Qs_Fragment_Two();
        Bundle bundle = new Bundle();
        bundle.putInt("from", id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.qs_twofragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        Bundle arguments = getArguments();
        int from = arguments.getInt("from");
        panduan(from);
        twosendHttpRequest(AppConstants.base_url+AppConstants.search_two_recycler_url);
        list = new ArrayList<>();
        return view;
    }

    //网络请求操作类，OKHttp
    public void twosendHttpRequest(String url) {
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                //异常处理操作
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                //得到服务器返回的具体数据 //切换会主线程更新UI
                getData(response);
                if(getActivity()==null)return;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final GridLayoutManager manager = new GridLayoutManager(getActivity(), 6);
                            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                @Override
                                public int getSpanSize(int position) {
                                    switch (position) {
                                        case 0:
                                            return 6;
                                        default:
                                            return 3;
                                    }
                                }
                            });
                            homefragemnttwoitem = new Homefragemnttwoitem(getActivity(),search_data_replace,list);
                            if(qsTwofragmentRecycler!=null){
                                qsTwofragmentRecycler.setLayoutManager(manager);
                                qsTwofragmentRecycler.setAdapter(homefragemnttwoitem);
                            }
                            homefragemnttwoitem.setOnItemClickLitener(new Homefragemnttwoitem.OnItemClickLitener() {
                                @Override
                                public void onItemClick(View view, int position,int id) {
                                    Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                                    intent.putExtra("GUOXIN",id);
                                    startActivity(intent);
                                }
                            });
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
                Type type = new TypeToken<ArrayList<Search_Data>>(){}.getType();
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
    private Search_Replace SearchDataToSearchReplace(Search_Data.ChildrenBean childrenBean) {
        Search_Replace search_replace = new Search_Replace(childrenBean.getIs_highlight(), childrenBean.getId(),
                childrenBean.getOpt_id_2(), childrenBean.getOpt_id_3(), childrenBean.getMan_priority(),
                childrenBean.getOpt_id(), childrenBean.getOpt_name(), childrenBean.getOpt_desc(),
                childrenBean.getOpt_id_1(), childrenBean.getPriority(), childrenBean.getImage_url());
        return search_replace;
    }
    /**
     * 对象的转换方法，用于多条目展示
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
    //网络请求操作类，OKHttp
    public void sendHttpRequest(String url) {
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                //异常处理操作
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                //得到服务器返回的具体数据 //切换会主线程更新UI
                String responseData = response.body().string();
                HomeTwoBean homeTwoBean = new Gson().fromJson(responseData, HomeTwoBean.class);
                list.addAll(homeTwoBean.getGoods_list());
                Log.e("list", "onResponse: " + list.toString());
                if(getActivity()==null)return;
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final GridLayoutManager manager = new GridLayoutManager(getActivity(), 6);
                            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                @Override
                                public int getSpanSize(int position) {
                                    switch (position) {
                                        case 0:
                                            return 6;
                                        default:
                                            return 3;
                                    }
                                }
                            });
                            homefragemnttwoitem = new Homefragemnttwoitem(getActivity(),search_data_replace,list);
                            if(qsTwofragmentRecycler!=null){
                                qsTwofragmentRecycler.setLayoutManager(manager);
                                qsTwofragmentRecycler.setAdapter(homefragemnttwoitem);
                            }
                            homefragemnttwoitem.setOnItemClickLitener(new Homefragemnttwoitem.OnItemClickLitener() {
                                @Override
                                public void onItemClick(View view, int position,int id) {
                                    Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                                    intent.putExtra("GUOXIN",id);
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                }

            }
        });
    }

    public void panduan(int from) {
        if (from == 1) {
            sendHttpRequest(AppConstants.costume);
        } else if (from == 2) {
            sendHttpRequest(AppConstants.manwear);
        } else if (from == 3) {
            sendHttpRequest(AppConstants.mom);
        } else if (from == 4) {
            sendHttpRequest(AppConstants.home);
        } else if (from == 5) {
            sendHttpRequest(AppConstants.food);
        } else if (from == 6) {
            sendHttpRequest(AppConstants.electricity);
        } else if (from == 7) {
            sendHttpRequest(AppConstants.makeup);
        } else if (from == 8) {
            sendHttpRequest(AppConstants.home1);
        } else if (from == 9) {
            sendHttpRequest(AppConstants.phone);
        } else if (from == 10) {
            sendHttpRequest(AppConstants.exercise);
        } else if (from == 11) {
            sendHttpRequest(AppConstants.fruits);
        }
    }

    @Override
    public void onItemClick(View view, int position,int id) {
        Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("GUOXIN",id);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
