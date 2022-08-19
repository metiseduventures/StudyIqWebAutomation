package pojo;

public class TestData {
	
	private String loginNumber;	//to login

	private String courseName; // search course to buy
	private String offerName; // gold,silver,premium
	private String couponCode;
	private String paymentMethod; // netbank, paytm
	
	//billing info
	private String nameBill;
	private String addressBill;
	private String zipBill;
	private String cityBill;
	private String stateBill;
	private String numberBill;
	private String emailBill;

	public String getLoginNumber() {
		return loginNumber;
	}
	
	public void setLoginNumber(String loginNumber) {
		this.loginNumber = loginNumber;
	}
	
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getOfferPackage() {
		return offerName;
	}

	public void setOfferPackage(String offerName) {
		this.offerName = offerName;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getOfferName() {
		return offerName;
	}

	public String getNameBill() {
		return nameBill;
	}

	public String getAddressBill() {
		return addressBill;
	}

	public String getZipBill() {
		return zipBill;
	}

	public String getCityBill() {
		return cityBill;
	}

	public String getStateBill() {
		return stateBill;
	}

	public String getNumberBill() {
		return numberBill;
	}

	public String getEmailBill() {
		return emailBill;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public void setNameBill(String nameBill) {
		this.nameBill = nameBill;
	}

	public void setAddressBill(String addressBill) {
		this.addressBill = addressBill;
	}

	public void setZipBill(String zipBill) {
		this.zipBill = zipBill;
	}

	public void setCityBill(String cityBill) {
		this.cityBill = cityBill;
	}

	public void setStateBill(String stateBill) {
		this.stateBill = stateBill;
	}

	public void setNumberBill(String numberBill) {
		this.numberBill = numberBill;
	}

	public void setEmailBill(String emailBill) {
		this.emailBill = emailBill;
	}

}
