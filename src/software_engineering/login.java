package software_engineering;
import java.awt.*;   
import java.awt.event.*;   
import javax.swing.*;   

public class login extends JFrame implements ActionListener   
{      
	  private static final long serialVersionUID = 1L;
	  JButton b_subit =new JButton("  ��¼  ");   
	  JButton b_reset =new JButton("  �˳�  ");   
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
	     super("Lab1-��Ա��");   
	       
	     JLabel label_1 = new JLabel("  �û���  ");   
	     JLabel label_2 = new JLabel("    ����   ");   
	     b_subit.addActionListener(this);   
	     b_reset.addActionListener(this);   
	     font1 = new Font("SansSerif",Font.BOLD,25);
	     font2 = new Font("SansSerif",Font.BOLD,18);
	     this.setResizable(false);   
	     this.setSize(500,250);                 //���ÿ�ܳߴ�   
	     this.setBackground(Color.LIGHT_GRAY);     //���ÿ�ܱ�����ɫ   
	     this.setLocation(600,300);               //�����ʾ����Ļ��λ��   
	     this.setLayout(new FlowLayout());        //��������֣�����   
	     this.add(new JLabel("                                                                                                                                                                                                               "));   
	     this.add(label_1);           //������ǩ����ӵ������    
	     this.add(loginname );     //�����ı���   
	     this.add(this.blank1);   
	     this.add(label_2);   
	     this.add(password);             //����20�е��ı���   
	     this.add(this.blank2);   
	     this.add(this.blank3);   
	     this.add(b_subit);              //������ť   
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
	  public void actionPerformed(ActionEvent e)           //������ťʱ����ִ��   
	  {   
	      if(e.getSource()==b_subit)     
	      {  
	    	 String pwd = new String(password.getPassword());
	    	 if(loginname.getText().equals("1150310320")&&pwd.equals("0123456789"))   
	         {
	    		 JLabel jlabel = new JLabel("<html><body>1.��ȷ�������԰�װ��GraphViz<br><br>2.��ȷ��GraphViz.java�ļ��е�dot.exe��·���޸�Ϊ�����Ե���Ч·��<br><br></body></html>");
	    		 jlabel.setForeground(Color.red);
	    		 jlabel.setFont(new Font("����",Font.BOLD,22));
	    		 JOptionPane.showMessageDialog(this, jlabel,"��ʾ",JOptionPane.WARNING_MESSAGE);
	    		 userjframe.setVisible(true);   
	             loginframe.setVisible(false);  
	         }   
	         else 
	         {
	        	JOptionPane.showMessageDialog(this,"�û������������\n");    
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
	      loginframe.setVisible(true);                   //��ʾ���    
	  }
}   

class WinClose implements WindowListener   
{   
	  public void windowClosing(WindowEvent e)     //�������ڹرհ�ťʱ������ִ��   
	  {                                            //ʵ��WindowListener�ӿ��еķ���   
	      System.exit(0);                          //������������   
	  }   
	 
	  public void windowOpened(WindowEvent e)         {  }   
	  public void windowActivated(WindowEvent e)      {  }   
	  public void windowDeactivated(WindowEvent e)    {  }   
	  public void windowClosed(WindowEvent e)         {  }   
	  public void windowIconified(WindowEvent e)      {  }   
	  public void windowDeiconified(WindowEvent e)    {  }   
}   