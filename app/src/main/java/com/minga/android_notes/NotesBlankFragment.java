package com.minga.android_notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NotesBlankFragment extends Fragment {

    private static final String ARG_NOTE = "note";
    private SimpleNote simpleNote;

    public static NotesBlankFragment newInstanse(SimpleNote simpleNote) {
        NotesBlankFragment f = new NotesBlankFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE, simpleNote);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            simpleNote = getArguments().getParcelable(ARG_NOTE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes_blank, container, false);
        TextView tv_title = view.findViewById(R.id.tv_title);
        tv_title.setText(simpleNote.getTitle());

        TextView tv_date = view.findViewById(R.id.tv_date);
        tv_date.setText(simpleNote.getDate());

        EditText et_desc = view.findViewById(R.id.desc);
        et_desc.setText(simpleNote.getDesc());
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.share, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_share) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}