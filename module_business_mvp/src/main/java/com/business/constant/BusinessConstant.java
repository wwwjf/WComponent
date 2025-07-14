package com.business.constant;

/**
 * 全局常量
 */
public interface BusinessConstant {


    interface Api {
        /**
         * 起始页
         */
        int PAGE_NO = 1;

        /**
         * 数据量
         */
        int PAGE_SIZE = 10;

        int PAGE_SIZE_20 = 20;

        int PAGE_SIZE_50 = 50;

        /**
         * 接口兼容，区分新老版本，和flag一样
         */
        int VERSION = 1;

        /**
         * 接口兼容，区分新老版本，和version一样
         */
        String FLAG = "1.0";
    }

    /**
     * 首页主要业务模块
     */
    interface AppItem {
        /**
         * 交易记录
         */
        int TYPE_TRANSACTION = -1;
        /**
         * 全部
         */
        int TYPE_ALL_0 = 0;
        /**
         * 全球汇款
         */
        int TYPE_GLOBAL_REMITTANCE_1 = 1;
        /**
         * 付款
         */
        int TYPE_PAYMENT_2 = 2;
        /**
         * 收款
         */
        int TYPE_RECEIPT_3 = 3;

        /**
         * 货币兑换
         */
        int TYPE_CURRENCY_EXCHANGE_4 = 4;
        /**
         * 充值
         */
        int TYPE_RECHARGE_5 = 5;
        /**
         * 担保交易
         */
        int TYPE_GUARANTEE_TRANSACTIONS_6 = 6;

        /**
         * 资讯动态
         */
        int TYPE_DYNAMIC_INFOMATION_7 = 7;

        /**
         * 提现
         */
        int TYPE_WITHDRAW_8 = 8;

        /**
         * 加密货币
         */
        int TYPE_CRYPT_CURRENCY_9 = 9;

        /**
         * 红包
         */
        int TYPE_ENVELOPE_10 = 10;

    }

    /**
     * 实名认证状态
     */
    interface AuthStatus {
        int STATUS_0 = 0;//未认证       未认证
        int STATUS_3 = 3;//认证拒绝     未认证
        int STATUS_5 = 8;//认证即将到期  未认证  4.0版本数据为5，5.0版本修改为8

        int STATUS_1 = 1;//认证申请     初审
        int STATUS_6 = 6;//补录审核     初审

        int STATUS_4 = 4;//认证过期
        int STATUS_7 = 7;//补录复核     复核

        int STATUS_2 = 2;//认证完成
    }

    /**
     * KYC等级
     */
    interface KYCLevel {
        int LEVEL_0 = 0;//未认证
        int LEVEL_1 = 1;//1级
        int LEVEL_2 = 2;//2级
        int LEVEL_3 = 3;//3级
    }

    /**
     * 材料认证状态
     */
    interface AuthProfileStatus {
        int STATUS_0 = 0;//0非认证模式

        int STATUS_2 = 2;//2认证拒绝
        int STATUS_3 = 3;//3即将到期
        int STATUS_6 = 6;//6补录拒绝

        int STATUS_1 = 1;//1认证申请
        int STATUS_5 = 5;//5补录材料
        int STATUS_7 = 7;//7补录通过

        int STATUS_4 = 4;//4证件过期

        int STATUS_9 = 9;//9认证通过
    }


    interface OrderStatus {

        int STATUS_0 = 0;//待处理 订单初始状态 创建成功 正常情况下后台不会返回
        int STATUS_1 = 1;//待处理 订单处理中（红包待领取）
        int STATUS_3 = 3;//已领取
        int STATUS_4 = 4;//已过期

        int STATUS_5 = 5;//已撤销
        int STATUS_6 = 6;//失败
        int STATUS_7 = 7;//成功
        int STATUS_8 = 8;//已关闭
        int STATUS_9 = 9;//已冻结
        int STATUS_10 = 10;//已退款
        int STATUS_11 = 11;//异常

