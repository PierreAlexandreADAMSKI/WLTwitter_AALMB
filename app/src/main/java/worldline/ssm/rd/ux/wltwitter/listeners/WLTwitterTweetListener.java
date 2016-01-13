package worldline.ssm.rd.ux.wltwitter.listeners;

import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by mb-p_pilou on 15/12/2015.
 */
public interface WLTwitterTweetListener {
    void onReply(Tweet tweet);
    void onRetweet(Tweet tweet);
    void onStar(Tweet tweet);
    void onViewTweet(Tweet tweet);
}
