package haeun.kim.surveyproject.service;

import haeun.kim.surveyproject.domain.Questions;
import haeun.kim.surveyproject.dto.QuestionsResponseDto;
import haeun.kim.surveyproject.dto.QuestionsSaveRequestDto;
import haeun.kim.surveyproject.repository.QuestionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class  QuestionsService {

    public final QuestionsRepository questionsRepository;

    @Transactional
    public Long save(QuestionsSaveRequestDto requestDto) {
        return questionsRepository.save(requestDto.toEntity()).getId();
    }

    public String savePic(MultipartFile pic) throws IOException {

        // 파일 이름을 업로드 한 날짜로 바꾸어서 저장할 것이다
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String current_date = simpleDateFormat.format(new Date());

        // 프로젝트 폴더에 저장하기 위해 절대경로를 설정 (Window 의 Tomcat 은 Temp 파일을 이용한다)
        String absolutePath = new File("").getAbsolutePath() + "\\";

        // 경로를 지정하고 그곳에다가 저장할 심산이다
        String path = "images/" + current_date;
        File file = new File(path);
        // 저장할 위치의 디렉토리가 존재하지 않을 경우
        if(!file.exists()){
            // mkdir() 함수와 다른 점은 상위 디렉토리가 존재하지 않을 때 그것까지 생성
            file.mkdirs();
        }

        if(!pic.isEmpty()){
            // jpeg, png, gif 파일들만 받아서 처리할 예정
            String contentType = pic.getContentType();
            String originalFileExtension;
            // 확장자 명이 없으면 이 파일은 잘 못 된 것이다
            if (ObjectUtils.isEmpty(contentType)){
                return null;
            }
            else{
                if(contentType.contains("image/jpeg")){
                    originalFileExtension = ".jpg";
                }
                else if(contentType.contains("image/png")){
                    originalFileExtension = ".png";
                }
                else if(contentType.contains("image/gif")){
                    originalFileExtension = ".gif";
                }
                // 다른 파일 명이면 아무 일 하지 않는다
                else{
                    return null;
                }
            }
            // 각 이름은 겹치면 안되므로 나노 초까지 동원하여 지정
            String new_file_name = Long.toString(System.nanoTime()) + originalFileExtension;

            // 저장된 파일로 변경하여 이를 보여주기 위함
            file = new File(absolutePath + path + "/" + new_file_name);
            pic.transferTo(file);
            return path + "/" + new_file_name;
        }
        return null;
    }

    @Transactional
    public void delete (Long id) {
        Questions questions = questionsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        questionsRepository.delete(questions);
    }

    @Transactional
    public List<QuestionsResponseDto> findByPostId(Long id){
        return questionsRepository.findByPostId(id).stream()
                .map(QuestionsResponseDto::new)
                .collect(Collectors.toList());
    }
}
