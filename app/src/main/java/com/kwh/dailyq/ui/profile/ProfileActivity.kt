package com.kwh.dailyq.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.kwh.dailyq.R
import com.kwh.dailyq.databinding.ActivityProfileBinding
import com.kwh.dailyq.ui.base.BaseActivity

class ProfileActivity : BaseActivity() {
    companion object {
        const val EXTRA_UID = "uid"
    }

    lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val fragment = ProfileFragment()
            fragment.arguments = Bundle().apply {
                putString(ProfileFragment.ARG_UID, intent.getStringExtra(EXTRA_UID))
            }
            val ft = supportFragmentManager.beginTransaction()
            ft.add(R.id.host, fragment).commit();
        }

        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}