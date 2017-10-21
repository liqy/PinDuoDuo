package bw.pdd.util.weiscrollview;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

import bw.pdd.R;

/**
 * Created by asus on 2017/8/31.
 */

public class PullScrollView extends ScrollView {
    //头部图片View
    private View mHeaderView;
    //保存头部View初始化位置。用来找到回弹的位置用
    private Rect mHeadInitRect = new Rect();

    //底部View
    private View mContentView;
    //保存ContentView的初始化位置。跟mHeadInitRect一样，也是回弹用
    private Rect mContentInitRect = new Rect();

    //用户在滑动手指的初始点击位置。用来计算手指的移动距离的
    private Point mTouchPoint = new Point();


    //标识当前view是否移动
    boolean mIsMoving = false;

    //是否禁止控件本身的的移动
    boolean mEnableMoving = false;

    //是否使用layout函数移动布局
    boolean mIsLayout = false;
    /**
     * 阻尼系数,越小阻力就越大.
     */
    private static final float SCROLL_RATIO = 0.5f;

    private int mContentTop, mContentBottom;

    private int mHeaderCurTop, mHeaderCurBottom;

    //用户定义的headview高度
    private int mHeaderHeight = 0;

    public PullScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PullScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        // set scroll mode
        setOverScrollMode(OVER_SCROLL_NEVER);

        if (null != attrs) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PullScrollView);

            if (ta != null) {
                mHeaderHeight = (int) ta.getDimension(R.styleable.PullScrollView_headerHeight, -1);
                ta.recycle();

            }
        }
    }

    /**
     * 使用PullScrollView的用户来设置它要操作的headView
     * @param view
     */
    public void setmHeaderView(View view) {
        mHeaderView = view;
    }

    /**
     * mContentView的初始化
     */
    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            mContentView = getChildAt(0);
        }
        super.onFinishInflate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE: {
                //当前的手指所在的Y轴位置减去点击时的位置。就得到了手指的移动距离
                int deltaY = (int) event.getY() - mTouchPoint.y;
                //假设最大值就是headview的高度
                deltaY = deltaY < 0 ? 0 : (deltaY > mHeaderHeight ? mHeaderHeight : deltaY);
                //deltaY >= getScrollY()
                //当先向上滑一段之后，再下滑，会发现，headView也会跟着下滑；
                // 这明显是不对的，因为headview应该在我们超过原始位置的时候再下滑才是正确的。
                // 所以要判断当前PullScrollView是不是已经滚动。
                // 如果手指的移动距离大于滚动距离，这才说明，我们已经超过了原始位置在下拉。这时候再移动headView;
                if (deltaY > 0 && deltaY >= getScrollY() && mIsLayout) {
                    //headview要比contentview移动的慢，因为我们在计算headerMoveHeight 时多乘以了一个0.5；
                    // 这其实也是为了增加一个效果：让用户觉得下拉比较困难，移动的速度慢
                    float headerMoveHeight = deltaY * 0.5f * SCROLL_RATIO;
                    mHeaderCurTop = (int) (mHeadInitRect.top + headerMoveHeight);
                    mHeaderCurBottom = (int) (mHeadInitRect.bottom + headerMoveHeight);

                    float contentMoveHeight = deltaY * SCROLL_RATIO;
                    mContentTop = (int) (mContentInitRect.top + contentMoveHeight);
                    mContentBottom = (int) (mContentInitRect.bottom + contentMoveHeight);

                    //contentView的上边沿不能低于headView的底边。
                    // 如果低于headView的底边，那就是contentView和headView分离了，这怎么能行。
                    // 不能分离！
                    if (mContentTop <= mHeaderCurBottom) {
                        mHeaderView.layout(mHeadInitRect.left, mHeaderCurTop, mHeadInitRect.right, mHeaderCurBottom);
                        mContentView.layout(mContentInitRect.left, mContentTop, mContentInitRect.right, mContentBottom);
                        mIsMoving = true;
                        mEnableMoving = true;
                    }
                }
            }
            break;
            case MotionEvent.ACTION_UP: {
                //反弹
                if (mIsMoving) {
                    mHeaderView.layout(mHeadInitRect.left, mHeadInitRect.top, mHeadInitRect.right, mHeadInitRect.bottom);
                    TranslateAnimation headAnim = new TranslateAnimation(0, 0, mHeaderCurTop - mHeadInitRect.top, 0);
                    headAnim.setDuration(200);
                    mHeaderView.startAnimation(headAnim);

                    mContentView.layout(mContentInitRect.left, mContentInitRect.top, mContentInitRect.right, mContentInitRect.bottom);
                    TranslateAnimation contentAnim = new TranslateAnimation(0, 0, mContentTop - mContentInitRect.top, 0);
                    contentAnim.setDuration(200);
                    mContentView.startAnimation(contentAnim);
                    mIsMoving = false;
                }
                mEnableMoving = false;
                mIsLayout = false;
            }
            break;
        }
        // 禁止控件本身的滑动.
        //这句厉害,如果mEnableMoving返回TRUE,那么就不会执行super.onTouchEvent(event)
        //只有返回FALSE的时候,才会执行super.onTouchEvent(event)
        //禁止控件本身的滑动，就会让它，本来应有的滑动就不会滑动了，比如向上滚动
        return mEnableMoving || super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //保存原始位置
            mTouchPoint.set((int) event.getX(), (int) event.getY());
            mHeadInitRect.set(mHeaderView.getLeft(), mHeaderView.getTop(), mHeaderView.getRight(), mHeaderView.getBottom());
            mContentInitRect.set(mContentView.getLeft(), mContentView.getTop(), mContentView.getRight(), mContentView.getBottom());
            mIsMoving = false;
            //如果当前不是从初始化位置开始滚动的话，就不让用户拖拽
            if (getScrollY() == 0){
                mIsLayout = true;
            }
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            //如果当前的事件是我们要处理的事件时，比如现在的下拉，这时候，我们就不能让子控件来处理这个事件
            //这里就需要把它截获，不传给子控件，更不能让子控件消费这个事件
            //不然子控件的行为就可能与我们的相冲突
            int deltaY = (int) event.getY() - mTouchPoint.y;//当前的手指所在的Y轴位置减去点击时的位置。就得到了手指的移动距离：
            deltaY = deltaY < 0 ? 0 : (deltaY > mHeaderHeight ? mHeaderHeight : deltaY);//假设最大值就是headview的高度
            if (deltaY > 0 && deltaY >= getScrollY()&& getScrollY() == 0) {
                onTouchEvent(event);
                return true;
            }
        }
        return super.onInterceptTouchEvent(event);
    }
}
