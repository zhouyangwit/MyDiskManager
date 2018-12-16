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
	
	public void setInteroperabilityValues(String  InteroperabilityIndex  ,String  InteroperabilityVersion ){
		this.InteroperabilityIndex =  InteroperabilityIndex  ;
		this.InteroperabilityVersion =  InteroperabilityVersion  ;
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

}
