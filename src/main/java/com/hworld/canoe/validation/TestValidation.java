package com.hworld.canoe.validation;

import com.hworld.canoe.domain.req.TestRequest;
import com.hworld.canoe.framework.validation.ParameterRuleValidator;
import com.hworld.canoe.framework.validation.SurveillanceFeatureList;
import org.springframework.stereotype.Service;

@Service
public class TestValidation {
    public SurveillanceFeatureList check(TestRequest entity) {
        ParameterRuleValidator prv = new ParameterRuleValidator();
        Object[][] checkArraySingle = new Object[][]{
                new Object[]{entity.getName(), ParameterRuleValidator.NN, null,
                        "名字不能为空", new Object[]{}, "name", 0},
                new Object[]{entity.getName1(), ParameterRuleValidator.NN, null,
                        "名字1不能为空", new Object[]{}, "name1", 0},

        };
        SurveillanceFeatureList msg = prv.checkByArray(
                checkArraySingle, true);
        if (msg.size() > 0) {
            return msg;
        }
        return new SurveillanceFeatureList();
    }
}
