package kursigoyang.bakingapp;

import java.util.List;
import kursigoyang.bakingapp.model.Baking;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Fajar Rianda on 18/08/2017.
 */
public interface ApiService {
  @GET(URLCons.URL_BAKING) Observable<List<Baking>> getBaking();
}
