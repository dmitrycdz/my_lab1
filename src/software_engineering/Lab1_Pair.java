package software_engineering;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;
import software_engineering.GraphViz;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.PriorityQueue;  
import java.util.Comparator; 
/**��ȡԭʼ�ļ���������ת��Ϊ��׼�ļ�data.txt*/
class ReadFile{
	public void transformFile(String string){
		try{
			InputStream is = new FileInputStream(string);
			OutputStream os = new FileOutputStream("data.txt");
			int size = is.available();
			char str;
			boolean flag = true;
			for(int i=0; i<size; i++){
				str = (char)is.read();
				if(str >= 'a'&&str <= 'z'){
					os.write(str);
					flag = true;
				}
				else if(str >= 'A'&&str <= 'Z'){
					os.write((char)(str+32));
					flag = true;
				}
				else{
					if(flag&&str == ' '){
						os.write(str);
						flag = false;
					}
					else{
						if(flag){
							os.write(' ');
							flag = false;
						}
					}
				}
			}
			is.close();
			os.close();
		}catch(IOException e){
			//System.out.print("Exception");
		}
	}
}
//ͼ�ı߽����
class Edge{
	/**�ڽӶ������*/
	protected int verAdj;
	/**�ڽӶ�������*/
	protected String Name;
	/**�ߵ�Ȩֵ*/
	protected int cost;
	/**��һ���߽��*/
	protected Edge link;
}
//������еĽ����
class Vertex{
	/**�������*/
	protected int verName;
	/**��������**/
	protected String Name;
	/**�������ͷָ��*/
	protected Edge adjacent;
}
/**���ڽӱ����ʽ�洢ͼ�ṹ*/
class Graph{
	/**ָ�򶥵�������*/
	public Vertex[] head;
	/*��ǰ����ĸ���*/
	private int vertexNum;
	/*�ļ�·��*/
	private String str;
	/**���췽��*/
	Lab1_Pair lab1 = new Lab1_Pair();
	public Graph(String string){
		this.str = string;
	}
	public int buildGraph(){
		try{
			InputStream is = new FileInputStream(this.str);
			int size = is.available();
			this.vertexNum = size;
			this.head = new Vertex[this.vertexNum];
			for(int i = 0;i < this.vertexNum;i++){
				head[i] = new Vertex();
				head[i].verName = i;
				head[i].adjacent = null;
				head[i].Name = "!";
			}
			int num = 0,charnum = this.vertexNum;
			//System.out.println(i);
			String string1;
			string1 = "";
			char str1 = (char)is.read();
			while(str1 == ' '){
				str1 = (char)is.read();
				charnum -= 1;
			}
			charnum -= 1;
	    	while(str1 != ' '){
	    		string1 += str1;
	    		str1 = (char)is.read();
	    		charnum -= 1;
	    	}
	    	head[num++].Name = string1;
		    while(charnum > 0){
		    	String string2 = "";
		    	str1 = (char)is.read();
		    	charnum -= 1;
		    	while(str1 != ' '&&charnum >= 0){
		    		string2 += str1;
		    		str1 = (char)is.read();
		    		charnum -= 1;
		    	}
		    	for(int j = 0;j < this.vertexNum;j++){
		    		if(head[j].Name.equals(string1)){
		    			Edge q = head[j].adjacent;
		    			Edge qr = null;
		    			while(q != null){
		    				if(q.Name.equals(string2)){
		    					q.cost+=1;
		    					break;
		    				}
		    				qr = q;
		    				q = q.link;
		    			}
		    			if(q == null){
		    				Edge p = new Edge();
		    				//p.verAdj = num;
		    				p.Name = string2;
		    				p.cost = 1;
		    				p.link = null;
		    				if(qr == null)
		    					head[j].adjacent = p;
		    				else{
		    					p.link = head[j].adjacent;
		    					head[j].adjacent = p;
		    				}
		    				int n = findVertex(string2);
		    				if(n == -1){
		    					p.verAdj = num;
		    					head[num++].Name = string2;
		    				}
		    				else
		    					p.verAdj = n;
		    					
		    			}
		    			break;
		    		}
		    	}
		    	string1 = string2;
		    }
		    is.close();
		    //System.out.println("finished!!!");
		}catch(IOException e){
		    //System.out.print("Exception");
		}
		int a = 0;
		for(int i = 0 ;i < vertexNum&&!head[i].Name.equals("!");i++){
			a += 1;
		}
		this.vertexNum = a;
		//System.out.println(a);
		return this.vertexNum;
	}
	/**չʾ����ͼ*/
	public void showDirectedGraph(){
		GraphViz gv = new GraphViz();
        gv.addln(gv.start_graph());
        for(int i = 0;i < this.vertexNum;i++){
        	Edge p = this.head[i].adjacent;
        	while(p != null){
        		gv.addln(this.head[i].Name+"->"+p.Name+"[label="+p.cost+"];");
        		p = p.link;
        	}
        }
        gv.addln(gv.end_graph());
        //System.out.println(gv.getDotSource());
        
        String type = "gif";
        File out = new File(Lab1_Pair.pathtext+"/out." + type);
        gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
	}
	
