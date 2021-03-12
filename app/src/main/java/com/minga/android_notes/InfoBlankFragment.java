package com.minga.android_notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class InfoBlankFragment extends Fragment {

    private static final String ARG_INFO = "info";

    private String mParam1;


    public static InfoBlankFragment newInstance(String param1) {
        InfoBlankFragment fragment = new InfoBlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_INFO, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_INFO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_blank, container, false);
        TextView textView = view.findViewById(R.id.tv_info);
        textView.setText("Мингалеев Артур Чулпанович");

        return view;
    }
}