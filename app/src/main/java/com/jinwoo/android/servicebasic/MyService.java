package com.jinwoo.android.servicebasic;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

public class MyService extends Service {
    private static final String TAG = "MyService";

    // 외부에 데이터를 전달하려면 bind 사용
    /*
       ---------------------------- bindService()에서 사용하는 부분 ------------------------------
     */
    // Binder 객체는 IBinder 인터페이스 구현 객체입니다

    IBinder mBinder = new MyBinder();
    class MyBinder extends Binder {
        MyService getService(){  // 서비스 객체를 리턴
            return MyService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "=========================onBind");
        // 액티비티에서 bindService()를 실행하면 호출됨
        // 리턴한 IBinder 객체는 서비스와 클라이언트 사이의 인터페이스를 정의한다.
        return mBinder;
    }

    public int getRandomNumber() { // 임의 랜덤값을 리턴하는 메서드
        return new Random().nextInt();
    }

    // -----------------------------------------------------------------------------------------




    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"===========================onCreate");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i(TAG,"========================onStartCommand");

        for(int i = 0; i<5; i++){
            Toast.makeText(getApplicationContext(), "서비스에서 동작중입니다" + i, Toast.LENGTH_SHORT).show();

        }
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "=============================onDestroy");
    }
}
