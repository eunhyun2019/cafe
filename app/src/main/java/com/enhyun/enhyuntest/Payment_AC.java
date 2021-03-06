package com.enhyun.enhyuntest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import kr.co.bootpay.Bootpay;
import kr.co.bootpay.BootpayAnalytics;
import kr.co.bootpay.enums.Method;
import kr.co.bootpay.enums.PG;
import kr.co.bootpay.enums.UX;
import kr.co.bootpay.listener.CancelListener;
import kr.co.bootpay.listener.CloseListener;
import kr.co.bootpay.listener.ConfirmListener;
import kr.co.bootpay.listener.DoneListener;
import kr.co.bootpay.listener.ErrorListener;
import kr.co.bootpay.listener.ReadyListener;
import kr.co.bootpay.model.BootExtra;
import kr.co.bootpay.model.BootUser;

public class Payment_AC extends AppCompatActivity {
    RadioButton rdoCard, rdoKakao, rdoNaver;
    Button btnPay;
    private int stuck = 10;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        BootpayAnalytics.init(this, "59a4d326396fa607cbe75de5");


        rdoCard = findViewById(R.id.pay_card);
        rdoKakao = findViewById(R.id.pay_kakao);
        rdoNaver = findViewById(R.id.pay_naver);

        btnPay = findViewById(R.id.btn_payment);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (rdoCard.isChecked()){
                    paymentCard();
                } else if (rdoKakao.isChecked()){
                    paymentKaKAo();
                } else if (rdoNaver.isChecked()){
                    paymentNaver();
                }

