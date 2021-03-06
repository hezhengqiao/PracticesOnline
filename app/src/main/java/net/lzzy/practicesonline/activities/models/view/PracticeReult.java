package net.lzzy.practicesonline.activities.models.view;

import android.os.Bundle;

import net.lzzy.practicesonline.activities.constants.ApiConstants;
import net.lzzy.practicesonline.activities.models.Option;
import net.lzzy.practicesonline.activities.models.Question;
import net.lzzy.practicesonline.activities.models.QuestionFactory;
import net.lzzy.practicesonline.activities.models.UserCookies;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by lzzy_gxy on 2019/5/8.
 * Description:
 */
public class PracticeReult {
    /**结果*/
    private List<QuestionResult> results;
    private int id;
    private String info;
    private static final String SPLITTER=",";

    public PracticeReult(List<QuestionResult> results,int apiId,String info){
        this.id=apiId;
        this.info=info;
        this.results=results;
    }

    public List<QuestionResult> getResults() {
        return results;
    }

    public void setResults(List<QuestionResult> results) {
        this.results = results;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    private double getRatio(){
        //正确的题目数量
        int number=0;
       for (QuestionResult result:results){
            if (result.isRight()){
                number++;
            }
       }
       //总分
       return number*1.0/results.size();
    }

    private  String getWrongOrders(){
        //错误题目序号
        int i=0;
        String ids="";
           for (QuestionResult result:results){
               i++;
               if (!result.isRight()){
                   ids=ids.concat(i+SPLITTER);
               }
           }
           if (ids.endsWith(SPLITTER)){
               ids=ids.substring(0,ids.length()-1);
           }
        return ids;
    }

    public JSONObject toJson()throws JSONException {
        JSONObject json=new JSONObject();
        //调用put方法
       json.put(ApiConstants.JSON_RESULT_API_ID, id);
       json.put(ApiConstants.JSON_RESULT_PERSON_INFO, info);
       json.put(ApiConstants.JSON_RESULT_SCORE_RATIO,
               new DecimalFormat("#.00").format(getRatio()));
       json.put(ApiConstants.JSON_RESULT_WRONG_IDS,getWrongOrders());
        return json;

    }
}
