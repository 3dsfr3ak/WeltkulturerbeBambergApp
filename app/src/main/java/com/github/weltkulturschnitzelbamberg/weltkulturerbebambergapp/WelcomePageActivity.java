package com.github.weltkulturschnitzelbamberg.weltkulturerbebambergapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.github.weltkulturschnitzelbamberg.weltkulturerbebambergapp.Utilities.DebugUtils;
import com.github.weltkulturschnitzelbamberg.weltkulturerbebambergapp.contentprovider.WeltkulturerbeContentProvider;
import com.github.weltkulturschnitzelbamberg.weltkulturerbebambergapp.database.LongRouteTable;

/**
 * This activity is the launch activity of the World-Heritage-Application
 *
 * @author Projekt-Seminar "Schnitzeljagd World-heritage" 2015/2016 des Clavius Gymnasiums Bamberg
 * @version 1.0
 * @since 2015-06-04
 */

public class WelcomePageActivity extends Activity implements AppCompatCallback {
    @Override
    public void onSupportActionModeStarted(ActionMode mode) {
        //let's leave this empty, for now
    }

    @Override
    public void onSupportActionModeFinished(ActionMode mode) {
        // let's leave this empty, for now
    }

    private AppCompatDelegate delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page_app);

        //let's create the delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);
        //we need to call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //we use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_welcome_page_app);

        //Finally, let's add the Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.welcome_toolbar);
        delegate.setSupportActionBar(toolbar);

        Button btn_welcome_start = (Button) findViewById(R.id.btn_welcome_start);
        btn_welcome_start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(WelcomePageActivity.this, InstructionActivity.class));
            }
        });

        Button btn_welcome_continue = (Button) findViewById(R.id.btn_welcome_continue);
        btn_welcome_continue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                query();
            }
        });

        Button btn_welcome_score = (Button) findViewById(R.id.btn_welcome_score);
        btn_welcome_score.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insert();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_score) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void insert()
    {
        ContentValues values =new ContentValues();
        values.put(LongRouteTable.COLUMN_NAME,"CG");
        values.put(LongRouteTable.COLUMN_LONGITUDE,23.34);
        values.put(LongRouteTable.COLUMN_LATITUDE,50.70);
        getContentResolver().insert(WeltkulturerbeContentProvider.URI_TABLE_LONG_ROUTE,values);
    }

    public void query()
    {
        String projection[]={LongRouteTable.COLUMN_NAME,LongRouteTable.COLUMN_LONGITUDE,LongRouteTable.COLUMN_LATITUDE};
        String selection=LongRouteTable.COLUMN_NAME+"=?";
        String selectionArgs[]={"Bamberger Dom"};
        String sortOrder=null;
        Cursor cursor=getContentResolver().query(WeltkulturerbeContentProvider.URI_TABLE_LONG_ROUTE,projection,selection,selectionArgs,sortOrder);
        String result="";
        while(cursor.moveToNext())
        {result+=cursor.getString(cursor.getColumnIndex(LongRouteTable.COLUMN_NAME));
            result+=", "+cursor.getFloat(cursor.getColumnIndex(LongRouteTable.COLUMN_LONGITUDE));
            result+=", "+cursor.getFloat(cursor.getColumnIndex(LongRouteTable.COLUMN_LATITUDE));
            result+="/n";
                DebugUtils.toast(this,result);

        }
    }
}