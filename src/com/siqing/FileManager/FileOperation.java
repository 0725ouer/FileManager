package com.siqing.FileManager;
/**
 * FileOperation:�Զ����ļ�������
 * �����ԣ�filePath
 * �෽����viewFile,viewProperty,deleteFile,renameFile,comrenameFile,comdeleteFile
 */

import java.io.File;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileOperation {
	private String filePath=null;  //ѡ����ļ���·��
	public void viewFile(JComboBox<File> jComboBox) {  //��ʾ�ļ����е��ļ�
		JFileChooser selectFolder = new JFileChooser("C://");
		selectFolder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//��ѡ���ļ���
		int returnVal = selectFolder.showOpenDialog(selectFolder);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			filePath = selectFolder.getSelectedFile().getAbsolutePath();// ѡ����ļ��е�·��
			jComboBox.removeAllItems();//��������б�
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
	
	public boolean comDeleteFile(String sPath) {  //ͨ��ɾ���ļ�����
	    boolean flag = false;  
	    File file = new File(sPath);  
	    // ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}
	public boolean comRenameFile(String path,String oldname,String newname){ //ͨ������������
        if(!oldname.equals(newname)){//�µ��ļ�������ǰ�ļ�����ͬʱ,���б�Ҫ���������� 
            File oldfile=new File(path+"\\"+oldname); 
            File newfile=new File(path+"\\"+newname); 
            if(!oldfile.exists()){
            	JOptionPane.showMessageDialog(null, "�������ļ�������", "����",JOptionPane.ERROR_MESSAGE);
            	return false;
            }
            if(newfile.exists()){//���ڸ�Ŀ¼���Ѿ���һ���ļ������ļ�����ͬ�������������� 
            	JOptionPane.showMessageDialog(null, newname+"�Ѿ����ڣ�", "����",JOptionPane.ERROR_MESSAGE);
            	return false;
            	} 
            else{
                oldfile.renameTo(newfile);//��������������
                return true;
            } 
        }else{
        	JOptionPane.showMessageDialog(null, "���ļ����;��ļ�����ͬ", "����",JOptionPane.ERROR_MESSAGE);
        	return false;
        }
    }
	public void viewProperty(JComboBox<File> jComboBox) {   //�鿴���Է���		
		if (jComboBox.getSelectedItem()==null) {
			JOptionPane.showMessageDialog(null, "δѡ���ļ�", "����",JOptionPane.ERROR_MESSAGE);
		}else{
		File aFile=new File(jComboBox.getSelectedItem().toString());
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String dateTime=df.format(new Date(aFile.lastModified()));//�޸�ʱ��ת������������
		String property="�ļ��ϼ�Ŀ¼: "+aFile.getParent()+
	            "\n�ļ�����Ŀ¼�����ļ�����: "+aFile.getPath()+
	            "\n�Ƿ�ɶ�: "+aFile.canRead()+
	            "\n�Ƿ��д: "+aFile.canWrite()+
	            "\n�ļ���: "+aFile.getName()+
	            "\n�ļ�����: "+aFile.length()+"�ֽ�"+
	            "\n����޸�ʱ��: "+dateTime+
	            "\n�Ƿ�����: "+aFile.isHidden();
		JOptionPane.showMessageDialog(null, property,"�ļ�����",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public void deleteFile(JComboBox<File> jComboBox) { //ɾ���ļ�����
		String selectFilePath=null;
		if (jComboBox.getSelectedItem()==null)
		{
			JOptionPane.showMessageDialog(null, "δѡ���ļ�", "����",JOptionPane.ERROR_MESSAGE);
		}else{
		selectFilePath=jComboBox.getSelectedItem().toString();
		int index=jComboBox.getSelectedIndex();
		if(comDeleteFile(selectFilePath)){
			jComboBox.removeItemAt(index);//ɾ��֮��ɾ������
			JOptionPane.showMessageDialog(null, selectFilePath+"��ɾ��", "��ʾ",JOptionPane.INFORMATION_MESSAGE);			
		}
		}
	}
	public void renameFile(JComboBox<File> jComboBox,String newname) {  //�������ļ�����
		String selectFilePath=null;
		int flag=0;
		if (jComboBox.getSelectedItem()==null)
		{
			JOptionPane.showMessageDialog(null, "δѡ���ļ�", "����",JOptionPane.ERROR_MESSAGE);
		}else {
			selectFilePath=jComboBox.getSelectedItem().toString();
			int index=jComboBox.getSelectedIndex();
			int last=selectFilePath.lastIndexOf("\\"); //��ȡ���һ����\����λ��
			String directory=selectFilePath.substring(0,last);
			String oldname=selectFilePath.substring(last+1);
			int oldLastName=oldname.lastIndexOf(".");
			String oldLast=oldname.substring(oldLastName+1);
			int newLastName=newname.lastIndexOf(".");
			String newLast=newname.substring(newLastName+1);
			if(!oldLast.equals(newLast)){
			flag=JOptionPane.showConfirmDialog(null, "�ļ�����׺���Ľ���ı��ļ���ʽ�����ܵ����ļ������ã��Ƿ�ȷ�����ģ�");	
			}
			if(flag==0){
			if(comRenameFile(directory, oldname, newname)){//����ͨ������������
			File newFile=new File(directory+"\\"+newname);
			jComboBox.removeItemAt(index);
			jComboBox.insertItemAt(newFile, index);
			jComboBox.setSelectedIndex(index);
			}
			
			}
			
		}
	}
}
