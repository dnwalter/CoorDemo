package com.ousy.coordemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @author ousiyuan
 * @date 2020/11/6
 */
public class TestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private List<String> mList;
    public TestAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_item, viewGroup, false);
        TextViewHolder holder = new TextViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        TextViewHolder holder = (TextViewHolder) viewHolder;
        holder.contentTv.setText(mList.get(i));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    // 刷新数据
    public void refreshData(List<String> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    private class TextViewHolder extends RecyclerView.ViewHolder {
        public TextView contentTv;
        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            contentTv = itemView.findViewById(R.id.tv_content);
        }
    }
}
