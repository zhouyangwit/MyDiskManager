package Omap_Location;

public class Excel_Elements {
	
	/*
	 *excel主要参数：
	 *编号（0），普查小区编号(1)，企业名称(6)，普查小区名称(13)，地址_门牌号(14)，公司联系人姓名(15)，公司联系方式(17)，行业分类(18)，经度(22)，纬度(23)，普查员编号(25)，普查员名称(24)
	 * 
	 */
	private String num;
	private String VillageID;
	private String CompanyName;
	private String VillageName;
	private String address;
	private String people;
	private String phoneNumber;
	private String Industry_category;
	private String Longitude;
	private String latitude;
	private String EnumeratorID;
	private String EnumeratorName;

	public void setValues(String num,String VillageID,String CompanyName,String VillageName,String address,String people,String phoneNumber,String Industry_category,String Longitude,String latitude,String EnumeratorName,String EnumeratorID)
	{
		this.setNum(num);
		this.setVillageID(VillageID);
		this.setCompanyName(CompanyName);
		this.setVillageName(VillageName);
		this.setAddress(address);
		this.setPeople(people);
		this.setPhoneNumber(phoneNumber);
		this.setIndustry_category(Industry_category);
		this.setLongitude(Longitude);
		this.setLatitude(latitude);
		this.setEnumeratorName(EnumeratorName);
		this.setEnumeratorID(EnumeratorID);
	}
	

	public String getVillageID() {
		return VillageID;
	}


	public void setVillageID(String villageID) {
		VillageID = villageID;
	}


	public String getCompanyName() {
		return CompanyName;
	}


	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}


	public String getVillageName() {
		return VillageName;
	}


	public void setVillageName(String villageName) {
		VillageName = villageName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPeople() {
		return people;
	}


	public void setPeople(String people) {
		this.people = people;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getIndustry_category() {
		return Industry_category;
	}


	public void setIndustry_category(String industry_category) {
		Industry_category = industry_category;
	}


	public String getLongitude() {
		return Longitude;
	}


	public void setLongitude(String longitude) {
		Longitude = longitude;
	}


	public String getLatitude() {
		return latitude;
	}


	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	public String getEnumeratorID() {
		return EnumeratorID;
	}


	public void setEnumeratorID(String enumeratorID) {
		this.EnumeratorID = enumeratorID;
	}


	public String getEnumeratorName() {
		return EnumeratorName;
	}


	public void setEnumeratorName(String enumeratorName) {
		this.EnumeratorName = enumeratorName;
	}


	public String getNum() {
		return num;
	}


	public void setNum(String num) {
		this.num = num;
	}
	

	public String getSumString()
	{
		String sumString=getCompanyName()+"  "+getIndustry_category()+"  "+getVillageName()+"  "+getAddress()+"  "+getPeople()+"  "+getPhoneNumber()+"  普查员-"+getEnumeratorName();
		
		return sumString;
		
	}

}
