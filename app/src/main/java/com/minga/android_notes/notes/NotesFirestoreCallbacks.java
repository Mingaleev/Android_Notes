package com.minga.android_notes.notes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.minga.android_notes.model.SimpleNote;

import java.util.List;

public interface NotesFirestoreCallbacks {

    void onSuccessNotes (@NonNull List<SimpleNote> items);
    void onErrorNotes (@Nullable String message);
}
