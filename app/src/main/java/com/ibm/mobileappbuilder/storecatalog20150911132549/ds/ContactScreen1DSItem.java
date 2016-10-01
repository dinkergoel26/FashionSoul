
package com.ibm.mobileappbuilder.storecatalog20150911132549.ds;
import android.graphics.Bitmap;
import android.net.Uri;

import ibmmobileappbuilder.mvp.model.IdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class ContactScreen1DSItem implements Parcelable, IdentifiableBean {

    @SerializedName("address") public String address;
    @SerializedName("phone") public String phone;
    @SerializedName("picture") public String picture;
    @SerializedName("email") public String email;
    @SerializedName("id") public String id;
    @SerializedName("pictureUri") public transient Uri pictureUri;

    @Override
    public String getIdentifiableId() {
      return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(address);
        dest.writeString(phone);
        dest.writeString(picture);
        dest.writeString(email);
        dest.writeString(id);
    }

    public static final Creator<ContactScreen1DSItem> CREATOR = new Creator<ContactScreen1DSItem>() {
        @Override
        public ContactScreen1DSItem createFromParcel(Parcel in) {
            ContactScreen1DSItem item = new ContactScreen1DSItem();

            item.address = in.readString();
            item.phone = in.readString();
            item.picture = in.readString();
            item.email = in.readString();
            item.id = in.readString();
            return item;
        }

        @Override
        public ContactScreen1DSItem[] newArray(int size) {
            return new ContactScreen1DSItem[size];
        }
    };

}


