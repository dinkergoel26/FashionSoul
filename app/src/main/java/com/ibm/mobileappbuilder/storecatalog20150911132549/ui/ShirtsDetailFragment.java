
package com.ibm.mobileappbuilder.storecatalog20150911132549.ui;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ibm.mobileappbuilder.storecatalog20150911132549.R;
import ibmmobileappbuilder.behaviors.ShareBehavior;
import ibmmobileappbuilder.ds.restds.AppNowDatasource;
import ibmmobileappbuilder.util.image.ImageLoader;
import ibmmobileappbuilder.util.image.PicassoImageLoader;
import ibmmobileappbuilder.util.StringUtils;
import java.net.URL;
import static ibmmobileappbuilder.util.image.ImageLoaderRequest.Builder.imageLoaderRequest;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.storecatalog20150911132549.ds.ProductsDSItem;
import com.ibm.mobileappbuilder.storecatalog20150911132549.ds.ProductsDS;

public class ShirtsDetailFragment extends ibmmobileappbuilder.ui.DetailFragment<ProductsDSItem> implements ShareBehavior.ShareListener  {

    private Datasource<ProductsDSItem> datasource;
    public static ShirtsDetailFragment newInstance(Bundle args){
        ShirtsDetailFragment fr = new ShirtsDetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public ShirtsDetailFragment(){
        super();
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
    public void onCreate(Bundle state) {
        super.onCreate(state);
        addBehavior(new ShareBehavior(getActivity(), this));

    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.shirtsdetail_detail;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final ProductsDSItem item, View view) {
        
        ImageView view0 = (ImageView) view.findViewById(R.id.view0);
        URL view0Media = ((AppNowDatasource) getDatasource()).getImageUrl(item.picture);
        if(view0Media != null){
          ImageLoader imageLoader = new PicassoImageLoader(view0.getContext());
          imageLoader.load(imageLoaderRequest()
                                   .withPath(view0Media.toExternalForm())
                                   .withTargetView(view0)
                                   .fit()
                                   .build()
                    );
        	
        } else {
          view0.setImageDrawable(null);
        }
        if (item.price != null && item.rating != null){
            
            TextView view1 = (TextView) view.findViewById(R.id.view1);
            view1.setText("$" + item.price + " " + item.rating);
            
        }
        if (item.description != null){
            
            TextView view2 = (TextView) view.findViewById(R.id.view2);
            view2.setText(item.description);
            
        }
    }

    @Override
    protected void onShow(ProductsDSItem item) {
        // set the title for this fragment
        getActivity().setTitle(item.name);
    }
    @Override
    public void onShare() {
        ProductsDSItem item = getItem();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, (item.price != null && item.rating != null ? "$" + item.price + " " + item.rating : "" ) + "\n" +
                    (item.description != null ? item.description : "" ));
        intent.putExtra(Intent.EXTRA_SUBJECT, item.name);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.share)), 1);
    }
}