	public int findVertex(String str){
		for(int i = 0;i < this.vertexNum;i++){
			if(head[i].Name.equals(str)){
				return i;
			}
			if(head[i].Name.equals("!"))
				break;
		}
		return -1;
	}
	
	public void printGraph(){
		for(int i = 0;i < this.vertexNum;i++){
			if(!head[i].Name.equals("!")){
				System.out.print(head[i].Name+":");
				Edge p = head[i].adjacent;
				while(p != null){
					System.out.print(p.Name+"("+p.cost+")"+" ");
					p = p.link;
				}
				System.out.print("\n");
			}
			else
				break;
		}
	}
}

class BFS{
	/**ָ�򶥵�������*/
	private Vertex[] head;
	/*��ǰ����ĸ���*/
	private int vertexNum;
	
	public BFS(Vertex[] H,int num){
		this.head = H;
		this.vertexNum = num;
	}
	//�õ���һ���ڽӽ����±�
    public int getFirstNeighbor(int index){
    	if (head[index].adjacent != null){
    		return head[index].adjacent.verAdj;
        }
        return -1;
    }

	//˽�к�����������ȱ���
    private String[] broadFirstSearch(boolean[] isVisited,int i,String destination){
        int u,flag = 0;
        String[] str;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        //���ʽ��i
        //System.out.print(getValueByIndex(i)+"  ");
        isVisited[i] = true;
        //��������
        //queue.addLast(i);
        //w = getFirstNeighbor(i);
        Edge p = null;
        p = head[i].adjacent;
        if(p == null){
        	//System.out.println("No bridge words from word1 to word2!");
        	str = new String[1];
        	str[0] = "0";
        	return str;
        }
        else{
        	while(p != null){
        		queue.addLast(p.verAdj);
        		isVisited[p.verAdj] = true;
        		p = p.link;
        	}
        }
        str = new String[this.vertexNum];
        int a = 0;
        while(!queue.isEmpty()){
            u = ((Integer)queue.removeFirst()).intValue();
            p = head[u].adjacent;
            while(p != null){
                if(!isVisited[p.verAdj]){
                	if(p.Name.equals(destination)){
                			//System.out.println("The bridge words from word1 to word2 are:");
                		str[a++] = head[u].Name;
                		//System.out.print(head[u].Name+" ");
                		flag = 1;
                		break;
                	}
                    isVisited[p.verAdj] = true;
                }
                p = p.link;
            }
        }
        if(flag == 0){
        	//str = new String[1];
        	str[0] = "0";
        }
        return str;
    }
    
