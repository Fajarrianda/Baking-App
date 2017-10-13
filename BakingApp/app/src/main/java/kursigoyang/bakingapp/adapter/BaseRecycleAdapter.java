package kursigoyang.bakingapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import kursigoyang.bakingapp.ListItemClickListener;

/**
 * Created by Fajar Rianda on 18/08/2017.
 */
public class BaseRecycleAdapter<T, VH extends RecyclerView.ViewHolder>
    extends RecyclerView.Adapter<VH> {

  List<T> listData = new ArrayList<>();
  protected ListItemClickListener<T> mOnClickListener;

  public BaseRecycleAdapter() {
  }

  @Override public VH onCreateViewHolder(ViewGroup parent, int viewType) {
    return null;
  }

  @Override public void onBindViewHolder(VH holder, int position) {
  }

  @Override public int getItemCount() {
    return listData.size();
  }

  public void pushData(List<T> data) {
    listData.clear();
    listData.addAll(data);
    notifyDataSetChanged();
  }

  public T getItem(int position) {
    return listData.get(position);
  }

  public void setOnListItemClickListener(ListItemClickListener<T> listItemClickListener) {
    mOnClickListener = listItemClickListener;
  }
}
