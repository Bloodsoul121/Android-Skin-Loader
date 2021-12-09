package cn.feng.skin.demo.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import cn.feng.skin.demo.databinding.ActivityMainBinding
import cn.feng.skin.manager.base.BaseFragmentActivity
import cn.feng.skin.manager.listener.ILoaderListener
import cn.feng.skin.manager.loader.SkinManager
import cn.feng.skin.manager.util.L
import com.blankj.utilcode.util.ResourceUtils
import com.blankj.utilcode.util.Utils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File

class MainActivity : BaseFragmentActivity() {

    companion object {
        private const val TAG = "MainActivity"
        private const val SKIN_NAME = "BlackFantacy.skin"
        private val SKIN_DIR = Utils.getApp().filesDir.absolutePath + File.separator + SKIN_NAME
    }

    private lateinit var binding: ActivityMainBinding
    private var isSkinDefault: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initEvent()
    }

    private fun initView() {
        binding.btnSkinDefault.setOnClickListener { setSkinDefault() }
        binding.btnSkinNew.setOnClickListener { setNewSkin() }
    }

    private fun initEvent() {
        GlobalScope.launch {
            Log.i(TAG, "initEvent: ${Thread.currentThread().name}")
            ResourceUtils.copyFileFromAssets(SKIN_NAME, SKIN_DIR)
        }
    }

    private fun setSkinDefault() {
        if (isSkinDefault) {
            return
        }
        isSkinDefault = true
        SkinManager.getInstance().restoreDefaultTheme()
        Toast.makeText(applicationContext, "切换成功", Toast.LENGTH_SHORT).show()
    }

    private fun setNewSkin() {
        if (!isSkinDefault) {
            return
        }
        isSkinDefault = false
        val skin = File(SKIN_DIR)
        if (!skin.exists()) {
            Toast.makeText(applicationContext, "请检查" + SKIN_DIR + "是否存在", Toast.LENGTH_SHORT).show()
            return
        }
        SkinManager.getInstance().load(skin.absolutePath,
            object : ILoaderListener {
                override fun onStart() {
                    L.e("startloadSkin")
                }

                override fun onSuccess() {
                    L.e("loadSkinSuccess")
                    Toast.makeText(applicationContext, "切换成功", Toast.LENGTH_SHORT).show()
                }

                override fun onFailed() {
                    L.e("loadSkinFail")
                    Toast.makeText(applicationContext, "切换失败", Toast.LENGTH_SHORT).show()
                }
            })
    }

}