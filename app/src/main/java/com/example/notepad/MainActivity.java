package com.example.notepad;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView mListViewnotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListViewnotes = findViewById(R.id.listnotes);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mainaddnote:
                startActivity(new Intent(this,noteactivity.class));
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mListViewnotes.setAdapter(null);
        final ArrayList<notes> notes = utilite.getallsaved(this);
        if(notes ==null || notes.size()==0){
            Toast.makeText(this,"no notes saved",Toast.LENGTH_SHORT).show();
        }
        else{
            noteadapter na = new noteadapter(this,R.layout.activity_noteactivity,notes);
            mListViewnotes.setAdapter(na);

            mListViewnotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String filename = ((notes)mListViewnotes.getItemAtPosition(position)).getmDateTime()+ utilite.File_extension;
                    Intent viewnote =  new Intent(getApplicationContext(),noteactivity.class);
                    viewnote.putExtra("notefile",filename);
                    startActivity(viewnote);
                }
            });
        }
    }
}
