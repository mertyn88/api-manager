# 소개
* Fun.D 사이드 프로젝트인 Flio 중고 마켓 시장에서 사용될 API 프로그램이다.
* 프론트와의 통신에서 모든 동적 데이터 처리는 해당 프로그램을 통한다.

# 사용 기술
__`SpringBoot`__
> * Java Version : 11
> * Spring Version : 2.1.6
> * Gradle Version : 5.6.4
> * DB Vendor : MySQL 8.20
> * Elasticsearch : 7.8.0
> * Was : Tomcat
> * Port : 8080

# 적용 기능
__`Firebase`__ 토큰 저장 기능  
`${BASE_URL}/user/insertUser`
```json
{
  "email": "string",
  "imageUrl": "string",
  "messageToken": "string",
  "name": "string",
  "uid": "string",
  "userToken": "string"
}
```
> 프론트 로그인시에 카카오, 네이버, 구글에서 받은 messageToken정보와 Firebase를 통해서 받은 userToken, uid를 DB에 저장한다.  
> 프로세스 중간에 https://fcm.googleapis.com/fcm/send 를 통해 Firebase에게 요청을 받으며 최종적으로 전달받은 데이터를 저장한다.  
> userToken이 만료될 시에, uid에 해당하는 신규 userToken으로 업데이트한다.  

__`게시판`__ Select  
`${BASE_URL}/board/selectBoard`
```json
{
  "data": [
    {
      "boardKind": "COMMUNITY",
      "boardNo": 3,
      "boardTitle": "파입업로드테스트",
      "boardContent": null,
      "boardWriter": null,
      "replyContent": null,
      "replySort": 0,
      "replyDepth": 0,
      "regDate": "2020-08-04 21:21:53",
      "chgDate": "2020-08-04 21:21:53",
      "boardImgUrl": "/Users/flio/project/api-manager/image/20200804",
      "boardImgOriginNm": "recommand_1.png,recommand_2.png",
      "boardImgDestinationNm": "nkMwzBijjkfCi4WmItFqhiED0eu6RUjj.png,3nuieQmxF9Y4goyb1jr2odVQTOZg0LYx.png"
    },
    {
      "boardKind": "COMMUNITY",
      "boardNo": 2,
      "boardTitle": "파입업로드테스트",
      "boardContent": null,
      "boardWriter": null,
      "replyContent": null,
      "replySort": 0,
      "replyDepth": 0,
      "regDate": "2020-08-02 21:50:30",
      "chgDate": "2020-08-02 21:50:30",
      "boardImgUrl": "/Users/flio/project/api-manager/image/20200802",
      "boardImgOriginNm": "recommand_1.png",
      "boardImgDestinationNm": "omZF04YaDl2zlxuZXrrYWb6J13fs547V.png"
    }
  ]
}
```
> 해당 기능은 프론트와의 연동이 이루어지질 않았으며 샘플로써 추후 프론트에서 정의되면 게시판에 필요한 필드를 재정의한다.  
> Flio에서 사용될 모든 게시판에 대한 글과 댓글에 대한 내용을 조회할 수 있다.  
> 또한 업로드된 이미지에 대한 경로도 조회한다. (구분자는 콤마)

__`게시글`__ Insert
`${BASE_URL}/board/insertBoard`
> 해당 기능은 게시글에 대한 내용을 DB에 삽입한다.  
> 이미지 업로드를 위해 MultiPart를 사용함으로써 Json형태가 아닌 파라미터 형태로 전달한다.
> 이미지는 여러개 업로드 가능하며 32자리의 랜덤한 파일명으로 저장된다.

__`게시글에 대한 댓글`_ Insert  
`${BASE_URL}/board/insertReply`  
```json
{
  "boardKind": "string",
  "boardNo": 0,
  "replyContent": "string",
  "replyDepth": 0,
  "replySort": 0
}
```
> 해당 기능은 작성된 게시글에 대한 댓글을 DB에 삽입한다.  
> 게시글에 대한 댓글은 depth별로 나열될 수 있다.  
> 게시글에 대한 번호만으로 댓글내용을 작성할 수 있으며 추후, uid를 통한 댓글에 대한 작성자를 남길지 여부를 정하여 필드를 추가할지 말지를 결정한다.

