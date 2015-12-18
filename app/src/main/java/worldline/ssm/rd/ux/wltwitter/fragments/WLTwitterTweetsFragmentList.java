package worldline.ssm.rd.ux.wltwitter.fragments;


import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.async.RetrieveTweetsAsyncTask;
import worldline.ssm.rd.ux.wltwitter.fragments.adapter.WLTwitterTweetAdapter;
import worldline.ssm.rd.ux.wltwitter.interfaces.WLTwitterTweetChangeListener;
import worldline.ssm.rd.ux.wltwitter.interfaces.WLTwitterTweetListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class WLTwitterTweetsFragmentList extends Fragment implements WLTwitterTweetChangeListener, AdapterView.OnItemClickListener {

    private RetrieveTweetsAsyncTask retrieveTweetsAsyncTask;
    private RecyclerView recyclerView;
    //private ListView listView;
    private WLTwitterTweetListener tweetListener;


    public WLTwitterTweetsFragmentList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View tweetsListView = inflater.inflate(R.layout.fragment_wltwitter_tweets_fragment_list, container, false);

        //get the RecycleView from xml
        recyclerView = (RecyclerView) tweetsListView.findViewById(R.id.wifiRecyclerView);

        //Generate a progressBar from an emptyListView
        final ProgressBar progressBar = new ProgressBar(getActivity());
        progressBar.setLayoutParams(new
                ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER));
        progressBar.setIndeterminate(true);
        //listView.setEmptyView(progressBar);


        //ViewGroup listFragmentView = (ViewGroup) tweetsListView.findViewById(R.id.tweets_list_fragment);
        //listFragmentView.addView(progressBar);

        //set up ItemClick
        //recyclerView.setOnClickListener(this);

        return tweetsListView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!TextUtils.isEmpty(PreferenceUtils.getLogin())) {
            //new AsyncTask
            retrieveTweetsAsyncTask = new RetrieveTweetsAsyncTask(this);
            retrieveTweetsAsyncTask.execute(PreferenceUtils.getLogin());
        }
    }

    @Override
    public void onTweetRetrieved(List<Tweet> tweets) {
        WLTwitterTweetAdapter tweetAdapter = new WLTwitterTweetAdapter(tweets);
        //set up tweet adapter
        recyclerView.setAdapter(tweetAdapter);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof WLTwitterTweetListener) {
            //Pass the Activity threw a listener
            tweetListener = (WLTwitterTweetListener) activity;
        }
    }

   @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        if (recyclerView != null) {
            //get the tweet which you clicked on by his position
            final Tweet tweet = (Tweet) adapterView.getItemAtPosition(position);
            //throw a toast
            tweetListener.onViewTweet(tweet);
        }
    }
}
