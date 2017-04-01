package dsardy.in.chalkcards;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static dsardy.in.chalkcards.Appli.adapter;
import static dsardy.in.chalkcards.Appli.bitmaplist;

public class MainActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMG = 1 ;
    private static final int CAMERA_REQUEST = 2 ;
    FloatingActionButton floatingActionButton1, fab2,fab3;
    LinearLayout linearLayoutoptions;
    boolean isFabPressed = false;

    Button bPreview;
    TextView t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton1 = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        fab2 = (FloatingActionButton)findViewById(R.id.floatingActionButton2);
        fab3 = (FloatingActionButton)findViewById(R.id.floatingActionButton3);
        linearLayoutoptions = (LinearLayout)findViewById(R.id.lloptions);
        bPreview = (Button)findViewById(R.id.buttonp);
        t = (TextView)findViewById(R.id.textViewHint);

        bitmaplist = new  ArrayList<Bitmap>();







        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFabPressed){
                    linearLayoutoptions.setVisibility(View.GONE);
                    isFabPressed = false;
                    floatingActionButton1.setImageResource(R.drawable.ic_add_black_24dp);
                    t.setText(" press FAB to add an Image");


                }else{
                    linearLayoutoptions.setVisibility(View.VISIBLE);
                    isFabPressed=true;
                    floatingActionButton1.setImageResource(R.drawable.ic_cancel_black_24dp);
                    t.setText("select your method");


                }

            }
        });


        //take image from camera
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);

            }
        });


        //pick a image from gallery
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);

            }
        });

        bPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter = new DataAdapter(MainActivity.this,bitmaplist);
                startActivity(new Intent(MainActivity.this,PreviewActivity.class));
            }
        });


    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);




        if(reqCode==RESULT_LOAD_IMG){

            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    bitmaplist.add(selectedImage);

                    t.setText("Image added to the stock!");
                    bPreview.setBackgroundColor(getResources().getColor(R.color.colorAccent));



                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
                }

            }else {
                Toast.makeText(this, "You haven't picked Image",Toast.LENGTH_LONG).show();
            }

        }

        if (reqCode == CAMERA_REQUEST) {

            if (resultCode == RESULT_OK){


                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    bitmaplist.add(photo);
                    t.setText("Image added to the stock!");
                bPreview.setBackgroundColor(getResources().getColor(R.color.colorAccent));



            }else {
                Toast.makeText(this, "You haven't picked Image",Toast.LENGTH_LONG).show();
            }


        }

    }


}
