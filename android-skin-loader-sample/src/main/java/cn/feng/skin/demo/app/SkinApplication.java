package cn.feng.skin.demo.app;

import android.app.Application;
import cn.feng.skin.manager.loader.SkinManager;

public class SkinApplication extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
		initSkinLoader();
	}

	private void initSkinLoader() {
		SkinManager.getInstance().init(this);
//		SkinManager.getInstance().load();
	}
}