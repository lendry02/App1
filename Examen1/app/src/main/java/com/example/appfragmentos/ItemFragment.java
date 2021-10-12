package com.example.appfragmentos;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.appfragmentos.placeholder.PlaceholderContent;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


/**
 * A fragment representing a list of Items.
 */
public class ItemFragment extends Fragment implements OntouchItemList<PlaceholderContent.PlaceholderVersion> {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private final int permRequestCode = 200;
    private ArrayList<String> permisos = new ArrayList<>();
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(PlaceholderContent.ITEMS, this));
        }
        return view;
    }

    private boolean checkedPermission(String permiso){
        int resultpermiss = ContextCompat.checkSelfPermission(getContext(),permiso);
        return resultpermiss == PackageManager.PERMISSION_GRANTED ;
    }

    private void requestPermission(String[] permiso){
        ActivityCompat.requestPermissions(getActivity(),permiso,permRequestCode);
    }


    @Override
    public void onClick(PlaceholderContent.PlaceholderVersion element) {


        if (checkedPermission("android.permission.WRITE_EXTERNAL_STORAGE") && checkedPermission("android.permission.WRITE_EXTERNAL_STORAGE") && checkedPermission("android.permission.READ_EXTERNAL_STORAGE")) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_parent,ItemDetailsFragment.newInstance(element))
                    .addToBackStack(null)
                    .commit();
            //Snackbar.make(getView(),element.getDetails(), Snackbar.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "Please request Permission", Toast.LENGTH_LONG).show();
        }

        if (!checkedPermission("android.permission.CAMERA")) {
            permisos.add("android.permission.CAMERA");
        }

        if (!checkedPermission("android.permission.READ_EXTERNAL_STORAGE")) {
            permisos.add("android.permission.READ_EXTERNAL_STORAGE");
        }

        if (!checkedPermission("android.permission.WRITE_EXTERNAL_STORAGE")) {
            permisos.add("android.permission.WRITE_EXTERNAL_STORAGE");
        }

        String arregloString[] = permisos.toArray(new String[permisos.size()]);

        if(!permisos.isEmpty()){
            requestPermission(arregloString);
        }



            // Snackbar.make(getView(),element.getDetails(), Snackbar.LENGTH_LONG).show();
//        final int containerViewId;
//
//        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
//            containerViewId = R.id.fragment_parent;
//        }else{
//            containerViewId = R.id.fragment_details;
//        }



        }


}