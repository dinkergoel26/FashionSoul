
package com.ibm.mobileappbuilder.storecatalog20150911132549.ui;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.view.View;
import com.ibm.mobileappbuilder.storecatalog20150911132549.ds.ProductsDSItem;
import com.ibm.mobileappbuilder.storecatalog20150911132549.ds.ProductsDSService;
import com.ibm.mobileappbuilder.storecatalog20150911132549.presenters.JacketsFormPresenter;
import com.ibm.mobileappbuilder.storecatalog20150911132549.R;
import ibmmobileappbuilder.ui.FormFragment;
import ibmmobileappbuilder.util.StringUtils;
import ibmmobileappbuilder.views.ImagePicker;
import ibmmobileappbuilder.views.TextWatcherAdapter;
import java.io.IOException;
import java.io.File;

import static android.net.Uri.fromFile;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.storecatalog20150911132549.ds.ProductsDSItem;
import com.ibm.mobileappbuilder.storecatalog20150911132549.ds.ProductsDS;

public class ProductsDSItemFormFragment extends FormFragment<ProductsDSItem> {

    private CrudDatasource<ProductsDSItem> datasource;

    public static ProductsDSItemFormFragment newInstance(Bundle args){
        ProductsDSItemFormFragment fr = new ProductsDSItemFormFragment();
        fr.setArguments(args);

        return fr;
    }

    public ProductsDSItemFormFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        // the presenter for this view
        setPresenter(new JacketsFormPresenter(
                (CrudDatasource) getDatasource(),
                this));

            }

    @Override
    protected ProductsDSItem newItem() {
        return new ProductsDSItem();
    }

    private ProductsDSService getRestService(){
        return ProductsDSService.getInstance();
    }

    @Override
    protected int getLayout() {
        return R.layout.jackets_form;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final ProductsDSItem item, View view) {
        
        bindString(R.id.productsds_name, item.name, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.name = s.toString();
            }
        });
        
        
        bindString(R.id.productsds_description, item.description, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.description = s.toString();
            }
        });
        
        
        bindString(R.id.productsds_category, item.category, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.category = s.toString();
            }
        });
        
        
        bindString(R.id.productsds_price, item.price, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.price = s.toString();
            }
        });
        
        
        bindString(R.id.productsds_rating, item.rating, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.rating = s.toString();
            }
        });
        
        
        bindImage(R.id.productsds_picture,
            item.picture != null ?
                getRestService().getImageUrl(item.picture) : null,
            0,
            new ImagePicker.Callback(){
                @Override
                public void imageRemoved(){
                    item.picture = null;
                    item.pictureUri = null;
                    ((ImagePicker) getView().findViewById(R.id.productsds_picture)).clear();
                }
            }
        );
        
        
        bindImage(R.id.productsds_thumbnail,
            item.thumbnail != null ?
                getRestService().getImageUrl(item.thumbnail) : null,
            1,
            new ImagePicker.Callback(){
                @Override
                public void imageRemoved(){
                    item.thumbnail = null;
                    item.thumbnailUri = null;
                    ((ImagePicker) getView().findViewById(R.id.productsds_thumbnail)).clear();
                }
            }
        );
        
    }

    @Override
    public Datasource<ProductsDSItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
       datasource = ProductsDS.getInstance(new SearchOptions());
        return datasource;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            ImagePicker picker = null;
            Uri imageUri = null;
            ProductsDSItem item = getItem();

            if((requestCode & ImagePicker.GALLERY_REQUEST_CODE) == ImagePicker.GALLERY_REQUEST_CODE) {
              imageUri = data.getData();
              switch (requestCode - ImagePicker.GALLERY_REQUEST_CODE) {
                        
                        case 0:   // picture field
                            item.pictureUri = imageUri;
                            item.picture = "cid:picture";
                            picker = (ImagePicker) getView().findViewById(R.id.productsds_picture);
                            break;
                        
                        
                        case 1:   // thumbnail field
                            item.thumbnailUri = imageUri;
                            item.thumbnail = "cid:thumbnail";
                            picker = (ImagePicker) getView().findViewById(R.id.productsds_thumbnail);
                            break;
                        
                default:
                  return;
              }

              picker.setImageUri(imageUri);
            } else if((requestCode & ImagePicker.CAPTURE_REQUEST_CODE) == ImagePicker.CAPTURE_REQUEST_CODE) {
				      switch (requestCode - ImagePicker.CAPTURE_REQUEST_CODE) {
                        
                        case 0:   // picture field
                            picker = (ImagePicker) getView().findViewById(R.id.productsds_picture);
                            imageUri = fromFile(picker.getImageFile());
                        		item.pictureUri = imageUri;
                            item.picture = "cid:picture";
                            break;
                        
                        
                        case 1:   // thumbnail field
                            picker = (ImagePicker) getView().findViewById(R.id.productsds_thumbnail);
                            imageUri = fromFile(picker.getImageFile());
                        		item.thumbnailUri = imageUri;
                            item.thumbnail = "cid:thumbnail";
                            break;
                        
                default:
                  return;
              }
              picker.setImageUri(imageUri);
            }
        }
    }
}

