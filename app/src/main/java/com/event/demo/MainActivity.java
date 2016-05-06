package com.event.demo;

import de.greenrobot.event.EventBus;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity
{

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    //注册EventBus
    EventBus.getDefault().register(this);
  }

  // click--------------------------------------start----------------------
 
  
  public void methodPost(View view)
  {
    Log.d("yzy", "PostThread-->"+Thread.currentThread().getId());
    EventBus.getDefault().post(new PostEvent("PostEvent"));
  }
  
  public void methodMain(View view)
  {
    Log.d("yzy", "PostThread-->"+Thread.currentThread().getId());
    EventBus.getDefault().post(new MainEvent("MainEvent"));
  }
  
  public void methodBack(View view)
  {
    Log.d("yzy", "PostThread-->"+Thread.currentThread().getId());
    EventBus.getDefault().post(new BackEvent("BackEvent"));
  }
  
  public void methodAsync(View view)
  {
    Log.d("yzy", "PostThread-->"+Thread.currentThread().getId());
    EventBus.getDefault().post(new AsyncEvent("AsyncEvent"));
  }
  
  public void methodSubPost(View view)
  {
    new Thread()
    {
      public void run() {
        Log.d("yzy", "PostThread-->"+Thread.currentThread().getId());
        EventBus.getDefault().post(new PostEvent("PostEvent"));
      };
    }.start();
   
  }
  
  public void s(View view)
  {
    new Thread()
    {
      public void run() {
        Log.d("yzy", "PostThread-->"+Thread.currentThread().getId());
        EventBus.getDefault().post(new MainEvent("MainEvent"));
      };
    }.start();
    
  }
  
  public void methodSubBack(View view)
  {
    new Thread()
    {
      public void run() {
        Log.d("yzy", "PostThread-->"+Thread.currentThread().getId());
        EventBus.getDefault().post(new BackEvent("BackEvent"));
      };
    }.start();
   
  }
  
  public void methodSubAsync(View view)
  {
    new Thread()
    {
      public void run() {
        Log.d("yzy", "PostThread-->"+Thread.currentThread().getId());
        EventBus.getDefault().post(new AsyncEvent("AsyncEvent"));
      };
    }.start();
   
  }
  
  
  //click--------------------end------------------------------
  
  
  //Event-------------------------start-------------------------------
  /**
   * 使用onEvent来接收事件，那么接收事件和分发事件在一个线程中执行
   * @param event
   */
  public void onEvent(PostEvent event)
  {
    Log.d("yzy", "OnEvent-->"+Thread.currentThread().getId());
  }
  
  /**
   * 使用onEventMainThread来接收事件，那么不论分发事件在哪个线程运行，接收事件永远在UI线程执行，
   * 这对于android应用是非常有意义的
   * @param event
   */
  public void onEventMainThread(MainEvent event)
  {
    Log.d("yzy", "onEventMainThread-->"+Thread.currentThread().getId());
  }
  
  /**
   * 使用onEventBackgroundThread来接收事件，如果分发事件在子线程运行，那么接收事件直接在同样线程
   * 运行，如果分发事件在UI线程，那么会启动一个子线程运行接收事件
   * @param event
   */
  public void onEventBackgroundThread(BackEvent event)
  {
    Log.d("yzy", "onEventBackgroundThread-->"+Thread.currentThread().getId());
  }
  /**
   * 使用onEventAsync接收事件，无论分发事件在（UI或者子线程）哪个线程执行，接收都会在另外一个子线程执行
   * @param event
   */
  public void onEventAsync(AsyncEvent event)
  {
    Log.d("yzy", "onEventAsync-->"+Thread.currentThread().getId());
  }
  //Event------------------------------end-------------------------------------

  
  @Override
  protected void onDestroy()
  {
    //取消注册EventBus
    EventBus.getDefault().unregister(this);
    super.onDestroy();
  }
}
