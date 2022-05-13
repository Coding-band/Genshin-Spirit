package com.voc.genshin_helper.kidding;
/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

import java.io.Console;
import java.util.Scanner;

public class PasswordSetup {

    public static void main (String args[]){
        psw_setup();
    }

    public static void psw_setup(){
        System.out.println("----設定密碼----");
        System.out.println("輸入密碼 : ");
        Scanner scan = new Scanner(System.in);
        String psw = scan.nextLine();
        psw_2nd(0,psw);
    }

    public static void psw_2nd(int tried, String psw){
        final int try_times = 3;
        Scanner scan = new Scanner(System.in);

        if (tried > 0){
            System.out.println("錯"+tried+"次,請重新輸入密碼 : ");
        }else{
            System.out.println("重新輸入密碼 : ");
        }

        String psw2 = scan.nextLine();
        if (!psw2.equals(psw) && tried < try_times){
            tried++;
            psw_2nd(tried, psw);
        }else if(psw2.equals(psw)){
            login(psw);
        }else{
            tried = 0;
            psw_setup();
        }
    }

    public static void login(String psw){
        int tried = 0;
        final int try_times = 3;
        Scanner scan = new Scanner(System.in);
        String psw_input = " ";
        while (tried < try_times){
            System.out.println("入密碼喇 : ");
            psw_input = scan.nextLine();
            if (psw_input.equals(psw)){
                System.out.println("得喇");
                break;
            }else{
                tried++;

                if (tried == try_times){
                    System.out.println("唔好白撞喇");
                    break;
                }else{System.out.println("唔對路喎, 你係咪記得㗎， 仲有"+(try_times-tried)+"次機會， 好好珍惜，祝你好運");}
            }
        }
        System.out.println("完啦");
        scan.nextLine();
    }
}