package com.ramadan.remember.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.ramadan.remember.R;
import com.ramadan.remember.adapter.note_rcv_adp;
import com.ramadan.remember.model.writtenNote;
import com.simplemobiletools.commons.views.MyRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static CollectionReference collectionReference;
    FirebaseFirestore db;
    List<writtenNote> obj;
    ProgressBar progressBar;
    FloatingActionsMenu multiple_actions;
    FloatingActionButton newNote, logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        multiple_actions = findViewById(R.id.multiple_actions);
        newNote = findViewById(R.id.newWrittenNote);
        logout = findViewById(R.id.logout);
        progressBar = findViewById(R.id.progressBar);
        db = FirebaseFirestore.getInstance();
        String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        collectionReference = db.collection("user").document(user_id).collection("writtenNote");
        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                obj = queryDocumentSnapshots.toObjects(writtenNote.class);
                setAdapter(obj);
                progressBar.setVisibility(View.GONE);
            }
        });

        newNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, new_written_note.class);
                MainActivity.this.startActivity(intent);
                Animatoo.animateZoom(MainActivity.this);
                multiple_actions.collapseImmediately();

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void setAdapter(List<writtenNote> note) {
        MyRecyclerView myrv = findViewById(R.id.writtenNoteRecyclerView);
        GridLayoutManager mGrid = new GridLayoutManager(MainActivity.this, 1, RecyclerView.HORIZONTAL, false);
        myrv.setLayoutManager(mGrid);
        myrv.setHasFixedSize(true);
        myrv.setItemAnimator(new DefaultItemAnimator());
        note_rcv_adp myAdapter = new note_rcv_adp(MainActivity.this, (ArrayList) note);
        myAdapter.notifyDataSetChanged();
        myrv.setAdapter(myAdapter);
    }
}
