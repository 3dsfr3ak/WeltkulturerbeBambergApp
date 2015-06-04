package com.github.weltkulturschnitzelbamberg.weltkulturerbebambergapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Diese Klasse h�ndelt die SQL-Database 'Weltkulturebe'
 *
 * @author Projekt-Seminar "Schnitzeljagd World-heritage" 2015/2016 des Clavius Gymnasiums Bamberg
 * @version 1.0
 * @since 2015-06-04
 */
public class WeltkulturerbeDatabaseHelper extends SQLiteOpenHelper{

    /** Name und Version der Datenbank **/
    private static final String DATABASE_WELTKULTURERBE = "weltkulturerbe.db";
    private static final int DATABASE_VERSION = 1;

    public WeltkulturerbeDatabaseHelper(Context context) {
        super(context, DATABASE_WELTKULTURERBE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        KurzeRouteTable.onCreate(db);
        LangeRouteTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO onUpgrade() schreiben
    }
}
