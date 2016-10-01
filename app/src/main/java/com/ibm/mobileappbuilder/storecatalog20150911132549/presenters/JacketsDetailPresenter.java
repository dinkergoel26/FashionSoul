
package com.ibm.mobileappbuilder.storecatalog20150911132549.presenters;

import com.ibm.mobileappbuilder.storecatalog20150911132549.R;
import com.ibm.mobileappbuilder.storecatalog20150911132549.ds.ProductsDSItem;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.DetailCrudPresenter;
import ibmmobileappbuilder.mvp.view.DetailView;

public class JacketsDetailPresenter extends BasePresenter implements DetailCrudPresenter<ProductsDSItem>,
      Datasource.Listener<ProductsDSItem> {

    private final CrudDatasource<ProductsDSItem> datasource;
    private final DetailView view;

    public JacketsDetailPresenter(CrudDatasource<ProductsDSItem> datasource, DetailView view){
        this.datasource = datasource;
        this.view = view;
    }

    @Override
    public void deleteItem(ProductsDSItem item) {
        datasource.deleteItem(item, this);
    }

    @Override
    public void editForm(ProductsDSItem item) {
        view.navigateToEditForm();
    }

    @Override
    public void onSuccess(ProductsDSItem item) {
                view.showMessage(R.string.item_deleted, true);
        view.close(true);
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic, true);
    }
}

