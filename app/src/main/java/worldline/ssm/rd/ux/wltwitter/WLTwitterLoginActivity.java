package worldline.ssm.rd.ux.wltwitter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import worldline.ssm.rd.ux.wltwitter.utils.Constants;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceUtils;

/**
 * Created by isen on 03/12/2015.
 */
public class WLTwitterLoginActivity extends Activity implements View.OnClickListener {

    public static EditText login;
    public static EditText password;
    public static CheckBox saveLoginCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        setContentView(R.layout.activity_login);
        findViewById(R.id.loginButton).setOnClickListener(this);
        // cast the String as an EditText for compatibility
        login = (EditText) this.findViewById(R.id.loginEditText);
        //login.setCursorVisible(true);
        password = (EditText) this.findViewById(R.id.passwordEditText);
        //password.setCursorVisible(true);

        saveLoginCheckBox = (CheckBox)findViewById(R.id.saveLoginCheckBox);

        //if remember me was already checked then show login and password and check the box
        if (!PreferenceUtils.getCheckBox()) {
            saveLoginCheckBox.setChecked(true);
        }

        /* -- SET CURSOR ONLY VISIBLE WHEN KEYBOARD LAUNCHES -- */
        //create a view of the login container activity
        final View loginActivity = findViewById(R.id.login_activity);
        //get the tree view and set a listener on the layout from the view
        loginActivity.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            //set up comportment when the view updates threw the listener
            @Override
            public void onGlobalLayout() {
                //on all of the container area create a rectangle off same height and width
                Rect r = new Rect();
                //put the size of the layout to the rectangle
                loginActivity.getWindowVisibleDisplayFrame(r);
                //check if the rectangle height shrinks like when keyboard launches
                //for than check on the difference between regular container's height and
                //actual container's
                int heightDiff = loginActivity.getRootView().getHeight() - (r.bottom - r.top);
                if (login != null && password != null) {
                    // if more than 100 pixels, its probably a keyboard...
                    if (heightDiff > 100) {
                        //then set cursor visible for login and password
                        login.setCursorVisible(true);
                        password.setCursorVisible(true);
                    } else {
                        //else set both cursors invisible
                        //cursor will appear when item is selected only
                        //so when keyboard launches
                        login.setCursorVisible(false);
                        password.setCursorVisible(false);
                    }
                }
            }
        });
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
        // get the login from id loginEditText on the activity_main.xml
        Log.i("onClick(View) - user login ",login.getText().toString());

        if (TextUtils.isEmpty(login.getText().toString())) {
            // if login is empty throw a toast
            Toast.makeText(this, R.string.error_no_login, Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password.getText().toString())) {
            // if password is empty throw a toast
            Toast.makeText(this, R.string.error_no_password, Toast.LENGTH_LONG).show();
            return;
        }
        //set preferences
        PreferenceUtils.setLogin(login.getText().toString());
        PreferenceUtils.setPassword(password.getText().toString());


        //if box is checked mention it for next time
        if (saveLoginCheckBox.isChecked()) {
            Toast.makeText(this, "Login saved as : " + login.getText().toString(), Toast.LENGTH_LONG).show();
            PreferenceUtils.setCheckBox(true);
        }

        //start new activity
        startActivity(getNameActivityIntent(login.getText().toString()));
    }


    /**
     * getNameActivityIntent return an Intent, as we already know, an Intent can store serializable
     * data, data which will be given for the activity to be launched.
     * This data must be stock in a Bundle (object type), as a pair key/value
     * We use the putExtra method to store it. putString method permit to set a String value
     * in the preference editor.
     *
     * @param login
     * @return Intent
     */
    private Intent getNameActivityIntent(String login) {
        // instanciate a new intent of the WLTwitterActivity in WLTwitterApplication's context
        Intent WLTwitterIntent = new Intent(this, WLTwitterActivity.class);
        Bundle extra = new Bundle();
        //save login in Bundle
        extra.putString(Constants.Login.EXTRA_LOGIN, login);
        WLTwitterIntent.putExtras(extra);
        
        return WLTwitterIntent;
    }

}

