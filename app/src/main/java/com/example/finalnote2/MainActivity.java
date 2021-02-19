package com.example.finalnote2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> notes = new ArrayList<>();
    static ArrayAdapter<String> arrayAdapter;
    ImageButton img1;
    Button btnback,btnrate;
    RatingBar rb;
    Dialog dialog;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_main );


        img1 = findViewById( R.id.img1 );
        btnback = findViewById( R.id.btnback );
        btnrate = findViewById( R.id.btnrate );

        dialog = new Dialog(this);
        rb = findViewById( R.id.rb );

        img1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fii();
            }

            private void fii() {
                Intent i = new Intent( getApplicationContext(), Activity3.class );
                startActivity( i );
            }
        } );


        ListView listView = findViewById( R.id.listView );

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences( "com.example.finalnote2",
                Context.MODE_PRIVATE );

        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet( "notes", null );
        if (set == null) {
            notes.add( "Write Here" );
        } else {
            notes = new ArrayList<>( set );
        }


        arrayAdapter = new ArrayAdapter<>( this, android.R.layout.simple_list_item_1, notes );
        listView.setAdapter( arrayAdapter );
        notes.add( "Write Here" );


        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent( getApplicationContext(), NoteEditorActivity.class );
                intent.putExtra( "noteId", i );
                startActivity( intent );
            }
        } );

        listView.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int itemToDelete = i;

                new AlertDialog.Builder( MainActivity.this )
                        .setIcon( android.R.drawable.ic_dialog_alert )
                        .setTitle( "Are You Want Delete?" )
                        .setPositiveButton( "Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                                notes.remove( itemToDelete );
                                arrayAdapter.notifyDataSetChanged();

                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences( "com.example.finalnote2",
                                        Context.MODE_PRIVATE );
                                HashSet<String> set = new HashSet<>( MainActivity.notes );
                                sharedPreferences.edit().putStringSet( "notes", set ).apply();
                            }
                        } )
                        .setNegativeButton( "No", null )
                        .show();
                return true;

            }
        } );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Button btnback,btnrate;
        RatingBar rb;

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate( R.menu.add_note_ittem, menu );
        btnback = findViewById( R.id.btnback );
        btnrate = findViewById( R.id.btnrate );
        rb = findViewById( R.id.rb );
        return super.onCreateOptionsMenu( menu );
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.about) {
            AlertDialog.Builder b = new AlertDialog.Builder( this );
            b.setTitle("Easy Note Version 2.2 Newage Productions" );
            b.setIcon( getResources().getDrawable( R.drawable.notesd, getTheme() ) );
            b.create();
            b.show();
        } else if (item.getItemId() == R.id.rate) {
            dialog.setContentView( R.layout.activity_2 );
            Button btnrate = dialog.findViewById( R.id.btnrate );
            Button btnback = dialog.findViewById( R.id.btnback );


            btnback.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            } );
            btnrate.setOnClickListener( new View.OnClickListener() {
                
                
                @Override
                public void onClick(View view) {
                    dialog.setContentView( R.layout.rate );
                            Toast.makeText(getApplicationContext(),"Thank you for rating us" ,Toast.LENGTH_SHORT).show();

                        }
                    } );

            dialog.show();

           

        } else if (item.getItemId() == R.id.pp) {
            androidx.appcompat.app.AlertDialog.Builder bb = new androidx.appcompat.app.AlertDialog.Builder( this );
            bb.setTitle( "Privacy Policy" );
            bb.setMessage( "This privacy policy governs your use of the software\n" +
                    "application Easy Note(\"Mobile Application\") for mobile\n" +
                    "devices that was created by NewAge Productions.\n" +
                    "\n" +
                    "\n" +
                    "Easy Note allows you to enter as many characters as \n" +
                    "you're willing to type. You can also delete the note\n" +
                    " if it is not required anymore.\n" +
                    "\n" +
                    "It allows lined-paper styled text option format. It \n" +
                    "is user friendly and available with a clean user interface.\n" +
                    "\n" +
                    "\n" +
                    "Features:\n" +
                    "--------------\n" +
                    "• Fast, colorful and responsive interface.\n" +
                    "• Available in more than 90+ languages.\n" +
                    "• Tablet support also available.\n" +
                    "\n" +
                    "And much more to come in future updates!\n" +
                    "\n" +
                    "Please leave us your valuable comments for \n" +
                    "improvements and thanks for downloading.\n" +
                    "\n" +
                    "Enjoy!\n" +
                    "\n" +
                    "Contact us @ Newageproductions.androidapps@gmail.com\n" +
                    "\n" );
            bb.setNegativeButton( "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText( getApplicationContext(), "OK was clicked", Toast.LENGTH_SHORT ).show();
                }
            } );

            bb.setCancelable( false );
            bb.show();


        }
        return super.onOptionsItemSelected( item );

    }
}




