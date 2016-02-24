package com.hkm.slidingmenulib.v4.recycler;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hkm.slidingmenulib.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.quickAdapter.easyRegularAdapter;


/**
 * Created by hesk on 15/2/16.
 */
public abstract class catelogGrid<adapter extends easyRegularAdapter, binder extends UltimateRecyclerviewViewHolder>
        extends
        paginator {
    public static String TAG = "catelog";
    public UltimateRecyclerView listview_layout;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getFragmentResId(), container, false);
    }

    @IdRes
    protected int getUltimate_recycler_viewResId() {
        return R.id.urv_main_list;
    }

    @LayoutRes
    protected abstract int getFragmentResId();

    protected abstract void onClickItem(final String route);

    protected abstract void onClickItem(final long route_id);

    protected abstract int getColumn();

    protected abstract adapter getAdatperWithdata();

    protected abstract void setUltimateRecyclerViewExtra(final UltimateRecyclerView listview, final adapter madapter);

    /**
     * step 1:
     * takes the arguement form the intent bundle and determine if there is a need to queue a loading process. If that is a yes then we need to load up the data before displaying the list out.
     *
     * @param r and the data bundle
     * @return tells if  there is a loading process to be done before hand
     */
    protected abstract boolean onArguments(final Bundle r);

    /**
     * step 2:
     * this is the call for the loading the data stream externally
     *
     * @param confirmAdapter the adapter
     */
    protected abstract void loadDataInitial(final adapter confirmAdapter);

    protected GridLayoutManager mLayoutManager;
    protected adapter madapter;


    protected void renderviewlayout(View view) throws Exception {
        listview_layout = (UltimateRecyclerView) view.findViewById(getUltimate_recycler_viewResId());
        listview_layout.setHasFixedSize(true);
        listview_layout.setSaveEnabled(true);
        if (mLayoutManager == null) {
            mLayoutManager = new GridLayoutManager(view.getContext(), getColumn(), LinearLayoutManager.VERTICAL, false);
        }
        listview_layout.setLayoutManager(mLayoutManager);
        listview_layout.setAdapter(madapter = getAdatperWithdata());
        setUltimateRecyclerViewExtra(listview_layout, madapter);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        try {
            renderviewlayout(view);
            if (getArguments() != null) {
                if (onArguments(getArguments())) {
                    showLoadingCircle();
                    initPaginator();
                    loadDataInitial(madapter);
                }
            }
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }

    }

}