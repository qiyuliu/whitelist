package corn.jamboy.constants;

/**
 * 错误码定义
 * 错误码按模块划分进行定义
 * 注：10000-9999 表系统级默认码
 * 其他错错误以 6位表识
 * 
 * 100开头表 系统级通用错误码
 * 101开头表 舍员模块
 * 102开头表 计费模块
 * 103开头表 认证模块
 * 
 * 901开头表 礼包模块
 * 
 * 
 * 
 * @author jhb
 *
 */
public class ResultConstants {

	/**
	 * 0 代表正常
	 */
	public static final Integer STATE_NORMAL = 0;

	/**
	 * -1代表异常
	 */
	public static final Integer STATE_EXCEPTION = -1;

	/**
	 * 1代表错误
	 */
	public static final Integer STATE_FAILURE = 1;

	/**
	 * 正常码
	 */
	public static final String SYS_NORMAL_CODE = "10000";

	/**
	 * 系统模块未知错误
	 */
	public static final String SYS_UNKOWN_CODE = "10001";

	/**
	 * 系统配置异常
	 */
	public static final String SYS_CONFIGURE_CODE = "100002";
	
	/**
	 * 系统参数异常
	 */
	public static final String SYS_ARGUMENT_CODE = "100009";
	
	/**
	 * 缺少必要参数
	 */
	public static final String SYS_LACK_PARAM_CODE = "100100";
	
	/**
	 * 参数验证失败
	 */
	public static final String SYS_PARAM_VALIDATE_FAILURE_CODE = "100101";

	/**
	 * 会员模块错误
	 */
	public static final String ACC_UNKOWN_CODE = "101000";

	/**
	 * 账号已存在
	 */
	public static final String ACC_EXIST_CODE = "101001";

	/**
	 * 账号不存在
	 */
	public static final String ACC_NOT_EXIST_CODE = "101002";
	/**
	 * 密码错误
	 */
	public static final String ACC_PWD_ERROR_CODE = "101003";
	
	/**
	 * 帐号注册失败
	 */
	public static final String ACC_register_ERROR_CODE = "101099";
	
	/**
	 * 帐号注册成功
	 */
	public static final String ACC_register_SUCCESS_CODE = "101100";
	/**
	 * 会话认证失败(第三方会话认证失败)
	 */
	@Deprecated
	public static final String ACC_AUTH_CODE = "101901";

	/**
	 * 帐号状态异常 被冻结
	 */
	public static final String ACC_STATUS_FREEZE_CODE = "101004";

	/**
	 * 激活码未找到或已经绑定了帐号
	 */
	public static final String ACC_BIND_ACTIVATION_CODE_FAILD_CODE = "103009";

	/**
	 * 该帐号不可重复激活
	 */
	public static final String ACC_NOT_REPEAT_BIND_ACTIVATION_CODE = "103011";

	/**
	 * 账本冻结异常
	 */
	public static final String ACC_BILL_FREEZE_CODE = "101005";

	/**
	 * 账本余额不足
	 */
	public static final String ACC_BILL_BALANCE_NOT_ENOUGH_CODE = "101006";

	
	
	/**
	 * 邮箱格式错误
	 */
	@Deprecated
	public static final String ACC_EMAIL_FORMATTER_CODE = "101004";


	/**
	 * 注册信息验证失败
	 */
	@Deprecated
	public static final String ACC_REGIST_INFO_ERROR_CODE = "101007";
	
	/**
	 * 注册用户名格式不正确
	 */
	@Deprecated
	public static final String ACC_REGIST_ACC_FORMAT_CODE = "101009";

	/**
	 * 注册两次密码不同
	 */
	@Deprecated
	public static final String ACC_REGIST_RPASSWORD_NOT_SAME_CODE = "101011";
	
	
	
	/**
	 * 认证失败
	 */
	public static final String AUTH_FAILURE_CODE = "103000";
	/**
	 * 账号未找到
	 */
	public static final String AUTH_ACC_NOT_FOUND_CODE = "103001";
	/**
	 * 登录失败,用户名或账号不正确
	 */
	public static final String AUTH_LOGIN_FAILURE_CODE = "103002";
	/**
	 * 禁止进入
	 */
	public static final String AUTH_PREVENT_ENTRY_CODE = "103003";
	/**
	 * 凭证验证失败
	 */
	public static final String AUTH_CREDENTIAL_VALIDATE_CODE = "103004";
	/**
	 * 票据不合法
	 */
	public static final String AUTH_TICKET_INVALID_CODE = "103005";
	/**
	 * 授权失败
	 */
	public static final String AUTH_AUTHORIZE_FAILURE_CODE = "103006";
	/**
	 * 服务未授权
	 */
	public static final String AUTH_SERVICE_UNAUTHORIZE_CODE = "103007";
	
	

	/**
	 * 计费错误
	 */
	public static final String BILL_UNKOWN_CODE = "102000";

	/**
	 * 订单未支付
	 */
	public static final String BILL_ORDER_NOT_PAY_CODE = "102001";
	/**
	 * 订购关系已经存在
	 */
	public static final String BILL_ORDER_RELATIONSHIP_EXIST_CODE = "102002";

