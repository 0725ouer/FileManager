package com.siqing.FileManager;
/**
 * 项目名称：FileManager
 * 项目主要功能：查看文件属性；删除和重命名文件。
 * 项目完成人：刘思清
 * 项目完成时间：2017.5.5
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import com.siqing.FileManager.FileOperation;

public class FileManager extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {  //主方法
		EventQueue.invokeLater(new Runnable() { //创建线程，节省内存
			public void run() {
				try {
					FileManager frame = new FileManager();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FileManager() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FileManager.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		setFont(new Font("华文行楷", Font.PLAIN, 20));
		setTitle("\u6587\u4EF6\u7BA1\u7406\u5668");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 594, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JComboBox<File> resultComboBox = new JComboBox<File>();
		resultComboBox.setSelectedItem(null);
		resultComboBox.setToolTipText("\u9009\u5B9A\u6587\u4EF6\u5939\u4E0B\u7684\u6587\u4EF6");
		FileOperation fileOperation=new FileOperation();

		JButton btnViewProperty = new JButton("\u67E5\u770B\u5C5E\u6027");
		btnViewProperty.setFont(new Font("宋体", Font.PLAIN, 12));
		btnViewProperty.addActionListener(new ActionListener() {  //显示属性按钮
			public void actionPerformed(ActionEvent e) {
				fileOperation.viewProperty(resultComboBox);
			}
		});
		btnViewProperty.setToolTipText("\u67E5\u770B\u5C5E\u6027");

		JButton btnDelete = new JButton("\u5220\u9664\u8BE5\u6587\u4EF6");
		btnDelete.setFont(new Font("宋体", Font.PLAIN, 12));
		btnDelete.addActionListener(new ActionListener() { //删除文件按钮
			public void actionPerformed(ActionEvent e) {
				fileOperation.deleteFile(resultComboBox);
			}
		});
		btnDelete.setToolTipText("\u5220\u9664\u6587\u4EF6");

		JButton btnRename = new JButton("\u91CD\u547D\u540D\u8BE5\u6587\u4EF6");
		btnRename.setFont(new Font("宋体", Font.PLAIN, 12));
		btnRename.addActionListener(new ActionListener() {  //重命名按钮
			public void actionPerformed(ActionEvent e) {
				if (resultComboBox.getSelectedItem()==null) {
					JOptionPane.showMessageDialog(null, "未选定文件", "错误",JOptionPane.ERROR_MESSAGE);
				}else{
				int last=resultComboBox.getSelectedItem().toString().lastIndexOf("\\"); //获取最后一个“\”的位置
				String oldname=resultComboBox.getSelectedItem().toString().substring(last+1);
				String inputValue = JOptionPane.showInputDialog("重命名文件，建议不要改变文件后缀:",oldname);//可输入的对话框
				if(inputValue!=null)
				fileOperation.renameFile(resultComboBox, inputValue);
				}
			}
		});
		btnRename.setToolTipText("\u91CD\u547D\u540D\u6587\u4EF6");

		JButton btnSelect = new JButton("\u9009\u62E9\u6587\u4EF6\u5939");
		btnSelect.setFont(new Font("宋体", Font.PLAIN, 16));
		btnSelect.addActionListener(new ActionListener() { //选择文件夹按钮
			public void actionPerformed(ActionEvent e) {
				fileOperation.viewFile(resultComboBox);
			}
		});
		btnSelect.setToolTipText("\u9009\u62E9\u6587\u4EF6\u5939");
		JLabel lblNewLabel = new JLabel("\u9009\u5B9A\u6587\u4EF6\u5939\u5373\u53EF\u5728\u53F3\u8FB9\u663E\u793A\u8BE5\u6587\u4EF6\u5939\u4E0B\u7684\u6587\u4EF6");
		lblNewLabel.setFont(new Font("华文行楷", Font.PLAIN, 20));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnViewProperty, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
									.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
									.addGap(32)
									.addComponent(btnRename, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnSelect, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(resultComboBox, 0, 294, Short.MAX_VALUE)))
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 286, Short.MAX_VALUE)
							.addGap(145))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSelect, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
						.addComponent(resultComboBox, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(43)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRename)
						.addComponent(btnViewProperty)
						.addComponent(btnDelete))
					.addGap(25))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
