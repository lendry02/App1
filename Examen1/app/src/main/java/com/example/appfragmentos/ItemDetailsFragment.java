package com.example.appfragmentos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfragmentos.placeholder.PlaceholderContent;

public class ItemDetailsFragment extends Fragment {

    private PlaceholderContent.PlaceholderVersion element;

    public ItemDetailsFragment(){

    }

    public static ItemDetailsFragment newInstance(PlaceholderContent.PlaceholderVersion element) {
        ItemDetailsFragment fragment = new ItemDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable("element", element);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            element = (PlaceholderContent.PlaceholderVersion) getArguments().getSerializable("element");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_details, container, false);

        TextView details = view.findViewById(R.id.details);
        TextView code = view.findViewById(R.id.textcode);
        TextView version = view.findViewById(R.id.textversion);
        TextView link= view.findViewById(R.id.textlink);

        details.setText(element.getDetails());
        code.setText(element.getInternalCodeName());
        version.setText(element.getVersionNumber());
        link.setText(element.getLink());


        return view;
    }
}
