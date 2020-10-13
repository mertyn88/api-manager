package kr.co.api.manager.db.board.service.impl;

import kr.co.api.manager.db.board.mapper.BoardMapper;
import kr.co.api.manager.db.board.model.*;
import kr.co.api.manager.db.board.service.BoardService;
import kr.co.api.manager.message.model.MessageModel;
import kr.co.api.manager.message.model.MessageSettingModel;
import kr.co.api.manager.util.HttpUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Value("${image.upload-path}")
    private String basePath;

    /**
     * 게시판 글 보기
     * @return
     */
    public List<DataModel> selectBoard() {
        return boardMapper.selectBoard();
    }

    /**
     * 게시판 댓글 삽입
     * @param replyModel
     * @return
     */
    public int insertReply(ReplyModel replyModel) {
        return boardMapper.insertReply(replyModel);
    }

    /**
     * 게시판 글 삽입
     * @param boardModel
     * @param multipartFiles
     * @return
     * @throws IOException
     */
    public int insertBoard(BoardModel boardModel, List<MultipartFile> multipartFiles) throws IOException {

        List<ImageVo> imageVoList = new ArrayList<>();
        for(MultipartFile files : multipartFiles){
            /**
             * 파일 변환 프로세스
             */
            imageVoList.add(setTransferImage(files));
        }

        /**
         * Set Image Info
         */
        boardModel.setBoardImgUrl(getUploadPath());
        boardModel.setBoardImgOriginNm(imageVoList.stream().map(i -> i.getBoardOriginImg()).collect(Collectors.joining(",")));
        boardModel.setBoardImgDestinationNm(imageVoList.stream().map(i -> i.getBoardDestinationImg()).collect(Collectors.joining(",")));

        return boardMapper.insertBoard(boardModel);
    }

    /**
     * 이미지 이름름 변환 및 업로드 저
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public ImageVo setTransferImage(MultipartFile multipartFile) throws IOException {

        /**
         * 파일 업로드 경로 가져오기
         */
        String uploadPath = getUploadPath();

        /**
         * 원본 이미지 이름 및 확장자
         */
        String originalFileNm =  multipartFile.getOriginalFilename();
        String originalFileNmExtension = FilenameUtils.getExtension(originalFileNm).toLowerCase();

        /**
         * 변환 이미지 이름
         */
        String destinationFileNm = RandomStringUtils.randomAlphanumeric(32) + "." + originalFileNmExtension;
        File destinationFile = new File(getUploadPath() + File.separator +destinationFileNm);

        /**
         * 이미지 파일 전송
         */
        destinationFile.getParentFile().mkdirs();
        multipartFile.transferTo(destinationFile);

        /**
         * DB Insert Vo저장
         */
        return new ImageVo(){{
            setBoardOriginImg(originalFileNm);
            setBoardDestinationImg(destinationFileNm);
        }};
    }

    /**
     * 실제 업로드되는 경로
     * @return
     */
    private String getUploadPath(){
        return basePath + File.separator + getBasePath();
    }

    /**
     * Base Url
     * @return
     */
    private String getBasePath(){
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    @Setter
    @Getter
    class ImageVo {
        private String boardOriginImg;
        private String boardDestinationImg;
    }


    /**
     * 상품글 올리기
     * @param productModel
     * @param multipartFiles
     * @return
     * @throws IOException
     */
    public int insertProduct(ProductModel productModel, List<MultipartFile> multipartFiles) throws IOException {

        List<ImageVo> imageVoList = new ArrayList<>();
        for(MultipartFile files : multipartFiles){
            /**
             * 파일 변환 프로세스
             */
            imageVoList.add(setTransferImage(files));
        }

        /**
         * Set Image Info
         */
        productModel.setBaseUrl(getBasePath());
        productModel.setImageUrl(imageVoList.stream().map(i -> i.getBoardDestinationImg()).collect(Collectors.joining(",")));

        /** Insert **/
        try{
            boardMapper.insertProduct(productModel);
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return HttpStatus.OK.value();
    }

    /**
     * 상품 글 보기 - 마이페이지
     * @return
     */
    public List<ProductModel> mypageProduct(String uid) {
        return boardMapper.mypageProduct(uid);
    }

    /**
     * 마이페이지 - 구매내역
     * @return
     */
    public List<ProductModel> targetProduct(String targetUid){
        return boardMapper.targetProduct(targetUid);
    }

    @Override
    public List<ProductModel> mainProduct() {
        return boardMapper.mainProduct();
    }

    @Override
    public List<ProductModel> recommendProduct(String categoryDepth1, String categoryDepth2) {
        return boardMapper.recommendProduct(categoryDepth1, categoryDepth2);
    }

    @Override
    public ProductModel detailProduct(String productId) {
        return boardMapper.detailProduct(productId);
    }

    @Override
    public List<ProductModel> purposeProduct(String productId, List<String> purpose) {
        return boardMapper.purposeProduct(productId, purpose);
    }

    @Override
    public List<ProductModel> searchProduct(String keyword) {
        return boardMapper.searchProduct(keyword);
    }


    @Override
    public List<TargetListModel> targetUserList(String productId, String sourceUid) {
        return boardMapper.targetUserList(productId, sourceUid);
    }

    @Override
    public int targetUserUpdate(String productId, String sourceUid, String targetUid) throws IOException {
        /** 물품 상태 업데이트 **/
        try{
            boardMapper.targetUserUpdate(productId, sourceUid, targetUid);
        }catch (DataAccessException e){
            e.printStackTrace();
        }

        /** 상품 정보 가져오기 (푸쉬알람용) **/
        TargetUpdatePushModel targetUpdatePushModel = boardMapper.targetUserUpdatePush(productId, targetUid);

        /** 푸쉬알람 보내기 **/
        MessageSettingModel messageSettingModel = new MessageSettingModel(){{
            setTo(targetUpdatePushModel.getTargetMessageToken());
            setPriority("high");
            setData(
                    new MessageModel(){{
                        setTradeTitle(targetUpdatePushModel.getTitle());
                        setTradeBaseUrl(targetUpdatePushModel.getBaseUrl());
                        setTradeImageUrl(targetUpdatePushModel.getImageUrl());
                        setType("trade");
                    }}
            );
        }};

        return HttpUtil.getPostUrl(messageSettingModel, HttpUtil.FIRE_BASE_URL, HttpUtil.SEND_MASSAGE_ACCESS_TOKEN);
    }

    @Override
    public int targetUserReview(String productId, String targetUid, String review) {

        /** 구매자 후기 업데이트 **/
        try{
            boardMapper.targetUserReview(productId, targetUid, review);
        }catch (DataAccessException e){
            e.printStackTrace();
        }

        return HttpStatus.OK.value();
    }
}