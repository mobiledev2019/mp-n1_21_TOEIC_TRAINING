package com.toeic.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.toeic.R;
import com.toeic.model.Unit;
import com.toeic.model.Word;

import java.util.List;

public class lessonWordAdpater extends RecyclerView.Adapter<lessonWordAdpater.MyViewHolder>{
    private Context mContext;
    private List<Word> mData;

    public lessonWordAdpater(Context mContext, List<Word> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public lessonWordAdpater.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate((R.layout.card_item_words), viewGroup, false);

        return new lessonWordAdpater.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

    }



    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder {

        TextView en;
        TextView vn;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            en = (TextView) itemView.findViewById(R.id.en);
            vn = (TextView) itemView.findViewById(R.id.vn) ;
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);

        }
    }
}
