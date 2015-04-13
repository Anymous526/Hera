package com.justinmobile.endpoint.mina.domain;

/**结算数据
 * @author ThinkPad7
 *
 */
public class Hs8583ClosingCostDto extends Hs8583BaseDto {
	/**消费总笔数*/
	private Long consumeCount;
	/**消费总金额*/
	private Long consumeAmount;
	/**退货总比数*/
	private Long returnCount;
	/**退货总金额*/
	private Long returnAmount;
	/**积分消费总笔数*/
	private Long integralConsumeCount;
	/**积分消费总金额*/
	private Long integralConsumeAmount;
	/**积分退货总比数*/
	private Long integralReturnCount;
	/**积分退货总金额*/
	private Long integralReturnAmount;
	
	public Long getConsumeCount() {
		return consumeCount;
	}
	public String getConsumeCountString() {
		return String.format("%06d", consumeCount);
	}
	public void setConsumeCount(Long consumeCount) {
		this.consumeCount = consumeCount;
	}
	public Long getConsumeAmount() {
		return consumeAmount;
	}
	public String getConsumeAmountString() {
		return String.format("%010d", consumeAmount);
	}
	public void setConsumeAmount(Long consumeAmount) {
		this.consumeAmount = consumeAmount;
	}
	public Long getReturnCount() {
		return returnCount;
	}
	public String getReturnCountString() {
		return String.format("%06d", returnCount);
	}
	public void setReturnCount(Long returnCount) {
		this.returnCount = returnCount;
	}
	public Long getReturnAmount() {
		return returnAmount;
	}
	public String getReturnAmountString() {
		return String.format("%010d", returnAmount);
	}
	public void setReturnAmount(Long returnAmount) {
		this.returnAmount = returnAmount;
	}
	public Long getIntegralConsumeCount() {
		return integralConsumeCount;
	}
	public String getIntegralConsumeCountString() {
		return String.format("%06d", integralConsumeCount);
	}
	public void setIntegralConsumeCount(Long integralConsumeCount) {
		this.integralConsumeCount = integralConsumeCount;
	}
	public Long getIntegralConsumeAmount() {
		return integralConsumeAmount;
	}
	
	public String getIntegralConsumeAmountString() {
		return  String.format("%010d", integralConsumeAmount);
	}
	public void setIntegralConsumeAmount(Long integralConsumeAmount) {
		this.integralConsumeAmount = integralConsumeAmount;
	}
	public Long getIntegralReturnCount() {
		return integralReturnCount;
	}
	public String getIntegralReturnCountString() {
		return String.format("%06d", integralReturnCount);
	}
	public void setIntegralReturnCount(Long integralReturnCount) {
		this.integralReturnCount = integralReturnCount;
	}
	public Long getIntegralReturnAmount() {
		return integralReturnAmount;
	}
	public String getIntegralReturnAmountString() {
		return String.format("%010d", integralReturnAmount);
	}
	public void setIntegralReturnAmount(Long integralReturnAmount) {
		this.integralReturnAmount = integralReturnAmount;
	}
	
	
	
	
}
