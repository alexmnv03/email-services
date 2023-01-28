package com.example.commondata.data;

public class EmailConstants {
    // Адрес отправителя для тестовых сообщений
    public static final String FIX_FROM_EMAIL = "test@mail.ru";
    public static final String EMAIL_SUBJECT = "Подтверждение регистрации";
    public static final String EMAIL_TEXT = "<body style='margin:0;padding:0;'> <table role='table' style='width:100%%;border-collapse:collapse;border:0;border-spacing:0;background:#ffffff;'> <tr> <td align='center' style='padding:0;'> <table role='table' style='width:602px;border-collapse:collapse;border:1px solid #cccccc;border-spacing:0;text-align:left;'> <tr> <td align='center' style='padding:4px 0 4px;background:#FFFFFF;border:1px solid #cccccc;'> <img src='\\src\\img\\free-icon-logo.png' alt='my_logo' width='170' </td></tr><tr> <td style='padding:36px 30px 42px 30px;background:#F0F4F2;font-family:Roboto,sans-serif;'> <p style='font-family: Roboto, sans-serif;'>Уважаемый, %1s</p> <p style='font-family: Roboto, sans-serif;'>Вы получили это письмо, потому что в сервисе «Name company» была создана учетная запись c данным электронным адресом.</p> <p style='font-family: Roboto, sans-serif;'>Если вы не запрашивали подтверждение адреса почты, пожалуйста, проигнорируйте это письмо.</p> <p style='font-family: Roboto, sans-serif;'>Если регистрация осуществлена Вами, то Вам следует подтвердить свою регистрацию и тем самым активировать Вашу учетную запись. </p> <p style='font-family: Roboto, sans-serif;'>Подтверждение регистрации производится один раз и необходимо для защиты от злоумышленников.</p> <p style='font-family: Roboto, sans-serif;'>Чтобы активировать Вашу учетную запись, необходимо ввести этот код в окне подтверждения: <h3>%2s</h3></p> <p style='font-family: Roboto, sans-serif;'>С наилучшими пожеланиями, администрация «Name company».</p></td></tr><tr> <td style='padding:30px;background:#198754;text-align:center;border:1px solid #cccccc;'> <a style='text-decoration:none;color:#ffffff;opacity:50%%' href='#'>Name-company.com</a> </td></tr></table> </td></tr></table> </body>";

    public static final Integer WRITE_TIME_OUT = 30000;
    public static final Integer READ_TIME_OUT = 40000;
    public static final Integer COUNT_NAMES = 40;
    public static final Integer COUNT_EMAILS = 6;
    public static final Integer CODE_MAX_VALUE = 99999;
    public static final String BODY_MESSAGE = "You received this email because an account with " +
            "this email address was created in the 'Name company' service.";

}
