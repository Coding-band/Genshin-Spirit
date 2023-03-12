package com.voc.genshin_helper.buff;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

import java.util.Arrays;

public class Artifact {

    public static final int FLOWER = 0;
    public static final int PLUME = 1;
    public static final int SAND = 2;
    public static final int CIRCLET = 3;
    public static final int GOBLET = 4;

    public static final String FLOWER_STR = "EQUIP_BRACER";
    public static final String PLUME_STR = "EQUIP_NECKLACE";
    public static final String SAND_STR = "EQUIP_SHOES";
    public static final String CIRCLET_STR = "EQUIP_RING";
    public static final String GOBLET_STR = "EQUIP_DRESS";

    String artifactName = "Crimson Witch of Flames";
    int artifactLvl = 21; // need to source-1
    int artifactRare = 5;
    int artifactType = FLOWER;

    int artifactId = 80544;

    double[] artifactStatValue = new double[]{0,0,0,0,0};  // [MainStatus, SubStatus1, SubStatus2, SubStatus3, SubStatus4]
    String[] artifactStatStr = new String[]{"N/A","N/A","N/A","N/A","N/A"};  // [MainStatus, SubStatus1, SubStatus2, SubStatus3, SubStatus4]

    public String getArtifactName() {
        return artifactName;
    }

    public void setArtifactName(String artifactName) {
        this.artifactName = artifactName;
    }

    public int getArtifactLvl() {
        return artifactLvl;
    }

    public void setArtifactLvl(int artifactLvl) {
        this.artifactLvl = artifactLvl;
    }

    public int getArtifactRare() {
        return artifactRare;
    }

    public void setArtifactRare(int artifactRare) {
        this.artifactRare = artifactRare;
    }

    public int getArtifactType() {
        return artifactType;
    }

    public void setArtifactType(int artifactType) {
        this.artifactType = artifactType;
    }

    public int getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(int artifactId) {
        this.artifactId = artifactId;
    }

    public double[] getArtifactStatValue() {
        return artifactStatValue;
    }

    public void setArtifactStatValue(double[] artifactStatValue) {
        this.artifactStatValue = artifactStatValue;
    }

    public String[] getArtifactStatStr() {
        return artifactStatStr;
    }

    public void setArtifactStatStr(String[] artifactStatStr) {
        this.artifactStatStr = artifactStatStr;
    }


    /**
     *
     * @param level : Root is 0, subItem is 1...n
     * @return object's information
     */
    public String toString(int level){
        int nextLvl = level+1;
        String padding = "┣ ";
        String equal = " = ";
        for (int x = 0 ; x < level ; x++){
            padding = "\t"+padding;
        }

        return "Artifact@"+ Integer.toHexString(hashCode())+"\n"+
                padding + "artifactName" + equal + getArtifactName()+"\n"+
                padding + "artifactId" + equal + getArtifactId()+"\n"+
                padding + "artifactLvl" + equal + getArtifactLvl()+"\n"+
                padding + "artifactType" + equal + getTypeStrByType(getArtifactType())+"\n"+
                padding + "artifactStatStr" + equal + Arrays.toString(getArtifactStatStr())+"\n"+
                padding + "artifactStatValue" + equal + Arrays.toString(getArtifactStatValue())

                ;
    }

    public String getTypeStrByType(int TYPE){
        switch (TYPE){
            case FLOWER: return "FLOWER"+"_"+String.valueOf(TYPE);
            case PLUME: return "PLUME"+"_"+String.valueOf(TYPE);
            case SAND: return "SAND"+"_"+String.valueOf(TYPE);
            case CIRCLET: return "CIRCLET"+"_"+String.valueOf(TYPE);
            case GOBLET: return "GOBLET"+"_"+String.valueOf(TYPE);
            default: return "UNDEFINED"+"_"+String.valueOf(TYPE);
        }
    };
}
