// Generated by view binder compiler. Do not edit!
package com.ericaskari.healthapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

/**
 * Generated by view binder compiler.
 */
public final class MedicinesBinding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  @NonNull
  public final ConstraintLayout constraintLayout;

  @NonNull
  public final TextView textView5;

  private MedicinesBinding(@NonNull CoordinatorLayout rootView,
      @NonNull ConstraintLayout constraintLayout, @NonNull TextView textView5) {
    this.rootView = rootView;
    this.constraintLayout = constraintLayout;
    this.textView5 = textView5;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static MedicinesBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static MedicinesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.medicines, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static MedicinesBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.constraintLayout;
      ConstraintLayout constraintLayout = ViewBindings.findChildViewById(rootView, id);
      if (constraintLayout == null) {
        break missingId;
      }

      id = R.id.textView5;
      TextView textView5 = ViewBindings.findChildViewById(rootView, id);
      if (textView5 == null) {
        break missingId;
      }

      return new MedicinesBinding((CoordinatorLayout) rootView, constraintLayout, textView5);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}