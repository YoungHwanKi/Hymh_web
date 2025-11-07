package home.parns.Hymh_web.common.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommonVo {

    /** 생성자 */
    private String crt_user;
    /** 생성일 */
    private String crtdt;
    /** 수정자 */
    private String updt_user;
    /** 수정일 */
    private String updtdt;
    /** 페이지ID */
    private String pageId;
    /** 메인페이지ID */
    private String pPageId;
    /** 버튼ID */
    private String objectId;

}