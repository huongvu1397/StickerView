package com.shashi.stickerview;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.shashi.mysticker.BitmapStickerIcon;
import com.shashi.mysticker.DeleteIconEvent;
import com.shashi.mysticker.DrawableSticker;
import com.shashi.mysticker.FlipHorizontallyEvent;
import com.shashi.mysticker.Sticker;
import com.shashi.mysticker.StickerView;
import com.shashi.mysticker.TextSticker;
import com.shashi.mysticker.ZoomIconEvent;

import java.util.Arrays;

/**
 * Created by shashi on 6/6/18
 */
public class Main_Activity extends Activity {

    StickerView sticker_view;
    private TextSticker sticker;
    Button btn_reset, btn_replace, btn_remove, btn_removeall, btn_lock, btn_add;
    AppCompatSeekBar seek_bar;
    String TAG = getClass().getSimpleName();
    public static final int PERM_RQST_CODE = 110;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        sticker_view = (StickerView) findViewById(R.id.sticker_view);
        btn_reset = (Button) findViewById(R.id.btn_reset);
        btn_replace = (Button) findViewById(R.id.btn_replace);
        btn_remove = (Button) findViewById(R.id.btn_remove);
        btn_removeall = (Button) findViewById(R.id.btn_removeall);
        btn_lock = (Button) findViewById(R.id.btn_lock);
        btn_add = (Button) findViewById(R.id.btn_add);
        seek_bar  = findViewById(R.id.seek_bar);
        BitmapStickerIcon deleteIcon = new BitmapStickerIcon(ContextCompat.getDrawable(this,
                R.drawable.ic_close),
                BitmapStickerIcon.LEFT_TOP);
        deleteIcon.setIconEvent(new DeleteIconEvent());

        BitmapStickerIcon zoomIcon = new BitmapStickerIcon(ContextCompat.getDrawable(this,
                R.drawable.ic_scale),
                BitmapStickerIcon.RIGHT_BOTOM);
        zoomIcon.setIconEvent(new ZoomIconEvent());

        BitmapStickerIcon flipIcon = new BitmapStickerIcon(ContextCompat.getDrawable(this,
                R.drawable.ic_flip),
                BitmapStickerIcon.RIGHT_TOP);
        flipIcon.setIconEvent(new FlipHorizontallyEvent());

        BitmapStickerIcon heartIcon =
                new BitmapStickerIcon(ContextCompat.getDrawable(this, R.drawable
                        .ic_star),
                        BitmapStickerIcon.LEFT_BOTTOM);
        heartIcon.setIconEvent(new HelloIconEvent());

        sticker_view.setIcons(Arrays.asList(deleteIcon, zoomIcon, flipIcon, heartIcon));

        sticker_view.setBackgroundColor(Color.WHITE);
        sticker_view.setLocked(false);
        sticker_view.setConstrained(true);

        sticker = new TextSticker(this);

        sticker.setDrawable(ContextCompat.getDrawable(getApplicationContext(),
                R.drawable.sticker_transparent_background));
        sticker.setText("Hello, world!");
        sticker.setTextColor(Color.BLACK);
        sticker.setTextAlign(Layout.Alignment.ALIGN_CENTER);
        sticker.resizeText();



        sticker_view.setOnStickerOperationListener(new StickerView.OnStickerOperationListener() {
            @Override
            public void onStickerAdded(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerAdded");
            }

            @Override
            public void onStickerClicked(@NonNull Sticker sticker) {
                //sticker_view.removeAllSticker();
                if (sticker instanceof TextSticker) {
                    ((TextSticker) sticker).setTextColor(Color.RED);
                    sticker_view.replace(sticker);
                    sticker_view.invalidate();
                }
                Log.d(TAG, "onStickerClicked");
            }

            @Override
            public void onStickerDeleted(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerDeleted");
            }

            @Override
            public void onStickerDragFinished(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerDragFinished");
            }

            @Override
            public void onStickerTouchedDown(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerTouchedDown");
            }

            @Override
            public void onStickerZoomFinished(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerZoomFinished");
            }

            @Override
            public void onStickerFlipped(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerFlipped");
            }

            @Override
            public void onStickerDoubleTapped(@NonNull Sticker sticker) {
                Log.d(TAG, "onDoubleTapped: double tap will be with two click");
            }
        });

        loadSticker();

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetSticker();
            }
        });

        btn_replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceSticker();
            }
        });

        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeSticker();
            }
        });

        btn_removeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAllSticker();
            }
        });

        btn_lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lockSticker();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSticker();
            }
        });
    }

    private void loadSticker() {

        Log.d(TAG, "loadSticker");

        Drawable drawable =
                ContextCompat.getDrawable(this, R.drawable.a1);
        Drawable drawable1 =
                ContextCompat.getDrawable(this, R.drawable.a2);
        sticker_view.addSticker(new DrawableSticker(drawable));
        sticker_view.addSticker(new DrawableSticker(drawable1), Sticker.Position.BOTTOM | Sticker
                .Position.RIGHT);



        Drawable bubble = ContextCompat.getDrawable(this, R.drawable.bubble);
        sticker_view.addSticker(
                new TextSticker(getApplicationContext())
                        .setDrawable(bubble)
                        .setText("Sticker\n")
                        .setMaxTextSize(14)
                        .resizeText()
                , Sticker.Position.TOP);
    }

    public void replaceSticker() {
        if (sticker_view.replace(sticker)) {
            Toast.makeText(Main_Activity.this, "Replace Sticker successfully!", Toast
                    .LENGTH_SHORT).show();
        } else {
            Toast.makeText(Main_Activity.this, "Replace Sticker failed!", Toast.LENGTH_SHORT).show();
        }
    }

    public void lockSticker() {
        sticker_view.setLocked(!sticker_view.isLocked());
    }

    public void removeSticker() {
        if (sticker_view.removeCurrentSticker()) {
            Toast.makeText(Main_Activity.this, "Remove current Sticker successfully!", Toast.LENGTH_SHORT)
                    .show();
        } else {
            Toast.makeText(Main_Activity.this, "Remove current Sticker failed!", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    public void removeAllSticker() {
        sticker_view.removeAllStickers();
    }

    public void resetSticker() {
        sticker_view.removeAllStickers();
        loadSticker();
    }

    public void addSticker() {
        final TextSticker sticker = new TextSticker(this);
        sticker.setText("Hello, world!");
        sticker.setTextColor(Color.BLUE);
        sticker.setTextAlign(Layout.Alignment.ALIGN_CENTER);
        sticker.resizeText();
        //sticker.setTypeface(Typeface.DEFAULT_BOLD);
        sticker_view.addSticker(sticker);


        seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int[] location = new int[2];
                sticker_view.getLocationOnScreen(location);
                Log.e("HVV1312Location"," location 0  "+location[0]+"location 1 "+location[1]);
                sticker_view.customeZoomAndRotateSticker(sticker,(location[0]*i)/100,(location[1]*i)/100,location[0],location[1]);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }


}
