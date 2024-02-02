package org.philip.anno;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.philip.validation.StateValidation;

import java.lang.annotation.*;


@Documented // 原註解
@Target({ElementType.FIELD}) // 原註解
@Retention(RetentionPolicy.RUNTIME) // 原註解
@Constraint(validatedBy = {StateValidation.class}) // 指定提供校驗規則的類

public @interface State {
    // 提供校驗失敗後的提示訊息
    String message() default "state參數的值只能是已發布或著草稿";
    // 指定分組
    Class<?>[] groups() default {};
    // 負載 獲取 state 註解的附加訊息
    Class<? extends Payload>[] payload() default {};
}
