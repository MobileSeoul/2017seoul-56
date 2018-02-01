package com.funnytoday.ddoniddoni.seoulzerobottle.Data;

/**
 * Created by Owner on 2017-09-25.
 */

public class PointData {
    public String point;
    public String userId;
    public String pointHistory;
    public String pointReuslt;
    public String pointTime;

    public PointData(){

    }
    public PointData(String point, String userId, String pointHistory, String pointReuslt, String pointTime){
        this.point = point;
        this.userId = userId;
        this.pointHistory = pointHistory;
        this.pointReuslt = pointReuslt;
        this.pointTime = pointTime;

    }

}
