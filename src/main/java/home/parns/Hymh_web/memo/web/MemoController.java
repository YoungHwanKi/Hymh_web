package home.parns.Hymh_web.memo.web;

import home.parns.Hymh_web.memo.service.MemoService;
import home.parns.Hymh_web.memo.vo.MemoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;
    //기록장 첫페이지
    @GetMapping("/memo")
    public String memo(Model model) {
        List<MemoVo> memoList = memoService.getMemoList();
        model.addAttribute("memoList", memoList);
        
        model.addAttribute("tpl", "memo/firstPage");   // 템플릿 경로만
        model.addAttribute("frag", "content");    // 프래그먼트 이름만


        return "main/home";
    }
}
