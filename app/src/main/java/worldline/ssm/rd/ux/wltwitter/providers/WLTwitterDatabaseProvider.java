package worldline.ssm.rd.ux.wltwitter.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import worldline.ssm.rd.ux.wltwitter.database.WLTwitterDatabaseContract;
import worldline.ssm.rd.ux.wltwitter.helpers.WLTwitterDatabaseHelper;
import worldline.ssm.rd.ux.wltwitter.utils.Constants;

public class WLTwitterDatabaseProvider extends ContentProvider {
    public WLTwitterDatabaseProvider() {
    }

    private static final int TWEET_URI_CODE = 42;
    private WLTwitterDatabaseHelper databaseHelper;
    private UriMatcher uriMatcher;


    @Override
    public boolean onCreate() {
        this.databaseHelper = new WLTwitterDatabaseHelper(getContext());
        this.uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        this.uriMatcher.addURI(WLTwitterDatabaseContract.CONTENT_PROVIDER_TWEETS_AUTHORITY,
                WLTwitterDatabaseContract.TABLE_TWEETS,
                TWEET_URI_CODE);
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.v(Constants.General.LOG_TAG, "QUERY");
        return databaseHelper.getReadableDatabase().query(WLTwitterDatabaseContract.TABLE_TWEETS,projection,selection,selectionArgs,sortOrder,null,null);
    }


    @Nullable
    @Override
    public String getType(Uri uri) {
        if(this.uriMatcher.match(uri) == TWEET_URI_CODE){
            return WLTwitterDatabaseContract.TWEETS_CONTENT_TYPE;
        }
        throw new IllegalArgumentException("unknown URI : "+uri);
    }

    @Nullable
    @Override
    public Uri insert(Uri arg0, ContentValues arg1) {
        Log.i(Constants.General.LOG_TAG,"INSERT");
        databaseHelper.getReadableDatabase().insert(WLTwitterDatabaseContract.TABLE_TWEETS, null, arg1);
        return arg0;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.e(Constants.General.LOG_TAG, "DELETE");
        return databaseHelper.getReadableDatabase().delete(WLTwitterDatabaseContract.TABLE_TWEETS,selection,selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.d(Constants.General.LOG_TAG,"UPDATE");
        return databaseHelper.getReadableDatabase().update(WLTwitterDatabaseContract.TABLE_TWEETS,values,selection,selectionArgs);
    }
}
