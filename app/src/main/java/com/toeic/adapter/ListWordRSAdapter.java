package com.toeic.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.toeic.R;
import com.toeic.activity.lesson.lesson_words;
import com.toeic.common.Common;
import com.toeic.model.WordAnswer;

import java.util.ArrayList;
import java.util.List;

public class ListWordRSAdapter extends RecyclerView.Adapter<ListWordRSAdapter.RecyclerViewHolder>{

    private List<WordAnswer> data = new ArrayList<>();
    private Context mContext;
    Animation animation;
    ViewGroup view;
    Common cm;
    public String urlDelete = cm.getUrlDelete();
    public String urlSave = cm.getUrlPostSaved();

    public ListWordRSAdapter(List<WordAnswer> data) {
        this.data = data;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = parent;

        View view = inflater.inflate(R.layout.item_message, parent, false);

         animation = AnimationUtils.loadAnimation(parent.getContext(), R.anim.scale_list);
//        view.startAnimation(animation);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        String result = data.get(position).getResult();
        int is_true = data.get(position).getIs_true();

        holder.txt_rs.setText(result);
        holder.txt_user_2.setText(""+ position);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, lesson_words.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (data == null){
            return 0;
        }
        return data.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView txt_user_2, txt_rs;
        ImageView imgPost, imgHeart;
        CardView cardView;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            txt_user_2 = (TextView) itemView.findViewById(R.id.txt_user_2);
            txt_rs = (TextView) itemView.findViewById(R.id.message_content);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }

}
