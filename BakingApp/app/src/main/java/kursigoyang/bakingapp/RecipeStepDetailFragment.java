package kursigoyang.bakingapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import kursigoyang.bakingapp.model.Step;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeStepDetailFragment extends Fragment {

  final static String KEY_POSITION = "position";
  @Bind(R.id.videoContent) SimpleExoPlayerView videoContent;
  @Bind(R.id.imgThumbnail) ImageView imgThumbnail;
  @Bind(R.id.txtInstruction) TextView txtInstruction;
  SimpleExoPlayer exoPlayer;
  Context context;
  int currentPosition = -1;

  public RecipeStepDetailFragment() {
    // Required empty public constructor
  }

  public static RecipeStepDetailFragment newInstance(Step step) {
    Bundle bundle = new Bundle();
    bundle.putParcelable(Step.class.getSimpleName(), step);
    RecipeStepDetailFragment recipeStepDetailFragment = new RecipeStepDetailFragment();
    recipeStepDetailFragment.setArguments(bundle);
    return recipeStepDetailFragment;
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    if (savedInstanceState != null) {
      currentPosition = savedInstanceState.getInt(KEY_POSITION);
    }
    View view = inflater.inflate(R.layout.fragment_recipe_step_detail, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    this.context = context;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  private void init() {
    Step step = getArguments().getParcelable(Step.class.getSimpleName());
    if (step != null) {
      setupVideo(step);
      txtInstruction.setText(step.getDescription());
      Glide.with(context)
          .load(step.getThumbnailURL())
          .placeholder(R.drawable.image_baking)
          .into(imgThumbnail);
    }
  }

  private void setupVideo(Step step) {
    if (step.getVideoURL().isEmpty()) {
      imgThumbnail.setVisibility(View.GONE);
      videoContent.setVisibility(View.GONE);
    }

    TrackSelection.Factory videoTrackSelectionFactory =
        new AdaptiveTrackSelection.Factory(new DefaultBandwidthMeter());

    exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(),
        new DefaultTrackSelector(videoTrackSelectionFactory));
    videoContent.setPlayer(exoPlayer);
    videoContent.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);

    DefaultExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
    DefaultDataSourceFactory dataSourceFactory =
        new DefaultDataSourceFactory(context, Util.getUserAgent(context, "mediaPlayerSample"));

    MediaSource mediaSource =
        new ExtractorMediaSource(Uri.parse(step.getVideoURL()), dataSourceFactory,
            extractorsFactory, null, null);
    exoPlayer.prepare(mediaSource);
  }

  @Override public void onStart() {
    super.onStart();
    init();
  }

  @Override public void onResume() {
    super.onResume();
    if (exoPlayer == null) {
      init();
    }
  }

  @Override public void onPause() {
    super.onPause();
    exoPlayer.setPlayWhenReady(false);
    exoPlayer.release();
  }

  @Override public void onStop() {
    super.onStop();
    exoPlayer.setPlayWhenReady(false);
    exoPlayer.release();
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);

    // Save the current description selection in case we need to recreate the fragment
    outState.putInt(KEY_POSITION, currentPosition);
  }

  @OnClick(R.id.imgThumbnail) public void onThumbnailClick() {
    if (exoPlayer != null) {
      imgThumbnail.setVisibility(View.GONE);
      exoPlayer.setPlayWhenReady(true);
    }
  }
}