    //���⹫��������������ȱ���
    public String[] broadFirstSearch(String word1,String word2){
    	boolean[] isVisited = new boolean[this.vertexNum];
    	String[] str;
    	for(int i = 0;i < this.vertexNum;i++)
    		isVisited[i] = false;
    	for(int i = 0;i < this.vertexNum;i++){
    		if(head[i].Name.equals(word2))
    			break;
    		else if(head[i].Name.equals("!")){
    			//System.out.println("No word1 or word2 in the graph!");
    			str = new String[1];
    			str[0] = "-1";
            	return str;
    		}
    	}
        for(int i = 0;i < this.vertexNum;i++){
            if(!isVisited[i]&&head[i].Name.equals(word1)){
                str = broadFirstSearch(isVisited,i,word2);
                return str;
            }
            else if(head[i].Name.equals("!")){
            	//System.out.println("No word1 or word2 in the graph!");
            	break;
            }
        }
        str = new String[1];
		str[0] = "-1";
    	return str;
    }
}

class Node{
	protected int id;
	protected int d;
}

class Dij{
	private static int INF = 9999;
	ArrayList<Integer>[] prev;
	int size;
	Node[] dist;
	PriorityQueue<Node> queue = new PriorityQueue<Node>();
	boolean[] flag;
	@SuppressWarnings("unchecked") //*** 
	public Dij(int vertexNum){
		size = vertexNum;
		prev = new ArrayList[size];
		flag = new boolean[size];
		dist = new Node[size];
		//System.out.println(dist.length);
		for (int i = 0;i<size;i++){
			prev[i] = new ArrayList<Integer>();
			prev[i].add(-1);
			flag[i] = false;
			dist[i] = new Node();
			dist[i].id = i;
			dist[i].d = INF;
		}
		queue = new PriorityQueue<Node>(size,
			new Comparator<Node>(){
				public int compare(Node n1,Node n2){ 
					return n1.d - n2.d;
				}
		});
	}
	public void dijkstra(int s,Vertex[] head){
		//System.out.println(G.head.length);
		dist[s].d = 0;
		queue.add(dist[s]);
		while(queue.peek()!=null){
			Node temp=queue.poll();
			Edge p = head[temp.id].adjacent;
			int u = temp.id;
			
			if(flag[u]) continue;
			flag[u] = true;
			
			while(p != null){
				int tempid = p.verAdj;
				int tempcost = p.cost;
				if(!flag[tempid]){
					if( dist[tempid].d > dist[u].d+tempcost){
						dist[tempid].d = dist[u].d+tempcost;
						prev[tempid].clear();
						prev[tempid].add(u);
						queue.add(dist[tempid]);
					}else if(dist[tempid].d == dist[u].d+tempcost){
						prev[tempid].add(u);
					}
				}
				
				p = p.link;
			}
		}
	}
	
	public ArrayList<ArrayList<Integer>> findpath(int beg,int end){
		ArrayList<ArrayList<Integer>> childPaths = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> midPaths = new ArrayList<ArrayList<Integer>>();
		if(beg != end){
			for(int i=0;i<prev[end].size();i++){
				childPaths = findpath(beg,prev[end].get(i));
				for(int j=0;j<childPaths.size();j++){
					childPaths.get(j).add(end);
				}
				if(midPaths.size()==0){
					midPaths = childPaths;
				}else{
					midPaths.addAll(childPaths);
				}
			}
		}else{
			ArrayList<Integer> temp = new ArrayList<Integer>(1);
			temp.add(beg);
			midPaths.add(temp);
		}
		return midPaths;
	}
}

public class Lab1_Pair{
	public static String pathtext = System.getProperty("user.dir");
	/**ָ�򶥵�������*/
	public static Vertex[] head;
	/*��ǰ����ĸ���*/
	public static int vertexNum;
	private static String[] str;
	private static boolean flag = false;
	public static ArrayList<ArrayList<Integer>> waypoint;
	public static void main(String args[]){
		login loginframe = new login();
		loginframe.run();
	    //loginframe.setVisible(true);
		//System.out.println(System.getProperty("user.dir"));
	}
	
