// Generated by view binder compiler. Do not edit!
package com.nvt.bloodbank.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.google.android.gms.maps.MapView;
import com.nvt.bloodbank.R;
import java.lang.NullPointerException;
import java.lang.Override;

public final class ActivityMapsBinding implements ViewBinding {
  @NonNull
  private final MapView rootView;

  @NonNull
  public final MapView ggmap;

  private ActivityMapsBinding(@NonNull MapView rootView, @NonNull MapView ggmap) {
    this.rootView = rootView;
    this.ggmap = ggmap;
  }

  @Override
  @NonNull
  public MapView getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMapsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMapsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_maps, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMapsBinding bind(@NonNull View rootView) {
    if (rootView == null) {
      throw new NullPointerException("rootView");
    }

    MapView ggmap = (MapView) rootView;

    return new ActivityMapsBinding((MapView) rootView, ggmap);
  }
}
