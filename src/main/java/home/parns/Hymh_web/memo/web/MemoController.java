package home.parns.Hymh_web.memo.controller;

import home.parns.Hymh_web.memo.service.MemoService;
import home.parns.Hymh_web.memo.vo.MemoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @GetMapping("/memo")
    public String memo(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model
    ) {
        int totalCount = memoService.getMemoCount();
        int totalPages = (int) Math.ceil((double) totalCount / size);
        int start = (page - 1) * size;

        List<MemoVo> memoList = memoService.getMemoListPaged(start, size);

        model.addAttribute("memoList", memoList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("size", size);

        model.addAttribute("tpl", "memo/firstPage");
        model.addAttribute("frag", "content");
        return "main/home";
    }
}