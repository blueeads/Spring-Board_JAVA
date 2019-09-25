package com.work.board.scheduler.controller;

import java.io.File;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class schedulerController {
	// 오후 1시에 스케줄 컨트롤러 작동
	// 파일 업로드를 DB에 바로 저장하기 때문에 지금은 사용 X
	@Scheduled(cron = "0 00 13 * * *")
	public void autoUpdate(){
		String path = "C:/Temp/TsetFolder/";
		File folder = new File(path);
		try {
		    while(folder.exists()) {
			File[] folder_list = folder.listFiles(); //폴더 리스트 파일 개수 확인
					
			for (int j = 0; j < folder_list.length; j++) {
				folder_list[j].delete(); //내부 폴더 모두 삭제
				System.out.println("파일 삭제 ");
						
			}
		    }
		 } catch (Exception e) {
			e.getStackTrace();
		}
	}
	
}
