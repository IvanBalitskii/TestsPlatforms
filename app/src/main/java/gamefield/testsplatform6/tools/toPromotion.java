package gamefield.testsplatform6.tools;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import gamefield.testsplatform6.LastResult;
import gamefield.testsplatform6.R;

public class toPromotion extends Activity {
    private RewardedVideoAd mRewardedVideoAd;
    TextView text;
    ImageView promImage;
    int WIDTH, HEIGHT, score;
    Button promote;
    boolean isCliked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_to_promotion);
        MobileAds.initialize(this, "ca-app-pub-6634645519102438~3068726717");

        Display mDisplay = getWindowManager().getDefaultDisplay();
        WIDTH = mDisplay.getWidth();
        HEIGHT = mDisplay.getHeight();
        Typeface typeFace = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/sec.ttf");

        text = findViewById(R.id.text_prom);
        promote = findViewById(R.id.promotion);
        promImage = findViewById(R.id.person_image);
        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);


        text.setTypeface(typeFace);
        text.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (HEIGHT * 0.055));
        text.setText("Результат откроется после просмотра короткой рекламы (10-30 секунд)" + "\n" + "P.S. Разработчикам нужно закупать кофе и печенье)");

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(new RevVideo());


        loadRewardedVideoAd();
        LinearLayout.LayoutParams params_buttons = new LinearLayout.LayoutParams(WIDTH, ((70 * HEIGHT) / 768));
        params_buttons.setMargins(0, ((25 * HEIGHT) / 768), 0, 0);
        promote.setLayoutParams(params_buttons);
        promote.setTypeface(typeFace);
        promote.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (HEIGHT*0.06));
        promote.setText("Открыть");
        promote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promote.startAnimation(shake);

                isCliked = true;
                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                }else{
                    Toast.makeText(getApplicationContext(),
                            "Подождите, идет загрузка", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Intent mIntent = getIntent();
        score = mIntent.getIntExtra("score", 0);


    }
    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd(
                        "\n" +
                                "ca-app-pub-6634645519102438/4232321499",
                new AdRequest.Builder().addTestDevice("39A667D4F0082B33C1B39716FA6B3E77").build());
    }
    private class RevVideo implements RewardedVideoAdListener{
        @Override
        public void onRewarded(RewardItem reward) {
            Intent intent = new Intent(toPromotion.this, LastResult.class);
            startActivityForResult(intent);
            overridePendingTransition(R.anim.to_right, R.anim.to_left);
        }

        @Override
        public void onRewardedVideoAdLeftApplication() {
        }

        @Override
        public void onRewardedVideoAdClosed() {
            Intent intent = new Intent(toPromotion.this, LastActivity.class);
            startActivityForResult(intent);
            overridePendingTransition(R.anim.to_right, R.anim.to_left);
        }

        @Override
        public void onRewardedVideoAdFailedToLoad(int errorCode) {
            Intent intent = new Intent(toPromotion.this, LastResult.class);
            startActivityForResult(intent);
            overridePendingTransition(R.anim.to_right, R.anim.to_left);
        }

        @Override
        public void onRewardedVideoAdLoaded() {
            if (mRewardedVideoAd.isLoaded()&&isCliked) {
                mRewardedVideoAd.show();
            }
        }

        @Override
        public void onRewardedVideoAdOpened() {
        }

        @Override
        public void onRewardedVideoStarted() {

        }

    }
    private void startActivityForResult(Intent intent) {
        intent.putExtra("score", score);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
