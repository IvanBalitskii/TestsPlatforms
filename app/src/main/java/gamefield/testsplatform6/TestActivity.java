package gamefield.testsplatform6;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class TestActivity extends Activity {
    Button An1, An2, An3;
    int An1W[], An2W[], An3W[], ANSWER_NUMBER = 1, SCORE;
    TextView score, AnT;
    ImageView answer;
    LinearLayout buttons_layout, content_lo;
    int WIDTH, HEIGHT;
    private static final int TICKET_QUANTITY = 15;
    boolean IS_ANIMATION_STARTED = false, IS_ANIMATION_ENDED = false;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_test);

        Display mDisplay = getWindowManager().getDefaultDisplay();
        WIDTH = mDisplay.getWidth();
        HEIGHT = mDisplay.getHeight();
        Resources r = getResources();
        int margin = (int)(HEIGHT*0.3);
        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                margin,
                r.getDisplayMetrics()
        );
        buttons_layout = findViewById(R.id.buttons_lo);
        content_lo = findViewById(R.id.content_lo);

        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) buttons_layout.getLayoutParams();
        params.setMargins(0, (int) (HEIGHT * 0.5), 0, 0);
        buttons_layout.setLayoutParams(params);
        params = (FrameLayout.LayoutParams) content_lo.getLayoutParams();
        params.setMargins(0, (int) (HEIGHT*0.04), 0, 0);
        content_lo.setLayoutParams(params);

        Typeface typeFace = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/sec.ttf");
        Typeface typeFace2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/main_font.ttf");

        AnT = findViewById(R.id.AnT);
        AnT.setTypeface(typeFace);
        AnT.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (HEIGHT*0.035));

        answer = findViewById(R.id.Answer);
        score = findViewById(R.id.score);
        score.setTypeface(typeFace2);
        score.setTextSize(TypedValue.COMPLEX_UNIT_PX, ((35 * HEIGHT) / 768));


        score.setText(ANSWER_NUMBER + "/15");
        answer.setImageResource(getResources().getIdentifier(("im" + ANSWER_NUMBER), "drawable", getPackageName()));
        answer.getLayoutParams().height = (int) (HEIGHT * 0.4);

        An1W = new int[]{-1, 2, 2, -1, 1, 0, 1, 2, 1, 1, 2, 1, 1, 2, 2};
        An2W = new int[]{0, 1, 0, 1, -1, 1, 0, 1, -1, 0, 1, 0, 0, 1, 0};
        An3W = new int[]{1, -1, 1, 0, 0, -1, 0, 0, 0, 0, -1, 0, -1, 0, -1};

        An1 = (Button) findViewById(R.id.an1);
        An1.setTypeface(typeFace);
       // An1.setLayoutParams(new LinearLayout.LayoutParams(WIDTH, ((70 * HEIGHT) / 768)));
        An1.setTextSize(TypedValue.COMPLEX_UNIT_PX, (WIDTH * HEIGHT) / 20000);
        An1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeAnswer(1, An1W);
            }
        });

        An2 = (Button) findViewById(R.id.an2);
        An2.setTypeface(typeFace);
        An2.setTextSize(TypedValue.COMPLEX_UNIT_PX, (WIDTH * HEIGHT) / 20000);
        An2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeAnswer(2, An2W);
            }
        });

        An3 = (Button) findViewById(R.id.an3);
        An3.setTypeface(typeFace);
        An3.setTextSize(TypedValue.COMPLEX_UNIT_PX, (WIDTH * HEIGHT) / 20000);
        An3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeAnswer(3, An3W);
            }
        });

        String A = String.valueOf(ANSWER_NUMBER);

        LinearLayout.LayoutParams params_buttons = new LinearLayout.LayoutParams(WIDTH, ((70 * HEIGHT) / 768));
        params_buttons.setMargins(0, (int) (HEIGHT*0.013), 0, 0);
        System.out.println(HEIGHT);
        An1.setLayoutParams(params_buttons);
        An2.setLayoutParams(params_buttons);
        An3.setLayoutParams(params_buttons);

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-6634645519102438/1372501665");

        MobileAds.initialize(this,
                "ca-app-pub-6634645519102438~3068726717");

        mAdView = findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("39A667D4F0082B33C1B39716FA6B3E77").build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
            }
        });

        An1.setText(getResources().getIdentifier(("AN" + A + "BTN1"), "string", getPackageName()));
        An2.setText(getResources().getIdentifier(("AN" + A + "BTN2"), "string", getPackageName()));
        An3.setText(getResources().getIdentifier(("AN" + A + "BTN3"), "string", getPackageName()));
        AnT.setText(getResources().getIdentifier(("An" + A), "string", getPackageName()));
    }


    private void changeAnswer(int BUTTON, int Answers[]) {
        if (ANSWER_NUMBER == TICKET_QUANTITY) {

                SCORE += Answers[ANSWER_NUMBER - 1];
                System.out.println(SCORE);

            Intent intent = new Intent(TestActivity.this, Results.class);
            startActivityForResult(intent);
            overridePendingTransition(R.anim.to_right, R.anim.to_left);
        } else {

            if (!IS_ANIMATION_STARTED && !IS_ANIMATION_ENDED) {
                    SCORE += Answers[ANSWER_NUMBER - 1];
                    System.out.println(ANSWER_NUMBER);
                TranslateAnimation animation = new TranslateAnimation(0, -WIDTH, 0, 0);
                animation.setDuration(325);
                animation.setFillAfter(false);
                animation.setAnimationListener(new MyAnimationListener());
                IS_ANIMATION_STARTED = true;
                buttons_layout.startAnimation(animation);
                content_lo.startAnimation(animation);

            }
        }
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            if (IS_ANIMATION_STARTED) {
                animation = new TranslateAnimation(-WIDTH, 0, 0, 0);
                animation.setDuration(325);
                animation.setFillAfter(false);
                animation.setAnimationListener(new MyAnimationListener());
                buttons_layout.startAnimation(animation);
                ANSWER_NUMBER++;
               // AnT.setText(getResources().getIdentifier(("AnT" + ANSWER_NUMBER), "string", getPackageName()));
                An1.setText(getResources().getIdentifier(("AN" + ANSWER_NUMBER + "BTN1"), "string", getPackageName()));
                An2.setText(getResources().getIdentifier(("AN" + ANSWER_NUMBER + "BTN2"), "string", getPackageName()));
                An3.setText(getResources().getIdentifier(("AN" + ANSWER_NUMBER + "BTN3"), "string", getPackageName()));
                AnT.setText(getResources().getIdentifier(("An" + ANSWER_NUMBER), "string", getPackageName()));
                answer.setImageResource(getResources().getIdentifier(("im" + ANSWER_NUMBER), "drawable", getPackageName()));
                score.setText(ANSWER_NUMBER + "/15");
                IS_ANIMATION_ENDED = true;
                animation = new TranslateAnimation(WIDTH, 0, 0, 0);
                animation.setDuration(325);
                animation.setFillAfter(false);
                animation.setAnimationListener(new MyAnimationListener());
                content_lo.startAnimation(animation);
            }
            if(!IS_ANIMATION_STARTED)IS_ANIMATION_ENDED = false;
            IS_ANIMATION_STARTED = false;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }

    }

    private void startActivityForResult(Intent intent) {
        intent.putExtra("score", SCORE);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
