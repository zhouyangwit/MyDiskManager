package EXIF_Rewrite;

public class EXIF_Elements {
	
	//EXIF中的主要信息名称
	
	//File Directory
	String FileName;
	String FileSize;
	String FileModifiedDate;
	
	//Exif IFD0 Directory
	String  Model;
	String  Software;
	String  Orientation;
	String  DateTime;
	String  YCbCr_Positioning;
	String  ResolutionUnit;
	String  X_Resolution;
	String  Y_Resolution;
	String  Make;
	
	//Exif SubIFD Directory
	String  ISO_SpeedRatings;
	String  	ExposureProgram;
	String  	F_Number;
	String  	ExposureTime;
	String  	UnknownTag;
	String  	SensingMethod;
	String  Sub_SecTimeDigitized;
	String  	Sub_SecTimeOriginal;
	String  	Sub_SecTime;
	String  	FocalLength;
	String  Flash;
	String  	MeteringMode;
	String  	SceneCaptureType;
	String  FocalLength35;
	String  	DateTimeDigitized;
	String  	ExifImageHeight;
	String  	WhiteBalanceMode;
	String  	DateTimeOriginal;
	String  BrightnessValue;
	String  ExifImageWidth;
	String  ExposureMode;
	String  ApertureValue;
	String  ComponentsConfiguration;
	String  ColorSpace;
	String  SceneType;
	String  	ShutterSpeedValue;
	String  	ExifVersion;
	String  FlashPixVersion;
	
	//GPS
	String  GPSLatitudeRef  ;
	String  GPSLatitude  ;
	String  GPSLongitudeRef  ;
	String  GPSLongitude  ;
	String  GPSAltitudeRef  ;
	String  GPSAltitude  ;
	String  GPSTimeStamp  ;
	String  GPSProcessingMethod  ;
	String  GPSDateStamp  ;
	
	//JPEG
	String  CompressionType  ;
	String  DataPrecision  ;
	String  ImageHeight  ;
	String  ImageWidth  ;
	String  NumberofComponents  ;
	String  Component1  ;
	String  Component2  ;
	String  Component3  ;


	//Interoperability
	String  InteroperabilityIndex  ;
	String  InteroperabilityVersion  ;
	
//	Exif Thumbnail
	String  ThumbnailOffset  ;
	String  Thumbnail_Orientation  ;
	String  ThumbnailLength  ;
	String  ThumbnailCompression  ;
	String  Thumbnail_ResolutionUnit  ;
	String  Thumbnail_X_Resolution  ;
	String  Thumbnail_Y_Resolution  ;
	
	public void setFileValues(String  FileName  ,String  FileSize  ,String  FileModifiedDate){
		this.FileName=FileName;
		this.FileSize=FileSize;
		this.FileModifiedDate=FileModifiedDate;

	}
	
	public void setFileValues(String[] tag_info){
	//	this.FileName=tag_info[0];
	//	this.FileSize=tag_info[1];
		this.FileModifiedDate=tag_info[2];

	}
	
	
	public void setExifIFD0Values(String  Model  ,String  Software  ,String  Orientation  ,String  DateTime  ,String  YCbCr_Positioning  ,String  ResolutionUnit  ,String  X_Resolution  ,String  Y_Resolution  ,String  Make){
		this.Model=  Model;
		this.Software=  Software;
		this.Orientation=  Orientation;
		this.DateTime=  DateTime;
		this.YCbCr_Positioning=  YCbCr_Positioning;
		this.ResolutionUnit=  ResolutionUnit;
		this.X_Resolution =  X_Resolution;
		this.Y_Resolution =  Y_Resolution;
		this.Make =  Make;
	}
	
	public void setExifIFD0Values(String[] tag_info){
//		this.Model=  tag_info[0];
//		this.Software=  tag_info[1];
//		this.Orientation=  tag_info[2];
		this.DateTime=  tag_info[9];
//		this.YCbCr_Positioning=  tag_info[4];
//		this.ResolutionUnit=  tag_info[5];
//		this.X_Resolution =  tag_info[6];
//		this.Y_Resolution =  tag_info[7];
//		this.Make =  tag_info[8];
	}
	
