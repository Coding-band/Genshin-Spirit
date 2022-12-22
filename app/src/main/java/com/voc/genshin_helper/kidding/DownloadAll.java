package com.voc.genshin_helper.kidding;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;

import com.squareup.picasso.Picasso;
import com.voc.genshin_helper.util.DownloadTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

public class DownloadAll {

    String[] url = {"https://genshin.honeyhunterworld.com/img/i_n330000_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330001_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330002_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330003_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330004_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330005_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330006_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330007_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330008_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330010_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330011_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330012_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330013_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330014_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330015_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330016_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330017_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330018_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330019_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330020_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330021_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330022_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330023_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330024_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330025_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330026_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330027_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330500_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330501_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330502_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330503_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330504_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330505_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330506_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330507_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330508_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330510_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330511_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330512_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330513_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330514_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330515_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330516_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330517_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330518_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330519_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330520_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330521_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330522_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330523_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330524_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330525_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330526_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n330527_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331000_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331001_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331002_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331003_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331004_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331005_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331006_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331007_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331008_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331009_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331010_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331011_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331012_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331013_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331014_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331015_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331016_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331017_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331018_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331019_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331020_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331021_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331022_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331023_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331024_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331025_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331026_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331027_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331028_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331029_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331030_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331031_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331032_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331033_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n331034_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n332000_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n332001_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n332002_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n332003_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n332004_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n332005_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n332006_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n332007_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n332008_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n332009_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n332010_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n332011_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n332012_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n332013_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n332014_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n332015_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n332016_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n332017_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n332018_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n332019_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333000_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333001_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333002_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333003_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333004_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333005_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333006_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333007_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333008_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333009_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333010_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333011_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333012_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333013_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333014_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333015_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333016_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333017_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333018_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333019_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333020_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333021_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333022_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333023_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333024_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333025_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333026_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333027_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333028_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333029_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333030_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333031_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333032_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333033_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333034_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333035_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n333036_gcg_high_resolution.webp",
            "https://genshin.honeyhunterworld.com/img/i_n339000.webp",
            "https://genshin.honeyhunterworld.com/img/i_n339001.webp",
            "https://genshin.honeyhunterworld.com/img/i_n339002.webp",
            "https://genshin.honeyhunterworld.com/img/i_n339003.webp",
            "https://genshin.honeyhunterworld.com/img/i_n339004.webp",
            "https://genshin.honeyhunterworld.com/img/i_n339009.webp",
            "https://genshin.honeyhunterworld.com/img/i_n339010.webp",
            "https://genshin.honeyhunterworld.com/img/i_n339011.webp",
            "https://genshin.honeyhunterworld.com/img/i_n339012.webp",
            "https://genshin.honeyhunterworld.com/img/i_n339013.webp",
            "https://genshin.honeyhunterworld.com/img/i_n339014.webp",
            "https://genshin.honeyhunterworld.com/img/i_n339015.webp",
            "https://genshin.honeyhunterworld.com/img/i_n339016.webp",
            "https://genshin.honeyhunterworld.com/img/i_n339017.webp",
            "https://genshin.honeyhunterworld.com/img/i_n339018.webp",
            "https://genshin.honeyhunterworld.com/img/i_n339019.webp",
            "https://genshin.honeyhunterworld.com/img/i_n339020.webp"};

