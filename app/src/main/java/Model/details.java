package Model;

/**
 * Created by akhil on 16/6/16.
 */
public class details {
    private String name,message,email,imageUrl;

    public String getImageUrl() {

        return imageUrl;
    }

    public details(String name, String message, String email, String imageUrl) {
        this.name = name;
        this.message = message;
        this.email = email;
        this.imageUrl = imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public details() {

    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
