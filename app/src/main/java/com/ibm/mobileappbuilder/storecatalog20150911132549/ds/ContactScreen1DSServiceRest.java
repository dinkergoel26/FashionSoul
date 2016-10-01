
package com.ibm.mobileappbuilder.storecatalog20150911132549.ds;
import java.util.List;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.POST;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Path;
import retrofit.http.PUT;
import retrofit.mime.TypedByteArray;
import retrofit.http.Part;
import retrofit.http.Multipart;

public interface ContactScreen1DSServiceRest{

	@GET("/app/57ee6d2311cb3e03009e91e9/r/contactScreen1DS")
	void queryContactScreen1DSItem(
		@Query("skip") String skip,
		@Query("limit") String limit,
		@Query("conditions") String conditions,
		@Query("sort") String sort,
		@Query("select") String select,
		@Query("populate") String populate,
		Callback<List<ContactScreen1DSItem>> cb);

	@GET("/app/57ee6d2311cb3e03009e91e9/r/contactScreen1DS/{id}")
	void getContactScreen1DSItemById(@Path("id") String id, Callback<ContactScreen1DSItem> cb);

	@DELETE("/app/57ee6d2311cb3e03009e91e9/r/contactScreen1DS/{id}")
  void deleteContactScreen1DSItemById(@Path("id") String id, Callback<ContactScreen1DSItem> cb);

  @POST("/app/57ee6d2311cb3e03009e91e9/r/contactScreen1DS/deleteByIds")
  void deleteByIds(@Body List<String> ids, Callback<List<ContactScreen1DSItem>> cb);

  @POST("/app/57ee6d2311cb3e03009e91e9/r/contactScreen1DS")
  void createContactScreen1DSItem(@Body ContactScreen1DSItem item, Callback<ContactScreen1DSItem> cb);

  @PUT("/app/57ee6d2311cb3e03009e91e9/r/contactScreen1DS/{id}")
  void updateContactScreen1DSItem(@Path("id") String id, @Body ContactScreen1DSItem item, Callback<ContactScreen1DSItem> cb);

  @GET("/app/57ee6d2311cb3e03009e91e9/r/contactScreen1DS")
  void distinct(
        @Query("distinct") String colName,
        @Query("conditions") String conditions,
        Callback<List<String>> cb);
    
    @Multipart
    @POST("/app/57ee6d2311cb3e03009e91e9/r/contactScreen1DS")
    void createContactScreen1DSItem(
        @Part("data") ContactScreen1DSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<ContactScreen1DSItem> cb);
    
    @Multipart
    @PUT("/app/57ee6d2311cb3e03009e91e9/r/contactScreen1DS/{id}")
    void updateContactScreen1DSItem(
        @Path("id") String id,
        @Part("data") ContactScreen1DSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<ContactScreen1DSItem> cb);
}