    String[] names = {"i_n330000_gcg_high_resolution.webp",
            "i_n330001_gcg_high_resolution.webp",
            "i_n330002_gcg_high_resolution.webp",
            "i_n330003_gcg_high_resolution.webp",
            "i_n330004_gcg_high_resolution.webp",
            "i_n330005_gcg_high_resolution.webp",
            "i_n330006_gcg_high_resolution.webp",
            "i_n330007_gcg_high_resolution.webp",
            "i_n330008_gcg_high_resolution.webp",
            "i_n330010_gcg_high_resolution.webp",
            "i_n330011_gcg_high_resolution.webp",
            "i_n330012_gcg_high_resolution.webp",
            "i_n330013_gcg_high_resolution.webp",
            "i_n330014_gcg_high_resolution.webp",
            "i_n330015_gcg_high_resolution.webp",
            "i_n330016_gcg_high_resolution.webp",
            "i_n330017_gcg_high_resolution.webp",
            "i_n330018_gcg_high_resolution.webp",
            "i_n330019_gcg_high_resolution.webp",
            "i_n330020_gcg_high_resolution.webp",
            "i_n330021_gcg_high_resolution.webp",
            "i_n330022_gcg_high_resolution.webp",
            "i_n330023_gcg_high_resolution.webp",
            "i_n330024_gcg_high_resolution.webp",
            "i_n330025_gcg_high_resolution.webp",
            "i_n330026_gcg_high_resolution.webp",
            "i_n330027_gcg_high_resolution.webp",
            "i_n330500_gcg_high_resolution.webp",
            "i_n330501_gcg_high_resolution.webp",
            "i_n330502_gcg_high_resolution.webp",
            "i_n330503_gcg_high_resolution.webp",
            "i_n330504_gcg_high_resolution.webp",
            "i_n330505_gcg_high_resolution.webp",
            "i_n330506_gcg_high_resolution.webp",
            "i_n330507_gcg_high_resolution.webp",
            "i_n330508_gcg_high_resolution.webp",
            "i_n330510_gcg_high_resolution.webp",
            "i_n330511_gcg_high_resolution.webp",
            "i_n330512_gcg_high_resolution.webp",
            "i_n330513_gcg_high_resolution.webp",
            "i_n330514_gcg_high_resolution.webp",
            "i_n330515_gcg_high_resolution.webp",
            "i_n330516_gcg_high_resolution.webp",
            "i_n330517_gcg_high_resolution.webp",
            "i_n330518_gcg_high_resolution.webp",
            "i_n330519_gcg_high_resolution.webp",
            "i_n330520_gcg_high_resolution.webp",
            "i_n330521_gcg_high_resolution.webp",
            "i_n330522_gcg_high_resolution.webp",
            "i_n330523_gcg_high_resolution.webp",
            "i_n330524_gcg_high_resolution.webp",
            "i_n330525_gcg_high_resolution.webp",
            "i_n330526_gcg_high_resolution.webp",
            "i_n330527_gcg_high_resolution.webp",
            "i_n331000_gcg_high_resolution.webp",
            "i_n331001_gcg_high_resolution.webp",
            "i_n331002_gcg_high_resolution.webp",
            "i_n331003_gcg_high_resolution.webp",
            "i_n331004_gcg_high_resolution.webp",
            "i_n331005_gcg_high_resolution.webp",
            "i_n331006_gcg_high_resolution.webp",
            "i_n331007_gcg_high_resolution.webp",
            "i_n331008_gcg_high_resolution.webp",
            "i_n331009_gcg_high_resolution.webp",
            "i_n331010_gcg_high_resolution.webp",
            "i_n331011_gcg_high_resolution.webp",
            "i_n331012_gcg_high_resolution.webp",
            "i_n331013_gcg_high_resolution.webp",
            "i_n331014_gcg_high_resolution.webp",
            "i_n331015_gcg_high_resolution.webp",
            "i_n331016_gcg_high_resolution.webp",
            "i_n331017_gcg_high_resolution.webp",
            "i_n331018_gcg_high_resolution.webp",
            "i_n331019_gcg_high_resolution.webp",
            "i_n331020_gcg_high_resolution.webp",
            "i_n331021_gcg_high_resolution.webp",
            "i_n331022_gcg_high_resolution.webp",
            "i_n331023_gcg_high_resolution.webp",
            "i_n331024_gcg_high_resolution.webp",
            "i_n331025_gcg_high_resolution.webp",
            "i_n331026_gcg_high_resolution.webp",
            "i_n331027_gcg_high_resolution.webp",
            "i_n331028_gcg_high_resolution.webp",
            "i_n331029_gcg_high_resolution.webp",
            "i_n331030_gcg_high_resolution.webp",
            "i_n331031_gcg_high_resolution.webp",
            "i_n331032_gcg_high_resolution.webp",
            "i_n331033_gcg_high_resolution.webp",
            "i_n331034_gcg_high_resolution.webp",
            "i_n332000_gcg_high_resolution.webp",
            "i_n332001_gcg_high_resolution.webp",
            "i_n332002_gcg_high_resolution.webp",
            "i_n332003_gcg_high_resolution.webp",
            "i_n332004_gcg_high_resolution.webp",
            "i_n332005_gcg_high_resolution.webp",
            "i_n332006_gcg_high_resolution.webp",
            "i_n332007_gcg_high_resolution.webp",
            "i_n332008_gcg_high_resolution.webp",
            "i_n332009_gcg_high_resolution.webp",
            "i_n332010_gcg_high_resolution.webp",
            "i_n332011_gcg_high_resolution.webp",
            "i_n332012_gcg_high_resolution.webp",
            "i_n332013_gcg_high_resolution.webp",
            "i_n332014_gcg_high_resolution.webp",
            "i_n332015_gcg_high_resolution.webp",
            "i_n332016_gcg_high_resolution.webp",
            "i_n332017_gcg_high_resolution.webp",
            "i_n332018_gcg_high_resolution.webp",
            "i_n332019_gcg_high_resolution.webp",
            "i_n333000_gcg_high_resolution.webp",
            "i_n333001_gcg_high_resolution.webp",
            "i_n333002_gcg_high_resolution.webp",
            "i_n333003_gcg_high_resolution.webp",
            "i_n333004_gcg_high_resolution.webp",
            "i_n333005_gcg_high_resolution.webp",
            "i_n333006_gcg_high_resolution.webp",
            "i_n333007_gcg_high_resolution.webp",
            "i_n333008_gcg_high_resolution.webp",
            "i_n333009_gcg_high_resolution.webp",
            "i_n333010_gcg_high_resolution.webp",
            "i_n333011_gcg_high_resolution.webp",
            "i_n333012_gcg_high_resolution.webp",
            "i_n333013_gcg_high_resolution.webp",
            "i_n333014_gcg_high_resolution.webp",
            "i_n333015_gcg_high_resolution.webp",
            "i_n333016_gcg_high_resolution.webp",
            "i_n333017_gcg_high_resolution.webp",
            "i_n333018_gcg_high_resolution.webp",
            "i_n333019_gcg_high_resolution.webp",
            "i_n333020_gcg_high_resolution.webp",
            "i_n333021_gcg_high_resolution.webp",
            "i_n333022_gcg_high_resolution.webp",
            "i_n333023_gcg_high_resolution.webp",
            "i_n333024_gcg_high_resolution.webp",
            "i_n333025_gcg_high_resolution.webp",
            "i_n333026_gcg_high_resolution.webp",
            "i_n333027_gcg_high_resolution.webp",
            "i_n333028_gcg_high_resolution.webp",
            "i_n333029_gcg_high_resolution.webp",
            "i_n333030_gcg_high_resolution.webp",
            "i_n333031_gcg_high_resolution.webp",
            "i_n333032_gcg_high_resolution.webp",
            "i_n333033_gcg_high_resolution.webp",
            "i_n333034_gcg_high_resolution.webp",
            "i_n333035_gcg_high_resolution.webp",
            "i_n333036_gcg_high_resolution.webp",
            "i_n339000_gcg_high_resolution.webp",
            "i_n339001_gcg_high_resolution.webp",
            "i_n339002_gcg_high_resolution.webp",
            "i_n339003_gcg_high_resolution.webp",
            "i_n339004_gcg_high_resolution.webp",
            "i_n339009_gcg_high_resolution.webp",
            "i_n339010_gcg_high_resolution.webp",
            "i_n339011_gcg_high_resolution.webp",
            "i_n339012_gcg_high_resolution.webp",
            "i_n339013_gcg_high_resolution.webp",
            "i_n339014_gcg_high_resolution.webp",
            "i_n339015_gcg_high_resolution.webp",
            "i_n339016_gcg_high_resolution.webp",
            "i_n339017_gcg_high_resolution.webp",
            "i_n339018_gcg_high_resolution.webp",
            "i_n339019_gcg_high_resolution.webp",
            "i_n339020_gcg_high_resolution.webp"};

