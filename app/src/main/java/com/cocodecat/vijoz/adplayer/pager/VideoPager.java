package com.cocodecat.vijoz.adplayer.pager;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cocodecat.vijoz.adplayer.R;
import com.cocodecat.vijoz.adplayer.activity.MainActivity;
import com.cocodecat.vijoz.adplayer.activity.SystemVideoPlayerActivity;
import com.cocodecat.vijoz.adplayer.adapter.VideoAdapter;
import com.cocodecat.vijoz.adplayer.base.BasePager;
import com.cocodecat.vijoz.adplayer.bean.MediaItem;
import com.cocodecat.vijoz.adplayer.utils.FileUtils;
import com.cocodecat.vijoz.adplayer.utils.LogUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VideoPager extends BasePager implements AdapterView.OnItemClickListener {

    private static final int DATA_LOADING_FINISH = 0x01;
    private ListView mLvVideo;
    private TextView mTvNoVideo;
    private ProgressBar mPbLoading;

    private List<MediaItem> mAllItems;
    private List<MediaItem> mMediaItems;
    private List<MediaItem> mPicItems;

    private VideoAdapter mVideoAdapter;
    private List list = new ArrayList();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DATA_LOADING_FINISH:
                    if (!mAllItems.isEmpty() && mAllItems.size() > 0) {
                        mTvNoVideo.setVisibility(View.GONE);
                        mVideoAdapter.refreshData(mAllItems);
                    } else {
                        mTvNoVideo.setVisibility(View.VISIBLE);
                    }
                    mPbLoading.setVisibility(View.GONE);

//                  //从第0个开始播放视频
                    //SystemVideoPlayerActivity.startSystemVideoPlayerActivity(mContext, (ArrayList<MediaItem>) mAllItems, null,0);

                    SystemVideoPlayerActivity.setImageListDatas(list);
                    break;
            }
        }
    };

    public VideoPager(MainActivity context) {
        super(context);
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.video_pager, null);
        mLvVideo = view.findViewById(R.id.lv_video);
        mTvNoVideo = view.findViewById(R.id.tv_no_video);
        mPbLoading = view.findViewById(R.id.pb_loading);
        mTvNoVideo.setText("没有视频文件...");
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        mAllItems = new ArrayList<>();
        mMediaItems = new ArrayList<>();
        mPicItems = new ArrayList<>();
        mVideoAdapter = new VideoAdapter(mContext, mAllItems);
        mLvVideo.setAdapter(mVideoAdapter);
        mLvVideo.setOnItemClickListener(this);
        getDataFromLocal();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SystemVideoPlayerActivity.startSystemVideoPlayerActivity(mContext, (ArrayList<MediaItem>) mAllItems,(ArrayList<MediaItem>) mMediaItems,(ArrayList<MediaItem>) mPicItems, null, position);
    }

    public void getDataFromLocal() {
        mPbLoading.setVisibility(View.VISIBLE);
        new Thread() {
            @Override
            public void run() {
                super.run();
                //读取A文件夹中的数据
                String APath = FileUtils.AFilesPath(mContext);
//                String BPath = FileUtils.BFilesPath(mContext);

                File path = new File(APath);
                File[] files = path.listFiles();// 读取文件夹下文件

                for (File file : files) {
                    if (!file.isDirectory()) {
                        //把文件名转化为小写
                        String fileName = file.getName();
                        fileName = fileName.toLowerCase();
                        LogUtil.i("vijoz打印fileName：" + fileName);
                        if (fileName.endsWith(".mp4")) {//格式为mp4文件
                            MediaPlayer mediaPlayer = getVideoMediaPlayer(file);
                            MediaItem mediaItem = new MediaItem();
                            mediaItem.setName(file.getName());
                            if (mediaPlayer != null) {//如果这个文件是mp4文件则加载这个文件到列表中
                                mediaItem.setDuration(mediaPlayer.getDuration());
                                mediaItem.setSize(file.length());
                                mediaItem.setData(file.getAbsolutePath());
                                mediaItem.setIsVideos(1);
                                mAllItems.add(mediaItem);
                                mMediaItems.add(mediaItem);
                            }
                        } else if (fileName.endsWith(".jpg") || fileName.endsWith(".bmp") || fileName.endsWith(".png")) {
                            if (!fileName.contains("._")){
                                MediaItem mediaItem = new MediaItem();
                                mediaItem.setName(file.getName());
                                mediaItem.setDuration(0);
                                mediaItem.setSize(file.length());
                                mediaItem.setData(file.getAbsolutePath());
                                mediaItem.setIsVideos(0);
                                mAllItems.add(mediaItem);
                                mPicItems.add(mediaItem);
                                LogUtil.i("vijoz打印JPG：" + fileName);
                            }
                        }
                        list.add(file);
                    }
                }
                Message.obtain(mHandler, DATA_LOADING_FINISH).sendToTarget();
            }
        }.start();
    }


    private MediaPlayer getVideoMediaPlayer(File file) {
        try {
            return MediaPlayer.create(mContext, Uri.fromFile(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
