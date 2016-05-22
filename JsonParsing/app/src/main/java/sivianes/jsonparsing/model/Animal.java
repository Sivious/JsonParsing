package sivianes.jsonparsing.model;

/**
 * Created by Javier on 22/05/2016.
 */
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Animal {

    @SerializedName("species")
    @Expose
    public String species;
    @SerializedName("family")
    @Expose
    public String family;
    @SerializedName("IUCN")
    @Expose
    public String IUCN;
    @SerializedName("year")
    @Expose
    public String year;
    @SerializedName("picture")
    @Expose
    public String picture;
    @SerializedName("notes")
    @Expose
    public Notes notes;

}
