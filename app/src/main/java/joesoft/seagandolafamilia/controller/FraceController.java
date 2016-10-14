package joesoft.seagandolafamilia.controller;

import java.util.List;

import joesoft.seagandolafamilia.DAO.FraceDAO;
import joesoft.seagandolafamilia.model.Frace;
import joesoft.seagandolafamilia.util.ResultListener;


/**
 * Created by joe on 8/2/16.
 */
public class FraceController {

    public void getFraces(final ResultListener<List<Frace>> listenerFromView){
        FraceDAO fraceDAO = new FraceDAO();
        fraceDAO.getFracesFromFireBase(new ResultListener<List<Frace>>() {
            @Override
            public void finish(List<Frace> resultado) {
                listenerFromView.finish(resultado);
            }
        });
    }

}
