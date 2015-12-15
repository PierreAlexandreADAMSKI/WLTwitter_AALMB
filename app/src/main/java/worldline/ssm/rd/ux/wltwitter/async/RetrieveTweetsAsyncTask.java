package worldline.ssm.rd.ux.wltwitter.async;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.helpers.TwitterHelper;
import worldline.ssm.rd.ux.wltwitter.interfaces.WLTwitterTweetChangeListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by mb-p_pilou on 10/12/2015.
 */
public class RetrieveTweetsAsyncTask extends AsyncTask<String, Integer, List<Tweet>>{

    private WLTwitterTweetChangeListener tweetChangeListener;

    public RetrieveTweetsAsyncTask(WLTwitterTweetChangeListener tweetChangeListener) {
        this.tweetChangeListener = tweetChangeListener;
    }

    @Override
    protected List<Tweet> doInBackground(String... params) {
        String login = null;
        if (params!=null && params.length>0) login = params[0];
        if (login != null){
            List<Tweet> tweetList = TwitterHelper.getTweetsOfUser(login);
            return tweetList;
        }else return TwitterHelper.getFakeTweets();
    }

    @Override
    protected void onPostExecute(List<Tweet> tweets) {
        super.onPostExecute(tweets);
        if (tweetChangeListener !=null){
            tweetChangeListener.onTweetRetrieved(tweets);
        }
        for (Tweet tweet : tweets){
            Log.d("Tweets",tweet.text);
        }

    }

}
