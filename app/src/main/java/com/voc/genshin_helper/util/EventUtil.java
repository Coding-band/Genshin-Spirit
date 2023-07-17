package com.voc.genshin_helper.util;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */


import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.voc.genshin_helper.R;

public class EventUtil {

    public EventItem[] getCurrEventList(){
        return VER_DEMO;
    }
    public EventItem[] VER_DEMO = {
        new EventItem(R.string.none_info,1689416493000L, 1699416494000L ),
        new EventItem(R.string.none_info,1689417493000L, 1692416494000L ),
        new EventItem(R.string.none_info,1689419493000L, 1694416494000L ),
        new EventItem(R.string.none_info,1689426493000L, 1697416494000L )
    };


    public void init(View view, Context context, Activity activity){

    }






    public static class EventItem{
        int eventNameRID;
        long eventBeginUnix;
        long eventEndUnix;

        public EventItem(int eventNameRID, long eventBeginUnix, long eventEndUnix) {
            this.eventNameRID = eventNameRID;
            this.eventBeginUnix = eventBeginUnix;
            this.eventEndUnix = eventEndUnix;
        }

        public int getEventNameRID() {
            return eventNameRID;
        }

        public void setEventNameRID(int eventNameRID) {
            this.eventNameRID = eventNameRID;
        }

        public long getEventBeginUnix() {
            return eventBeginUnix;
        }

        public void setEventBeginUnix(long eventBeginUnix) {
            this.eventBeginUnix = eventBeginUnix;
        }

        public long getEventEndUnix() {
            return eventEndUnix;
        }

        public void setEventEndUnix(long eventEndUnix) {
            this.eventEndUnix = eventEndUnix;
        }
    }
    
}
