package com.example.someonebe.service;

import com.example.someonebe.dto.request.BoardRequestDto;
import com.example.someonebe.dto.response.*;
import com.example.someonebe.entity.Board;
import com.example.someonebe.entity.BoardComment;
import com.example.someonebe.entity.User;
import com.example.someonebe.exception.ApiException;
import com.example.someonebe.exception.ExceptionEnum;
import com.example.someonebe.repository.BoardCommentRepository;
import com.example.someonebe.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardCommentRepository boardCommentRepository;
    private final FileStorageService fileStorageService;

    // 집들이 작성
    @Transactional
    public MessageResponseDto writeBoard(BoardRequestDto boardRequestDto, User user) {
        //image파일 비어있을때 예외처리
        if (boardRequestDto.getImage() == null || boardRequestDto.getImage().isEmpty()) {
            // 적절한 오류 메시지와 함께 응답을 반환하거나 예외를 던집니다.
            throw new ApiException(ExceptionEnum.NOT_FOUND_IMAGE);
        }
        // 이미지를 S3에 업로드하고 파일 이름을 가져옴
        String fileName = fileStorageService.storeFile(boardRequestDto.getImage());
        // 파일 이름을 사용하여 S3 URL을 가져옴
        String imageUrl = fileStorageService.getFileUrl(fileName);

        boardRepository.saveAndFlush(new Board(boardRequestDto, user, imageUrl));
        return new MessageResponseDto(StatusEnum.OK, "null");
    }

    // 집들이 수정
    @Transactional
    public MessageResponseDto updateBoard(Long houseid,
                                          BoardRequestDto boardRequestDto,
                                          User user,
                                          MultipartFile image) {
        // 수정할 집들이 찾기
        Board board = findPost(houseid);
        // 집들이 내가 썻나 확인
        checkMyPost(houseid, user);

        //기존 이미지 삭제
        String oldImageUrl = board.getImageUrl();
        if (oldImageUrl != null && oldImageUrl.isEmpty()) {
            String oldFileName = oldImageUrl.substring(oldImageUrl.lastIndexOf("/") + 1);
            fileStorageService.deleteFile(oldFileName);
        }

        // 이미지를 S3에 업로드하고 파일 이름을 가져옴
        String fileName = fileStorageService.storeFile(image);
        // 파일 이름을 사용하여 S3 URL을 가져옴
        String imageUrl = fileStorageService.getFileUrl(fileName);

        board.update(boardRequestDto, imageUrl);
        return new MessageResponseDto(StatusEnum.OK, "null");
    }

    // 집들이 삭제
    @Transactional
    public MessageResponseDto deleteBoard(Long houseid, User user) {
        // 집들이 존재 확인
        Board board = findPost(houseid);
        // 집들이 내가 썻나 확인
        checkMyPost(houseid, user);
        // s3저장된 이미지 삭제
        String imageUrl = board.getImageUrl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
            fileStorageService.deleteFile(fileName);
        }
        boardRepository.deleteById(houseid);
        return new MessageResponseDto(StatusEnum.OK, "null");
    }

    //메인페이지 전체 글 리스트 조회  -토큰 x
    @Transactional(readOnly = true)
    public List<BoardListResponseDto> getBoardList() {
        List<BoardListResponseDto> boardListResponseDtos = new ArrayList<>(); //mapstream 사용해보기...
        List<Board> boards = boardRepository.findAllByOrderByCreatedAtDesc();
        for (Board board : boards) {
            boardListResponseDtos.add(new BoardListResponseDto(board));
        }
        return boardListResponseDtos;
    }

    // 상세 조회
    @Transactional(readOnly = true)
    public BoardDetailResponseDto getBoardDetailList(Long houseid, User user) {
        // 게시글 존재 확인
        Board board = boardRepository.findById(houseid).orElseThrow(
            () -> new ApiException(ExceptionEnum.NOT_FOUND_POST_ALL));
        // 내가 썻는지 확인(스크랩 눌렀나)
        //
        // 댓글 리스트 가져오기
        List<BoardComment> boardList = boardCommentRepository.findAllByBoard(board);

        List<BoardCommentResponseDto> boardCommentList = new ArrayList<>();
        for (BoardComment board1 : boardList) {
            BoardCommentResponseDto boardCommentResponseDto = new BoardCommentResponseDto(board1);
            boardCommentList.add(boardCommentResponseDto);
        }
        return new BoardDetailResponseDto(board, user, boardCommentList);
    }

    // 집들이 존재 확인
    public Board findPost(Long houseid) {
        return boardRepository.findById(houseid).orElseThrow(
            () -> new ApiException(ExceptionEnum.NOT_FOUND_POST_ALL));
    }
    // 집들이 내가 썻나 확인

    public void checkMyPost(Long houseid, User user) {
        boardRepository.findByIdAndUser(houseid, user).orElseThrow(() -> new ApiException(ExceptionEnum.NOT_MATCH_USER));
    }


//    // 집들이 게시글 스크랩
//    @Transactional
//    public MessageResponseDto houseScrap(User user, Long houseid) {
//        // 게시글 존재 확인
//        Board board = boardRepository.findById(houseid).orElseThrow(() -> new ApiException(ExceptionEnum.NOT_FOUND_HOUSE_ID));
//        // 스크랩 내가 눌렀나 확인 -> 스크랩 버튼 눌렀나 안눌렀나.
//        Optional<Scrap> scrap = scrapRepository.findByUserAndId(user, houseid);
//
//        boolean scrapstatus = scrap.isEmpty();
//        int scrapCount = board.getScrapcount();
//
//        // 스크랩 안했으면 하기
//        if (scrapstatus) {
//            scrapCount ++;
//            scrapRepository.saveAndFlush(new Scrap(user, board));
//        } else{ // 스크랩 했으면 취소
//            scrapCount --;
//            scrapRepository.deleteById(scrap.get().getId());
//        }
//        // Entity의 count증가 메서드 실행 그리고 저장
//        board.updateScrapcount(scrapCount);
//        boardRepository.save(board);
//        ScrapResponseDto scrapResponseDto = new ScrapResponseDto(countScrap(board), scrapstatus);
//
//        return new MessageResponseDto(StatusEnum.OK, scrapResponseDto);
//    }
//
//    // 스크랩 여부
//    public boolean checkScrap(User user, Board board) {
//        if (user == null) return false;
//        Optional<Scrap> scrap = scrapRepository.findByUserAndBoard(user, board);
//        return scrap.isPresent();
//    }

}
