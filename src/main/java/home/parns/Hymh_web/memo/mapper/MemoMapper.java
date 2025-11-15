package home.parns.Hymh_web.memo.mapper;

import home.parns.Hymh_web.memo.vo.MemoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemoMapper {
    int getMemoCount();
    List<MemoVo> getMemoListPaged(@Param("start") int start, @Param("size") int size);
}
