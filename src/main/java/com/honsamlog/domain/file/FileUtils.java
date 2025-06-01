package com.honsamlog.domain.file;

import com.honsamlog.domain.file.FileRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class FileUtils {

    private final String uploadPath = Paths.get("C:", "develop", "upload-files").toString();

    /**
     * 다중 파일 업로드
     * @param multipartFiles - 파일 객체 List
     * @return DB에 저장할 파일 정보 List
     */
    public List<FileRequest> uploadFiles(final List<MultipartFile> multipartFiles) {
        log.info("uploadFile 입성222222222");
        List<FileRequest> files = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (multipartFile.isEmpty()) {
                continue;
            }
            files.add(uploadFile(multipartFile));
        }
        return files;
    }

    /**
     * 단일 파일 업로드
     * @param multipartFile - 파일 객체
     * @return DB에 저장할 파일 정보
     */

        public FileRequest uploadFile(final MultipartFile multipartFile) {
            log.info("uploadFile 입성");
            if (multipartFile.isEmpty()) {
                log.warn("업로드된 파일이 비어 있습니다.");
                return null;
            }

            String originalName = multipartFile.getOriginalFilename();
            log.info("파일 업로드 요청: 원본 이름 = {}", originalName);

            String saveName = generateSaveFilename(originalName);
            String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
            String uploadPath = getUploadPath(today) + File.separator + saveName;
            File uploadFile = new File(uploadPath);

            log.info("파일 저장 경로: {}", uploadPath);

            try {
                multipartFile.transferTo(uploadFile);
                log.info("파일 저장 완료: {}", uploadFile.getAbsolutePath());
            } catch (IOException e) {
                log.error("파일 저장 실패: {}", originalName, e);
                throw new RuntimeException("파일 저장 중 오류 발생", e);
            }

            FileRequest fileRequest = FileRequest.builder()
                    .originalName(originalName)
                    .saveName(saveName)
                    .size(multipartFile.getSize())
                    .build();

            log.info("파일 정보 객체 생성 완료: {}", fileRequest);

            return fileRequest;
        }


    /**
     * 저장 파일명 생성
     * @param filename 원본 파일명
     * @return 디스크에 저장할 파일명
     */
    private String generateSaveFilename(final String filename) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String extension = StringUtils.getFilenameExtension(filename);
        return uuid + "." + extension;
    }

    /**
     * 업로드 경로 반환
     * @return 업로드 경로
     */
    private String getUploadPath() {
        return makeDirectories(uploadPath);
    }

    /**
     * 업로드 경로 반환
     * @param addPath - 추가 경로
     * @return 업로드 경로
     */
    private String getUploadPath(final String addPath) {
        return makeDirectories(uploadPath + File.separator + addPath);
    }

    /**
     * 업로드 폴더(디렉터리) 생성
     * @param path - 업로드 경로
     * @return 업로드 경로
     */
    private String makeDirectories(final String path) {
        File dir = new File(path);
        if (dir.exists() == false) {
            dir.mkdirs();
        }
        return dir.getPath();
    }

}