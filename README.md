# monthlyMill

월간방앗간

---

## HTML

### component

공통 사용 컴포넌트 폴더

- header
- footer
- orderAside
- mobile_header : 모바일 헤더
- mobile_sideMenu : 모바일 사이드바
- admin_gnb : 관리자 공통메뉴
- makerHeader : 메이커스 헤더
- makerAside : 메이커스 사이드바

### common

고객/메이커스 공통 화면

- login: 로그인
- main: 메인화면(시연 이후 사용)
- introduce: 월간방앗간 소개(=임시용 메인창)

### memberJoin

회원가입 관련 화면들

- join_method: 회원가입 첫 화면
- join_agreement: 약관동의창
- join_basic: 필수정보 입력
- join_additory: 추가정보 입력
- join_finish: 회원가입 완료

### customer

고객 이용 화면

- findItem: 떡 찾기
- cart: 장바구니
- payment:결제창
- payment_deposit: 결제계산창
- payment_confirm: 입금내역 확인
- loading: 결제대기 로딩화면
- loadingMatch: 매칭대기 로딩화면
- orderList: 나의 주문정보-주문배송조회
- cancelOrderList: 나의 주문정보-취소반품조회
- cancelOrder: 주문취소 신청

### admin

관리자 화면 (정리덜됨!)

- adminLogin: 관리자 로그인
- adminMain: 관리자 메인페이지(대시보드)
- adminProduct_regist: 상품- 상품 등록 및 수정
- adminCustomer_infoSet: 고객- 고객정보관리
- adminCustomer_deliverySet: 고객- 고객배송관리

### makers

메이커스 화면

- makerMain: 메이커스 메인화면
- makerJoin_popup: 메이커스 가입 전 입점팝업창
- makerJoin_agreement: 메이커스 가입 1단계 약관동의 (팝업창 여기 뜸)
- makerJoin_basic: 메이커스 가입 2단계 기본정보
- makerJoin_enter: 메이커스 가입 3단계 입점신청
- makerJoin_finish: 메이커스 가입 4단계 가입완료

- makerMatch_main: 월간매칭 메인화면
- makerMatch_detail: 월간매칭 상세화면

- makerMymatch_state: my매칭 매칭현황
- makerMymatch_history: my매칭 매칭이력
- makerCalculation: 정산
- makersMypage_user_read: 회원정보 조회
- makersMypage_user_set: 회원정보 수정
- makersMypage_enter_read: 입점정보 조회
- makersMypage_enter_set: 입점정보 수정
- makersMypage_additional_info: 추가정보 안내
- makersMypage_additional_read: 추가정보 조회
- makersMypage_additional_set: 추가정보 수정

- makerNotice: 공지사항 리스트
- makerNotice_post: 공지사항 게시글

---

메이커스 관련 JS파일

- makerJoin_front: 3단계 입점신청 유효값 검사 조금 해둠(다한건아님)
- makerMatch_front: 서버시간 뜨는부분 현재 로컬시간 가져와서 하는걸로 텍스트 띄워둠! (시작/종료 시간은 적당히 넣어놨음)

---

## 주요 흐름

### 고객 회원가입 ~ 로그인

1. join_method: 고객 탭 선택된 상태로 회원가입 버튼 클릭
2. join_agreement: 필수 약관에 모두 동의 후 다음 버튼 클릭
3. join_basic: 필수 정보 모두 입력, 중복확인 및 본인인증 완료 후 다음 버튼 클릭
4. join_additory: 선호 정보 해시태그 선택 등 추가정보 입력하고 쿠폰받기 클릭하거나, 추가정보 안쓰고 넘어가기 클릭
5. join_finish: 회원가입 완료 후 로그인하기 버튼 클릭
6. login: 고객 탭 선택된 상태로 로그인 진행
7. introduce(추후 main): 메인페이지로 넘어옴(로그인 된 상태)

### 고객 주문 ~ 결제

1. findItem: 조건에 해당하는 해시태그 선택 및 조건설정 후 '결과보기' 클릭
2. findItem(동일): 하단에 조건에 맞는 상품 순위 리스트로 나타남. 장바구니에 클릭해서 담고 상단바의 '장바구니' 버튼 클릭해 이동
3. cart: 장바구니에 담은 상품들 리스트와 총 가격 나옴. '결제하기' 버튼 클릭
4. payment: 주문자 정보, 상품정보, 배송정보 확인 및 입력. 결제수단 선택, 결제약관 동의 후 '결제하기' 버튼 클릭
5. payment_deposit: 결제정보와 무통장입금 안내 나타남. 입금자명과 이메일 입력, 구매내역 동의 후 '확인'버튼 클릭
6. loading: 입금대기 로딩창. 입금내역 확인된 후 '모래시계' 버튼 클릭.
7. payment_confirm: 입금확인 글 확인 후 '매칭하기' 버튼 클릭.
8. loading: 매칭대기 로딩창. 일정시간 기다리는 동안 매칭 실패 시, 모래시계 클릭 시 재매칭 버튼 나타남. '재매칭' 버튼 클릭.
9. loading(동일): 모래시계 버튼 클릭. 매칭 성공되어 매칭완료 버튼 나타남. '매칭완료' 버튼 클릭.
10. orderList: 주문/배송조회 목록에 결제한 상품 나타남.

### 고객 주문취소

1. orderList: 주문/배송 조회 목록 하단의 '주문취소'버튼 클릭
2. orderCancel: 주문취소 신청 페이지가 나옴. 여기서 취소사유 선택 및 상세사유 입력, 환불정보 입력. 그 후 '주문취소 신청하기' 버튼 클릭.
3. orderList_cancel: 취소/반품 조회 목록에 주문취소된 상품 나타남.
