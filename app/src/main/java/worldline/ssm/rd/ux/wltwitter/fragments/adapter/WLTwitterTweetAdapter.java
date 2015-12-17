package worldline.ssm.rd.ux.wltwitter.fragments.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.WLTwitterApplication;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by mb-p_pilou on 17/12/2015.
 */
public class WLTwitterTweetAdapter extends BaseAdapter {

    private LayoutInflater inflater = LayoutInflater.from(WLTwitterApplication.getContext());
    List<Tweet> tweets;

    public WLTwitterTweetAdapter(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    @Override
    public int getCount() {
        return null != tweets ? tweets.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null != tweets ? tweets.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //inflate the view of the tweet adapter
        final View view = inflater.inflate(R.layout.tweet_fragment_adapter,null);
        //get the current tweet
        final Tweet tweet = (Tweet) getItem(position);
        //set up username
        //
        TextView username = (TextView) view.findViewById(R.id.tweet_username);
        username.setText(tweet.user.name);
        //set up alias
        final TextView alias = (TextView) view.findViewById(R.id.tweet_alias);
        alias.setText("@"+tweet.user.screenName);
        //set up tweet
        final TextView tweetContent = (TextView) view.findViewById(R.id.tweet_content);
        tweetContent.setText(tweet.text);
        //set up user image
        final ImageView image = (ImageView) view.findViewById(R.id.image);
        //image.setImageURI(t);
        return view;
    }
}