    String[] xpr = {
            "/cards/i_n330000_gcg_high_resolution.webp",
            "/cards/i_n330001_gcg_high_resolution.webp",
            "/cards/i_n330002_gcg_high_resolution.webp",
            "/cards/i_n330003_gcg_high_resolution.webp",
            "/cards/i_n330004_gcg_high_resolution.webp",
            "/cards/i_n330005_gcg_high_resolution.webp",
            "/cards/i_n330006_gcg_high_resolution.webp",
            "/cards/i_n330007_gcg_high_resolution.webp",
            "/cards/i_n330008_gcg_high_resolution.webp",
            "/cards/i_n330010_gcg_high_resolution.webp",
            "/cards/i_n330011_gcg_high_resolution.webp",
            "/cards/i_n330012_gcg_high_resolution.webp",
            "/cards/i_n330013_gcg_high_resolution.webp",
            "/cards/i_n330014_gcg_high_resolution.webp",
            "/cards/i_n330015_gcg_high_resolution.webp",
            "/cards/i_n330016_gcg_high_resolution.webp",
            "/cards/i_n330017_gcg_high_resolution.webp",
            "/cards/i_n330018_gcg_high_resolution.webp",
            "/cards/i_n330019_gcg_high_resolution.webp",
            "/cards/i_n330020_gcg_high_resolution.webp",
            "/cards/i_n330021_gcg_high_resolution.webp",
            "/cards/i_n330022_gcg_high_resolution.webp",
            "/cards/i_n330023_gcg_high_resolution.webp",
            "/cards/i_n330024_gcg_high_resolution.webp",
            "/cards/i_n330025_gcg_high_resolution.webp",
            "/cards/i_n330026_gcg_high_resolution.webp",
            "/cards/i_n330027_gcg_high_resolution.webp",
            "/cards/i_n330500_gcg_high_resolution.webp",
            "/cards/i_n330501_gcg_high_resolution.webp",
            "/cards/i_n330502_gcg_high_resolution.webp",
            "/cards/i_n330503_gcg_high_resolution.webp",
            "/cards/i_n330504_gcg_high_resolution.webp",
            "/cards/i_n330505_gcg_high_resolution.webp",
            "/cards/i_n330506_gcg_high_resolution.webp",
            "/cards/i_n330507_gcg_high_resolution.webp",
            "/cards/i_n330508_gcg_high_resolution.webp",
            "/cards/i_n330510_gcg_high_resolution.webp",
            "/cards/i_n330511_gcg_high_resolution.webp",
            "/cards/i_n330512_gcg_high_resolution.webp",
            "/cards/i_n330513_gcg_high_resolution.webp",
            "/cards/i_n330514_gcg_high_resolution.webp",
            "/cards/i_n330515_gcg_high_resolution.webp",
            "/cards/i_n330516_gcg_high_resolution.webp",
            "/cards/i_n330517_gcg_high_resolution.webp",
            "/cards/i_n330518_gcg_high_resolution.webp",
            "/cards/i_n330519_gcg_high_resolution.webp",
            "/cards/i_n330520_gcg_high_resolution.webp",
            "/cards/i_n330521_gcg_high_resolution.webp",
            "/cards/i_n330522_gcg_high_resolution.webp",
            "/cards/i_n330523_gcg_high_resolution.webp",
            "/cards/i_n330524_gcg_high_resolution.webp",
            "/cards/i_n330525_gcg_high_resolution.webp",
            "/cards/i_n330526_gcg_high_resolution.webp",
            "/cards/i_n330527_gcg_high_resolution.webp",
            "/cards/i_n331000_gcg_high_resolution.webp",
            "/cards/i_n331001_gcg_high_resolution.webp",
            "/cards/i_n331002_gcg_high_resolution.webp",
            "/cards/i_n331003_gcg_high_resolution.webp",
            "/cards/i_n331004_gcg_high_resolution.webp",
            "/cards/i_n331005_gcg_high_resolution.webp",
            "/cards/i_n331006_gcg_high_resolution.webp",
            "/cards/i_n331007_gcg_high_resolution.webp",
            "/cards/i_n331008_gcg_high_resolution.webp",
            "/cards/i_n331009_gcg_high_resolution.webp",
            "/cards/i_n331010_gcg_high_resolution.webp",
            "/cards/i_n331011_gcg_high_resolution.webp",
            "/cards/i_n331012_gcg_high_resolution.webp",
            "/cards/i_n331013_gcg_high_resolution.webp",
            "/cards/i_n331014_gcg_high_resolution.webp",
            "/cards/i_n331015_gcg_high_resolution.webp",
            "/cards/i_n331016_gcg_high_resolution.webp",
            "/cards/i_n331017_gcg_high_resolution.webp",
            "/cards/i_n331018_gcg_high_resolution.webp",
            "/cards/i_n331019_gcg_high_resolution.webp",
            "/cards/i_n331020_gcg_high_resolution.webp",
            "/cards/i_n331021_gcg_high_resolution.webp",
            "/cards/i_n331022_gcg_high_resolution.webp",
            "/cards/i_n331023_gcg_high_resolution.webp",
            "/cards/i_n331024_gcg_high_resolution.webp",
            "/cards/i_n331025_gcg_high_resolution.webp",
            "/cards/i_n331026_gcg_high_resolution.webp",
            "/cards/i_n331027_gcg_high_resolution.webp",
            "/cards/i_n331028_gcg_high_resolution.webp",
            "/cards/i_n331029_gcg_high_resolution.webp",
            "/cards/i_n331030_gcg_high_resolution.webp",
            "/cards/i_n331031_gcg_high_resolution.webp",
            "/cards/i_n331032_gcg_high_resolution.webp",
            "/cards/i_n331033_gcg_high_resolution.webp",
            "/cards/i_n331034_gcg_high_resolution.webp",
            "/cards/i_n332000_gcg_high_resolution.webp",
            "/cards/i_n332001_gcg_high_resolution.webp",
            "/cards/i_n332002_gcg_high_resolution.webp",
            "/cards/i_n332003_gcg_high_resolution.webp",
            "/cards/i_n332004_gcg_high_resolution.webp",
            "/cards/i_n332005_gcg_high_resolution.webp",
            "/cards/i_n332006_gcg_high_resolution.webp",
            "/cards/i_n332007_gcg_high_resolution.webp",
            "/cards/i_n332008_gcg_high_resolution.webp",
            "/cards/i_n332009_gcg_high_resolution.webp",
            "/cards/i_n332010_gcg_high_resolution.webp",
            "/cards/i_n332011_gcg_high_resolution.webp",
            "/cards/i_n332012_gcg_high_resolution.webp",
            "/cards/i_n332013_gcg_high_resolution.webp",
            "/cards/i_n332014_gcg_high_resolution.webp",
            "/cards/i_n332015_gcg_high_resolution.webp",
            "/cards/i_n332016_gcg_high_resolution.webp",
            "/cards/i_n332017_gcg_high_resolution.webp",
            "/cards/i_n332018_gcg_high_resolution.webp",
            "/cards/i_n332019_gcg_high_resolution.webp",
            "/cards/i_n333000_gcg_high_resolution.webp",
            "/cards/i_n333001_gcg_high_resolution.webp",
            "/cards/i_n333002_gcg_high_resolution.webp",
            "/cards/i_n333003_gcg_high_resolution.webp",
            "/cards/i_n333004_gcg_high_resolution.webp",
            "/cards/i_n333005_gcg_high_resolution.webp",
            "/cards/i_n333006_gcg_high_resolution.webp",
            "/cards/i_n333007_gcg_high_resolution.webp",
            "/cards/i_n333008_gcg_high_resolution.webp",
            "/cards/i_n333009_gcg_high_resolution.webp",
            "/cards/i_n333010_gcg_high_resolution.webp",
            "/cards/i_n333011_gcg_high_resolution.webp",
            "/cards/i_n333012_gcg_high_resolution.webp",
            "/cards/i_n333013_gcg_high_resolution.webp",
            "/cards/i_n333014_gcg_high_resolution.webp",
            "/cards/i_n333015_gcg_high_resolution.webp",
            "/cards/i_n333016_gcg_high_resolution.webp",
            "/cards/i_n333017_gcg_high_resolution.webp",
            "/cards/i_n333018_gcg_high_resolution.webp",
            "/cards/i_n333019_gcg_high_resolution.webp",
            "/cards/i_n333020_gcg_high_resolution.webp",
            "/cards/i_n333021_gcg_high_resolution.webp",
            "/cards/i_n333022_gcg_high_resolution.webp",
            "/cards/i_n333023_gcg_high_resolution.webp",
            "/cards/i_n333024_gcg_high_resolution.webp",
            "/cards/i_n333025_gcg_high_resolution.webp",
            "/cards/i_n333026_gcg_high_resolution.webp",
            "/cards/i_n333027_gcg_high_resolution.webp",
            "/cards/i_n333028_gcg_high_resolution.webp",
            "/cards/i_n333029_gcg_high_resolution.webp",
            "/cards/i_n333030_gcg_high_resolution.webp",
            "/cards/i_n333031_gcg_high_resolution.webp",
            "/cards/i_n333032_gcg_high_resolution.webp",
            "/cards/i_n333033_gcg_high_resolution.webp",
            "/cards/i_n333034_gcg_high_resolution.webp",
            "/cards/i_n333035_gcg_high_resolution.webp",
            "/cards/i_n333036_gcg_high_resolution.webp",
            "/cards/i_n339000_gcg_high_resolution.webp",
            "/cards/i_n339001_gcg_high_resolution.webp",
            "/cards/i_n339002_gcg_high_resolution.webp",
            "/cards/i_n339003_gcg_high_resolution.webp",
            "/cards/i_n339004_gcg_high_resolution.webp",
            "/cards/i_n339009_gcg_high_resolution.webp",
            "/cards/i_n339010_gcg_high_resolution.webp",
            "/cards/i_n339011_gcg_high_resolution.webp",
            "/cards/i_n339012_gcg_high_resolution.webp",
            "/cards/i_n339013_gcg_high_resolution.webp",
            "/cards/i_n339014_gcg_high_resolution.webp",
            "/cards/i_n339015_gcg_high_resolution.webp",
            "/cards/i_n339016_gcg_high_resolution.webp",
            "/cards/i_n339017_gcg_high_resolution.webp",
            "/cards/i_n339018_gcg_high_resolution.webp",
            "/cards/i_n339019_gcg_high_resolution.webp",
            "/cards/i_n339020_gcg_high_resolution.webp"
    };

    public void download(Context context, Activity activity){
        ArrayList<String> myList = new ArrayList<String>(Arrays.asList(url));
        ArrayList<String> myList2 = new ArrayList<String>(Arrays.asList(names));
        ArrayList<String> xpr2 = new ArrayList<String>(Arrays.asList(xpr));
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.startA(myList,myList2,xpr2,context,activity);
    }
}
