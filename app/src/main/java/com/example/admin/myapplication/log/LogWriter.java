package com.example.admin.myapplication.log;

import android.content.Context;
import android.content.ContextWrapper;

import java.io.*;

public class LogWriter {
    private static LogWriter instance;

    private LogWriter(){}

    public static LogWriter getInstance(){
        if(instance == null){
            instance = new LogWriter();
        }
        return instance;
    }

    public void appendLog(Context context, String text)
    {
        File logFile = new File(context.getFilesDir(),"log.file");
        if (!logFile.exists())
        {
            try
            {
                logFile.createNewFile();
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try
        {
            //BufferedWriter for performance, true to set append to file flag
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(text);
            buf.newLine();
            buf.close();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