	public void setExifSubIFDValues(String  ISO_SpeedRatings  ,String  ExposureProgram  ,String  F_Number  ,String  ExposureTime  ,String  UnknownTag   ,String  SensingMethod  ,String  Sub_SecTimeDigitized  ,String  Sub_SecTimeOriginal  ,String  Sub_SecTime  ,String  FocalLength  ,String  Flash  ,String  MeteringMode  ,String  SceneCaptureType  ,String  FocalLength35  ,String  DateTimeDigitized  ,String  ExifImageHeight  ,String  WhiteBalanceMode  ,String  DateTimeOriginal  ,String  BrightnessValue  ,String  ExifImageWidth  ,String  ExposureMode  ,String  ApertureValue  ,String  ComponentsConfiguration  ,String  ColorSpace  ,String  SceneType  ,String  ShutterSpeedValue  ,String  ExifVersion  ,String  FlashPixVersion ){
		this.ISO_SpeedRatings =  ISO_SpeedRatings;
		this.ExposureProgram =  	ExposureProgram;
		this.F_Number =  	F_Number;
		this.ExposureTime =  	ExposureTime;
		this.UnknownTag =  	UnknownTag;
		this.SensingMethod =  	SensingMethod;
		this.Sub_SecTimeDigitized=  Sub_SecTimeDigitized;
		this.Sub_SecTimeOriginal =  	Sub_SecTimeOriginal;
		this.Sub_SecTime =  	Sub_SecTime;
		this.FocalLength=  	FocalLength;
		this.Flash =  Flash;
		this.MeteringMode =  	MeteringMode;
		this.SceneCaptureType =  	SceneCaptureType;
		this.FocalLength35 =  FocalLength35;
		this.DateTimeDigitized =  	DateTimeDigitized;
		this.ExifImageHeight  =	ExifImageHeight;
		this.WhiteBalanceMode =  	WhiteBalanceMode;
		this.DateTimeOriginal =  	DateTimeOriginal;
		this.BrightnessValue =  BrightnessValue;
		this.ExifImageWidth =  ExifImageWidth;
		this.ExposureMode =  ExposureMode;
		this.ApertureValue =  ApertureValue;
		this.ComponentsConfiguration =  ComponentsConfiguration;
		this.ColorSpace =  ColorSpace;
		this.SceneType =  SceneType;
		this.ShutterSpeedValue =  	ShutterSpeedValue;
		this.ExifVersion =  	ExifVersion;
		this.FlashPixVersion =  FlashPixVersion;
	}
	
	public void setExifSubIFDValues(String[] tag_info){
//		this.ISO_SpeedRatings =  tag_info[0];
	//	this.ExposureProgram =  	tag_info[1];
	//	this.F_Number =  	tag_info[2];
//		this.ExposureTime =  	tag_info[3];
	//	this.UnknownTag =  	tag_info[4];
//		this.SensingMethod =  	tag_info[5];
//		this.Sub_SecTimeDigitized=  tag_info[6];
//		this.Sub_SecTimeOriginal =  	tag_info[7];
//		this.Sub_SecTime =  	tag_info[8];
//		this.FocalLength=  	tag_info[9];
//		this.Flash =  tag_info[10];
//		this.MeteringMode =  	tag_info[11];
//		this.SceneCaptureType =  	tag_info[12];
//		this.FocalLength35 =  tag_info[13];
		this.DateTimeDigitized =  	tag_info[7];
//		this.ExifImageHeight  =	tag_info[15];
//		this.WhiteBalanceMode =  	tag_info[16];
		this.DateTimeOriginal =  	tag_info[6];
//		this.BrightnessValue =  tag_info[18];
//		this.ExifImageWidth =  tag_info[19];
//		this.ExposureMode =  tag_info[20];
//		this.ApertureValue =  tag_info[21];
//		this.ComponentsConfiguration =  tag_info[22];
//		this.ColorSpace =  tag_info[23];
//		this.SceneType =  tag_info[24];
//		this.ShutterSpeedValue =  	tag_info[25];
//		this.ExifVersion =  	tag_info[26];
//		this.FlashPixVersion =  tag_info[27];
	}
	
