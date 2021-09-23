package com.kebob.geta

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kebob.geta.data.Meal
import com.kebob.geta.databinding.ActivityTimeListBinding

class TimeListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTimeListBinding
    private lateinit var adapter: TimeListAdapter
    private lateinit var menu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        setActionBar()

        val array = arrayOf(
            Meal("아침", "9:00", "11:00", "엄마", ""),
            Meal("점심", "9:00", "11:00", "엄마", ""),
            Meal("저녁", "9:00", "11:00", "엄마", "")
        )
        adapter = TimeListAdapter(array)
        adapter.setOnMyItemLongClickListener(object: TimeListAdapter.OnItemLongClickListener {
            override fun onItemLongClick(view: View, position: Int) {
                val delete: MenuItem = menu.findItem(R.id.menu_delete_time)
                delete.isVisible = true

                for (i in 0 until adapter.itemCount) {
                    val item = binding.recyclerView.getChildAt(i)
                    val checkBox: CheckBox = item.findViewById(R.id.checkBox)
                    checkBox.visibility = View.VISIBLE

                    if (position == i) {
                        checkBox.isChecked = true
                    }
                }
            }
        })

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        val fabAdd = binding.fabAdd
        fabAdd.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
            R.id.menu_delete_time -> {
                // TODO: item 삭제
                for (i in 0 until adapter.itemCount) {
                    val view = binding.recyclerView.getChildAt(i)
                    val checkBox: CheckBox = view.findViewById(R.id.checkBox)
                    checkBox.visibility = View.GONE
                }

                item.isVisible = false
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_time_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        this.menu = menu?: return false
        return super.onPrepareOptionsMenu(menu)
    }

    private fun setActionBar() {
        supportActionBar?.let {
            CustomActionBar(this, it).setActionBar()
            it.setDisplayHomeAsUpEnabled(true)
            it.title = "급식 시간 관리"
        }
    }
}