__`채팅방 내역`__ Select  
`${BASE_URL}/chat/selectMyChat`  
```json
{
  "chatDate": "string",
  "chatSeq": 0,
  "chatSourceName": "string",
  "chatSourceUid": "string",
  "chatTargetName": "string",
  "chatTargetUid": "string",
  "productId": "string",
  "productName": "string"
}
```
> 해당 기능은 1:1 대화를 나눈 방의 리스트를 볼 수 있다.
> 본인 기준으로 누구누구와 어떤 상품에 대해 이야기를 나누었던 여부, 마지막으로 대화한 시간에 대한 정보를 가지고 있다.


__`채팅글 내역`__ Select  
`${BASE_URL}/chat/selectMyChatDetail`
```json
{
  "chatDate": "string",
  "chatSeq": 0,
  "chatSourceMessage": "string",
  "chatTargetMessage": "string",
  "productName": "string"
}
```
> 해당 기능은 대화에 대한 실제 내역을 볼 수 있다.
> chatSeq에 의해서 메세지 데이터를 확인 할 수 있으며 물품 테이블, 유저 테이블과의 JOIN으로 대화내용, 대화 상대, 거래 물품에 대한 정보를 가져올 수있다.  
> 추후 물품 테이블이 정의되면 물품에 대한 정보를 추가로 가져와야한다. ( 예: 물품가격, 시간, 사용기간 등 )

__`채팅방 개설`__ Insert  
`${BASE_URL}/chat/insertMyChat`
> 해당 기능은 판매자와 구매자간의 1:1 채팅방을 개설하는 기능이다.  
> 프론트에서 메세지 전송 또는 1:1채팅하기라는 이벤트가 동작 시에 1:1채팅을 위한 방을 개설한다.  
> 단, 방 개설 후 메세지를 전송하지 않으면 프론트에서는 해당 방이 보이지 않는다.

__`채팅메세지 저장`__ Insert  
`${BASE_URL}/chat/insertMyChatDetail`
> 해당 기능은 `Firebase`를 통해 push 알람 메세지 전송을 하는 데이터를 저장한다.  

__`검색엔진(Elasticsearch) 카테고리`__ Aggregation  
`${BASE_URL}/aggregation/keyword`  
```json
{
  "data": [
    {
      "keyword": "배음 하모닉스",
      "count": 183
    },
    {
      "keyword": "케이블",
      "count": 180
    },
    {
      "keyword": "믹싱 마스터링",
      "count": 179
    },
    {
      "keyword": "마샬 블루투스",
      "count": 177
    },
    {
      "keyword": "하만카돈 스피커",
      "count": 172
    },
    {
      "keyword": "스튜디오 마이크",
      "count": 171
    },
    {
      "keyword": "폴리 사운드",
      "count": 167
    },
    {
      "keyword": "음반",
      "count": 165
    },
    {
      "keyword": "사운드 디자이너",
      "count": 159
    },
    {
      "keyword": "dolby atmos",
      "count": 158
    }
  ]
}
```
> 해당 기능은 검색엔진에서 수집/색인한 데이터를 특정 필드 기준으로 그룹화하여 보여준다. (DB Group by와 유사)  
> 현재까지는 crawler-manager에서 수집/색인한 네이버포스트데이터를 기준으로 정의되어있다.  


__`검색엔진(Elasticsearch) 검색`__ Normal Search  
> 구현 예정


__`crawler-manager 임시 저장`__  
${BASE_URL}/temporary/save , view  
> 해당 기능은 crawler-manager에서 동적 이벤트로 만들어진 데이터를 임시로 URL로 접근이 가능한 페이지로 만들수 있게 해주는 기능이다.  
> 임시로 만들어진 데이터는 html의 형태로 되어있으며 save 호출시에 저장 및 만들어지며 view호출시 해당페이지가 보여진다.( 네이버 포스트에서 가져온 데이터를 save하여 네이버 포스트가 아닌 해당 url를 통해 네이버 포스트와 같은 화면을 보여준다.)
