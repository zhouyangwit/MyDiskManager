package demo;

public class FindeNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FindeNumber().PythagoreanTriple(0,4000);

	}
	
	int num=0;
	
	public boolean IsPrimeNum(int m)
	{
		boolean flag=true;
		if(m==2)
		{
			num++;
		}
		else{
			for(int i=(int) Math.sqrt(m)+1;i>1;i--)
			{
				num++;
				if(m%i==0)
					flag=false;
			}
		}
		return flag;
	}
	
	public void FindSuShu(int length)
	{
		int count=0;
		for(int i=2;i<=length;i++)
		{
			
			if(IsPrimeNum(i))
			{
				count++;
				System.out.println("【"+count+"】\t【"+i+"】");
			}
		}
		System.out.println("计算完成，【"+length+"]以内共找出素数【"+count+"】个，程序循环【"+num+"】次");
	}
	
	public void FindZhishu(int countNum)
	{
		double length=100E100;
		int count=0;
		for(int i=2;i<=length;i++)
		{
			
			if(IsPrimeNum(i))
			{
				count++;
				System.out.println(count+"\t"+i+"");
				if(count>=countNum)
					break;
			}
		}
		System.out.println("计算完成，共找出素数【"+count+"】个，程序循环【"+num+"】次");
	}
	
	
	public void PythagoreanTriple(int length)
	{
		int x,y,z;
		int count=0,num=0;
		
		for(x=1;x<=length;x++)
		{
			for(y=1;y<x;y++)
			{
				for(z=1;z<y;z++)
				{
					if((z*z+y*y)==x*x)
					{
						boolean flag=true;
						for(int i=(int)Math.sqrt(z)+1;i>1;i--)
						{
							if((x%i==0)&&(y%i==0)&&(z%i==0))
							{
								flag=false;
							}
						}
						if(flag)
						{
							count++;
							System.out.println("【"+count+"】\t【"+x+"\t"+y+"\t"+z+"】");
						}
					}
				}
			}
		}
	}
	
	public void PythagoreanTriple(int start,int end)
	{
		if(start<2)
		{
			start=2;
		}
		int x,y,z;
		int count=0,num=0;
		long start_time,end_time;
		double time;
		start_time=System.currentTimeMillis();
		for(x=start;x<=end;x++)
		{
			for(y=start;y<x;y++)
			{
				for(z=start;z<y;z++)
				{
					if((z*z+y*y)==x*x)
					{
						boolean flag=true;
						for(int i=(int)Math.sqrt(z)+1;i>1;i--)
						{
							if((x%i==0)&&(y%i==0)&&(z%i==0))
							{
								flag=false;
							}
						}
						if(flag)
						{
							count++;
							System.out.println("【"+count+"】\t【"+x+"\t"+y+"\t"+z+"】");
						}
					}
				}
			}
		}
		end_time=System.currentTimeMillis();
		time=(end_time-start_time)/1000.0;
		System.out.println("区间【"+start+"，"+end+"】的勾股数有【"+count+"】组，耗时【"+time+"】秒");
	}
	
	public void PythagoreanTripleNum(int countNum)
	{
		int x,y,z;
		int count=0,num=0;
		long start_time,end_time;
		double time;
		start_time=System.currentTimeMillis();
		double length=100E100;
	//	Log log=new Log();
		for(x=2;x<=length;x++)
		{
			for(y=2;y<x;y++)
			{
				for(z=2;z<y;z++)
				{
					if((z*z+y*y)==x*x)
					{
						boolean flag=true;
						for(int i=(int)Math.sqrt(z)+1;i>1;i--)
						{
							if((x%i==0)&&(y%i==0)&&(z%i==0))
							{
								flag=false;
							}
						}
						if(flag)
						{
							count++;
							System.out.println("【"+count+"】\t【"+x+"\t"+y+"\t"+z+"】");
				//			log.log_record(z+"\t"+y+"\t"+x);
							if(count>=countNum)
								break;
						}
					}
				}
				if(count>=countNum)
					break;
			}
			if(count>=countNum)
				break;
		}
		end_time=System.currentTimeMillis();
		time=(end_time-start_time)/1000.0;
		System.out.println("耗时【"+time+"】秒");
	}
	
	
}
