package com.example.notepad;

import android.content.Context;
import android.content.DialogInterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class utilite {
    public static final String File_extension = ".bin";

    public static boolean savenote(Context context,notes note){
        String filename = String.valueOf(note.getmDateTime())  +File_extension;

        FileOutputStream fos;
        ObjectOutputStream oos;
        try{
            fos = context.openFileOutput(filename,context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(note);
            oos.close();
            fos.close();

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static ArrayList<notes> getallsaved(Context context){
        ArrayList<notes> notes = new ArrayList<>();
        File filesDir = context.getFilesDir();
        ArrayList<String> notesfiles = new ArrayList<>();

        for(String file: filesDir.list()){
            if(file.endsWith(File_extension)){
                notesfiles.add(file);
            }
        }
        FileInputStream fis;
        ObjectInputStream ois;

        for(int i=0 ;i<notesfiles.size();i++){
            try{
                fis = context.openFileInput(notesfiles.get(i));
                ois = new ObjectInputStream(fis);
                notes.add((notes)ois.readObject());
                fis.close();
                ois.close();

            }catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
                return null;

            }
        }
        return notes;
    }

    public static notes getnotebyname(Context context, String filename){
        File file = new File(context.getFilesDir(),filename);
        notes note;
        if(file.exists()){
            FileInputStream fis;
            ObjectInputStream ois;

            try {
                fis = context.openFileInput(filename);
                ois = new ObjectInputStream(fis);

                note =(notes) ois.readObject();
                fis.close();
                ois.close();

            }catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
                return null;
            }
            return note;
        }else {
            return null;
        }
}


    public static void deletenote(Context context, String filename) {
        File filedir =context.getFilesDir();
        File file = new File(filedir,filename);
        if(file.exists()){
            file.delete();
    }
}
}
