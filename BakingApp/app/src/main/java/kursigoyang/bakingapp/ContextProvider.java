package kursigoyang.bakingapp;

import android.content.Context;

/**
 * Created by Fajar Rianda on 18/09/2017.
 */

public class ContextProvider {

  private static ContextProvider mInstance;
  private Context mContext;

  private static ContextProvider getInstance(){
    if (mInstance == null){
      mInstance = new ContextProvider();
    }
    return mInstance;
  }

  private void init(Context context){
    this.mContext = context;
  }
  private Context getContext(){
    if (this.mContext == null){
      throw new IllegalStateException("Must call init first before getContext()");
    } else {
      return this.mContext;
    }
  }

  public static void install(Context context){
    getInstance().init(context);
  }
  public static Context get(){
    return getInstance().getContext();
  }
}