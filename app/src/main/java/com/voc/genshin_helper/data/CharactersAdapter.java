package com.voc.genshin_helper.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.voc.genshin_helper.R;
import com.voc.genshin_helper.ui.Characters_Info;
import com.voc.genshin_helper.ui.MainActivity;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.voc.genshin_helper.data.RoundRectImageView.getRoundBitmapByShader;

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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_char_ico, parent, false);
        ViewHolder evh = new ViewHolder(v, (OnItemClickListener) mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Characters Characters = charactersList.get(position);
        Characters_Rss characters_rss = new Characters_Rss();
        int width , height;
        int count = 3;

        width = (int) ((ScreenSizeUtils.getInstance(context).getScreenWidth() - 32*2*2)/2);
        height = (int) (width*1.7);

        holder.char_name.setText(Characters.getName());
        holder.char_base_name.setText(Characters.getName());

        if(Characters.getIsComing() == 0){holder.char_isComing.setVisibility(View.GONE);}
        if(Characters.getIsComing() == 1){holder.char_isComing.setVisibility(View.VISIBLE);}

        if(Characters.getRare() >3 && Characters.getRare() < 6){holder.char_star.setNumStars(Characters.getRare());holder.char_star.setRating(Characters.getRare());}

        if(Characters.getElement().equals("Anemo")){holder.char_element.setImageResource(R.drawable.anemo);holder.char_icon.setBackgroundResource(R.drawable.bg_anemo_bg);holder.char_nl.setBackgroundResource(R.drawable.bg_anemo_char);}
        if(Characters.getElement().equals("Cryo")){holder.char_element.setImageResource(R.drawable.cryo);holder.char_icon.setBackgroundResource(R.drawable.bg_cryo_bg);holder.char_nl.setBackgroundResource(R.drawable.bg_cryo_char);}
        if(Characters.getElement().equals("Electro")){holder.char_element.setImageResource(R.drawable.electro);holder.char_icon.setBackgroundResource(R.drawable.bg_electro_bg);holder.char_nl.setBackgroundResource(R.drawable.bg_electro_char);}
        if(Characters.getElement().equals("Geo")){holder.char_element.setImageResource(R.drawable.geo);holder.char_icon.setBackgroundResource(R.drawable.bg_geo_bg);holder.char_nl.setBackgroundResource(R.drawable.bg_geo_char);}
        if(Characters.getElement().equals("Hydro")){holder.char_element.setImageResource(R.drawable.hydro);holder.char_icon.setBackgroundResource(R.drawable.bg_hydro_bg);holder.char_nl.setBackgroundResource(R.drawable.bg_hydro_char);}
        if(Characters.getElement().equals("Pyro")){holder.char_element.setImageResource(R.drawable.pyro);holder.char_icon.setBackgroundResource(R.drawable.bg_pyro_bg);holder.char_nl.setBackgroundResource(R.drawable.bg_pyro_char);}
        if(Characters.getElement().equals("Dendro")){holder.char_element.setImageResource(R.drawable.dendro);holder.char_icon.setBackgroundResource(R.drawable.bg_dendro_bg);holder.char_nl.setBackgroundResource(R.drawable.bg_dendro_char);}

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),characters_rss.getCharByName(Characters.getName())[0]);
        holder.char_icon.getLayoutParams().width = width;
        holder.char_icon.getLayoutParams().height = height;
        Bitmap outBitmap =getRoundBitmapByShader(bitmap, (int) Math.round(width/1.5),(int)Math.round(height/1.5),20, 0);
        holder.char_icon.setImageBitmap(outBitmap);
        holder.char_icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
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


        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            char_icon = itemView.findViewById(R.id.char_img);
            char_name = itemView.findViewById(R.id.char_name);
            char_star = itemView.findViewById(R.id.char_star);
            char_element = itemView.findViewById(R.id.char_element);
            char_isComing = itemView.findViewById(R.id.char_is_coming);
            char_base_name = itemView.findViewById(R.id.char_base_name);
            char_nl = itemView.findViewById(R.id.char_nl);

            char_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.wtf("is context instanceof MainActivity ?",context.getPackageName());
                   if (context instanceof MainActivity){Log.wtf("YES","IT's");
                       (((MainActivity) context)).startInfo(String.valueOf(char_base_name.getText()));

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