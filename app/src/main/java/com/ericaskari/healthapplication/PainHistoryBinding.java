// Generated by view binder compiler. Do not edit!
package com.ericaskari.healthapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.github.mikephil.charting.charts.PieChart;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

/**
 * Generated by view binder compiler.
 */
public final class PainHistoryBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final RecyclerView painHistoryRecyclerView;

  @NonNull
  public final PieChart pieChart;

  @NonNull
  public final LinearLayout sampleMainLayout;

  private PainHistoryBinding(@NonNull LinearLayout rootView,
      @NonNull RecyclerView painHistoryRecyclerView, @NonNull PieChart pieChart,
      @NonNull LinearLayout sampleMainLayout) {
    this.rootView = rootView;
    this.painHistoryRecyclerView = painHistoryRecyclerView;
    this.pieChart = pieChart;
    this.sampleMainLayout = sampleMainLayout;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static PainHistoryBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static PainHistoryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.pain_history, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static PainHistoryBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.pain_history_recycler_view;
      RecyclerView painHistoryRecyclerView = ViewBindings.findChildViewById(rootView, id);
      if (painHistoryRecyclerView == null) {
        break missingId;
      }

      id = R.id.pieChart;
      PieChart pieChart = ViewBindings.findChildViewById(rootView, id);
      if (pieChart == null) {
        break missingId;
      }

      LinearLayout sampleMainLayout = (LinearLayout) rootView;

      return new PainHistoryBinding((LinearLayout) rootView, painHistoryRecyclerView, pieChart,
          sampleMainLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
