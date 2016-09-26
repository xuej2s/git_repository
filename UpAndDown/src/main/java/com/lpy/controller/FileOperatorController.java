package com.lpy.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/operating")
public class FileOperatorController {

	@RequestMapping("/skip.do")
	public String skip() {

		return "skip";
	}

	@RequestMapping("/upload.do")
	public String upload() {
		return "upload";
	}

	//文件上传
	@RequestMapping("/uploading.do")
	public String uploading(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, ModelMap model) {
		System.out.println("upload start");

		// 创建保存文件路径
		String path = request.getRealPath("upload");

		// 获取文件名
		String fileName = file.getOriginalFilename();

		System.out.println(path);

		File targetFile = new File(path, fileName);

		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}

		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		List<String> files = loadFiles(request);
		model.addAttribute("files", files);
		return "skip";
	}

	@RequestMapping("download.do")
	public String download(HttpServletRequest request, ModelMap model) {
		List<String> files = loadFiles(request);
		model.addAttribute("files", files);
		return "download";
	}

	//文件下载
	@RequestMapping("downloading.do")
	public String downloading(String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("downloading");
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;

		String ctxPath = request.getSession().getServletContext().getRealPath("upload")+"\\";
		String downLoadPath = ctxPath + fileName;
		System.out.println(downLoadPath);
		try {
			long fileLength = new File(downLoadPath).length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
		return null;
	}

	public List<String> loadFiles(HttpServletRequest request) {
		List<String> files = new ArrayList<String>();
		String path = request.getRealPath("/upload");
		File file = new File(path);
		if (file.exists()) {
			File[] fs = file.listFiles();
			String fname = null;
			for (File f : fs) {
				fname = f.getName();
				if (f.isFile()) {
					files.add(fname);
				}
			}
		}
		return files;
	}
}
