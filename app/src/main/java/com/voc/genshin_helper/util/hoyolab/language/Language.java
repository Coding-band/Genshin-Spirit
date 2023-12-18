package com.voc.genshin_helper.util.hoyolab.language;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

/*
 * hoyolab.language.Language was refer from Dalufishe.
 * This is for Hoyolab only, not for app language change.
 */
public enum Language {
    SIMPLIFIED_CHINESE("zh-cn"),
    TRADIIONAL_CHINESE("zh-tw"),
    GERMAN("de-de"),
    ENGLISH("en-us"),
    SPANISH("es-es"),
    FRENCH("fr-fr"),
    INDONESIAN("id-id"),
    ITALIAN("it-it"),
    JAPANESE("ja-jp"),
    KOREAN("ko-kr"),
    PORTUGUESE("pt-pt"),
    RUSSIAN("ru-ru"),
    THAI("th-th"),
    TURKISH("tr-tr"),
    VIETNAMESE("vi-vn");

    private String code;
    Language(String code) {
        this.code = code;
    }
}
