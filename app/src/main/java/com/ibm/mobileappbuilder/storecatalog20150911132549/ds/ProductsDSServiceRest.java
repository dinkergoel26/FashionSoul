
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

public interface ProductsDSServiceRest{

	@GET("/app/57ee6d2311cb3e03009e91e9/r/productsDS")
	void queryProductsDSItem(
		@Query("skip") String skip,
		@Query("limit") String limit,
		@Query("conditions") String conditions,
		@Query("sort") String sort,
		@Query("select") String select,
		@Query("populate") String populate,
		Callback<List<ProductsDSItem>> cb);

	@GET("/app/57ee6d2311cb3e03009e91e9/r/productsDS/{id}")
	void getProductsDSItemById(@Path("id") String id, Callback<ProductsDSItem> cb);

	@DELETE("/app/57ee6d2311cb3e03009e91e9/r/productsDS/{id}")
  void deleteProductsDSItemById(@Path("id") String id, Callback<ProductsDSItem> cb);

  @POST("/app/57ee6d2311cb3e03009e91e9/r/productsDS/deleteByIds")
  void deleteByIds(@Body List<String> ids, Callback<List<ProductsDSItem>> cb);

  @POST("/app/57ee6d2311cb3e03009e91e9/r/productsDS")
  void createProductsDSItem(@Body ProductsDSItem item, Callback<ProductsDSItem> cb);

  @PUT("/app/57ee6d2311cb3e03009e91e9/r/productsDS/{id}")
  void updateProductsDSItem(@Path("id") String id, @Body ProductsDSItem item, Callback<ProductsDSItem> cb);

  @GET("/app/57ee6d2311cb3e03009e91e9/r/productsDS")
  void distinct(
        @Query("distinct") String colName,
        @Query("conditions") String conditions,
        Callback<List<String>> cb);
    
    @Multipart
    @POST("/app/57ee6d2311cb3e03009e91e9/r/productsDS")
    void createProductsDSItem(
        @Part("data") ProductsDSItem item,
        @Part("picture") TypedByteArray picture,
    @Part("thumbnail") TypedByteArray thumbnail,
        Callback<ProductsDSItem> cb);
    
    @Multipart
    @PUT("/app/57ee6d2311cb3e03009e91e9/r/productsDS/{id}")
    void updateProductsDSItem(
        @Path("id") String id,
        @Part("data") ProductsDSItem item,
        @Part("picture") TypedByteArray picture,
    @Part("thumbnail") TypedByteArray thumbnail,
        Callback<ProductsDSItem> cb);
}

