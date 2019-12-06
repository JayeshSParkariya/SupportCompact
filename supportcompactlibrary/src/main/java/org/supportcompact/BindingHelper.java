package org.supportcompact;

import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

import java.io.File;

public class BindingHelper {

    @BindingAdapter("imgUrl")
    public static void setIndicator(ImageView img, File mFile) {
        if (mFile != null) {
            Glide.with(img).load(mFile).into(img);
        }
    }

    @BindingAdapter("imgUrl")
    public static void setIndicator(ImageView img, @DrawableRes int resIcon) {
        Glide.with(img).load(resIcon).into(img);
    }

    @BindingAdapter("imgUrl")
    public static void loadImage(ImageView img, String url) {
        Glide.with(img).load(url).into(img);
        /*if (TextUtils.isEmpty(url))
            img.setImageResource(R.drawable.ic_profile_default);
        else {
            Glide.with(img).load(url).placeholder(R.drawable.ic_profile_default).into(img);
        }*/

    }
}
