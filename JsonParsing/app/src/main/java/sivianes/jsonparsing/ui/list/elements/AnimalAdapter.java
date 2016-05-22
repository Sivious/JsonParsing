package sivianes.jsonparsing.ui.list.elements;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.utils.DiskCacheUtils;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;

import java.io.File;
import java.util.List;

import sivianes.jsonparsing.R;
import sivianes.jsonparsing.model.Animal;
import sivianes.jsonparsing.ui.detail.DetailActivity;
import sivianes.jsonparsing.ui.detail.DetailView;

/**
 * Created by Javier on 22/05/2016.
 */
public class AnimalAdapter extends android.support.v7.widget.RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder> {
    private static List<Animal> JSONSchema;
    private static Context context;
    ImageLoader imageLoader;
    private DisplayImageOptions options;

    public AnimalAdapter(List<Animal> JSONSchema) {
        this.JSONSchema = JSONSchema;
    }

    @Override
    public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_item, parent, false);
        AnimalViewHolder animalViewHolder = new AnimalViewHolder(v);
        init();
        return animalViewHolder;
    }

    private void init() {
        imageLoader = ImageLoader.getInstance();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .discCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .build();

        imageLoader.init(config);
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageOnFail(R.drawable.not_found)
                .displayer(new RoundedBitmapDisplayer(20))
                .build();
    }

    @Override
    public void onBindViewHolder(AnimalViewHolder holder, int position) {
        holder.name.setText(JSONSchema.get(position).species);
        holder.family.setText(JSONSchema.get(position).family);
        holder.index = position;

        getBitmapFromUrl(JSONSchema.get(position).picture, holder.image);
    }

    private void getBitmapFromUrl(String picture, final ImageView image) {
        imageLoader.displayImage(picture, image, options);
    }


    @Override
    public int getItemCount() {
        return JSONSchema.size();
    }

    public static class AnimalViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name;
        public TextView family;
        public int index;

        public AnimalViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.item_animal_image);
            name = (TextView) v.findViewById(R.id.item_animal_name);
            family = (TextView) v.findViewById(R.id.item_animal_family);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle extras = new Bundle();

                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(DetailView.BUNDLE_SPECIES_KEY, JSONSchema.get(index).species);
                    intent.putExtra(DetailView.BUNDLE_FAMILY_KEY, JSONSchema.get(index).family);
                    intent.putExtra(DetailView.BUNDLE_IUCN_KEY, JSONSchema.get(index).IUCN);
                    intent.putExtra(DetailView.BUNDLE_YEAR_KEY, JSONSchema.get(index).year);
                    intent.putExtra(DetailView.BUNDLE_NOTES_KEY, JSONSchema.get(index).notes.Cdata);

                    File file = DiskCacheUtils.findInCache(JSONSchema.get(index).picture, ImageLoader.getInstance().getDiskCache());
                    String imagePath = getImageToLoad(file);

                    intent.putExtra(DetailView.BUNDLE_IMAGE_KEY, imagePath);
                    intent.putExtras(extras);

                    context.startActivity(intent);
                }

                private String getImageToLoad(File file) {
                    if (file != null && file.exists()){
                        return DiskCacheUtils.findInCache(JSONSchema.get(index).picture, ImageLoader.getInstance().getDiskCache()).getAbsolutePath();
                    }else if (file == null){
                        return DetailView.IMAGE_NOT_FOUND;
                    }else {
                        return JSONSchema.get(index).picture;
                    }
                }
            });
        }
    }

}
