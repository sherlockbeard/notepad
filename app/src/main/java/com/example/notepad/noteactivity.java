package com.example.notepad;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class noteactivity extends AppCompatActivity {
    private EditText mEdittitle;
    private EditText mEditdata;

    private String mnotefilename;
    private notes mLoadednote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noteactivity);
        mEdittitle = findViewById(R.id.nottitle);
        mEditdata = findViewById(R.id.notetext);
        mnotefilename = getIntent().getStringExtra("notefile");
        if(mnotefilename!=null && !mnotefilename.isEmpty()){
            mLoadednote = utilite.getnotebyname(this,mnotefilename);
            if(mLoadednote!=null){
                mEdittitle.setText(mLoadednote.getmTitle());
                mEditdata.setText(mLoadednote.getmContent());
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notesmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.notesave:
                if(mEdittitle.getText().toString().trim().isEmpty() || mEditdata.getText().toString().trim().isEmpty()){
                    Toast.makeText(this, "please add something!!!!", Toast.LENGTH_SHORT).show();
                }else {
                    savenote();
                }
                     break;
            case R.id.notedelete:
                deletnote();
                break;
        }
        return true;

    }

    private void deletnote() {
        if (mLoadednote == null) {
            finish();
        } else {
            utilite.deletenote(getApplicationContext(), mLoadednote.getmDateTime() + utilite.File_extension);
            Toast.makeText(getApplicationContext(), "deleted", Toast.LENGTH_SHORT).show();
            finish();


        }
    }

    private void savenote() {
        notes note;
        if(mLoadednote==null) {
             note = new notes(System.currentTimeMillis(), mEdittitle.getText().toString(), mEditdata.getText().toString());
        }else {
             note = new notes(mLoadednote.mDateTime, mEdittitle.getText().toString(), mEditdata.getText().toString());
        }
        if(utilite.savenote(this,note)){
            Toast.makeText(this,"note is saved",Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(this, "no space nigga", Toast.LENGTH_SHORT).show();
        }
    }
}
