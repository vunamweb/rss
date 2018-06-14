package com.example.nvu7.readrss.core.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.nvu7.readrss.common.Constants;

/**
 * Created by Administrator on 6/8/2018.
 */

public class Goto {
  public static void startActivity(Context context, Class to, Bundle bundle)
  {
      Intent intent=new Intent(context, to);
      intent.putExtra(Constants.INTENT_DATA,bundle);
      context.startActivity(intent);
  }
}