                /*BootUser bootUser = new BootUser().setPhone("010-1234-1234"); //자기 전화번호
                BootExtra bootExtra = new BootExtra().setQuotas(new int[]{0, 2, 3});*/
                /*Bootpay.init(getFragmentManager())
                        .setApplicationId("59a4d326396fa607cbe75de5") // 해당 프로젝트(안드로이드)의 application id값
                        .setPG(PG.KCP) // 결제할 PG 사
                        .setMethod(Method.KAKAO) // 결제수단
                        .setContext(MainActivity.this)
                        .setBootUser(bootUser)
                        .setBootExtra(bootExtra)
                        .setUX(UX.PG_DIALOG)
//                      .setUserPhone("010-1234-5678") // 구매자 전화번호
                        .setName("맥북프로's 임다") // 결제할 상품명
                        .setOrderId("1234") // 결제 고유번호expire_month
                        .setPrice(100) // 결제할 금액

                        //개발자 메뉴얼에 가면 더 추가할 수 있음 .

                        .addItem("마우's 스", 1, "ITEM_CODE_MOUSE", 100) // 주문정보에 담길 상품정보, 통계를 위해 사용
                        .addItem("키보드", 1, "ITEM_CODE_KEYBOARD", 200, "패션", "여성상의", "블라우스") // 주문정보에 담길 상품정보, 통계를 위해 사용
                        .onConfirm(new ConfirmListener() { // 결제가 진행되기 바로 직전 호출되는 함수로, 주로 재고처리 등의 로직이 수행
                            @Override
                            public void onConfirm(@Nullable String message) {

                                if (0 < stuck) Bootpay.confirm(message); // 재고가 있을 경우.
                                else Bootpay.removePaymentWindow(); // 재고가 없어 중간에 결제창을 닫고 싶을 경우
                                Log.d("confirm", message);
                            }
                        })
                        .onDone(new DoneListener() { // 결제완료시 호출, 아이템 지급 등 데이터 동기화 로직을 수행합니다
                            @Override
                            public void onDone(@Nullable String message) {
                                Log.d("done", message);
                            }
                        })
                        .onReady(new ReadyListener() { // 가상계좌 입금 계좌번호가 발급되면 호출되는 함수입니다.
                            @Override
                            public void onReady(@Nullable String message) {
                                Log.d("ready", message);
                            }
                        })
                        .onCancel(new CancelListener() { // 결제 취소시 호출
                            @Override
                            public void onCancel(@Nullable String message) {

                                Log.d("cancel", message);
                            }
                        })
                        .onError(new ErrorListener() { // 에러가 났을때 호출되는 부분
                            @Override
                            public void onError(@Nullable String message) {
                                Log.d("error", message);
                            }
                        })
                        .onClose(
                                new CloseListener() { //결제창이 닫힐때 실행되는 부분
                                    @Override
                                    public void onClose(String message) {
                                        Log.d("close", "close");
                                    }
                                })
                        .request();
            }
        });*/
            }

            public void paymentKaKAo(){
                BootUser bootUser = new BootUser().setPhone("010-1234-1234"); //자기 전화번호
                BootExtra bootExtra = new BootExtra().setQuotas(new int[]{0, 2, 3});

                Bootpay.init(getFragmentManager())
                        .setApplicationId("59a4d326396fa607cbe75de5") // 해당 프로젝트(안드로이드)의 application id값
                        .setPG(PG.KCP) // 결제할 PG 사
                        .setMethod(Method.KAKAO) // 결제수단
                        .setContext(Payment_AC.this)
                        .setBootUser(bootUser)
                        .setBootExtra(bootExtra)
                        .setUX(UX.PG_DIALOG)
//                      .setUserPhone("010-1234-5678") // 구매자 전화번호
                        .setName("맥북프로's 임다") // 결제할 상품명
                        .setOrderId("1234") // 결제 고유번호expire_month
                        .setPrice(100) // 결제할 금액

                        //개발자 메뉴얼에 가면 더 추가할 수 있음 .

                        .addItem("마우's 스", 1, "ITEM_CODE_MOUSE", 100) // 주문정보에 담길 상품정보, 통계를 위해 사용
                        .addItem("키보드", 1, "ITEM_CODE_KEYBOARD", 200, "패션", "여성상의", "블라우스") // 주문정보에 담길 상품정보, 통계를 위해 사용
                        .onConfirm(new ConfirmListener() { // 결제가 진행되기 바로 직전 호출되는 함수로, 주로 재고처리 등의 로직이 수행
                            @Override
                            public void onConfirm(@Nullable String message) {

                                if (0 < stuck) Bootpay.confirm(message); // 재고가 있을 경우.
                                else Bootpay.removePaymentWindow(); // 재고가 없어 중간에 결제창을 닫고 싶을 경우
                                Log.d("confirm", message);
                            }
                        })
                        .onDone(new DoneListener() { // 결제완료시 호출, 아이템 지급 등 데이터 동기화 로직을 수행합니다
                            @Override
                            public void onDone(@Nullable String message) {
                                Log.d("done", message);
                            }
                        })
                        .onReady(new ReadyListener() { // 가상계좌 입금 계좌번호가 발급되면 호출되는 함수입니다.
                            @Override
                            public void onReady(@Nullable String message) {
                                Log.d("ready", message);
                            }
                        })
                        .onCancel(new CancelListener() { // 결제 취소시 호출
                            @Override
                            public void onCancel(@Nullable String message) {

                                Log.d("cancel", message);
                            }
                        })
                        .onError(new ErrorListener() { // 에러가 났을때 호출되는 부분
                            @Override
                            public void onError(@Nullable String message) {
                                Log.d("error", message);
                            }
                        })
                        .onClose(
                                new CloseListener() { //결제창이 닫힐때 실행되는 부분
                                    @Override
                                    public void onClose(String message) {
                                        Log.d("close", "close");
                                        Intent intent=new Intent(Payment_AC.this,Receipt.class);
                                        startActivity(intent);
                                    }
                                })
                        .request();
            }

            public void paymentCard(){
                BootUser bootUser = new BootUser().setPhone("010-1234-1234"); //자기 전화번호
                BootExtra bootExtra = new BootExtra().setQuotas(new int[]{0, 2, 3});

                Bootpay.init(getFragmentManager())
                        .setApplicationId("59a4d326396fa607cbe75de5") // 해당 프로젝트(안드로이드)의 application id값
                        .setPG(PG.KCP) // 결제할 PG 사
                        .setMethod(Method.CARD) // 결제수단
                        .setContext(Payment_AC.this)
                        .setBootUser(bootUser)
                        .setBootExtra(bootExtra)
                        .setUX(UX.PG_DIALOG)
//                      .setUserPhone("010-1234-5678") // 구매자 전화번호
                        .setName("맥북프로's 임다") // 결제할 상품명
                        .setOrderId("1234") // 결제 고유번호expire_month
                        .setPrice(100) // 결제할 금액

                        //개발자 메뉴얼에 가면 더 추가할 수 있음 .

                        .addItem("마우's 스", 1, "ITEM_CODE_MOUSE", 100) // 주문정보에 담길 상품정보, 통계를 위해 사용
                        .addItem("키보드", 1, "ITEM_CODE_KEYBOARD", 200, "패션", "여성상의", "블라우스") // 주문정보에 담길 상품정보, 통계를 위해 사용
                        .onConfirm(new ConfirmListener() { // 결제가 진행되기 바로 직전 호출되는 함수로, 주로 재고처리 등의 로직이 수행
                            @Override
                            public void onConfirm(@Nullable String message) {

                                if (0 < stuck) Bootpay.confirm(message); // 재고가 있을 경우.
                                else Bootpay.removePaymentWindow(); // 재고가 없어 중간에 결제창을 닫고 싶을 경우
                                Log.d("confirm", message);
                            }
                        })
                        .onDone(new DoneListener() { // 결제완료시 호출, 아이템 지급 등 데이터 동기화 로직을 수행합니다
                            @Override
                            public void onDone(@Nullable String message) {
                                Log.d("done", message);
                            }
                        })
                        .onReady(new ReadyListener() { // 가상계좌 입금 계좌번호가 발급되면 호출되는 함수입니다.
                            @Override
                            public void onReady(@Nullable String message) {
                                Log.d("ready", message);
                            }
                        })
                        .onCancel(new CancelListener() { // 결제 취소시 호출
                            @Override
                            public void onCancel(@Nullable String message) {

                                Log.d("cancel", message);
                            }
                        })
                        .onError(new ErrorListener() { // 에러가 났을때 호출되는 부분
                            @Override
                            public void onError(@Nullable String message) {
                                Log.d("error", message);
                            }
                        })
                        .onClose(
                                new CloseListener() { //결제창이 닫힐때 실행되는 부분
                                    @Override
                                    public void onClose(String message) {
                                        Log.d("close", "close");
                                        Intent intent=new Intent(Payment_AC.this,Receipt.class);
                                        startActivity(intent);
                                    }
                                })
                        .request();
            }

            public void paymentNaver(){
                BootUser bootUser = new BootUser().setPhone("010-1234-1234"); //자기 전화번호
                BootExtra bootExtra = new BootExtra().setQuotas(new int[]{0, 2, 3});

                Bootpay.init(getFragmentManager())
                        .setApplicationId("59a4d326396fa607cbe75de5") // 해당 프로젝트(안드로이드)의 application id값
                        .setPG(PG.KCP) // 결제할 PG 사
                        .setMethod(Method.NPAY) // 결제수단
                        .setContext(Payment_AC.this)
                        .setBootUser(bootUser)
                        .setBootExtra(bootExtra)
                        .setUX(UX.PG_DIALOG)
//                      .setUserPhone("010-1234-5678") // 구매자 전화번호
                        .setName("맥북프로's 임다") // 결제할 상품명
                        .setOrderId("1234") // 결제 고유번호expire_month
                        .setPrice(100) // 결제할 금액

                        //개발자 메뉴얼에 가면 더 추가할 수 있음 .

                        .addItem("마우's 스", 1, "ITEM_CODE_MOUSE", 100) // 주문정보에 담길 상품정보, 통계를 위해 사용
                        .addItem("키보드", 1, "ITEM_CODE_KEYBOARD", 200, "패션", "여성상의", "블라우스") // 주문정보에 담길 상품정보, 통계를 위해 사용
                        .onConfirm(new ConfirmListener() { // 결제가 진행되기 바로 직전 호출되는 함수로, 주로 재고처리 등의 로직이 수행
                            @Override
                            public void onConfirm(@Nullable String message) {

                                if (0 < stuck) Bootpay.confirm(message); // 재고가 있을 경우.
                                else Bootpay.removePaymentWindow(); // 재고가 없어 중간에 결제창을 닫고 싶을 경우
                                Log.d("confirm", message);
                            }
                        })
                        .onDone(new DoneListener() { // 결제완료시 호출, 아이템 지급 등 데이터 동기화 로직을 수행합니다
                            @Override
                            public void onDone(@Nullable String message) {
                                Log.d("done", message);
                            }
                        })
                        .onReady(new ReadyListener() { // 가상계좌 입금 계좌번호가 발급되면 호출되는 함수입니다.
                            @Override
                            public void onReady(@Nullable String message) {
                                Log.d("ready", message);
                            }
                        })
                        .onCancel(new CancelListener() { // 결제 취소시 호출
                            @Override
                            public void onCancel(@Nullable String message) {

                                Log.d("cancel", message);
                            }
                        })
                        .onError(new ErrorListener() { // 에러가 났을때 호출되는 부분
                            @Override
                            public void onError(@Nullable String message) {
                                Log.d("error", message);
                            }
                        })
                        .onClose(
                                new CloseListener() { //결제창이 닫힐때 실행되는 부분
                                    @Override
                                    public void onClose(String message) {
                                        Log.d("close", "close");
                                        Intent intent=new Intent(Payment_AC.this,Receipt.class);
                                        startActivity(intent);
                                    }

                                })
                        .request();
            }



        });
    }
}
