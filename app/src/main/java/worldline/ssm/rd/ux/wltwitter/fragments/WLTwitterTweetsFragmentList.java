package worldline.ssm.rd.ux.wltwitter.fragments;


import android.app.ActionBar;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.WLTwitterApplication;
import worldline.ssm.rd.ux.wltwitter.async.RetrieveTweetsAsyncTask;
import worldline.ssm.rd.ux.wltwitter.database.WLTwitterDatabaseContract;
import worldline.ssm.rd.ux.wltwitter.database.WLTwitterDatabaseManager;
import worldline.ssm.rd.ux.wltwitter.decoration.SpacesItemDecoration;
import worldline.ssm.rd.ux.wltwitter.fragments.adapter.WLTwitterTweetAdapter;
import worldline.ssm.rd.ux.wltwitter.listeners.WLTwitterTweetChangeListener;
import worldline.ssm.rd.ux.wltwitter.listeners.WLTwitterTweetListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class WLTwitterTweetsFragmentList extends Fragment implements WLTwitterTweetChangeListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final Integer DIVIDER_HEIGHT = 20;
    private RetrieveTweetsAsyncTask retrieveTweetsAsyncTask;
    private RecyclerView recyclerView;
    //private ListView listView;
    private WLTwitterTweetListener tweetListener;
    private WLTwitterTweetAdapter tweetAdapter;


    public WLTwitterTweetsFragmentList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View recyclerView_ = inflater.inflate(R.layout.fragment_wltwitter_tweets_fragment_list, container, false);

        //get the RecycleView from xml
        this.recyclerView = (RecyclerView) recyclerView_.findViewById(R.id.recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(WLTwitterApplication.getContext());
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.addItemDecoration(new SpacesItemDecoration(DIVIDER_HEIGHT));

        //Generate a progressBar from an emptyListView
        final ProgressBar progressBar = new ProgressBar(getActivity());
        progressBar.setLayoutParams(new
                ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER));
        progressBar.setIndeterminate(true);

        return recyclerView_;
    }

    @Override
    public void onStart() {
        super.onStart();
        //new AsyncTask
        this.retrieveTweetsAsyncTask = new RetrieveTweetsAsyncTask(this);
        this.retrieveTweetsAsyncTask.execute(PreferenceUtils.getLogin());
        //load data using cursor
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onTweetRetrieved(List<Tweet> tweets) {
        this.tweetAdapter = new WLTwitterTweetAdapter(tweets, this.tweetListener);
        //set up tweet adapter
        this.recyclerView.setAdapter(this.tweetAdapter);
        WLTwitterDatabaseManager.testDatabase(tweets);
        WLTwitterDatabaseManager.testContentProvider(tweets);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof WLTwitterTweetListener) {
            //Pass the Activity threw a listener
            this.tweetListener = (WLTwitterTweetListener) context;
        }
    }

    @Override
    public android.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        final CursorLoader cursorLoader = new CursorLoader(WLTwitterApplication.getContext());
        cursorLoader.setUri(WLTwitterDatabaseContract.TWEETS_URI);
        cursorLoader.setProjection(WLTwitterDatabaseContract.PROJECTION_FULL);
        cursorLoader.setSelection(null);
        cursorLoader.setSelectionArgs(null);
        cursorLoader.setSortOrder(null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(android.content.Loader<Cursor> loader, Cursor data) {
        if (data!=null){
            while(data.moveToNext()){
                final Tweet tweet = WLTwitterDatabaseManager.tweetFromCursor(data);
                Log.d("tweets fragment", tweet.toString());
            }
            if(!data.isClosed()){
                data.close();
            }
        }
    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) {
        loader.reset();
    }
}