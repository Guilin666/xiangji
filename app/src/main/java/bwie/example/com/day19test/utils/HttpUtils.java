package bwie.example.com.day19test.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class HttpUtils {

    private  BaseService baseService;
    private io.reactivex.Observable observable;
    private HttpUtilsListener listener;

    //初始化Retrofit
    public HttpUtils(){
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://www.zhaoapi.cn/")
                .build();
        //通过反射获取接口对象
        baseService = retrofit.create(BaseService.class);
    }



    //get方法
    public HttpUtils get(String url, Map<String,String> map){

        if (map == null) {
            map=new HashMap<>();
        }
        //获取被观察者
        observable = baseService.get(url, map);
        setObservable();
        return this;

    }


    //post方法
    public HttpUtils post(String url,Map<String,String> map){
        if (map == null) {
            map=new HashMap<>();
        }
        //获取观察者
        observable = baseService.post(url, map);
        setObservable();
        return this;
    }


    //设置被观察者
    private void setObservable() {
        //设置调度器
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }



    private Observer observer=new Observer<ResponseBody>(){

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(ResponseBody responseBody) {
            try {
                String string = responseBody.string();
                listener.success(string);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(Throwable e) {
            String message = e.getMessage();
            listener.fail(message);
        }

        @Override
        public void onComplete() {

        }
    };

    public void setHttpUtilsListener(HttpUtilsListener listener){
        this.listener=listener;
    }
    public interface HttpUtilsListener{
        void success(String  data);
        void fail(String error);
    }



}
