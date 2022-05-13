package com.voc.genshin_helper.kidding;/*
 * Package com.voc.genshin_helper.kidding.goSleep was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.Console;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

public class GoSleep {

    String fin_str = "";

    public void sleep (Context context){
        System.out.println("忘憂訓緊覺");
        for (int i = 1 ; i < 4 ; i++){
            System.out.println(String.valueOf(i));
        }
        System.out.println("忘憂醒咗");
        System.out.println("忘憂去咗返工");
        System.out.println("忘憂->忙憂");

        String str1 = "  本書是關於龍應台與她大兒子之間的書信。他們已經四年沒有見面，加上母親在亞洲生活，兒子卻是在歐洲長大，大家相隔千里，缺乏溝通，於是他們希望透過互傳家書，重新認識對方，重建母子關係。";
        String str2 = "  龍應台在18歲時，以讀書作為人生的主要活動；而安德烈的18歲卻是周遊列國，到處玩樂。龍應台把孩子的「不務正業」視為「天地學問的根本」，因為她認為人生可以從玩樂中學習，這與華人社會的傳統價值觀背道而馳，可見龍應台是一位思想前衛的母親。即使如此開明的母親，仍然不敵處於反叛期的兒子。母親的天性是保護子女，並關心他們的一切，甚至渴望掌握孩子的一舉一動，生活在西方文化的安德烈卻不這樣樣認為。他對母親說:「你到今天都沒法明白，你的兒子不是你的兒子，他是一個完全獨立於你的別人。」龍應台花了好些年才慢慢接受兒子已經「長大」這個事實，學習如何給予對空間，尊重對方的意願。兩代人的矛盾，總要有一方需要稍稍向後退一步。";
        String str3 = "  另外，龍應台的金錢觀亦與傳統華人不同。華人社會總是以金錢作為成功的唯一的指標，認為孩子賺到越多錢便越是成功，龍應台卻表示我們對工作的熱誠才是勞動的真正意義。只要我們喜歡自己的工作，並從獲得滿足感，便是「工作」的真正意義。即使日進斗金，我們卻悶悶不樂，這只是成為工作的俘虜，人生是毫無意義的。";
        String str4 = "  現在的科技日新月異，無人機器越來越多，早前冬奧就出現了無人餐廳，機械人當起了廚師，甜品師甚至是調酒師，可能在不久的將來餐廳和酒吧裏的人類唯一的職責就是與別人聊天，所有的工序都交給了機械人負責。在這樣的未來下，我們是否還應該以金錢作成功的唯一的衡量指標呢?是否應該開始關注自身的快樂呢?無論從事多麼高上的職業，若果你無法從中得到快樂，那麼也只是成為了工作的奴隸。我們應當更加重視我們自身的特質，找到令自己滿足的工作，重新作自己的主人。";
        //String str5 = "  另外，無法加入工作室形同失業的制度，亦是使我感到可惜的，由於火星社會注重科技先進，所以亦只會投放資源於工作室設立及資金提供。但是這會使社會工種單一化，亦無法使社會多元化，社會上只有對某一部分的專業人才亦難以讓社會有更多其他方面的發展，故此制度並非完全可取。";

        splitStr(str1);
        splitStr(str2);
        splitStr(str3);
        splitStr(str4);
        //splitStr(str5);
        writeToFile(fin_str,context);
    };

    private void splitStr(String str){
        String[] str_split = str.split("");

        for (int x = 0 ; x < str.length() ; x++){

            fin_str = fin_str + str_split[x]+"\t";
            if (x % 15 == 0){
                fin_str = fin_str + "\n";
            }
        }
        fin_str = fin_str+"\n";
    }

    private void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public void putty(){
        int i = 115;
        String str = "yummy, i like putty";
        String replaceStr  = Character.toString((char) i) + Character.toString((char) i);

        str = str.replace("tt",replaceStr);
        System.out.println("SCLeon : "+str);
    }

    public void psw(){
        String password = "a123456";
        int x = 3;
        String psw_input = null;

        while (true){
            System.out.println("Enter Password : ");
            psw_input = System.console().readLine();
            if (psw_input.equals(password)){
                System.out.println("Success");
                break;
            }else{
                x --;
                System.out.println("fail, 還有"+x+"次機會");
                if (x == 0){
                    break;
                }
            }
        }
    }
}