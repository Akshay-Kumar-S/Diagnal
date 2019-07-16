package com.diagnal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.diagnal.R;
import com.diagnal.interfaces.LazyLoadListener;
import com.diagnal.model.DataModel;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter implements GridView.OnScrollListener {
    private ArrayList<DataModel> dataModels;
    private static LayoutInflater inflater = null;
    private LazyLoadListener listener;

    private static final int DEFAULT_THRESHOLD = 10;
    private boolean loading = true;
    private int previousTotal = 0;
    private int threshold = DEFAULT_THRESHOLD;

    public GridAdapter(Context context, ArrayList<DataModel> dataModels) {
        this.dataModels = dataModels;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dataModels.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder = new Holder();
        View rowView = inflater.inflate(R.layout.single_item_view, null);
        holder.name = rowView.findViewById(R.id.tv_name);
        holder.poster = rowView.findViewById(R.id.iv_poster);

        holder.name.setSelected(true);
        holder.name.setText(dataModels.get(i).getName());
        holder.poster.setImageResource(getImage(dataModels.get(i).getImage()));

        return rowView;
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (loading) {
            if (totalItemCount > previousTotal) {
                // the loading has finished
                loading = false;
                previousTotal = totalItemCount;
            }
        }

        // check if the List needs more data
        if (!loading && ((firstVisibleItem + visibleItemCount) >= (totalItemCount - threshold))) {
            loading = true;
            // List needs more data. Go fetch !!
            listener.loadNext();
        }
    }


    public void setListener(LazyLoadListener lazyLoadListener) {
        this.listener = lazyLoadListener;
    }

    public class Holder {
        TextView name;
        ImageView poster;
    }

    private int getImage(String image_name) {
        int image = R.drawable.placeholder_for_missing_posters;
        switch (image_name) {
            case "poster1.jpg":
                image = R.drawable.poster1;
                break;
            case "poster2.jpg":
                image = R.drawable.poster2;
                break;
            case "poster3.jpg":
                image = R.drawable.poster3;
                break;
            case "poster4.jpg":
                image = R.drawable.poster4;
                break;
            case "poster5.jpg":
                image = R.drawable.poster5;
                break;
            case "poster6.jpg":
                image = R.drawable.poster6;
                break;
            case "poster7.jpg":
                image = R.drawable.poster7;
                break;
            case "poster8.jpg":
                image = R.drawable.poster8;
                break;
            case "poster9.jpg":
                image = R.drawable.poster9;
                break;
        }
        return image;
    }
}
