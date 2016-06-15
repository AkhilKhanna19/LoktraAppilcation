package integrategit.application.com.integrategit;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by akhil on 15/6/16.
 */
public class ImageController extends AppController {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}