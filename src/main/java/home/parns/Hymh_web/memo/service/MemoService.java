package home.parns.Hymh_web.memo.service;

import home.parns.Hymh_web.memo.vo.MemoVo;
import java.util.List;

public interface MemoService {
    int getMemoCount();
    List<MemoVo> getMemoListPaged(int start, int size);
}