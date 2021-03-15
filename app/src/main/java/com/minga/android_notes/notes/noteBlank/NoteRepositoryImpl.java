package com.minga.android_notes.notes.noteBlank;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.minga.android_notes.Constants;

import java.util.HashMap;
import java.util.Map;

class NoteRepositoryImpl implements NoteRepository{

    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private final NoteFirestoreCallbacks callbacks;

    NoteRepositoryImpl(NoteFirestoreCallbacks callbacks) {
        this.callbacks = callbacks;
    }

    @Override
    public void setNote(@NonNull String id,
                        @NonNull String title,
                        @NonNull String desc) {

        final Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("title", title);
        map.put("desc", desc);

        firebaseFirestore.collection(Constants.TABLE_NAME_NOTES)
                .document(id)
                .set(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        callbacks.onSuccess("Заметка успешно обновлена");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callbacks.onError(e.getMessage());
                    }
                });

    }

    @Override
    public void deleteNote(@NonNull String id) {
        firebaseFirestore.collection(Constants.TABLE_NAME_NOTES)
                .document(id)
                .delete();
    }
}
