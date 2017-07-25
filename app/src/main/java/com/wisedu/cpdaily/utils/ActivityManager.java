/*
 * Copyright (c) 2017. wisedu.com
 */

package com.wisedu.cpdaily.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * activity堆栈管理
 */
public class ActivityManager {

    public final static String TAG = "ActivityManager";
    private static Stack<Activity> activityStack;
    private static ActivityManager instance;

    private ActivityManager() {
    }

    /**
     * 单一实例
     */
    public static synchronized ActivityManager getInstance() {
        if (instance == null) {
            synchronized (ActivityManager.class) {
                if (instance == null) {
                    instance = new ActivityManager();
                    activityStack = new Stack<>();
                }
            }
        }
        return instance;
    }

    /**
     * 获取指定的Activity
     */
    public static Activity findActivity(Class<?> cls) {
        if (activityStack != null) {
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    return activity;
                }
            }
        }
        return null;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        LogUtils.d(TAG, "add " + activity.toString());
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        if (activityStack.size() > 0)
            return activityStack.lastElement();
        return null;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishCurrentActivity() {
        if (activityStack.size() > 0) {
            finishActivity(activityStack.lastElement());
        }
    }

    /**
     * 结束指定的Activity,并从堆栈删除,堆栈内容变化了
     */
    private void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);//删除引用，避免内存泄露
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {

        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivities() {

        //有 length=2 index=2的bug
//        for (int i = 0, size = activityStack.size(); i < size; i++) {//要求堆栈内容不变
//            if (null != activityStack.get(i)) {
//                finishActivity(activityStack.get(i));//堆栈内容变化了
//            }
//        }
//        activityStack.clear();

        while (activityStack.size() > 0) {
            Activity item = activityStack.pop();
            if (null != item && !item.isFinishing()) {
                item.finish();//不会立即触发onDestroy中的remove(this)。如果立即触发，也只是no need
            }
        }
    }

    /**
     * 结束栈前的Activity
     */
    public void finishBeforeActivities() {
        if (activityStack.size() > 0) {
            Activity current = activityStack.pop();
            //1、要求 遍历过程中，activityStack中数目保持不变
//            for (int i = 0, size = activityStack.size(); i < size; i++) {
//                Activity item = activityStack.elementAt(i);
//                if (null != item) {
//                    item.finish();//不会立即触发onDestroy中的remove(this)。如果立即触发，导致内容改变，则有length=2
// index=2的bug了
//                }
//            }
//            activityStack.clear();//一次性清空

            //2、不要求 遍历过程中，activityStack中内容保持不变
            while (activityStack.size() > 0) {
                Activity item = activityStack.pop();
                if (null != item && !item.isFinishing()) {
                    item.finish();//不会立即触发onDestroy中的remove(this)。如果立即触发，也只是no need
                }
            }
            //结论：由于不会立即触发onDestroy中的remove(this)，2种方法均可，第2种更加健壮些。
            activityStack.add(current);
        }
    }

    /**
     * 退出应用程序
     */
    public void exitApp() {
        finishAllActivities();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);

    }

    /**
     * 及时删除引用，避免内存泄露
     *
     * @param activity
     */
    public void remove(Activity activity) {
        if (activityStack.contains(activity)) {
            activityStack.remove(activity);
            LogUtils.d(TAG, "remove " + activity.toString() + " success");
        } else {
            LogUtils.d(TAG, "remove " + activity.toString() + " no need");
        }
    }
}