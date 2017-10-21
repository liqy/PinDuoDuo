package bw.pdd.util.onClick;

/**
 * Created by Administrator on 2017/9/2.
 *
 * 回调监听传递给ItemHeaderDecoration
 * 用来更新left RecyclerView的背景 6666、
 */

public interface CheckListener {
    void check(int position,boolean isScroll);
}
