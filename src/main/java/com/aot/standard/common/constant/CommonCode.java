package com.aot.standard.common.constant;

import com.aot.standard.context.init.InitWebConstantContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommonCode {
    private CommonCode() {
        throw new UnsupportedOperationException();
    }

    /**
     * <pre>
     * 이 클래스의 필드로 등록된 값들은 JSP에서 자동으로 EL 표현식으로 사용할 수 있도록 (스프링 구동시) InitWebConstantContext에서 세팅하고 있습니다.
     * 이 곳에서 상수로 정의 후 JSP에서 바로 불러 사용하시면 됩니다.
     * 예) ${PATH_JS}
     * </pre>
     */

    public static final String CONTEXT_PATH = InitWebConstantContext.getContextPath();
    public static final String PATH_RESOURCES = CONTEXT_PATH.concat("/resources");
    public static final String PATH_JS = PATH_RESOURCES.concat("/js");
    public static final String PATH_IMAGE = PATH_RESOURCES.concat("/images");
    public static final String PATH_CSS = PATH_RESOURCES.concat("/css");
    public static final String PATH_PLUGIN = PATH_RESOURCES.concat("/plugin");
    public static final String PATH_PUBLISH = PATH_RESOURCES.concat("/publish");
    public static final String PATH_ALARM_SOUND = PATH_RESOURCES.concat("/alarm");

    public static final String FILEPATH_TEXT_EDITOR = "/textEditor/";

    public static final String YES = "YES";
    public static final String NO = "NO";
    public static final String Y = "Y";
    public static final String N = "N";

    public static final String PROC_DATA_SEP = "^|";

    // CommonResponseException 에 정의된 필드와 같다.
    public static final String RESPONSE_CODE = "responseCode";
    public static final String RESPONSE_MESSAGE = "responseMessage";
    public static final String RESPONSE_DATA = "responseData";
    public static final String ADDITIONAL_MESSAGE = "additionalMessage";

    public static final String ADMIN_GROUP_LEVEL = "100";
    public static final String SUPERADMIN_GROUP_LEVEL = "000";// 슈퍼관리자 지정 유저그룹레벨
    public static final String ADMIN_KIND_USER = "0"; // 일반유저
    public static final String ADMIN_KIND_ADMIN = "1"; // 관리자
    public static final String ADMIN_KIND_SUPERADMIN = "2"; // 슈퍼관리자

    /**
     * 로그인안해도 되는 URL정의
     */
    public static final List<String> LOGIN_URL_LIST;
    /**
     * 관리자모드만 접근할수 있는 URL 정의
     */
    public static final List<String> LOGIN_ADMIN_URL_LIST;

    static {
        /* 로그인이 필요없는 화면 */
        final List<String> list = new ArrayList<>();
        list.add("/login.go");
        list.add("/forward.go");
        LOGIN_URL_LIST = Collections.unmodifiableList(list);

        /* 관리자만 접근가능 */
        final List<String> adminList = new ArrayList<>();
        LOGIN_ADMIN_URL_LIST = Collections.unmodifiableList(adminList);
    }
}
