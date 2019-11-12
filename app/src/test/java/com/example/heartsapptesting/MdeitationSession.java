package com.example.heartsapptesting;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
public class MdeitationSession {

    public AndroidDriver<AndroidElement> driver1, driver2;

    public AndroidDriver<AndroidElement> createAndroidDriver (String deviceName,
                                                              String appPackage,
                                                              String appActivity,
                                                              String appiumHost)throws MalformedURLException{
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("deviceName", deviceName);
        dc.setCapability("platformName", "Android");
        dc.setCapability("appPackage", appPackage);
        dc.setCapability("appActivity", appActivity);
        // It will launch Dialer app in android emulator-device.
        return new AndroidDriver<AndroidElement>(new URL(appiumHost),dc);

    }

    public void delay(int sec){
        driver1.getOrientation(); // execute some command to keep the session alive
        driver2.getOrientation(); // execute some command to keep the session alive
        while(sec > 0){
            int sleepTime = Math.min(59,sec);
            sec = sec - sleepTime;
            try{Thread.sleep(sleepTime*1000);}catch(InterruptedException e){System.out.println(e);}
            driver1.getOrientation(); // execute some command to keep the session alive
            driver2.getOrientation(); // execute some command to keep the session alive
        }
    }


    public void hfnLoginEmail(AndroidDriver<AndroidElement> driver, String username, String password){
        driver.findElementById("com.hfn.unified:id/btn_wel_sign_in").click();
        delay(2);
        driver.findElementById("com.hfn.unified:id/checkbox_policy").click();
        delay(2);
        //driver.findElementByXPath("//android.widget.LinearLayout[3]/android.widget.FrameLayout/android.widget.ImageView").click();
        driver.findElementById("com.hfn.unified:id/btn_hfn_signin").click();
        delay(2);
        driver.findElementById("com.hfn.unified:id/signin_email").sendKeys(username);
        driver.findElementById("com.hfn.unified:id/signin_password").click();
        driver.findElementById("com.hfn.unified:id/signin_password").sendKeys(password);
        if(driver.isKeyboardShown()) { driver.hideKeyboard(); }
        //driver1.findElements(By.xpath("//android.widget.Button")).get(0).click();
        driver.findElementById("com.hfn.unified:id/btn_sign_in").click();

    }

    public void hfnDoNotDisturb(AndroidDriver<AndroidElement> driver){
        //driver.findElementById("android:id/button3").click();
        driver.findElementsByXPath("//android.widget.Button").get(0).click();
    }

    public void hfnMeditateNow(AndroidDriver<AndroidElement> driver){
        driver.findElementById("com.hfn.unified:id/btn_view_more_meditation").click();
        //driver.findElementById("com.hfn.unified:id/btn_view_more_meditation").click();
        //driver.findElementByXPath("//hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView").click();
        delay(5);
        driver.findElementById("com.hfn.unified:id/btn_start").click();
        delay(5);
        driver.findElementById("com.hfn.unified:id/btn_end").click();
    }

    public void hfnMakePreceptorAvailable(AndroidDriver<AndroidElement> driver){
        driver.findElementById("com.hfn.unified:id/profile_image").click();
        //driver.findElementsByXPath("//android.widget.ImageView").get(1).click();
        delay(5);
        driver.findElementById("com.hfn.unified:id/switch_status").click();
        // wait till notification disapper
        delay(15);
        driver.findElementByXPath("//android.widget.ImageButton").click();
//        "com.hfn.unified:id/btn_accept";
//        "com.hfn.unified:id/btn_cancle";
//        "com.hfn.unified:id/btn_start_now";
//        "com.hfn.unified:id/btn_end_session";
//        "com.hfn.unified:id/btn_end"; "Yes"
//        "com.hfn.unified:id/btn_continue"; "No"
//        "com.hfn.unified:id/edittext_chat_box";
//        "com.hfn.unified:id/btn_send";
//        "com.hfn.unified:id/nav_exit_session";

        //driver.findElementByXPath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]").click();
    }

