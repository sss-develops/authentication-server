package project.goorm.authentication.common.response;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class CommonInformationResponse {

    private final Locale lang;
    private final String platForm;
    private final String resolution;

    public CommonInformationResponse(HttpServletRequest httpServletRequest) {
        this.lang = httpServletRequest.getLocale();
        this.platForm = "web";
        this.resolution = "1920x1080";
    }

    public Locale getLang() {
        return lang;
    }

    public String getPlatForm() {
        return platForm;
    }

    public String getResolution() {
        return resolution;
    }

    @Override
    public String toString() {
        return String.format("Language:%s, Platform: %s, Resolution: %s", lang, platForm, resolution);
    }
}
