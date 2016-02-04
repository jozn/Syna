package com.mmdt.syna.view.imagecrop;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.mmdt.syna.view.imagecrop.ImageCropperFragment.ImageCropperFragment;

public class ImageCropperActivity extends Activity implements ImageCropperFragment {
    public boolean f2489a;
    private ActionBar f2490b;
    private ImageCropperFragment f2491c;
    private String f2492d;

    public ImageCropperActivity() {
        this.f2489a = false;
    }

    private Activity m3457b() {
        return this;
    }

    private void m3459b(Bitmap bitmap) {
        new Thread(new ImageCropperActivity(this, bitmap)).start();
    }

    public void m3460a(Bitmap bitmap) {
        if (bitmap == null) {
            Intent intent;
            if (this.f2492d.equals("image_cropper_profile")) {
                intent = new Intent();
                intent.putExtra("key_cropped_image_address", "");
                setResult(1110, intent);
            } else if (this.f2492d.equals("image_cropper_chat")) {
                intent = new Intent();
                intent.putExtra("KeyCroppedImageAddress", "");
                setResult(1106, intent);
            }
            finish();
            overridePendingTransition(0, 0);
            return;
        }
        m3459b(bitmap);
    }

    public boolean m3461a() {
        return this.f2489a;
    }

    public void onBackPressed() {
        Intent intent;
        if (this.f2492d.equals("image_cropper_profile")) {
            intent = new Intent();
            intent.putExtra("key_cropped_image_address", "");
            setResult(1110, intent);
        } else if (this.f2492d.equals("image_cropper_chat")) {
            intent = new Intent();
            intent.putExtra("KeyCroppedImageAddress", "");
            setResult(1106, intent);
        }
        finish();
        overridePendingTransition(0, 0);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903146);
        this.f2490b = getActionBar();
        this.f2490b.setDisplayHomeAsUpEnabled(true);
        this.f2490b.setIcon(2130837897);
        this.f2490b.setTitle(2131493621);
        this.f2492d = getIntent().getStringExtra("key_image_cropper_source_activity");
        if (getIntent().hasExtra("key_image_cropper_source_activity") && getIntent().getStringExtra("key_image_cropper_source_activity") != null) {
            this.f2492d = getIntent().getStringExtra("key_image_cropper_source_activity");
            if (this.f2492d.equals("image_cropper_profile")) {
                this.f2489a = true;
            } else if (this.f2492d.equals("image_cropper_chat")) {
                this.f2489a = false;
            }
        }
        if (!getIntent().hasExtra("key_image_cropper_source_image_address") || getIntent().getStringExtra("key_image_cropper_source_image_address") == null) {
            Intent intent;
            if (this.f2492d.equals("image_cropper_profile")) {
                intent = new Intent();
                intent.putExtra("key_cropped_image_address", "");
                setResult(1110, intent);
            } else if (this.f2492d.equals("image_cropper_chat")) {
                intent = new Intent();
                intent.putExtra("KeyCroppedImageAddress", "");
                setResult(1106, intent);
            }
            finish();
            overridePendingTransition(0, 0);
            return;
        }
        this.f2491c = new ImageCropperFragment(getIntent().getStringExtra("key_image_cropper_source_image_address"));
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(2131427461, this.f2491c);
        beginTransaction.setTransition(4099);
        beginTransaction.commit();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(2131689476, menu);
        return super.onCreateOptionsMenu(menu);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                onBackPressed();
                return true;
            case 2131427850:
                this.f2491c.m3463a();
                return true;
            case 2131427851:
                this.f2491c.m3464a(-90);
                return true;
            case 2131427852:
                this.f2491c.m3464a(90);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
