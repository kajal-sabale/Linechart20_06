package studytutorial.in.linechart;

import android.os.Bundle;
import java.io.IOException;
import java.util.UUID;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements OnClickListener{

    private static final String TAG = "MyActivity";
    private BluetoothAdapter BA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button b = (Button) findViewById(R.id.button1);
        BA = BluetoothAdapter.getDefaultAdapter();
        b.setOnClickListener(this);
    }
    @Override
    public void onClick(View V) {
        AcceptThread at=new AcceptThread();
        at.start();

    }


    private class AcceptThread extends Thread {
        private final BluetoothServerSocket mmServerSocket;
        final UUID MY_UUID = UUID
                .fromString("fa87c0d0-afac-11de-8a39-0800200c9a66");

        public AcceptThread() {
            // Use a temporary object that is later assigned to mmServerSocket
            // because mmServerSocket is final.
            BluetoothServerSocket tmp = null;



            try {
                // MY_UUID is the app's UUID string, also used by the client code.
                String NAME = "BluetoothChat";
                tmp = BA.listenUsingRfcommWithServiceRecord(NAME, MY_UUID);
                Log.i(TAG, "ListenUsingRfid called successfully");
            } catch (IOException e) {
                Log.e(TAG, "Socket's listen() method failed", e);
            }
            mmServerSocket = tmp;
        }

        public void run() {

            @SuppressWarnings("UnusedAssignment") BluetoothSocket socket = null;
            // Keep listening until exception occurs or a socket is returned.
            Log.e(TAG, "Inside Run method");
            while (true) {
                try {
                    Log.i(TAG,"before accept() method");
                    socket = mmServerSocket.accept();
                    Log.i(TAG, "Socket's accepted successfully");
                    //   MainActivity.this.intentChnge();

                } catch (IOException e) {
                    Log.e(TAG, "Socket's accept() method failed", e);
                    break;
                }

                if (socket != null) {

                    Intent i=new Intent(MainActivity.this,ReceiveActivity.class);
                    StaticVariables.setSocket(socket);
                    startActivity(i);

                    break;
                }
            }
        }

    }



}
