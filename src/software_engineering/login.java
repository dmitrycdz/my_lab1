package software_engineering;
import java.awt.*;   
import java.awt.event.*;   
import javax.swing.*;   

public class login extends JFrame implements ActionListener   
{      
	  private static final long serialVersionUID = 1L;
	  JButton b_subit =new JButton("  登录  ");   
	  JButton b_reset =new JButton("  退出  ");   
	  JLabel blank1=new JLabel("                                                                                                                                                                                                                                                                     ");   
	  JLabel blank2=new JLabel("                                                                                                                                                                                ");   
	  JLabel blank3=new JLabel("      "); 
	  JLabel blank4=new JLabel("        ");
	  TextField loginname = new TextField ("1150310320",24);   
	  JPasswordField password = new JPasswordField("0123456789",17);   
	  UserJFrame userjframe = new UserJFrame();   
	  private Font font1,font2;
	  static login loginframe = new login();  
	  public login()   
	  {    
	     super("Lab1-结对编程");   
	       
	     JLabel label_1 = new JLabel("  用户名  ");   
	     JLabel label_2 = new JLabel("    密码   ");   
	     b_subit.addActionListener(this);   
	     b_reset.addActionListener(this);   
	     font1 = new Font("SansSerif",Font.BOLD,25);
	     font2 = new Font("SansSerif",Font.BOLD,18);
	     this.setResizable(false);   
	     this.setSize(500,250);                 //设置框架尺寸   
	     this.setBackground(Color.LIGHT_GRAY);     //设置框架背景颜色   
	     this.setLocation(600,300);               //框架显示在屏幕的位置   
	     this.setLayout(new FlowLayout());        //框架流布局，居中   
	     this.add(new JLabel("                                                                                                                                                                                                               "));   
	     this.add(label_1);           //创建标签，添加到框架上    
	     this.add(loginname );     //创建文本行   
	     this.add(this.blank1);   
	     this.add(label_2);   
	     this.add(password);             //创建20列的文本行   
	     this.add(this.blank2);   
	     this.add(this.blank3);   
	     this.add(b_subit);              //创建按钮   
	     this.add(this.blank4);
	     this.add(b_reset);   
	     label_1.setFont(font1);
	     label_2.setFont(font1);
	     loginname.setFont(font1);
	     password.setFont(font1);
	     b_subit.setFont(font2);
	     b_reset.setFont(font2);
	     this.addWindowListener(new WinClose());     
	  }   
	  @Override
	  public void actionPerformed(ActionEvent e)           //单击按钮时触发执行   
	  {   
	      if(e.getSource()==b_subit)     
	      {  
	    	 String pwd = new String(password.getPassword());
	    	 if(loginname.getText().equals("1150310320")&&pwd.equals("0123456789"))   
	         {
	    		 JLabel jlabel = new JLabel("<html><body>1.请确保本电脑安装了GraphViz<br><br>2.请确保GraphViz.java文件中的dot.exe的路径修改为本电脑的有效路径<br><br></body></html>");
	    		 jlabel.setForeground(Color.red);
	    		 jlabel.setFont(new Font("宋体",Font.BOLD,22));
	    		 JOptionPane.showMessageDialog(this, jlabel,"提示",JOptionPane.WARNING_MESSAGE);
	    		 userjframe.setVisible(true);   
	             loginframe.setVisible(false);  
	         }   
	         else 
	         {
	        	JOptionPane.showMessageDialog(this,"用户名或密码错误\n");    
	            System.out.println(loginname.getText().equals("1150310320"));
	         }     
	      }   
	      if(e.getSource()==b_reset)
	      {   
	         System.exit(0);   
	      }   
	  }   
	  public void run()   
	  {       
	      loginframe.setVisible(true);                   //显示框架    
	  }
}   

class WinClose implements WindowListener   
{   
	  public void windowClosing(WindowEvent e)     //单击窗口关闭按钮时触发并执行   
	  {                                            //实现WindowListener接口中的方法   
	      System.exit(0);                          //结束程序运行   
	  }   
	 
	  public void windowOpened(WindowEvent e)         {  }   
	  public void windowActivated(WindowEvent e)      {  }   
	  public void windowDeactivated(WindowEvent e)    {  }   
	  public void windowClosed(WindowEvent e)         {  }   
	  public void windowIconified(WindowEvent e)      {  }   
	  public void windowDeiconified(WindowEvent e)    {  }   
}   