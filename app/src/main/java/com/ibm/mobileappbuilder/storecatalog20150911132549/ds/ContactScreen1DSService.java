
package com.ibm.mobileappbuilder.storecatalog20150911132549.ds;
import java.net.URL;
import com.ibm.mobileappbuilder.storecatalog20150911132549.R;
import ibmmobileappbuilder.ds.RestService;
import ibmmobileappbuilder.util.StringUtils;

/**
 * "ContactScreen1DSService" REST Service implementation
 */
public class ContactScreen1DSService extends RestService<ContactScreen1DSServiceRest>{

    public static ContactScreen1DSService getInstance(){
          return new ContactScreen1DSService();
    }

    private ContactScreen1DSService() {
        super(ContactScreen1DSServiceRest.class);

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

