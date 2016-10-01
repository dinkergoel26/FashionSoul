package com.ibm.mobileappbuilder.storecatalog20150911132549.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ibm.mobileappbuilder.storecatalog20150911132549.R;
import ibmmobileappbuilder.behaviors.SearchBehavior;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.filter.StringFilter;
import ibmmobileappbuilder.ds.restds.AppNowDatasource;
import ibmmobileappbuilder.ui.ListGridFragment;
import ibmmobileappbuilder.util.ColorUtils;
import ibmmobileappbuilder.util.image.ImageLoader;
import ibmmobileappbuilder.util.image.PicassoImageLoader;
import ibmmobileappbuilder.util.StringUtils;
import ibmmobileappbuilder.util.ViewHolder;
import java.net.URL;
import java.util.ArrayList;
import static ibmmobileappbuilder.util.image.ImageLoaderRequest.Builder.imageLoaderRequest;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.storecatalog20150911132549.ds.ProductsDSItem;
import com.ibm.mobileappbuilder.storecatalog20150911132549.ds.ProductsDS;
import android.content.Intent;
import ibmmobileappbuilder.util.Constants;

import static ibmmobileappbuilder.util.NavigationUtils.generateIntentToAddOrUpdateItem;

/**
 * "TiesFragment" listing
 */
public class TiesFragment extends ListGridFragment<ProductsDSItem>  {

    private Datasource<ProductsDSItem> datasource;

    
    ArrayList<String> price_values;
    
    ArrayList<String> rating_values;

    public static TiesFragment newInstance(Bundle args) {
        TiesFragment fr = new TiesFragment();

        fr.setArguments(args);
        return fr;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addBehavior(new SearchBehavior(this));
    }

    protected SearchOptions getSearchOptions() {
      SearchOptions.Builder searchOptionsBuilder = SearchOptions.Builder.searchOptions();
            searchOptionsBuilder
                    .withFixedFilters(Arrays.<Filter>asList(new StringFilter("category", "Ties")));
      return searchOptionsBuilder.build();
    }


    /**
    * Layout for the list itselft
    */
    @Override
    protected int getLayout() {
        return R.layout.fragment_grid;
    }

    /**
    * Layout for each element in the list
    */
    @Override
    protected int getItemLayout() {
        return R.layout.ties_item;
    }

    @Override
    protected Datasource<ProductsDSItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
      datasource = ProductsDS.getInstance(getSearchOptions());
      return datasource;
    }

    @Override
    protected void bindView(ProductsDSItem item, View view, int position) {
        
        ImageLoader imageLoader = new PicassoImageLoader(view.getContext());
        ImageView image = ViewHolder.get(view, R.id.image);
        URL imageMedia = ((AppNowDatasource) getDatasource()).getImageUrl(item.picture);
        if(imageMedia != null){
          imageLoader.load(imageLoaderRequest()
                          .withPath(imageMedia.toExternalForm())
                          .withTargetView(image)
                          .fit()
                          .build()
          );
        	
        }
        else {
          imageLoader.load(imageLoaderRequest()
                          .withResourceToLoad(R.drawable.ic_ibm_placeholder)
                          .withTargetView(image)
                          .build()
          );
        }
        
        
        TextView title = ViewHolder.get(view, R.id.title);
        
        if (item.name != null){
            title.setText(item.name);
            
        }
    }


    @Override
    public void showDetail(ProductsDSItem item, int position) {
        Bundle args = new Bundle();
        args.putInt(Constants.ITEMPOS, position);
        args.putParcelable(Constants.CONTENT, item);
        Intent intent = new Intent(getActivity(), TiesDetailActivity.class);
        intent.putExtras(args);

        if (!getResources().getBoolean(R.bool.tabletLayout)) {
            startActivityForResult(intent, Constants.DETAIL);
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        // inflate menu options and tint icon
        inflater.inflate(R.menu.filter_menu, menu);
        ColorUtils.tintIcon(menu.findItem(R.id.filter),
                            R.color.textBarColor,
                            getActivity());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.filter){
            Intent intent = new Intent(getActivity(), TiesFilterActivity.class);

            // pass current values to filter activity
                        
            intent.putStringArrayListExtra("price_values", price_values);
            
            intent.putStringArrayListExtra("rating_values", rating_values);

            // launch filter screen
            startActivityForResult(intent, 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            // store the incoming selection
                        
            price_values = data.getStringArrayListExtra("price_values");
            
            rating_values = data.getStringArrayListExtra("rating_values");
            // apply filter to datasource
            clearFilters();

                        
            if(price_values != null && price_values.size() > 0)
                addStringFilter("price", price_values);
            
            if(rating_values != null && rating_values.size() > 0)
                addStringFilter("rating", rating_values);
            // and finally refresh the list
            refresh();

            // and redraw menu (to refresh tinted icons, like the search icon)
            getActivity().invalidateOptionsMenu();
        }
    }
}

