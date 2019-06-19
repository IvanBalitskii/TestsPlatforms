package gamefield.testsplatform6;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
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

public class MainMenu extends Activity {
    Button toTets, exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_menu);
        Display mDisplay = getWindowManager().getDefaultDisplay();
        final int width  = mDisplay.getWidth();
        final int height = mDisplay.getHeight();
        int margin = (int)(height*0.32);
        LinearLayout buttons =  findViewById(R.id.buttons);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)buttons.getLayoutParams();
        params.setMargins(0, margin*2, 0, 0);
        buttons.setLayoutParams(params);
        toTets = (Button) findViewById(R.id.toTest);
        Typeface typeFace = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/main_font.ttf");
        toTets.setTypeface(typeFace);
        toTets.setLayoutParams(new LinearLayout.LayoutParams(width, ((70*height)/768)));
        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);

        toTets.setTextSize(TypedValue.COMPLEX_UNIT_PX, ((40*height)/768));
        toTets.setText("пройти тест");
        toTets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toTets.startAnimation(shake);
                Intent intent = new Intent(getApplicationContext(), TestActivity.class);
                startActivityForResult(intent);
                overridePendingTransition(R.anim.to_right, R.anim.to_left);
            }
        });
        exit = findViewById(R.id.exit);
        exit.setTypeface(typeFace);



        exit.setText("выход");
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exit.startAnimation(shake);
                System.exit(0);
            }
        });
        LinearLayout.LayoutParams params_buttons = new LinearLayout.LayoutParams(width, ((70*height)/768));
        params_buttons.setMargins(0, (int)(height*0.032) , 0 , 0);
        exit.setLayoutParams(params_buttons);
       // exit.setLayoutParams(new LinearLayout.LayoutParams(width, ((70*height)/768)));
        exit.setTextSize(TypedValue.COMPLEX_UNIT_PX, ((40*height)/768));
    }

    private void startActivityForResult(Intent intent) {
        startActivity(intent);
        finish();
    }
}
