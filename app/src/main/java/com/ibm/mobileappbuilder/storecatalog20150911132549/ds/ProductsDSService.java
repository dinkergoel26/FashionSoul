
package com.ibm.mobileappbuilder.storecatalog20150911132549.ds;
import java.net.URL;
import com.ibm.mobileappbuilder.storecatalog20150911132549.R;
import ibmmobileappbuilder.ds.RestService;
import ibmmobileappbuilder.util.StringUtils;

/**
 * "ProductsDSService" REST Service implementation
 */
public class ProductsDSService extends RestService<ProductsDSServiceRest>{

    public static ProductsDSService getInstance(){
          return new ProductsDSService();
    }

    private ProductsDSService() {
        super(ProductsDSServiceRest.class);

    }

    @Override
    public String getServerUrl() {
        return "https://ibm-pods.buildup.io";
    }

    @Override
    protected String getApiKey() {
        return "3FUFSWHX";
    }

    @Override
    public URL getImageUrl(String path){
        return StringUtils.parseUrl("https://ibm-pods.buildup.io/app/57ee6d2311cb3e03009e91e9",
                path,
                "apikey=3FUFSWHX");
    }

}

