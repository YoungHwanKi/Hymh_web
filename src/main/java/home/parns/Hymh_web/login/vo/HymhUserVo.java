package home.parns.Hymh_web.login.vo;


import lombok.Data;
import java.time.LocalDateTime;


@Data
public class HymhUserVo {
    private String yid;
    private String ypassword;
    private String yprepassword;
    private String ypasschgdt;
    private Integer failcnt;
    private String yname;
    private String ytel;
    private String yadress;
    private String status;
    private String yfiller1;
    private String yfiller2;
    private String yfiller3;
    private LocalDateTime regdt;
    private LocalDateTime upddt;
    private String regid;
    private String updid;
    private LocalDateTime lastLogindt;
}
