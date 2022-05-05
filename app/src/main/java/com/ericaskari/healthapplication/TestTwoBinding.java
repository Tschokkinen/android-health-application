// Generated by view binder compiler. Do not edit!
package com.ericaskari.healthapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

/**
 * Generated by view binder compiler.
 */
public final class TestTwoBinding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  @NonNull
  public final Button buttonPickBirthdateFirstLaunch;

  @NonNull
  public final EditText editTextLongTermIllness;

  @NonNull
  public final EditText firstName;

  @NonNull
  public final EditText height;

  @NonNull
  public final EditText lastName;

  @NonNull
  public final Button nextButtonFirstLaunchLongTermIllness;

  @NonNull
  public final RadioGroup radioGroup;

  @NonNull
  public final RadioButton radioNo;

  @NonNull
  public final RadioButton radioYes;

  @NonNull
  public final TextView textView10;

  @NonNull
  public final TextView textView11;

  @NonNull
  public final TextView textView12;

  @NonNull
  public final TextView textView9;

  @NonNull
  public final TextView textViewBirthDate;

  @NonNull
  public final TextView textViewFirstName;

  @NonNull
  public final TextView userDataTitle;

  @NonNull
  public final EditText weight;

  private TestTwoBinding(@NonNull CoordinatorLayout rootView,
      @NonNull Button buttonPickBirthdateFirstLaunch, @NonNull EditText editTextLongTermIllness,
      @NonNull EditText firstName, @NonNull EditText height, @NonNull EditText lastName,
      @NonNull Button nextButtonFirstLaunchLongTermIllness, @NonNull RadioGroup radioGroup,
      @NonNull RadioButton radioNo, @NonNull RadioButton radioYes, @NonNull TextView textView10,
      @NonNull TextView textView11, @NonNull TextView textView12, @NonNull TextView textView9,
      @NonNull TextView textViewBirthDate, @NonNull TextView textViewFirstName,
      @NonNull TextView userDataTitle, @NonNull EditText weight) {
    this.rootView = rootView;
    this.buttonPickBirthdateFirstLaunch = buttonPickBirthdateFirstLaunch;
    this.editTextLongTermIllness = editTextLongTermIllness;
    this.firstName = firstName;
    this.height = height;
    this.lastName = lastName;
    this.nextButtonFirstLaunchLongTermIllness = nextButtonFirstLaunchLongTermIllness;
    this.radioGroup = radioGroup;
    this.radioNo = radioNo;
    this.radioYes = radioYes;
    this.textView10 = textView10;
    this.textView11 = textView11;
    this.textView12 = textView12;
    this.textView9 = textView9;
    this.textViewBirthDate = textViewBirthDate;
    this.textViewFirstName = textViewFirstName;
    this.userDataTitle = userDataTitle;
    this.weight = weight;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static TestTwoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static TestTwoBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.test_two, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static TestTwoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonPickBirthdateFirstLaunch;
      Button buttonPickBirthdateFirstLaunch = ViewBindings.findChildViewById(rootView, id);
      if (buttonPickBirthdateFirstLaunch == null) {
        break missingId;
      }

      id = R.id.editTextLongTermIllness;
      EditText editTextLongTermIllness = ViewBindings.findChildViewById(rootView, id);
      if (editTextLongTermIllness == null) {
        break missingId;
      }

      id = R.id.firstName;
      EditText firstName = ViewBindings.findChildViewById(rootView, id);
      if (firstName == null) {
        break missingId;
      }

      id = R.id.height;
      EditText height = ViewBindings.findChildViewById(rootView, id);
      if (height == null) {
        break missingId;
      }

      id = R.id.lastName;
      EditText lastName = ViewBindings.findChildViewById(rootView, id);
      if (lastName == null) {
        break missingId;
      }

      id = R.id.nextButtonFirstLaunchLongTermIllness;
      Button nextButtonFirstLaunchLongTermIllness = ViewBindings.findChildViewById(rootView, id);
      if (nextButtonFirstLaunchLongTermIllness == null) {
        break missingId;
      }

      id = R.id.radioGroup;
      RadioGroup radioGroup = ViewBindings.findChildViewById(rootView, id);
      if (radioGroup == null) {
        break missingId;
      }

      id = R.id.radioNo;
      RadioButton radioNo = ViewBindings.findChildViewById(rootView, id);
      if (radioNo == null) {
        break missingId;
      }

      id = R.id.radioYes;
      RadioButton radioYes = ViewBindings.findChildViewById(rootView, id);
      if (radioYes == null) {
        break missingId;
      }

      id = R.id.textView10;
      TextView textView10 = ViewBindings.findChildViewById(rootView, id);
      if (textView10 == null) {
        break missingId;
      }

      id = R.id.textView11;
      TextView textView11 = ViewBindings.findChildViewById(rootView, id);
      if (textView11 == null) {
        break missingId;
      }

      id = R.id.textView12;
      TextView textView12 = ViewBindings.findChildViewById(rootView, id);
      if (textView12 == null) {
        break missingId;
      }

      id = R.id.textView9;
      TextView textView9 = ViewBindings.findChildViewById(rootView, id);
      if (textView9 == null) {
        break missingId;
      }

      id = R.id.textViewBirthDate;
      TextView textViewBirthDate = ViewBindings.findChildViewById(rootView, id);
      if (textViewBirthDate == null) {
        break missingId;
      }

      id = R.id.textViewFirstName;
      TextView textViewFirstName = ViewBindings.findChildViewById(rootView, id);
      if (textViewFirstName == null) {
        break missingId;
      }

      id = R.id.userDataTitle;
      TextView userDataTitle = ViewBindings.findChildViewById(rootView, id);
      if (userDataTitle == null) {
        break missingId;
      }

      id = R.id.weight;
      EditText weight = ViewBindings.findChildViewById(rootView, id);
      if (weight == null) {
        break missingId;
      }

      return new TestTwoBinding((CoordinatorLayout) rootView, buttonPickBirthdateFirstLaunch,
          editTextLongTermIllness, firstName, height, lastName,
          nextButtonFirstLaunchLongTermIllness, radioGroup, radioNo, radioYes, textView10,
          textView11, textView12, textView9, textViewBirthDate, textViewFirstName, userDataTitle,
          weight);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
