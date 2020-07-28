package com.flys.generictools.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @todo Elle définit un adaptateur générique.
 * @since 25/08/2018
 */

public abstract class AbstractAdapter<T extends Object> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Context context;

    public AbstractAdapter() {
    }


    public AbstractAdapter(Context context) {
        this.context = context;
    }

}
