package com.cocodecat.vijoz.adplayer.adapter;

import android.content.Context;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.cocodecat.vijoz.adplayer.R;
import com.cocodecat.vijoz.adplayer.bean.MediaItem;
import com.cocodecat.vijoz.adplayer.utils.Utils;

import java.util.List;

public class VideoAdapter extends BaseAdapter {

    private Context mContext;
    private List<MediaItem> mMediaItems;
    private Utils mUtils;

    public VideoAdapter(Context context, List<MediaItem> mediaItems) {
        mContext = context;
        mMediaItems = mediaItems;
        mUtils=new Utils();
    }

    public void refreshData(List<MediaItem> mediaItems){
        if (!mediaItems.isEmpty()&&mediaItems.size()>0) {
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mMediaItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mMediaItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=View.inflate(mContext, R.layout.item_video_pager,null);
            holder.iv_icon=convertView.findViewById(R.id.iv_icon);
            holder.tv_name=convertView.findViewById(R.id.tv_name);
            holder.tv_time=convertView.findViewById(R.id.tv_time);
            holder.tv_size=convertView.findViewById(R.id.tv_size);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        MediaItem mediaItem = mMediaItems.get(position);
        holder.tv_name.setText(mediaItem.getName());
        holder.tv_size.setText(Formatter.formatFileSize(mContext,mediaItem.getSize()));
        holder.tv_time.setText(mUtils.stringForTime((int) mediaItem.getDuration()));
        return convertView;
    }

    static class ViewHolder{
        private ImageView iv_icon;
        private TextView tv_name;
        private TextView tv_time;
        private TextView tv_size;
    }
}