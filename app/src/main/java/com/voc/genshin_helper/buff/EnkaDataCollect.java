package com.voc.genshin_helper.buff;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */


import static com.voc.genshin_helper.util.LogExport.DAILYMEMO;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.voc.genshin_helper.util.LogExport;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class EnkaDataCollect implements Serializable {
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String genshinUID;
    String enkaData = "N/A";

    ArrayList<BuffObject> buffObjects = new ArrayList<BuffObject>();

    public void init(Context context){
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
        genshinUID = sharedPreferences.getString("genshin_uid","-1");
        readEnka();
    }

    public void readEnka(){
        if(!genshinUID.equals("-1")){
            File jsonFile = new File(context.getFilesDir()+"/enkaData", genshinUID+".json");
            if(jsonFile.exists()){
                StringBuilder text = new StringBuilder();
                try {
                    BufferedReader br = new BufferedReader(new FileReader(jsonFile));
                    String line;
                    while ((line = br.readLine()) != null) {
                        text.append(line);
                        text.append('\n');
                    }
                    br.close();
                    enkaData = text.toString();

                    JSONObject root = new JSONObject(enkaData); // Root
                    JSONArray avatarInfoList = root.getJSONArray("avatarInfoList");

                    for(int i = 0 ; i < avatarInfoList.length(); i++){
                        BuffObject buffObject = new BuffObject();

                        Character character = new Character();
                        JSONObject propMap = avatarInfoList.getJSONObject(i).getJSONObject("propMap");
                        JSONArray talentIdList = (avatarInfoList.getJSONObject(i).has("talentIdList") ? avatarInfoList.getJSONObject(i).getJSONArray("talentIdList") : null);
                        JSONObject skillLevelMap = avatarInfoList.getJSONObject(i).getJSONObject("skillLevelMap");
                        JSONArray equipList = avatarInfoList.getJSONObject(i).getJSONArray("equipList");
                        JSONObject fightPropMap = avatarInfoList.getJSONObject(i).getJSONObject("fightPropMap");

                        character.setCharId(avatarInfoList.getJSONObject(i).getInt("avatarId") - 10000000);
                        int charId = character.getCharId() ;

                        // Character EXP
                        if(propMap.has("1001")){
                            character.setCharEXP(propMap.getJSONObject("1001").getInt("ival"));
                        }
                        // Character Ascension
                        if(propMap.has("1002")){
                            character.setCharASC(propMap.getJSONObject("1002").getInt("ival"));
                        }
                        // Character Level
                        if(propMap.has("4001")){
                            character.setCharLvl(propMap.getJSONObject("4001").getInt("ival"));
                        }

                        // Character Talent Level
                        int[] skillLevelValue = new int[]{0,0,0};
                        Iterator<String> keys = skillLevelMap.keys();
                        for (int x = 0 ; x < 3 ; x++){
                            if(skillLevelMap.length() > 3 && x == 0){
                                keys.next();
                            }else{
                                skillLevelValue[x] = skillLevelMap.getInt(keys.next());
                            }
                        }
                        character.setCharTalentLvl(skillLevelValue);

                        // Character Constellation
                        if(talentIdList != null){
                            character.setCharCon(talentIdList.length());
                        }else{
                            character.setCharCon(0);
                        }

                        // Artifact & Weapon [Use itemID to get item's name, database will be ready later]
                        for (int x = 0 ; x < equipList.length() ; x++){
                            int itemId = equipList.getJSONObject(x).getInt("itemId");
                            JSONObject equip = equipList.getJSONObject(x);
                            JSONObject flat = equip.getJSONObject("flat");
                            if(Math.floor(itemId / 10000) == 1){
                                //Weapon Case
                                JSONObject weaponJ = equip.getJSONObject("weapon");
                                JSONArray weaponStats = flat.getJSONArray("weaponStats");

                                Weapon weapon = new Weapon();
                                weapon.setWeaponName("SET_LATER");
                                weapon.setWeaponType(Weapon.POLEARM);
                                weapon.setWeaponId(equip.getInt("itemId"));
                                weapon.setWeaponLvl(weaponJ.getInt("level"));
                                weapon.setWeaponAffixLvl((weaponJ.has("affixMap") ? weaponJ.getJSONObject("affixMap").getInt(String.valueOf(100000+itemId)) : 0));
                                weapon.setWeaponRare(flat.getInt("rankLevel"));
                                weapon.setWeaponASCLvl((weaponJ.has("promoteLevel") ? weaponJ.getInt("promoteLevel") : 0));

                                double[] statusValue = new double[]{0,0};
                                String[] statusStr = new String[]{"N/A","N/A"};
                                for (int y = 0 ; y < weaponStats.length() && y < 2; y++){
                                    statusStr[y] = weaponStats.getJSONObject(y).getString("appendPropId"); // [MainStatus, SubStatus]
                                    statusValue[y] = weaponStats.getJSONObject(y).getDouble("statValue"); // [MainStatus, SubStatus]
                                }
                                weapon.setWeaponStatStr(statusStr);
                                weapon.setWeaponStatValue(statusValue);

                                buffObject.setWeapon(weapon);
                            }
                            else if (Math.floor(itemId / 10000) == 8) {
                                //Artifact Case
                                JSONObject reliquary = equip.getJSONObject("reliquary");
                                JSONObject reliquaryMainstat = flat.getJSONObject("reliquaryMainstat");
                                JSONArray reliquarySubstats = flat.getJSONArray("reliquarySubstats");

                                Artifact artifact = new Artifact();
                                artifact.setArtifactName("SET_LATER");
                                artifact.setArtifactId(equip.getInt("itemId"));
                                artifact.setArtifactLvl(reliquary.getInt("level")-1);
                                artifact.setArtifactRare(flat.getInt("rankLevel"));

                                double[] statusValue = new double[]{0,0,0,0,0};  // [MainStatus, SubStatus1, SubStatus2, SubStatus3, SubStatus4]
                                String[] statusStr = new String[]{"N/A","N/A","N/A","N/A","N/A"};  // [MainStatus, SubStatus1, SubStatus2, SubStatus3, SubStatus4]
                                statusStr[0] = reliquaryMainstat.getString("mainPropId");
                                statusValue[0] = reliquaryMainstat.getDouble("statValue");
                                for (int y = 0 ; y < (reliquarySubstats.length()) && y < 5; y++){
                                    statusStr[y+1] = reliquarySubstats.getJSONObject(y).getString("appendPropId");
                                    statusValue[y+1] = reliquarySubstats.getJSONObject(y).getDouble("statValue");
                                }
                                artifact.setArtifactStatStr(statusStr);
                                artifact.setArtifactStatValue(statusValue);

                                switch (flat.getString("equipType")){
                                    case Artifact.FLOWER_STR: {
                                        artifact.setArtifactType(Artifact.FLOWER);
                                        buffObject.setArtifactFlower(artifact);
                                        break;
                                    }
                                    case Artifact.PLUME_STR: {
                                        artifact.setArtifactType(Artifact.PLUME);
                                        buffObject.setArtifactPlume(artifact);
                                        break;
                                    }
                                    case Artifact.SAND_STR: {
                                        artifact.setArtifactType(Artifact.SAND);
                                        buffObject.setArtifactSand(artifact);
                                        break;
                                    }
                                    case Artifact.CIRCLET_STR: {
                                        artifact.setArtifactType(Artifact.CIRCLET);
                                        buffObject.setArtifactCirclet(artifact);
                                        break;
                                    }
                                    case Artifact.GOBLET_STR: {
                                        artifact.setArtifactType(Artifact.GOBLET);
                                        buffObject.setArtifactGoblet(artifact);
                                        break;
                                    }
                                }
                            }
                        }
                        buffObject.setCharacter(character);

                        // Character Board Status 面板狀態
                        for (int x = 0 ; x < Character.CHAR_FIGHT_PROP_LIST.length ; x ++){
                            if(fightPropMap.has(String.valueOf(Character.CHAR_FIGHT_PROP_LIST[x]))) {
                                double value = fightPropMap.getDouble(String.valueOf(Character.CHAR_FIGHT_PROP_LIST[x]));
                                switch (Character.CHAR_FIGHT_PROP_LIST[x]) {
                                    case 1 : { character.setCharBaseHP(value); break;}
                                    case 2 : { character.setCharHP(value); break;}
                                    case 3 : { character.setCharHPP(value); break;}
                                    case 4 : { character.setCharBaseATK(value); break;}
                                    case 5 : { character.setCharATK(value); break;}
                                    case 6 : { character.setCharATKP(value); break;}
                                    case 7 : { character.setCharBaseDEF(value); break;}
                                    case 8 : { character.setCharDEF(value); break;}
                                    case 9 : { character.setCharDEFP(value); break;}
                                    case 20 : { character.setCharCritRate(value); break;}
                                    case 22 : { character.setCharCritDMG(value); break;}
                                    case 23 : { character.setCharEnRech(value); break;}
                                    case 26 : { character.setCharHealP(value); break;}
                                    case 27 : { character.setCharGotHealP(value); break;}
                                    case 28 : { character.setCharEleMas(value); break;}
                                    case 29 : { character.setCharPhyRes(value); break;}
                                    case 30 : { character.setCharPhyDMGP(value); break;}
                                    case 40 : { character.setCharPyroDMGP(value); break;}
                                    case 41 : { character.setCharElectroDMGP(value); break;}
                                    case 42 : { character.setCharHydroDMGP(value); break;}
                                    case 43 : { character.setCharDendroDMGP(value); break;}
                                    case 44 : { character.setCharAnemoDMGP(value); break;}
                                    case 45 : { character.setCharGeoDMGP(value); break;}
                                    case 46 : { character.setCharCryoDMGP(value); break;}
                                    case 50 : { character.setCharPyroResP(value); break;}
                                    case 51 : { character.setCharElectroResP(value); break;}
                                    case 52 : { character.setCharHydroResP(value); break;}
                                    case 53 : { character.setCharDendroResP(value); break;}
                                    case 54 : { character.setCharAnemoResP(value); break;}
                                    case 55 : { character.setCharGeoResP(value); break;}
                                    case 56 : { character.setCharCryoResP(value); break;}
                                    case 2000 : { character.setCharMaxHP(value); break;}
                                    case 2001 : { character.setCharMaxATK(value); break;}
                                    case 2002 : { character.setCharMaxDEF(value); break;}

                                }
                            }
                        }

                        //System.out.println(buffObject.toString(0));
                        buffObjects.add(buffObject);
                    }
                } catch (IOException | JSONException e) {
                    throw new RuntimeException(e);
                }
            }else{
                new grabDataFromEnka().execute("https://enka.network/api/uid/"+genshinUID);
            }
        }
    }
    class grabDataFromEnka extends AsyncTask<String,Integer,String> {
        private static final int TIME_OUT = 5000;

        String str = "";
        protected void onPreExecute() {
            str = "";
        }
        @Override
        protected String doInBackground(String... url) {
            // TODO Auto-generated method stub
            // 再背景中處理的耗時工作
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url[0]).build();

            try {
                Response sponse = client.newCall(request).execute();
                str = sponse.body().string();
                Object json = new JSONTokener(str).nextValue();
                if (json instanceof JSONObject){
                    saveEnkaFile(context,genshinUID,str);
                }else{
                    System.out.println("Enka Read Error");
                }

            } catch (IOException | JSONException e) {
                LogExport.export("DataCollection","grabDataFromEnka.doInBackground", e.getMessage(), context, DAILYMEMO);
            }
            return "DONE";
        }



        public void onPostExecute(String result )
        { super.onPreExecute();
            // 背景工作處理完"後"需作的事
            readEnka();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);

        }
    }

    public void saveEnkaFile(Context context, String sFileName, String sBody){
        File dir = new File(context.getFilesDir(), "enkaData");
        if(!dir.exists()){
            dir.mkdir();
        }

        try {
            File jsonFile = new File(dir, sFileName+".json");
            FileWriter writer = new FileWriter(jsonFile);
            writer.append(sBody);
            writer.flush();
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
