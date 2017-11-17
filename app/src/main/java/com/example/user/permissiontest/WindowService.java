package com.example.user.permissiontest;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class WindowService extends Service {

    WindowManager mWindowManager = null;
    View mWindowLayout = null;

    @Override
    public int onStartCommand( Intent intent,
                               int flags,
                               int startId )
    {

        // 뷰 생성
        ImageView floatingView = new ImageView(this);
        floatingView.setImageResource(R.drawable.ic_launcher);

        // 윈도우 레이아웃 파라미터 생성 및 설정
        WindowManager.LayoutParams mWindowLP = new WindowManager.LayoutParams();
        mWindowLP = new WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_PHONE);
        mWindowLP.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindowLP.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindowLP.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mWindowLP.format = PixelFormat.TRANSLUCENT;

        // 윈도우 생성
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mWindowManager.addView(floatingView, mWindowLP);

        // 터치 이벤트
        floatingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "터치됨",Toast.LENGTH_SHORT).show();
            }
        });

        return super.onStartCommand( intent, flags, startId );
    }

    @Override
    public void onDestroy()
    {
        mWindowManager.removeView( mWindowLayout );
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}
