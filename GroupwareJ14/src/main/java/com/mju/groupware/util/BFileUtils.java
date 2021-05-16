package com.mju.groupware.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mju.groupware.dto.Board;

@Component("fileUtils")
public class BFileUtils {
	private static final String filePath = "F:\\mju\\file\\"; // 파일이 저장될 위치

	public List<Map<String, Object>> InsertFileInfo(Board board, MultipartHttpServletRequest mpRequest)
			throws Exception {

		Iterator<String> iterator = mpRequest.getFileNames();

		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> listMap = null;

		int bno = board.getBno();

		File file = new File(filePath);
		if (file.exists() == false) {
			file.mkdirs();
		}

		while (iterator.hasNext()) {
			multipartFile = mpRequest.getFile(iterator.next());
			if (multipartFile.isEmpty() == false) {
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = getRandomString() + originalFileExtension;
				file = new File(filePath + storedFileName);
				multipartFile.transferTo(file);
				listMap = new HashMap<String, Object>();
				listMap.put("Bno", bno);
				listMap.put("OriginalFileName", originalFileName);
				listMap.put("StoredFileName", storedFileName);
				listMap.put("FileSize", multipartFile.getSize());
				list.add(listMap);
			}
		}
		return list;
	}

	public List<Map<String, Object>> UpdateFileInfo(Board board, String[] fileList, String[] fileNameList,
			MultipartHttpServletRequest mpRequest) throws Exception {
		Iterator<String> iterator = mpRequest.getFileNames();
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> listMap = null;
		int bno = board.getBno();
		while (iterator.hasNext()) {
			multipartFile = mpRequest.getFile(iterator.next());
			if (multipartFile.isEmpty() == false) {
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = getRandomString() + originalFileExtension;
				multipartFile.transferTo(new File(filePath + storedFileName));
				listMap = new HashMap<String, Object>();
				listMap.put("IsNew", "Y");
				listMap.put("Bno", bno);
				listMap.put("OriginalFileName", originalFileName);
				listMap.put("StoredFileName", storedFileName);
				listMap.put("FileSize", multipartFile.getSize());
				list.add(listMap);
			}
		}
		if (fileList != null && fileNameList != null) {
			for (int i = 0; i < fileNameList.length; i++) {
				listMap = new HashMap<String, Object>();
				listMap.put("IsNew", "N");
				listMap.put("BFileID", fileList[i]);
				list.add(listMap);
			}
		}
		return list;
	}

	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}