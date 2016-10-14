package joesoft.seagandolafamilia.model;

/**
 * Created by joe on 7/30/16.
 */
public class Picture {
    private Frace textPicture;
    private ImageToShow idImage;

    public Picture(Frace textPicture, ImageToShow idImage) {
        this.textPicture = textPicture;
        this.idImage = idImage;
    }

    public Frace getTextPicture() {
        return textPicture;
    }

    public void setTextPicture(Frace textPicture) {
        this.textPicture = textPicture;
    }

    public ImageToShow getIdImage() {
        return idImage;
    }

    public void setIdImage(ImageToShow idImage) {
        this.idImage = idImage;
    }
}
