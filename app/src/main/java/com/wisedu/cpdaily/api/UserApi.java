package com.wisedu.cpdaily.api;


import com.wisedu.cpdaily.model.Academy;
import com.wisedu.cpdaily.model.DepartVo;
import com.wisedu.cpdaily.model.Discover;
import com.wisedu.cpdaily.model.Statistic;
import com.wisedu.cpdaily.model.TeacherVo;
import com.wisedu.cpdaily.model.UserComplete;
import com.wisedu.cpdaily.model.Wrapper;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 用户接口
 * Created by wjj on 2017/7/3 17:51.
 */

public interface UserApi {
    //获取发现圈子列表
    @GET("v3/circles/discovery")
    Observable<Wrapper<Discover>> getDiscover();

    //获取关注列表
    @GET("v3/user/followees")
    Observable<Wrapper<List<UserComplete>>> getFollowers(
            @Query("userId") String userId,
            @Query("limits") int limits,
            @Query("offset") int offset);

    //获取校园号
    @GET("v3/campusMedias")
    Observable<Wrapper<List<UserComplete>>> getCampusMedia();

    //筛选同学
    @GET("v3/classmates")
    Observable<Wrapper<List<UserComplete>>> getClassmates(
            @Query("grade") String grade,
            @Query("academy") String academy,
            @Query("major") String major,
            @Query("sex") String sex,
            @Query("keyword") String keyword,
            @Query("limit") int limit,
            @Query("offset") int offset);

    //数据统计
    @GET("v3/user/statistics")
    Observable<Wrapper<Statistic>> getStatistic(
            @Query("userId") String userId);

    //找同学中 学院 的筛选条件列表
    @GET("v3/academyList")
    Observable<Wrapper<List<Academy>>> getAcademy();

    //部门列表
    @GET("v3/teachers/mailList")
    Observable<Wrapper<List<DepartVo>>> getDeportVo(
            @Query("departId") String departId);

    //搜索老师 (这里是limits，应该是服务器笔误)
    @GET("v3/teachers")
    Observable<Wrapper<List<TeacherVo>>> getTeacher(
            @Query("keyword") String keyword,
            @Query("departId") String departId,
            @Query("limits") int limit,
            @Query("offset") int offset);
}
