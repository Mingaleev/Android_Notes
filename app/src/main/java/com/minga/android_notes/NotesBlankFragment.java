package com.minga.android_notes;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;

public class NotesBlankFragment extends Fragment {

    private static final String ARG_NOTE = "note";
    private SimpleNote simpleNote;
    private MaterialToolbar toolbar;
    private EditText etBlankTitle;
    private EditText etBlankDesc;
    private Button btn_update;
    private Button btn_remove;

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
        return inflater.inflate(R.layout.fragment_notes_blank, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etBlankTitle = view.findViewById(R.id.et_note_blank_title);
        etBlankTitle.setText(simpleNote.getTitle());

        etBlankDesc = view.findViewById(R.id.et_note_blank_desc);
        etBlankDesc.setText(simpleNote.getDesc());

        toolbar = view.findViewById(R.id.tb_note_blank);
        btn_update = view.findViewById(R.id.btn_note_blank_update);
        btn_remove = view.findViewById(R.id.btn_note_blank_remove);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    getActivity().onBackPressed();
                }
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String title = etBlankTitle.getText().toString();
                final String desc = etBlankDesc.getText().toString();
                saveDataToDatabase(title, desc);
            }
        });
    }

    private void saveDataToDatabase (@Nullable String title,@Nullable String desc) {
        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(desc)) {
            // Todo
        }
    }
}

