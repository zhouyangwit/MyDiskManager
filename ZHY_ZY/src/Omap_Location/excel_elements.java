package Omap_Location;

public class excel_elements {
	
	//要从excel中获取的主要参数
	String CompanyName;
	String VillageName;
	String VillageID;
	String EnumeratorName;
	String EnumeratorID;
	String InstructorName;
	String InstructorID;
	String Longitude;
	String latitude;
	String seal_level;
	
	public void setName(String CompanyName,String VillageName,String Longitude,String latitude)
	{
		this.CompanyName=CompanyName;
		this.VillageName=VillageName;
		this.Longitude=Longitude;
		this.latitude=latitude;
	}
	

}
