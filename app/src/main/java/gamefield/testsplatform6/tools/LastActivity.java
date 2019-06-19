package gamefield.testsplatform6.tools;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import gamefield.testsplatform6.MainMenu;
import gamefield.testsplatform6.R;

public class LastActivity extends Activity {
    Button toMarket, start, more;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_last);
        Display mDisplay = getWindowManager().getDefaultDisplay();
        final int width = mDisplay.getWidth();
        final int height = mDisplay.getHeight();
        text = findViewById(R.id.textpl);

        LinearLayout buttons = findViewById(R.id.buttons);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) buttons.getLayoutParams();
        params.setMargins(0, (int) (height * 0.58), 0, 0);
        buttons.setLayoutParams(params);
        toMarket = (Button) findViewById(R.id.toMarket);
        Typeface typeFace = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/sec.ttf");
        toMarket.setTypeface(typeFace);
        toMarket.setLayoutParams(new LinearLayout.LayoutParams(width, ((70 * height) / 768)));
        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        text.setTypeface(typeFace);
        text.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (height * 0.055));
        text.setText("У нас есть еще несколько тестов которые могут быть интересны" + "\n" + "P.S. Это поможет нам в разработке игр");
        toMarket.setTextSize(TypedValue.COMPLEX_UNIT_PX, ((40 * height) / 768));
        toMarket.setText("Оценить");
        toMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toMarket.startAnimation(shake);
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                } //https://play.google.com/store/apps/developer?id=GameField.inc
            }
        });
        start = findViewById(R.id.return1);
        start.setTypeface(typeFace);


        start.setText("Заново");
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start.startAnimation(shake);
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                startActivityForResult(intent);
                overridePendingTransition(R.anim.to_right, R.anim.to_left);
            }
        });
        LinearLayout.LayoutParams params_buttons = new LinearLayout.LayoutParams(width, ((70 * height) / 768));
        params_buttons.setMargins(0, ((25 * height) / 768), 0, 0);
        start.setLayoutParams(params_buttons);
        //
        start.setTextSize(TypedValue.COMPLEX_UNIT_PX, ((40 * height) / 768));

        more = findViewById(R.id.More);
        more.setTypeface(typeFace);


        more.setText("Еще тесты");
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                more.startAnimation(shake);
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=GameField.inc")));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=GameField.inc")));
                }
            }
        });
        more.setLayoutParams(params_buttons);
        // exit.setLayoutParams(new LinearLayout.LayoutParams(width, ((70*height)/768)));
        more.setTextSize(TypedValue.COMPLEX_UNIT_PX, ((40 * height) / 768));
    }

    private void startActivityForResult(Intent intent) {
        startActivity(intent);
        finish();
    }
}