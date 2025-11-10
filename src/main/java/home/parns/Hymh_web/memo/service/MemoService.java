package home.parns.Hymh_web.memo.service;

import home.parns.Hymh_web.memo.mapper.MemoMapper;;
import home.parns.Hymh_web.memo.vo.MemoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoMapper memoMapper;

    public List<MemoVo> getMemoList() {
        return memoMapper.getMemoList();
    }
}