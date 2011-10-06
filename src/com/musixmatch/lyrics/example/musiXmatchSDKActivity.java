package com.musixmatch.lyrics.example;

import com.musixmatch.lyrics.musiXmatchLyricsConnector;
import com.musixmatch.lyrics.example.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class musiXmatchSDKActivity extends Activity {
	private musiXmatchLyricsConnector lyricsPlugin= null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final String mArtistName = "The Beatles";
        final String mTrackName  = "Let It Be";
        lyricsPlugin = new musiXmatchLyricsConnector(this);
        
        // Please uncomment this line when approved and before going in production
        //
        // lyricsPlugin.setTestMode(false);

        Button btn = (Button) findViewById(R.id.showLyrics);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (lyricsPlugin.getIsBound())
					try {
						lyricsPlugin.startLyricsActivity(mArtistName,mTrackName);
					} catch (RemoteException e) {
						lyricsPlugin.downloadLyricsPlugin();
					}
				else
					lyricsPlugin.downloadLyricsPlugin();
			}
		}); 
    }
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	if (!lyricsPlugin.getIsBound())
    		lyricsPlugin.doBindService();

    	super.onResume();
    }

}