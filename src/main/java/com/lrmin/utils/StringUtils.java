package com.lrmin.utils;

import com.github.pagehelper.util.StringUtil;

public class StringUtils extends StringUtil{
	public static String toLowerCaseFirstOne(String s){
		  if (Character.isLowerCase(s.charAt(0))) {
			  return s;
		  } else {
			  return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
		  }
	}
}
