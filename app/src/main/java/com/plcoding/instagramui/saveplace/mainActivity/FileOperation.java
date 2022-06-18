package com.plcoding.instagramui.saveplace.mainActivity;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOperation {


    public String readFile(Context context ,String filename){
        String data ="";
        try {

            File path =context.getFilesDir();
            File file = new File(path,filename);
            byte[] content = new byte[1024];
            file.createNewFile();
            FileInputStream reader = new FileInputStream(file);
            int contentLen = reader.read(content);
            if(contentLen==-1){
                return "";
            }
            byte[] newContent = new byte[contentLen];
            System.arraycopy(content,0,newContent,0,contentLen);

            data = new String(newContent,0,contentLen);


        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return data ;
    }


    public void writeFile(Context context,String data,String filename){
        try{
            File path =context.getFilesDir();
            File file = new File(path,filename);
            FileOutputStream writer = new FileOutputStream(file);
            writer.write(data.getBytes());
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }



}
