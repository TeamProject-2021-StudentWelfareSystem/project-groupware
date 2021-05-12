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

@Component("fileUtilsS")
public class BFileUtils {
	//공통된걸로 바꾸거나 서버컴으로 위치변경해야됨 + xml적용해야겠지?
	private static final String FilePath = "F:\\mju\\file\\";

	public List<Map<String, Object>> InsertFileInfo(Board board, MultipartHttpServletRequest mpRequest)
			throws Exception {

		Iterator<String> iterator = mpRequest.getFileNames();
		MultipartFile MultiPartFile = null;
		String OriginalFileName = null;
		String OriginalFileExtension = null;
		String StoredFileName = null;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> listMap = null;
		int Bno = board.getBno();

		File file = new File(FilePath);
		if (file.exists() == false) {
			file.mkdirs();
		}
		while (iterator.hasNext()) {
			MultiPartFile = mpRequest.getFile(iterator.next());
			if (MultiPartFile.isEmpty() == false) {
				OriginalFileName = MultiPartFile.getOriginalFilename();
				OriginalFileExtension = OriginalFileName.substring(OriginalFileName.lastIndexOf("."));
				StoredFileName = getRandomString() + OriginalFileExtension;

				file = new File(FilePath + StoredFileName);
				MultiPartFile.transferTo(file);
				listMap = new HashMap<String, Object>();
				//게시판번호
				listMap.put("Bno", Bno);
				//원래파일이름
				listMap.put("OriginalFileName", OriginalFileName);
				//저장될 파일이름
				listMap.put("StoredFileName", StoredFileName);
				//파일크기
				listMap.put("FileSize", MultiPartFile.getSize());
				list.add(listMap);
			}
		}

		return list;
	}

	private String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
