package kursigoyang.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import kursigoyang.bakingapp.R;
import kursigoyang.bakingapp.model.Baking;

/**
 * Created by Fajar Rianda on 18/08/2017.
 */
public class BakingAdapter extends BaseRecycleAdapter<Baking, BakingAdapter.BakingViewHolder> {

  Context context;

  public BakingAdapter(Context context) {
    this.context = context;
  }

  @Override public BakingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new BakingViewHolder(
        LayoutInflater.from(context).inflate(R.layout.list_item_baking, parent, false));
  }

  @Override public void onBindViewHolder(BakingViewHolder holder, int position) {
    Baking baking = getItem(position);

    if (baking == null) {
      return;
    }

    holder.txtCake.setText(context.getString(R.string.cake_name, baking.getName()));
    holder.txtServing.setText(context.getString(R.string.servings, baking.getServings()));
    Glide.with(context)
        .load(baking.getImage())
        .placeholder(R.drawable.image_baking)
        .into(holder.imgContent);
  }

  public class BakingViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.txtCake) TextView txtCake;
    @Bind(R.id.imgContent) ImageView imgContent;
    @Bind(R.id.txtServing) TextView txtServing;

    public BakingViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    @OnClick(R.id.rootContainer) public void onListClick(){
      int clickedPosition = getAdapterPosition();
      mOnClickListener.onListItemClick(clickedPosition, listData.get(clickedPosition));
    }
  }
}
