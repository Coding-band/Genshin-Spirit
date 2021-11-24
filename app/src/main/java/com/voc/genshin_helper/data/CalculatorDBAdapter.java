package com.voc.genshin_helper.data;

import static android.content.Context.MODE_PRIVATE;
import static com.voc.genshin_helper.util.RoundedCornersTransformation.CornerType.TOP;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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
import com.voc.genshin_helper.database.DataBaseHelper;
import com.voc.genshin_helper.ui.CalculatorDBActivity;
import com.voc.genshin_helper.ui.CalculatorUI;
import com.voc.genshin_helper.ui.MainActivity;
import com.voc.genshin_helper.util.CustomToast;
import com.voc.genshin_helper.util.RoundedCornersTransformation;

import java.util.ArrayList;
import java.util.List;

/*
 * Package com.voc.genshin_helper.data.CalculatorDBAdapter was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

/**
 * Created by ankit on 27/10/17.
 */

public class CalculatorDBAdapter extends RecyclerView.Adapter<CalculatorDBAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<CalculatorDB> dbList;
    private AdapterView.OnItemClickListener mListener;
    private WebView webView ;

    public CalculatorDBAdapter(Context context, List<CalculatorDB> dbList, Activity activity) {
        this.context = context;
        this.activity = activity;
        this.dbList = dbList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = this.context;
        View v;
        ViewHolder evh = null;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calculator_db, parent, false);
        evh = new ViewHolder(v, (OnItemClickListener) mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CalculatorDB db = dbList.get(position);
        ItemRss itemRss = new ItemRss();

        holder.name = db.getName();

        holder.db_sheet_tv.setText(db.getName());
        holder.db_sheet_info.setText(
                context.getString(R.string.title_char)+" : "+String.valueOf(db.getChar_cnt())+" / "+
                context.getString(R.string.title_weapons)+" : "+String.valueOf(db.getWeapon_cnt())+" / "+
                context.getString(R.string.title_artifacts)+" : "+String.valueOf(db.getArtifact_cnt())
        );


    }

    @Override
    public int getItemCount() {
        return dbList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView db_sheet_tv,db_sheet_info;
        public ImageView db_sheet_ico;
        public LinearLayout db_sheet_bg ;
        public String name ;

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            db_sheet_ico = itemView.findViewById(R.id.db_sheet_ico);
            db_sheet_tv = itemView.findViewById(R.id.db_sheet_tv);
            db_sheet_tv = itemView.findViewById(R.id.db_sheet_tv);
            db_sheet_bg = itemView.findViewById(R.id.db_sheet_bg);
            db_sheet_info = itemView.findViewById(R.id.db_sheet_info);


            db_sheet_bg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (context instanceof CalculatorDBActivity){Log.wtf("YES","IT's");
                        (((CalculatorDBActivity) context)).transferDataToUI(name);
                    }
                }
            });

            db_sheet_bg.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                    View view = View.inflate(context, R.layout.menu_edit_delete, null);

                    EditText menu_edit_tv = view.findViewById(R.id.menu_edit_tv);
                    Button cancel = view.findViewById(R.id.menu_cancel);
                    Button ok = view.findViewById(R.id.menu_ok);
                    Button delete = view.findViewById(R.id.menu_delete);

                    menu_edit_tv.setText(db_sheet_tv.getText());

                    String oldName = db_sheet_tv.getText().toString();

                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String newName = menu_edit_tv.getText().toString();

                            if(!newName.equals("") && !newName.equals(" ") && menu_edit_tv.getText() != null) {
                                if(newName.startsWith("1")||newName.startsWith("2")||newName.startsWith("3")||newName.startsWith("4")||newName.startsWith("5")||newName.startsWith("6")||newName.startsWith("7")||newName.startsWith("8")||newName.startsWith("9")||newName.startsWith("10")){
                                    CustomToast.toast(context,activity,"Number is not allow to use a first character.");
                                }else {
                                    if(newName.contains(" ")){
                                        newName = newName.replace(" ","_");
                                    }
                                    if(!oldName.toLowerCase().equals(newName.toLowerCase())){
                                    DataBaseHelper dbHelper = new DataBaseHelper(context);
                                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                                    db.execSQL("ALTER TABLE "+oldName+"_char"+ " RENAME TO "+newName+"_char");
                                    db.execSQL("ALTER TABLE "+oldName+ "_weapon"+" RENAME TO "+newName +"_weapon");
                                    db.execSQL("ALTER TABLE "+oldName+ "_artifact"+" RENAME TO "+newName+ "_artifact");

                                    db.execSQL("UPDATE IndexDB SET name = \""+newName+"\" WHERE name = \""+oldName+"\";");
                                    dialog.dismiss();
                                    if (context instanceof CalculatorDBActivity){Log.wtf("YES","IT's");
                                        (((CalculatorDBActivity) context)).readIndexRecord();
                                        }
                                    }else {
                                        CustomToast.toast(context,activity,"You must not use the same name (Even different letter case) !");
                                    }
                                }
                            }
                        }
                    });

                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DataBaseHelper dbHelper = new DataBaseHelper(context);
                            SQLiteDatabase db = dbHelper.getWritableDatabase();
                            db.execSQL("DROP TABLE "+oldName+"_char");
                            db.execSQL("DROP TABLE "+oldName+"_weapon");
                            db.execSQL("DROP TABLE "+oldName+"_artifact");
                            // DELETE FROM Customers WHERE CustomerName='Alfreds Futterkiste';
                            db.execSQL("DELETE FROM IndexDB WHERE name = \""+oldName+"\"");
                            dialog.dismiss();
                            if (context instanceof CalculatorDBActivity){Log.wtf("YES","IT's");
                                (((CalculatorDBActivity) context)).readIndexRecord();
                            }
                        }
                    });

                    dialog.setContentView(view);
                    dialog.setCanceledOnTouchOutside(true);
                    //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
                    Window dialogWindow = dialog.getWindow();
                    WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                    lp.width = (int) (ScreenSizeUtils.getInstance(context).getScreenWidth());
                    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    lp.gravity = Gravity.CENTER;
                    dialogWindow.setAttributes(lp);
                    dialog.show();
                    return false;
                }
            });

        }
    }




    public void filterList(List<CalculatorDB> filteredList) {
        dbList = filteredList;
        notifyDataSetChanged();
    }
}