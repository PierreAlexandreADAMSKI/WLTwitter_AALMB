package worldline.ssm.rd.ux.wltwitter.fragments;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.WLTwitterApplication;
import worldline.ssm.rd.ux.wltwitter.listeners.WLTwitterTweetListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * A simple {@link Fragment} subclass.
 */
public class WLTwitterTweetFragment extends Fragment implements View.OnClickListener {

    private Tweet tweet;
    private View view;
    private WLTwitterTweetListener tweetListener;

    public static WLTwitterTweetFragment newInstance(Tweet tweet) {
        final WLTwitterTweetFragment tweetFragment = new WLTwitterTweetFragment();
        final Bundle bundle = new Bundle();
        bundle.putParcelable(WLTwitterApplication.getContext().getString(R.string.tweet_parcelable), tweet);
        tweetFragment.setArguments(bundle);
        return tweetFragment;
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tweet, container, false);

        tweet = getArguments().getParcelable(getString(R.string.tweet_parcelable));

        TextView name = (TextView) view.findViewById(R.id.name);
        TextView alias = (TextView) view.findViewById(R.id.alias);
        TextView text = (TextView) view.findViewById(R.id.text);
        ImageView image = (ImageView) view.findViewById(R.id.img);
        name.setText(tweet.user.name);
        alias.setText("(@" + tweet.user.screenName + ")");
        text.setText(tweet.text);

        Button reply = (Button) view.findViewById(R.id.tweetReply);
        Button retweet = (Button) view.findViewById(R.id.tweetRetweet);
        Button star = (Button) view.findViewById(R.id.tweetStar);

        reply.setOnClickListener(this);
        retweet.setOnClickListener(this);
        star.setOnClickListener(this);

        if (!tweet.user.profileImageUrl.isEmpty()) {
            Picasso.with(WLTwitterApplication.getContext()).load(tweet.user.profileImageUrl).into(image);
        } else {
            Picasso.with(WLTwitterApplication.getContext()).load(R.drawable.twitter).into(image);
        }

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof WLTwitterTweetListener) {
            //Pass the Activity threw a listener
            tweetListener = (WLTwitterTweetListener) context;
        }
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        // each button has a different action
        if (view != null) {
            switch (button.getId()) {
                case R.id.tweetRetweet:
                    //action is done on activity
                    tweetListener.onRetweet(tweet);
                    break;
                case R.id.tweetReply:
                    tweetListener.onReply(tweet);
                    break;
                case R.id.tweetStar:
                    tweetListener.onStar(tweet);
                    break;
                default:
                    break;
            }
        }
    }
}
