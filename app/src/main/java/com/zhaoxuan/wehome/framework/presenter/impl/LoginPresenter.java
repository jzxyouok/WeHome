package com.zhaoxuan.wehome.framework.presenter.impl;

import com.zhaoxuan.wehome.framework.base.BasePresenter;
import com.zhaoxuan.wehome.framework.model.ICallBack;
import com.zhaoxuan.wehome.framework.model.ILoginModel;
import com.zhaoxuan.wehome.framework.model.impl.LoginModel;
import com.zhaoxuan.wehome.framework.presenter.IForgetPresenter;
import com.zhaoxuan.wehome.framework.presenter.ILoginPresenter;
import com.zhaoxuan.wehome.framework.presenter.IRegisterPresenter;
import com.zhaoxuan.wehome.framework.view.IForgetView;
import com.zhaoxuan.wehome.framework.view.ILoginView;
import com.zhaoxuan.wehome.framework.view.IRegisterView;
import com.zhaoxuan.wehome.support.utils.StrUtils;
import com.zhaoxuan.wehome.support.constants.Ints;
import com.zhaoxuan.wehome.support.dto.UserDto;
import com.zhaoxuan.wehome.view.activity.ChatActivity;
import com.zhaoxuan.wehome.view.activity.JoinFamilyActivity;

/**
 * Created by lizhaoxuan on 15/11/12.
 */
public class LoginPresenter extends BasePresenter implements ILoginPresenter, IForgetPresenter, IRegisterPresenter {

    private ILoginView loginView;
    private IForgetView forgetView;
    private IRegisterView registerView;
    private ILoginModel model;


    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
        init();
    }

    public LoginPresenter(IRegisterView registerView) {
        this.registerView = registerView;
        init();
    }

    public LoginPresenter(IForgetView forgetView) {
        this.forgetView = forgetView;
        init();
    }

    private void init() {
        model = new LoginModel();
    }

    @Override
    public void login(String account, String password) {
        if (StrUtils.isNullStr(account) || StrUtils.isNullStr(password)) {
            loginView.showToast("账号或密码不能为空哦~");
        } else {
            model.login(account, password, new ICallBack<UserDto>() {
                @Override
                public void callBackSuccess(UserDto dto) {
                    if (dto.isHaveHome()) {
                        loginView.loginSuccess(ChatActivity.class);
                    } else {
                        loginView.loginSuccess(JoinFamilyActivity.class);
                    }
                }

                @Override
                public void callBackError(String error) {
                    loginView.showToast(error);
                }
            });
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, String data) {
        if (resultCode == Ints.RESULT_OK) {
            switch (requestCode) {
                case Ints.INTENT_FORGET:
                    loginView.setAccountEdit(data);
                    break;
                case Ints.INTENT_REGISTER:
                    loginView.setAccountEdit(data);
                    break;
            }
        }
    }

    @Override
    public void forgetPassword(String account) {
        if (account.equals("")) {
            forgetView.showToast("邮箱不能为空哦~");
        } else {
            model.forgetPassword(account, new ICallBack<String>() {
                @Override
                public void callBackSuccess(String t) {
                    forgetView.sendSuccess(t);
                }

                @Override
                public void callBackError(String error) {
                    forgetView.showToast(error);
                }
            });
        }
    }

    @Override
    public void register(String account, String password, String passwordAgain) {
        if (StrUtils.isNullStr(account) || StrUtils.isNullStr(password) || StrUtils.isNullStr(passwordAgain)) {
            registerView.showToast("账号或密码不能为空哦~");
            return;
        } else if (!password.equals(passwordAgain)) {
            registerView.showToast("两次密码要相同哦~");
        } else {
            model.register(account, password, new ICallBack<UserDto>() {
                @Override
                public void callBackSuccess(UserDto t) {
                    registerView.sendSuccess();
                }

                @Override
                public void callBackError(String error) {
                    registerView.showToast(error);
                }
            });
        }
    }
}
