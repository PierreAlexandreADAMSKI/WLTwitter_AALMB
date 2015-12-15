package worldline.ssm.rd.ux.wltwitter;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.async.RetrieveTweetsAsyncTask;
import worldline.ssm.rd.ux.wltwitter.fragments.WLTwitterTweetsFragmentList;
import worldline.ssm.rd.ux.wltwitter.interfaces.WLTwitterTweetChangeListener;
import worldline.ssm.rd.ux.wltwitter.interfaces.WLTwitterTweetListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.utils.Constants;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceUtils;


public class WLTwitterActivity extends Activity implements WLTwitterTweetListener {

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * We use the method SetContentView to link layout
         * it takes an integer as an ID for the ressource (the XML file of the layout)
         * this integer, is generated automatically (R.layout.nameoftheactivity)
         */

        setContentView(R.layout.activity_main);
        final Intent intent = getIntent(); //get the Intent that started the activity
        if (null != intent) {
            final Bundle extras = intent.getExtras(); //get the "map" of the data

            if ((null != extras) && (extras.containsKey(Constants.Login.EXTRA_LOGIN))) {
                final String login = extras.getString(Constants.Login.EXTRA_LOGIN);
                final String test = PreferenceUtils.getLogin();
                //set user name as subtitle
                getActionBar().setSubtitle(test);

            }
        }

        FragmentTransaction tweetListTransaction = getFragmentManager().beginTransaction();
        tweetListTransaction.add(R.id.main_activity, new WLTwitterTweetsFragmentList());
        tweetListTransaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.wltwitter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.actionLogout) {
            PreferenceUtils.setLogin(null);
            PreferenceUtils.setPassword(null);

            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRetweet(Tweet tweet) {

    }

    @Override
    public void onViewTweet(Tweet tweet) {
        Toast.makeText(this, tweet.text, Toast.LENGTH_LONG).show();
    }
}
