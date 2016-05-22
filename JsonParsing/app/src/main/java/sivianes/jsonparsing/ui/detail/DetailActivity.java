package sivianes.jsonparsing.ui.detail;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.io.File;

import sivianes.jsonparsing.R;

/**
 * Created by Javier on 22/05/2016.
 */
public class DetailActivity extends AppCompatActivity implements DetailView{
    private ImageView image;
    private String imagePath;
    private TextView species;
    private TextView family;
    private TextView iucn;
    private TextView year;
    private TextView notes;


    ImageLoader imageLoader;
    private DisplayImageOptions options;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        init();
    }

    private void init() {
        imageLoader = ImageLoader.getInstance();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .discCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .build();

        imageLoader.init(config);
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageOnFail(R.drawable.not_found)
                .displayer(new RoundedBitmapDisplayer(20))
                .build();

        image = (ImageView) findViewById(R.id.detail_animal_image);
        species = (TextView) findViewById(R.id.detail_animal_species);
        family = (TextView) findViewById(R.id.detail_animal_family);
        iucn = (TextView) findViewById(R.id.detail_animal_iucn);
        year = (TextView) findViewById(R.id.detail_animal_year);
        notes = (TextView) findViewById(R.id.detail_animal_notes);

        getInfoFromExtras(getIntent());

    }

    private void getInfoFromExtras(Intent intent) {
        //image.setImageBitmap((Bitmap) intent.getExtras().getParcelable(DetailView.BUNDLE_IMAGE_KEY));

        imagePath = intent.getStringExtra(DetailView.BUNDLE_IMAGE_KEY);
        loadImage(imagePath);

        species.setText(intent.getStringExtra(DetailView.BUNDLE_SPECIES_KEY));
        family.setText(intent.getStringExtra(DetailView.BUNDLE_FAMILY_KEY));
        iucn.setText(intent.getStringExtra(DetailView.BUNDLE_IUCN_KEY));
        year.setText(intent.getStringExtra(DetailView.BUNDLE_YEAR_KEY));
        notes.setText(intent.getStringExtra(DetailView.BUNDLE_NOTES_KEY));

    }

    private void loadImage(String imagePath) {
        if (imagePath.compareTo(IMAGE_NOT_FOUND) == 0){
            image.setImageResource(R.drawable.not_found);
        }else if ((new File(imagePath)).exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imagePath);
            image.setImageBitmap(myBitmap);
        }else{
            imageLoader.displayImage(imagePath, image, options);
        }
    }
}
