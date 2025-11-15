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
    public int getMemoCount() {
        return memoMapper.getMemoCount();
    }

    @Override
    public List<MemoVo> getMemoListPaged(int start, int size) {
        return memoMapper.getMemoListPaged(start, size);
    }
}