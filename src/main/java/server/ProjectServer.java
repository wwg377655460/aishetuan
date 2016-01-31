package server;

import entry.Project;
import exception.AuthException;

import java.util.List;

/**
 * Created by wsdevotion on 16/1/30.
 */
public class ProjectServer extends MainServer {

    /**
     * 获取按顺序项目信息
     * @param page 页数
     * @return
     * @throws AuthException 传输的信息不正确
     */
    public List<Project> getProjectByPage(String page) throws AuthException {
        //判断page类型
        boolean isNum1 = page.matches("[0-9]+");
        if (!isNum1) {
            throw new AuthException("-1");
        }

        //转换成整形
        int p = Integer.parseInt(page);
        //获取信息
        return getProjectDao().getProjectByPage(p);
    }


    public Project getProjectById(String id) throws AuthException {
        //判断id类型
        boolean isNum1 = id.matches("[0-9]+");
        if (!isNum1) {
            throw new AuthException("-1");
        }
        //转换成整形
        int idn = Integer.parseInt(id);
        //获取信息
        Project project = getProjectDao().get(idn);
        if (project == null) {
            throw new AuthException("-1");
        }
        return project;
    }
}
