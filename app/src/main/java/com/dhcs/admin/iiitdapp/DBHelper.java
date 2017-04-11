package com.dhcs.admin.iiitdapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Admin on 4/5/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private SQLiteDatabase myDataBase;
    private final Context myContext;
    private static final String DATABASE_NAME = "IIITDApp.sqlite";
    public static String DATABASE_PATH = "";
    public static final int DATABASE_VERSION = 1;
    //Constructor

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
        DATABASE_PATH = myContext.getDatabasePath(DATABASE_NAME).toString();
    }

    //Create a empty database on the system
    public void createDataBase() throws IOException
    {
        boolean dbExist = checkDataBase();
        if(dbExist)
        {
            Log.v("DB Exists", "db exists");
            // By calling this method here onUpgrade will be called on a
            // writeable database, but only if the version number has been
            // bumped
            //onUpgrade(myDataBase, DATABASE_VERSION_old, DATABASE_VERSION);
        }
        boolean dbExist1 = checkDataBase();
        if(!dbExist1)
        {
            this.getReadableDatabase();
            try
            {
                this.close();
                copyDataBase();
            }
            catch (IOException e)
            {
                throw new Error("Error copying database");
            }
        }
    }

    //Check database already exist or not
    private boolean checkDataBase()
    {
        boolean checkDB = false;
        try
        {
            String myPath = DATABASE_PATH;
            File dbfile = new File(myPath);
            checkDB = dbfile.exists();
        }
        catch(SQLiteException e)
        {
        }
        return checkDB;
    }

    //Copies your database from your local assets-folder to the just created empty database in the system folder

    private void copyDataBase() throws IOException
    {
        String outFileName = DATABASE_PATH;
        OutputStream myOutput = new FileOutputStream(outFileName);
        InputStream myInput = myContext.getAssets().open(DATABASE_NAME);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0)
        {
            myOutput.write(buffer, 0, length);
        }
        myInput.close();
        myOutput.flush();
        myOutput.close();
    }

    //delete database
    public void db_delete()
    {
        File file = new File(DATABASE_PATH);
        if(file.exists())
        {
            file.delete();
            System.out.println("delete database file.");
        }
    }

    //Open database
    public void openDataBase() throws SQLException
    {
        String myPath = DATABASE_PATH;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    public synchronized void closeDataBase()throws SQLException
    {
        if(myDataBase != null)
            myDataBase.close();
        super.close();
    }

    public void onCreate(SQLiteDatabase db)
    {
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if (newVersion > oldVersion)
        {
            Log.v("Database Upgrade", "Database version higher than old.");
            db_delete();
        }
    }
    public Cursor retrieveEntry(String courseCode)
    {
        //SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=myDataBase.rawQuery("Select * from Course where CourseCode= '"+courseCode+"'",null);
        return cursor;
    }

    public ArrayList<String> retrieveCourses()
    {
        ArrayList<String> courses=new ArrayList<>();
        //SQLiteDatabase db=this.getReadableDatabase();
        for(int i=0;i<=70;i=i+20)
        {
            Cursor cursor=myDataBase.rawQuery("SELECT * FROM course where rowid>"+i+" and rowid <="+(i+20)+"",null);
            cursor.moveToFirst();
            do{
                //System.out.println("*****************************"+cursor.getString(1));
                courses.add(cursor.getString(1));
            }while(cursor.moveToNext());
        }
        return courses;
    }
    public Cursor retrieveScheduleByCourseName(String courseName)
    {
        //SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=myDataBase.rawQuery("SELECT * FROM Schedule where Acronym=(Select Acronym from Course where CourseName='"+courseName+"')",null);
        return cursor;
    }
    /*
    public boolean updateEntry(String roll_no, String name, String cur_sem)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_ROLL_NO,roll_no);
        contentValues.put(COLUMN_NAME,name);
        contentValues.put(COLUMN_CUR_SEM,cur_sem);
        if(db.update(TABLE_NAME,contentValues,""+COLUMN_ROLL_NO+" = ? ",new String[]{roll_no})>0)
            return true;
        else
            return false;
    }
    */
    public boolean deleteRecords()
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DELETE FROM alarm_schedule");
            db.close();
            return true;
        }
        catch(Exception e)
        {

        }
        return false;
    }
    public void updateRecords(){

    }

    //public boolean insertIntoAlarmTable(){
     //   SQLiteDatabase db=this.getWritableDatabase();
    //}

}