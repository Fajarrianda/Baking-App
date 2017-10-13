package kursigoyang.bakingapp;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Fajar Rianda on 17/08/2017.
 */
public class AppClient {
  public static <S> S createService(Class<S> serviceClass) {
    return retrofit().create(serviceClass);
  }

  private static OkHttpClient client() {
    OkHttpClient.Builder clientBuilder =
        new OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(
                new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
    return clientBuilder.build();
  }

  public static Retrofit retrofit() {
    Retrofit.Builder retrofitBuilder =
        new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(client());
    return retrofitBuilder.baseUrl(URLCons.URL_BASE).build();
  }
}
