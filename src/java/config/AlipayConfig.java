package config;

public class AlipayConfig {
    // 商户appid
    public static String APPID = "2016092700611386";
    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCLW1MfBsynafkrvS4EvvBH5yEhf8D1PPFGU3nSbh9D6LNI4isHmANZqxk3iMX9fFar4a679crWYPKZJDdMyCZdlvzPN3qeNXWbe3RfGpUnjHdLlIfk4w2fW4af1kEXJAD7yb0A85BuSV0cocnkOoLXyjhnc3G3SdwcVNDscBPBonu8T1YbCXp52X886vU+lx6liVPyEcgAx3cKZoofKoD6LD5ZhweKH1B6tBauLWub/ch7zKCSB2pnqBVnn4KqTKIwl79qVDx0OykXsto2hGAZeg4qXLZK1IA8IpiNADeFeuccHOkPJR6h+ntQHX9cwU3D5r8VOQpp0FD5uvHFpUrHAgMBAAECggEACj893nMui0TkmI3Bm4G6w17Adekh9CZ2C1jKBWopjGHJBRv0VAuee3J0WTwKavhAm0TuB04sixT34cDi/BdfktQuZMJMluORcUxjozaeLxlKEFmsoGz9rHEbdhdYE+6Gz/zmk/JU9XcZjKhs4wZWZq2s9hZsDzE0KTUnY6teGIfRNdV6gpyVOdYJUWFPVAxAjs21LGhBUuhcLUHd4P+Vx/AznFGRj1yheclAeex+kcDnDyefZgy1Do7ri+sPQgiRreQz4A8pHy9jG5kj5E/Gc8a2ga2i2ixu9Ka/UTCrsqAQa3Ik9aAAGjUMJXSd+BAZATngQ5/5xJ6yBWhIaGlZ+QKBgQDN7Zx2A4A/zUj0qeOvWztB5KgeychIPnEm9Qk4mF4bgH1fRSaH3v1qi0yZOLI4APUv2LaJ+RurF+AY9MeGFDJ54zuCYrF3JGql6H4oiQpQEZZk0406SEeAUtXJqP1psbc++fDJ0vT6yniedvbLb1AQEPkGJYWZQt+YxGPrX+dJJQKBgQCtPdlD2iyJwIZi+bwsKFW4ktxtg8ucmKcAlB0jed1oUPBKM39eoTAyaQR0ay1MB27DBrV7LTwd6OIQYNscj7yR4uhxSfwPnNDeZcACgcYzVA8Z4tXUvE6SvSupOtCOOSK+oWzbg/Wc5fLjx9h7r+wcbTzL7YKZ3ai0KmFk0DauewKBgGcoRp6ftlk/vvJBQLuRWifyIX362wfFr+0yBsKBIXNpdGSLQXgC7rO2wFJpIm4HnMuxJhAL6B5XQX1+8YxrBbYlbKQuSHREm+AsjTZFIBNoHAy6JR8zIg5YcJwquXd+/SrhDgwsT2hqkO3g7lSOmG7T8ctwdHKIG3Ztuxgn3La5AoGBAKo3r+ccUlCTKzfKm1pj0z4EhrLQIVpfHw/ReAPodGRPypmNmeNXyyxsxSTCPgKK9wiG6f/6RSw6KwzercuVazmIUs0lXZXVheBO52dPLXg3apMLrwBUeXtyhZjEojBr8TiPEY9qTAT6ZznpnUqCYyLXLjpMxtr50Q4c6g8NCf1LAoGBAJaAcxktpROMhfuhWON4NrjMyEzsf1GxvS+ClQiXyYP8J4ERZ1Z7NZcY/ftyTuJ+MPtGJdKsUKByMoYdFow7wrY5Kis+UAzkayyETU2/sYpV7gnGX1HimrPjWw2JAxQCbiRiWUnmhmcPaYEnp4gUJMGCKQO2exyNGZUOMlN40Dxb";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost/pay/returnurlPayServlet";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://localhost:8080/returnurlPayServlet";
    // 请求网关地址
    public static String URL = "https://openapi.alipaydev.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjrEVFMOSiNJXaRNKicQuQdsREraftDA9Tua3WNZwcpeXeh8Wrt+V9JilLqSa7N7sVqwpvv8zWChgXhX/A96hEg97Oxe6GKUmzaZRNh0cZZ88vpkn5tlgL4mH/dhSr3Ip00kvM4rHq9PwuT4k7z1DpZAf1eghK8Q5BgxL88d0X07m9X96Ijd0yMkXArzD7jg+noqfbztEKoH3kPMRJC2w4ByVdweWUT2PwrlATpZZtYLmtDvUKG/sOkNAIKEMg3Rut1oKWpjyYanzDgS7Cg3awr1KPTl9rHCazk15aNYowmYtVabKwbGVToCAGK+qQ1gT3ELhkGnf3+h53fukNqRH+wIDAQAB";
    // 日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA2";
}
