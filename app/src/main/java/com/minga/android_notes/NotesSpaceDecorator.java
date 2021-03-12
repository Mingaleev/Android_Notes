package com.minga.android_notes;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class NotesSpaceDecorator extends RecyclerView.ItemDecoration {

    private final int defaultSpace;

    NotesSpaceDecorator(int defaultSpace) {
        this.defaultSpace = defaultSpace;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildAdapterPosition(view);

        if (position >= 0) {
            outRect.set(defaultSpace, defaultSpace, defaultSpace, defaultSpace);
        }
    }
}
