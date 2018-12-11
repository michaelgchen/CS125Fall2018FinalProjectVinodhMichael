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

    //Counter Variable
    private int lifeCount = 3;
    //Frame
    private FrameLayout gameFrame;
    private int frameHeight, frameWidth, initialFrameWidth;
    private LinearLayout startLayout;

    //Image
    private ImageView character, a, f, b, c, d;
    private Drawable imageCharacterRight, imageCharacterLeft;
    private ImageView lifeOne, lifeTwo, lifeThree;



    //Size
    private int characterSize;

    //Position (Add all other characters)
    private float characterX, characterY;
    private float aX, aY;
    private float bX, bY;
    private float cX, cY;
    private float dX, dY;
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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);



        gameFrame = findViewById(R.id.gameFrame);
        startLayout = findViewById(R.id.startLayout);
        character = findViewById(R.id.character);
        a = findViewById(R.id.a);
        b = findViewById(R.id.b);
        c = findViewById(R.id.c);
        d = findViewById(R.id.d);
        f = findViewById(R.id.f);
        //add other characters
        scoreLabel = findViewById(R.id.scoreLabel);
        //highScoreLabel = findViewById(R.id.highScoreLabel);

        //Lives
        lifeOne = findViewById(R.id.life1);
        lifeTwo = findViewById(R.id.life2);
        lifeThree = findViewById(R.id.life3);

        //add left version of character
        imageCharacterLeft = getResources().getDrawable(R.drawable.student);
        imageCharacterRight = getResources().getDrawable(R.drawable.student);

    }

    public void changePos() {

        // f
        fY += 30;

        //a
        aY += 10;

        //b
        bY += 15;

        //c
        cY += 20;

        //d
        dY += 25;


        //f Center
        float fCenterX = fX + f.getWidth()/2;
        float fCenterY = fY + f.getHeight() / 2;

        // A Center
        float aCenterX = aX + a.getWidth()/2;
        float aCenterY = aY + a.getHeight()/2;

        // B Center
        float bCenterX = bX + b.getWidth()/2;
        float bCenterY = bY + b.getHeight()/2;

        // C Center
        float cCenterX = cX + c.getWidth()/2;
        float cCenterY = cY + c.getHeight()/2;


        // D Center
        float dCenterX = dX + d.getWidth()/2;
        float dCenterY = dY + d.getHeight()/2;


        //A Hit Check
        if (hitCheck(aCenterX, aCenterY)) {
            aY = frameHeight + 100;
            //score += 10;
            if (lifeCount < 3 && lifeCount > 0) {
                lifeCount++;
                addHeart(lifeCount);
            } else {
                score += 50;
            }
        }

        //B Hit Check
        if (hitCheck(bCenterX, bCenterY)) {
            bY = frameHeight + 100;
            score += 30;
        }

        //C Hit Check
        if (hitCheck(cCenterX, cCenterY)) {
            cY = frameHeight + 100;
            score += 10;
        }

        //D Hit Check
        if (hitCheck(dCenterX, dCenterY)) {
            dY = frameHeight + 100;
            if(score >= 40 ) {
                score -= 40;
            }
        }

        // F Hit Check
        if (hitCheck(fCenterX, fCenterY)) {
            fY = frameHeight + 100;
            //score -=10;
            lifeCount--;
            removeHeart(lifeCount);

        }

        // f spawn
        if (fY > frameHeight) {
            fY = -100;
            fX = (float) Math.floor(Math.random() * (frameWidth - f.getWidth()));
        }
        f.setX(fX);
        f.setY(fY);

        // a spawn
        if (aY > frameHeight) {
           aY = -100;
           aX = (float) Math.floor(Math.random() * (frameWidth - a.getWidth()));
        }
        a.setX(aX);
        a.setY(aY);

        // b spawn
        if (bY > frameHeight) {
            bY = -100;
            bX = (float) Math.floor(Math.random() * (frameWidth - b.getWidth()));
        }
        b.setX(bX);
        b.setY(bY);

        // c spawn
        if (cY > frameHeight) {
            cY = -100;
            cX = (float) Math.floor(Math.random() * (frameWidth - c.getWidth()));
        }
        c.setX(cX);
        c.setY(cY);

        // d spawn
        if (dY > frameHeight) {
            dY = -100;
            dX = (float) Math.floor(Math.random() * (frameWidth - d.getWidth()));
        }
        d.setX(dX);
        d.setY(dY);




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

    public void removeHeart(int numberOfLives) {
        if (numberOfLives == 2) {
            lifeOne.setVisibility(View.INVISIBLE);
        } else if (numberOfLives == 1) {
            lifeTwo.setVisibility(View.INVISIBLE);
        } else if (numberOfLives == 0) {
            lifeThree.setVisibility(View.INVISIBLE);
            finish();
        }
    }

    public void addHeart(int numberOfLives) {
        if (numberOfLives == 3) {
            lifeOne.setVisibility(View.VISIBLE);
        } else if (numberOfLives == 2) {
            lifeTwo.setVisibility(View.VISIBLE);
        }
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
        b.setY(3000.0f);
        c.setY(3000.0f);
        d.setY(3000.0f);



        aY = a.getY();
        fY = f.getY();
        bY = a.getY();
        cY = f.getY();
        dY = a.getY();

        character.setVisibility(View.VISIBLE);
        a.setVisibility(View.VISIBLE);
        f.setVisibility(View.VISIBLE);
        b.setVisibility(View.VISIBLE);
        c.setVisibility(View.VISIBLE);
        d.setVisibility(View.VISIBLE);

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

