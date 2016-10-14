package joesoft.seagandolafamilia.controller;

import java.util.List;

import joesoft.seagandolafamilia.DAO.ImageDAO;
import joesoft.seagandolafamilia.model.ImageToShow;
import joesoft.seagandolafamilia.util.ResultListener;


/**
 * Created by joe on 8/2/16.
 */
public class ImageController {

    public void getImagesToShow(final ResultListener<List<ImageToShow>> listernetView) {
        ImageDAO imageDAO = new ImageDAO();
        imageDAO.getImagesToShowFromFireBase(new ResultListener<List<ImageToShow>>() {
            @Override
            public void finish(List<ImageToShow> resultado) {
                listernetView.finish(resultado);
            }
        });
    }

    public void getImageToShowURL(String imageToShow, final ResultListener<String> listenerController) {
        ImageDAO imageDAO = new ImageDAO();
        imageDAO.getImageToShowURLFromFireBase(imageToShow, new ResultListener<String>() {
            @Override
            public void finish(String resultado) {
                listenerController.finish(resultado);
            }
        });
    }
}