        int STATUS_12 = 12;//处理中 用户已经确认付款
        int STATUS_13 = 13;//处理中 汇款商A确认收款
        int STATUS_14 = 14;//处理中 汇款商B确认付款

        int STATUS_2 = 2;//申诉
        int STATUS_15 = 15;//申诉 用户申诉汇款商A
        int STATUS_16 = 16;//申诉 汇款商A申诉用户
        int STATUS_17 = 17;//申诉 用户申诉汇款商B
        int STATUS_18 = 18;//申诉 汇款商B申诉用户
        int STATUS_19 = 19;//现金待处理
        int STATUS_20 = 20;//失败不退手续费
    }

    interface IOrderOperate {

        /**
         * 充值--本地币种--创建者--撤销
         */
        String BTN_01 = "btn01";

        /**
         * 充值--本地币种--创建者--确认付款
         */
        String BTN_02 = "btn02";

        /**
         * 充值--本地币种--创建者/汇款商A--申诉
         */
        String BTN_03 = "btn03";

        /**
         * 充值--本地币种--汇款商A--确认收款
         */
        String BTN_04 = "btn04";

        /**
         * 充值--国际电汇--更新
         */
        String BTN_05 = "btn05";

        /**
         * 充值--国际电汇--撤销
         */
        String BTN_06 = "btn06";

        /**
         * 充值--电子货币--继续付款
         */
        String BTN_07 = "btn07";

        /**
         * 充值--电子钱包--撤销
         */
        String BTN_08 = "btn08";

        /**
         * 提现--本地银行--汇款商B--确认付款
         */
        String BTN_09 = "btn09";

        /**
         * 提现--本地银行--创建者--确认收款
         */
        String BTN_10 = "btn10";

        /**
         * 提现--本地银行--创建者/汇款商B--申诉
         */
        String BTN_11 = "btn11";

        /**
         * 全球汇款--创建者 确认付款
         */
        String BTN_12 = "btn12";

        /**
         * 全球汇款--创建者/汇款商A 申诉
         */
        String BTN_13 = "btn13";

        /**
         * 全球汇款--汇款商A 确认收款
         */
        String BTN_14 = "btn14";

        /**
         * 全球汇款--汇款商B 确认付款
         */
        String BTN_15 = "btn15";

        /**
         * 全球汇款--创建者、汇款商B 申诉
         */
        String BTN_16 = "btn16";

        /**
         * 全球汇款--创建者 确认收款
         */
        String BTN_17 = "btn17";

        /**
         * 查看形式发票
         */
        String BTN_18 = "btn18";

        /**
         * 全球汇款-创建者 取消订单
         */
        String BTN_19 = "btn19";

        /**
         * 提现--汇款商撤销
         */
        String BTN_20 = "btn20";

        /**
         * 充值-Safetypay继续付款
         */
        String BTN_21 = "btn21";
    }

    /***
     * 渠道编号
     */
    interface PayType {
        int TYPE_0 = 0;//Epay
        int TYPE_1 = 1;//国际电汇
        int TYPE_2 = 2;//西联汇款
        int TYPE_3 = 3;//速汇金
        int TYPE_4 = 4;//瑞亚速汇
        int TYPE_5 = 5;//渠道五号
        int TYPE_6 = 6;//6号渠道
        int TYPE_7 = 7;//银行转账2
        int TYPE_9 = 9;//银行转账
        int TYPE_11 = 11;//Perfect Money
        int TYPE_12 = 12;//AdvCash
        int TYPE_13 = 13;//WebMoney
        int TYPE_15 = 15;//OKPAY
        int TYPE_16 = 16;//Payeer
        int TYPE_17 = 17;//PayPal
        int TYPE_18 = 18;//Fasapay
        int TYPE_21 = 21;//Payza 国内转账
        int TYPE_22 = 22;//Fineshot
        int TYPE_23 = 23;//MaxPay
        int TYPE_101 = 101;//BTC
        int TYPE_102 = 102;//ETH
        int TYPE_103 = 103;//USDT
        int TYPE_104 = 104;//EOS
        int TYPE_105 = 105;//BCH
        int TYPE_106 = 106;//LTC
        int TYPE_107 = 107;//XRP
        int TYPE_108 = 108;//PAX
        int TYPE_109 = 109;//EUSD
        int TYPE_999 = 999;//现金
        int TYPE_41 = 41;//XM现金
        int TYPE_43 = 43;//IPay
        int TYPE_46 = 46;//safetyPay
        int TYPE_48 = 48;//韩币入金 Hanpass
        int TYPE_50 = 50;//GCC渠道
        int TYPE_52 = 52;//日本渠道
        int TYPE_53 = 53;//GCC现金渠道
        int TYPE_54 = 54;//mpurse 印度渠道


    }

