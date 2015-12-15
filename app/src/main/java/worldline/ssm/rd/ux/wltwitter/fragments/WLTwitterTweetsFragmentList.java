package worldline.ssm.rd.ux.wltwitter.fragments;


import android.app.ActionBar;
import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.async.RetrieveTweetsAsyncTask;
import worldline.ssm.rd.ux.wltwitter.interfaces.WLTwitterTweetChangeListener;
import worldline.ssm.rd.ux.wltwitter.interfaces.WLTwitterTweetListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceUtils;

/**
 * A simple {@link ListFragment} subclass.
 */
public class WLTwitterTweetsFragmentList extends ListFragment implements WLTwitterTweetChangeListener, AdapterView.OnItemClickListener {

    private RetrieveTweetsAsyncTask retrieveTweetsAsyncTask;
    private ListView listView;
    private WLTwitterTweetListener tweetListener;


    public WLTwitterTweetsFragmentList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View tweetsListView = inflater.inflate(R.layout.fragment_wltwitter_tweets_fragment_list, container, false);

        //get the ListView from xml
        listView = (ListView) tweetsListView.findViewById(R.id.tweets_list_view);

        //Generate a progressBar from an emptyListView
        final ProgressBar progressBar = new ProgressBar(getActivity());
        progressBar.setLayoutParams(new
                ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER));
        progressBar.setIndeterminate(true);
        listView.setEmptyView(progressBar);


        ViewGroup listFragmentView = (ViewGroup) tweetsListView.findViewById(R.id.tweets_list_fragment);
        listFragmentView.addView(progressBar);

        listView.setOnItemClickListener(this);

        return tweetsListView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(!TextUtils.isEmpty(PreferenceUtils.getLogin())) {
            //new AsyncTask
            retrieveTweetsAsyncTask = new RetrieveTweetsAsyncTask(this);
            retrieveTweetsAsyncTask.execute(PreferenceUtils.getLogin());
        }
    }

    @Override
    public void onTweetRetrieved(List<Tweet> tweets) {
        final ArrayAdapter<Tweet> tweetArrayAdapter = new ArrayAdapter<Tweet>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                tweets);
        listView.setAdapter(tweetArrayAdapter);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof WLTwitterTweetListener){
            tweetListener = (WLTwitterTweetListener) activity;
        }
    }

    @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        if (listView != null){
            final Tweet tweet = (Tweet) adapterView.getItemAtPosition(position);
            tweetListener.onViewTweet(tweet);
        }
    }
}
