package shop.mtcoding.blogv1_1.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.blogv1_1.dto.reply.ReplyResp.ReplyAllRespDto;

@Mapper
public interface ReplyRepository {
    public List<Reply> findAll();

    public List<ReplyAllRespDto> findByBoardIdwithUser(int boardId);

    public Reply findById(int id);

    public int insert(@Param("comment") String comment, @Param("boardId") int boardId, @Param("userId") int userId);

    public int updateById(@Param("id") int id, @Param("comment") String comment);

    public int deleteById(int id);
}