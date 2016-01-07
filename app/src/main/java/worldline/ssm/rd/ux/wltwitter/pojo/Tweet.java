package worldline.ssm.rd.ux.wltwitter.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Tweet implements Parcelable {

	private int data;
	@SerializedName("created_at")
	public String dateCreated;

	@SerializedName("id")
	public String id;

	@SerializedName("text")
	public String text;

	@SerializedName("in_reply_to_status_id")
	public String inReplyToStatusId;

	@SerializedName("in_reply_to_user_id")
	public String inReplyToUserId;

	@SerializedName("in_reply_to_screen_name")
	public String inReplyToScreenName;

	@SerializedName("user")
	public TwitterUser user;

	@Override
	public String toString() {
		return text;
	}

	public Tweet(){}


	protected Tweet(Parcel in) {
		data = in.readInt();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(data);
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<Tweet> CREATOR = new Parcelable.Creator<Tweet>() {
		@Override
		public Tweet createFromParcel(Parcel in) {
			return new Tweet(in);
		}

		@Override
		public Tweet[] newArray(int size) {
			return new Tweet[size];
		}
	};
}