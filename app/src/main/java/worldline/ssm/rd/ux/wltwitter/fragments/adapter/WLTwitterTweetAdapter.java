package worldline.ssm.rd.ux.wltwitter.fragments.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.WLTwitterApplication;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by mb-p_pilou on 17/12/2015.
 */
public class WLTwitterTweetAdapter extends RecyclerView.Adapter<WLTwitterTweetAdapter.ViewHolder> {

    private LayoutInflater inflater = LayoutInflater.from(WLTwitterApplication.getContext());
    private List<Tweet> tweets;

    public WLTwitterTweetAdapter(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView username;
        public TextView alias;
        public TextView tweetContent;
        public Button retweet;

        public ViewHolder(View view) {
            super(view);
            this.image = (ImageView) view.findViewById(R.id.image);
            this.username = (TextView) view.findViewById(R.id.tweet_username);
            this.alias = (TextView) view.findViewById(R.id.tweet_alias);
            this.tweetContent = (TextView) view.findViewById(R.id.tweet_content);
            this.retweet = (Button) view.findViewById(R.id.tweet_retweet);
        }
    }

    @Override
    public WLTwitterTweetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder;
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweet_fragment_adapter,parent,false);
        //if the reused view is null then inflate the list of tweets
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.tweet_fragment_adapter, null);
            //instantiate the viewHolder on the reused view
            viewHolder = new ViewHolder(convertView);
            //tag to convertView to get it back easily
            convertView.setTag(viewHolder);
        } else {
            //if the old view exists set the holder by tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        //get the current tweet
        final Tweet tweet = getItem(position);
        //set up username in the holder
        viewHolder.username.setText(tweet.user.name);
        //set up alias
        viewHolder.alias.setText("@" + tweet.user.screenName);
        //set up tweet
        viewHolder.tweetContent.setText(tweet.text);
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
