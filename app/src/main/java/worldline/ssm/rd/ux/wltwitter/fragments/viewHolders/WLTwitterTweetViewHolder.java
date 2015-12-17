package worldline.ssm.rd.ux.wltwitter.fragments.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import worldline.ssm.rd.ux.wltwitter.R;

/**
 * Created by mb-p_pilou on 17/12/2015.
 */
public class WLTwitterTweetViewHolder extends RecyclerView.ViewHolder {
    public ImageView image;
    public TextView username;
    public TextView alias;
    public TextView tweetContent;
    public Button retweet;

    public WLTwitterTweetViewHolder(View fragmentAdapterView) {
        super(fragmentAdapterView);
        this.image = (ImageView) fragmentAdapterView.findViewById(R.id.image);
        this.username = (TextView) fragmentAdapterView.findViewById(R.id.tweet_username);
        this.alias = (TextView) fragmentAdapterView.findViewById(R.id.tweet_alias);
        this.tweetContent = (TextView) fragmentAdapterView.findViewById(R.id.tweet_content);
        this.retweet = (Button) fragmentAdapterView.findViewById(R.id.tweet_retweet);
    }
}