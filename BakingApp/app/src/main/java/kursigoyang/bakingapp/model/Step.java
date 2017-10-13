package kursigoyang.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Fajar Rianda on 18/08/2017.
 */
public class Step implements Parcelable {

  @SerializedName("id") private int id;
  @SerializedName("shortDescription") private String shortDescription;
  @SerializedName("description") private String description;
  @SerializedName("videoURL") private String videoURL;
  @SerializedName("thumbnailURL") private String thumbnailURL;

  protected Step(Parcel in) {
    id = in.readInt();
    shortDescription = in.readString();
    description = in.readString();
    videoURL = in.readString();
    thumbnailURL = in.readString();
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeString(shortDescription);
    dest.writeString(description);
    dest.writeString(videoURL);
    dest.writeString(thumbnailURL);
  }

  @Override public int describeContents() {
    return 0;
  }

  public static final Creator<Step> CREATOR = new Creator<Step>() {
    @Override public Step createFromParcel(Parcel in) {
      return new Step(in);
    }

    @Override public Step[] newArray(int size) {
      return new Step[size];
    }
  };

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }

  public String getThumbnailURL() {
    return thumbnailURL;
  }

  public void setThumbnailURL(String thumbnailURL) {
    this.thumbnailURL = thumbnailURL;
  }

  public String getVideoURL() {
    return videoURL;
  }

  public void setVideoURL(String videoURL) {
    this.videoURL = videoURL;
  }
}
