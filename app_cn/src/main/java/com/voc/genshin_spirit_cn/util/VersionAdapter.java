package com.voc.genshin_spirit_cn.util;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.voc.genshin_spirit_cn.R;

import java.util.List;

/**
 * Created by ankit on 27/10/17.
 */

public class VersionAdapter extends RecyclerView.Adapter<VersionAdapter.ViewHolder> {

    private Context context;
    private List<Version> versionList;
    private AdapterView.OnItemClickListener mListener;
    private WebView webView ;


    public VersionAdapter(Context context, List<Version> versionList) {
        this.context = context;
        this.versionList = versionList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_version_item, parent, false);
        ViewHolder evh = new ViewHolder(v, (OnItemClickListener) mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Version Version = versionList.get(position);

        holder.Version_TV.setText(Version.getVersion());
        holder.VerName_TV.setText(Version.getVerName());
        holder.Desc_TV.setText(Html.fromHtml(Version.getDescription()));

        /*

        holder.IconIdTV.setText(Version.getIconId());
        String str = String.valueOf(holder.IconIdTV.getText());
        holder.IconIdTV.setText(Version.getIconId());
        if (str.equals("a")){holder.Type_IMG.setImageResource(R.drawable.version_a_ic);}
        else if (str.equals("b")){holder.Type_IMG.setImageResource(R.drawable.version_b_ic);}
        else if (str.equals("c")){holder.Type_IMG.setImageResource(R.drawable.version_c_ic);}
        else if (str.equals("d")){holder.Type_IMG.setImageResource(R.drawable.version_d_ic);}else{holder.Type_IMG.setImageResource(R.drawable.app_icon_round);}
         */


    }

    @Override
    public int getItemCount() {
        return versionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView Version_TV, VerName_TV, Desc_TV , IconIdTV;


        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            Version_TV = itemView.findViewById(R.id.version_id);
            VerName_TV = itemView.findViewById(R.id.version);
            Desc_TV = itemView.findViewById(R.id.intro);
            IconIdTV = itemView.findViewById(R.id.icon_id);

        }
    }
    public void filterList(List<Version> filteredList) {
        versionList = filteredList;
        notifyDataSetChanged();
    }
}