package worldline.ssm.rd.ux.wltwitter.fragments.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.WLTwitterApplication;
import worldline.ssm.rd.ux.wltwitter.listeners.WLTwitterTweetListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by mb-p_pilou on 17/12/2015.
 */
public class WLTwitterTweetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public ImageView image;
    public TextView username;
    public TextView alias;
    public TextView tweetContent;
    public Button retweet;
    private Tweet tweet;
    private WLTwitterTweetListener tweetListener;

    public WLTwitterTweetViewHolder(View fragmentAdapterView) {
        super(fragmentAdapterView);
        fragmentAdapterView.setOnClickListener(this);
        this.image = (ImageView) fragmentAdapterView.findViewById(R.id.image);
        this.username = (TextView) fragmentAdapterView.findViewById(R.id.tweet_username);
        this.alias = (TextView) fragmentAdapterView.findViewById(R.id.tweet_alias);
        this.tweetContent = (TextView) fragmentAdapterView.findViewById(R.id.tweet_content);
        this.retweet = (Button) fragmentAdapterView.findViewById(R.id.tweet_retweet);
    }

    public void setTweetListener(WLTwitterTweetListener tweetListener) {
        this.tweetListener = tweetListener;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
        this.username.setText(tweet.user.name);
        this.alias.setText("@" + tweet.user.screenName);
        this.tweetContent.setText(tweet.text);
        if (!tweet.user.profileImageUrl.isEmpty()) {
            Picasso.with(WLTwitterApplication.getContext()).load(tweet.user.profileImageUrl).into(this.image);
        }else {
            Picasso.with(WLTwitterApplication.getContext()).load(R.drawable.twitter).into(this.image);
        }
    }


    @Override
    public void onClick(View v) {
        this.tweetListener.onViewTweet(this.tweet);
    }
}