package worldline.ssm.rd.ux.wltwitter.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static worldline.ssm.rd.ux.wltwitter.database.WLTwitterDatabaseContract.*;
import static worldline.ssm.rd.ux.wltwitter.database.WLTwitterDatabaseContract.DROP_TABLE_IF_EXISTS;
import static worldline.ssm.rd.ux.wltwitter.database.WLTwitterDatabaseContract.TABLE_TWEETS;
import static worldline.ssm.rd.ux.wltwitter.database.WLTwitterDatabaseContract.TABLE_TWEETS_CREATE_SCRIPT;


/**
 * Created by mb-p_pilou on 07/01/2016.
 */
public class WLTwitterDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "twitter_tweets_db";
    private static final int DATABASE_VERSION = 1;

    public WLTwitterDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create the database
        db.execSQL(TABLE_TWEETS_CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop tweets table
        db.execSQL(DROP_TABLE_IF_EXISTS+TABLE_TWEETS);
        //reCreate the database
        onCreate(db);
    }
}
