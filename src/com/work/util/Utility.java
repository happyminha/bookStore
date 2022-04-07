package com.work.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * <pre>
 * 공통으로 사용하는 기능 모델링
 * -- 모든 메서드는 객체생성없이 사용가능한 class 메서드로 구현
 * </pre>
 */
public class Utility {
	/**
	 * 키보드 입력 숫자 반환
	 * @return 정수
	 */
	public static int inputNumber() {
		String inputData = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			inputData = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();	
		}
		return Integer.parseInt(inputData);
	}
	
	/**
	 * 키보드 입력 문자열 반환
	 * @return 문자열
	 */
	public static String inputString() {
		String inputData = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			inputData = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inputData;
	}

	
	
	public static String inputStrings() {
		String inputData = null;
		StringBuilder builder = new StringBuilder();
		while(true) {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				inputData = in.readLine();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(inputData.charAt(inputData.length() - 1) == ';') {
				builder.append(inputData);
				builder.deleteCharAt(builder.length() - 1);
				break;
			}
			builder.append(inputData);
			builder.append("\n");
		}
		return builder.toString();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 현재날짜 기본 형식 : 년도4자리-월2자리-일2자리 문자열 변환 메서드
	 * @return 기본형식 날짜 문자열
	 */
	public static String getCurrentDate() {
		return getCurrentDate("yyyy-MM-dd");
	}
	
	/**
	 * 현재날짜 아규먼트 형식 : pattern
	 * @param pattern 날짜형식
	 * @return 지정형식 날짜 문자열
	 */
	public static String getCurrentDate(String pattern) {
		return new SimpleDateFormat(pattern).format(new Date());
	}
	
	/**
	 * 현재시간 : 기본 형식 : 오후 5:00 
	 * @return 기본형식 시간
	 */
	public static String getCurrentTime() {
		return getCurrentTime("a h:mm");
	}
	
	/**
	 * 현재시간 : 아규먼트 형식 : pattern
	 * @param pattern 시간형식
	 * @return 지정한 형식 시간 문자열
	 */
	public static String getCurrentTime(String pattern) {
		return new SimpleDateFormat(pattern).format(new Date());
	}

	/**
	 * 현재 날짜 및 시간 기본형식, 기본로케일은 대한민국 변환
	 * @return 기본형식 날짜, 시간 문자열
	 */
	public static String getCurrentDateAndCurrentTime() {
		return getCurrentDateAndCurrentTime("yyyy-MM-dd a h:mm", Locale.KOREA);
	}

	/**
	 * 현재 날짜 및 시간, 로케일은 전달받은 아규먼트의 형식 및 로케일반영 변환
	 * @param pattern 형식
	 * @param locale 로케일
	 * @return 지정형식, 지정 로케일 날짜 또는 시간 문자열 
	 */
	public static String getCurrentDateAndCurrentTime(String pattern, Locale locale) {
		return new SimpleDateFormat(pattern, locale).format(new Date());
	}
	
	/**
	 * <pre>
	 * 아규먼트 전달받은 bound 임의 숫자 문자열 반환
	 * -- Lotto645 임의의 숫자 등에서 활용
	 * </pre>  
	 * @param bound 임의숫자의 범위
	 * @return 임의의 bound 내의 숫자 : 0 ~ (bound - 1)
	 */
	public static String getRandom(int bound) {
		Random random = new Random((long)(System.nanoTime() * Math.random()));  
		return String.valueOf(random.nextInt(bound));
	}

	/**
	 * 기본으로 0 ~ 9 사이의 임의의 숫자 문자열 반환
	 * @return 0 ~ 9 사이의 임의의 숫자 문자열
	 */
	public static String getRandom() {
		Random random = new Random((long)(System.nanoTime() * Math.random()));  
		return String.valueOf(random.nextInt(10));  
	}

	/**
	 * 임시 보안 문자 반환: 응용해서 다양하게 활용해보세요 : 8357
	 * @return 기본 4자리 숫자의 보안 문자열 반환
	 */
	public static String getSecureCodeNumber() {
		return getSecureCodeNumber(4);
	}
	
	/**
	 * 아규먼트 길이의 임시 보안 문자 반환 : 8356143
	 * @param length 길이
	 * @return 지정길이 임의 숫자형식 문자열
	 */
	public static String getSecureCodeNumber(int length) {
		StringBuilder secureCode = new StringBuilder();
		for (int index = 0; index < length; index++) {
			secureCode.append(getRandom());
		}
		return secureCode.toString();
	}
	
	/**
	 * 아규먼트 길이의 임시 보안 영문자 반환 : ABZQYB
	 * @param length 길이
	 * @return 지정길이 임의 영문대문자 문자열
	 */
	public static String getSecureCodeString(int length) {
		StringBuilder secureCode = new StringBuilder();
		for (int index = 0; index < length; index++) {
			secureCode.append((char)(Integer.parseInt(getRandom(26)) +65));
		}
		return secureCode.toString();
	}
	
	/**
	 * 아규머트 길이의 임시 보안 숫자 및 영문자 혼용 반환 : 38ZB72 
	 * @param length 길이
	 * @return 지정길이 임의 숫자 및 영문대문자 혼합 문자열
	 */
	public static String getSecureCodeNumberAndString(int length) {
		StringBuilder secureCode = new StringBuilder();
		Random random = new Random((long)(Math.random() * System.nanoTime()));
		for (int index = 0; index < length; index++) {
			if (random.nextBoolean()) {
				secureCode.append((char)(Integer.parseInt(getRandom(26)) +65));
			} else {
				secureCode.append(getRandom());
			}
		}
		return secureCode.toString();
	}
	
	/**
	 * <pre>
	 * 복불복 이름 지정하기: 우수회원 등업시에 담당자 배정시에 활용, 오늘 청소당번 지정
	 * -- 아래코드를 api 확인하면서 코드 리뷰 해볼것!!!
	 * </pre>
	 * @return 임의 지정 이름 문자열
	 */
	public static String getRandomManger() {
		String[] names = {"김미주", "김민하", "김희택", "송하늘", "신다운", "박용연"};
		Random random = new Random((long)(System.nanoTime() * Math.random())); 
		return names[random.nextInt(names.length)];
	}
	
	/**
	 * 공통 기능 메서드 테스트
	 * @param args 문자열 배열
	 */
	public static void main1(String[] args) {
		System.out.println("현재날짜: " + getCurrentDate()); // 기본형식날짜
		System.out.println("현재날짜: " + getCurrentDate("yyyy.MM.dd")); // 지정한형식날짜
		System.out.println("현재날짜: " + getCurrentDate("yy/MM/dd"));
		
		System.out.println("현재날짜: " + getCurrentTime()); // 기본형식시간
		System.out.println("현재날짜: " + getCurrentTime("HH:mm:ss"));	// 지정한형식시간
		
		System.out.println();
		System.out.println("현재날짜: " + getCurrentDateAndCurrentTime()); // 기본형식 날짜 및 시간, 기본로케일 KOREA
		System.out.println("현재날짜: " + getCurrentDateAndCurrentTime("yyyy-MM-dd", Locale.KOREA));
		System.out.println("현재날짜: " + getCurrentDateAndCurrentTime("a h:mm", Locale.US));
		System.out.println("현재날짜: " + getCurrentDateAndCurrentTime("a h:mm", Locale.JAPAN));
	}
}
