package jackiechans.recipeek;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class helpActivity extends AppCompatActivity {
    private TextView introductionText;
    private TextView signUpText;
    private TextView loginScreenHelp;
    private TextView searchHelp;
    private TextView favouriteHelp;
    private TextView recipeMakingHelp;
    private ScrollView helpScroll;

    public void onClickFocusText(View view){
        int pressId = view.getId();
        switch(pressId) {
            case R.id.signUpJump:
                helpScroll.smoothScrollTo(0,signUpText.getTop());
                break;
            case R.id.loggingInJump:
                helpScroll.smoothScrollTo(0,loginScreenHelp.getTop());
                break;
            case R.id.searchJump:
                helpScroll.smoothScrollTo(0,searchHelp.getTop());
                break;
            case R.id.favouriteJump:
                helpScroll.smoothScrollTo(0,favouriteHelp.getTop());
                break;
            case R.id.createRecipeJump:
                helpScroll.smoothScrollTo(0,recipeMakingHelp.getTop());
                break;
            case R.id.topButton1:
                helpScroll.smoothScrollTo(0,introductionText.getTop());
                break;
            case R.id.topButton2:
                helpScroll.smoothScrollTo(0,introductionText.getTop());
                break;
            case R.id.topButton3:
                helpScroll.smoothScrollTo(0,introductionText.getTop());
                break;
            case R.id.topButton4:
                helpScroll.smoothScrollTo(0,introductionText.getTop());
                break;
            case R.id.topButton5:
                helpScroll.smoothScrollTo(0,introductionText.getTop());
                break;
            default:
                helpScroll.smoothScrollTo(0,introductionText.getTop());
                break;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Help");
        introductionText = (TextView) findViewById(R.id.introductionText);
        signUpText = (TextView) findViewById(R.id.signUpHelp);
        loginScreenHelp = (TextView) findViewById(R.id.loginScreenHelp);
        searchHelp = (TextView) findViewById(R.id.searchHelp);
        favouriteHelp = (TextView) findViewById(R.id.favouriteHelp);
        recipeMakingHelp = (TextView) findViewById(R.id.recipeMakingHelp);
        helpScroll = (ScrollView) findViewById(R.id.helpScroll);
    }
}
