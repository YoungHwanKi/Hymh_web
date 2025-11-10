package home.parns.Hymh_web.login.mapper;

import home.parns.Hymh_web.login.vo.HymhUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HymhUserMapper {
    HymhUserVo findById(@Param("yid") String userid);
    int updateLoginInfo(HymhUserVo tbYuser);
    int insertUser(HymhUserVo tbYuser);
}
