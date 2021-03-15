package com.minga.android_notes.notes;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.minga.android_notes.NotesSpaceDecorator;
import com.minga.android_notes.R;
import com.minga.android_notes.model.SimpleNote;
import com.minga.android_notes.notes.adapter.NotesAdapter;
import com.minga.android_notes.notes.adapter.NotesAdapterCallback;
import com.minga.android_notes.notes.noteBlank.NotesBlankFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BlankFragmentOne extends Fragment implements NotesAdapterCallback, NotesFirestoreCallbacks {

    private static final String ARG_NOTE = "note";
    private static final String ARG_R = "note_r";
    private SimpleNote simpleNote;
    private NotesRepository repository = new NotesRepositoryImpl(this);

    private List<SimpleNote> simpleNotes = new ArrayList<>();
    private final NotesAdapter notesAdapter = new NotesAdapter(this);
    private boolean isLandscape;
    private FloatingActionButton floatingActionButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank_one, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
        floatingActionButton = view.findViewById(R.id.fb_note_add);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(ARG_NOTE, simpleNote);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        repository.requestNotes();

        isLandscape = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;

        if (savedInstanceState != null) {
            simpleNote = savedInstanceState.getParcelable(ARG_NOTE);
        } else {
//            simpleNote = simpleNotes.get(0);
        }

        if (isLandscape) {
            showLandNote(simpleNote);
        }

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPortNote(null);
            }
        });
    }

    private void initList(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.rv_notes);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.addItemDecoration(new NotesSpaceDecorator(getResources()
                .getDimensionPixelOffset(R.dimen.space_rv)));
        recyclerView.setAdapter(notesAdapter);
    }

    private void showNotes(SimpleNote simpleNote) {
        if (isLandscape) {
            showLandNote(simpleNote);
        } else {
            showPortNote(simpleNote);
        }
    }

    private void showPortNote(@Nullable SimpleNote simpleNote) {
        NotesBlankFragment detail = NotesBlankFragment.newInstanse(simpleNote);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = Objects.requireNonNull(fragmentManager).beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, detail);
        fragmentTransaction.addToBackStack(ARG_R).commit();
    }

    private void showLandNote(SimpleNote simpleNote) {
        NotesBlankFragment detail = NotesBlankFragment.newInstanse(simpleNote);
        FragmentManager fragmentManager = getFragmentManager();
        assert fragmentManager != null;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_land, detail);
        fragmentManager.popBackStack(ARG_R, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentTransaction.commit();

    }


    @Override
    public void onClickItem(SimpleNote simpleNote) {
        showNotes(simpleNote);
    }


    @Override
    public void onSuccessNotes(@NonNull List<SimpleNote> items) {
        simpleNotes.clear();
        simpleNotes.addAll(items);
        notesAdapter.setSimpleNotes(items);
    }

    @Override
    public void onErrorNotes(@Nullable String message) {
        showToast(message);
    }

    private void showToast(@Nullable String message) {
        if (message != null) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
        }
    }
}