package com.cocodecat.vijoz.adplayer.base;

public class Constants {
    // 单例模式
    private static Constants instance;
    public synchronized static Constants instance() {
        if (instance == null) {
            instance = new Constants();
        }
        return instance;
    }
    public String SP_U_Dish = "SP_U_Dish";

    public String SP_TWO = "SP_TWO";

    public String SP_Pic_ChangeTime = "SP_PicChangeTime";



}
