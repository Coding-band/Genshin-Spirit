package com.voc.genshin_helper.kidding;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

import com.voc.genshin_helper.data.Material;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class RandomClsNo {
    ArrayList<Integer> askArray = new ArrayList<>();
    int randNum = 0;

    public void run(){
        for (int x = 0 ; x < 30 ; x ++){
            randNum = (int) (Math.random() * 30 + 1) ;
            while (askArray.contains(randNum)){
                randNum = (int) (Math.random() * 30 + 1) ;
            }
            System.out.println("6A "+randNum+"是誰?");
            askArray.add(randNum);
        }
    }



}
