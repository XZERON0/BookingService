package project.backend.DTO;
import java.util.List;
public class ServiceCategoryDTO {
    private long id;
    private String type;
    private List<String> subCategories;
    
    public long getId()
    {
        return this.id;
    }
    public String getType(){return this.type;}
    public List<String> getSubCategories(){return this.subCategories;}
    public void setId(long id){this.id = id;}
    public void setType(String type){this.type = type;}
    public void setSubCategories(List<String> subCategories){this.subCategories = subCategories;}

}
