package gamefield.testsplatform6;


import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import gamefield.testsplatform6.tools.toPromotion;

public class Results extends AppCompatActivity {
    ImageView fonting;
    TextView wait;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_results);
        Intent mIntent = getIntent();
        score = mIntent.getIntExtra("score", 0);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Display mDisplay = getWindowManager().getDefaultDisplay();
        final int height = mDisplay.getHeight();
        Typeface typeFace = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/sec.ttf");

        wait = findViewById(R.id.wait);
        wait.setTypeface(typeFace);
        wait.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (height*0.065));
        wait.setText("Подождите, мы загружаем результат...");
        fonting = findViewById(R.id.loader);

        TranslateAnimation animation = new TranslateAnimation(0, 0, (float) (height*0.79), (float) (height*0.39));
        animation.setDuration(10000);
        animation.setFillAfter(false);
        animation.setAnimationListener(new MyAnimationListener());
        fonting.startAnimation(animation);

    }
    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            fonting.clearAnimation();
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(fonting.getWidth(), fonting.getHeight());
            fonting.setLayoutParams(lp);
            Intent intent = new Intent(Results.this, toPromotion.class);
            startActivityForResult(intent);
            overridePendingTransition(R.anim.to_right,R.anim.to_left);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }

    }
    private void startActivityForResult(Intent intent) {
        intent.putExtra("score", score);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
