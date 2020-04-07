package com.ramadan.remember.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.ramadan.remember.R;
import com.ramadan.remember.activity.new_written_note;
import com.ramadan.remember.model.writtenNote;

import java.util.ArrayList;

public class note_rcv_adp extends RecyclerView.Adapter<note_rcv_adp.noteViewHolder> {
    private Context mContext;
    private ArrayList<writtenNote> _note;


    public note_rcv_adp(Context mContext, ArrayList note) {
        this.mContext = mContext;
        this._note = note;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        AdapterView.OnItemClickListener mListener = (AdapterView.OnItemClickListener) listener;
    }

    @NonNull
    @Override
    public noteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.written_note_card, parent, false);
        return new noteViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull note_rcv_adp.noteViewHolder holder, int position) {
        writtenNote mNote = _note.get(position);
        String noteID = mNote.getNoteID();
        String noteDate = mNote.getNoteDate();
        String noteName = mNote.getNoteName();
        String noteData = mNote.getNoteData();
        int noteColor = mNote.getNoteColor();
        int fontColor = mNote.getfontColor();
//        Color noteColor = mNote.getNoteData();
//        Icon category = mNote.getcategory();

        ///
        holder.noteID = noteID;
        holder.noteDate.setText(noteDate);
        holder.noteName.setText(noteName);
        holder.noteData.setText(noteData);
        holder.noteDate.setTextColor(fontColor);
        holder.noteName.setTextColor(fontColor);
        holder.noteData.setTextColor(fontColor);
        holder.card.setBackgroundColor(noteColor);
//        holder.noteColor.convert(noteColor);
//        holder.category.setImageIcon(category);
    }


    @Override
    public int getItemCount() {
        return _note.size();
    }

    public interface OnItemClickListener {

        void onItemClick(int position);
    }

    class noteViewHolder extends RecyclerView.ViewHolder {
        TextView noteName, noteDate, noteData;
        String noteID;
        int noteColor, fontColor;
        int noteTextSize;
        LinearLayout card;


        noteViewHolder(View itemView) {
            super(itemView);
            noteName = itemView.findViewById(R.id.writtenNoteName);
            noteData = itemView.findViewById(R.id.writtenNoteData);
            noteDate = itemView.findViewById(R.id.writtenNoteDate);
            card = itemView.findViewById(R.id.card);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, new_written_note.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("noteID", noteID);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                    Animatoo.animateZoom(mContext);
                }
            });
        }
    }
}



