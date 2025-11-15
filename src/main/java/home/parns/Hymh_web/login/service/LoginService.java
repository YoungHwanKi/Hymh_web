package home.parns.Hymh_web.login.service;

import home.parns.Hymh_web.login.vo.HymhUserVo;
import home.parns.Hymh_web.login.mapper.HymhUserMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class LoginService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final HymhUserMapper hymhUserMapper;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public HymhUserVo login(String yid, String rawPassword) {

//        logger.info(">>>>>>>>>>>>>>>>"+yid);
//        logger.info(">>>>>>>>>>>>>>>>"+rawPassword);

        // 존재하지 않는
        HymhUserVo user = hymhUserMapper.findById(yid);
        if (user == null) return null;


        // 성공시
        if (user.getYpassword() != null && encoder.matches(rawPassword, user.getYpassword())) {

            if(user.getFailcnt().intValue() >= 5 ){
                // 패스워드 입력이 5회이상일 경우
                return user;
            }else {
                user.setFailcnt(0);
                user.setLastLogindt(LocalDateTime.now());
                hymhUserMapper.updateLoginInfo(user);
                return user;
            }
        }

        // 존재하지만 실패 처리
        Integer fail = user.getFailcnt() == null ? 0 : user.getFailcnt();
        user.setFailcnt(fail + 1);
        hymhUserMapper.updateLoginInfo(user);
        return null;
    }

    public int register(String yid, String rawPassword, String yname, String ytel) {
        HymhUserVo user = new HymhUserVo();
        user.setYid(yid);
        user.setYpassword(encoder.encode(rawPassword));
        user.setYprepassword(null);

        // ypasschgdt (yyyyMMdd)
        String today = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        user.setYpasschgdt(today);

        user.setFailcnt(0);
        user.setYname(yname);
        user.setYtel(ytel);
        user.setYadress(null);
        user.setStatus("0");
        user.setRegdt(LocalDateTime.now());
        user.setUpddt(null);
        user.setRegid("system");
        user.setUpdid(null);
        user.setLastLogindt(null);

        return hymhUserMapper.insertUser(user);
    }
}