    public void hfnAbhyasiWaitingForAcceptedSession(AndroidDriver<AndroidElement> driver){
        while (driver.findElements( By.id("com.hfn.unified:id/edittext_chat_box") ).size() == 0){
            delay(2);
            System.out.println("Waiting for trainer");
        }
        driver.findElementById("com.hfn.unified:id/edittext_chat_box").sendKeys("Hi");
        driver.findElementById("com.hfn.unified:id/btn_send").click();
    }

    public void hfnPreceptorWaitingForAcceptSession(AndroidDriver<AndroidElement> driver){
        while (driver.findElements( By.id("com.hfn.unified:id/btn_accept") ).size() == 0){
            delay(2);
            System.out.println("Waiting for abhyasi request ...");
        }

        driver.findElementById("com.hfn.unified:id/btn_accept").click();
    }


    public void hfnPreceptorChatAndStartSession(AndroidDriver<AndroidElement> driver){
        driver.findElementById("com.hfn.unified:id/edittext_chat_box").sendKeys("Hello");
        delay(2);
        driver.findElementById("com.hfn.unified:id/btn_send").click();
        delay(10);
        driver.findElementById("com.hfn.unified:id/btn_start_now").click();
    }

    public void hfnPreceptorEndSession(AndroidDriver<AndroidElement> driver){
        int count = 0;
        while (count < 30){
            delay(2);
            System.out.println("Session in progress ..");
            if(driver.findElements( By.id("com.hfn.unified:id/btn_end_session") ).size() == 0 )
                System.out.println("End Session not available");
            else
                System.out.println("End Session is available now");
            count++;
        }
        driver.findElementById("com.hfn.unified:id/btn_end_session").click();
        delay(2);
        driver.findElementById("com.hfn.unified:id/btn_end").click();
        delay(1);
        driver.findElementById("com.hfn.unified:id/edittext_chat_box").sendKeys("Hello");
        driver.findElementById("com.hfn.unified:id/btn_send").click();
        delay(2);
        driver.findElementById("com.hfn.unified:id/nav_exit_session").click();
    }

    public void hfnTakeMeHome(AndroidDriver<AndroidElement> driver){
        driver.findElementById("com.hfn.unified:id/btn_home").click();
    }

    public void hfnWriteDiary(AndroidDriver<AndroidElement> driver){
        driver.findElementById("com.hfn.unified:id/btn_dairy").click();
        delay(2);
        driver.findElementById("com.hfn.unified:id/fab_edit").click();
        delay(2);
        driver.findElementById("com.hfn.unified:id/fab_add").click();
        delay(2);
        driver.findElementById("com.hfn.unified:id/text_body").sendKeys("Blissful");
        delay(2);
        driver.findElementById("com.hfn.unified:id/nav_save").click();
        delay(5);
        driver.findElementByXPath("//android.widget.ImageButton").click();
    }

    @Before
    public void setUp() throws MalformedURLException {


         driver1 = createAndroidDriver("emulator-5556",
                 "com.hfn.unified",
                 "com.hfn.unified.view.splash.SplashActivity",
                 "http://127.0.0.1:4725/wd/hub");
        driver2 = createAndroidDriver("emulator-5554",
                "com.hfn.unified",
                "com.hfn.unified.view.splash.SplashActivity",
                "http://127.0.0.1:4723/wd/hub");
        delay(10);

    }

    @Test
    public void prefectMeditationSession() {

        hfnLoginEmail(driver1, "preceptor.2@mailinator.com", "preceptor");
        hfnLoginEmail(driver2, "abhyasi.1@mailinator.com", "abhyasi");
        delay(15);
        hfnDoNotDisturb(driver1);
        hfnDoNotDisturb(driver2);
        hfnMakePreceptorAvailable(driver1);
        delay(5);
        hfnMeditateNow(driver2);
        delay(5);
        hfnPreceptorWaitingForAcceptSession(driver1);
        delay(5);
        hfnAbhyasiWaitingForAcceptedSession(driver2);
        delay(5);
        hfnPreceptorChatAndStartSession(driver1);
        hfnPreceptorEndSession(driver1);
        hfnTakeMeHome(driver1);
        delay(5);
        driver2.findElementById("com.hfn.unified:id/nav_exit_session").click();
        hfnWriteDiary(driver2);
        delay(5);
        hfnTakeMeHome(driver2);
        delay(20);
    }



    @After
    public void End() {
        driver1.quit();
        driver2.quit();

    }
}