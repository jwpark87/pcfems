/*****************************************************************************
 * 프로그램명  : CodeTableMng.java
 * 설     명  : 그룹별코드테이블관리 공통부품
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.11.04  LYS    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.common.code;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.CodeInfo;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.spring.code.CodeService;
import com.aot.standard.common.util.AotAccessBeanUtils;
import com.aot.standard.common.util.AotMapperUtils;
import com.google.gson.JsonElement;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 그룹별코드테이블 데이터관리（코드테이블,회사정보용）Bean
 *
 * @author eaction
 * @version 1.0
 */

/**
 * @author user
 *
 */
@Component
public class CodeTableMng {
    private static final Logger LOGGER = LoggerFactory.getLogger(CodeTableMng.class);
    // @Value("#{enviromentProperties['LANGUAGE']}")
    // private static String LANGUAGE;

    // public static String getName(final UserVO user, final String group, final String key) throws UserSysException {
    // // 2017.01.16 언어설정 변경 E(명시적 선택으로 변경)
    // for (CodeInfo codeInfo : getCodeListByGrcode(user, group)) {
    // if (codeInfo.getCode().equals(key)) {
    // // Locale에 따른 이미지 루트패스 취득
    // if ("KOR".equals(LANGUAGE)) {
    // return codeInfo.getCodenm_k();
    // } else if ("ENG".equals(LANGUAGE)) {
    // return codeInfo.getCodenm_e();
    // } else if ("JPN".equals(LANGUAGE)) {
    // return codeInfo.getCodenm_j();
    // } else {
    // return codeInfo.getName();
    // }
    // }
    // }
    // return "";
    // }

    public static List<CodeInfo> getCodeListByGrcode(final UserVO userVO, final String group) throws UserSysException {
        final List<CodeInfo> codeListByGrcode = getCodeListByGrcode(group);
        final List<CodeInfo> result = new ArrayList<>();
        for (final CodeInfo codeInfo : codeListByGrcode) {
            if (StringUtils.isNotEmpty(userVO.getUser_lang()) && codeInfo.getLevelcod().intValue() >= Integer.parseInt(StringUtils.defaultIfEmpty(userVO.getGroupLevelCod(), "101"))) {
                if (userVO.getUser_lang().equals("KOR")) {
                    codeInfo.setName(codeInfo.getCodenm_k());
                } else if (userVO.getUser_lang().equals("ENG")) {
                    codeInfo.setName(codeInfo.getCodenm_e());
                } else if (userVO.getUser_lang().equals("JPN")) {
                    codeInfo.setName(codeInfo.getCodenm_j());
                } else {
                    codeInfo.setName(codeInfo.getCodenm_k());
                }
                if (StringUtils.isEmpty(codeInfo.getName())) {
                    codeInfo.setName(codeInfo.getCodenm());
                }
            } else {
                if (codeInfo.getLevelcod().intValue() >= Integer.parseInt(StringUtils.defaultIfEmpty(userVO.getGroupLevelCod(), "101"))) {
                    codeInfo.setName(codeInfo.getCodenm_k());
                    if (StringUtils.isEmpty(codeInfo.getName())) {
                        codeInfo.setName(codeInfo.getCodenm());
                    }
                }
            }
            result.add(codeInfo);
        }
        LOGGER.debug("{} : {}", group, AotMapperUtils.writeObjectAsString(result));
        return result;

    }

    public static JsonElement getCodeListToJsonElement(final UserVO user, final String group) throws UserSysException {
        return AotMapperUtils.writeObjectAsJsonElement(getCodeListByGrcode(user, group));
    }

    /**
     * 키에 대한 값을 취득(코드명칭)
     *
     * @param user
     *            유저정보
     * @param group
     *            그룹코드
     * @param key
     *            키값
     * @return strReturn 키에대한 값
     * @throws UserSysException
     */
    public static String getName(final UserVO user, final String group, final String key) throws UserSysException {
        final CodeInfo coderesult = new CodeInfo();
        for (final CodeInfo codeInfo : getCodeListByGrcode(user, group)) {
            if (StringUtils.equals(key, codeInfo.getCode())) {
                if (StringUtils.isNotEmpty(user.getUser_lang())) {
                    if (user.getUser_lang().equals("KOR")) {
                        coderesult.setName(codeInfo.getCodenm_k());
                    } else if (user.getUser_lang().equals("ENG")) {
                        coderesult.setName(codeInfo.getCodenm_e());
                    } else if (user.getUser_lang().equals("JPN")) {
                        coderesult.setName(codeInfo.getCodenm_j());
                    } else {
                        coderesult.setName(codeInfo.getCodenm_k());
                    }
                } else {
                    coderesult.setName(codeInfo.getCodenm_k());
                    if (StringUtils.isEmpty(codeInfo.getName())) {
                        coderesult.setName(codeInfo.getCodenm());
                    }

                }
            }
        }
        return coderesult.getName();
    }

    /**
     * 키에 대한 값을 취득(코드명칭) - 언어에 상관없이 codenm 만을 취득
     *
     * @param user
     *            유저정보
     * @param group
     *            그룹코드
     * @param key
     *            키값
     * @return strReturn 키에대한 값
     * @throws UserSysException
     */
    public static String getCodeNameNotLang(final UserVO user, final String group, final String key) throws UserSysException {
        final CodeInfo coderesult = new CodeInfo();
        for (final CodeInfo codeInfo : getCodeListByGrcode(user, group)) {
            if (!"".equals(codeInfo.getCodenm_k()) && key.equals(codeInfo.getCode())) {
                coderesult.setName(codeInfo.getCodenm_k());
            } else if ("".equals(codeInfo.getCodenm_k()) && key.equals(codeInfo.getCode())) {
                coderesult.setName(codeInfo.getCodenm());
            }
        }
        return coderesult.getName();
    }

    public static List<CodeInfo> getCodeListByGrcode(final String grcode) throws UserSysException {
        return AotAccessBeanUtils.getBean(CodeService.class).getCodeListByGrcode(grcode);
    }

    @SuppressWarnings("unchecked")
    public static List<CodeInfo> getCodeListByGrcode(final String grcode, final HttpSession session) throws UserSysException {
        if (session.getAttribute("getCodeListByGrcode." + grcode) == null) {
            session.setAttribute("getCodeListByGrcode." + grcode, getCodeListByGrcode(grcode));
            LOGGER.debug("save ==> getCodeListByGrcode.{}.", grcode);
        }
        LOGGER.debug("cache ==> getCodeListByGrcode.{}.", grcode);
        return (List<CodeInfo>) session.getAttribute("getCodeListByGrcode." + grcode);
    }

    public static List<CodeInfo> getCodeListByGrcode(final UserVO userVO, final String grcode, final HttpSession session) throws UserSysException {
        final List<CodeInfo> codeList = getCodeListByGrcode(grcode, session);
        final List<CodeInfo> reseult = new ArrayList<>();
        try {
            for (final CodeInfo codeInfo : codeList) {
                if (codeInfo.getLevelcod().intValue() >= Integer.parseInt(StringUtils.defaultIfEmpty(userVO.getGroupLevelCod(), "101"))) {
                    reseult.add(codeInfo);
                }
            }
        } catch (final Throwable e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            session.removeAttribute("getCodeListByGrcode." + grcode);
        }
        return reseult;
    }

}
