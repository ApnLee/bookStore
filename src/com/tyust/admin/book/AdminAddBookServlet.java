package com.tyust.admin.book;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.tyust.book.bean.Book;
import com.tyust.book.service.BookService;
import com.tyust.category.bean.Category;
import com.tyust.category.service.CategoryService;
import com.tyust.commons.CommonUtil;

public class AdminAddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//需要上传
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setFileSizeMax(80 * 1024);
		//解析request得到<FileItem>
		List<FileItem> fileItemList = null;
		try {
			fileItemList = sfu.parseRequest(request);
		} catch (FileUploadException e) {
			//如果出现异常，说明文件超出了80KB
			error("上传的文件超出了80KB",request,response);
			return;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		for(FileItem fileItem : fileItemList){
			if(fileItem.isFormField()){  //如果是普通表单字段
				map.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
			}
		}
		Book book = CommonUtil.toBean(Book.class, map);
		Category category = CommonUtil.toBean(Category.class, map);
		book.setCategory(category);
		//把上传的图片保存起来
		/*
		 * 获取文件名，并截取
		 * 给文件添加前缀，使用uuid，避免出现重名
		 * 检验文件的扩展名是否为jpg格式的
		 * 校验图片的尺寸
		 * 指定图片的保存路径，需要用到servletContext 的getRealPath
		 * 吧图片的路径设置给图片对象
		 */
		FileItem fileItem = fileItemList.get(1);//获取大图
		String filename = fileItem.getName();
		
		int index = filename.lastIndexOf("\\");
		if(index!=-1){
			filename = filename.substring(index+1);
		}
		filename = CommonUtil.uuid()+"_"+filename;
		if(!filename.toLowerCase().endsWith(".jpg")){
			error("上传的文件扩展名必须是.jpg格式的",request,response);
			return;
		}
		//校验图片的尺寸
		//得先保存上传的图片,把图片new成一个对象：Image，Icon，ImageIcon
		//保存图片1.获取真是路径
		String savepath = this.getServletContext().getRealPath("/book_img");
		File destFile = new File(savepath,filename);
		//保存文件
		try {
			fileItem.write(destFile);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		//校验尺寸
		//创建ImageIcon，使用文件路径
		//通过ImageIcon对象的到Image对象
		//获取宽高校验
		ImageIcon icon = new ImageIcon(destFile.getAbsolutePath());
		
		Image image = icon.getImage();
		if(image.getWidth(null)>350 || image.getHeight(null)>350){
			error("您上传的图片尺寸超出了350*350",request,response);
			destFile.delete();
			return;
		}
		//把图片的路径设置给Book对象
		book.setImage_w("book_img/"+filename);
		
		
		
		fileItem = fileItemList.get(2);//获取小图
		filename = fileItem.getName();
		
		index = filename.lastIndexOf("\\");
		if(index!=-1){
			filename = filename.substring(index+1);
		}
		filename = CommonUtil.uuid()+"_"+filename;
		if(!filename.toLowerCase().endsWith(".jpg")){
			error("上传的文件扩展名必须是.jpg格式的",request,response);
			return;
		}
		//校验图片的尺寸
		//得先保存上传的图片,把图片new成一个对象：Image，Icon，ImageIcon
		//保存图片1.获取真是路径
		savepath = this.getServletContext().getRealPath("/book_img");
		destFile = new File(savepath,filename);
		//保存文件
		try {
			fileItem.write(destFile);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		//校验尺寸
		//创建ImageIcon，使用文件路径
		//通过ImageIcon对象的到Image对象
		//获取宽高校验
		icon = new ImageIcon(destFile.getAbsolutePath());
		
		image = icon.getImage();
		if(image.getWidth(null)>350 || image.getHeight(null)>350){
			error("您上传的图片尺寸超出了350*350",request,response);
			destFile.delete();
			return;
		}
		//把图片的路径设置给Book对象
		book.setImage_b("book_img/"+filename);
		
		
		//调用service保存到数据库
		book.setBid(CommonUtil.uuid());
		BookService bookService  = new BookService();
		bookService.addBook(book);
		request.setAttribute("msg", "添加图书成功！");
		request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request, response);
		
	}
	
	private void error(String msg,HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		request.setAttribute("msg", msg);
		request.setAttribute("parents", new CategoryService().findParents());
		request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
	}
}
