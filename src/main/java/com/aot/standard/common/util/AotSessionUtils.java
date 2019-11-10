package com.aot.standard.common.util;

import com.aot.pcfems.common.model.UserVO;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

public class AotSessionUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(AotSessionUtils.class);
    public static final String SESSION_VALUE_OF_LOGIN_VO = "loginedUserVO";

    private AotSessionUtils() {
        throw new UnsupportedOperationException();
    }

    public static boolean isLogined(final HttpSession session) {
        return getUserVO(session) != null && StringUtils.isNotEmpty(getUserVO(session).getUser_id());
    }

    public static UserVO getUserVO(final HttpSession session) {
        try {
            return (UserVO) session.getAttribute(SESSION_VALUE_OF_LOGIN_VO);
        } catch (final Exception e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    public static void setUserVO(final HttpSession session, final UserVO loginVO) {
        session.setAttribute(SESSION_VALUE_OF_LOGIN_VO, loginVO);
    }

    public static void logout(final HttpSession session) {
        final JsonObject result = new JsonObject();
        try {
            for (final Enumeration<String> attributeNames = session.getAttributeNames(); attributeNames.hasMoreElements(); ) {
                final String nextElement = attributeNames.nextElement();
                result.addProperty(nextElement, AotMapperUtils.writeObjectAsString(session.getAttribute(nextElement)));
                session.removeAttribute(nextElement);
            }
            LOGGER.debug("(because logout)remove all session's attirubet : {}", result.toString());
        } catch (final Exception e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
        }
    }

    public static boolean hasAttribute(final HttpSession httpSession, final String name) {
        try {
            return httpSession.getAttribute(name) != null;
        } catch (final Exception e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            return false;
        }
    }

    public static String getAttribute(final HttpSession session, final String name) {
        try {
            return (String) session.getAttribute(name);
        } catch (final Exception e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    public static void setAttribute(final HttpSession session, final String name, final Object value) {
        session.setAttribute(name, value);
    }

    public static Integer getAttributeInteger(final HttpSession session, final String name) {
        try {
            return (Integer) session.getAttribute(name);
        } catch (final Exception e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    public static Object getAttributeObject(final HttpSession session, final String name) {
        try {
            return session.getAttribute(name);
        } catch (final Exception e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getAttributeObject(final HttpSession session, final String name, final Class<T> returnType) {
        try {
            return (T) session.getAttribute(name);
        } catch (final Exception e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    public static void removeAttribute(final HttpSession session, final String name) {
        session.removeAttribute(name);
    }

    public static void printAttributeList(final HttpSession session) {
        final JsonObject result = new JsonObject();
        try {
            for (final Enumeration<String> attributeNames = session.getAttributeNames(); attributeNames.hasMoreElements(); ) {
                final String nextElement = attributeNames.nextElement();
                result.addProperty(nextElement, AotMapperUtils.writeObjectAsString(session.getAttribute(nextElement)));
            }
            LOGGER.debug(result.toString());
        } catch (final Exception e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
        }
    }

    public static JsonObject getAttributeJson(final HttpSession session) {
        final JsonObject result = new JsonObject();
        try {
            for (final Enumeration<String> attributeNames = session.getAttributeNames(); attributeNames.hasMoreElements(); ) {
                final String nextElement = attributeNames.nextElement();
                result.addProperty(nextElement, AotMapperUtils.writeObjectAsString(session.getAttribute(nextElement)));
            }
        } catch (final Exception e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
        }
        return result;
    }
}
