package haeun.kim.surveyproject.controller;

import haeun.kim.surveyproject.dto.AnswersResponseDto;
import haeun.kim.surveyproject.dto.QuestionsSaveRequestDto;
import haeun.kim.surveyproject.service.AnswersService;
import haeun.kim.surveyproject.service.QuestionsService;
import haeun.kim.surveyproject.service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class QuestionsApiController {

    private final QuestionsService questionsService;
    private final AnswersService answersService;
    private final S3Uploader s3Uploader;

    @PostMapping("/api/v1/questions")
    public Long save( @RequestParam("img") MultipartFile file,
                      @RequestParam("postId") Long postId,
                      @RequestParam("content") String content,
                      @RequestParam("choiceable") Boolean choiceable,
                      @RequestParam("multiple") Boolean multiple,
                      @RequestParam("etcAnswer") Boolean etcAnswer,
                      @RequestParam("necessaryAns") Boolean necessaryAns,
                      @RequestParam("choiceCnt") int choiceCnt,
                      @RequestParam("choice1") String choice1,
                      @RequestParam("choice2") String choice2,
                      @RequestParam("choice3") String choice3,
                      @RequestParam("choice4") String choice4,
                      @RequestParam("choice5") String choice5,
                      @RequestParam("choice6") String choice6,
                      @RequestParam("choice7") String choice7,
                      @RequestParam("choice8") String choice8,
                      @RequestParam("choice9") String choice9,
                      @RequestParam("choice10") String choice10) {
        String filepath = null;
        try {
            //            filepath = questionsService.savePic(file);
            filepath = s3Uploader.upload(file, "images");
        } catch (IOException ignored) {
            // TODO
        }
        QuestionsSaveRequestDto requestDto = QuestionsSaveRequestDto.builder()
                                                .postId(postId)
                                                .content(content)
                                                .originPic(file.getOriginalFilename())
                                                .storedPic(filepath)
                                                .choicable(choiceable)
                                                .multiple(multiple)
                                                .etcAnswer(etcAnswer)
                                                .necessaryAns(necessaryAns)
                                                .choiceCnt(choiceCnt)
                                                .choice1(choice1)
                                                .choice2(choice2)
                                                .choice3(choice3)
                                                .choice4(choice4)
                                                .choice5(choice5)
                                                .choice6(choice6)
                                                .choice7(choice7)
                                                .choice8(choice8)
                                                .choice9(choice9)
                                                .choice10(choice10)
                                                .build();

        return questionsService.save(requestDto);
    }

    @DeleteMapping("api/v1/questions/{id}")
    public Long delete(@PathVariable Long id) {
        // 질문에 대한 답변 모두 삭제
        List<AnswersResponseDto> answers = answersService.findByQuestionId(id);
        for (AnswersResponseDto answer : answers) {
            answersService.delete(answer.getId());
        }
        // 질문 삭제
        questionsService.delete(id);
        return id;
    }
}
