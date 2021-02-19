package com.example.finalnote2;


import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.os.Handler;

        import com.luolc.emojirain.EmojiRainLayout;

public class splash extends AppCompatActivity {

    private EmojiRainLayout mContainer;

    Handler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash);

        mContainer = (EmojiRainLayout)findViewById( R.id.activity_main);
        //  btnStart = (Button) findViewById( R.id.start_button );

        //  btnStart.setOnClickListener( new View.OnClickListener() {
        //   @Override
        //  public void onClick(View view) {
        mContainer.addEmoji(R.drawable.emoji_1_3);
        mContainer.addEmoji(R.drawable.emoji_2_3);
        mContainer.addEmoji(R.drawable.emoji_3_3);
        mContainer.addEmoji(R.drawable.emoji_4_3);
        mContainer.addEmoji(R.drawable.emoji_5_3);

        mContainer.stopDropping();
        mContainer.setPer( 10 );
        mContainer.setDuration( 17200 );
        mContainer.setDropDuration( 2400 );
        mContainer.setDropFrequency( 500 );


        mContainer.startDropping();








        h = new Handler();
        h.postDelayed( new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(splash.this, MainActivity.class );
                startActivity( i );
                finish();

            }
        }, 9000 );


    }
}
