package worldline.ssm.rd.ux.wltwitter.fragments;


import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.async.RetrieveTweetsAsyncTask;
import worldline.ssm.rd.ux.wltwitter.interfaces.WLTwitterTweetsChangeListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceUtils;

/**
 * A simple {@link ListFragment} subclass.
 */
public class WLTwitterTweetsFragmentList extends ListFragment implements WLTwitterTweetsChangeListener {

    private RetrieveTweetsAsyncTask retrieveTweetsAsyncTask;
    private ListView listView;

    public WLTwitterTweetsFragmentList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View tweetsListView = inflater.inflate(R.layout.fragment_wltwitter_tweets_fragment_list, container, false);
        //get the ListView from xml
        listView = (ListView) tweetsListView.findViewById(R.id.tweets_list_view);
        return tweetsListView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(!TextUtils.isEmpty(PreferenceUtils.getLogin())) {
            retrieveTweetsAsyncTask = new RetrieveTweetsAsyncTask(this);
            retrieveTweetsAsyncTask.execute(PreferenceUtils.getLogin());
        }
    }

    @Override
    public void onTweetRetrieved(List<Tweet> tweets) {
        final ArrayAdapter<Tweet> tweetArrayAdapter = new ArrayAdapter<Tweet>(getActivity(), android.R.layout.simple_list_item_1, tweets);
        listView.setAdapter(tweetArrayAdapter);
    }
}
