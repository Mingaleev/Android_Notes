package com.minga.android_notes.notes.noteBlank;

import androidx.annotation.NonNull;

public interface NoteRepository {

    void setNote(
            @NonNull String id,
            @NonNull String title,
            @NonNull String desc
    );

    void deleteNote(
            @NonNull String id
    );
}
