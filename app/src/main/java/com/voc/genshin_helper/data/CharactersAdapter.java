package com.voc.genshin_helper.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.ui.CalculatorUI;
import com.voc.genshin_helper.ui.MainActivity;
import com.voc.genshin_helper.util.RoundedCornersTransformation;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.voc.genshin_helper.util.RoundedCornersTransformation.CornerType.TOP;

/*
 * Package com.voc.genshin_helper.data.CharactersAdapter was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

/**
 * Created by ankit on 27/10/17.
 */

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.ViewHolder> {

    private Context context;
    private List<Characters> charactersList;
    private AdapterView.OnItemClickListener mListener;
    private WebView webView ;

    public CharactersAdapter(Context context, List<Characters> charactersList) {
        this.context = context;
        this.charactersList = charactersList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        ViewHolder evh = null;
        // 1) MainActivity's char_list
        // 2) CalculatorUI's char_list
        if(context instanceof MainActivity){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_char_ico, parent, false);
            evh = new ViewHolder(v, (OnItemClickListener) mListener);
        }else if(context instanceof CalculatorUI){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_char_ico_square, parent, false);
            evh = new ViewHolder(v, (OnItemClickListener) mListener);
        }

        return evh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Characters Characters = charactersList.get(position);
        Characters_Rss characters_rss = new Characters_Rss();
        int width = 0, height = 0;
        int count = 3;
        final int radius = 25;
        final int margin = 0;
        final Transformation transformation = new RoundedCornersTransformation(radius, margin,TOP);

        holder.char_name.setText(Characters.getName());
        holder.char_base_name.setText(Characters.getName());

        if(Characters.getIsComing() == 0){holder.char_isComing.setVisibility(View.GONE);}
        if(Characters.getIsComing() == 1){holder.char_isComing.setVisibility(View.VISIBLE);}

        if(Characters.getRare() >3 && Characters.getRare() < 6){holder.char_star.setNumStars(Characters.getRare());holder.char_star.setRating(Characters.getRare());}

        if(context instanceof MainActivity){
            width = (int) ((ScreenSizeUtils.getInstance(context).getScreenWidth() - 32*2*2)/2);
            height = (int) (width*58/32);
        }else if(context instanceof CalculatorUI){
            width = (int) ((ScreenSizeUtils.getInstance(context).getScreenWidth() - 32*2*2)/3);
            height = (int) ((ScreenSizeUtils.getInstance(context).getScreenWidth() - 32*2*2)/3);
        }
        if(Characters.getElement().equals("Anemo")){holder.char_element.setImageResource(R.drawable.anemo);holder.char_bg.setBackgroundResource(R.drawable.bg_anemo_bg);holder.char_nl.setBackgroundResource(R.drawable.bg_anemo_char);}
        if(Characters.getElement().equals("Cryo")){holder.char_element.setImageResource(R.drawable.cryo);holder.char_bg.setBackgroundResource(R.drawable.bg_cryo_bg);holder.char_nl.setBackgroundResource(R.drawable.bg_cryo_char);}
        if(Characters.getElement().equals("Electro")){holder.char_element.setImageResource(R.drawable.electro);holder.char_bg.setBackgroundResource(R.drawable.bg_electro_bg);holder.char_nl.setBackgroundResource(R.drawable.bg_electro_char);}
        if(Characters.getElement().equals("Geo")){holder.char_element.setImageResource(R.drawable.geo);holder.char_bg.setBackgroundResource(R.drawable.bg_geo_bg);holder.char_nl.setBackgroundResource(R.drawable.bg_geo_char);}
        if(Characters.getElement().equals("Hydro")){holder.char_element.setImageResource(R.drawable.hydro);holder.char_bg.setBackgroundResource(R.drawable.bg_hydro_bg);holder.char_nl.setBackgroundResource(R.drawable.bg_hydro_char);}
        if(Characters.getElement().equals("Pyro")){holder.char_element.setImageResource(R.drawable.pyro);holder.char_bg.setBackgroundResource(R.drawable.bg_pyro_bg);holder.char_nl.setBackgroundResource(R.drawable.bg_pyro_char);}
        if(Characters.getElement().equals("Dendro")){holder.char_element.setImageResource(R.drawable.dendro);holder.char_bg.setBackgroundResource(R.drawable.bg_dendro_bg);holder.char_nl.setBackgroundResource(R.drawable.bg_dendro_char);}

        holder.char_icon.getLayoutParams().width = width;
        holder.char_icon.getLayoutParams().height = height;

        Bitmap bitmap ;
        Bitmap outBitmap ;
        // Already knew that IMG size is not the main reason of main_list lag
        //bitmap = BitmapFactory.decodeResource(context.getResources(),characters_rss.getCharByName(Characters.getName())[0]);
        //outBitmap =getRoundBitmapByShader(bitmap, (int) Math.round(width/2),(int)Math.round(height/2),20, 0);
        if(context instanceof MainActivity){
            Picasso.get()
                    .load (characters_rss.getCharByName(Characters.getName())[0]).fit().centerCrop().transform(transformation)
                    .error (R.drawable.paimon_full)
                    .into (holder.char_icon);

            //bitmap = BitmapFactory.decodeResource(context.getResources(),characters_rss.getCharByName(Characters.getName())[0]);
            //outBitmap =getRoundBitmapByShader(bitmap, (int) Math.round(width/2),(int)Math.round(height/2),20, 0);
        }else if(context instanceof CalculatorUI){
            Picasso.get()
                    .load (characters_rss.getCharByName(Characters.getName())[3]).fit().centerInside().transform(transformation)
                    .error (R.drawable.paimon_full)
                    .into (holder.char_icon);

            //bitmap = BitmapFactory.decodeResource(context.getResources(),characters_rss.getCharByName(Characters.getName())[3]);
            //outBitmap =getRoundBitmapByShader(bitmap, (int) Math.round(width/2),(int)Math.round(height/2),20, 0);
        }

       // holder.char_icon.setImageBitmap(outBitmap);
       // holder.char_icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.char_name.setText(characters_rss.getCharByName(Characters.getName())[1]);

        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info",MODE_PRIVATE);
        String color_hex = sharedPreferences.getString("theme_color_hex","#FF5A5A"); // Must include #

        ColorStateList myList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_pressed},
                        new int[]{-android.R.attr.state_checked},
                        new int[]{android.R.attr.state_checked},
                },
                new int[] {
                        context.getResources().getColor(R.color.tv_color),
                        context.getResources().getColor(R.color.tv_color),
                        Color.parseColor(color_hex)
                }
        );

    }

    @Override
    public int getItemCount() {
        return charactersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView char_name,char_base_name;
        public ImageView char_icon,char_isComing,char_element;
        public LinearLayout char_nl;
        public RatingBar char_star;
        public ConstraintLayout char_bg ;

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            char_icon = itemView.findViewById(R.id.char_img);
            char_name = itemView.findViewById(R.id.char_name);
            char_star = itemView.findViewById(R.id.char_star);
            char_element = itemView.findViewById(R.id.char_element);
            char_isComing = itemView.findViewById(R.id.char_is_coming);
            char_base_name = itemView.findViewById(R.id.char_base_name);
            char_nl = itemView.findViewById(R.id.char_nl);
            char_bg = itemView.findViewById(R.id.char_bg);

            char_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.wtf("is context instanceof MainActivity ?",context.getPackageName());
                    if (context instanceof MainActivity){Log.wtf("YES","IT's");
                        (((MainActivity) context)).startInfo(String.valueOf(char_base_name.getText()));

                    }
                    else if (context instanceof CalculatorUI){Log.wtf("YES","IT's");
                        ArrayList<String> nameList = (((CalculatorUI) context)).checkNameList();
                        boolean have = false;
                        String name = String.valueOf(char_base_name.getText());

                        if(nameList.contains(name.replace("_"," "))){
                            have = true;
                        }

                        /*
                        if(char_isComing.getVisibility() == View.GONE) {
                            if (have == false) {
                                (((CalculatorUI) context)).charQuestion(String.valueOf(char_base_name.getText()), "ADD", 0);
                            } else {
                                Toast.makeText(((CalculatorUI) context), "You have already set this character !", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(((CalculatorUI) context), "暫時沒有他/她的相關資料,無法計算", Toast.LENGTH_SHORT).show();
                        }

                         */

                        if (have == false) {
                            (((CalculatorUI) context)).charQuestion(String.valueOf(char_base_name.getText()), "ADD", 0);
                        } else {
                            Toast.makeText(((CalculatorUI) context), "You have already set this character !", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });

        }
    }




    public void filterList(List<Characters> filteredList) {
        charactersList = filteredList;
        notifyDataSetChanged();
    }
}