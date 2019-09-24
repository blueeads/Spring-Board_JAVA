package com.work.board.scheduler.controller;

import java.io.File;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class schedulerController {
	// * �쓣 �엯�젰�븷寃쎌슦 紐⑤몢(�빆�긽)�쑝濡� �꽕�젙�븿.
	//                 珥�  遺�  �떆  �씪  �썡  �슂�씪
	@Scheduled(cron = "0 00 13 * * *")
	public void autoUpdate(){
		String path = "C:/Temp/TsetFolder/";
		File folder = new File(path);
		try {
		    while(folder.exists()) {
			File[] folder_list = folder.listFiles(); //�뙆�씪由ъ뒪�듃 �뼸�뼱�삤湲�
					
			for (int j = 0; j < folder_list.length; j++) {
				folder_list[j].delete(); //�뙆�씪 �궘�젣 
				System.out.println("파일 삭제 ");
						
			}
		    }
		 } catch (Exception e) {
			e.getStackTrace();
		}
	}
	
}
