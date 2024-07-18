package com.wh.bank.vo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data //Vo view object 视图对象 从客户端提交的对象
public class AccountVo {
    @NotBlank(message = "银行账户不能为空")
    @Length(max = 16,message = "超过16个字符")
    @Min(value = 3,message = "账户不能过短")
    @Size(min = 3,max = 16,message = "银行账户长度必须3-16")
    private String accountId;

    @NotBlank(message = "密码不能为空")
    @Min(value = 3,message = "密码不能过短")
    private String password;
    @NotBlank(message = "姓名不能为空")
    private String identityName;

    @Pattern(regexp = "^1[3-9]\\d{9}$",message = "手机格式不正确")
    private String mobile;
}
