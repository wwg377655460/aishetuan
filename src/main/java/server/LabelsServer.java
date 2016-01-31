package server;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import entry.Article;
import entry.Label;
import entry.Project;
import exception.AuthException;
import org.nutz.json.Json;

import java.util.List;

/**
 * Created by wsdevotion on 16/1/30.
 */
public class LabelsServer extends MainServer {

    //按热度获取标签列表
    public List<Label> getLabelsByFollowNum(String start_num, String num) throws AuthException {
        //对参数字段进行验证
        boolean isNum1 = start_num.matches("[0-9]+");
        boolean isNum2 = num.matches("[0-9]+");
        if (!isNum1 || !isNum2) {
            throw new AuthException("-1");
        }
        //转换成整形
        int start = Integer.parseInt(start_num);
        int number = Integer.parseInt(num);
        //获取热度信息
        return getLabelDao().getLabelsByFollowNum(start, number);
    }

    //获取标签信息
    public String getLabelById(String id) throws AuthException {
        //判断id类型
        boolean isNum1 = id.matches("[0-9]+");
        if (!isNum1) {
            throw new AuthException("-1");
        }
        int idn = Integer.parseInt(id);
        Label label = getLabelDao().get(idn);
        //获取对应的文章信息
        Article article = null;
        if(label.getLable_article_id() != null) {
            article = getArticleDao().get(Integer.parseInt(label.getLable_article_id()));
            if (label == null || article == null) {
                throw new AuthException("-1");
            }
        }
        //获取对应的项目信息
        List<Project> list = getProjectDao().getProjectByLabelId(label.getLable_id());
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(label);
        JSONObject jsonObject1 = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        long time = 0;
        for (Project project : list) {
            time = Long.parseLong(project.getProject_end_time()) - Long.parseLong(project.getProject_pub_time());
            jsonObject1.put("project_id", project.getProject_id());
            jsonObject1.put("project_image", project.getProject_art_img());
            jsonObject1.put("project_name", project.getProject_name());
            jsonObject1.put("project_author", project.getProject_author());
            jsonObject1.put("project_money_goal", project.getProject_money_goal());
            jsonObject1.put("time", time);
            jsonArray.add(jsonObject1);
            jsonArray = JSONArray.parseArray(jsonArray.toString());
            jsonObject1.clear();
        }

        jsonObject.put("items", jsonArray);
        if (article != null){
            jsonObject.put("article_img", article.getArticle_img());
            jsonObject.put("article_url", article.getArticle_url());
            jsonObject.put("article_title", article.getArticle_title());
        } else {
            jsonObject.put("article_img", "");
            jsonObject.put("article_url", "");
            jsonObject.put("article_title", "");
        }


        jsonObject1.put("data", jsonObject);
        jsonObject1.put("status", 1);
        return jsonObject1.toJSONString();


    }
}
