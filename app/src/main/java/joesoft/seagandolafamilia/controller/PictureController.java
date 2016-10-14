package joesoft.seagandolafamilia.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import joesoft.seagandolafamilia.model.Frace;
import joesoft.seagandolafamilia.model.ImageToShow;
import joesoft.seagandolafamilia.model.Picture;
import joesoft.seagandolafamilia.util.ResultListener;

/**
 * Created by joe on 8/2/16.
 */
public class PictureController {

    private ResultListener<List<Picture>> listenerController;
    private List<Frace> fraceList;
    private List<ImageToShow> imageToShowList;
    private List<Picture> pictureList;


    private static PictureController instance;

    public PictureController() {
        this.fraceList = new ArrayList<>();
        this.imageToShowList = new ArrayList<>();
        this.pictureList = new ArrayList<>();
    }

    public static PictureController getInstante(){
        if(instance == null){
            instance = new PictureController();

        }
        return instance;
    }

    public void getPictures(ResultListener<List<Picture>>listenerController){
        this.listenerController = listenerController;

        if(pictureList.size()>0){
            listenerController.finish(pictureList);
            return;
        }

        FraceController fraceController = new FraceController();
        fraceController.getFraces(new ResultListener<List<Frace>>() {
            @Override
            public void finish(List<Frace> resultado) {
                fraceList = resultado;
                checkAndReturnListener();
            }
        });

        final ImageController imageController = new ImageController();
        imageController.getImagesToShow(new ResultListener<List<ImageToShow>>() {
            @Override
            public void finish(List<ImageToShow> resultado) {
                imageToShowList = resultado;
                checkAndReturnListener();
            }
        });
    }

    public void checkAndReturnListener(){
        if(imageToShowList.size() == 0 || fraceList.size() == 0)return;

        List<Picture> pictureList = generateRandomPictures(imageToShowList,fraceList);
        listenerController.finish(pictureList);
    }

    private List<Picture> generateRandomPictures(List<ImageToShow> imageToShowList, List<Frace> fraceList) {
//note a single Random object is reused here
        Random randomGeneratorFraces = new Random();
        Random randomGeneratorImage = new Random();

        for (int idx = 1; idx <= 20; ++idx){
            int randomIntFace = randomGeneratorFraces.nextInt(fraceList.size()-1);
            int randomIntImage = randomGeneratorImage.nextInt(imageToShowList.size()-1);
            pictureList.add(new Picture(fraceList.get(randomIntFace),imageToShowList.get(randomIntImage)));

        }

        return pictureList;
    }

    public void getPictureURI(final Picture picture, final ResultListener<String>listenerView){
        if(picture.getIdImage().getUrl() == null){
            ImageController imageController = new ImageController();
            imageController.getImageToShowURL(picture.getIdImage().getPicture(), new ResultListener<String>() {
                @Override
                public void finish(String resultado) {
                    picture.getIdImage().setUrl(resultado);
                    listenerView.finish(resultado);
                }
            });
        }
        else{
            listenerView.finish(picture.getIdImage().getUrl());
        }
    }
}
