package worldline.ssm.rd.ux.wltwitter.fragments.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.WLTwitterApplication;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by mb-p_pilou on 17/12/2015.
 */
public class WLTwitterTweetAdapter extends BaseAdapter {

    private List<Tweet> tweets;
    private LayoutInflater inflater;


    public WLTwitterTweetAdapter(List<Tweet> tweets) {
        this.tweets = tweets;
        inflater = LayoutInflater.from(WLTwitterApplication.getContext());
    }

    private class ViewHolder {
        public ImageView image;
        public TextView username;
        public TextView alias;
        public TextView tweetContent;

        public ViewHolder(View view) {
            this.image = (ImageView) view.findViewById(R.id.image);
            this.username = (TextView) view.findViewById(R.id.tweet_username);
            this.alias = (TextView) view.findViewById(R.id.tweet_alias);
            this.tweetContent = (TextView) view.findViewById(R.id.tweet_content);
        }
    }

    @Override
    public int getCount() {
        return tweets != null ? tweets.size() : 0;
    }

    @Override
    public Tweet getItem(int position) {
        return tweets != null ? tweets.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        //this.convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweet_fragment_adapter, null);
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

        final Tweet tweet = getItem(position);
        //set up username in the holder

        viewHolder.username.setText(tweet.user.name);
        //set up alias
        viewHolder.alias.setText("@" + tweet.user.screenName);
        //set up tweet
        viewHolder.tweetContent.setText(tweet.text);

        if (!tweet.user.profileImageUrl.isEmpty()) {
            Picasso.with(WLTwitterApplication.getContext()).load(tweet.user.profileImageUrl).into(viewHolder.image);
        }

        return convertView;
    }
}
