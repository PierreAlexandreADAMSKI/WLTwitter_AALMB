package worldline.ssm.rd.ux.wltwitter.async;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;
import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.helpers.TwitterHelper;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by mb-p_pilou on 10/12/2015.
 */
public class RetrieveTweetsAsyncTask extends AsyncTask<String, Void, List<Tweet>>{

    @Override
    protected List<Tweet> doInBackground(String... params) {
        String login = params[0];
        if (login != null){
            List<Tweet> tweetList = TwitterHelper.getTweetsOfUser(login);
            Log.i("WLTwitterActivity","RetrieveTweetsAsyncTask.doInBackground(login) - tweet list " + tweetList);
            return tweetList;
        }else return null;
    }

    @Override
    protected void onPostExecute(List<Tweet> tweets) {
        super.onPostExecute(tweets);
        for (Tweet tweet : tweets){
            System.out.println("["+R.string.app_name+"]"+tweet.text);
        }

    }

}
