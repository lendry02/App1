package com.example.appfragmentos;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appfragmentos.placeholder.PlaceholderContent;
import com.example.appfragmentos.databinding.FragmentItemBinding;

import java.util.List;


public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final OntouchItemList<PlaceholderContent.PlaceholderVersion> mListener;

    private final List<PlaceholderContent.PlaceholderVersion> mValues;

    public MyItemRecyclerViewAdapter(List<PlaceholderContent.PlaceholderVersion> items,OntouchItemList<PlaceholderContent.PlaceholderVersion> Listener) {
        mValues = items;
        mListener = Listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)  {

        FragmentItemBinding binding =FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding,mListener);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentName.setText(holder.mItem.getName());
        holder.mContentView.setText(holder.mItem.getVersionNumber());
      //  holder.mContentView.setText(holder.mItem.getVersionNumber());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //public final TextView mIdView;
        public final TextView mContentView,mContentName;
        public PlaceholderContent.PlaceholderVersion mItem;
        private OntouchItemList<PlaceholderContent.PlaceholderVersion> mListener;

        public ViewHolder(FragmentItemBinding binding,OntouchItemList<PlaceholderContent.PlaceholderVersion> Listener) {
            super(binding.getRoot());
            mListener = Listener;
            mContentView = binding.content;
            mContentName = binding.content1;
            binding.getRoot().setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            mListener.onClick(mItem);

        }


    }
}