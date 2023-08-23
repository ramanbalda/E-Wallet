package com.icsd.exceptionhand;

public class IcsdException extends RuntimeException{
	
	 public IcsdException()
	 {
	  
	 }
	 public IcsdException(String msg)
	 {
	  super(msg + "  Icsd exception fired ");
	 }

}
