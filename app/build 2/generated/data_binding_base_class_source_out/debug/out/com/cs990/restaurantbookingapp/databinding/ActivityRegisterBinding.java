// Generated by view binder compiler. Do not edit!
package com.cs990.restaurantbookingapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.cs990.restaurantbookingapp.R;
import com.google.android.material.textfield.TextInputEditText;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRegisterBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextInputEditText enterEmail;

  @NonNull
  public final TextInputEditText enterPassword;

  @NonNull
  public final TextInputEditText enterUsername;

  @NonNull
  public final TextView loginLabel;

  @NonNull
  public final TextView loginText;

  @NonNull
  public final Button registerButton;

  private ActivityRegisterBinding(@NonNull LinearLayout rootView,
      @NonNull TextInputEditText enterEmail, @NonNull TextInputEditText enterPassword,
      @NonNull TextInputEditText enterUsername, @NonNull TextView loginLabel,
      @NonNull TextView loginText, @NonNull Button registerButton) {
    this.rootView = rootView;
    this.enterEmail = enterEmail;
    this.enterPassword = enterPassword;
    this.enterUsername = enterUsername;
    this.loginLabel = loginLabel;
    this.loginText = loginText;
    this.registerButton = registerButton;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.enterEmail;
      TextInputEditText enterEmail = rootView.findViewById(id);
      if (enterEmail == null) {
        break missingId;
      }

      id = R.id.enterPassword;
      TextInputEditText enterPassword = rootView.findViewById(id);
      if (enterPassword == null) {
        break missingId;
      }

      id = R.id.enterUsername;
      TextInputEditText enterUsername = rootView.findViewById(id);
      if (enterUsername == null) {
        break missingId;
      }

      id = R.id.loginLabel;
      TextView loginLabel = rootView.findViewById(id);
      if (loginLabel == null) {
        break missingId;
      }

      id = R.id.loginText;
      TextView loginText = rootView.findViewById(id);
      if (loginText == null) {
        break missingId;
      }

      id = R.id.registerButton;
      Button registerButton = rootView.findViewById(id);
      if (registerButton == null) {
        break missingId;
      }

      return new ActivityRegisterBinding((LinearLayout) rootView, enterEmail, enterPassword,
          enterUsername, loginLabel, loginText, registerButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}