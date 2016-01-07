package worldline.ssm.rd.ux.wltwitter.interfaces;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by mb-p_pilou on 15/12/2015.
 */
public interface WLTwitterTweetChangeListener {
    public void onTweetRetrieved(List<Tweet> tweets);
}
