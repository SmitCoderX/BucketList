package com.smitcoderx.learn.bucketlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BucketAdapter extends RecyclerView.Adapter<BucketAdapter.BucketViewHolder> {

    private ArrayList<BucketItems> mList;
    private onBinClickListener listener;

    public BucketAdapter(ArrayList<BucketItems> mList) {
        this.mList = mList;
    }

    public void onBinClickListener(onBinClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public BucketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout, parent, false);
        return new BucketViewHolder(view, listener);
    }

    public void clear() {
        int size = mList.size();
        mList.clear();
        this.notifyItemRangeRemoved(0, size);
    }

    @Override
    public void onBindViewHolder(@NonNull BucketViewHolder holder, int position) {
        BucketItems currentItems = mList.get(position);

        holder.textItem.setText(currentItems.getItem());
        holder.textQuantity.setText(currentItems.getQuantity());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface onBinClickListener {
        void onBinClick(int position);
    }

    public static class BucketViewHolder extends RecyclerView.ViewHolder {

        private TextView textItem;
        private TextView textQuantity;


        public BucketViewHolder(@NonNull View itemView, final onBinClickListener listener) {
            super(itemView);

            textItem = itemView.findViewById(R.id.text1);
            textQuantity = itemView.findViewById(R.id.text2);
            ImageView imageDelete = itemView.findViewById(R.id.imageViewDelete);
            imageDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                    {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onBinClick(position);
                        }
                    }
                }
            });
        }
    }
}
