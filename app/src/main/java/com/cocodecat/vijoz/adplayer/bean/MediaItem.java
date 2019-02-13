package com.cocodecat.vijoz.adplayer.bean;

import android.os.Parcel;
import android.os.Parcelable;


public class MediaItem implements Parcelable {

    private String name;

    private long duration;

    private long size;

    private String data;

    private String artist;

    private String desc;

    private String imageUrl;

    private int isVideos;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getIsVideos() {
        return isVideos;
    }

    public void setIsVideos(int isVideos) {
        this.isVideos = isVideos;
    }

    @Override
    public String toString() {
        return "MediaItem{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", size=" + size +
                ", data='" + data + '\'' +
                ", artist='" + artist + '\'' +
                ", desc='" + desc + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeLong(this.duration);
        dest.writeLong(this.size);
        dest.writeString(this.data);
        dest.writeString(this.artist);
        dest.writeString(this.desc);
        dest.writeString(this.imageUrl);
        dest.writeInt(this.isVideos);
    }

    public MediaItem() {}

    protected MediaItem(Parcel in) {
        this.name = in.readString();
        this.duration = in.readLong();
        this.size = in.readLong();
        this.data = in.readString();
        this.artist = in.readString();
        this.desc = in.readString();
        this.imageUrl = in.readString();
        this.isVideos = in.readInt();
    }

    public static final Creator<MediaItem> CREATOR = new Creator<MediaItem>() {
        @Override
        public MediaItem createFromParcel(Parcel source) {
            return new MediaItem(source);
        }

        @Override
        public MediaItem[] newArray(int size) {
            return new MediaItem[size];
        }
    };
}
