package com.gl4.tp5

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView


class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)
    }
    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>) {

    }
}