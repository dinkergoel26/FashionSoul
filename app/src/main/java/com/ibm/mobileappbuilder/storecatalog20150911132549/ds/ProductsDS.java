

package com.ibm.mobileappbuilder.storecatalog20150911132549.ds;

import android.content.Context;

import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.restds.AppNowDatasource;
import ibmmobileappbuilder.util.StringUtils;
import ibmmobileappbuilder.ds.restds.TypedByteArrayUtils;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * "ProductsDS" data source. (e37eb8dc-6eb2-4635-8592-5eb9696050e3)
 */
public class ProductsDS extends AppNowDatasource<ProductsDSItem>{

    // default page size
    private static final int PAGE_SIZE = 20;

    private ProductsDSService service;

    public static ProductsDS getInstance(SearchOptions searchOptions){
        return new ProductsDS(searchOptions);
    }

    private ProductsDS(SearchOptions searchOptions) {
        super(searchOptions);
        this.service = ProductsDSService.getInstance();
    }

    @Override
    public void getItem(String id, final Listener<ProductsDSItem> listener) {
        if ("0".equals(id)) {
                        getItems(new Listener<List<ProductsDSItem>>() {
                @Override
                public void onSuccess(List<ProductsDSItem> items) {
                    if(items != null && items.size() > 0) {
                        listener.onSuccess(items.get(0));
                    } else {
                        listener.onSuccess(new ProductsDSItem());
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure(e);
                }
            });
        } else {
                      service.getServiceProxy().getProductsDSItemById(id, new Callback<ProductsDSItem>() {
                @Override
                public void success(ProductsDSItem result, Response response) {
                                        listener.onSuccess(result);
                }

                @Override
                public void failure(RetrofitError error) {
                                        listener.onFailure(error);
                }
            });
        }
    }

    @Override
    public void getItems(final Listener<List<ProductsDSItem>> listener) {
        getItems(0, listener);
    }

    @Override
    public void getItems(int pagenum, final Listener<List<ProductsDSItem>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
        int skipNum = pagenum * PAGE_SIZE;
        String skip = skipNum == 0 ? null : String.valueOf(skipNum);
        String limit = PAGE_SIZE == 0 ? null: String.valueOf(PAGE_SIZE);
        String sort = getSort(searchOptions);
                service.getServiceProxy().queryProductsDSItem(
                skip,
                limit,
                conditions,
                sort,
                null,
                null,
                new Callback<List<ProductsDSItem>>() {
            @Override
            public void success(List<ProductsDSItem> result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    private String[] getSearchableFields() {
        return new String[]{"name", "description", "category", "price", "rating"};
    }

    // Pagination

    @Override
    public int getPageSize(){
        return PAGE_SIZE;
    }

    @Override
    public void getUniqueValuesFor(String searchStr, final Listener<List<String>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
                service.getServiceProxy().distinct(searchStr, conditions, new Callback<List<String>>() {
             @Override
             public void success(List<String> result, Response response) {
                                  result.removeAll(Collections.<String>singleton(null));
                 listener.onSuccess(result);
             }

             @Override
             public void failure(RetrofitError error) {
                                  listener.onFailure(error);
             }
        });
    }

    @Override
    public URL getImageUrl(String path) {
        return service.getImageUrl(path);
    }

    @Override
    public void create(ProductsDSItem item, Listener<ProductsDSItem> listener) {
                    
        if(item.pictureUri != null ||
        item.thumbnailUri != null){
            service.getServiceProxy().createProductsDSItem(item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                TypedByteArrayUtils.fromUri(item.thumbnailUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().createProductsDSItem(item, callbackFor(listener));
        
    }

    private Callback<ProductsDSItem> callbackFor(final Listener<ProductsDSItem> listener) {
      return new Callback<ProductsDSItem>() {
          @Override
          public void success(ProductsDSItem item, Response response) {
                            listener.onSuccess(item);
          }

          @Override
          public void failure(RetrofitError error) {
                            listener.onFailure(error);
          }
      };
    }

    @Override
    public void updateItem(ProductsDSItem item, Listener<ProductsDSItem> listener) {
                    
        if(item.pictureUri != null ||
        item.thumbnailUri != null){
            service.getServiceProxy().updateProductsDSItem(item.getIdentifiableId(),
                item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                TypedByteArrayUtils.fromUri(item.thumbnailUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().updateProductsDSItem(item.getIdentifiableId(), item, callbackFor(listener));
        
    }

    @Override
    public void deleteItem(ProductsDSItem item, final Listener<ProductsDSItem> listener) {
                service.getServiceProxy().deleteProductsDSItemById(item.getIdentifiableId(), new Callback<ProductsDSItem>() {
            @Override
            public void success(ProductsDSItem result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    @Override
    public void deleteItems(List<ProductsDSItem> items, final Listener<ProductsDSItem> listener) {
                service.getServiceProxy().deleteByIds(collectIds(items), new Callback<List<ProductsDSItem>>() {
            @Override
            public void success(List<ProductsDSItem> item, Response response) {
                                listener.onSuccess(null);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    protected List<String> collectIds(List<ProductsDSItem> items){
        List<String> ids = new ArrayList<>();
        for(ProductsDSItem item: items){
            ids.add(item.getIdentifiableId());
        }
        return ids;
    }

}

