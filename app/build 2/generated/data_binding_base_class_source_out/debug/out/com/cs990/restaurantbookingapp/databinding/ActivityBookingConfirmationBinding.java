// Generated by view binder compiler. Do not edit!
package com.cs990.restaurantbookingapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.cs990.restaurantbookingapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityBookingConfirmationBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Button CalendarButton;

  @NonNull
  public final TextView TimeAndDate;

  @NonNull
  public final TextView addText;

  @NonNull
  public final TextView addText2;

  @NonNull
  public final TextView addText3;

  @NonNull
  public final TextView addText4;

  @NonNull
  public final TextView addText5;

  @NonNull
  public final Button btnHome;

  @NonNull
  public final Button cancelButton;

  @NonNull
  public final TextView confirmSent;

  @NonNull
  public final TextView confirmSent2;

  @NonNull
  public final Button editButton;

  @NonNull
  public final TextView restName;

  private ActivityBookingConfirmationBinding(@NonNull RelativeLayout rootView,
      @NonNull Button CalendarButton, @NonNull TextView TimeAndDate, @NonNull TextView addText,
      @NonNull TextView addText2, @NonNull TextView addText3, @NonNull TextView addText4,
      @NonNull TextView addText5, @NonNull Button btnHome, @NonNull Button cancelButton,
      @NonNull TextView confirmSent, @NonNull TextView confirmSent2, @NonNull Button editButton,
      @NonNull TextView restName) {
    this.rootView = rootView;
    this.CalendarButton = CalendarButton;
    this.TimeAndDate = TimeAndDate;
    this.addText = addText;
    this.addText2 = addText2;
    this.addText3 = addText3;
    this.addText4 = addText4;
    this.addText5 = addText5;
    this.btnHome = btnHome;
    this.cancelButton = cancelButton;
    this.confirmSent = confirmSent;
    this.confirmSent2 = confirmSent2;
    this.editButton = editButton;
    this.restName = restName;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityBookingConfirmationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityBookingConfirmationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_booking_confirmation, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityBookingConfirmationBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.CalendarButton;
      Button CalendarButton = rootView.findViewById(id);
      if (CalendarButton == null) {
        break missingId;
      }

      id = R.id.TimeAndDate;
      TextView TimeAndDate = rootView.findViewById(id);
      if (TimeAndDate == null) {
        break missingId;
      }

      id = R.id.addText;
      TextView addText = rootView.findViewById(id);
      if (addText == null) {
        break missingId;
      }

      id = R.id.addText2;
      TextView addText2 = rootView.findViewById(id);
      if (addText2 == null) {
        break missingId;
      }

      id = R.id.addText3;
      TextView addText3 = rootView.findViewById(id);
      if (addText3 == null) {
        break missingId;
      }

      id = R.id.addText4;
      TextView addText4 = rootView.findViewById(id);
      if (addText4 == null) {
        break missingId;
      }

      id = R.id.addText5;
      TextView addText5 = rootView.findViewById(id);
      if (addText5 == null) {
        break missingId;
      }

      id = R.id.btn_home;
      Button btnHome = rootView.findViewById(id);
      if (btnHome == null) {
        break missingId;
      }

      id = R.id.cancelButton;
      Button cancelButton = rootView.findViewById(id);
      if (cancelButton == null) {
        break missingId;
      }

      id = R.id.confirmSent;
      TextView confirmSent = rootView.findViewById(id);
      if (confirmSent == null) {
        break missingId;
      }

      id = R.id.confirmSent2;
      TextView confirmSent2 = rootView.findViewById(id);
      if (confirmSent2 == null) {
        break missingId;
      }

      id = R.id.editButton;
      Button editButton = rootView.findViewById(id);
      if (editButton == null) {
        break missingId;
      }

      id = R.id.restName;
      TextView restName = rootView.findViewById(id);
      if (restName == null) {
        break missingId;
      }

      return new ActivityBookingConfirmationBinding((RelativeLayout) rootView, CalendarButton,
          TimeAndDate, addText, addText2, addText3, addText4, addText5, btnHome, cancelButton,
          confirmSent, confirmSent2, editButton, restName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}