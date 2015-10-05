/*
    Copyright 2015 Carson McLean

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package com.carsonmclean.camclean_reflex.Utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.widget.Toast;

/**
 * Created by carsonmclean on 3/10/15.
 */

// Create messages in the Utilities and pass them as object to Activities to be seen
// Also allow easy creation of dialogs without having to repeat setup code
public class MessagePasser {
    private Activity activity;

    // Constructor
    // Sameer Segal; http://stackoverflow.com/questions/3572463/what-is-context-in-android; 2015-10-03
    // When calling Constructor in an Activity class, pass 'this' as argument to get Context
    public MessagePasser(Activity activity) {
        this.activity = activity;
    }

    // http://stackoverflow.com/questions/2115758/how-to-display-alert-dialog-in-android
    public void createDialog(String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show();
    }

    // http://developer.android.com/guide/topics/ui/notifiers/toasts.html; 2015-10-03
    public void createToast(String message) {
        Toast toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
