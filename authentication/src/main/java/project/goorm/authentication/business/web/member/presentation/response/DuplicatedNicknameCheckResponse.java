package project.goorm.authentication.business.web.member.presentation.response;

public class DuplicatedNicknameCheckResponse {

    private final boolean existent;

    public DuplicatedNicknameCheckResponse(Integer value) {
        this.existent = value != null;
    }

    private DuplicatedNicknameCheckResponse(boolean existent) {
        this.existent = existent;
    }

    public static DuplicatedNicknameCheckResponse createNicknameExistResponse() {
        return new DuplicatedNicknameCheckResponse(true);
    }

    public static DuplicatedNicknameCheckResponse createNicknameNotExistResponse() {
        return new DuplicatedNicknameCheckResponse(false);
    }

    public boolean exist() {
        return existent;
    }

    @Override
    public String toString() {
        return String.valueOf(existent);
    }
}
