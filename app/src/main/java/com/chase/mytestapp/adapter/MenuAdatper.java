package com.chase.mytestapp.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chase.mytestapp.R;

public class MenuAdatper extends RecyclerView.Adapter<MenuAdatper.TextViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private String[] mTitles;



    public MenuAdatper(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TextViewHolder(mLayoutInflater.inflate(R.layout.item_menu, parent, false));
    }

    @Override
    public void onBindViewHolder(TextViewHolder holder, int position) {
//        ViewGroup.LayoutParams lp =  holder.item_menu.getLayoutParams();
//        lp.height = (int)(150 + Math.random() * 300);
//        holder.item_menu.setLayoutParams(lp);

        holder.mTextView.setText("test" + position);

        holder.item_menu.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("setOnLongClickListener", "onClick--> position = ");

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return 30;
    }




    public static class TextViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        CardView item_menu;

        TextViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.textView1);
            item_menu = (CardView) view.findViewById(R.id.item_menu);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("NormalTextViewHolder", "onClick--> position = " + getPosition());
                }
            });
        }
    }
}
