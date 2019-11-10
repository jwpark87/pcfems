package com.aot.standard.common.valuelabel;

import com.aot.standard.common.exception.CommonException;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service
public class ValueLabelService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ValueLabelDAO valueLabelDAO;

    @SuppressWarnings("unchecked")
    public List<ValueLabelVO> getValueLabelItemsArray(final String grcode, final HttpSession session) throws CommonException {
        if (session.getAttribute("ValueLabel." + grcode) == null) {
            session.setAttribute("ValueLabel." + grcode, this.valueLabelDAO.getValueLabelItemsToList(grcode));
            this.logger.debug("save ==> ValueLabel.{}.", grcode);
        }
        this.logger.debug("cache ==> ValueLabel.{}.", grcode);
        return (List<ValueLabelVO>) session.getAttribute("ValueLabel." + grcode);
    }

    public String getLabelByValue(final Map<String, Object> params) throws CommonException {
        return this.valueLabelDAO.getLabelByValue(params);
    }

    public JsonObject getValueLabelItemsToJsonObject(final String grcode, final HttpSession session) throws CommonException {
        final List<ValueLabelVO> list = this.getValueLabelItemsArray(grcode, session);
        final JsonObject jsonObject = new JsonObject();
        for (final ValueLabelVO vo : list) {
            jsonObject.addProperty(vo.getValue(), vo.getLabel());
        }
        return jsonObject;
    }
}
