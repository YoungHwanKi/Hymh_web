package home.parns.Hymh_web.memo.service;

import home.parns.Hymh_web.memo.vo.MemoVo;
import java.util.List;

public interface MemoService {
    int getMemoCount(String searchType, String keyword, String startDate, String endDate);

    List<MemoVo> getMemoListPaged(int start, int size,
                                  String searchType, String keyword,
                                  String startDate, String endDate);

    MemoVo getMemoDetail(long seq);
}