package worldline.ssm.rd.ux.wltwitter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import worldline.ssm.rd.ux.wltwitter.utils.Constants;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceUtils;

/**
 * Created by isen on 03/12/2015.
 */
public class WLTwitterLoginActivity extends Activity implements View.OnClickListener {

    private EditText mLoginEditText;
    private EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.loginButton).setOnClickListener(this);
        /**
         * findViewById permit to locate the component which interest us to interact with it
         * @loginButton here we get the View thanks to the ID of LoginButton (component Button, in
         * login layout).
         * We get the reference only in the onCreate method,
         * if we want to manipulate the component we do it into the onResume method
         *
         * To intercept the event occuring on we implement our object view with the method
         * setOnClickListener
         * @this is token by setOnClickListener
         */
    }

    /**
     * @param v type : View
     *          We verify in the onClick method that login and password fields are not Empty
     *          We print it with a Toast. It's a message which is printed on the screen
     *          in a short time. [It's also used for debugging by the developpers
     *          <p/>
     *          To print the text, we use the static makeText method and the show method implemented in
     *          the Toast class. makeText will construct the message as a toast and show will print it
     *          makeText(Context context, CharSequence text, int duration)
     */
    @Override
    public void onClick(View v) {
        if (TextUtils.isEmpty(mLoginEditText.getText())) {
            Toast.makeText(this, R.string.error_no_login, Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(mPasswordEditText.getText())) {
            Toast.makeText(this, R.string.error_no_password, Toast.LENGTH_LONG).show();
            return;
        }
        final Intent homeIntent = getNameActivityIntent(mLoginEditText.getText().toString());
        PreferenceUtils.setLogin(mLoginEditText.getText().toString());
        PreferenceUtils.setPassword(mPasswordEditText.getText().toString());
        startActivity(homeIntent);
    }

    /**
     * getNameActivityIntent return an Intent, as we already know, an Intent can store serializable
     * data, data which will be given for the activity to be launched.
     * This data must be stock in a Bundle (object type), as a pair key/value
     * We use the putExtra method to store it. putString method permit to set a String value
     * in the preference editor.
     *
     * @param userName
     * @return Intent
     */
    private Intent getNameActivityIntent(String userName) {
        Intent intent = new Intent(this, WLTwitterActivity.class);
        final Bundle extras = new Bundle();
        extras.putString(Constants.Login.EXTRA_LOGIN, userName);
        intent.putExtras(extras);
        return intent;
    }
}

