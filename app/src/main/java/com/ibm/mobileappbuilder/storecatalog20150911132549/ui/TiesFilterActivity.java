

package com.ibm.mobileappbuilder.storecatalog20150911132549.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Arrays;

import com.ibm.mobileappbuilder.storecatalog20150911132549.R;

import ibmmobileappbuilder.ui.BaseFragment;
import ibmmobileappbuilder.ui.FilterActivity;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;

import com.ibm.mobileappbuilder.storecatalog20150911132549.ds.ProductsDS;
import ibmmobileappbuilder.dialogs.ValuesSelectionDialog;
import ibmmobileappbuilder.ds.filter.StringFilter;
import ibmmobileappbuilder.views.ListSelectionPicker;
import java.util.ArrayList;

/**
 * TiesFilterActivity filter activity
 */
public class TiesFilterActivity extends FilterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // set title
        setTitle(R.string.tiesFilterActivity);
    }

    @Override
    protected Fragment getFragment() {
        return new PlaceholderFragment();
    }

    public static class PlaceholderFragment extends BaseFragment {
        private SearchOptions.Builder searchOptionsBuilder = SearchOptions.Builder.searchOptions();
        private SearchOptions searchOptions;

        // filter field values
            
    ArrayList<String> price_values;
    
    ArrayList<String> rating_values;

        public PlaceholderFragment() {
              searchOptions = searchOptionsBuilder
                                .withFixedFilters(Arrays.<Filter>asList(new StringFilter("category", "Ties")))
                                .build();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.ties_filter, container, false);
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            // Get saved values
            Bundle bundle = savedInstanceState;
            if(bundle == null) {
                bundle = getArguments();
            }
            // get initial data
                        
            price_values = bundle.getStringArrayList("price_values");
            
            rating_values = bundle.getStringArrayList("rating_values");

            // bind pickers
                        
            final ListSelectionPicker price_view = (ListSelectionPicker) view.findViewById(R.id.price_filter);
            ValuesSelectionDialog price_dialog = (ValuesSelectionDialog) getFragmentManager().findFragmentByTag("price");
            if (price_dialog == null)
                price_dialog = new ValuesSelectionDialog();
            
            // configure the dialog
            price_dialog.setColumnName("price")
                .setDatasource(ProductsDS.getInstance(searchOptions))
                .setSearchOptions(searchOptions)
                .setTitle("Price")
                .setHaveSearch(true)
                .setMultipleChoice(true);
            
            // bind the dialog to the picker
            price_view.setSelectionDialog(price_dialog)
                .setTag("price")
                .setSelectedValues(price_values)
                .setSelectedListener(new ListSelectionPicker.ListSelectedListener() {
                @Override
                public void onSelected(ArrayList<String> selected) {
                    price_values = selected;
                }
            });
            
            final ListSelectionPicker rating_view = (ListSelectionPicker) view.findViewById(R.id.rating_filter);
            ValuesSelectionDialog rating_dialog = (ValuesSelectionDialog) getFragmentManager().findFragmentByTag("rating");
            if (rating_dialog == null)
                rating_dialog = new ValuesSelectionDialog();
            
            // configure the dialog
            rating_dialog.setColumnName("rating")
                .setDatasource(ProductsDS.getInstance(searchOptions))
                .setSearchOptions(searchOptions)
                .setTitle("Rating")
                .setHaveSearch(true)
                .setMultipleChoice(true);
            
            // bind the dialog to the picker
            rating_view.setSelectionDialog(rating_dialog)
                .setTag("rating")
                .setSelectedValues(rating_values)
                .setSelectedListener(new ListSelectionPicker.ListSelectedListener() {
                @Override
                public void onSelected(ArrayList<String> selected) {
                    rating_values = selected;
                }
            });

            // Bind buttons
            Button okBtn = (Button) view.findViewById(R.id.ok);
            okBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();

                    // send filter result back to caller
                                        
                    intent.putStringArrayListExtra("price_values", price_values);
                    
                    intent.putStringArrayListExtra("rating_values", rating_values);

                    getActivity().setResult(RESULT_OK, intent);
                    getActivity().finish();
                }
            });

            Button cancelBtn = (Button) view.findViewById(R.id.reset);
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Reset values
                                        
                    price_values = new ArrayList<String>();
                    price_view.setSelectedValues(null);
                    
                    rating_values = new ArrayList<String>();
                    rating_view.setSelectedValues(null);
                }
            });
        }

        @Override
        public void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);

            // save current status
                        
            bundle.putStringArrayList("price_values", price_values);
            
            bundle.putStringArrayList("rating_values", rating_values);
        }
    }

}

