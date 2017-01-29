package com.allo.screeninfo.utils;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allo.screeninfo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * KeyValueAdapter
 * <p>
 * Created by ALLO on 29/1/17.
 */
public class KeyValueAdapter<T extends KeyValue> extends RecyclerView.Adapter<KeyValueAdapter.KeyValueView> {

    private List<T> mItems;

    public KeyValueAdapter(List<T> items) {
        this.mItems = items;
    }

    @Override
    public KeyValueView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_key_value, parent, false);
        return new KeyValueView(view);
    }

    @Override
    public void onBindViewHolder(KeyValueAdapter.KeyValueView holder, int position) {
        T item = mItems.get(position);
        holder.configureViewWithItem(item);
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    public class KeyValueView extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_key)
        AppCompatTextView tvKey;

        @BindView(R.id.tv_value)
        AppCompatTextView tvValue;

        public KeyValueView(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void configureViewWithItem(T item) {
            tvKey.setText(item.getKey());
            tvValue.setText(item.getValue());
        }
    }
}
