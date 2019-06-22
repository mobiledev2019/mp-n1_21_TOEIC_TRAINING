package com.toeic.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.toeic.R;
import com.toeic.activity.lesson.lesson_part2;
import com.toeic.activity.lesson.lesson_part3;
import com.toeic.model.Unit;

import java.util.List;

public class LessonPart3Adapter  extends RecyclerView.Adapter<LessonPart3Adapter.MyViewHolder>{
    private Context mContext;
    private List<Unit> mData;

    public LessonPart3Adapter(Context mContext, List<Unit> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public LessonPart3Adapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate((R.layout.cart_item_lesson), viewGroup, false);

        return new LessonPart3Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, final int i) {

        myViewHolder.tv_item_title.setText(mData.get(i).getTitle());
        myViewHolder.tv_item_descripton.setText(mData.get(i).getDescription());
        myViewHolder.iv_item_img.setImageResource(mData.get(i).getAvatar());


        // set click listener
        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, lesson_part3.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder {

        TextView tv_item_title;
        TextView tv_item_descripton;
        ImageView iv_item_img;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_item_title = (TextView) itemView.findViewById(R.id.item_title_id);
            tv_item_descripton = (TextView) itemView.findViewById(R.id.item_description) ;
            iv_item_img = (ImageView) itemView.findViewById(R.id.item_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);

        }
    }
}

