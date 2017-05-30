package com.siqing.FileManager;
/**
 * FileOperation:自定义文件操作类
 * 类属性：filePath
 * 类方法：viewFile,viewProperty,deleteFile,renameFile,comrenameFile,comdeleteFile
 */

import java.io.File;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileOperation {
	private String filePath=null;  //选择的文件夹路径
	public void viewFile(JComboBox<File> jComboBox) {  //显示文件夹中的文件
		JFileChooser selectFolder = new JFileChooser("C://");
		selectFolder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//仅选择文件夹
		int returnVal = selectFolder.showOpenDialog(selectFolder);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			filePath = selectFolder.getSelectedFile().getAbsolutePath();// 选择的文件夹的路径
			jComboBox.removeAllItems();//清空下拉列表
			File f = new File(filePath);
			if (f != null) {
				File[] array = f.listFiles();
				for (int i = 0; i < array.length; i++) {
					if (array[i].isFile()) {
						jComboBox.addItem(array[i]);
					}
				}
			}
			
		}
	}	
	
	public boolean comDeleteFile(String sPath) {  //通用删除文件方法
	    boolean flag = false;  
	    File file = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}
	public boolean comRenameFile(String path,String oldname,String newname){ //通用重命名方法
        if(!oldname.equals(newname)){//新的文件名和以前文件名不同时,才有必要进行重命名 
            File oldfile=new File(path+"\\"+oldname); 
            File newfile=new File(path+"\\"+newname); 
            if(!oldfile.exists()){
            	JOptionPane.showMessageDialog(null, "重命名文件不存在", "错误",JOptionPane.ERROR_MESSAGE);
            	return false;
            }
            if(newfile.exists()){//若在该目录下已经有一个文件和新文件名相同，则不允许重命名 
            	JOptionPane.showMessageDialog(null, newname+"已经存在！", "错误",JOptionPane.ERROR_MESSAGE);
            	return false;
            	} 
            else{
                oldfile.renameTo(newfile);//我们来改名字啦
                return true;
            } 
        }else{
        	JOptionPane.showMessageDialog(null, "新文件名和旧文件名相同", "错误",JOptionPane.ERROR_MESSAGE);
        	return false;
        }
    }
	public void viewProperty(JComboBox<File> jComboBox) {   //查看属性方法		
		if (jComboBox.getSelectedItem()==null) {
			JOptionPane.showMessageDialog(null, "未选定文件", "错误",JOptionPane.ERROR_MESSAGE);
		}else{
		File aFile=new File(jComboBox.getSelectedItem().toString());
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String dateTime=df.format(new Date(aFile.lastModified()));//修改时间转化成正常日期
		String property="文件上级目录: "+aFile.getParent()+
	            "\n文件所在目录（含文件名）: "+aFile.getPath()+
	            "\n是否可读: "+aFile.canRead()+
	            "\n是否可写: "+aFile.canWrite()+
	            "\n文件名: "+aFile.getName()+
	            "\n文件长度: "+aFile.length()+"字节"+
	            "\n最后修改时间: "+dateTime+
	            "\n是否被隐藏: "+aFile.isHidden();
		JOptionPane.showMessageDialog(null, property,"文件属性",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public void deleteFile(JComboBox<File> jComboBox) { //删除文件方法
		String selectFilePath=null;
		if (jComboBox.getSelectedItem()==null)
		{
			JOptionPane.showMessageDialog(null, "未选定文件", "错误",JOptionPane.ERROR_MESSAGE);
		}else{
		selectFilePath=jComboBox.getSelectedItem().toString();
		int index=jComboBox.getSelectedIndex();
		if(comDeleteFile(selectFilePath)){
			jComboBox.removeItemAt(index);//删除之后删除该项
			JOptionPane.showMessageDialog(null, selectFilePath+"已删除", "提示",JOptionPane.INFORMATION_MESSAGE);			
		}
		}
	}
	public void renameFile(JComboBox<File> jComboBox,String newname) {  //重命名文件方法
		String selectFilePath=null;
		int flag=0;
		if (jComboBox.getSelectedItem()==null)
		{
			JOptionPane.showMessageDialog(null, "未选定文件", "错误",JOptionPane.ERROR_MESSAGE);
		}else {
			selectFilePath=jComboBox.getSelectedItem().toString();
			int index=jComboBox.getSelectedIndex();
			int last=selectFilePath.lastIndexOf("\\"); //获取最后一个“\”的位置
			String directory=selectFilePath.substring(0,last);
			String oldname=selectFilePath.substring(last+1);
			int oldLastName=oldname.lastIndexOf(".");
			String oldLast=oldname.substring(oldLastName+1);
			int newLastName=newname.lastIndexOf(".");
			String newLast=newname.substring(newLastName+1);
			if(!oldLast.equals(newLast)){
			flag=JOptionPane.showConfirmDialog(null, "文件名后缀更改将会改变文件格式，可能导致文件不可用，是否确定更改？");	
			}
			if(flag==0){
			if(comRenameFile(directory, oldname, newname)){//调用通用重命名方法
			File newFile=new File(directory+"\\"+newname);
			jComboBox.removeItemAt(index);
			jComboBox.insertItemAt(newFile, index);
			jComboBox.setSelectedIndex(index);
			}
			
			}
			
		}
	}
}
