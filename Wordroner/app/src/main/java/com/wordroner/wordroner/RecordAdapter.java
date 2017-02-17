package com.wordroner.wordroner;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by seohwan on 2017. 2. 17..
 */

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {

    List<Map.Entry<String, Integer>> items;

    public RecordAdapter(List<Map.Entry<String, Integer>> items) {
        this.items = items;
    }

    @Override
    public RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record, parent, false);
        return new RecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecordViewHolder holder, int position) {
        Map.Entry<String, Integer> item = getItem(position);
        String key = item.getKey();
        Integer value = item.getValue();
        //(Counts)Word
        holder.tvTitle.setText("("+value+")"+key);

    }

    Map.Entry<String, Integer> getItem(int position) {
        return items.get(position);
    }
    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    static class RecordViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;


        public RecordViewHolder(View itemView) {
            super(itemView);
            this.tvTitle = ((TextView) itemView.findViewById(R.id.tv_item_record_title));

        }
    }
}
