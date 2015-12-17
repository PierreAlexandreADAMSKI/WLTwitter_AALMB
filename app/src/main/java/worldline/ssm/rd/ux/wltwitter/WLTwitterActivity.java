package worldline.ssm.rd.ux.wltwitter;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


import worldline.ssm.rd.ux.wltwitter.fragments.WLTwitterTweetsFragmentList;
import worldline.ssm.rd.ux.wltwitter.interfaces.WLTwitterTweetListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.utils.Constants;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceUtils;

import static worldline.ssm.rd.ux.wltwitter.WLTwitterLoginActivity.login;
import static worldline.ssm.rd.ux.wltwitter.WLTwitterLoginActivity.password;
import static worldline.ssm.rd.ux.wltwitter.WLTwitterLoginActivity.saveLoginCheckBox;


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
        // Thomas test le commit!

        setContentView(R.layout.activity_main);
        final Intent intent = getIntent(); //get the Intent that started the activity
        if (null != intent) {
            final Bundle extras = intent.getExtras(); //get the "map" of the data

            if ((null != extras) && (extras.containsKey(Constants.Login.EXTRA_LOGIN))) {
                final String login = PreferenceUtils.getLogin();
                //set user name as subtitle
                getActionBar().setSubtitle(login);

                //create a transaction to the fragment threw FragmentManager
                FragmentTransaction tweetListTransaction = getFragmentManager().beginTransaction();
                //add the Fragment container "main_activity" by id to the Transaction and tell the target fragment
                tweetListTransaction.add(R.id.main_activity, new WLTwitterTweetsFragmentList());
                //commit transaction
                tweetListTransaction.commit();
            }
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.wltwitter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // get the id of the incoming item
        int id = item.getItemId();

        //logout
        if (id == R.id.actionLogout) {
            if (!saveLoginCheckBox.isChecked()) {
                //clear preferences but not TextView
                login.setText("", TextView.BufferType.EDITABLE);
                password.setText("", TextView.BufferType.EDITABLE);
            }
            PreferenceUtils.setLogin(null);
            PreferenceUtils.setPassword(null);
            //end of activity
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
        //throw a toast
        Toast.makeText(this, tweet.text, Toast.LENGTH_LONG).show();
    }
}
