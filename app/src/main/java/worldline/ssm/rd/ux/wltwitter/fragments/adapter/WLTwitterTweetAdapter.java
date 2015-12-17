package worldline.ssm.rd.ux.wltwitter.fragments.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.WLTwitterApplication;
import worldline.ssm.rd.ux.wltwitter.fragments.viewHolders.WLTwitterTweetViewHolder;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by mb-p_pilou on 17/12/2015.
 */
public class WLTwitterTweetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater = LayoutInflater.from(WLTwitterApplication.getContext());
    private List<Tweet> tweets;

    public WLTwitterTweetAdapter(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        WLTwitterTweetViewHolder viewHolder;
        View convertView = parent.getChildAt(getItemCount());
        //if the reused view is null then inflate the list of tweets
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.tweet_fragment_adapter, null);
            //instantiate the viewHolder on the reused view
            viewHolder = new WLTwitterTweetViewHolder(convertView);
            //tag to convertView to get it back easily
            convertView.setTag(viewHolder);
        } else {
            //if the old view exists set the holder by tag
            viewHolder = (WLTwitterTweetViewHolder) convertView.getTag();
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        //get the current tweet
        final Tweet tweet = getItem(position);
        //set up username in the holder
        ((WLTwitterTweetViewHolder) viewHolder).username.setText(tweet.user.name);
        //set up alias
        ((WLTwitterTweetViewHolder) viewHolder).alias.setText("@" + tweet.user.screenName);
        //set up tweet
        ((WLTwitterTweetViewHolder) viewHolder).tweetContent.setText(tweet.text);
        //set up user image
    }

    @Override
    public int getItemCount() {
        return tweets != null ? tweets.size() : 0;
    }

    public Tweet getItem(int position) {
        return tweets != null ? tweets.get(position) : null;
    }
}
