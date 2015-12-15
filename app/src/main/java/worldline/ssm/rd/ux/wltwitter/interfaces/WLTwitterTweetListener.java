package worldline.ssm.rd.ux.wltwitter.interfaces;

import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by mb-p_pilou on 15/12/2015.
 */
public interface WLTwitterTweetListener {
    public void onRetweet(Tweet tweet);
    public void onViewTweet(Tweet tweet);
}
