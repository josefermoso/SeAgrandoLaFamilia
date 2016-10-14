package joesoft.seagandolafamilia.DAO;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import joesoft.seagandolafamilia.model.Frace;
import joesoft.seagandolafamilia.util.ResultListener;

/**
 * Created by joe on 8/2/16.
 */
public class FraceDAO {

    public void getFracesFromFireBase(final ResultListener<List<Frace>> controllerListener){
        FirebaseDatabase fireBaseDB = FirebaseDatabase.getInstance();
        DatabaseReference rootRef = fireBaseDB.getReference();

        rootRef.child("frases").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        List<Frace> fraces = new ArrayList<Frace>();
                        Log.w("", "getUser:onCancelled");
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            fraces.add(child.getValue(Frace.class));
                        }
                        controllerListener.finish(fraces);

                        // ...
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("", "getUser:onCancelled", databaseError.toException());
                    }
                });

    }
}
