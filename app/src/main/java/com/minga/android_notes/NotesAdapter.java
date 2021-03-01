package com.minga.android_notes;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private final List<SimpleNote> simpleNotes = new ArrayList<>();
    private static final String TAG = "NotesAdapter";
    private final NotesAdapterCallback callback;

    NotesAdapter(NotesAdapterCallback callback) {
        this.callback = callback;
    }


    public void setSimpleNotes(List<SimpleNote> items) {
        simpleNotes.clear();
        simpleNotes.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notes, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.onBind(simpleNotes.get(position), position);
    }

    @Override
    public int getItemCount() {
        return simpleNotes.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        private MaterialTextView textViewTitle;
        private MaterialTextView textViewDate;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.tv_rv_title);
            textViewDate = itemView.findViewById(R.id.tv_rv_date);
        }

        public void onBind(final SimpleNote model, int position) {
            Log.d(TAG, String.valueOf(position));
            textViewTitle.setText(model.getTitle());
            textViewDate.setText(model.getDate());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                        callback.onClickItem(model);
                    }
                }
            });
        }

    }
}
