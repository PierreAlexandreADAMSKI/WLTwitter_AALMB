package worldline.ssm.rd.ux.wltwitter.fragments.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.WLTwitterApplication;
import worldline.ssm.rd.ux.wltwitter.fragments.viewHolders.WLTwitterTweetViewHolder;
import worldline.ssm.rd.ux.wltwitter.listeners.WLTwitterTweetListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by mb-p_pilou on 17/12/2015.
 */
public class WLTwitterTweetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater = LayoutInflater.from(WLTwitterApplication.getContext());
    private List<Tweet> tweets;
    private WLTwitterTweetViewHolder viewHolder;
    private WLTwitterTweetListener tweetListener;

    public WLTwitterTweetAdapter(List<Tweet> tweets, WLTwitterTweetListener tweetListener) {
        this.tweets = tweets;
        this.tweetListener = tweetListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = parent.getChildAt(getItemCount());
        //if the reused view is null then inflate the list of tweets
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.tweet_fragment_adapter, null);
            //instantiate the viewHolder on the reused view
            this.viewHolder = new WLTwitterTweetViewHolder(convertView);
            this.viewHolder.setTweetListener(tweetListener);
            //tag to convertView to get it back easily
            convertView.setTag(this.viewHolder);
        } else {
            //if the old view exists set the holder by tag
            this.viewHolder = (WLTwitterTweetViewHolder) convertView.getTag();
        }
        return this.viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        //get the current tweet
        Tweet tweet = getItem(position);
        this.viewHolder.setTweet(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets != null ? tweets.size() : 0;
    }

    public Tweet getItem(int position) {
        return tweets != null ? tweets.get(position) : null;
    }
}
