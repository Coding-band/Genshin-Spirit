package com.voc.genshin_spirit_cn.kidding;/*
 * Package com.voc.genshin_spirit_cn.kidding.goSleep was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

import android.util.Log;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

public class GoSleep {


    public void sleep (){
        System.out.println("忘憂訓緊覺");
        for (int i = 1 ; i < 4 ; i++){
            System.out.println(String.valueOf(i));
        }
        System.out.println("忘憂醒咗");
        System.out.println("忘憂去咗返工");
        System.out.println("忘憂->忙憂");
    };

    public void putty(){
        int i = 115;
        String str = "yummy, i like putty";
        String replaceStr  = Character.toString((char) i) + Character.toString((char) i);

        str = str.replace("tt",replaceStr);
        System.out.println("SCLeon : "+str);
    }
}