package home.parns.Hymh_web.memo.service;

import home.parns.Hymh_web.memo.mapper.MemoMapper;
import home.parns.Hymh_web.memo.vo.MemoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {

    private final MemoMapper memoMapper;

    @Override
    public int getMemoCount(String searchType, String keyword, String startDate, String endDate) {
        return memoMapper.getMemoCount(searchType, keyword, startDate, endDate);
    }

    @Override
    public List<MemoVo> getMemoListPaged(int start, int size,
                                         String searchType, String keyword,
                                         String startDate, String endDate) {
        return memoMapper.getMemoListPaged(start, size, searchType, keyword, startDate, endDate);
    }

    public MemoVo getMemoDetail(long seq) {
        return memoMapper.getMemoDetail(seq);
    }

    @Override
    public int updateMemo(MemoVo memoVo) {
       return memoMapper.updateMemo(memoVo);
    }

    @Override
    public int insertMemo(MemoVo memoVo) {
        return memoMapper.insertMemo(memoVo);
    }
}