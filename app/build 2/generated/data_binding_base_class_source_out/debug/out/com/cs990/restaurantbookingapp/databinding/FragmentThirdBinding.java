// Generated by view binder compiler. Do not edit!
package com.cs990.restaurantbookingapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.cs990.restaurantbookingapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentThirdBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final ImageView profileIcon;

  @NonNull
  public final RecyclerView profileListView;

  @NonNull
  public final TextView usernameText;

  private FragmentThirdBinding(@NonNull RelativeLayout rootView, @NonNull ImageView profileIcon,
      @NonNull RecyclerView profileListView, @NonNull TextView usernameText) {
    this.rootView = rootView;
    this.profileIcon = profileIcon;
    this.profileListView = profileListView;
    this.usernameText = usernameText;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentThirdBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentThirdBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_third, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentThirdBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.profile_icon;
      ImageView profileIcon = rootView.findViewById(id);
      if (profileIcon == null) {
        break missingId;
      }

      id = R.id.profile_list_view;
      RecyclerView profileListView = rootView.findViewById(id);
      if (profileListView == null) {
        break missingId;
      }

      id = R.id.usernameText;
      TextView usernameText = rootView.findViewById(id);
      if (usernameText == null) {
        break missingId;
      }

      return new FragmentThirdBinding((RelativeLayout) rootView, profileIcon, profileListView,
          usernameText);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}