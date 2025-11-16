package home.parns.Hymh_web.memo.web;

import home.parns.Hymh_web.memo.service.MemoService;
import home.parns.Hymh_web.memo.vo.MemoVo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemoController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MemoService memoService;

    // 메모 리스트 + 페이징
    @GetMapping("/memo")
    public String memoList(@RequestParam(defaultValue = "1") int page,
                           @RequestParam(defaultValue = "10") int size,
                           @RequestParam(required = false) String searchType,
                           @RequestParam(required = false) String keyword,
                           @RequestParam(required = false) String startDate,
                           @RequestParam(required = false) String endDate,
                           Model model) {

        // 빈 문자열 체크 → null 처리
        if (keyword != null && keyword.trim().isEmpty()) keyword = null;
        if (searchType != null && searchType.trim().isEmpty()) searchType = null;
        if (startDate != null && startDate.trim().isEmpty()) startDate = null;
        if (endDate != null && endDate.trim().isEmpty()) endDate = null;

        logger.info("SearchType = {}", searchType);
        logger.info("Keyword    = {}", keyword);

        // 총 개수 조회
        int totalCount = memoService.getMemoCount(searchType, keyword, startDate, endDate);

        // 총 페이지 수 계산
        int totalPages = (int) Math.ceil((double) totalCount / size);
        if (totalPages < 1) totalPages = 1;

        // 현재 페이지 보정
        if (page < 1) page = 1;
        if (page > totalPages) page = totalPages;

        // LIMIT 시작 위치
        int start = (page - 1) * size;

        // 리스트 조회
        List<MemoVo> memoList = memoService.getMemoListPaged(
                start, size, searchType, keyword, startDate, endDate
        );

        /* ============================================
         *   📌 페이지 번호(5개만 보이도록) 계산
         * ============================================ */
        int pageDisplaySize = 5;  // 5개씩 보여줄 것

        int currentBlock = (page - 1) / pageDisplaySize;  // 현재 블록 번호
        int startPage = currentBlock * pageDisplaySize + 1;
        int endPage = startPage + pageDisplaySize - 1;

        if (endPage > totalPages) {
            endPage = totalPages;
        }

        /* ============================================
         *       Model 에 데이터 담기
         * ============================================ */
        model.addAttribute("memoList", memoList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("size", size);

        // ⭐ 페이지 범위 추가
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        // 검색조건 유지
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        model.addAttribute("tpl", "memo/firstPage");
        model.addAttribute("frag", "content");

        return "main/home";
    }



    @GetMapping("/memo/detail")
    @ResponseBody
    public MemoVo memoDetail(@RequestParam Long seq) {
        return memoService.getMemoDetail(seq);
    }
}