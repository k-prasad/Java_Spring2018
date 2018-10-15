package edu.umb.cs.cs681.hw14;

public class FileSystem {
	private static FileSystem instance;
	public Directory root = null;
	private int tab;

	private FileSystem() {}
	
	public static FileSystem getFileSystem(){
		
		clearSystem();
		
		if (instance == null){
			instance = new FileSystem();
			
		}
		return instance;
	}
	
	private static void clearSystem(){
		// Set singleton instance to null
		// to clear out old instance
		// in case one exists
		instance = null;
	}
	
	public Directory getRootDirectory(){
		if (this.root == null){
				this.root = new Directory(null, "PC Root");
				//System.out.println("\n");
				this.root.setOwner("windowsSystem");
			}
		
		return this.root;
	}
	
	public void showAllElements(){
		
		System.out.println("Welcome to the File Fystem\n\n");
		
		if (root.getChildren().size() > 0){	
			
			StringBuilder sb = new StringBuilder();
			
			sb.append(root + "\n");
				
				for (FSElement e : root.getChildren()){
					//System.out.println("i m here");
					sb.append("\t" + e + "\n");
					
					if (!e.isFile() && !e.isLink()){
						
						Directory dir = root.getDirectory(e.getName());
						
						this.tab=2;
						
						for (FSElement a :dir.getChildren()){
							
							sb.append("\t\t" + a + "\n");
							
							moreElements(a, this.tab, sb);
							
						}
						
					}
					
				}
			
			System.out.print(sb);
			
		} else {
			System.out.println("Root directory is EMPTY!.");
		}
		
	}
	
	public void moreElements (FSElement a, int num, StringBuilder s){
		
		StringBuilder sb = s;
	
		if (!a.isFile() && !a.isLink()){
			
			Directory dir1 = (Directory) a;
			
			dir1.getDirectory(a.getName());
			
			this.tab++;
			
			for (FSElement b :dir1.getChildren()){
			
				sb.append(getTab() + b + "\n");
				
				moreElements(b, this.tab, sb);
			}
		}
		
	}
	
	public String getTab(){
		
		String tabs = "";
		String tab = "\t";
		
		for(int i=0; i < this.tab; i++){
			
			tabs = tabs + tab;
		}
		
		return tabs;
		
	}
	
	public static void main (String args[]){
		FileSystem fileSystem = FileSystem.getFileSystem();
		
		File rand = new File(fileSystem.getRootDirectory(), "hidden file", 5);
		
		Directory system = new Directory(fileSystem.root, "C Drive: \n\t|__ ");
		File a = new File(system, "Explorer.exe", 10);
		File b = new File(system, "Calculator.exe", 10);
		File c = new File(system, "System Restore.exe", 10);
		
		
		Directory home = new Directory(fileSystem.root, "D Drive: \n\t|__ ");
		File d = new File(home, "hw14.java\n", 10);
		Link x = new Link(home, "puppy.png");
		x.setElement(system);
		
		Directory music = new Directory(home, "- MyMusic \n\t\t|__");
		File e = new File(music, "Fireball.mp3", 10);
		File f = new File(music, "Bloodstain.mp3", 10);
		Link y = new Link(music, "DeepBlue.mp3", f);
		Link x2 = new Link(music, "Heights.mp3", x);
		Link x3 = new Link(music, "HowLong.mp3\n", x2);
		
		Directory newMusic = new Directory(music, " ~80's Music files \n\t\t\t|__");
		File g = new File(music, "Shake.mp3", 10 );
		File h = new File(music, "Sky.mp3\n", 10);
		
		Directory morenewMusic = new Directory(music, " ~EDM Music files \n\t\t\t\t|__");
		File i = new File(music, "You&Me.mp3", 10);
		File j = new File(music, "YoungBlood.mp3\n", 10);
		
		Directory evenMorenewMusic = new Directory(music, " ~R & B Music files~ \n\t\t\t\t\t|__");
		File k = new File(music, "Fantacy.mp3", 10);
		File l = new File(music, "OnlyYou.mp3", 10);
		
		fileSystem.root.appendChild(system);
		fileSystem.root.appendChild(home);
		fileSystem.root.appendChild(rand);
		
		system.appendChild(a);
		system.appendChild(b);
		system.appendChild(c);
		
		music.appendChild(e);
		music.appendChild(f);
		music.appendChild(y);
		music.appendChild(x2);
		music.appendChild(x3);
		
		newMusic.appendChild(g);
		newMusic.appendChild(h);
		
		morenewMusic.appendChild(i);
		morenewMusic.appendChild(j);
		
		evenMorenewMusic.appendChild(k);
		evenMorenewMusic.appendChild(l);
		
		home.appendChild(d);
		home.appendChild(music);
		home.appendChild(x);
		
		music.appendChild(newMusic);
		newMusic.appendChild(morenewMusic);
		morenewMusic.appendChild(evenMorenewMusic);
		
		fileSystem.showAllElements();
		
		System.out.println("\n\n");
		
		System.out.println("CRAWLER & INDEXER Started \n");
		
		FileQueue fq1 = new FileQueue ();
		FileQueue fq2 = new FileQueue ();
		FileQueue fq3 = new FileQueue ();
		
		
		FileCrawler fileCrawler1 = new FileCrawler(fq1, fileSystem.root);
        FileIndexer fileIndex1 = new FileIndexer(fq1);
        Thread crawlerThread1 = new Thread(fileCrawler1, "Crawler Thread-1,");
        Thread indexThread1 = new Thread(fileIndex1, "Indexer Thread-1,");
        
        FileCrawler fileCrawler2 = new FileCrawler(fq2, evenMorenewMusic);
        FileIndexer fileIndex2 = new FileIndexer(fq2);
        Thread crawlerThread2 = new Thread(fileCrawler2, "Crawler Thread-2,");
        Thread indexThread2 = new Thread(fileIndex2, "Indexer Thread-2,");
        
        FileCrawler fileCrawler3 = new FileCrawler(fq3, morenewMusic);
        FileIndexer fileIndex3 = new FileIndexer(fq3);
        Thread crawlerThread3 = new Thread(fileCrawler3, "Crawler Thread-3,");
        Thread indexThread3 = new Thread(fileIndex3, "Indexer Thread-3,");
        
        
        
        crawlerThread1.start();
        indexThread1.start();
        
        crawlerThread2.start();
        indexThread2.start();
        
        crawlerThread3.start();
        indexThread3.start();
        
        
        while(true){
            if(!crawlerThread1.isAlive()){
            	indexThread1.interrupt();
            	break;
            }
        }
        
        while(true){
            if(!crawlerThread2.isAlive()){
            	indexThread2.interrupt();
            	break;
            }
        }
        
        while(true){
            if(!crawlerThread3.isAlive()){
            	indexThread3.interrupt();
            	break;
            }
        }
        
        
        fileCrawler1.setDone();
        fileIndex1.setDone();
        fileCrawler2.setDone();
        fileIndex2.setDone();
        fileCrawler3.setDone();
        fileIndex3.setDone();
        
        
        try {
			
        	crawlerThread1.join();
        	indexThread1.join();
        	crawlerThread2.join();
        	indexThread2.join();
        	crawlerThread3.join();
        	indexThread3.join();

		} catch (InterruptedException interupt) {
			interupt.printStackTrace();}
		
	}


}



