package gamefield.testsplatform6;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import gamefield.testsplatform6.tools.LastActivity;
import gamefield.testsplatform6.tools.toPromotion;

public class LastResult extends Activity {
    Button toAnswers;
    int score, person;
    int WIDTH, HEIGHT;

    ImageView person_image;
    TextView ScoreView, DescriptionView, FullDescriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_last_result);

        ScoreView = findViewById(R.id.score);
        DescriptionView = findViewById(R.id.description);
        FullDescriptionView = findViewById(R.id.full_description);
        person_image = findViewById(R.id.person_image);

        Intent mIntent = getIntent();
        score = mIntent.getIntExtra("score", 0);

        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);

        if (score <= 6)
            person = 1;
        if (score > 6)
            person = 2;
        if (score > 10)
            person = 3;
        if (score > 16)
            person = 4;

        Display mDisplay = getWindowManager().getDefaultDisplay();
        WIDTH = mDisplay.getWidth();
        HEIGHT = mDisplay.getHeight();
        Typeface typeFace = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/sec.ttf");

        ScoreView.setTypeface(typeFace);
        ScoreView.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (HEIGHT * 0.13));
        ScoreView.setText((int)(score/0.21) + "");

        DescriptionView.setTypeface(typeFace);
        DescriptionView.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (HEIGHT * 0.065));
        DescriptionView.setText(getResources().getIdentifier(("PERSON" + person + "D"), "string", getPackageName()));

        FullDescriptionView.setTypeface(typeFace);
        FullDescriptionView.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (HEIGHT * 0.035));
        FullDescriptionView.setText(getResources().getIdentifier(("PERSON" + person + "FD"), "string", getPackageName()));

        person_image.getLayoutParams().height = (int) (HEIGHT * 0.22);
        person_image.setImageResource(getResources().getIdentifier(("d" + person), "drawable", getPackageName()));

        toAnswers = findViewById(R.id.toAnswers);

        LinearLayout.LayoutParams params_buttons = new LinearLayout.LayoutParams(WIDTH, ((70 * HEIGHT) / 768));
        params_buttons.setMargins(0, ((25 * HEIGHT) / 768), 0, 0);
        toAnswers.setLayoutParams(params_buttons);
        toAnswers.setTypeface(typeFace);
        toAnswers.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (HEIGHT*0.05));
        toAnswers.setText("далее");
        toAnswers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toAnswers.startAnimation(shake);


                    Intent intent = new Intent(LastResult.this, LastActivity.class);
                    startActivityForResult(intent);
                    overridePendingTransition(R.anim.to_right, R.anim.to_left);

            }
        });


    }

    public static boolean hasConnection(final Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        return false;
    }

    private void startActivityForResult(Intent intent) {
        startActivity(intent);
        finish();
    }
}