	public void setGPSValues(String  GPSLatitudeRef  ,String  GPSLatitude  ,String GPSLongitudeRef  ,String  GPSLongitude  ,String  GPSAltitudeRef  ,String  GPSAltitude  ,String  GPSTimeStamp  ,String  GPSProcessingMethod  ,String  GPSDateStamp){
		this.GPSLatitudeRef =  GPSLatitudeRef  ;
		this.GPSLatitude =  GPSLatitude  ;
		this.GPSLongitudeRef =  GPSLongitudeRef  ;
		this.GPSLongitude =  GPSLongitude  ;
		this.GPSAltitudeRef =  GPSAltitudeRef  ;
		this.GPSAltitude =  GPSAltitude  ;
		this.GPSTimeStamp =  GPSTimeStamp  ;
		this.GPSProcessingMethod =  GPSProcessingMethod  ;
		this.GPSDateStamp =  GPSDateStamp  ;
	}
	
	public void setGPSValues(String[] tag_info){
//		this.GPSLatitudeRef =  tag_info[0]  ;
	//	this.GPSLatitude =   tag_info[1]   ;
//		this.GPSLongitudeRef =   tag_info[2]   ;
//		this.GPSLongitude =   tag_info[3]   ;
//		this.GPSAltitudeRef =   tag_info[4]   ;
//		this.GPSAltitude =   tag_info[5]   ;
		this.GPSTimeStamp =   tag_info[6]   ;
//		this.GPSProcessingMethod =   tag_info[7]   ;
		this.GPSDateStamp =   tag_info[8]   ;
	}
	
	public void setJPEGValues(String  CompressionType  ,String  DataPrecision  ,String  ImageHeight  ,String  ImageWidth  ,String  NumberofComponents  ,String  Component1  ,String  Component2  ,String  Component3){
		this.CompressionType =  CompressionType  ;
		this.DataPrecision =  DataPrecision  ;
		this.ImageHeight =  ImageHeight  ;
		this.ImageWidth =  ImageWidth  ;
		this.NumberofComponents =  NumberofComponents  ;
		this.Component1 =  Component1  ;
		this.Component2 =  Component2  ;
		this.Component3 =  Component3  ;
	}
	
	public void setJPEGValues(String[] tag_info){
		this.CompressionType =  tag_info[0]  ;
		this.DataPrecision =  tag_info[1]   ;
		this.ImageHeight =  tag_info[2]   ;
		this.ImageWidth =  tag_info[3]   ;
		this.NumberofComponents =  tag_info[4]   ;
		this.Component1 =  tag_info[5]   ;
		this.Component2 =  tag_info[6]   ;
		this.Component3 =  tag_info[7]   ;
	}
	
	public void setInteroperabilityValues(String  InteroperabilityIndex  ,String  InteroperabilityVersion ){
		this.InteroperabilityIndex =  InteroperabilityIndex  ;
		this.InteroperabilityVersion =  InteroperabilityVersion  ;
	}
	
	public void setInteroperabilityValues(String[] tag_info ){
		this.InteroperabilityIndex =  tag_info[0]  ;
		this.InteroperabilityVersion =  tag_info[1]  ;
	}
	
	public void setExifThumbnailValues(String  ThumbnailOffset  ,String  Thumbnail_Orientation  ,String  ThumbnailLength  ,String  ThumbnailCompression  ,String  Thumbnail_ResolutionUnit  ,String  Thumbnail_X_Resolution  ,String  Thumbnail_Y_Resolution){
		this.ThumbnailOffset =  ThumbnailOffset  ;
		this.Thumbnail_Orientation =  Thumbnail_Orientation  ;
		this.ThumbnailLength =  ThumbnailLength  ;
		this.ThumbnailCompression =  ThumbnailCompression  ;
		this.Thumbnail_ResolutionUnit =  Thumbnail_ResolutionUnit  ;
		this.Thumbnail_X_Resolution =  Thumbnail_X_Resolution  ;
		this.Thumbnail_Y_Resolution =  Thumbnail_Y_Resolution  ;
	}
	
	public void setExifThumbnailValues(String[] tag_info){
		this.ThumbnailOffset =  tag_info[0]  ;
		this.Thumbnail_Orientation =   tag_info[1]   ;
		this.ThumbnailLength =   tag_info[2]   ;
		this.ThumbnailCompression =   tag_info[3]   ;
		this.Thumbnail_ResolutionUnit =   tag_info[4]   ;
		this.Thumbnail_X_Resolution =   tag_info[5]   ;
		this.Thumbnail_Y_Resolution =   tag_info[6]   ;
	}

}
