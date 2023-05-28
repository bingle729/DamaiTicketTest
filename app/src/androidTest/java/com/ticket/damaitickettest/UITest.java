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
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.view.KeyEvent;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.Test;
import org.junit.runner.RunWith;

//@RunWith(AndroidJUnit4.class)
public class UITest extends TestCase {
    private static final int LAUNCH_TIMEOUT = 5000;
    private static final String BASIC_SAMPLE_PACKAGE = "cn.damai";
//    @Test
    public void testA() throws UiObjectNotFoundException, InterruptedException {
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

        // 打开CollapsingToolbarLayout
        String resourceId = "cn.damai:id/homepage_header_search_btn";
        UiObject collapsingToolbarLayout = uiDevice.findObject(new UiSelector().resourceId(resourceId));
        UiObject searchText = uiDevice.findObject(new UiSelector().resourceId("cn.damai:id/channel_search_text"));
        UiObject searchBtn = uiDevice.findObject(new UiSelector().resourceId("cn.damai:id/homepage_header_search_btn"));
        searchText.click();
        UiObject searchEdit = uiDevice.findObject(new UiSelector().resourceId("cn.damai:id/header_search_v2_input"));

        searchEdit.setText("梁静茹 武汉站");
//        searchEdit.clickBottomRight();
        uiDevice.click(uiDevice.getDisplayWidth() - 20, uiDevice.getDisplayHeight() - 20);

//        UiScrollable showList = new UiScrollable(new UiSelector().resourceId("cn.damai:id/search_v2_suggest_recycler"));
        UiScrollable showList = new UiScrollable(new UiSelector().className("androidx.recyclerview.widget.RecyclerView"));
        System.out.println("count = " + showList.getChildCount());
        UiObject targetShow = showList.getChild(new UiSelector().instance(0));

        targetShow.click();

        UiObject knowText = uiDevice.findObject(new UiSelector().resourceId("cn.damai:id/damai_theme_dialog_cancel_btn"));
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
