package com.voc.genshin_helper.buff;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */


import static com.voc.genshin_helper.util.LogExport.DAILYMEMO;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Handler;

import com.voc.genshin_helper.buff.db.BuffDBContract;
import com.voc.genshin_helper.buff.db.BuffDBHelper;
import com.voc.genshin_helper.buff.obj.Artifact;
import com.voc.genshin_helper.buff.obj.BuffObject;
import com.voc.genshin_helper.buff.obj.Character;
import com.voc.genshin_helper.buff.obj.Weapon;
import com.voc.genshin_helper.data.Characters;
import com.voc.genshin_helper.data.IconCard;
import com.voc.genshin_helper.util.LogExport;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
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
    String genshinUserName;
    String enkaData = "N/A";
    BuffDBHelper dbHelper;

    Context tmpContext;

    ArrayList<BuffObject> buffObjectArray = new ArrayList<>();

    public void init(Context context){
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
        genshinUID = sharedPreferences.getString("genshin_uid","-1");
        genshinUserName = sharedPreferences.getString("genshin_username","Unknown");
        this.dbHelper = new BuffDBHelper(context);

        buffObjectArray = readEnka();
    }

    public void updateEnka(Context tmpContext){
        this.tmpContext = tmpContext;
        new grabDataFromEnka().execute("https://enka.network/api/uid/"+genshinUID);
    }

    public ArrayList<BuffObject> readEnka(){

        ArrayList<BuffObject> buffObjects = new ArrayList<BuffObject>();
        BuffCatelogy buffCatelogy = new BuffCatelogy();
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
                        character.setCharId(avatarInfoList.getJSONObject(i).getInt("avatarId"));
                        character.setCharName(buffCatelogy.getCharNameByID(avatarInfoList.getJSONObject(i).getInt("avatarId")));
                        character.setCharElement(buffCatelogy.getCharElementByName(character.getCharName(),context));
                        character.setCharRare(buffCatelogy.getCharRareByName(character.getCharName(),context));
                        JSONObject propMap = avatarInfoList.getJSONObject(i).getJSONObject("propMap");
                        JSONArray talentIdList = (avatarInfoList.getJSONObject(i).has("talentIdList") ? avatarInfoList.getJSONObject(i).getJSONArray("talentIdList") : null);
                        JSONObject skillLevelMap = avatarInfoList.getJSONObject(i).getJSONObject("skillLevelMap");
                        JSONArray equipList = avatarInfoList.getJSONObject(i).getJSONArray("equipList");
                        JSONObject fightPropMap = avatarInfoList.getJSONObject(i).getJSONObject("fightPropMap");


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
                                weapon.setWeaponId(equip.getInt("itemId"));
                                weapon.setWeaponName(buffCatelogy.getWeaponById(equip.getInt("itemId")));
                                weapon.setWeaponType(buffCatelogy.getWeaponTypeByName(weapon.getWeaponName(),context));
                                weapon.setWeaponLvl(weaponJ.getInt("level"));
                                weapon.setWeaponAffixLvl(1+(weaponJ.has("affixMap") ? weaponJ.getJSONObject("affixMap").getInt(String.valueOf(100000+itemId)) : 0));
                                weapon.setWeaponRare(flat.getInt("rankLevel"));
                                weapon.setWeaponASCLvl((weaponJ.has("promoteLevel") ? weaponJ.getInt("promoteLevel") : 0));
                                weapon.setWeaponFollow(character.getCharName());
                                weapon.setWeaponFollowId(character.getCharId());
                                double[] statusValue = new double[]{0,0};
                                String[] statusStr = new String[]{BuffObject.NONE,BuffObject.NONE};
                                for (int y = 0 ; y < weaponStats.length() && y < 2; y++){
                                    statusStr[y] = weaponStats.getJSONObject(y).getString("appendPropId"); // [MainStatus, SubStatus]
                                    statusValue[y] = weaponStats.getJSONObject(y).getDouble("statValue"); // [MainStatus, SubStatus]
                                }
                                weapon.setWeaponStatStr(statusStr);
                                weapon.setWeaponStatValue(statusValue);

                                buffObject.setWeapon(weapon);
                            }
                            else {
                                //Artifact Case
                                JSONObject reliquary = equip.getJSONObject("reliquary");
                                JSONObject reliquaryMainstat = flat.getJSONObject("reliquaryMainstat");
                                JSONArray reliquarySubstats = flat.getJSONArray("reliquarySubstats");
                                String iconSplit = flat.getString("icon").split("_")[2];

                                Artifact artifact = new Artifact();
                                artifact.setArtifactName(buffCatelogy.getArtifactById(Integer.parseInt(iconSplit)));
                                artifact.setArtifactId(equip.getInt("itemId"));
                                artifact.setArtifactLvl(reliquary.getInt("level")-1);
                                artifact.setArtifactRare(flat.getInt("rankLevel"));
                                artifact.setArtifactFollow(character.getCharName());
                                artifact.setArtifactFollowId(character.getCharId());

                                double[] statusValue = new double[]{0,0,0,0,0};  // [MainStatus, SubStatus1, SubStatus2, SubStatus3, SubStatus4]
                                String[] statusStr = new String[]{BuffObject.NONE,BuffObject.NONE,BuffObject.NONE,BuffObject.NONE,BuffObject.NONE};  // [MainStatus, SubStatus1, SubStatus2, SubStatus3, SubStatus4]
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

                        buffObject.setCharacter(character);
                        //System.out.println(buffObject.toString(0));
                        buffObjects.add(buffObject);
                    }

                    this.buffObjectArray = buffObjects;
                } catch (IOException | JSONException e) {
                    throw new RuntimeException(e);
                }
                return buffObjects;
            }else{
                new grabDataFromEnka().execute("https://enka.network/api/uid/"+genshinUID);
                return null;
            }
        }
        if(buffObjects.size() == 0){
            return null;
        }
        return buffObjects;
    }

    public ArrayList<BuffObject> getPreLoadBuffObjectArray(){
        return buffObjectArray;
    }

    public boolean saveToDataBase(ArrayList<BuffObject> buffObjects){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        BuffCatelogy buffCatelogy = new BuffCatelogy();
        if(buffObjects != null){

            if(tableExists(db,"Account_"+genshinUID+"_"+genshinUserName)){
                db.execSQL("DELETE FROM "+"Account_"+genshinUID+"_"+genshinUserName);

                int tmp_art_cnt = 0;

                ContentValues contentValues = new ContentValues();
                for (int x = 0 ; x < buffObjects.size() ; x++){
                    //Character
                    contentValues = new ContentValues();
                    contentValues.put("itemId", buffObjects.get(x).getCharacter().getCharId());
                    contentValues.put("itemName", buffObjects.get(x).getCharacter().getCharName());
                    contentValues.put("itemType", buffObjects.get(x).getCharacter().getCharElement());
                    contentValues.put("itemLvl", buffObjects.get(x).getCharacter().getCharLvl());
                    contentValues.put("itemASCLvl", buffObjects.get(x).getCharacter().getCharASC());
                    contentValues.put("itemRare", buffObjects.get(x).getCharacter().getCharRare());
                    contentValues.put("itemConstellationLvl", buffObjects.get(x).getCharacter().getCharCon());
                    contentValues.put("itemEXP", buffObjects.get(x).getCharacter().getCharEXP());
                    contentValues.put("charTalent1Lvl", buffObjects.get(x).getCharacter().getCharTalentLvl()[0]);
                    contentValues.put("charTalent2Lvl", buffObjects.get(x).getCharacter().getCharTalentLvl()[1]);
                    contentValues.put("charTalent3Lvl", buffObjects.get(x).getCharacter().getCharTalentLvl()[2]);
                    contentValues.put("charBaseHP", buffObjects.get(x).getCharacter().getCharBaseHP());
                    contentValues.put("charHP", buffObjects.get(x).getCharacter().getCharHP());
                    contentValues.put("charHPP", buffObjects.get(x).getCharacter().getCharHPP());
                    contentValues.put("charBaseATK", buffObjects.get(x).getCharacter().getCharBaseATK());
                    contentValues.put("charATK", buffObjects.get(x).getCharacter().getCharATK());
                    contentValues.put("charATKP", buffObjects.get(x).getCharacter().getCharATKP());
                    contentValues.put("charBaseDEF", buffObjects.get(x).getCharacter().getCharBaseDEF());
                    contentValues.put("charDEF", buffObjects.get(x).getCharacter().getCharDEF());
                    contentValues.put("charDEFP", buffObjects.get(x).getCharacter().getCharDEFP());
                    contentValues.put("charSpdP", buffObjects.get(x).getCharacter().getCharSpdP());
                    contentValues.put("charCritRate", buffObjects.get(x).getCharacter().getCharCritRate());
                    contentValues.put("charCritDMG", buffObjects.get(x).getCharacter().getCharCritDMG());
                    contentValues.put("charEnRech", buffObjects.get(x).getCharacter().getCharEnRech());
                    contentValues.put("charHealP", buffObjects.get(x).getCharacter().getCharHealP());
                    contentValues.put("charGotHealP", buffObjects.get(x).getCharacter().getCharGotHealP());
                    contentValues.put("charEleMas", buffObjects.get(x).getCharacter().getCharEleMas());
                    contentValues.put("charPhyRes", buffObjects.get(x).getCharacter().getCharPhyRes());
                    contentValues.put("charPhyDMGP", buffObjects.get(x).getCharacter().getCharPhyDMGP());
                    contentValues.put("charPyroDMGP", buffObjects.get(x).getCharacter().getCharPyroDMGP());
                    contentValues.put("charElectroDMGP", buffObjects.get(x).getCharacter().getCharElectroDMGP());
                    contentValues.put("charHydroDMGP", buffObjects.get(x).getCharacter().getCharHydroDMGP());
                    contentValues.put("charDendroDMGP", buffObjects.get(x).getCharacter().getCharDendroDMGP());
                    contentValues.put("charAnemoDMGP", buffObjects.get(x).getCharacter().getCharAnemoDMGP());
                    contentValues.put("charGeoDMGP", buffObjects.get(x).getCharacter().getCharGeoDMGP());
                    contentValues.put("charCryoDMGP", buffObjects.get(x).getCharacter().getCharCryoDMGP());
                    contentValues.put("charPyroResP", buffObjects.get(x).getCharacter().getCharPyroResP());
                    contentValues.put("charElectroResP", buffObjects.get(x).getCharacter().getCharElectroResP());
                    contentValues.put("charHydroResP", buffObjects.get(x).getCharacter().getCharHydroResP());
                    contentValues.put("charDendroResP", buffObjects.get(x).getCharacter().getCharDendroResP());
                    contentValues.put("charAnemoResP", buffObjects.get(x).getCharacter().getCharAnemoResP());
                    contentValues.put("charGeoResP", buffObjects.get(x).getCharacter().getCharGeoResP());
                    contentValues.put("charCryoResP", buffObjects.get(x).getCharacter().getCharCryoResP());
                    contentValues.put("charMaxHP", buffObjects.get(x).getCharacter().getCharMaxHP());
                    contentValues.put("charMaxATK", buffObjects.get(x).getCharacter().getCharMaxATK());
                    contentValues.put("charMaxDEF", buffObjects.get(x).getCharacter().getCharMaxDEF());
                    db.insert("Account_"+genshinUID+"_"+genshinUserName, null, contentValues);

                    //Weapon
                    contentValues = new ContentValues();
                    contentValues.put("itemId", buffObjects.get(x).getWeapon().getWeaponId());
                    contentValues.put("itemName", buffObjects.get(x).getWeapon().getWeaponName());
                    contentValues.put("itemType", buffCatelogy.getWeaponTypeByName(buffObjects.get(x).getWeapon().getWeaponName(),context));
                    contentValues.put("itemLvl", buffObjects.get(x).getWeapon().getWeaponLvl());
                    contentValues.put("itemAffixLvl", buffObjects.get(x).getWeapon().getWeaponAffixLvl());
                    contentValues.put("itemASCLvl", buffObjects.get(x).getWeapon().getWeaponASCLvl());
                    contentValues.put("itemRare", buffObjects.get(x).getWeapon().getWeaponRare());
                    contentValues.put("itemFollowChar", buffObjects.get(x).getWeapon().getWeaponFollow());
                    contentValues.put("itemFollowCharId", buffObjects.get(x).getWeapon().getWeaponFollowId());
                    contentValues.put("itemMainStatusStr", buffObjects.get(x).getWeapon().getWeaponStatStr()[0]);
                    contentValues.put("itemSubStatus1Str", buffObjects.get(x).getWeapon().getWeaponStatStr()[1]);
                    contentValues.put("itemMainStatusValue", buffObjects.get(x).getWeapon().getWeaponStatValue()[0]);
                    contentValues.put("itemSubStatus1Value", buffObjects.get(x).getWeapon().getWeaponStatValue()[1]);
                    db.insert("Account_"+genshinUID+"_"+genshinUserName, null, contentValues);
                    //Artifacts

                    ArrayList<Artifact> preAddArt = new ArrayList<>();
                    if (buffObjects.get(x).getArtifactFlower() != null){preAddArt.add(buffObjects.get(x).getArtifactFlower());}
                    if (buffObjects.get(x).getArtifactPlume() != null){preAddArt.add(buffObjects.get(x).getArtifactPlume());}
                    if (buffObjects.get(x).getArtifactSand() != null){preAddArt.add(buffObjects.get(x).getArtifactSand());}
                    if (buffObjects.get(x).getArtifactCirclet() != null){preAddArt.add(buffObjects.get(x).getArtifactCirclet());}
                    if (buffObjects.get(x).getArtifactGoblet() != null){preAddArt.add(buffObjects.get(x).getArtifactGoblet());}

                    for (int y = 0 ; y < preAddArt.size() ; y++){
                        contentValues = new ContentValues();
                        contentValues.put("itemId", preAddArt.get(y).getArtifactId());
                        contentValues.put("itemName", preAddArt.get(y).getArtifactName());
                        contentValues.put("itemType", preAddArt.get(y).getArtifactType());
                        contentValues.put("itemLvl", preAddArt.get(y).getArtifactLvl());
                        contentValues.put("itemRare", preAddArt.get(y).getArtifactRare());
                        contentValues.put("itemFollowChar", preAddArt.get(y).getArtifactFollow());
                        contentValues.put("itemFollowCharId", preAddArt.get(y).getArtifactFollowId());
                        contentValues.put("itemMainStatusStr", preAddArt.get(y).getArtifactStatStr()[0]);
                        contentValues.put("itemSubStatus1Str", preAddArt.get(y).getArtifactStatStr()[1]);
                        contentValues.put("itemSubStatus2Str", preAddArt.get(y).getArtifactStatStr()[2]);
                        contentValues.put("itemSubStatus3Str", preAddArt.get(y).getArtifactStatStr()[3]);
                        contentValues.put("itemSubStatus4Str", preAddArt.get(y).getArtifactStatStr()[4]);
                        contentValues.put("itemMainStatusValue", preAddArt.get(y).getArtifactStatValue()[0]);
                        contentValues.put("itemSubStatus1Value", preAddArt.get(y).getArtifactStatValue()[1]);
                        contentValues.put("itemSubStatus2Value", preAddArt.get(y).getArtifactStatValue()[2]);
                        contentValues.put("itemSubStatus3Value", preAddArt.get(y).getArtifactStatValue()[3]);
                        contentValues.put("itemSubStatus4Value", preAddArt.get(y).getArtifactStatValue()[4]);
                        db.insert("Account_"+genshinUID+"_"+genshinUserName, null, contentValues);

                        tmp_art_cnt ++;
                    }

                    db.execSQL("UPDATE Catelogy SET " +
                            "update_unix = \""+System.currentTimeMillis()+"\"," +
                            "char_cnt = "+buffObjects.size()+"," +
                            "weapon_cnt = "+buffObjects.size()+"," +
                            "artifact_cnt = "+tmp_art_cnt+
                            " where name = \""+"Account_"+genshinUID+"_"+genshinUserName+"\"");
                }
            }else{
                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(BuffDBContract.DataBase.COLUMN_NAME_ID, getIndexDBLength());
                values.put(BuffDBContract.DataBase.COLUMN_NAME_NAME, "Account_"+genshinUID+"_"+genshinUserName);
                values.put(BuffDBContract.DataBase.COLUMN_NAME_CHAR_CNT, -1);
                values.put(BuffDBContract.DataBase.COLUMN_NAME_WEAPON_CNT, -1);
                values.put(BuffDBContract.DataBase.COLUMN_NAME_ARTIFACT_CNT, -1);
                values.put(BuffDBContract.DataBase.COLUMN_NAME_CREATE_UNIX, System.currentTimeMillis());
                values.put(BuffDBContract.DataBase.COLUMN_NAME_UPDATE_UNIX, System.currentTimeMillis());

                db.execSQL("CREATE TABLE IF NOT EXISTS " + "Account_"+genshinUID+"_"+genshinUserName + " ( " +
                        "itemId"+ " INTEGER," +
                        "itemName"+ " TEXT," +
                        //"itemNameCode"+ " INTEGER," +
                        "itemType"+ " TEXT," +
                        "itemLvl"+ " INTEGER," +
                        "itemASCLvl"+ " INTEGER," +
                        "itemRare"+ " INTEGER," +
                        "itemAffixLvl"+ " INTEGER," +
                        "itemConstellationLvl"+ " INTEGER," +
                        "itemEXP"+ " INTEGER," +
                        "itemFollowChar"+ " TEXT," +
                        "itemFollowCharId"+ " INTEGER," +
                        "itemMainStatusStr"+ " TEXT," +
                        "itemMainStatusValue"+ " REAL," +
                        "itemSubStatus1Str"+ " TEXT," +
                        "itemSubStatus1Value"+ " REAL," +
                        "itemSubStatus2Str"+ " TEXT," +
                        "itemSubStatus2Value"+ " INTEGER," +
                        "itemSubStatus3Str"+ " TEXT," +
                        "itemSubStatus3Value"+ " REAL," +
                        "itemSubStatus4Str"+ " TEXT," +
                        "itemSubStatus4Value"+ " REAL," +
                        "charTalent1Lvl"+ " INTEGER," +
                        "charTalent2Lvl"+ " INTEGER," +
                        "charTalent3Lvl"+ " INTEGER," +
                        "charBaseHP"+ " REAL," +
                        "charHP"+ " REAL," +
                        "charHPP"+ " REAL," +
                        "charBaseATK"+ " REAL," +
                        "charATK"+ " REAL," +
                        "charATKP"+ " REAL," +
                        "charBaseDEF"+ " REAL," +
                        "charDEF"+ " REAL," +
                        "charDEFP"+ " REAL," +
                        "charSpdP"+ " REAL," +
                        "charCritRate"+ " REAL," +
                        "charCritDMG"+ " REAL," +
                        "charEnRech"+ " REAL," +
                        "charHealP"+ " REAL," +
                        "charGotHealP"+ " REAL," +
                        "charEleMas"+ " REAL," +
                        "charPhyRes"+ " REAL," +
                        "charPhyDMGP"+ " REAL," +
                        "charPyroDMGP"+ " REAL," +
                        "charElectroDMGP"+ " REAL," +
                        "charHydroDMGP"+ " REAL," +
                        "charDendroDMGP"+ " REAL," +
                        "charAnemoDMGP"+ " REAL," +
                        "charGeoDMGP"+ " REAL," +
                        "charCryoDMGP"+ " REAL," +
                        "charPyroResP"+ " REAL," +
                        "charElectroResP"+ " REAL," +
                        "charHydroResP"+ " REAL," +
                        "charDendroResP"+ " REAL," +
                        "charAnemoResP"+ " REAL," +
                        "charGeoResP"+ " REAL," +
                        "charCryoResP"+ " REAL," +
                        "charMaxHP"+ " REAL," +
                        "charMaxATK"+ " REAL," +
                        "charMaxDEF"+ " REAL"+");");


                // Insert the new row, returning the primary key value of the new row
                db.insert(BuffDBContract.DataBase.TABLE_NAME, null, values);
            }
        }
        return true;
    }



    private long getIndexDBLength(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, BuffDBContract.DataBase.TABLE_NAME);
        return count;
    }
    public boolean tableExists(SQLiteDatabase db, String tableName)
    {
        if (tableName == null || db == null || !db.isOpen())
        {
            return false;
        }
        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM Catelogy WHERE LOWER(name) = LOWER(?)",
                new String[] {tableName}
        );
        if (!cursor.moveToFirst())
        {
            cursor.close();
            return false;
        }
        int count = cursor.getInt(0);
        cursor.close();

        return count > 0;
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
            if(saveToDataBase(readEnka())) {
                if (tmpContext instanceof BuffDatabaseUI) {
                    ((BuffDatabaseUI) tmpContext).readIndexRecord();
                }
            }
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
