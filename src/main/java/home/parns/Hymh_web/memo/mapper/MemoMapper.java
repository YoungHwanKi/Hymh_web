package home.parns.Hymh_web.memo.mapper;

import home.parns.Hymh_web.memo.vo.MemoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemoMapper {

    List<MemoVo> getMemoList();

}
