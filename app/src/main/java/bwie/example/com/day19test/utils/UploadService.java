package bwie.example.com.day19test.utils;


import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

//上传头像接口
public interface UploadService {
    @Multipart
    @POST("/file/upload")
    Observable<ResponseBody> upload(@Query("uid") String uid, @Part MultipartBody.Part part);
}