    /**
     * 订单交易类型
     */
    interface TransactionType {
        int TYPE_20 = 20;//付款           付款
        int TYPE_21 = 21;//收款           收款
        int TYPE_24 = 24;//货币兑换        货币兑换
        int TYPE_25 = 25;//担保付款
        int TYPE_26 = 26;//担保收款
        int TYPE_27 = 27;//网关付款
        int TYPE_28 = 28;//网关收款
        int TYPE_29 = 29;//网关提现
        int TYPE_30 = 30;//退款
        int TYPE_40 = 40;//全球汇款         全球汇款
        int TYPE_41 = 41;//保证金
        int TYPE_42 = 42;//收款收益
        int TYPE_43 = 43;//付款收益
        int TYPE_44 = 44;//本地币种充值
        int TYPE_45 = 45;//本地币种提现
        int TYPE_46 = 46;//网关本地付款
        int TYPE_48 = 48;//韩币入金
        int TYPE_60 = 60;//银行电汇充值
        int TYPE_61 = 61;//银行电汇提现
        int TYPE_62 = 62;//提现退回
        int TYPE_70 = 70;//电子货币充值       充值
        int TYPE_71 = 71;//电子货币提现       提现
        int TYPE_72 = 72;//充值
        int TYPE_80 = 80;//加密货币充值
        int TYPE_81 = 81;//加密货币提现
        int TYPE_90 = 90;//调账
        int TYPE_95 = 95;//推荐返佣
        int TYPE_96 = 96;//口令红包
        int TYPE_97 = 97;//社交红包
        int TYPE_98 = 98;//领红包
    }

    interface OrderType {
        String TYPE_ALL = "";
        String TYPE_100 = "100";//付款
        String TYPE_101 = "101";//收款
        String TYPE_102 = "102";//兑换
        String TYPE_103 = "103";//充值
        String TYPE_104 = "104";//提现
        String TYPE_105 = "105";//退款
        String TYPE_106 = "106";//推荐返佣
        String TYPE_107 = "107";//分润
        String TYPE_110 = "110";//全球汇款
        String TYPE_111 = "111";//冻结
    }

    interface BusinessCode {
        int PAY = 100;//付款
        int RECEIVE = 101;//收款
        int EXCHANGE = 102;//兑换
        int RECHARGE = 103;//充值
        int WITHDRAW = 104;//提现
        int CRYPTO = 108;//加密货币买卖
        int API_RECEIVE = 114;//网关代收
        int API_PAY = 115;//网关代付
        int API_USER_PAY = 116;//网关用户付款
        int API_USER_RECEIVE = 117;//网关用户收款
    }


    interface senderOrReceiver {
        int SENDER = 1;//付款
        int RECEIVER = 2;//收款
    }


    interface Role {
        /**
         * 下单用户、创建者
         */
        String CREATOR = "0";
        /**
         * 汇款商A
         */
        String MERCHANT_A = "1";
        /**
         * 汇款商B
         */
        String MERCHANT_B = "2";
    }

