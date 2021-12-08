package cn.feng.skin.demo.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.blankj.utilcode.util.ResourceUtils;

import cn.feng.skin.demo.R;
import cn.feng.skin.demo.fragment.ArticleListFragment;
import cn.feng.skin.manager.base.BaseFragmentActivity;

public class MainActivity extends BaseFragmentActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initFragment();

		ResourceUtils.copyFileFromAssets(SettingActivity.SKIN_NAME, SettingActivity.SKIN_DIR);
	}

	private void initFragment() {
		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragment_container);
		if(fragment == null){
			fragment = ArticleListFragment.newInstance();
			fm.beginTransaction()
				.add(R.id.fragment_container, fragment)
				.commit();
		}
	}
}
