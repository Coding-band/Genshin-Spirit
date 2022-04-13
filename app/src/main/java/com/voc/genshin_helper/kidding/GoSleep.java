package com.voc.genshin_helper.kidding;/*
 * Package com.voc.genshin_helper.kidding.goSleep was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

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

        /*
        String str1 = "  本書從人類到火星上建立一個大城市開始，在經過十數年時間從地球不斷運輸建材，人才，以及最新的科技後，火星出現了與地球與別不同社會，例如在創作，科研方面，火星城市裡的新發明科技，藝術創作等等，均是一律公開，不允許作版權買賣的。亦因為行星之間社會制度過於相異，間接導致長達三十年的戰爭。";
        String str2 = "  而主角洛盈，則是戰後在火星上出生的少女，她在十三歲時被選中為地球交流團的一員，並展開長達五年的交流活動。洛盈在地球裏曾嘗試學習舞蹈，認識有相同興趣的朋友。在回到火星的時候，她卻對火星上的生活感到陌生。一次，她被告知父母因某些原因而被發配到邊緣地區，永不能回家，洛盈對此事十分感興趣，心中繼而藏了希望查明父母下落及真相的想法。及後，她獲邀加入其他團員的比賽小隊，打算一起參與火星社會裏一項發掘人才的大型比賽，洛盈藉著小隊在比賽中能使用獨立工作室，希望能發明一部小型飛機，到禁區尋找父母的下落。";
        String str3 = "  綜合全書內容，我對火星社會的制度有所反思，在地球社會裏，人們注重自己科研，藝術創作的版權，盈利等事物，而火星裏，人們卻因為制度不同，欠缺服務業等工種需求下，加入工作室變相成為了他們唯一的工作機會，當中，工作室研發的成果，成員設計的電影，均會被公開至中央伺服器，供任何人觀賞及借鑒。";
        String str4 = "  我認為，雖然公開科研成果予大眾看似能使整個社會得到高速，更先進的發展，但是上述的方法卻使研發成果變得毫無價值般，亦難以推動研究員繼續自由地研發對自己，甚或對社會有幫助的科技。";
        String str5 = "  另外，無法加入工作室形同失業的制度，亦是使我感到可惜的，由於火星社會注重科技先進，所以亦只會投放資源於工作室設立及資金提供。但是這會使社會工種單一化，亦無法使社會多元化，社會上只有對某一部分的專業人才亦難以讓社會有更多其他方面的發展，故此制度並非完全可取。";

        splitStr(str1);
        splitStr(str2);
        splitStr(str3);
        splitStr(str4);
        splitStr(str5);
        writeToFile(fin_str,context);
         */
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
}