	/**
	 * 订单不存在
	 */
	public static final String BILL_ORDER_NOT_EXIST_CODE = "102003";
	
	/**
	 * 订单己支付
	 */
	public static final String BILL_ORDER_PARYED_CODE = "102004";

	/**
	 * 订单不属于该用户
	 */
	public static final String BILL_ORDER_OWNER_CODE = "102005";

	/**
	 * 订单金额不正确
	 */
	public static final String BILL_ORDER_PRICE_CODE = "102006";

	/**
	 * 订单状态不正确
	 */
	public static final String BILL_ORDER_STATUS_CODE = "102007";

	/**
	 * 道具不存在
	 */
	public static final String BILL_ITEMS_NOT_EXIST_CODE = "102008";
	/**
	 * 道具已过期
	 */
	public static final String BILL_ITEMS_EXPIRED_CODE = "102009";
	/**
	 * 道具不可售卖
	 */
	public static final String BILL_ITEMS_NOT_SALE_CODE = "102010";
	/**
	 * 道具价格不匹配
	 */
	public static final String BILL_ITEMS_PRICE_NOT_MATCH_CODE = "102011";
	
	/**
	 * 规则不存在
	 */
	public static final String BILL_RULE_NOT_EXIST_CODE = "102012";
	
	
	/**
	 * 订单己完成
	 */
	public static final String BILL_ORDER_COMPLETED_CODE = "102013";
	
	/**
	 * 订单己取消
	 */
	public static final String BILL_ORDER_CANCLED_CODE = "102014";
	
	/**
	 * 订单己被领取
	 */
	public static final String BILL_ORDER_DELIVERED_CODE = "102015";
	
	
	/**
	 * 订单还未被领取
	 */
	public static final String BILL_ORDER_NOT_DELIVERED_CODE = "102016";
	
	/**
	 * 交易数据验证错误
	 */
	public static final String BILL_TRADE_VALIDATE_CODE  = "102017";
	
	/**
	 * 交易失败
	 */
	public static final String BILL_TRADE_FAILUER_CODE  = "102018";
	
	/**
	 * 交易进行中
	 */
	public static final String BILL_TRADE_DOING_CODE  = "102019";
	
	/**
	 * 交易等待买家支付
	 */
	public static final String BILL_TRADE_WAIT_BUYER_PAY_CODE  = "102020";
	
	/**
	 * 交易己完成，重复购买
	 */
	public static final String BILL_TRADE_REPEAT_PURCHASE_CODE  = "102021";
	
	/**
	 * 订单正在发货中，不能再次领取,等待确认
	 */
	public static final String BILL_ORDER_DELIVERING_CODE = "102022";
	
	
	/**
	 * 订单里的物品己被领取
	 */
	@Deprecated
	public static final String BILL_ORDER_RDELIVERED_CODE = "102007";
	

	/**
	 * 渠道信息未知异常
	 */
	public static final String CHANNEL_UNKOWN_CODE = "104000";

	/**
	 * 渠道不存在
	 */
	public static final String CHANNEL_NOT_EXIST_CODE = "104001";
	/**
	 * 渠道身份验证失败
	 */
	public static final String CHANNEL_AUTH_CODE = "104002";
	/**
	 * 该渠道不支持支付
	 */
	public static final String CHANNEL_NOT_SUPPORT_PAY_CODE = "104003";
	
	
	
	
	
	/**
	 * 礼包未知异常
	 */
	public static final String GIFT_UNKOWN_CODE = "901000";
	
	
	/**
	 * 礼包兑换码未找到
	 */
	public static final String GIFT_CODE_NOT_FOUND_CODE = "901001";
	
	
	/**
	 * 礼包兑换码状态不正确
	 */
	public static final String GIFT_CODE_STATUS_CODE = "901002";
	
	/**
	 * 礼包兑换码使用还未开始
	 */
	public static final String GIFT_CODE_USE_NOT_START_CODE = "901003";
	
	
	/**
	 * 礼包兑换码使用己结束
	 */
	public static final String GIFT_CODE_USE_AREADY_END_CODE = "901004";
	
	/**
	 * 礼包状态不正确
	 */
	public static final String GIFT_STATUS_CODE = "901005";
	
	
	/**
	 * 礼包账户领取次数限制
	 */
	public static final String GIFT_ACC_LIMIT_CODE = "901006";
	
	
	/**
	 * 礼包角色领取次数限制
	 */
	public static final String GIFT_ROLE_LIMIT_CODE = "901007";
	
	
	
	/**
	 * 礼包服务器能否领取限制
	 */
	public static final String GIFT_SERVICE_LIMIT_CODE = "901008";
	
	

	/**
	 * 礼包渠道能否领取限制
	 */
	public static final String GIFT_CHANNEL_LIMIT_CODE = "901009";
	
	
	/**
	 * 礼包单个兑换码使用次数限制
	 */
	public static final String GIFT_SIGN_TIME_LIMIT_CODE = "901010";
	
	
	/**
	 * 礼包批次领取限制
	 */
	public static final String GIFT_BATCH_LIMIT_CODE = "901011";
	

}
