package com.musixmatch.lyrics.example;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;

import com.musixmatch.lyrics.MissingPluginException;
import com.musixmatch.lyrics.musiXmatchLyricsConnector;

public class musiXmatchSDKActivity extends Activity {
    private musiXmatchLyricsConnector mLyricsPlugin = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        final String artistName = "The Beatles";
        final String trackName = "Let It Be";

        mLyricsPlugin = new musiXmatchLyricsConnector(this);
        mLyricsPlugin.setLoadingMessage("Your custom title", "Your custom message");

        findViewById(R.id.showLyrics).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mLyricsPlugin.startLyricsActivity(artistName, trackName);
                } catch (MissingPluginException e) {
                    mLyricsPlugin.downloadLyricsPlugin();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        mLyricsPlugin.doBindService();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mLyricsPlugin.doUnbindService();
        super.onPause();
    }
}
