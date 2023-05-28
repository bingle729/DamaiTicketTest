package com.ticket.damaitickettest;

import junit.framework.TestCase;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
//import android.support.test.InstrumentationRegistry;
//import android.support.test.runner.AndroidJUnit4;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
//import android.support.test.uiautomator.By;
//import android.support.test.uiautomator.UiDevice;
//import android.support.test.uiautomator.UiObject;
//import android.support.test.uiautomator.UiObject2;
//import android.support.test.uiautomator.UiObjectNotFoundException;
//import android.support.test.uiautomator.UiScrollable;
//import android.support.test.uiautomator.UiSelector;
//import android.support.test.uiautomator.Until;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SdkSuppress;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.Configurator;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.widget.Toast;


import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

//@RunWith(AndroidJUnit4.class)
//@SdkSuppress(minSdkVersion = 18)
public class UITest extends TestCase {
    private static final int LAUNCH_TIMEOUT = 5000;
    private static final String BASIC_SAMPLE_PACKAGE = "cn.damai";
//    @Test
//    @Before
    public void testA() throws UiObjectNotFoundException, InterruptedException {
        Configurator.getInstance().setWaitForIdleTimeout(500).setActionAcknowledgmentTimeout(1000).setKeyInjectionDelay(200);
        // 获取设备对象
//        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        UiDevice uiDevice = UiDevice.getInstance(getInstrumentation());
        // 获取上下文
//        Context context = instrumentation.getContext();
        uiDevice.pressHome();
        final String launcherPackage = getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        uiDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

        Context context = getApplicationContext();
//        final Intent intent = context.getPackageManager()
//                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);    // Clear out any previous instances
//        context.startActivity(intent);
        // 启动测试App
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        uiDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT);
        // 打开CollapsingToolbarLayout
        System.out.println("aaa == " + TimeUtils.convertLongTimeToString(System.currentTimeMillis()));
//        assertThat(str2, is(notNullValue()));
        UiObject homepagePopup = uiDevice.findObject(new UiSelector().resourceId("cn.damai:id/homepage_popup_window_close_btn"));
        if (homepagePopup != null && homepagePopup.exists()){
            UiObject closeBtn = uiDevice.findObject(new UiSelector().className("android.widget.TextView"));
            closeBtn.click();
        }
        UiObject searchText = uiDevice.findObject(new UiSelector().resourceId("cn.damai:id/channel_search_text"));
//        UiObject2 searchText = uiDevice.wait(Until.findObject(By.res("cn.damai:id/channel_search_text")), 1000 );
//        uiDevice.wait(Until.hasObject(By.res("cn.damai:id/channel_search_text")), 10000);
//        UiObject2 searchText = uiDevice.findObject(By.res("cn.damai:id/channel_search_text"));

        searchText.click();


        System.out.println("bbb == " + TimeUtils.convertLongTimeToString(System.currentTimeMillis()));
//        UiObject searchEdit = uiDevice.findObject(new UiSelector().resourceId("cn.damai:id/header_search_v2_input"));
        UiObject2 searchEdit = uiDevice.wait(Until.findObject(By.res("cn.damai:id/header_search_v2_input")), 1000 );
        System.out.println("ccc == " + TimeUtils.convertLongTimeToString(System.currentTimeMillis()));
        searchEdit.setText("DEADLINE");
        System.out.println("ddd == " + TimeUtils.convertLongTimeToString(System.currentTimeMillis()));
//        searchEdit.clickBottomRight();
        uiDevice.click(uiDevice.getDisplayWidth() - 20, uiDevice.getDisplayHeight() - 20);
        System.out.println("eee == " + TimeUtils.convertLongTimeToString(System.currentTimeMillis()));
//        UiScrollable showList = new UiScrollable(new UiSelector().resourceId("cn.damai:id/search_v2_suggest_recycler"));
        UiScrollable showList = new UiScrollable(new UiSelector().className("androidx.recyclerview.widget.RecyclerView"));
        System.out.println("count = " + showList.getChildCount());
        UiObject targetShow = showList.getChild(new UiSelector().instance(0));
        System.out.println("fff == " + TimeUtils.convertLongTimeToString(System.currentTimeMillis()));
        targetShow.click();

        UiObject knowText = uiDevice.findObject(new UiSelector().resourceId("cn.damai:id/damai_theme_dialog_cancel_btn"));
        if (knowText  != null && knowText.exists())
            knowText.click();

//        assertTrue(searchText.waitForExists(35_000));
//        searchText.setText("DEADLINE");
//        searchBtn.click();
//        uiDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "channel_search_text")),
//                500);
//        searchText.setText("梁静茹");
//        assertThat(searchText.setText(""));
//        uiDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "channel_search_text"))
//                .setText("梁静茹");
//        uiDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "homepage_header_search_btn"))
//                .click();
//        searchBtn.click();
        /*
        for (int i = 0; i < 5; i++) {
            // 向上移动
            uiDevice.swipe(uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayHeight(),
                    uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayHeight() / 2, 10);

            // 向下移动
            uiDevice.swipe(uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayHeight() / 2,
                    uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayHeight(), 10);
        }

        // 点击应用返回按钮
        UiObject back = uiDevice.findObject(new UiSelector().description("Navigate up"));
        back.click();

        // 点击设备返回按钮
        uiDevice.pressBack();
        */

        /*
        id = cn.damai:id/homepage_popup_window_close_btn
        class = android.widget.FrameLayout

        class = android.widget.TextView
        * */

    }

    private String getLauncherPackageName() {
        // Create launcher Intent
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        // Use PackageManager to get the launcher package name
        PackageManager pm = getApplicationContext().getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName;
    }




}
