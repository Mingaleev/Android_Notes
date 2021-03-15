package com.minga.android_notes.notes.noteBlank;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.minga.android_notes.R;
import com.minga.android_notes.model.SimpleNote;

import java.util.UUID;

public class NotesBlankFragment extends Fragment implements NoteFirestoreCallbacks{

    private final NoteRepository repository = new NoteRepositoryImpl(this);

    private static final String ARG_NOTE = "note";
    private SimpleNote simpleNote;
    private MaterialToolbar toolbar;
    private EditText etBlankTitle;
    private EditText etBlankDesc;
    private Button btn_update;
    private Button btn_remove;

    public static NotesBlankFragment newInstanse(@Nullable SimpleNote simpleNote) {
        NotesBlankFragment f = new NotesBlankFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_NOTE, simpleNote);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            simpleNote = (SimpleNote) getArguments().getSerializable(ARG_NOTE);
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
        etBlankDesc = view.findViewById(R.id.et_note_blank_desc);
        if (simpleNote != null) {
            etBlankTitle.setText(simpleNote.getTitle());
            etBlankDesc.setText(simpleNote.getDesc());
        }
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
                update(title, desc);
            }
        });

        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repository.deleteNote(simpleNote.getId());
                if (getActivity() != null) {
                    getActivity().onBackPressed();
                }
            }
        });
    }

    @Override
    public void onSuccess(@Nullable String message) {
        showToastMessage(message);
    }

    @Override
    public void onError(@Nullable String message) {
        showToastMessage(message);
    }

    private void update(@Nullable String title,
                        @Nullable String desc) {

        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(desc)) {
            if (simpleNote != null) {
                repository.setNote(simpleNote.getId(),title,desc);
            } else {
                repository.setNote(UUID.randomUUID().toString(),title,desc);
            }
        } else {
            showToastMessage("Поля не могут быть пустыми");
        }
    }

    private void showToastMessage(@Nullable String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }


}

