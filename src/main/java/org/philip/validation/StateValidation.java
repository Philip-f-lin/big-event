package org.philip.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.philip.anno.State;

public class StateValidation implements ConstraintValidator<State, String> {
    /**
     *
     * @param value 將來要校驗的數據
     * @param context
     * @return 如果返回false, 則校驗不通過, 如果返回true, 則校驗通過
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 提供校驗規則
        if(value == null){
            return false;
        }

        if(value.equals("已發布") || value.equals("草稿")){
            return true;
        }

        return false;
    }
}
