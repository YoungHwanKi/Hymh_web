package home.parns.Hymh_web.memo.mapper;

import home.parns.Hymh_web.memo.vo.MemoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemoMapper {

    int getMemoCount(@Param("searchType") String searchType,
                     @Param("keyword") String keyword,
                     @Param("startDate") String startDate,
                     @Param("endDate") String endDate);

    List<MemoVo> getMemoListPaged(@Param("start") int start,
                                  @Param("size") int size,
                                  @Param("searchType") String searchType,
                                  @Param("keyword") String keyword,
                                  @Param("startDate") String startDate,
                                  @Param("endDate") String endDate);

    MemoVo getMemoDetail(long seq);

    int updateMemo(MemoVo memoVo);

    int insertMemo(MemoVo memoVo);
}