    /**
     * 页面跳转来源标记
     */
    interface OriginActivity {
        /**
         * 设置页
         */
        String SETTINGS = "settings";
        /**
         * 个人主页
         */
        String MINE = "mine";
        /**
         * 登录
         */
        String LOGIN = "login";

        /**
         * 注册
         */
        String REGISTER = "register";

        /**
         * 付款
         */
        String PAYMENT = "payment";

        /**
         * 提现
         */
        String WITHDRAW = "withdraw";
        /**
         * 充值
         */
        String RECHARGE = "recharge";

        /**
         * 首页
         */
        String HOME = "home";

        /**
         * 银行收款联系人
         */
        String CONTACT_BANK = "contact_bank";

        /**
         * 忘记支付密码
         */
        String PAY_PASSWORD_FORGET = "pay_password_forget";

        /**
         * 修改支付密码
         */
        String PAY_PASSWORD_UPDATE = "pay_password_update";

        /**
         * 修改密保问题
         */
        String PASSWORD_QUESTION_UPDATE = "password_question_update";

        /**
         * 忘记密保问题
         */
        String PASSWORD_QUESTION_FORGET = "password_question_forget";

        /**
         * 账户添加页面
         */
        String ACCOUNT_ADD = "account_add";
    }

    /**
     * 付款币种
     */
    interface Currency {
        /**
         * 法币
         */
        int TYPE_LEGAL = 1;
        /**
         * 加密货币
         */
        int TYPE_VIRTUAL = 2;
    }

    interface TransactionWay {
        /**
         * 本地币种(国内银行/银行账户)
         */
        int TYPE_REMIT_1 = 1;
        /**
         * 电汇
         */
        int TYPE_BANK_2 = 2;
        /**
         * 电子钱包
         */
        int TYPE_ELE_3 = 3;
        /**
         * 现金
         */
        int TYPE_CASH_4 = 4;
        /**
         * 加密货币
         */
        int TYPE_CRYPTO_5 = 5;
    }

    /**
     * 货币兑换币种
     */
    interface ExchangeCurrencyType {

        /**
         * 原始币种 ==> 兑换币种 /正向兑换
         */
        int TYPE_DEST_1 = 1;
        /**
         * 兑换币种 ==> 原始币种 /反向兑换
         */
        int TYPE_ORIGINAL_2 = 2;
    }

    interface Currency_Deposit {
        /**
         * 充值操作
         */
        int FLAG_RECHARGE = 1;
        /**
         * 提现操作
         */
        int FLAG_WITHDRAW = 2;

        /**
         * 全球汇款操作
         */
        int FLAG_REMIT = 3;
    }

    /**
     * "order_type,ms_order_status,pay_type" 参数
     */
    interface Params {

        String ORDER_TYPE = "order_type";

        String MS_ORDER_STATUS = "ms_order_status";

        String PAY_TYPE = "pay_type";
    }

    /**
     * 客户端类型
     * 1PC 2H5 3IOS 4Android 5安游
     */
    interface SourceClient {
        int PC = 1;
        int H5 = 2;
        int IOS = 3;
        int ANDROID = 4;
        int AN_YOU = 5;
    }

    /**
     * 性别
     */
    interface Gender {

        int DEFAULT = -1;
        /**
         * 男
         */
        int male = 0;
        /**
         * 女
         */
        int female = 1;
        /**
         * 其他
         */
        int others = 2;
    }

    interface Flag {
        int _TRUE = 1;
        int _FALSE = 0;
    }

    /**
     * 谷歌验证器
     */
    interface GoogleAuth {
        int FLAG_TRUE = 1;
        int FLAG_FALSE = 0;

        /**
         * SP文件信息
         */
        String SP_GOOGLE_AUTH = "google_auth";
    }

    /**
     * 用户相关信息
     */
    interface UserInfo {
        /**
         * 用户登录信息 sp文件名称
         */
        String SP_USERINFO = "USER_INFO";

        /**
         * 用户是否登录
         */
        String IS_LOGIN = "mIsLogin";

