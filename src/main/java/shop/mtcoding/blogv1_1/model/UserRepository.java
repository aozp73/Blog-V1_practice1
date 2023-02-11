package shop.mtcoding.blogv1_1.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {
    public int insert();

    public List<Board> findByAll();

    public Board findById(int id);

    public int updateById(@Param("id") int id, @Param("password") String password, @Param("email") String email);

    public int deleteById(int id);
}
