package worldline.ssm.rd.ux.wltwitter.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.WLTwitterApplication;
import worldline.ssm.rd.ux.wltwitter.helpers.WLTwitterDatabaseHelper;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.pojo.TwitterUser;
import worldline.ssm.rd.ux.wltwitter.providers.WLTwitterDatabaseProvider;
import worldline.ssm.rd.ux.wltwitter.utils.Constants;

import static worldline.ssm.rd.ux.wltwitter.database.WLTwitterDatabaseContract.DATE_CREATED;
import static worldline.ssm.rd.ux.wltwitter.database.WLTwitterDatabaseContract.DATE_CREATED_TIMESTAMP;
import static worldline.ssm.rd.ux.wltwitter.database.WLTwitterDatabaseContract.PROJECTION_FULL;
import static worldline.ssm.rd.ux.wltwitter.database.WLTwitterDatabaseContract.TABLE_TWEETS;
import static worldline.ssm.rd.ux.wltwitter.database.WLTwitterDatabaseContract.TEXT;
import static worldline.ssm.rd.ux.wltwitter.database.WLTwitterDatabaseContract.TWEETS_URI;
import static worldline.ssm.rd.ux.wltwitter.database.WLTwitterDatabaseContract.USER_ALIAS;
import static worldline.ssm.rd.ux.wltwitter.database.WLTwitterDatabaseContract.USER_IMAGE_URL;
import static worldline.ssm.rd.ux.wltwitter.database.WLTwitterDatabaseContract.USER_NAME;

public class WLTwitterDatabaseManager {

    public static Tweet tweetFromCursor(Cursor c){
        if (null != c){
            final Tweet tweet = new Tweet();
            tweet.user = new TwitterUser();

            // Retrieve the date created
            if (c.getColumnIndex(DATE_CREATED) >= 0){
                tweet.dateCreated = c.getString(c.getColumnIndex(DATE_CREATED));
            }

            // Retrieve the user name
            if (c.getColumnIndex(USER_NAME) >= 0){
                tweet.user.name = c.getString(c.getColumnIndex(USER_NAME));
            }

            // Retrieve the user alias
            if (c.getColumnIndex(USER_ALIAS) >= 0){
                tweet.user.screenName = c.getString(c.getColumnIndex(USER_ALIAS));
            }

            // Retrieve the user image url
            if (c.getColumnIndex(USER_IMAGE_URL) >= 0){
                tweet.user.profileImageUrl = c.getString(c.getColumnIndex(USER_IMAGE_URL));
            }

            // Retrieve the text of the tweet
            if (c.getColumnIndex(TEXT) >= 0){
                tweet.text = c.getString(c.getColumnIndex(TEXT));
            }

            return tweet;
        }
        return null;
    }

    public static ContentValues tweetToContentValues(Tweet tweet){
        final ContentValues values = new ContentValues();

        // Set the date created
        if (!TextUtils.isEmpty(tweet.dateCreated)){
            values.put(DATE_CREATED, tweet.dateCreated);
        }

        // Set the date created as timestamp
        values.put(DATE_CREATED_TIMESTAMP, tweet.getDateCreatedTimestamp());

        // Set the user name
        if (!TextUtils.isEmpty(tweet.user.name)){
            values.put(USER_NAME, tweet.user.name);
        }

        // Set the user alias
        if (!TextUtils.isEmpty(tweet.user.screenName)){
            values.put(USER_ALIAS, tweet.user.screenName);
        }

        // Set the user image url
        if (!TextUtils.isEmpty(tweet.user.profileImageUrl)){
            values.put(USER_IMAGE_URL, tweet.user.profileImageUrl);
        }

        // Set the text of the tweet
        if (!TextUtils.isEmpty(tweet.text)){
            values.put(TEXT, tweet.text);
        }

        return values;
    }

    public static void testDatabase(List<Tweet> tweets){
        WLTwitterDatabaseHelper databaseHelper = new WLTwitterDatabaseHelper(WLTwitterApplication.getContext());
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        for (Tweet tweet : tweets){
            ContentValues contentValues = tweetToContentValues(tweet);
            database.insert(TABLE_TWEETS, "", contentValues);
        }
        //query all entries as cursor
        final Cursor cursor = database.query(TABLE_TWEETS,PROJECTION_FULL,null,null,null,null,null);
        //log the records
        if(cursor!=null) {
            while (cursor.moveToNext()) {
                final Tweet tweet = tweetFromCursor(cursor);
                Log.i(Constants.General.LOG_TAG, "test database : " + tweet.user.name);
                Log.i(Constants.General.LOG_TAG, "test database : " + "@"+tweet.user.screenName);
                Log.i(Constants.General.LOG_TAG, "test database : " + tweet.text);
                Log.i(Constants.General.LOG_TAG, "test database : " + tweet.user.profileImageUrl);
                Log.i(Constants.General.LOG_TAG, "test database : " + tweet.dateCreated);
                Log.i(Constants.General.LOG_TAG, "test database : " + String.valueOf(tweet.getDateCreatedTimestamp()));
            }
            //close cursor 
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }
    }

    public static void testContentProvider(List<Tweet> tweets){
        ContentProvider databaseProvider = new WLTwitterDatabaseProvider();
        // query
        Cursor cursor = databaseProvider.query(TWEETS_URI, PROJECTION_FULL, null, null, null);
        if(cursor!=null) {
            while (cursor.moveToNext()) {
                final Tweet tweet = tweetFromCursor(cursor);
                Log.i(Constants.General.LOG_TAG, "test contentProvider : " + tweet.user.name);
                Log.i(Constants.General.LOG_TAG, "test contentProvider : " + "@"+tweet.user.screenName);
                Log.i(Constants.General.LOG_TAG, "test contentProvider : " + tweet.text);
                Log.i(Constants.General.LOG_TAG, "test contentProvider : " + tweet.user.profileImageUrl);
                Log.i(Constants.General.LOG_TAG, "test contentProvider : " + tweet.dateCreated);
                Log.i(Constants.General.LOG_TAG, "test contentProvider : " + String.valueOf(tweet.getDateCreatedTimestamp()));
            }
            //close cursor
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }
        // insert
        Uri uri = databaseProvider.insert(TWEETS_URI, null);
        if (uri!=null){
            Log.i(Constants.General.LOG_TAG, "test contentProvider uri : " + uri);
        }
        // update
        Log.i(Constants.General.LOG_TAG, "test contentProvider update : " + String.valueOf(databaseProvider.update(TWEETS_URI, null, null, null)));
        // delete
        Log.i(Constants.General.LOG_TAG, "test contentProvider update : " + String.valueOf(databaseProvider.delete(TWEETS_URI, null, null)));
    }
}