        //        String TOKEN = "token";
        String SESSIONID = "sessionId";
        String COOKIE = "cookie";
        /**
         * google验证器二维码图片Base64值
         */
        String QRCODE = "qrcode";
        /**
         * google验证器值
         */
        String QRCODE_VALUE = "qrcode_value";
        /**
         * 用户注册类型
         */
        String REGISTER_TYPE = "registerType";
        /**
         * 注册时的国家地区
         */
        String REGISTER_COUNTRY_AREACODE = "register_country_area_code";
        /**
         * 上一次的登录方式
         * {@link BusinessConstant.Login}
         */
        String LAST_LOGIN_TYPE = "last_login_type";

        /**
         * 上一次登录的账号名
         */
        String LAST_LOGIN_ACCOUNT = "last_login_account";

        /**
         * 上一次手机登录的区号
         */
        String LAST_LOGIN_AREA_CODE = "last_login_area_code";

        String AREA_CODE = "area_code";

        String PHONE = "phone";

        /**
         * 上一次登录账号的密码
         */
        String LAST_LOGIN_PASSWORD = "last_login_password";
        /**
         * 系统通知
         */
        String MESSAGE_SYSTEM = "message_system";
        /**
         * 活动通知
         */
        String MESSAGE_ACTIVITY = "message_activity";
        /**
         * 资讯消息
         */
        String MESSAGE_NEWS = "message_news";
        /**
         * 登录邮箱
         */
        String MAIL = "mail";
        /**
         * 备份邮箱
         */
        String BACKUP_MAIL = "backup_mail";

        String RANDOMKEY = "randomkey";

        /**
         * 是否绑定谷歌验证码
         */
        String GOOGLE_AUTH_KEY = "hasGoogleAuthKey";

        /**
         * 是否开启谷歌验证码
         */
        String GOOGLE_AUTH_OPEN_KEY = "hasOpenGoogleAuthKey";

        /**
         * 是否设置支付密码
         */
        String PAY_PASSWORD_KEY = "hasPayPasswordKey";

        /**
         * 是否设置密保问题
         */
        String PASSWORD_QUESTION_KEY = "hasPasswordQuestionKey";

        /**
         * 手机号码
         */
        String MOBILE = "mobile";

        /**
         * 主要联系方式
         */
        String MAINCONTACTTYPE = "mainContactType";
        /**
         * 主要联系方式账号
         */
        String MAINCONTACTACCOUNT = "mainContactAccount";
        /**
         * 次要联系方式
         */
        String SECONDCONTACTTYPE = "secondContactType";
        /**
         * 次要联系方式账号
         */
        String SECONDCONTACTACCOUNT = "secondContactAccount";
        /**
         * 指纹开启
         */
        String FINGERPRINT = "FingerPrint";
        /**
         * 头像链接
         */
        String USERAVATAR = "user_avatar_";
        /**
         * 客户号
         */
        String UID = "cst_no";
        /**
         * 指纹登录显示的名称
         */
        String FINGER_LOGINNAME = "fingerprint_login_name";

        /**
         * 友盟统计账户的id字符串
         */
        String UMENG_ID = "UMEN_ID";

        String ACCESS_KEY = "ACCESS_KEY";

        /**
         * 滑块验证信息
         */
        String SLIDE_ACCESS_KEY = "EPAY-SLIDER-ACCESS-KEY";

        /**
         * kyc认证等级
         */
        String LEVEL = "KYC_LEVEL";
        /**
         * kyc认证状态
         */
        String KYC_STATUS = "KYC_STATUS";

        /**
         * 是否已提示收集隐私
         */
        String SERVICE_NOTICE = "SERVICE_NOTICE";
    }

    /**
     * 登录方式
     */
    interface Login {
        /**
         * 邮箱登录
         */
        int type_mail = 1;
        /**
         * 手机号登录
         */
        int type_phone = 2;
    }

