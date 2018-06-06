StickerView
===========
Android fully customizable StickerView which can add sticker(text/drawable) and zoom,drag,flip,delete it.

You can copy the source code to your project so you can design your own function.

<p align="center">
 <img height=393 width=200 src="https://github.com/shashi180493/StickerView/blob/master/Sticker1.png"/>
 &nbsp;&nbsp;&nbsp;
 <img height=393 width=200 src="https://github.com/shashi180493/StickerView/blob/master/Sticker2.png"/>
</p>

I would appreciate any kind of help to improve this library. Thanks

Usage
-----

You must declare the following view in your xml layout:

```xml
<com.shashi.mysticker.StickerView
        android:id="@+id/sticker_view"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_gravity="center"
        android:layout_weight="1"
        app:showBorder="true"
        app:showIcons="true">

        <ImageView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:src="@drawable/sample" />

</com.shashi.mysticker.StickerView>
```

Add Sticker
-----

If the sticker is text, you can set text color, font and alignment and the region which holds the text and if the sticker is drawable  it's intrinsic width and height can not be zero.

```java
sticker_view.addSticker(sticker)
sticker_view.replace(sticker)
sticker_view.remove(sticker)
sticker_view.removeCurrentSticker()
sticker_view.removeAllStickers()
sticker_view.setLocked(true)
```

set custom icon and icon event and position

```java
BitmapStickerIcon demoIcon =
        new BitmapStickerIcon(ContextCompat.getDrawable(this, R.drawable.ic_star),
            BitmapStickerIcon.LEFT_BOTTOM);
demoIcon.setIconEvent(new DemoIconEvent());
sticker_view.setIcons(Arrays.asList(deleteIcon, zoomIcon, flipIcon, demoIcon));
```

Developed By
--------------------

Shashikant Patel - <shashi180493@gmail.com>

<a href="https://www.facebook.com/imshashikantpatel">
  <img alt="Follow me on Facebook"
       height=50 width=50
       src="https://github.com/shashi180493/StickerView/blob/master/facebook.png" />
</a>
<a href="https://plus.google.com/u/0/+ShashikantPatelsurvivor">
  <img alt="Follow me on Google+"
       height=50 width=50
       src="https://github.com/shashi180493/StickerView/blob/master/google-plus.png" />
</a>
<a href="https://www.linkedin.com/in/shashikant-patel-01597180/">
  <img alt="Follow me on LinkedIn"
       height=50 width=50
       src="https://github.com/shashi180493/StickerView/blob/master/linkedin.png" />

