package posgraducao.lamfsistemas.com.br.calculogasolina;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class CameraActivity extends AppCompatActivity {
    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Bundle extras = getIntent().getExtras();
        Bitmap  imageBitmap = (Bitmap) extras.get("data");

        mImageView = new ImageView( this);
        mImageView.setImageBitmap(imageBitmap);

        setContentView(mImageView);
    }
}
