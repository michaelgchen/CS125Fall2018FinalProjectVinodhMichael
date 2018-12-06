package com.example.android.cs125finalprojectvinodhmichael;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.MainThread;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.View;

public class GameView extends View {
        //implements SurfaceHolder.Callback {
    private MainThread thread;

    //Background
    private Bitmap bgImage;

    //student
    private Bitmap student;

    //Score
    private Paint scorePaint = new Paint();

    //Life
    private Bitmap life[] = new Bitmap[2];

    public GameView(Context context) {
        super(context);
       // getHolder().addCallback(this);
        student = BitmapFactory.decodeResource(getResources(), R.drawable.student);


        bgImage = BitmapFactory.decodeResource(getResources(), R.drawable.gamebg);

        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize(32);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.a);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.b);

    }
/*
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
*/
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bgImage,0,0,null);
        canvas.drawBitmap(student, 0, 0, null);
        canvas.drawText("Score: 0", 20, 60, scorePaint);
        canvas.drawBitmap(life[0], 560,30, null);
        canvas.drawBitmap(life[0], 620, 30, null);
        canvas.drawBitmap(life[1], 680,30,null);

    }


}

