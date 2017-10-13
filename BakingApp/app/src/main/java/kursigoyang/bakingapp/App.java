package kursigoyang.bakingapp;

import android.app.Application;

/**
 * Created by Fajar Rianda on 18/09/2017.
 */

public class App extends Application {

  @Override public void onCreate() {
    super.onCreate();
    ContextProvider.install(this);
  }
}
