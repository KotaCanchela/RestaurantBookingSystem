// Generated by view binder compiler. Do not edit!
package com.cs990.restaurantbookingapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.cs990.restaurantbookingapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class CardRestaurantBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final CardView cvCardView;

  @NonNull
  public final ImageView ivRestaurantImage;

  @NonNull
  public final RatingBar rbRatingBar;

  @NonNull
  public final RelativeLayout rlWrapper;

  @NonNull
  public final TextView tvDistance;

  @NonNull
  public final TextView tvRestaurantName;

  private CardRestaurantBinding(@NonNull CardView rootView, @NonNull CardView cvCardView,
      @NonNull ImageView ivRestaurantImage, @NonNull RatingBar rbRatingBar,
      @NonNull RelativeLayout rlWrapper, @NonNull TextView tvDistance,
      @NonNull TextView tvRestaurantName) {
    this.rootView = rootView;
    this.cvCardView = cvCardView;
    this.ivRestaurantImage = ivRestaurantImage;
    this.rbRatingBar = rbRatingBar;
    this.rlWrapper = rlWrapper;
    this.tvDistance = tvDistance;
    this.tvRestaurantName = tvRestaurantName;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static CardRestaurantBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static CardRestaurantBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.card_restaurant, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static CardRestaurantBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      CardView cvCardView = (CardView) rootView;

      id = R.id.iv_restaurantImage;
      ImageView ivRestaurantImage = rootView.findViewById(id);
      if (ivRestaurantImage == null) {
        break missingId;
      }

      id = R.id.rb_ratingBar;
      RatingBar rbRatingBar = rootView.findViewById(id);
      if (rbRatingBar == null) {
        break missingId;
      }

      id = R.id.rl_wrapper;
      RelativeLayout rlWrapper = rootView.findViewById(id);
      if (rlWrapper == null) {
        break missingId;
      }

      id = R.id.tv_distance;
      TextView tvDistance = rootView.findViewById(id);
      if (tvDistance == null) {
        break missingId;
      }

      id = R.id.tv_restaurantName;
      TextView tvRestaurantName = rootView.findViewById(id);
      if (tvRestaurantName == null) {
        break missingId;
      }

      return new CardRestaurantBinding((CardView) rootView, cvCardView, ivRestaurantImage,
          rbRatingBar, rlWrapper, tvDistance, tvRestaurantName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
