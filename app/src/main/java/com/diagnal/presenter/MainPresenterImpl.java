package com.diagnal.presenter;

import com.diagnal.model.AssetManager;
import com.diagnal.model.DataModel;
import com.diagnal.interfaces.LazyLoadListener;
import com.diagnal.interfaces.MainPresenter;
import com.diagnal.interfaces.MainView;

import java.util.ArrayList;

public class MainPresenterImpl implements MainPresenter, LazyLoadListener {

    private MainView mainView;
    private ArrayList<DataModel> dataInfo;
    private int pageNo = 0;

    public MainPresenterImpl(MainView mainView, ArrayList<DataModel> dataInfo) {
        this.mainView = mainView;
        this.dataInfo = dataInfo;
    }

    /*
    This method is initiating data fetching from asset and populating UI
    initiating from mainActivity
    result will be update in method updateDataFromAsset(String dataSting)
     */
    public void getData(int pageNo) {
        this.pageNo = pageNo;
        AssetManager assetManager = new AssetManager(this);
        assetManager.getDataFromAsset(getAssetFileName(pageNo));
    }

    @Override
    public void updateDataFromAsset(ArrayList<DataModel> dataModels) {
        this.dataInfo.addAll(dataModels);
        mainView.notifyDataSetUpdate();
    }

    @Override
    public void loadNext() {
        getData(++pageNo);
    }

    private String getAssetFileName(int pageNo) {
        return "CONTENTLISTINGPAGE-PAGE"+pageNo+".json";
    }
}
