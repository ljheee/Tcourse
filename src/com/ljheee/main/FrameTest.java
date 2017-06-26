package com.ljheee.main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;




public class FrameTest extends JFrame implements ActionListener, MouseListener,
		MouseWheelListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel jp;
	JLabel jl;
	JButton jb1, jb2, jb3, jb4;

	ImageIcon imgIco, showii;
	float width;
	float height;

	void init() {
		jp = new JPanel();
		jp.setLayout(null);
		jp.setBackground(new Color(0, 0, 0, 50));

		jb1 = new JButton();
		jb1.setIcon(new ImageIcon("img/bigger.png"));
		jb1.addActionListener(this);
		jb1.setBounds(280, 475, 42, 28);
		jb2 = new JButton();
		jb2.setIcon(new ImageIcon("img/smaller.png"));
		jb2.addActionListener(this);
		jb2.setBounds(320, 475, 42, 28);
		jb3 = new JButton();
		jb3.setIcon(new ImageIcon("img/save.png"));
		jb3.addActionListener(this);
		jb3.setBounds(360, 475, 42, 28);
		// 关闭按钮
		jb4 = new JButton();
		jb4.setIcon(new ImageIcon("img/closeButton.png"));
		jb4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		jb4.setBounds(675, 0, 25, 25);

		jl = new JLabel("", JLabel.CENTER);
		jl.setBounds(50, 50, 600, 400);
		jl.addMouseWheelListener(this);
		//jl.addMouseListener(this);
		 jp.addMouseListener(this);
		 //jp.addMouseMotionListener(this);

		jp.add(jb1);
		jp.add(jb2);
		jp.add(jb3);
		jp.add(jb4);
		jp.add(jl);

		

		this.add(jp);
		// 去边框
		this.setUndecorated(true);
		// 必须先去边框再添加背景色
		this.setBackground(new Color(0, 0, 0, 50));
		this.setSize(700, 500);
		this.setLocation(300, 200);
		this.setVisible(true);
		// 下面这行代码如果卸载上面注释的位置会报错

	}

	public static void main(String[] args) {

		FrameTest ft = new FrameTest();
		ft.init();
		ft.imgIco = ft.getAvatar("zhujiannan", 0);
		ft.ShowImg();
	}

	// 拖动边框

	int xOld = 0;
	int yOld = 0;

	public FrameTest() {
		// this.setLayout(null);
		// System.out.println("aaa");

		// 处理拖动事件
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xOld = e.getX();
				yOld = e.getY();
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int xOnScreen = e.getXOnScreen();
				int yOnScreen = e.getYOnScreen();
				int xx = xOnScreen - xOld;
				int yy = yOnScreen - yOld;
				FrameTest.this.setLocation(xx, yy);
			}
		});

	}
	
	
	
	

	

	public void ShowImg() {
		if (imgIco.getIconWidth() > 695 || imgIco.getIconHeight() > 465) {
			width = 695;
			height = 675 * (float) imgIco.getIconHeight()
					/ imgIco.getIconWidth();
			// System.out.println((float)ii.getIconHeight()/ii.getIconWidth());
		} else {
			width = imgIco.getIconWidth();
			height = imgIco.getIconHeight();
		}
		showii = new ImageIcon(imgIco.getImage().getScaledInstance((int) width,
				(int) height, 0));
		this.jl.setText("");
		this.jl.setIcon(showii);

	}

	public void Zoom(int flag) {
		//System.out.println(width + "  " + flag);
		if ((width < 5000 && height < 4000) && (width > 5 && height > 4)) {
			if (flag == 0) {
				// 放大
				width *= 1.3;
				height *= 1.3;
			} else if (flag == 1) {
				// 缩小
				width *= 0.7;
				height *= 0.7;
			}
			this.jl.setIcon(null);
			showii = new ImageIcon(imgIco.getImage().getScaledInstance(
					(int) width, (int) height, 0));
			//todo
			this.jl.setIcon(showii);

		} else if (width > 5000 || height > 4000) {
			width *= 0.8;
			height *= 0.8;
			JOptionPane.showMessageDialog(this, "再放大会失真");
		} else if (width < 5 || height < 3) {
			width *= 1.2;
			height *= 1.2;
			JOptionPane.showMessageDialog(this, "再缩小就看不见");
		}
	}

	public void Save() {
		// 弹出文件选择框
		JFileChooser chooser = new JFileChooser();

		// 下面的方法将阻塞，直到【用户按下保存按钮且“文件名”文本框不为空】或【用户按下取消按钮】
		int option = chooser.showSaveDialog(null);
		if (option == JFileChooser.APPROVE_OPTION) { // 假如用户选择了保存
			File file = chooser.getSelectedFile();
			downUrlTxt("",
					"",
					file.getPath());
		}
	}

	public void downUrlTxt(String fileName, String fileUrl, String downPath) {
		File savePath = new File(downPath);
		if (!savePath.exists()) {
			savePath.mkdir();
		}
		String[] urlname = fileUrl.split("/");
		int len = urlname.length - 1;
		String uname = urlname[len];// 获取文件名
		try {
			File file = new File(savePath + "/" + "x_" + uname);// 创建新文件
			if (file != null && !file.exists()) {
				file.createNewFile();
			}
			OutputStream oputstream = new FileOutputStream(file);
			URL url = new URL(fileUrl);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			uc.setDoInput(true);// 设置是否要从 URL 连接读取数据,默认为true
			uc.connect();
			InputStream iputstream = uc.getInputStream();
			// System.out.println("file size is:"+uc.getContentLength());//打印文件长度
			byte[] buffer = new byte[4 * 1024];
			int byteRead = -1;
			while ((byteRead = (iputstream.read(buffer))) != -1) {
				oputstream.write(buffer, 0, byteRead);
			}
			oputstream.flush();
			iputstream.close();
			oputstream.close();
		} catch (Exception e) {
			System.out.println("读取失败！");
			e.printStackTrace();
		}

	}

	//imagUrl里面有是个网址，然后这个网址里面有张图片

	public ImageIcon getAvatar(final String barejid, final int size) {
		String imageUrl = "";
		// ImageIcon avatarIcon = null;
		// try {
		// avatarIcon = new ImageIcon(new ImageIcon(new
		// URL(imageUrl)).getImage().getScaledInstance(36, 36, 4));
		// if (avatarIcon == null || avatarIcon.getIconWidth() <= 0) {
		// //avatarIcon =
		// SparkRes.getImageIcon(SparkRes.DEFAULT_AVATAR_32x32_IMAGE);
		// }
		// } catch (Exception e) {
		// }
		//
		// return avatarIcon;
		try {
			return new ImageIcon(new URL(imageUrl));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imgIco;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == jb1) {

			Zoom(0);

		} else if (e.getSource() == jb2) {
			
			
			// 缩小
			Zoom(1);
		} else if (e.getSource() == jb3) {
			// 保存
			Save();
		}
	}

	//

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		if (e.getWheelRotation() == 1) {
			Zoom(0);
		} else if (e.getWheelRotation() == -1) {
			Zoom(1);
		}

	}

	@Override
	public void mouseClicked(MouseEvent mouseevent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent mouseevent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent mouseevent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent mouseevent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent mouseevent) {
		// TODO Auto-generated method stub

	}

}
