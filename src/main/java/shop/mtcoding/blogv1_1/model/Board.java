package shop.mtcoding.blogv1_1.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
    private int id;
    private String title;
    private String content;
    private String thumbnail;
    private String userId;
    private Timestamp createdAt;
}
