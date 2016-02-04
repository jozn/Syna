package com.mmdt.syna.view.imagecrop;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.edmodo.cropper.CropImageView;
import mobi.mmdt.p041a.ImageTools;

/* renamed from: com.mmdt.syna.view.imagecrop.b */
public class ImageCropperFragment extends Fragment {
    public CropImageView f2495a;
    private View f2496b;
    private Bitmap f2497c;
    private String f2498d;
    private ImageCropperFragment f2499e;

    /* renamed from: com.mmdt.syna.view.imagecrop.b.a */
    public interface ImageCropperFragment {
        void m3454a(Bitmap bitmap);

        boolean m3455a();
    }

    public ImageCropperFragment(String str) {
        this.f2498d = str;
    }

    private void m3462b() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels * displayMetrics.heightPixels;
        this.f2495a = (CropImageView) this.f2496b.findViewById(2131427728);
        Bitmap a = ImageTools.m4104a(this.f2498d, Math.max(i, 2000000));
        if (a == null) {
            this.f2499e.m3454a(null);
        }
        this.f2495a.setImageBitmap(a);
        this.f2495a.setGuidelines(1);
        if (this.f2499e.m3455a()) {
            this.f2495a.setFixedAspectRatio(this.f2499e.m3455a());
            this.f2495a.setAspectRatio(1, 1);
        }
    }

    public void m3463a() {
        this.f2497c = this.f2495a.m2222a();
        this.f2499e.m3454a(this.f2497c);
    }

    public void m3464a(int i) {
        this.f2495a.m2223a(i);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.f2499e = (ImageCropperFragment) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnInteractionListener");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f2496b = layoutInflater.inflate(2130903147, viewGroup, false);
        m3462b();
        return this.f2496b;
    }
}
