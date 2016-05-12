package com.ketangpai.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ketangpai.activity.AccountUpdateActivity;
import com.ketangpai.base.BasePresenterFragment;
import com.ketangpai.nan.ketangpai.R;
import com.ketangpai.presenter.AccountUpdatePresenter;
import com.ketangpai.viewInterface.AccountUpdateViewInterface;

/**
 * Created by Administrator on 2016/4/19.
 */
public class AccountUpdateFragment extends BasePresenterFragment<AccountUpdateViewInterface, AccountUpdatePresenter> implements AccountUpdateViewInterface {
    public static final String TAG = "===AccountUpdateFragment";

    private String mColumnName;
    private String mColumnCode;
    private String mColumnValue;
    private String account;
    private String u_id;

    @Override
    protected void initVarious() {
        super.initVarious();
        u_id = mContext.getSharedPreferences("user", 0).getString("u_id", "");
        mColumnName = ((AccountUpdateActivity) getActivity()).getColunmnName();
        switch (mColumnName) {
            case "密码":
                mColumnCode = "password";
                mColumnValue = mContext.getSharedPreferences("user", 0).getString("password", "");
                break;
            case "学校":
                mColumnCode = "school";
                mColumnValue = mContext.getSharedPreferences("user", 0).getString("school", "");
                break;
            case "学号":
                mColumnCode = "number";
                mColumnValue = String.valueOf(mContext.getSharedPreferences("user", 0).getInt("number", -1));
                break;
            case "姓名":
                mColumnCode = "name";
                mColumnValue = mContext.getSharedPreferences("user", 0).getString("name", "");
                break;

            default:
                break;
        }

        account = mContext.getSharedPreferences("user", 0).getString("account", "");
    }

    @Override
    protected int getLayoutId() {
        if (mColumnCode.equals("password")) {
            return R.layout.fragment_account_update_password;
        } else {
            return R.layout.fragment_account_update;
        }
    }

