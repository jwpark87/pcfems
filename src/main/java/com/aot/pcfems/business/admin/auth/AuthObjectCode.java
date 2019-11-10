/*****************************************************************************
 * 프로그램명  : AuthorObjectCode.java
 * 설     명  : 권한레벨관리화면표시객체코드값 정의
 * 참고  사항  : 채번규정 = $패키지 태표명 + "_" +$화면사용정의텍스트 + "_T"(TEXT:T,BUTTON:B)
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2016.03.08  KYM    1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.admin.auth;

/**
 * 권한레벨관리의 화면표시객체(화면타이틀,화면객체등) 코드값 정의
 *
 * @author aot
 * @version 1.0
 */

public class AuthObjectCode {

    /*
     * 리스트화면
     */
    /**
     * 등록
     */
    public static final String AUTH_REGIST_B = "AUTH_REGIST_B";
    /**
     * 검색
     */
    public static final String AUTH_SEARCH_B = "AUTH_SEARCH_B";
    /**
     * 수정
     */
    public static final String AUTH_UPDATE_B = "AUTH_UPDATE_B";

    /**
     * == 선택 ==
     */
    public static final String AUTH_CHOOSE_T = "AUTH_CHOOSE_T";

    /*
     * 등록/수정화면
     */
    /**
     * 저장
     */
    public static final String AUTH_SAVE_B = "AUTH_SAVE_B";
    /**
     * 삭제
     */
    public static final String AUTH_DELETE_B = "AUTH_DELETE_B";
    /**
     * 닫기
     */
    public static final String AUTH_CLOSE_B = "AUTH_CLOSE_B";

}
