package com.diagnal.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.GridView;

import com.diagnal.model.DataModel;
import com.diagnal.adapter.GridAdapter;
import com.diagnal.presenter.MainPresenterImpl;
import com.diagnal.interfaces.MainView;
import com.diagnal.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainView {

    MainPresenterImpl presenter;
    ArrayList<DataModel> dataInfo;
    GridAdapter gridAdapter;
    GridView gridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
    }

    private void init() {
        dataInfo = new ArrayList<>();
        gridview = findViewById(R.id.gv_main);
        gridAdapter = new GridAdapter(getApplicationContext(), dataInfo);
        gridview.setAdapter(gridAdapter);
        gridview.setOnScrollListener(gridAdapter);
        presenter = new MainPresenterImpl(this, dataInfo);
        gridAdapter.setListener(presenter);
        presenter.getData(1);
    }

    @Override
    public void notifyDataSetUpdate() {
        gridAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