    @Override
    protected void initView() {
        super.initView();
        if (mColumnCode.equals("password")) {
            //修改密码界面
            final EditText et_dialog_password_confirm = (EditText) view.findViewById(R.id.et_dialog_password_confirm);
            final EditText et_dialog_password_new = (EditText) view.findViewById(R.id.et_dialog_password_new);
            final EditText et_dialog_password_pass = (EditText) view.findViewById(R.id.et_dialog_password_pass);
            Button btn_dialog_password_save = (Button) view.findViewById(R.id.btn_dialog_password_save);

            btn_dialog_password_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String confirmPwd = et_dialog_password_confirm.getText().toString();
                    String newPwd = et_dialog_password_new.getText().toString();
                    String passPwd = et_dialog_password_pass.getText().toString();
                    updatePassword(passPwd, newPwd, confirmPwd);
                }
            });
        } else {
            //修改学校,名字,学号界面
            TextView tv_dialog_content = (TextView) view.findViewById(R.id.tv_dialog_content);
            Button btn_dialog_password_save = (Button) view.findViewById(R.id.btn_dialog_password_save);
            final EditText et_dialog_content_new = (EditText) view.findViewById(R.id.et_dialog_content_new);

            switch (mColumnCode) {
                case "school":
                    tv_dialog_content.setText("学校");
                    et_dialog_content_new.setHint("请输入新学校");
                    et_dialog_content_new.setText(mColumnValue);
                    et_dialog_content_new.setSelection(mColumnValue.length());
                    break;
                case "name":
                    tv_dialog_content.setText("名字");
                    et_dialog_content_new.setHint("请输入新名字");
                    et_dialog_content_new.setText(mColumnValue);
                    et_dialog_content_new.setSelection(mColumnValue.length());

                    break;
                case "number":
                    tv_dialog_content.setText("学号");
                    et_dialog_content_new.setHint("请输入新学号");
                    et_dialog_content_new.setText(String.valueOf(mColumnValue));
                    et_dialog_content_new.setSelection(mColumnValue.length());
                    et_dialog_content_new.setFilters(new InputFilter[]{new InputFilter.LengthFilter(9)});
                    et_dialog_content_new.setInputType(InputType.TYPE_CLASS_NUMBER);
                    break;
                default:
                    break;
            }

            btn_dialog_password_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!et_dialog_content_new.equals("")) {
                        mColumnValue = et_dialog_content_new.getText().toString();
                        mPresenter.updateUserInfo(mContext, u_id, mColumnCode, mColumnValue);
                        showUpdateOnCompleteDialog(mColumnName, 3);
                    } else {
                        showUpdateOnCompleteDialog(mColumnName, 0);
                    }
                }
            });
        }

    }

    /**
     * 检查密码修改是否正确
     *
     * @param passPwd
     * @param newPwd
     * @param confirmPwd
     */
    private void updatePassword(String passPwd, String newPwd, String confirmPwd) {
        if (!passPwd.equals("") && !newPwd.equals("") && !confirmPwd.equals("")) {
            if (passPwd.equals(mColumnValue)) {
                if (!newPwd.equals(confirmPwd)) {
                    showUpdateOnCompleteDialog("密码", 2);
                } else {
                    mPresenter.updateUserInfo(mContext, u_id, "password", newPwd);
                    showUpdateOnCompleteDialog("密码", 3);
                }
            } else {
                showUpdateOnCompleteDialog("密码", 1);
            }
        } else {
            showUpdateOnCompleteDialog("密码", 0);
        }
    }

    /**
     * 显示修改完成对话框
     *
     * @param colunmnName 修改哪一项
     * @param type
     */
    private void showUpdateOnCompleteDialog(String colunmnName, int type) {
        StringBuffer title = new StringBuffer();
        StringBuffer message = new StringBuffer();
        AlertDialog dialog = null;
        title = title.append(colunmnName);
        switch (type) {
            case 0:
                message = message.append("不能为空,请重新填写");
                title.append("修改失败");
                break;
            case 1:
                title.append("修改失败");
                message = message.append("旧密码输入错误,请重新填写");
                break;
            case 2:
                title.append("修改失败");
                message = message.append("两次输入的密码不一致,请重新填写");
                break;
            case 3:
                title.append("修改成功");
                break;
            default:
                break;
        }
        if (type == 3) {

            dialog = new AlertDialog.Builder(mContext).setTitle(title.toString())
                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            intent.putExtra("columnCode", mColumnCode);
                            intent.putExtra("columnValue", mColumnValue);
                            getActivity().setResult(100, intent);
                            getActivity().finish();
                        }
                    }).create();
            dialog.show();

        } else {
            dialog = new AlertDialog.Builder(mContext).setTitle(title.toString()).setMessage(message.toString())
                    .setPositiveButton("确认", null).create();
            dialog.show();
        }
        dialog.setCanceledOnTouchOutside(false);
    }

    @Override
    protected AccountUpdatePresenter createPresenter() {
        return new AccountUpdatePresenter();
    }

    @Override
    public void updateUserInfoOnComplete(String columnName) {
//        Log.i(TAG, "result  colunmnName=" + columnName);
        if (columnName.equals("-1")) {
            new AlertDialog.Builder(mContext).setTitle("修改失败").setPositiveButton("确认", null).create().show();

        } else {
            switch (columnName) {
                case "password":
                    mContext.getSharedPreferences("user", 0).edit().putString("password", mColumnValue).commit();
                    break;
                case "school":
                    mContext.getSharedPreferences("user", 0).edit().putString("school", mColumnValue).commit();
                    break;
                case "number":
                    mContext.getSharedPreferences("user", 0).edit().putInt("number", Integer.parseInt(mColumnValue)).commit();
                    break;
                case "name":
                    mContext.getSharedPreferences("user", 0).edit().putString("name", mColumnValue).commit();
                    break;
                default:
                    break;
            }
        }
    }
}
