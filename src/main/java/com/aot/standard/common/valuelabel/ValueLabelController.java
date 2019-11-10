package com.aot.standard.common.valuelabel;

import com.aot.standard.common.exception.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ValueLabelController {
    @Autowired
    private ValueLabelService valueLabelService;
    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/common/valuelabel/initSelect.json", method = RequestMethod.POST)
    @ResponseBody
    public List<ValueLabelVO> getValueLabelItemsArray(@RequestParam("grcode") final String grcode) throws CommonException {
        return this.valueLabelService.getValueLabelItemsArray(grcode, this.session);
    }
}
