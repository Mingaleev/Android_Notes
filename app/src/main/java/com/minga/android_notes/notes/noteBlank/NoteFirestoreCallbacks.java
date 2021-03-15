package com.minga.android_notes.notes.noteBlank;

import androidx.annotation.Nullable;

public interface NoteFirestoreCallbacks {

    void onSuccess (@Nullable String message);
    void onError (@Nullable String message);
}
