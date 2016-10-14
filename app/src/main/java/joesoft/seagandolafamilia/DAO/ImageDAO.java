package joesoft.seagandolafamilia.DAO;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import joesoft.seagandolafamilia.model.ImageToShow;
import joesoft.seagandolafamilia.util.ResultListener;

/**
 * Created by joe on 8/2/16.
 */
public class ImageDAO {

    public void getImagesToShowFromFireBase(final ResultListener<List<ImageToShow>> listenerController) {
        FirebaseDatabase fireBaseDB = FirebaseDatabase.getInstance();
        DatabaseReference rootRef = fireBaseDB.getReference();

        rootRef.child("pictures").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        List<ImageToShow> imagesToShow = new ArrayList<>();
                        Log.w("", "getUser:onCancelled");
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            imagesToShow.add(child.getValue(ImageToShow.class));
                        }


                        listenerController.finish(imagesToShow);

                        // ...
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("", "getUser:onCancelled", databaseError.toException());
                    }
                });
    }

    public void getImageToShowURLFromFireBase(final String imageToShow, final ResultListener<String> listenerController) {
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference firebaseStorageRef = firebaseStorage.getReferenceFromUrl("gs://seagrandolafamilia.appspot.com");

        firebaseStorageRef.child(imageToShow).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
//                imageToShow.setUrl(uri.toString());
                // Got the download URL for 'users/me/profile.png'
                listenerController.finish(uri.toString());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }
}


