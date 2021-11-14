package haeun.kim.surveyproject.dto;

import haeun.kim.surveyproject.domain.Questions;
import lombok.Getter;

@Getter
public class QuestionsResponseDto {

    private Long id;
    private Long postId; // 어떤 설문에 대한 질문인가?
    private String content;
    private String originPic;
    private String storedPic;
    private Boolean choicable; // true면 객관식 false면 주관식
    private Boolean multiple; // true면 중복답 허용
    private Boolean etcAnswer; // 객관식 마지막 보기를 기타(주관식)으로 추가
    private Boolean necessaryAns; // 필수로 답해야하는 문항인지
    private int choiceCnt; // 객관식 답안 갯수 (max : 10)
    private String choice1; // 객관식 보기 1번
    private String choice2;
    private String choice3;
    private String choice4;
    private String choice5;
    private String choice6;
    private String choice7;
    private String choice8;
    private String choice9;
    private String choice10;

    public QuestionsResponseDto(Questions entity) {
        this.id = entity.getId();
        this.postId = entity.getPostId();
        this.content = entity.getContent();
        this.originPic = entity.getOriginPic();
        this.storedPic = entity.getStoredPic();
        this.choicable = entity.getChoicable();
        this.multiple = entity.getMultiple();
        this.etcAnswer = entity.getEtcAnswer();
        this.necessaryAns = entity.getNecessaryAns();
        this.choiceCnt = entity.getChoiceCnt();
        this.choice1 = entity.getChoice1();
        this.choice2 = entity.getChoice2();
        this.choice3 = entity.getChoice3();
        this.choice4 = entity.getChoice4();
        this.choice5 = entity.getChoice5();
        this.choice6 = entity.getChoice6();
        this.choice7 = entity.getChoice7();
        this.choice8 = entity.getChoice8();
        this.choice9 = entity.getChoice9();
        this.choice10 = entity.getChoice10();
    }
}
