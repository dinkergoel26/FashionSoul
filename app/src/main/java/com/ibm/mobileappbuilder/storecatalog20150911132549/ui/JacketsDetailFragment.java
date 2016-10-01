
package com.ibm.mobileappbuilder.storecatalog20150911132549.ui;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ibm.mobileappbuilder.storecatalog20150911132549.ds.ProductsDSService;
import com.ibm.mobileappbuilder.storecatalog20150911132549.presenters.JacketsDetailPresenter;
import com.ibm.mobileappbuilder.storecatalog20150911132549.R;
import ibmmobileappbuilder.behaviors.FabBehaviour;
import ibmmobileappbuilder.behaviors.ShareBehavior;
import ibmmobileappbuilder.ds.restds.AppNowDatasource;
import ibmmobileappbuilder.mvp.presenter.DetailCrudPresenter;
import ibmmobileappbuilder.util.ColorUtils;
import ibmmobileappbuilder.util.Constants;
import ibmmobileappbuilder.util.image.ImageLoader;
import ibmmobileappbuilder.util.image.PicassoImageLoader;
import ibmmobileappbuilder.util.StringUtils;
import java.net.URL;
import static ibmmobileappbuilder.util.image.ImageLoaderRequest.Builder.imageLoaderRequest;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.storecatalog20150911132549.ds.ProductsDSItem;
import com.ibm.mobileappbuilder.storecatalog20150911132549.ds.ProductsDS;

public class JacketsDetailFragment extends ibmmobileappbuilder.ui.DetailFragment<ProductsDSItem> implements ShareBehavior.ShareListener  {

    private CrudDatasource<ProductsDSItem> datasource;
    public static JacketsDetailFragment newInstance(Bundle args){
        JacketsDetailFragment fr = new JacketsDetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public JacketsDetailFragment(){
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
        // the presenter for this view
        setPresenter(new JacketsDetailPresenter(
                (CrudDatasource) getDatasource(),
                this));
        // Edit button
        addBehavior(new FabBehaviour(this, R.drawable.ic_edit_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DetailCrudPresenter<ProductsDSItem>) getPresenter()).editForm(getItem());
            }
        }));
        addBehavior(new ShareBehavior(getActivity(), this));

    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.jacketsdetail_detail;
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
        if (item.price != null){
            
            TextView view1 = (TextView) view.findViewById(R.id.view1);
            view1.setText(item.price);
            
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
    public void navigateToEditForm() {
        Bundle args = new Bundle();

        args.putInt(Constants.ITEMPOS, 0);
        args.putParcelable(Constants.CONTENT, getItem());
        args.putInt(Constants.MODE, Constants.MODE_EDIT);

        Intent intent = new Intent(getActivity(), ProductsDSItemFormActivity.class);
        intent.putExtras(args);
        startActivityForResult(intent, Constants.MODE_EDIT);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.delete_menu, menu);

        MenuItem item = menu.findItem(R.id.action_delete);
        ColorUtils.tintIcon(item, R.color.textBarColor, getActivity());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_delete){
            ((DetailCrudPresenter<ProductsDSItem>) getPresenter()).deleteItem(getItem());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onShare() {
        ProductsDSItem item = getItem();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, (item.price != null ? item.price : "" ) + "\n" +
                    (item.description != null ? item.description : "" ));
        intent.putExtra(Intent.EXTRA_SUBJECT, item.name);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.share)), 1);
    }
}

