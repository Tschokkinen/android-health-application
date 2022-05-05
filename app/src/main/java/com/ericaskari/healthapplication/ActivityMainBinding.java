// Generated by view binder compiler. Do not edit!
package com.ericaskari.healthapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

/**
 * Generated by view binder compiler.
 */
public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  @NonNull
  public final FloatingActionButton addButton;

  @NonNull
  public final RecyclerView mainActivityRecyclerView;

  private ActivityMainBinding(@NonNull CoordinatorLayout rootView,
      @NonNull FloatingActionButton addButton, @NonNull RecyclerView mainActivityRecyclerView) {
    this.rootView = rootView;
    this.addButton = addButton;
    this.mainActivityRecyclerView = mainActivityRecyclerView;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.add_button;
      FloatingActionButton addButton = ViewBindings.findChildViewById(rootView, id);
      if (addButton == null) {
        break missingId;
      }

      id = R.id.main_activity_recycler_view;
      RecyclerView mainActivityRecyclerView = ViewBindings.findChildViewById(rootView, id);
      if (mainActivityRecyclerView == null) {
        break missingId;
      }

      return new ActivityMainBinding((CoordinatorLayout) rootView, addButton,
          mainActivityRecyclerView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
