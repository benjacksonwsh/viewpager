package com.example.administrator.testviewpager;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.zip.Inflater;


public class MainActivity extends ActionBarActivity implements View.OnTouchListener {
    private ArrayList<View> mViewList;
    private ArrayList<View> mViewList2;
    ViewFlipper viewFlip;

    float touchDownX;
    float touchUpX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager1 = (ViewPager) findViewById(R.id.viewPager1);

        viewPager1.setAnimation( AnimationUtils.loadAnimation(this, R.anim.left_in) );
        mViewList = new ArrayList<View>();
        mViewList.add( getLayoutInflater().inflate( R.layout.view, null ) );
        mViewList.add( getLayoutInflater().inflate( R.layout.view, null ) );
        mViewList.add( getLayoutInflater().inflate( R.layout.view, null ) );
        mViewList.add( getLayoutInflater().inflate( R.layout.view, null ) );

        viewPager1.setAdapter( new PagerAdapter() {
            @Override
            public int getCount() {
                return mViewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(mViewList.get(position));

            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(mViewList.get(position));
                return mViewList.get(position);
            }
        });


        mViewList2 = new ArrayList<View>();
        mViewList2.add( getLayoutInflater().inflate( R.layout.view, null ) );
        mViewList2.add( getLayoutInflater().inflate( R.layout.view, null ) );
        mViewList2.add( getLayoutInflater().inflate( R.layout.view, null ) );
        mViewList2.add( getLayoutInflater().inflate( R.layout.view, null ) );
        ViewPager viewPager2 = (ViewPager) findViewById(R.id.viewPager2);
        viewPager2.setAdapter( new PagerAdapter() {
            @Override
            public int getCount() {
                return mViewList2.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(mViewList2.get(position));

            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(mViewList2.get(position));
                return mViewList2.get(position);
            }
        });

        viewFlip = (ViewFlipper) findViewById( R.id.body_flipper );
        viewFlip.setOnTouchListener(this);

        HorizontalListView hView = (HorizontalListView) findViewById( R.id.h_scroll_view );
        hView.setAdapter( new ImageViewerAdapter(this, 5) );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // 取得左右滑动时手指按下的X坐标
            touchDownX = event.getX();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            // 取得左右滑动时手指松开的X坐标
            touchUpX = event.getX();
            // 从左往右，看前一个View
            if (touchUpX - touchDownX > 100) {
                // 显示上一屏动画
                viewFlip.setInAnimation(AnimationUtils.loadAnimation(this,
                        R.anim.right_in));
                viewFlip.setOutAnimation(AnimationUtils.loadAnimation(this,
                        R.anim.right_out));
                // 显示上一屏的View
                viewFlip.showPrevious();
                // 从右往左，看后一个View
            } else if (touchDownX - touchUpX > 100) {
                //显示下一屏的动画
                viewFlip.setInAnimation(AnimationUtils.loadAnimation(this,
                        R.anim.left_in));
                viewFlip.setOutAnimation(AnimationUtils.loadAnimation(this,
                        R.anim.left_out));
                // 显示下一屏的View
                viewFlip.showNext();
            }
            return true;
        }
        return false;
    }
}
