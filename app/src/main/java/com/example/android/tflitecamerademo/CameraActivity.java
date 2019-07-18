/* Copyright 2017 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package com.example.android.tflitecamerademo;
import android.content.Context;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.security.Policy;
import java.util.Locale;

/** Main {@code Activity} class for the Camera app. */
public class CameraActivity extends Activity {
  private TextToSpeech textToSpeechSystem;
  private TextToSpeech textToSpeechSystem1;
  private FrameLayout textureView;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_camera);
      textureView=(FrameLayout)findViewById(R.id.container);
      CameraManager camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

    final GlobalClass globalVariable = (GlobalClass) getApplicationContext();



      // Get name and email from global/application context
    textureView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

//
//          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//
//              String cameraId = null; // Usually back camera is at 0 position.
//              try {
//                  cameraId = camManager.getCameraIdList()[0];
//                  camManager.setTorchMode(cameraId, true);   //Turn ON
//              } catch (CameraAccessException e) {
//                  e.printStackTrace();
//              }
//          }





          final String name  = globalVariable.getName();
        Toast.makeText(CameraActivity.this, ""+name, Toast.LENGTH_SHORT).show();
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

              textToSpeechSystem.speak(name+"रुपए", TextToSpeech.QUEUE_FLUSH, null, null);
              textToSpeechSystem1.speak(name+"Rupees", TextToSpeech.QUEUE_FLUSH, null, null);
          } else {
              textToSpeechSystem.speak(name+"रुपए", TextToSpeech.QUEUE_FLUSH, null);
              textToSpeechSystem1.speak(name+"Rupees", TextToSpeech.QUEUE_FLUSH, null);

          }




      }
    });
    final String name  = globalVariable.getName();






    if (null == savedInstanceState) {
      getFragmentManager()
          .beginTransaction()
          .replace(R.id.container, Camera2BasicFragment.newInstance())
          .commit();
    }
  }



    @Override
    public void onStart() {
        super.onStart();
        textToSpeechSystem=new TextToSpeech(CameraActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeechSystem.setLanguage(Locale.forLanguageTag("hin"));
                    textToSpeechSystem.setSpeechRate(1.5f);


                }
            }
        });


        textToSpeechSystem1=new TextToSpeech(CameraActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeechSystem1.setLanguage(Locale.UK);
                    textToSpeechSystem1.setSpeechRate(1.5f);


                }
            }
        });

    }

}
