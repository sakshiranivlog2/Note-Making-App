package com.example.finalnote2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.HashSet;

public class NoteEditorActivity extends AppCompatActivity {

    EditText editText;
   // ImageButton arrow2,save;
    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_note_editor );


      /*   arrow2 = findViewById( R.id.arrow2);
         save = findViewById( R.id.save );

         save.setOnClickListener( new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 saved();
             }

             private void saved() {
                 Toast.makeText(NoteEditorActivity.this,"Saved!",Toast.LENGTH_SHORT).show();
             }
         } );

         arrow2.setOnClickListener( new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 back();
             }

             private void back() {
                 Intent i = new Intent(NoteEditorActivity.this,MainActivity.class);
             }
         } );

       */

        EditText editText = findViewById( R.id.editText );
        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId",-1);
        if (noteId != -1){
            editText.setText( MainActivity.notes.get(noteId) );
        }
        else{
            MainActivity.notes.add("");
            noteId=MainActivity.notes.size()-1;
            MainActivity.arrayAdapter.notifyDataSetChanged();
        }

        editText.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                           MainActivity.notes.set(noteId,String.valueOf( charSequence ));
                           MainActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences( "com.example.finalnote2",
                        Context.MODE_PRIVATE );
                HashSet<String> set = new HashSet<>(MainActivity.notes);
                sharedPreferences.edit().putStringSet( "notes", set ).apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        } );
    }
}
