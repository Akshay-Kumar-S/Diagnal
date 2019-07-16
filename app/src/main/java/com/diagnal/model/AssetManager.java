package com.diagnal.model;

import com.diagnal.DiagnalApplication;
import com.diagnal.interfaces.MainPresenter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AssetManager {
    private MainPresenter mainPresenter;
    private final String page = "page", content_items = "content-items", content = "content";

    public AssetManager(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    public void getDataFromAsset(String assetFileName) {
        String stringData = loadJSONFromAsset(assetFileName);
        JSONObject jsonData = getJsonObject(stringData);
        ArrayList<DataModel> dataModels = processContentJson(jsonData);
        if (dataModels != null) {
            this.mainPresenter.updateDataFromAsset(dataModels);
        }
    }

    private String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            InputStream is = DiagnalApplication
                    .getContext()
                    .getAssets()
                    .open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }

    private JSONObject getJsonObject(String data) {
        try {
            if (data != null && !data.equals("")) {
                return new JSONObject(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<DataModel> processContentJson(JSONObject jsonObject) {
        try {
            if (jsonObject != null) {
                JSONObject page = jsonObject.getJSONObject(this.page);
                JSONObject contentItems = page.getJSONObject(this.content_items);
                JSONArray content = contentItems.getJSONArray(this.content);
                return getDataModel(content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<DataModel> getDataModel(JSONArray array) {
        try {
            ArrayList<DataModel> dataModels = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                dataModels.add(new DataModel(
                        getJsonObjectString(object, "name", "unknown"),
                        getJsonObjectString(object, "poster-image", "unknown"))
                );
            }
            return dataModels;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getJsonObjectString(JSONObject jsonObject, String param, String defaultValue) {
        try {
            if (jsonObject.get(param) != null) {
                return jsonObject.getString(param);
            }
        } catch (Exception e) {
        }
        return defaultValue;
    }
}