	/**��ѯ�ŽӴ�*/
	public static String queryBridgeWords(String word1,String word2){
		String str_ = "";
		word1 = word1.toLowerCase();
		//if(str2[0] >= 'A'&&str2[0] <='Z')
		word2 = word2.toLowerCase();
		BFS bfsSearch = new BFS(head,vertexNum);
		str = bfsSearch.broadFirstSearch(word1, word2);
		if(str[0].equals("-1")&&!flag)
			str_ = "-1";
		else if(str[0].equals("0")&&!flag)	
			str_ = "0";
		else if(!flag){
			//System.out.println("The bridge words from word1 to word2 are:");
			for(int i = 0;i < str.length&&str[i] != null;i++){
				str_ += (str[i]+" ");
			}
		}
		return str_;
	}
	/**����bridge word �������ı�*/
	public static String generateNewText(String inputText){
		String[] str1 = inputText.split(" ");
		//boolean Flag = false;
		/*if(str1[0].charAt(0) >= 'A'&& str1[0].charAt(0) <= 'Z'){
			Flag = true;
		}*/
		String[] str2 = new String[2*str1.length];
		int j = 0;
		flag = true;
		int i;
		for(i = 0;i < str1.length-1;i++){
			queryBridgeWords(str1[i].toLowerCase(),str1[i+1].toLowerCase());
			if(str[0].equals("0")||str[0].equals("-1")){
				str2[j++] = str1[i];
				//str2[j++] = str1[i+1];
			}
			else{
				str2[j++] = str1[i];
				str2[j++] = str[0];
			}
		}
		flag = false;
		str2[j] = str1[i];
		String string = "";
		for(i = 0;i < str2.length&&str2[i] != null;i++){
			string+=str2[i];
			string+=" ";
		}
		return string;
	}
	/**������������֮������·��*/
	public static String calcShortestPath(String word1,String word2){
		int INF = 9999;
		String ans = "";
		int w1=-1,w2=-1;
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		for(int i=0;i<vertexNum;i++){
			if(word1.equals(head[i].Name))
				w1 = i;
			if(word2.equals(head[i].Name))
				w2 = i;
		}
		if(w1 == -1 || w2 == -1)
			return "�ı��в����������ĳ����";
		Dij D = new Dij(vertexNum);
		D.dijkstra(w1,head);
		if(D.dist[w2].d == INF){
			ans += "���ɴ�";
			return ans;
		}
		waypoint = new ArrayList<ArrayList<Integer>>();
		waypoint = D.findpath(w1,w2);
		for(int i=0;i<waypoint.size();i++){
			//ans += "[";
			for(int j=0;j<waypoint.get(i).size()-1;j++){
				ans += head[waypoint.get(i).get(j)].Name;
				ans += "->";
			}
			ans += head[waypoint.get(i).get(waypoint.get(i).size()-1)].Name;
			ans += "  "+ "(length: "+D.dist[w2].d+")\n";
		}
		return ans;
	}
	/**����һ�����ʵ�����֮������·��*/
	public static String calcShortestPath_Oneword(String word){
		int wd = -1;
		String ans = "";
		word = word.toLowerCase();
		for(int i = 0;i<vertexNum;i++){
			if(word.equals(head[i].Name))
				wd = i;
		}
		if(wd == -1)
			return "�ı��в���������Ĵ�";
		Dij D = new Dij(vertexNum);
		D.dijkstra(wd,head);
		for(int i =0;i<vertexNum;i++){
			if(i==wd) continue;
			ans += word+"==>"+head[i].Name+":\n";
			ans += calcShortestPath(word,head[i].Name);
			ans += "\n";
		}
		return ans;
	}
	/**�������*/
	public static String randomWalk(){
		int r = (int)(Math.random()*vertexNum);
		String string = "";
		int [][]isVisited = new int[vertexNum][vertexNum];
		for(int i = 0;i < vertexNum;i++)
			for(int j = 0;j < vertexNum;j++)
				isVisited[i][j] = 0;
		try{
			string += head[r].Name;
			string += " ";
			Edge p = head[r].adjacent;
			while(p != null){    /**�����Ƿ����*/
				//System.out.print(head[p.verAdj].Name+" ");
				string += head[p.verAdj].Name;
				string += " ";
				if(isVisited[r][p.verAdj] == 0){
					isVisited[r][p.verAdj] = 1;
					r = p.verAdj;
					p = head[p.verAdj].adjacent;
				}
				else
					break;
			}
		}catch(Exception e){string = "0";}
		return string;
	}
}