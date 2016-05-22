package sivianes.jsonparsing.model;

/**
 * Created by Javier on 22/05/2016.
 */
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Animals {

    @SerializedName("favourites")
    @Expose
    public List<Animal> animals = new ArrayList<Animal>();
    @SerializedName("others")
    @Expose
    public List<Animal> others = new ArrayList<Animal>();

}
