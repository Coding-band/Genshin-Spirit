package com.voc.genshin_helper.data;

import java.util.ArrayList;
import java.util.Collections;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class Material {
    /**
     * Hi there, this is a set of materials
     *   that will use in Genshin's Characters, Weapons and Artifacts.
     *   It will use in Calculator Display & Record Part.
     */

    /** Crystal*/ /**[碎屑,斷片,塊,原顆]*/
    public ArrayList<Integer> 燃願瑪瑙 = new ArrayList<Integer>(Collections.nCopies(4, 0));
    public ArrayList<Integer> 滌淨青金 = new ArrayList<Integer>(Collections.nCopies(4, 0));
    public ArrayList<Integer> 最勝紫晶 = new ArrayList<Integer>(Collections.nCopies(4, 0));
    public ArrayList<Integer> 哀敘冰玉 = new ArrayList<Integer>(Collections.nCopies(4, 0));
    public ArrayList<Integer> 自在松石 = new ArrayList<Integer>(Collections.nCopies(4, 0));
    public ArrayList<Integer> 堅牢黃玉 = new ArrayList<Integer>(Collections.nCopies(4, 0));
    public ArrayList<Integer> 生長碧翡 = new ArrayList<Integer>(Collections.nCopies(4, 0));
    public ArrayList<Integer> 璀璨原鑽 = new ArrayList<Integer>(Collections.nCopies(4, 0));

    /** UNKNOWN BOSS ITEM*/
    public int 未知常駐BOSS跌落物1 = 0;
    public int 未知常駐BOSS跌落物2 = 0;
    public int 未知常駐BOSS跌落物3 = 0;
    public int 未知周本BOSS跌落物1 = 0;
    public int 未知周本BOSS跌落物2 = 0;
    public int 未知周本BOSS跌落物3 = 0;

    /** BOSS ASC*/
    public int  常燃火種  = 0 ;
    public int  淨水之心  = 0 ;
    public int  雷光棱鏡  = 0 ;
    public int  極寒之核  = 0 ;
    public int  颶風之種  = 0 ;
    public int  玄岩之塔  = 0 ;
    public int  未熟之玉  = 0 ;
    public int  晶凝之華  = 0 ;
    public int  魔偶機心  = 0 ;
    public int  恒常機關之心  = 0 ;
    public int  陰燃之珠  = 0 ;
    //add in 20210910
    public int  雷霆數珠 = 0;
    public int  排異之露 = 0;
    //add in 20220105
    public int 獸境王器 = 0;
    public int 龍嗣偽鰭 = 0;
    //add in 20220331
    public int 符紋之齒 = 0;
    //add in 20220716
    public int 蕈王鉤喙 = 0;
    public int 藏雷野實 = 0;
    //add in 20220829
    public int 永續機芯 = 0;
    public int 導光四面體 = 0;
    //add in 20220829
    public int 滅諍草蔓 = 0;
    //add in 20230115
    public int 蒼礫蕊羽 = 0;

    /** LOCAL */
    public int  小燈草  = 0 ;
    public int  慕風蘑菇  = 0 ;
    public int  夜泊石  = 0 ;
    public int  風車菊  = 0 ;
    public int  石珀  = 0 ;
    public int  蒲公英籽  = 0 ;
    public int  嘟嘟蓮  = 0 ;
    public int  落落莓  = 0 ;
    public int  琉璃百合  = 0 ;
    public int  琉璃袋  = 0 ;
    public int  鉤鉤果  = 0 ;
    public int  塞西莉亞花  = 0 ;
    public int  絕雲椒椒  = 0 ;
    public int  霓裳花  = 0 ;
    public int  星螺  = 0 ;
    public int  清心  = 0 ;
    public int  海靈芝  = 0 ;
    public int  緋櫻繡球  = 0 ;
    public int  鳴草  = 0 ;
    public int  晶化骨髓  = 0 ;
    //add in 20210910
    public int  天雲草實  = 0 ;
    public int  血斛  = 0 ;
    public int  幽燈蕈  = 0 ;
    public int  珊瑚真珠  = 0 ;
    public int  鬼兜蟲  = 0 ;
    //add in 20220716
    public int  樹王聖體菇 = 0;
    public int  劫波蓮 = 0;
    public int  月蓮 = 0;
    //add in 20220829
    public int  赤念果 = 0;
    public int  聖金蟲 = 0;
    public int  帕蒂沙蘭 = 0;
    //add in 20230115
    public int  沙脂蛹 = 0;

    /** T-BOSS ASC*/

    public int 智識之冕 = 0;
    public int  東風的吐息  = 0 ;
    public int  北風之環  = 0 ;
    public int  東風之翎  = 0 ;
    public int  北風的魂匣  = 0 ;
    public int  東風之爪  = 0 ;
    public int  北風之尾  = 0 ;
    public int  魔王之刃_殘片  = 0 ;
    public int  吞天之鯨_只角  = 0 ;
    public int  武煉之魂_孤影  = 0 ;
    public int  龍王之冕  = 0 ;
    public int  血玉之枝  = 0 ;
    public int  鎏金之鱗  = 0 ;
    //add in 20210910
    public int  熔毀之刻  = 0 ;
    public int  灰燼之心  = 0 ;
    public int  獄火之蝶  = 0 ;
    //add in 20220106
    public int  萬劫之真意  = 0 ; // Raiden Shogun Weekly Boss
    public int  凶將之手眼  = 0 ; // Raiden Shogun Weekly Boss
    public int  禍神之禊淚  = 0 ; // Raiden Shogun Weekly Boss
    //add in 20220106
    public int  傀儡的懸絲  = 0 ; // 散兵 Weekly Boss
    public int  無心的淵鏡  = 0 ; // 散兵 Weekly Boss
    public int  空行的虛鈴  = 0 ; // 散兵 Weekly Boss


    /** COMMON */ /**[牢固的箭簇,銳利的箭簇,歷戰的箭簇]*/
    public ArrayList<Integer> 歷戰的箭簇 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 禁咒繪卷 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 攫金鴉印 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 不祥的面具 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 尉官的徽記 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 原素花蜜 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 史萊姆原漿 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 名刀鐔 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    //add in 20210910
    public ArrayList<Integer> 浮游晶化核 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    //add in 20220716
    public ArrayList<Integer> 織金紅綢 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 孢囊晶塵 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 幽邃刻像 = new ArrayList<Integer>(Collections.nCopies(3, 0));


    /** T-COMMON*/

    public ArrayList<Integer> 未知1_的哲學 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 未知2_的哲學 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 未知3_的哲學 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 自由_的哲學 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 黃金_的哲學 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 抗爭_的哲學 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 勤勞_的哲學 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 詩文_的哲學 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 繁榮_的哲學 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 風雅_的哲學 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 浮世_的哲學 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 天光_的哲學 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    //add in 20220823
    public ArrayList<Integer> 篤行_的哲學 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 巧思_的哲學 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 諍言_的哲學 = new ArrayList<Integer>(Collections.nCopies(3, 0));

    /** W-COMMON*/
    public ArrayList<Integer> 	漆黑隕鐵的一塊	 = new ArrayList<Integer>(Collections.nCopies(4, 0));
    public ArrayList<Integer> 	獅牙鬥士的理想	 = new ArrayList<Integer>(Collections.nCopies(4, 0));
    public ArrayList<Integer> 	孤雲寒林的神體	 = new ArrayList<Integer>(Collections.nCopies(4, 0));
    public ArrayList<Integer> 	霧海雲間的轉還	 = new ArrayList<Integer>(Collections.nCopies(4, 0));
    public ArrayList<Integer> 	今昔劇畫的鬼人	 = new ArrayList<Integer>(Collections.nCopies(4, 0));
    public ArrayList<Integer> 	鳴神御靈的勇武	 = new ArrayList<Integer>(Collections.nCopies(4, 0));
    public ArrayList<Integer> 	高塔孤王的碎夢	 = new ArrayList<Integer>(Collections.nCopies(4, 0));
    public ArrayList<Integer> 	遠海夷地的金枝	 = new ArrayList<Integer>(Collections.nCopies(4, 0));
    public ArrayList<Integer> 	凜風奔狼的懷鄉	 = new ArrayList<Integer>(Collections.nCopies(4, 0));
    //add in 20220823
    public ArrayList<Integer> 	謐林涓露的金符	 = new ArrayList<Integer>(Collections.nCopies(4, 0));
    public ArrayList<Integer> 	綠洲花園的真諦	 = new ArrayList<Integer>(Collections.nCopies(4, 0));
    public ArrayList<Integer> 	烈日威權的舊日	 = new ArrayList<Integer>(Collections.nCopies(4, 0));

    public ArrayList<Integer> 	混沌真眼	 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 	混沌爐心	 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 	石化的骨片	 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 	霧虛燈芯	 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 	督察長祭刀	 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 	黑晶號角	 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 	地脈的新芽	 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 	偏光棱鏡	 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 	隱獸鬼爪	 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    //add in 20220823
    public ArrayList<Integer> 	茁壯菌核	 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    public ArrayList<Integer> 	混沌錨栓	 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    //add in 20220924
    public ArrayList<Integer> 	輝光稜晶	 = new ArrayList<Integer>(Collections.nCopies(3, 0));
    //add in 20220924
    public ArrayList<Integer> 	鍥紋的橫脊	 = new ArrayList<Integer>(Collections.nCopies(3, 0));

    //-----------↑Method↑-----------↓Return↓-----------\\

    /** CRYSTAL */
    public String[] crystalNameList (){
        return new String[]{
                "璀璨原鑽碎屑","璀璨原鑽斷片","璀璨原鑽塊","璀璨原鑽",
                "燃願瑪瑙碎屑","燃願瑪瑙斷片","燃願瑪瑙塊","燃願瑪瑙",
                "滌淨青金碎屑","滌淨青金斷片","滌淨青金塊","滌淨青金",
                "最勝紫晶碎屑","最勝紫晶斷片","最勝紫晶塊","最勝紫晶",
                "哀敘冰玉碎屑","哀敘冰玉斷片","哀敘冰玉塊","哀敘冰玉",
                "自在松石碎屑","自在松石斷片","自在松石塊","自在松石",
                "堅牢黃玉碎屑","堅牢黃玉斷片","堅牢黃玉塊","堅牢黃玉",
                "生長碧翡碎屑","生長碧翡斷片","生長碧翡塊","生長碧翡"
        };
    }
    public int[] crystalCountList (){
        return new int[]{
                璀璨原鑽.get(0),璀璨原鑽.get(1),璀璨原鑽.get(2),璀璨原鑽.get(3),
                燃願瑪瑙.get(0),燃願瑪瑙.get(1),燃願瑪瑙.get(2),燃願瑪瑙.get(3),
                滌淨青金.get(0),滌淨青金.get(1),滌淨青金.get(2),滌淨青金.get(3),
                最勝紫晶.get(0),最勝紫晶.get(1),最勝紫晶.get(2),最勝紫晶.get(3),
                哀敘冰玉.get(0),哀敘冰玉.get(1),哀敘冰玉.get(2),哀敘冰玉.get(3),
                自在松石.get(0),自在松石.get(1),自在松石.get(2),自在松石.get(3),
                堅牢黃玉.get(0),堅牢黃玉.get(1),堅牢黃玉.get(2),堅牢黃玉.get(3),
                生長碧翡.get(0),生長碧翡.get(1),生長碧翡.get(2),生長碧翡.get(3)
        };
    }
    public int[] crystalRareList (){
        return new int[]{
                2,3,4,5,
                2,3,4,5,
                2,3,4,5,
                2,3,4,5,
                2,3,4,5,
                2,3,4,5,
                2,3,4,5,
                2,3,4,5
        };
    }

    /** BOSS */
    public String[] bossNameList (){
        return new String[]{
                "未知常駐BOSS跌落物1","未知常駐BOSS跌落物2","未知常駐BOSS跌落物3",
                "常燃火種","淨水之心","雷光棱鏡",
                "極寒之核","颶風之種","玄岩之塔",
                "未熟之玉","晶凝之華","魔偶機心",
                "恒常機關之心","陰燃之珠","雷霆數珠",
                "排異之露","獸境王器","龍嗣偽鰭",
                "符紋之齒","蕈王鉤喙","藏雷野實",
                "永續機芯","導光四面體","滅諍草蔓",
                "蒼礫蕊羽"
        };
    }
    public int[] bossCountList (){
        return new int[]{
                未知常駐BOSS跌落物1,未知常駐BOSS跌落物2,未知常駐BOSS跌落物3,
                常燃火種,淨水之心,雷光棱鏡,
                極寒之核,颶風之種,玄岩之塔,
                未熟之玉,晶凝之華,魔偶機心,
                恒常機關之心,陰燃之珠,雷霆數珠,
                排異之露,獸境王器,龍嗣偽鰭,
                符紋之齒,蕈王鉤喙,藏雷野實,
                永續機芯,導光四面體,滅諍草蔓,
                蒼礫蕊羽
        };
    }

    /** WEEK-BOSS */
    public String[] weekBossNameList (){
        return new String[]{
                "未知周本BOSS跌落物1","未知周本BOSS跌落物2","未知周本BOSS跌落物3",
                "北風之環","東風的吐息","東風之翎",
                "北風的魂匣","東風之爪","北風之尾",
                "魔王之刃·殘片","吞天之鯨·只角","武煉之魂·孤影",
                "龍王之冕","血玉之枝","鎏金之鱗",
                "熔毀之刻","灰燼之心","獄火之蝶",
                "萬劫之真意","凶將之手眼","禍神之禊淚",
                "傀儡的懸絲","無心的淵鏡","空行的虛鈴"

        };
    }
    public int[] weekBossCountList (){
        return new int[]{
                未知周本BOSS跌落物1,未知周本BOSS跌落物2,未知周本BOSS跌落物3,
                北風之環,東風的吐息,東風之翎,
                北風的魂匣,東風之爪,北風之尾,
                魔王之刃_殘片,吞天之鯨_只角,武煉之魂_孤影,
                龍王之冕,血玉之枝,鎏金之鱗,
                熔毀之刻,灰燼之心,獄火之蝶,
                萬劫之真意,凶將之手眼,禍神之禊淚,
                傀儡的懸絲,無心的淵鏡,空行的虛鈴
        };
    }

    /** LOCAL */
    public String[] localNameList (){
        return new String[]{
                "小燈草","慕風蘑菇","夜泊石",
                "風車菊","石珀","蒲公英籽",
                "嘟嘟蓮","落落莓","琉璃百合",
                "琉璃袋","鉤鉤果","塞西莉亞花",
                "絕雲椒椒","霓裳花","星螺",
                "清心","海靈芝","緋櫻繡球",
                "鳴草","晶化骨髓","天雲草實",
                "血斛","幽燈蕈","珊瑚真珠",
                "鬼兜蟲","樹王聖體菇","月蓮",
                "劫波蓮","赤念果","聖金蟲",
                "帕蒂沙蘭","沙脂蛹"
        };
    }
    public int[] localCountList (){
        return new int[]{
                小燈草,慕風蘑菇,夜泊石,
                風車菊,石珀,蒲公英籽,
                嘟嘟蓮,落落莓,琉璃百合,
                琉璃袋,鉤鉤果,塞西莉亞花,
                絕雲椒椒,霓裳花,星螺,
                清心,海靈芝,緋櫻繡球,
                鳴草,晶化骨髓,天雲草實,
                血斛,幽燈蕈,珊瑚真珠,
                鬼兜蟲,樹王聖體菇,月蓮,
                劫波蓮,赤念果,聖金蟲,
                帕蒂沙蘭,沙脂蛹
        };
    }
    public int[] localRareList (){
        return new int[]{
                1,1,1,
                1,1,1,
                1,1,1,
                1,1,1,
                1,1,1,
                1,1,1,
                1,1,1,
                1,1,1,
                1,1,1,
                1,1,1,
                1,1,1,
                1
        };
    }

    /** COMMON */
    public String[] commonNameList (){
        return new String[]{
                "牢固的箭簇","銳利的箭簇","歷戰的箭簇",
                "導能繪卷","封魔繪卷","禁咒繪卷",
                "尋寶鴉印","藏銀鴉印","攫金鴉印",
                "破損的面具","污穢的面具","不祥的面具",
                "新兵的徽記","士官的徽記","尉官的徽記",
                "騙騙花蜜","微光花蜜","原素花蜜",
                "史萊姆凝液","史萊姆清","史萊姆原漿",
                "破舊的刀鐔","影打刀鐔","名刀鐔",
                "浮游乾核","浮游幽核","浮游晶化核",
                "混沌機關", "混沌樞紐", "混沌真眼",
                "混沌裝置", "混沌迴路", "混沌爐心",
                "脆弱的骨片", "結實的骨片", "石化的骨片",
                "霧虛花粉", "霧虛草囊", "霧虛燈芯",
                "獵兵祭刀", "特工祭刀", "督察長祭刀",
                "沉重號角", "黑銅號角", "黑晶號角",
                "地脈的舊枝", "地脈的枯葉", "地脈的新芽",
                "黯淡棱鏡", "水晶棱鏡", "偏光棱鏡",
                "隱獸指爪","隱獸利爪","隱獸鬼爪",
                "蕈獸孢子","螢光孢粉","孢囊晶塵",
                "褪色紅綢","鑲邊紅綢","織金紅綢",
                "晦暗刻像","夤夜刻像","幽邃刻像",
                "失活菌核","休眠菌核","茁壯菌核",
                "混沌容器","混沌模組","混沌錨栓",
                "破缺稜晶","混濁稜晶","輝光稜晶",
                "殘毀的橫脊","密固的橫脊","鍥紋的橫脊"

        };
    }
    public int[] commonCountList (){
        return new int[]{
                // 1,2,3
                歷戰的箭簇.get(0),歷戰的箭簇.get(1),歷戰的箭簇.get(2),
                禁咒繪卷.get(0),禁咒繪卷.get(1),禁咒繪卷.get(2),
                攫金鴉印.get(0),攫金鴉印.get(1),攫金鴉印.get(2),
                不祥的面具.get(0),不祥的面具.get(1),不祥的面具.get(2),
                尉官的徽記.get(0),尉官的徽記.get(1),尉官的徽記.get(2),
                原素花蜜.get(0),原素花蜜.get(1),原素花蜜.get(2),
                史萊姆原漿.get(0),史萊姆原漿.get(1),史萊姆原漿.get(2),
                名刀鐔.get(0),名刀鐔.get(1),名刀鐔.get(2),
                浮游晶化核.get(0),浮游晶化核.get(1),浮游晶化核.get(2),

                // 2,3,4
                混沌真眼.get(0),混沌真眼.get(1),混沌真眼.get(2),
                混沌爐心.get(0),混沌爐心.get(1),混沌爐心.get(2),
                石化的骨片.get(0),石化的骨片.get(1),石化的骨片.get(2),
                霧虛燈芯.get(0),霧虛燈芯.get(1),霧虛燈芯.get(2),
                督察長祭刀.get(0),督察長祭刀.get(1),督察長祭刀.get(2),
                黑晶號角.get(0),黑晶號角.get(1),黑晶號角.get(2),
                地脈的新芽.get(0),地脈的新芽.get(1),地脈的新芽.get(2),
                偏光棱鏡.get(0),偏光棱鏡.get(1),偏光棱鏡.get(2),
                隱獸鬼爪.get(0),隱獸鬼爪.get(1),隱獸鬼爪.get(2),
                孢囊晶塵.get(0),孢囊晶塵.get(1),孢囊晶塵.get(2),
                織金紅綢.get(0),織金紅綢.get(1),織金紅綢.get(2),
                幽邃刻像.get(0),幽邃刻像.get(1),幽邃刻像.get(2),
                茁壯菌核.get(0),茁壯菌核.get(1),茁壯菌核.get(2),
                混沌錨栓.get(0),混沌錨栓.get(1),混沌錨栓.get(2),
                輝光稜晶.get(0),輝光稜晶.get(1),輝光稜晶.get(2),
                鍥紋的橫脊.get(0),鍥紋的橫脊.get(1),鍥紋的橫脊.get(2)
        };
    }
    public int[] commonRareList (){
        return new int[]{
                1,2,3,
                1,2,3,
                1,2,3,
                1,2,3,
                1,2,3,
                1,2,3,
                1,2,3,
                1,2,3,
                1,2,3,

                2,3,4,
                2,3,4,
                2,3,4,
                2,3,4,
                2,3,4,
                2,3,4,
                2,3,4,
                2,3,4,
                2,3,4,
                2,3,4,
                2,3,4,
                2,3,4,
                2,3,4,
                2,3,4,
                2,3,4,
                2,3,4
        };
    }

    /** WEEKLY-MON-THUR-SUN */
    public String[] weekly1NameList (){
        return new String[]{
                "「未知1」的教導","「未知1」的指引","「未知1」的哲學",
                "「未知2」的教導","「未知2」的指引","「未知2」的哲學",
                "「未知3」的教導","「未知3」的指引","「未知3」的哲學",
                "「自由」的教導","「自由」的指引","「自由」的哲學",
                "「繁榮」的教導","「繁榮」的指引","「繁榮」的哲學",
                "「浮世」的教導","「浮世」的指引","「浮世」的哲學",
                "「諍言」的教導","「諍言」的指引","「諍言」的哲學",

                "高塔孤王的破瓦", "高塔孤王的殘垣", "高塔孤王的斷片", "高塔孤王的碎夢",
                "孤雲寒林的光砂", "孤雲寒林的輝岩", "孤雲寒林的聖骸", "孤雲寒林的神體",
                "遠海夷地的瑚枝", "遠海夷地的玉枝", "遠海夷地的瓊枝", "遠海夷地的金枝",
                "謐林涓露的銅符", "謐林涓露的鐵符", "謐林涓露的銀符", "謐林涓露的金符"
        };
    }
    public int[] weekly1CountList (){
        return new int[]{
                未知1_的哲學.get(0),未知1_的哲學.get(1),未知1_的哲學.get(2),
                未知2_的哲學.get(0),未知2_的哲學.get(1),未知2_的哲學.get(2),
                未知3_的哲學.get(0),未知3_的哲學.get(1),未知3_的哲學.get(2),
                自由_的哲學.get(0),自由_的哲學.get(1),自由_的哲學.get(2),
                繁榮_的哲學.get(0),繁榮_的哲學.get(1),繁榮_的哲學.get(2),
                浮世_的哲學.get(0),浮世_的哲學.get(1),浮世_的哲學.get(2),
                諍言_的哲學.get(0),諍言_的哲學.get(1),諍言_的哲學.get(2),

                高塔孤王的碎夢.get(0),高塔孤王的碎夢.get(1),高塔孤王的碎夢.get(2),高塔孤王的碎夢.get(3),
                孤雲寒林的神體.get(0),孤雲寒林的神體.get(1),孤雲寒林的神體.get(2),孤雲寒林的神體.get(3),
                遠海夷地的金枝.get(0),遠海夷地的金枝.get(1),遠海夷地的金枝.get(2),遠海夷地的金枝.get(3),
                謐林涓露的金符.get(0),謐林涓露的金符.get(1),謐林涓露的金符.get(2),謐林涓露的金符.get(3)
        };
    }
    public int[] weekly1RareList (){
        return new int[]{
                2,3,4,
                2,3,4,
                2,3,4,
                2,3,4,
                2,3,4,
                2,3,4,
                2,3,4,

                2,3,4,5,
                2,3,4,5,
                2,3,4,5,
                2,3,4,5,
                2,3,4,5
        };
    }


    /** WEEKLY-TUE-FRI-SUN */
    public String[] weekly2NameList (){
        return new String[]{
                "「未知2」的教導","「未知2」的指引","「未知2」的哲學",
                "「抗爭」的教導","「抗爭」的指引","「抗爭」的哲學",
                "「勤勞」的教導","「勤勞」的指引","「勤勞」的哲學",
                "「風雅」的教導","「風雅」的指引","「風雅」的哲學",
                "「巧思」的教導","「巧思」的指引","「巧思」的哲學",

                "凜風奔狼的始齔", "凜風奔狼的裂齒", "凜風奔狼的斷牙", "凜風奔狼的懷鄉",
                "霧海雲間的鉛丹", "霧海雲間的汞丹", "霧海雲間的金丹", "霧海雲間的轉還",
                "鳴神御靈的明惠", "鳴神御靈的歡喜", "鳴神御靈的親愛", "鳴神御靈的勇武",
                "綠洲花園的追憶","綠洲花園的恩惠","綠洲花園的哀思","綠洲花園的真諦"
        };
    }
    public int[] weekly2CountList (){
        return new int[]{
                未知2_的哲學.get(0),未知2_的哲學.get(1),未知2_的哲學.get(2),
                抗爭_的哲學.get(0),抗爭_的哲學.get(1),抗爭_的哲學.get(2),
                勤勞_的哲學.get(0),勤勞_的哲學.get(1),勤勞_的哲學.get(2),
                風雅_的哲學.get(0),風雅_的哲學.get(1),風雅_的哲學.get(2),
                巧思_的哲學.get(0),巧思_的哲學.get(1),巧思_的哲學.get(2),

                凜風奔狼的懷鄉.get(0),凜風奔狼的懷鄉.get(1),凜風奔狼的懷鄉.get(2),凜風奔狼的懷鄉.get(3),
                霧海雲間的轉還.get(0),霧海雲間的轉還.get(1),霧海雲間的轉還.get(2),霧海雲間的轉還.get(3),
                鳴神御靈的勇武.get(0),鳴神御靈的勇武.get(1),鳴神御靈的勇武.get(2),鳴神御靈的勇武.get(3),
                綠洲花園的真諦.get(0),綠洲花園的真諦.get(1),綠洲花園的真諦.get(2),綠洲花園的真諦.get(3)
        };
    }
    public int[] weekly2RareList (){
        return new int[]{
                2,3,4,
                2,3,4,
                2,3,4,
                2,3,4,

                2,3,4,5,
                2,3,4,5,
                2,3,4,5,
                2,3,4,5,
                2,3,4,5
        };
    }


    /** WEEKLY-WED-SAT-SUN */
    public String[] weekly3NameList (){
        return new String[]{
                "「未知3」的教導","「未知3」的指引","「未知3」的哲學",
                "「黃金」的教導","「黃金」的指引","「黃金」的哲學",
                "「詩文」的教導","「詩文」的指引","「詩文」的哲學",
                "「天光」的教導","「天光」的指引","「天光」的哲學",
                "「篤行」的教導","「篤行」的指引","「篤行」的哲學",

                "獅牙鬥士的枷鎖", "獅牙鬥士的鐵鍊", "獅牙鬥士的鐐銬", "獅牙鬥士的理想",
                "漆黑隕鐵的一粒", "漆黑隕鐵的一片", "漆黑隕鐵的一角", "漆黑隕鐵的一塊",
                "今昔劇畫的惡尉", "今昔劇畫的虎囓", "今昔劇畫的一角", "今昔劇畫的鬼人",
                "烈日威權的殘響","烈日威權的餘光","烈日威權的夢想","烈日威權的舊日"
        };
    }
    public int[] weekly3CountList (){
        return new int[]{
                未知3_的哲學.get(0),未知3_的哲學.get(1),未知3_的哲學.get(2),
                黃金_的哲學.get(0),黃金_的哲學.get(1),黃金_的哲學.get(2),
                詩文_的哲學.get(0),詩文_的哲學.get(1),詩文_的哲學.get(2),
                天光_的哲學.get(0),天光_的哲學.get(1),天光_的哲學.get(2),
                篤行_的哲學.get(0),篤行_的哲學.get(1),篤行_的哲學.get(2),

                獅牙鬥士的理想.get(0),獅牙鬥士的理想.get(1),獅牙鬥士的理想.get(2),獅牙鬥士的理想.get(3),
                漆黑隕鐵的一塊.get(0),漆黑隕鐵的一塊.get(1),漆黑隕鐵的一塊.get(2),漆黑隕鐵的一塊.get(3),
                今昔劇畫的鬼人.get(0),今昔劇畫的鬼人.get(1),今昔劇畫的鬼人.get(2),今昔劇畫的鬼人.get(3),
                烈日威權的舊日.get(0),烈日威權的舊日.get(1),烈日威權的舊日.get(2),烈日威權的舊日.get(3)
        };
    }
    public int[] weekly3RareList (){
        return new int[]{
                2,3,4,
                2,3,4,
                2,3,4,
                2,3,4,

                2,3,4,5,
                2,3,4,5,
                2,3,4,5,
                2,3,4,5,
                2,3,4,5
        };
    }

    //-----------↑Return↑-----------↓Function↓-----------\\

    /**EDIT WHEN ADD NEW ITEMS*/
    public void FindItemByName(ArrayList<String> temp_item, ArrayList<Integer> temp_count){
        if(temp_item.size() >= 5){
            /** CRYSTAL -> USING temp_count's pos 0-3*/

            switch (temp_item.get(1)) {
                case "燃願瑪瑙": addCountIntoVar(燃願瑪瑙, temp_count, "CRYSTAL"); break;
                case "滌淨青金": addCountIntoVar(滌淨青金, temp_count, "CRYSTAL"); break;
                case "最勝紫晶": addCountIntoVar(最勝紫晶, temp_count, "CRYSTAL"); break;
                case "哀敘冰玉": addCountIntoVar(哀敘冰玉, temp_count, "CRYSTAL"); break;
                case "自在松石": addCountIntoVar(自在松石, temp_count, "CRYSTAL"); break;
                case "堅牢黃玉": addCountIntoVar(堅牢黃玉, temp_count, "CRYSTAL"); break;
                case "生長碧翡": addCountIntoVar(生長碧翡, temp_count, "CRYSTAL"); break;
                case "璀璨原鑽": addCountIntoVar(璀璨原鑽, temp_count, "CRYSTAL"); break;
            }

            /** COMMON -> USING temp_count's pos 7-9*/
            switch (temp_item.get(4)) {
                case "歷戰的箭簇": addCountIntoVar(歷戰的箭簇, temp_count, "COMMON"); break;
                case "禁咒繪卷": addCountIntoVar(禁咒繪卷, temp_count, "COMMON"); break;
                case "攫金鴉印": addCountIntoVar(攫金鴉印, temp_count, "COMMON"); break;
                case "不祥的面具": addCountIntoVar(不祥的面具, temp_count, "COMMON"); break;
                case "尉官的徽記": addCountIntoVar(尉官的徽記, temp_count, "COMMON"); break;
                case "原素花蜜": addCountIntoVar(原素花蜜, temp_count, "COMMON"); break;
                case "史萊姆原漿": addCountIntoVar(史萊姆原漿, temp_count, "COMMON"); break;
                case "名刀鐔": addCountIntoVar(名刀鐔, temp_count, "COMMON"); break;
                //add in 20210910
                case "浮游晶化核": addCountIntoVar(浮游晶化核, temp_count, "COMMON"); break;
                //add in 20220716
                case "孢囊晶塵": addCountIntoVar(孢囊晶塵, temp_count, "COMMON"); break;
                case "織金紅綢": addCountIntoVar(織金紅綢, temp_count, "COMMON"); break;
            }

            /** BOSS -> USING temp_count's pos 8 */

            switch (temp_item.get(2)) {
                // SPECIAL CASE ONLY
                case "未知常駐BOSS跌落物1": 未知常駐BOSS跌落物1 = addCountIntoVar(未知常駐BOSS跌落物1, temp_count, "BOSS"); break;
                case "未知常駐BOSS跌落物2": 未知常駐BOSS跌落物2 = addCountIntoVar(未知常駐BOSS跌落物2, temp_count, "BOSS"); break;
                case "未知常駐BOSS跌落物3": 未知常駐BOSS跌落物3 = addCountIntoVar(未知常駐BOSS跌落物3, temp_count, "BOSS"); break;
                // NORMAL
                case "常燃火種": 常燃火種 = addCountIntoVar(常燃火種, temp_count, "BOSS"); break;
                case "淨水之心": 淨水之心 = addCountIntoVar(淨水之心, temp_count, "BOSS"); break;
                case "雷光棱鏡": 雷光棱鏡 = addCountIntoVar(雷光棱鏡, temp_count, "BOSS"); break;
                case "極寒之核": 極寒之核 = addCountIntoVar(極寒之核, temp_count, "BOSS"); break;
                case "颶風之種": 颶風之種 = addCountIntoVar(颶風之種, temp_count, "BOSS"); break;
                case "玄岩之塔": 玄岩之塔 = addCountIntoVar(玄岩之塔, temp_count, "BOSS"); break;
                case "未熟之玉": 未熟之玉 = addCountIntoVar(未熟之玉, temp_count, "BOSS"); break;
                case "晶凝之華": 晶凝之華 = addCountIntoVar(晶凝之華, temp_count, "BOSS"); break;
                case "魔偶機心": 魔偶機心 = addCountIntoVar(魔偶機心, temp_count, "BOSS"); break;
                case "恒常機關之心": 恒常機關之心 = addCountIntoVar(恒常機關之心, temp_count, "BOSS"); break;
                case "陰燃之珠": 陰燃之珠 = addCountIntoVar(陰燃之珠, temp_count, "BOSS"); break;
                //add in 20210910
                case "雷霆數珠": 雷霆數珠 = addCountIntoVar(雷霆數珠, temp_count, "BOSS"); break;
                case "排異之露": 排異之露 = addCountIntoVar(排異之露, temp_count, "BOSS"); break;
                //add in 20220105
                case "獸境王器": 獸境王器 = addCountIntoVar(獸境王器, temp_count, "BOSS"); break;
                case "龍嗣偽鰭": 龍嗣偽鰭 = addCountIntoVar(龍嗣偽鰭, temp_count, "BOSS"); break;
                //add in 20220331
                case "符紋之齒": 符紋之齒 = addCountIntoVar(符紋之齒, temp_count, "BOSS"); break;
                //add in 20220716
                case "蕈王鉤喙": 蕈王鉤喙 = addCountIntoVar(蕈王鉤喙, temp_count, "BOSS"); break;
                case "藏雷野實": 藏雷野實 = addCountIntoVar(藏雷野實, temp_count, "BOSS"); break;
                //add in 20220831
                case "永續機芯": 永續機芯 = addCountIntoVar(永續機芯, temp_count, "BOSS"); break;
                case "導光四面體": 導光四面體 = addCountIntoVar(導光四面體, temp_count, "BOSS"); break;
                //add in 20221001
                case "滅諍草蔓": 滅諍草蔓 = addCountIntoVar(滅諍草蔓, temp_count, "BOSS"); break;
                //add in 20230115
                case "蒼礫蕊羽": 蒼礫蕊羽 = addCountIntoVar(蒼礫蕊羽, temp_count, "BOSS"); break;
            }

            /** LOCAL */
            switch (temp_item.get(3)) {
                case "小燈草": 小燈草 = addCountIntoVar(小燈草, temp_count, "LOCAL"); break;
                case "慕風蘑菇": 慕風蘑菇 = addCountIntoVar(慕風蘑菇, temp_count, "LOCAL"); break;
                case "夜泊石": 夜泊石 = addCountIntoVar(夜泊石, temp_count, "LOCAL"); break;
                case "風車菊": 風車菊 = addCountIntoVar(風車菊, temp_count, "LOCAL"); break;
                case "石珀": 石珀 = addCountIntoVar(石珀, temp_count, "LOCAL"); break;
                case "蒲公英籽": 蒲公英籽 = addCountIntoVar(蒲公英籽, temp_count, "LOCAL"); break;
                case "嘟嘟蓮": 嘟嘟蓮 = addCountIntoVar(嘟嘟蓮, temp_count, "LOCAL"); break;
                case "落落莓": 落落莓 = addCountIntoVar(落落莓, temp_count, "LOCAL"); break;
                case "琉璃百合": 琉璃百合 = addCountIntoVar(琉璃百合, temp_count, "LOCAL"); break;
                case "琉璃袋": 琉璃袋 = addCountIntoVar(琉璃袋, temp_count, "LOCAL"); break;
                case "鉤鉤果": 鉤鉤果 = addCountIntoVar(鉤鉤果, temp_count, "LOCAL"); break;
                case "塞西莉亞花": 塞西莉亞花 = addCountIntoVar(塞西莉亞花, temp_count, "LOCAL"); break;
                case "絕雲椒椒": 絕雲椒椒 = addCountIntoVar(絕雲椒椒, temp_count, "LOCAL"); break;
                case "霓裳花": 霓裳花 = addCountIntoVar(霓裳花, temp_count, "LOCAL"); break;
                case "星螺": 星螺 = addCountIntoVar(星螺, temp_count, "LOCAL"); break;
                case "清心": 清心 = addCountIntoVar(清心, temp_count, "LOCAL"); break;
                case "海靈芝": 海靈芝 = addCountIntoVar(海靈芝, temp_count, "LOCAL"); break;
                case "緋櫻繡球": 緋櫻繡球 = addCountIntoVar(緋櫻繡球, temp_count, "LOCAL"); break;
                case "鳴草": 鳴草 = addCountIntoVar(鳴草, temp_count, "LOCAL"); break;
                case "晶化骨髓": 晶化骨髓 = addCountIntoVar(晶化骨髓, temp_count, "LOCAL"); break;
                //add in 20210910
                case "天雲草實": 天雲草實 = addCountIntoVar(天雲草實, temp_count, "LOCAL"); break;
                case "血斛": 血斛 = addCountIntoVar(血斛, temp_count, "LOCAL"); break;
                case "幽燈蕈": 幽燈蕈 = addCountIntoVar(幽燈蕈, temp_count, "LOCAL"); break;
                case "珊瑚真珠": 珊瑚真珠 = addCountIntoVar(珊瑚真珠, temp_count, "LOCAL"); break;
                case "鬼兜蟲": 鬼兜蟲 = addCountIntoVar(鬼兜蟲, temp_count, "LOCAL"); break;
                //add in 20220716
                case "樹王聖體菇": 樹王聖體菇 = addCountIntoVar(樹王聖體菇, temp_count, "LOCAL"); break;
                case "劫波蓮": 劫波蓮 = addCountIntoVar(劫波蓮, temp_count, "LOCAL"); break;
                case "月蓮": 月蓮 = addCountIntoVar(月蓮, temp_count, "LOCAL"); break;
                //add in 20220731
                case "赤念果": 赤念果 = addCountIntoVar(赤念果, temp_count, "LOCAL"); break;
                case "聖金蟲": 聖金蟲 = addCountIntoVar(聖金蟲, temp_count, "LOCAL"); break;
                case "帕蒂沙蘭": 帕蒂沙蘭 = addCountIntoVar(帕蒂沙蘭, temp_count, "LOCAL"); break;
                //add in 20230115
                case "沙脂蛹": 沙脂蛹 = addCountIntoVar(沙脂蛹, temp_count, "LOCAL"); break;
            }

        }

        if(temp_item.size() > 3){
            /** T-COMMON -> USING temp_count's pos 1-3*/
            switch (temp_item.get(2)) {
                case "歷戰的箭簇": addCountIntoVar(歷戰的箭簇, temp_count, "T-COMMON"); break;
                case "禁咒繪卷": addCountIntoVar(禁咒繪卷, temp_count, "T-COMMON"); break;
                case "攫金鴉印": addCountIntoVar(攫金鴉印, temp_count, "T-COMMON"); break;
                case "不祥的面具": addCountIntoVar(不祥的面具, temp_count, "T-COMMON"); break;
                case "尉官的徽記": addCountIntoVar(尉官的徽記, temp_count, "T-COMMON"); break;
                case "原素花蜜": addCountIntoVar(原素花蜜, temp_count, "T-COMMON"); break;
                case "史萊姆原漿": addCountIntoVar(史萊姆原漿, temp_count, "T-COMMON"); break;
                case "名刀鐔": addCountIntoVar(名刀鐔, temp_count, "T-COMMON"); break;
                //add in 20210910
                case "浮游晶化核": addCountIntoVar(浮游晶化核, temp_count, "T-COMMON"); break;
                //add in 20220716
                case "孢囊晶塵": addCountIntoVar(孢囊晶塵, temp_count, "pseudo_stamensT-COMMON"); break;
                case "織金紅綢": addCountIntoVar(織金紅綢, temp_count, "T-COMMON"); break;
            }

            /** T-BOOK -> USING temp_count's pos 0-2 */
            switch (temp_item.get(1)) {
                case "「未知1」的哲學": addCountIntoVar(未知1_的哲學, temp_count, "T-BOOK"); break;
                case "「未知2」的哲學": addCountIntoVar(未知2_的哲學, temp_count, "T-BOOK"); break;
                case "「未知3」的哲學": addCountIntoVar(未知3_的哲學, temp_count, "T-BOOK"); break;
                case "「自由」的哲學": addCountIntoVar(自由_的哲學, temp_count, "T-BOOK"); break;
                case "「黃金」的哲學": addCountIntoVar(黃金_的哲學, temp_count, "T-BOOK"); break;
                case "「抗爭」的哲學": addCountIntoVar(抗爭_的哲學, temp_count, "T-BOOK"); break;
                case "「勤勞」的哲學": addCountIntoVar(勤勞_的哲學, temp_count, "T-BOOK"); break;
                case "「詩文」的哲學": addCountIntoVar(詩文_的哲學, temp_count, "T-BOOK"); break;
                case "「繁榮」的哲學": addCountIntoVar(繁榮_的哲學, temp_count, "T-BOOK"); break;
                case "「風雅」的哲學": addCountIntoVar(風雅_的哲學, temp_count, "T-BOOK"); break;
                case "「浮世」的哲學": addCountIntoVar(浮世_的哲學, temp_count, "T-BOOK"); break;
                case "「天光」的哲學": addCountIntoVar(天光_的哲學, temp_count, "T-BOOK"); break;
                //add in 20220823
                case "「諍言」的哲學": addCountIntoVar(諍言_的哲學, temp_count, "T-BOOK"); break;
                case "「巧思」的哲學": addCountIntoVar(巧思_的哲學, temp_count, "T-BOOK"); break;
                case "「篤行」的哲學": addCountIntoVar(篤行_的哲學, temp_count, "T-BOOK"); break;
            }

            /** T-BOSS -> USING temp_count's pos 3 */
            // SPECIAL CASE ONLY
            switch (temp_item.get(3)) {
                case "未知周本BOSS跌落物1": 未知周本BOSS跌落物1 = addCountIntoVar(未知周本BOSS跌落物1, temp_count, "T-BOSS"); break;
                case "未知周本BOSS跌落物2": 未知周本BOSS跌落物2 = addCountIntoVar(未知周本BOSS跌落物2, temp_count, "T-BOSS"); break;
                case "未知周本BOSS跌落物3": 未知周本BOSS跌落物3 = addCountIntoVar(未知周本BOSS跌落物3, temp_count, "T-BOSS"); break;

                // NORMAL
                case "北風之環": 北風之環 = addCountIntoVar(北風之環, temp_count, "T-BOSS"); break;
                case "東風的吐息": 東風的吐息 = addCountIntoVar(東風的吐息, temp_count, "T-BOSS"); break;
                case "東風之翎": 東風之翎 = addCountIntoVar(東風之翎, temp_count, "T-BOSS"); break;
                case "北風的魂匣": 北風的魂匣 = addCountIntoVar(北風的魂匣, temp_count, "T-BOSS"); break;
                case "東風之爪": 東風之爪 = addCountIntoVar(東風之爪, temp_count, "T-BOSS"); break;
                case "北風之尾": 北風之尾 = addCountIntoVar(北風之尾, temp_count, "T-BOSS"); break;
                case "魔王之刃·殘片": 魔王之刃_殘片 = addCountIntoVar(魔王之刃_殘片, temp_count, "T-BOSS"); break;
                case "吞天之鯨·只角": 吞天之鯨_只角 = addCountIntoVar(吞天之鯨_只角, temp_count, "T-BOSS"); break;
                case "武煉之魂·孤影": 武煉之魂_孤影 = addCountIntoVar(武煉之魂_孤影, temp_count, "T-BOSS"); break;
                case "龍王之冕": 龍王之冕 = addCountIntoVar(龍王之冕, temp_count, "T-BOSS"); break;
                case "血玉之枝": 血玉之枝 = addCountIntoVar(血玉之枝, temp_count, "T-BOSS"); break;
                case "鎏金之鱗": 鎏金之鱗 = addCountIntoVar(鎏金之鱗, temp_count, "T-BOSS"); break;
                //add in 20210910
                case "熔毀之刻": 熔毀之刻 = addCountIntoVar(熔毀之刻, temp_count, "T-BOSS"); break;
                case "灰燼之心": 灰燼之心 = addCountIntoVar(灰燼之心, temp_count, "T-BOSS"); break;
                case "獄火之蝶": 獄火之蝶 = addCountIntoVar(獄火之蝶, temp_count, "T-BOSS"); break;
                //add in 20220106
                case "萬劫之真意": 萬劫之真意 = addCountIntoVar(萬劫之真意, temp_count, "T-BOSS"); break;
                case "凶將之手眼": 凶將之手眼 = addCountIntoVar(凶將之手眼, temp_count, "T-BOSS"); break;
                case "禍神之禊淚": 禍神之禊淚 = addCountIntoVar(禍神之禊淚, temp_count, "T-BOSS"); break;
                // add in 20221112
                case "傀儡的懸絲": 傀儡的懸絲 = addCountIntoVar(傀儡的懸絲, temp_count, "T-BOSS"); break;
                case "無心的淵鏡": 無心的淵鏡 = addCountIntoVar(無心的淵鏡, temp_count, "T-BOSS"); break;
                case "空行的虛鈴": 空行的虛鈴 = addCountIntoVar(空行的虛鈴, temp_count, "T-BOSS"); break;
            }

        }

    }


    /**EDIT WHEN ADD NEW ITEMS*/
    public void FindWeaponItemByName(ArrayList<String> temp_item, ArrayList<Integer> temp_count){

        /** COPY1 -> */
        switch (temp_item.get(1)) {
            case "漆黑隕鐵的一塊": addCountIntoVar(漆黑隕鐵的一塊, temp_count, "COPY1"); break;
            case "鳴神御靈的勇武": addCountIntoVar(鳴神御靈的勇武, temp_count, "COPY1"); break;
            case "遠海夷地的金枝": addCountIntoVar(遠海夷地的金枝, temp_count, "COPY1"); break;
            case "凜風奔狼的懷鄉": addCountIntoVar(凜風奔狼的懷鄉, temp_count, "COPY1"); break;
            case "高塔孤王的碎夢": addCountIntoVar(高塔孤王的碎夢, temp_count, "COPY1"); break;
            case "霧海雲間的轉還": addCountIntoVar(霧海雲間的轉還, temp_count, "COPY1"); break;
            case "獅牙鬥士的理想": addCountIntoVar(獅牙鬥士的理想, temp_count, "COPY1"); break;
            case "孤雲寒林的神體": addCountIntoVar(孤雲寒林的神體, temp_count, "COPY1"); break;
            case "今昔劇畫的鬼人": addCountIntoVar(今昔劇畫的鬼人, temp_count, "COPY1"); break;
            case "謐林涓露的金符": addCountIntoVar(謐林涓露的金符, temp_count, "COPY1"); break;
            case "綠洲花園的真諦": addCountIntoVar(綠洲花園的真諦, temp_count, "COPY1"); break;
            case "烈日威權的舊日": addCountIntoVar(烈日威權的舊日, temp_count, "COPY1"); break;
        }

        /** COPY2 -> */
        switch (temp_item.get(2)) {
            case "混沌真眼": addCountIntoVar(混沌真眼, temp_count, "COPY2"); break;
            case "混沌爐心": addCountIntoVar(混沌爐心, temp_count, "COPY2"); break;
            case "石化的骨片": addCountIntoVar(石化的骨片, temp_count, "COPY2"); break;
            case "霧虛燈芯": addCountIntoVar(霧虛燈芯, temp_count, "COPY2"); break;
            case "督察長祭刀": addCountIntoVar(督察長祭刀, temp_count, "COPY2"); break;
            case "黑晶號角": addCountIntoVar(黑晶號角, temp_count, "COPY2"); break;
            case "地脈的新芽": addCountIntoVar(地脈的新芽, temp_count, "COPY2"); break;
            case "偏光棱鏡": addCountIntoVar(偏光棱鏡, temp_count, "COPY2"); break;
            //add in 20211030
            case "隱獸鬼爪": addCountIntoVar(隱獸鬼爪, temp_count, "COPY2"); break;
            //add in 20220823
            case "茁壯菌核": addCountIntoVar(茁壯菌核, temp_count, "COPY2"); break;
            case "混沌錨栓": addCountIntoVar(混沌錨栓, temp_count, "COPY2"); break;
            case "輝光稜晶": addCountIntoVar(輝光稜晶, temp_count, "COPY2"); break;
            //add in 20230225
            case "鍥紋的橫脊": addCountIntoVar(鍥紋的橫脊, temp_count, "COPY2"); break;
        }


        /** COMMON -> */
        switch (temp_item.get(3)) {
            case "歷戰的箭簇": addCountIntoVar(歷戰的箭簇, temp_count, "W-COMMON"); break;
            case "禁咒繪卷": addCountIntoVar(禁咒繪卷, temp_count, "W-COMMON"); break;
            case "攫金鴉印": addCountIntoVar(攫金鴉印, temp_count, "W-COMMON"); break;
            case "不祥的面具": addCountIntoVar(不祥的面具, temp_count, "W-COMMON"); break;
            case "尉官的徽記": addCountIntoVar(尉官的徽記, temp_count, "W-COMMON"); break;
            case "原素花蜜": addCountIntoVar(原素花蜜, temp_count, "W-COMMON"); break;
            case "史萊姆原漿": addCountIntoVar(史萊姆原漿, temp_count, "W-COMMON"); break;
            case "名刀鐔": addCountIntoVar(名刀鐔, temp_count, "W-COMMON"); break;
            //add in 20210910
            case "浮游晶化核": addCountIntoVar(浮游晶化核, temp_count, "W-COMMON"); break;
            //add in 20220716
            case "孢囊晶塵": addCountIntoVar(孢囊晶塵, temp_count, "W-COMMON"); break;
            case "織金紅綢": addCountIntoVar(織金紅綢, temp_count, "W-COMMON"); break;
        }
    }

    /**
     * @param ITEM -> ArrayList of item STORE VAR
     * @param temp_count -> ArrayList
     * @param XPR -> TYPE -> TELL FUN Total pos have in the ITEM
     */
    public void addCountIntoVar(ArrayList<Integer> ITEM, ArrayList<Integer> temp_count, String XPR){
        switch (XPR) {
            case "CRYSTAL":
                for (int x = 0; x < 4; x++) { ITEM.set(x, ITEM.get(x) + temp_count.get(x));} break;
            case "COMMON":
                for (int x = 0; x < 3; x++) { ITEM.set(x, ITEM.get(x) + temp_count.get(x + 5));} break;
            case "T-BOOK":
                for (int x = 0; x < 3; x++) { ITEM.set(x, ITEM.get(x) + temp_count.get(x));} break;
            case "T-COMMON":
                for (int x = 0; x < 3; x++) { ITEM.set(x, ITEM.get(x) + temp_count.get(x + 3));} break;
            case "COPY1":
                for (int x = 0; x < 4; x++) { ITEM.set(x, ITEM.get(x) + temp_count.get(x));} break;
            case "COPY2":
                for (int x = 0; x < 3; x++) { ITEM.set(x, ITEM.get(x) + temp_count.get(x + 4));} break;
            case "W-COMMON":
                for (int x = 0; x < 3; x++) { ITEM.set(x, ITEM.get(x) + temp_count.get(x + 7));} break;
        }
    }

    /**
     *
     * @param ITEM -> Integer of item STORE VAR
     * @param temp_count -> ArrayList
     * @param XPR -> TYPE -> TELL FUN Total pos have in the ITEM
     */
    public int addCountIntoVar(int ITEM, ArrayList<Integer> temp_count, String XPR){
        switch (XPR) {
            case "BOSS": ITEM = ITEM + temp_count.get(8); return ITEM;
            case "T-BOSS": ITEM = ITEM + temp_count.get(6); return ITEM;
            case "LOCAL": ITEM = ITEM + temp_count.get(4); return ITEM;
            case "COMMON": ITEM = ITEM + temp_count.get(5); return ITEM;
        }

        return ITEM;
    }


    /**
     * For Character Info
     *
     */
    public String[] getCharCrystalListByItemName (String str){
        switch (str){
            case "燃願瑪瑙" : return new String[]{"燃願瑪瑙碎屑","燃願瑪瑙斷片","燃願瑪瑙塊","燃願瑪瑙"};
            case "滌淨青金" : return new String[]{"滌淨青金碎屑","滌淨青金斷片","滌淨青金塊","滌淨青金"};
            case "最勝紫晶" : return new String[]{"最勝紫晶碎屑","最勝紫晶斷片","最勝紫晶塊","最勝紫晶"};
            case "哀敘冰玉" : return new String[]{"哀敘冰玉碎屑","哀敘冰玉斷片","哀敘冰玉塊","哀敘冰玉"};
            case "自在松石" : return new String[]{"自在松石碎屑","自在松石斷片","自在松石塊","自在松石"};
            case "堅牢黃玉" : return new String[]{"堅牢黃玉碎屑","堅牢黃玉斷片","堅牢黃玉塊","堅牢黃玉"};
            case "生長碧翡" : return new String[]{"生長碧翡碎屑","生長碧翡斷片","生長碧翡塊","生長碧翡"};
            case "璀璨原鑽" : return new String[]{"璀璨原鑽碎屑","璀璨原鑽斷片","璀璨原鑽塊","璀璨原鑽"};

            default: return new String[]{"N/A","N/A","N/A","N/A"};
        }
    }
    public String[] getCharCommonListByItemName (String str){
        switch (str){
            case "歷戰的箭簇" : return new String[]{"牢固的箭簇","銳利的箭簇","歷戰的箭簇"};
            case "禁咒繪卷" : return new String[]{"導能繪卷","封魔繪卷","禁咒繪卷"};
            case "攫金鴉印" : return new String[]{"尋寶鴉印","藏銀鴉印","攫金鴉印"};
            case "不祥的面具" : return new String[]{"破損的面具","污穢的面具","不祥的面具"};
            case "尉官的徽記" : return new String[]{"新兵的徽記","士官的徽記","尉官的徽記"};
            case "原素花蜜" : return new String[]{"騙騙花蜜","微光花蜜","原素花蜜"};
            case "史萊姆原漿" : return new String[]{"史萊姆凝液","史萊姆清","史萊姆原漿"};
            case "名刀鐔" : return new String[]{"破舊的刀鐔","影打刀鐔","名刀鐔"};
            case "浮游晶化核" : return new String[]{"浮游乾核","浮游幽核","浮游晶化核"};
            case "孢囊晶塵" : return new String[]{"蕈獸孢子","螢光孢粉","孢囊晶塵"};
            case "織金紅綢" : return new String[]{"褪色紅綢","鑲邊紅綢","織金紅綢"};

            default: return new String[]{"N/A","N/A","N/A"};
        }
    }
    public String[] getBookListByItemName (String str){
        switch (str){
            case "「自由」的哲學" : return new String[]{"「自由」的教導","「自由」的指引","「自由」的哲學"};
            case "「黃金」的哲學" : return new String[]{"「黃金」的教導","「黃金」的指引","「黃金」的哲學"};
            case "「抗爭」的哲學" : return new String[]{"「抗爭」的教導","「抗爭」的指引","「抗爭」的哲學"};
            case "「繁榮」的哲學" : return new String[]{"「繁榮」的教導","「繁榮」的指引","「繁榮」的哲學"};
            case "「詩文」的哲學" : return new String[]{"「詩文」的教導","「詩文」的指引","「詩文」的哲學"};
            case "「勤勞」的哲學" : return new String[]{"「勤勞」的教導","「勤勞」的指引","「勤勞」的哲學"};
            case "「風雅」的哲學" : return new String[]{"「風雅」的教導","「風雅」的指引","「風雅」的哲學"};
            case "「浮世」的哲學" : return new String[]{"「浮世」的教導","「浮世」的指引","「浮世」的哲學"};
            case "「天光」的哲學" : return new String[]{"「天光」的教導","「天光」的指引","「天光」的哲學"};
            case "「諍言」的哲學" : return new String[]{"「諍言」的教導","「諍言」的指引","「諍言」的哲學"};
            case "「巧思」的哲學" : return new String[]{"「巧思」的教導","「巧思」的指引","「巧思」的哲學"};
            case "「篤行」的哲學" : return new String[]{"「篤行」的教導","「篤行」的指引","「篤行」的哲學"};

            default: return new String[]{"N/A","N/A","N/A"};
        }
    }

    /**
     * For Weapon Info
     */
    public String[] getWeaponLocal1ListByItemName (String str){
        switch (str){
            case "漆黑隕鐵的一塊" : return new String[]{"漆黑隕鐵的一粒","漆黑隕鐵的一片","漆黑隕鐵的一角","漆黑隕鐵的一塊"};
            case "鳴神御靈的勇武" : return new String[]{"鳴神御靈的明惠","鳴神御靈的歡喜","鳴神御靈的親愛","鳴神御靈的勇武"};
            case "遠海夷地的金枝" : return new String[]{"遠海夷地的瑚枝","遠海夷地的玉枝","遠海夷地的瓊枝","遠海夷地的金枝"};
            case "凜風奔狼的懷鄉" : return new String[]{"凜風奔狼的始齔","凜風奔狼的裂齒","凜風奔狼的斷牙","凜風奔狼的懷鄉"};
            case "高塔孤王的碎夢" : return new String[]{"高塔孤王的破瓦","高塔孤王的殘垣","高塔孤王的斷片","高塔孤王的碎夢"};
            case "霧海雲間的轉還" : return new String[]{"霧海雲間的鉛丹","霧海雲間的汞丹","霧海雲間的金丹","霧海雲間的轉還"};
            case "獅牙鬥士的理想" : return new String[]{"獅牙鬥士的枷鎖","獅牙鬥士的鐵鍊","獅牙鬥士的鐐銬","獅牙鬥士的理想"};
            case "孤雲寒林的神體" : return new String[]{"孤雲寒林的光砂","孤雲寒林的輝岩","孤雲寒林的聖骸","孤雲寒林的神體"};
            case "今昔劇畫的鬼人" : return new String[]{"今昔劇畫的惡尉","今昔劇畫的虎囓","今昔劇畫的一角","今昔劇畫的鬼人"};
            case "謐林涓露的金符" : return new String[]{"謐林涓露的銅符", "謐林涓露的鐵符", "謐林涓露的銀符", "謐林涓露的金符"};
            case "綠洲花園的真諦" : return new String[]{"綠洲花園的追憶","綠洲花園的恩惠","綠洲花園的哀思","綠洲花園的真諦"};
            case "烈日威權的舊日" : return new String[]{"烈日威權的殘響","烈日威權的餘光","烈日威權的夢想","烈日威權的舊日"};

            default: return new String[]{"N/A","N/A","N/A","N/A"};
        }
    }
    public String[] getWeaponLocal2ListByItemName (String str){
        switch (str){
            case "混沌真眼" : return new String[]{"混沌機關","混沌樞紐","混沌真眼"};
            case "混沌爐心" : return new String[]{"混沌裝置","混沌迴路","混沌爐心"};
            case "石化的骨片" : return new String[]{"脆弱的骨片","結實的骨片","石化的骨片"};
            case "霧虛燈芯" : return new String[]{"霧虛草囊","霧虛草囊","霧虛燈芯"};
            case "督察長祭刀" : return new String[]{"獵兵祭刀","特工祭刀","督察長祭刀"};
            case "黑晶號角" : return new String[]{"沉重號角","黑銅號角","黑晶號角"};
            case "地脈的新芽" : return new String[]{"地脈的舊枝","地脈的枯葉","地脈的新芽"};
            case "偏光棱鏡" : return new String[]{"黯淡棱鏡","水晶棱鏡","偏光棱鏡"};
            case "隱獸鬼爪" : return new String[]{"隱獸指爪","隱獸利爪","隱獸鬼爪"};
            case "茁壯菌核" : return new String[]{"失活菌核","休眠菌核","茁壯菌核"};
            case "混沌錨栓" : return new String[]{"混沌容器","混沌模組","混沌錨栓"};
            case "輝光稜晶" : return new String[]{"破缺稜晶","混濁稜晶","輝光稜晶"};
            case "鍥紋的橫脊" : return new String[]{"殘毀的橫脊","密固的橫脊","鍥紋的橫脊"};
            default: return new String[]{"N/A","N/A","N/A"};
        }
    }
    public String[] getWeaponCommonListByItemName (String str){
        switch (str){
            case "歷戰的箭簇" : return new String[]{"牢固的箭簇","銳利的箭簇","歷戰的箭簇"};
            case "禁咒繪卷" : return new String[]{"導能繪卷","封魔繪卷","禁咒繪卷"};
            case "攫金鴉印" : return new String[]{"尋寶鴉印","藏銀鴉印","攫金鴉印"};
            case "不祥的面具" : return new String[]{"破損的面具","污穢的面具","不祥的面具"};
            case "尉官的徽記" : return new String[]{"新兵的徽記","士官的徽記","尉官的徽記"};
            case "原素花蜜" : return new String[]{"騙騙花蜜","微光花蜜","原素花蜜"};
            case "史萊姆原漿" : return new String[]{"史萊姆凝液","史萊姆清","史萊姆原漿"};
            case "名刀鐔" : return new String[]{"破舊的刀鐔","影打刀鐔","名刀鐔"};
            case "浮游晶化核" : return new String[]{"浮游乾核","浮游幽核","浮游晶化核"};
            case "孢囊晶塵" : return new String[]{"蕈獸孢子","螢光孢粉","孢囊晶塵"};
            case "織金紅綢" : return new String[]{"褪色紅綢","鑲邊紅綢","織金紅綢"};

            default: return new String[]{"N/A","N/A","N/A"};
        }
    }
}