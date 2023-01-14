package com.voc.genshin_helper.kidding;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */
public class TellMeWhy {

     String[] itemName = {
             "anemo",
             "anemodmg",
             "combataction",
             "cryo",
             "cryoapplication",
             "cryodmg",
             "dendro",
             "dendroapplication",
             "dendrodmg",
             "dendrorelatedreactions",
             "durationrounds",
             "electro",
             "electroapplication",
             "electrodmg",
             "electroinfusion",
             "elementalburst",
             "energy",
             "fastaction",
             "geo",
             "geodmg",
             "hydro",
             "hydroapplication",
             "hydrodmg",
             "hydrorelatedreactions",
             "matchingelement",
             "omnielement",
             "passiveskill",
             "physicaldmg",
             "piercingdmg",
             "pyro",
             "pyroapplication",
             "pyrodmg",
             "pyrorelatedreactions",
             "randomhilichurlsummon",
             "shield",
             "unalignedelement",
             "usages"
     };

     String[] lang = {"en-US","zh-HK","zh-CN","ja-JP","ru-RU"};

     Context context;
     public void run(Context context){
          this.context = context;
          for (int y = 0 ; y < lang.length ; y++){
               for (int x = 0 ; x < itemName.length ; x++){
                    try {
                         JSONObject jsonObject = new JSONObject(LoadData("words/"+lang[y]+"/"+itemName[x]+".json"));
                         System.out.println(itemName[x]+"\t"+jsonObject.getString("name")+"\n");
                         writeToFile(itemName[x]+"\t"+jsonObject.getString("name")+"\n",context,lang[y]+"_keywords.txt");
                    } catch (JSONException e) {
                         e.printStackTrace();
                    }
               }
          }

     }
     public String LoadData(String inFile) {
          String tContents = "";
          try {
               File file = new File(context.getFilesDir()+"/"+inFile);
               InputStream stream = new FileInputStream(file);

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
     private void writeToFile(String data, Context context, String fileName) {
          try {
               Files.write(Paths.get(context.getFilesDir() + "/" + fileName), data.getBytes(), new StandardOpenOption[]{StandardOpenOption.APPEND});
          }
          catch (IOException e) {
               Log.e("Exception", "File write failed: " + e.toString());
          }
     }
}
