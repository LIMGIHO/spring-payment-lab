# Payment Lab

가상 결제 1건을 처리하는 Spring Boot 실습입니다. 실제 결제사 연동 없이 `FakePaymentGateway`가 항상 승인(`APPROVED`)을 반환합니다.

`POST /payments` 요청은 `PaymentController`에서 `PaymentService`를 거쳐 `FakePaymentGateway`로 이어집니다.

## 요구 사항

- Java 17

## 실행

```bash
./gradlew bootRun
```

## 호출

별도 터미널에서 실행합니다.

```bash
curl -i -X POST http://localhost:8080/payments \
  -H 'Content-Type: application/json' \
  -d '{"orderId":"order-001","amount":15000}'
```

`amount`는 원 단위 정수입니다. 예상 응답은 `200 OK`, `application/json`입니다.

```json
{"orderId":"order-001","status":"APPROVED"}
```

## 테스트

```bash
./gradlew test
```
