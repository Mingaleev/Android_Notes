package com.minga.android_notes.notes;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.minga.android_notes.Constants;
import com.minga.android_notes.model.SimpleNote;

import java.util.List;

public class NotesRepositoryImpl implements NotesRepository {
    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private final NotesFirestoreCallbacks callbacks;

    public NotesRepositoryImpl(NotesFirestoreCallbacks callbacks) {
        this.callbacks = callbacks;
    }


    @Override
    public void requestNotes() {
        firebaseFirestore
                .collection(Constants.TABLE_NAME_NOTES)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.getResult() != null) {
                            List<SimpleNote> items = task.getResult().toObjects(SimpleNote.class);
                            callbacks.onSuccessNotes(items);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callbacks.onErrorNotes(e.getMessage());
                    }
                });
    }
}
