

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
 * "ContactScreen1DS" data source. (e37eb8dc-6eb2-4635-8592-5eb9696050e3)
 */
public class ContactScreen1DS extends AppNowDatasource<ContactScreen1DSItem>{

    // default page size
    private static final int PAGE_SIZE = 20;

    private ContactScreen1DSService service;

    public static ContactScreen1DS getInstance(SearchOptions searchOptions){
        return new ContactScreen1DS(searchOptions);
    }

    private ContactScreen1DS(SearchOptions searchOptions) {
        super(searchOptions);
        this.service = ContactScreen1DSService.getInstance();
    }

    @Override
    public void getItem(String id, final Listener<ContactScreen1DSItem> listener) {
        if ("0".equals(id)) {
                        getItems(new Listener<List<ContactScreen1DSItem>>() {
                @Override
                public void onSuccess(List<ContactScreen1DSItem> items) {
                    if(items != null && items.size() > 0) {
                        listener.onSuccess(items.get(0));
                    } else {
                        listener.onSuccess(new ContactScreen1DSItem());
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure(e);
                }
            });
        } else {
                      service.getServiceProxy().getContactScreen1DSItemById(id, new Callback<ContactScreen1DSItem>() {
                @Override
                public void success(ContactScreen1DSItem result, Response response) {
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
    public void getItems(final Listener<List<ContactScreen1DSItem>> listener) {
        getItems(0, listener);
    }

    @Override
    public void getItems(int pagenum, final Listener<List<ContactScreen1DSItem>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
        int skipNum = pagenum * PAGE_SIZE;
        String skip = skipNum == 0 ? null : String.valueOf(skipNum);
        String limit = PAGE_SIZE == 0 ? null: String.valueOf(PAGE_SIZE);
        String sort = getSort(searchOptions);
                service.getServiceProxy().queryContactScreen1DSItem(
                skip,
                limit,
                conditions,
                sort,
                null,
                null,
                new Callback<List<ContactScreen1DSItem>>() {
            @Override
            public void success(List<ContactScreen1DSItem> result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    private String[] getSearchableFields() {
        return new String[]{"address", "phone", "email"};
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
    public void create(ContactScreen1DSItem item, Listener<ContactScreen1DSItem> listener) {
                    
        if(item.pictureUri != null){
            service.getServiceProxy().createContactScreen1DSItem(item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().createContactScreen1DSItem(item, callbackFor(listener));
        
    }

    private Callback<ContactScreen1DSItem> callbackFor(final Listener<ContactScreen1DSItem> listener) {
      return new Callback<ContactScreen1DSItem>() {
          @Override
          public void success(ContactScreen1DSItem item, Response response) {
                            listener.onSuccess(item);
          }

          @Override
          public void failure(RetrofitError error) {
                            listener.onFailure(error);
          }
      };
    }

    @Override
    public void updateItem(ContactScreen1DSItem item, Listener<ContactScreen1DSItem> listener) {
                    
        if(item.pictureUri != null){
            service.getServiceProxy().updateContactScreen1DSItem(item.getIdentifiableId(),
                item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().updateContactScreen1DSItem(item.getIdentifiableId(), item, callbackFor(listener));
        
    }

    @Override
    public void deleteItem(ContactScreen1DSItem item, final Listener<ContactScreen1DSItem> listener) {
                service.getServiceProxy().deleteContactScreen1DSItemById(item.getIdentifiableId(), new Callback<ContactScreen1DSItem>() {
            @Override
            public void success(ContactScreen1DSItem result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    @Override
    public void deleteItems(List<ContactScreen1DSItem> items, final Listener<ContactScreen1DSItem> listener) {
                service.getServiceProxy().deleteByIds(collectIds(items), new Callback<List<ContactScreen1DSItem>>() {
            @Override
            public void success(List<ContactScreen1DSItem> item, Response response) {
                                listener.onSuccess(null);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    protected List<String> collectIds(List<ContactScreen1DSItem> items){
        List<String> ids = new ArrayList<>();
        for(ContactScreen1DSItem item: items){
            ids.add(item.getIdentifiableId());
        }
        return ids;
    }

}