    /**
     * 电话类型
     */
    interface Phone {
        /**
         * 手机
         */
        int TYPE_MOBILE = 1;
        /**
         * 办公电话
         */
        int TYPE_OFFICE = 2;
        /**
         * 家庭电话
         */
        int TYPE_HOME = 3;
    }

    /**
     * 权限
     */
    interface Permission {
        int REQUEST_CODE = 1001;
    }

    /**
     * 权限
     */
    interface PermissionTag {
        /**
         * 写数据
         */
        String WRITE_STORAGE = "write_storage";


        /**
         * 相机
         */
        String CAMERA_TAG = "camera_tag";
    }

    /**
     * 语言
     */
    interface Languange {
        /**
         * 中文
         */
        String ZH_CN = "ZH_CN";
        /**
         * 英文
         */
        String EN_US = "EN_US";
    }

    /**
     * 账户注册类型
     */
    interface RegisterType {

        String DEFAULT = "";
        /**
         * 个人注册
         */
        String PERSONAL = "1";
        /**
         * 公司注册
         */
        String COMPANY = "2";
    }

    /**
     * 支持账户类型
     */
    interface AccountType {
        /**
         * 企业
         */
        int COMPANY_SUPPORT_0 = 0;
        /**
         * 用户
         */
        int PERSONAL_SUPPORT_1 = 1;
        /**
         * 都支持
         */
        int ALL_SUPPORT_2 = 2;
    }

    /**
     * 通讯录账户类型
     */
    interface ContactType {

        /**
         * Epay账户收款人
         */
        int CONTACT_EPAY = 0;

        /**
         * 银行账户收款人
         */
        int CONTACT_BANK = 1;
    }

    /**
     * Activity 请求code
     */
    interface ActivityCode {

        /**
         * Standard activity result: operation canceled.
         */
        int RESULT_CANCELED = 0;
        /**
         * Standard activity result: operation succeeded.
         */
        int RESULT_OK = -1;
        /**
         * Start of user-defined activity results.
         */
        int RESULT_FIRST_USER = 1;

        /**
         * 登录成功后返回
         */
        int RESULT_LOGIN_CODE_2 = 2;


        int REQUEST_CODE_10001 = 10001;
        int REQUEST_CODE_10002 = 10002;
        int REQUEST_CODE_10003 = 10003;
        int REQUEST_CODE_10004 = 10004;
        int REQUEST_CODE_10005 = 10005;
        int REQUEST_CODE_10006 = 10006;
        int REQUEST_CODE_10007 = 10007;
        int REQUEST_CODE_10008 = 10008;
        int REQUEST_CODE_10009 = 10009;
        int REQUEST_CODE_10010 = 10010;
        int REQUEST_CODE_10011 = 10011;
    }

    /**
     * intent传值key
     */
    interface IntentKey {
        String PARAMS_1 = "params_1";
        String PARAMS_2 = "params_2";
        String PARAMS_3 = "params_3";
        String PARAMS_4 = "params_4";
        String PARAMS_5 = "params_5";
        String PARAMS_6 = "params_6";
        String PARAMS_7 = "params_7";
        String PARAMS_8 = "params_8";
    }

    /**
     * bundle传值key
     */
    interface BundleKey {
        String PARAMS_1 = "params_1";
        String PARAMS_2 = "params_2";
        String PARAMS_3 = "params_3";
        String PARAMS_4 = "params_4";

    }

    /**
     * 本地文件相关路径
     */
    interface FileConfig {
//        String DOWNLOAD_FILE_PATH = "DCIM/Camera";
        String DOWNLOAD_FILE_PATH = "/";
    }

    /**
     * 动态配置信息输入方式
     */
    interface ConfigInfo {
        /**
         * 下拉框(pc)，跳到新页面(APP)
         */
        int type_input_drop_down_0 = 0;

        int type_input_drop_down_1 = 1;
        /**
         * 日期选择
         */
        int type_input_date_2 = 2;

        /**
         * 普通输入框
         */
        int type_input_normal_3 = 3;

        /**
         * 文件选择(pdf,图片)
         */
        int type_input_picture_4 = 4;

