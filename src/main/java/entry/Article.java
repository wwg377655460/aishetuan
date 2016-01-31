package entry;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by wsdevotion on 16/1/30.
 */
@Table("article_table")
public class Article {

    @Id
    private int article_id;

    @Column
    private String article_img;

    @Column
    private String article_title;

    //公众号等的文章链接
    @Column
    private String article_url;

    @Column
    private int article_like_number;

    @Column
    private String article_pub_time;

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public String getArticle_img() {
        return article_img;
    }

    public void setArticle_img(String article_img) {
        this.article_img = article_img;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_url() {
        return article_url;
    }

    public void setArticle_url(String article_url) {
        this.article_url = article_url;
    }

    public int getArticle_like_number() {
        return article_like_number;
    }

    public void setArticle_like_number(int article_like_number) {
        this.article_like_number = article_like_number;
    }

    public String getArticle_pub_time() {
        return article_pub_time;
    }

    public void setArticle_pub_time(String article_pub_time) {
        this.article_pub_time = article_pub_time;
    }
}
