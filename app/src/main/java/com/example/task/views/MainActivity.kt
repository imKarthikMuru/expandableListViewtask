package com.example.task.views

import androidx.appcompat.app.AppCompatActivity
import com.example.task.Adapters.StoreMenuAdapter
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.task.Models.StoreMenu
import com.example.task.Utils.constants
import com.google.gson.Gson
import com.example.task.views.MainActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.CompoundButton
import android.widget.ExpandableListView.OnGroupClickListener
import android.widget.ExpandableListView
import android.widget.Toast
import android.widget.ExpandableListView.OnGroupExpandListener
import android.widget.ExpandableListView.OnGroupCollapseListener
import android.widget.ExpandableListView.OnChildClickListener
import com.example.task.databinding.ActivityMainBinding
import com.example.task.viewModels.ViewModel

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    var viewModel: ViewModel? = null
    var adapter: StoreMenuAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val v: View = binding!!.root
        setContentView(v)
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        storeMenuList
        binding!!.btn.setOnClickListener {
            val checkedList = constants.Lists.parentItems
            deleteSelected(checkedList)
        }
    }

    private val storeMenuList: Unit
        private get() {
            binding!!.pBar.isIndeterminate = true
            viewModel!!.storeMenuList
                .observe(this) { storeMenus ->
                    if (storeMenus != null) {
                        if (storeMenus.size != 0) {
                            constants.Lists.parentItems.clear()
                            constants.Lists.childItems.clear()
                            constants.Lists.parentItems.addAll(storeMenus)
                            for (storeMenu in storeMenus) {
                                constants.Lists.childItems.add(storeMenu.list)
                            }
                            LoadRv()
                            val gson = Gson()
                            val JsonData = gson.toJson(storeMenus)
                        }
                    }
                }
        }

    private fun LoadRv() {
        val storeMenus = constants.Lists.parentItems
        val gson = Gson()
        Log.e(TAG, "LoadRv: :::::::" + gson.toJson(storeMenus))
        binding!!.pBar.isIndeterminate = false
        val layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        //        binding.recyclerView.setLayoutManager(layoutManager);
        adapter = StoreMenuAdapter(this@MainActivity, storeMenus, false)
        binding!!.recyclerView.setAdapter(adapter)
        binding!!.checkAll.setOnCheckedChangeListener { compoundButton, b ->
            for (storeMenu in storeMenus) {
                storeMenu.isChecked = b
                for (menu in storeMenu.list) {
                    menu.isChecked = b
                }
            }
            adapter!!.notifyDataSetChanged()
        }
        binding!!.recyclerView.setOnGroupClickListener { parent, v, groupPosition, id ->
            Toast.makeText(
                applicationContext,
                "Group Clicked " + storeMenus[groupPosition].name,
                Toast.LENGTH_SHORT
            ).show()
            false
        }

        // Listview Group expanded listener
        binding!!.recyclerView.setOnGroupExpandListener { groupPosition ->
            Toast.makeText(
                applicationContext,
                storeMenus[groupPosition].name + " Expanded",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Listview Group collasped listener
        binding!!.recyclerView.setOnGroupCollapseListener { groupPosition ->
            Toast.makeText(
                applicationContext,
                storeMenus[groupPosition].name + " Collapsed",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Listview on child click listener
        binding!!.recyclerView.setOnChildClickListener { parent, v, groupPosition, childPosition, id -> // TODO Auto-generated method stub
            Toast.makeText(
                this@MainActivity,
                storeMenus[groupPosition]
                    .toString() + " : "
                        + storeMenus[groupPosition]
                    .list[childPosition].name, Toast.LENGTH_SHORT
            )
                .show()
            false
        }
    }

    private fun deleteSelected(checkedList: List<StoreMenu>) {
        for (storeMenu in checkedList) {
            var i = 0
            while (i < storeMenu.list.size) {
                if (storeMenu.list[i].isChecked) {
                    storeMenu.list.removeAt(i)
                    i-- //to check the same array position again
                }
                i++
            }
        }
        adapter!!.notifyDataSetChanged()
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}