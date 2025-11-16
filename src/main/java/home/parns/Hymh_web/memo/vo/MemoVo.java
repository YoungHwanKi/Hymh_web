package home.parns.Hymh_web.memo.vo;

import lombok.Data;

@Data
public class MemoVo {

    private Long seq;
    private String title;
    private String memo;
    private String memoType;
    private String useCl;
    private String publicLevel;
    private String useType;
    private Integer depth;
    private Long superSeq;
    private String yid;
    private String yname;
    private String regdt;
    private String upddt;

}
