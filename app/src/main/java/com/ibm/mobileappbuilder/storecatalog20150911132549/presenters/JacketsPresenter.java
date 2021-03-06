
package com.ibm.mobileappbuilder.storecatalog20150911132549.presenters;

import com.ibm.mobileappbuilder.storecatalog20150911132549.R;
import com.ibm.mobileappbuilder.storecatalog20150911132549.ds.ProductsDSItem;

import java.util.List;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.ListCrudPresenter;
import ibmmobileappbuilder.mvp.view.CrudListView;

public class JacketsPresenter extends BasePresenter implements ListCrudPresenter<ProductsDSItem>,
      Datasource.Listener<ProductsDSItem>{

    private final CrudDatasource<ProductsDSItem> crudDatasource;
    private final CrudListView<ProductsDSItem> view;

    public JacketsPresenter(CrudDatasource<ProductsDSItem> crudDatasource,
                                         CrudListView<ProductsDSItem> view) {
       this.crudDatasource = crudDatasource;
       this.view = view;
    }

    @Override
    public void deleteItem(ProductsDSItem item) {
        crudDatasource.deleteItem(item, this);
    }

    @Override
    public void deleteItems(List<ProductsDSItem> items) {
        crudDatasource.deleteItems(items, this);
    }

    @Override
    public void addForm() {
        view.showAdd();
    }

    @Override
    public void editForm(ProductsDSItem item, int position) {
        view.showEdit(item, position);
    }

    @Override
    public void detail(ProductsDSItem item, int position) {
        view.showDetail(item, position);
    }

    @Override
    public void onSuccess(ProductsDSItem item) {
                view.showMessage(R.string.items_deleted);
        view.refresh();
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic);
    }

}