        /**
         * 单选框
         */
        int type_input_drop_map_5 = 5;

        interface ImageType {
            String inner_path = "1";
            String base64 = "2";
            String out_url = "3";
        }

    }

    /**
     * 字典表字段名
     */
    interface dictCode {
        String COUNTRY_TYPE = "COUNTRY_TYPE";
        String COUNTRY_INFO = "COUNTRY_INFO";
        String CHANNEL_BANK = "REMIT_MERCHANT_CHANNEL_BANK";
    }

    /**
     * 文件上传相关信息
     */
    interface UploadFile {

        long IMAGE_MAX_SIZE = 2 * 1024 * 1024;
    }

    /**
     * 文件上传者
     */
    interface Invoice {

        int ORDER_CREATOR = 0;
        int MERCHANT_A = 1;
        int MERCHANT_B = 2;

    }

    interface USDT {
        String ERC20 = "USDT-ERC20";
        String OMNI = "USDT-Omni";
        String TRC20 = "USDT-TRC20";
    }

    /**
     * XM提现交易时验证账户状态
     */
    interface XmWithdrawStart {
        int FLAG_NORMAL_0 = 0; //正常
        int FLAG_CERTIFICATION_1 = 1; //实名认证
        int FLAG__PHONE_2 = 2;//绑定手机
        int FLAG_ADDRESS_3 = 3;//完善地址信息
    }

    /**
     * 超过交易金额提示
     */
    interface TradingLimit {
        int AMOUNT_USD_PERSIONAL_10000 = 10000;
        int AMOUNT_USD_COMPANY_50000 = 50000;
        int AMOUNT_EUR_PERSIONAL_9000 = 9000;
        int AMOUNT_EUR_COMPANY_45000 = 45000;
        int AMOUNT_GBP_PERSIONAL_8000 = 8000;
        int AMOUNT_GBP_COMPANY_40000 = 40000;
        int AMOUNT_HKD_PERSIONAL_72000 = 72000;
        int AMOUNT_HKD_COMPANY_360000 = 360000;
        int AMOUNT_JPY_PERSIONAL_1000000 = 1000000;
        int AMOUNT_JPY_COMPANY_5000000 = 5000000;

    }

    interface IPayChannel {

        /**
         * 国家
         */
        String COUNTRY = "CTY";
        /**
         * 城市网点
         */
        String CITY = "CITY";
        /**
         * 证件类型
         */
        String DOCUMENT_TYPE = "DOC";
        /**
         * 固定的证件类型
         */
        String DOCUMENT_TYPE_1 = "DOC1";

        /**
         * 职业
         */
        String OCCUPATION = "OCC";

        /**
         * 资金来源
         */
        String SOURCE_OF_FUND = "SOF";

        /**
         * 和收款人关系
         */
        String RELATIONSHIP = "REL";

        /**
         * 提现目的
         */
        String PURPOSE = "POR";
    }

    interface Flavor {
        interface GooglePlay {
            String NAME = "googlePlay";
            String PACKAGE_NAME = "com.android.vending";
        }
    }

    /**
     * 渠道数据列表分组节点
     */
    interface SECTION {
        int TYPE_LEVEL_CATEGORY = 0;
        int TYPE_LEVEL_CHANNEL = 1;
    }

    interface IPagePath {
        String PAGE_COUNTRY = "countryPage";

        String PAGE_SWIFT = "swiftPage";
    }

    /**
     * 时间间隔 单位：ms
     */
    interface ITime {
        /**
         * 订单状态更新 间隔查询
         */
        int TRANSACTION_INTERVAL = 3 * 1000;

        /**
         * edittext间隔多少秒，自动收起键盘
         */
        int EDITTEXT_INTERVAL = 2 * 1000;
    }

    interface IMessageType {
        int KYC = 1;
        int PAY_PASSWORD = 2;
        int PHONE = 3;
        int GOOGLE_CODE = 4;
    }
}
