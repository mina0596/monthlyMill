# monthlyMill

월간방앗간

---

## HTML

### component

공통 사용 컴포넌트 폴더

- header
- footer
- mobile_header
- mobile_sideMenu

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
- loading: 결제대기, 매칭대기용 로딩 화면
- orderList: 나의 주문정보-주문배송조회
- cancelOrderList: 나의 주문정보-취소반품조회
- cancelOrder: 주문취소창

### admin

관리자 화면

- adminLogin: 관리자 로그인
- adminMain: 관리자 메인페이지(대시보드)
- adminProduct_regist: 상품- 상품 등록 및 수정
- adminCustomer_infoSet: 고객- 고객정보관리
- adminCustomer_orderSet: 고객- 고객배송관리

### makers

메이커스 화면

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
