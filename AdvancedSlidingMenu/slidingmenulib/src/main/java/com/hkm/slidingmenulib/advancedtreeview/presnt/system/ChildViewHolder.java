package com.hkm.slidingmenulib.advancedtreeview.presnt.system;


import android.view.View;
import android.view.View.OnClickListener;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.hkm.slidingmenulib.R;
import com.hkm.slidingmenulib.Util.TreeList;
import com.hkm.slidingmenulib.advancedtreeview.BaseViewHolder;
import com.hkm.slidingmenulib.advancedtreeview.ExpandableItemData;
import com.hkm.slidingmenulib.advancedtreeview.ChildVH;
import com.hkm.slidingmenulib.advancedtreeview.customizationbase.child;

/**
 * Author Zheng Haibo, jjhesk MIT
 * PersonalWebsite
 * Description
 */
public class ChildViewHolder extends child {

    public TextView text;
    public ImageView image;
    public RelativeLayout relativeLayout;

    public ChildViewHolder(View itemView) {
        super(itemView);
        text = (TextView) itemView.findViewById(R.id.text);
        image = (ImageView) itemView.findViewById(R.id.image);
        relativeLayout = (RelativeLayout) itemView.findViewById(R.id.container);
        itemMargin = itemView.getContext().getResources()
                .getDimensionPixelSize(R.dimen.item_margin);
        offsetMargin = itemView.getContext().getResources()
                .getDimensionPixelSize(R.dimen.expand_size);
    }

    @Override
    public void bindView(final ExpandableItemData itemData, int position) {
        image.setLayoutParams(getParamsLayoutOffset(image, itemData));
        text.setText(itemData.getText());
        relativeLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                TreeList.openFileInSystem(itemData.getPath(), view.getContext());
                onChildItemClick(itemData.getPath());
            }
        });
    }

    @Override
    public void onChildItemClick(String path) {

    }



    /**
     * not in use
     *
     * @param view nah
     * @return nah
     */
    @Override
    protected BaseViewHolder getHolder(View view) {
        return null;
    }

    /**
     * not in use
     *
     * @return nah
     */
    @Override
    protected int getLayout() {
        return 0;
    }


}