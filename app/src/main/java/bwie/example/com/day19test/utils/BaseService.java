package bwie.example.com.day19test.utils;



import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface BaseService {
    //获取被观察者
    @GET
    Observable<ResponseBody> get (@Url String url, @QueryMap Map<String,String> map);

    @POST
    Observable <ResponseBody> post(@Url String url,@QueryMap Map<String,String> map);
}
