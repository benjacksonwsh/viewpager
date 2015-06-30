package com.example.administrator.testviewpager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/6/30.
 */
public class ImageViewerAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private int size;
    private int selectIndex = -1;

    public ImageViewerAdapter(Context context, int size){
        this.mContext = context;
        mInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//LayoutInflater.from(mContext);

        this.size = size;
    }
    @Override
    public int getCount() {
        return size;
    }
    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = mInflater.inflate(R.layout.view, null);
        }
        if(position == selectIndex){
            convertView.setSelected(true);
        }else{
            convertView.setSelected(false);
        }
        return convertView;
    }

    public void setSelectIndex(int i){
        selectIndex = i;
    }
}