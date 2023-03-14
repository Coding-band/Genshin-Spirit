package com.voc.genshin_helper.util;/*
 * Package com.voc.genshin_helper.util.UpdateLog was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.ScreenSizeUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class ChangeLog {

    public static void show (Context context, Activity activity){

        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
        View view = View.inflate(context, R.layout.fragment_changelog, null);

        BackgroundReload.BackgroundReload(context,activity);

        VersionAdapter mAdapter;
        ArrayList<Version> versionList = jsonJXDate(LoadData("changelog/update.json",context));
        RecyclerView mList;
        LinearLayoutManager linearLayoutManager;
        DividerItemDecoration dividerItemDecoration;

        mList = view.findViewById(R.id.version_list);
        mAdapter = new VersionAdapter(context, versionList);
        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());
        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.addItemDecoration(dividerItemDecoration);
        mList.setAdapter(mAdapter);

        Button ok = view.findViewById(R.id.btn_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(context).getScreenWidth());
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.BOTTOM;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }

    private static String LoadData(String inFile, Context context) {
        String tContents = "";

        try {
            InputStream stream = context.getAssets().open(inFile);

            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            tContents = new String(buffer);
        } catch (IOException e) {
            // Handle exceptions here
        }

        return tContents;

    }

    private static ArrayList<Version> jsonJXDate (String date) {
        ArrayList<Version> versionList = new ArrayList<>();
        if (date != null) {
            try {
                JSONArray array = new JSONArray(date);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);

                    Version version = new Version();
                    version.setVerName(object.getString("verName"));
                    version.setVersion(object.getString("version"));
                    version.setDescription(object.getString("description"));
                    versionList.add(version);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return versionList;
    }


}
