package org.allbinary.media.audio.music;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class BaseMusicService 
extends Service
{
	private MediaPlayer player;
	
	private int songId = -1;
	
	//@Override
	public IBinder onBind(Intent intent) {
		
		//Toast.makeText(this, "Music Bind", Toast.LENGTH_LONG).show();
		
		//PreLogUtil.put(CommonStrings.getInstance().START, this, "onBind");
		LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "onBind"));
		
		//songId = intent.getIntExtra("SONG", AndroidResources.raw.angels_we_have_heard);
		return null;
	}
		
	//@Override
	public void onCreate() {
		//Toast.makeText(this, "Music Service Created", Toast.LENGTH_LONG).show();
		
		LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "onCreate"));
		//PreLogUtil.put(CommonStrings.getInstance().START, this, "onCreate");		
	}

	//@Override
	public void onDestroy() {
		//Toast.makeText(this, "Music Service Stopped", Toast.LENGTH_LONG).show();
		
		LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "onDestroy"));
		//PreLogUtil.put(CommonStrings.getInstance().START, this, "onDestroy");

		if(player != null)
		{
			player.stop();
			player.reset();
			player.release();
		}
	}
	
	//@Override
	public void onStart(Intent intent, int startid) {
		//Toast.makeText(this, "Music Service Started", Toast.LENGTH_LONG).show();
		
		onStartCommand(intent);
		
		//PreLogUtil.put(CommonStrings.getInstance().START, this, "onStart");
		LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "onStart"));
	}

	//@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		onStartCommand(intent);
	    return START_STICKY;
	}
	
	public void onStartCommand(Intent intent)
	{
		//PreLogUtil.put(CommonStrings.getInstance().START, this, "onStartCommand");
		LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "onStartCommand"));

		songId = intent.getIntExtra("SONG", -1);

		if(songId != -1)
		{
			System.gc();
			
			player = MediaPlayer.create(this, songId);
			player.setLooping(false);
			
			player.start();
		}
	}	
}
