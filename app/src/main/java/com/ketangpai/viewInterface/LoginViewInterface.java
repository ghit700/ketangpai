package com.ketangpai.viewInterface;

import com.ketangpai.bean.User;

/**
 * Created by nan on 2016/4/16.
 */
public interface LoginViewInterface {
    /**
     * 登录
     *
     * @param
     */
    public void login(User user);

    public void showLoginLoading();

    public void hideLoginLoading();

    public void showRegisterLoading();

    public void hideRegisterLoading();

    /**
     * @param
     */
    public void register(int ret);
}
