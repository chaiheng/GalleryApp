package com.example.galleryapp.ui;

import android.content.Intent;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.galleryapp.R;
import com.example.galleryapp.databinding.ActivityLoginBinding;
import com.example.modulebase.extend.BaseActivity;
import com.example.modulebase.extend.WebActivity;
import com.example.modulebase.util.RxCount;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements View.OnClickListener {

    private boolean isAgree = false;

    @Override
    protected void initView() {
        binding.tvBtnLogin.setOnClickListener(this);
        binding.etMsg.setOnClickListener(this);
        binding.ivBack.setOnClickListener(this);
        binding.ivAgree.setOnClickListener(this);
        binding.tvSms.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        try {
            SpannableString spannableString = new SpannableString("已阅读并同意《用户协议》与《隐私政策》");


            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(mActivity, WebActivity.class)
                            .putExtra(WebActivity.webTitle, "用户协议")
                            .putExtra(WebActivity.webUrl, "https://www.vod.dianzhenbox.com/user.html"));
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    ds.setColor(0xff3692FF);//设置颜色
                    ds.setUnderlineText(false);//去掉下划线
                }
            };
            int start = spannableString.toString().indexOf("《用户协议》");
            int end = "《用户协议》".length();
            spannableString.setSpan(clickableSpan, start, start + end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

            ClickableSpan clickableSpan2 = new ClickableSpan() {
                @Override
                public void onClick(View view) {

                    startActivity(new Intent(mActivity, WebActivity.class)
                            .putExtra(WebActivity.webTitle, "隐私协议")
                            .putExtra(WebActivity.webUrl, "https://www.vod.dianzhenbox.com/privacy.html"));
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    ds.setColor(0xff3692FF);//设置颜色
                    ds.setUnderlineText(false);//去掉下划线
                }

            };
            int start2 = spannableString.toString().indexOf("《隐私政策》");
            int end2 = "《隐私政策》".length();
            spannableString.setSpan(clickableSpan2, start2, start2 + end2, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

            binding.tvProtocol.setText(spannableString);
            binding.tvProtocol.setMovementMethod(LinkMovementMethod.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void timeDown() {
        RxCount.countdown(60)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        binding.tvSms.setText(integer + "S后重试");
                        binding.tvSms.setTextColor(0xff26ffda);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        binding.tvSms.setTextColor(0xff929aa0);
                        binding.tvSms.setText("获取验证码");
                    }

                    @Override
                    public void onComplete() {
                        binding.tvSms.setTextColor(0xff929aa0);
                        binding.tvSms.setText("获取验证码");
                    }
                });
    }


    @Override
    public void onClick(View v) {
        if (v == binding.tvBtnLogin) {
            String phone = binding.etPhone.getText().toString().trim();
            String code = binding.etMsg.getText().toString().trim();
            if (TextUtils.isEmpty(phone)) {
                showToast("请输入手机号");
                return;
            }
            if (TextUtils.isEmpty(code)) {
                showToast("请输入验证码");
                return;
            }
            if (!isAgree) {
                showToast("请勾选同意协议");
                return;
            }


        } else if (v == binding.tvSms) {
            String phone = binding.etPhone.getText().toString().trim();
            if (TextUtils.isEmpty(phone)) {
                return;
            }
            timeDown();
//                RetrofitServiceManager.getInstance().create(APIService.UserApi.class)
//                        .getSmsCode(phone)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .to(bindDestoryAutoDispose())
//                        .subscribe(new Observer<BaseNetBean>() {
//                            @Override
//                            public void onSubscribe(@NonNull Disposable d) {
//
//                            }
//
//                            @Override
//                            public void onNext(@NonNull BaseNetBean baseNetBean) {
//                                if (baseNetBean.code == 1) {
//
//                                }
//                            }
//
//                            @Override
//                            public void onError(@NonNull Throwable e) {
//
//                            }
//
//                            @Override
//                            public void onComplete() {
//
//                            }
//                        });


        } else if (binding.ivAgree == v) {
            if (!isAgree) {
                isAgree = true;
                binding.ivAgree.setImageResource(R.mipmap.ic_cert_agree);
            } else {
                isAgree = false;
                binding.ivAgree.setImageResource(R.mipmap.ic_cert_noagree);
            }
        } else if (binding.ivBack == v) {
            finish();
        }
    }
}