package com.voc.genshin_helper.util;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class Version {

    public String Version;
    public String VerName;
    public String Description;
    public String IconId;

    public Version() {

    }

    public Version(String version, String vername, String description, String iconId) {
        this.Version = version;
        this.VerName = vername;
        this.Description = description;
        this.Description = description;
        this.IconId = iconId;

    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }

    public String getVerName() {
        return VerName;
    }

    public void setVerName(String VerName) {
        this.VerName = VerName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getIconId() {
        return IconId;
    }

    public void setIconId(String IconId) {
        this.IconId = IconId;
    }


}