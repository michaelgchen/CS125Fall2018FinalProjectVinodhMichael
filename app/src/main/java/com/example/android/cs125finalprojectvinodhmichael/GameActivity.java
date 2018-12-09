package com.example.android.cs125finalprojectvinodhmichael;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {


    //Frame
    private FrameLayout gameFrame;
    private int frameHeight, frameWidth, initialFrameWidth;
    private LinearLayout startLayout;

    //Image
    private ImageView character, a, f;
    private Drawable imageCharacterRight, imageCharacterLeft;


    //Size
    private int characterSize;

    //Position (Add all other characters)
    private float characterX, characterY;
    private float aX, aY;
    private float fX, fY;

    //Score
    private TextView scoreLabel, highScoreLabel;
    private int score, highScore, timeCount;

    //Class
    private Timer timer;
    //Required Import
    private Handler handler = new Handler();

    //Status
    private boolean start_flg = false;
    private boolean action_flg = false;
    private boolean pink_flg = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameFrame = findViewById(R.id.gameFrame);
        startLayout = findViewById(R.id.startLayout);
        character = findViewById(R.id.character);
        a = findViewById(R.id.a);
        f = findViewById(R.id.f);
        //add other characters
        scoreLabel = findViewById(R.id.scoreLabel);
        //highScoreLabel = findViewById(R.id.highScoreLabel);

        //add left version of character
        imageCharacterLeft = getResources().getDrawable(R.drawable.student);
        imageCharacterRight = getResources().getDrawable(R.drawable.student);

    }

    public void changePos() {

        // f
        fY += 12;

        float fCenterX = fX + f.getWidth()/2;
        float fCenterY = fY + f.getHeight() / 2;

        if (hitCheck(fCenterX, fCenterY)) {
            fY = frameHeight + 100;
            score -=10;

        }

        if (fY > frameHeight) {
            fY = -100;
            fX = (float) Math.floor(Math.random() * (frameWidth - f.getWidth()));
        }
        f.setX(fX);
        f.setY(fY);


        //Move Character
        if (action_flg) {
            //Touching
            characterX += 14;
            character.setImageDrawable(imageCharacterRight);
        } else {
            //Releasing
            characterX -= 14;
            character.setImageDrawable(imageCharacterLeft);
        }

        //Check Character Position
        if (characterX < 0) {
            characterX = 0;
            character.setImageDrawable(imageCharacterRight);
        }
        if (frameWidth - characterSize < characterX) {
            characterX = frameWidth - characterSize;
            character.setImageDrawable(imageCharacterLeft);
        }
        character.setX(characterX);
        scoreLabel.setText("Score : " + score);
    }

    public boolean hitCheck(float x, float y) {
        if (characterX <= x && x <= characterX + characterSize &&
                characterY <= y && y <= frameHeight) {
            return true;
        }
        return false;

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (start_flg) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                action_flg = true;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                action_flg = false;
            }
        }
        return true;
    }

    public void startGame(View view) {
        start_flg = true;
        startLayout.setVisibility(View.INVISIBLE);

        if (frameHeight == 0) {
            frameHeight = gameFrame.getHeight();
            frameWidth = gameFrame.getWidth();
            initialFrameWidth = frameWidth;

            characterSize = character.getHeight();
            characterX = character.getX();
            characterY = character.getY();

        }
        character.setX(0.0f);
        a.setY(3000.0f);
        f.setY(3000.0f);


        aY = a.getY();
        fY = f.getY();

        character.setVisibility(View.VISIBLE);
        a.setVisibility(View.VISIBLE);
        f.setVisibility(View.VISIBLE);

        timeCount = 0;
        score = 0;
        scoreLabel.setText("Score : 0 ");

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (start_flg) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }

        }, 0, 20);


    }

    public void quitGame(View view) {

    }
}

