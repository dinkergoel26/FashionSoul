

package com.ibm.mobileappbuilder.storecatalog20150911132549.ui;

import android.os.Bundle;

import com.ibm.mobileappbuilder.storecatalog20150911132549.R;

import java.util.ArrayList;
import java.util.List;

import ibmmobileappbuilder.MenuItem;

import ibmmobileappbuilder.actions.StartActivityAction;
import ibmmobileappbuilder.util.Constants;

/**
 * StoreCatalogScreen1Fragment menu fragment.
 */
public class StoreCatalogScreen1Fragment extends ibmmobileappbuilder.ui.MenuFragment {

    /**
     * Default constructor
     */
    public StoreCatalogScreen1Fragment(){
        super();
    }

    // Factory method
    public static StoreCatalogScreen1Fragment newInstance(Bundle args) {
        StoreCatalogScreen1Fragment fragment = new StoreCatalogScreen1Fragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
      public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
                }

    // Menu Fragment interface
    @Override
    public List<MenuItem> getMenuItems() {
        ArrayList<MenuItem> items = new ArrayList<MenuItem>();
        items.add(new MenuItem()
            .setLabel("JACKETS")
            .setIcon(R.drawable.png_store1692)
            .setAction(new StartActivityAction(JacketsActivity.class, Constants.DETAIL))
        );
        items.add(new MenuItem()
            .setLabel("TROUSERS")
            .setIcon(R.drawable.png_store4197)
            .setAction(new StartActivityAction(TrousersActivity.class, Constants.DETAIL))
        );
        items.add(new MenuItem()
            .setLabel("SHIRTS")
            .setIcon(R.drawable.png_store2362)
            .setAction(new StartActivityAction(ShirtsActivity.class, Constants.DETAIL))
        );
        items.add(new MenuItem()
            .setLabel("TIES")
            .setIcon(R.drawable.png_store340)
            .setAction(new StartActivityAction(TiesActivity.class, Constants.DETAIL))
        );
        return items;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_grid;
    }

    @Override
    public int getItemLayout() {
        return R.layout.storecatalogscreen1_item;
    }
}

