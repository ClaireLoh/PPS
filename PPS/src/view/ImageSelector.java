package view;

import javax.swing.JFrame;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import ij.io.Opener;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import controller.Image;

public class ImageSelector extends JFrame implements ActionListener
{
  
    private JMenuItem   open;
    private JFileChooser fc;
    private JLabel jl;
    private File img[];
    private ImageIcon imgIcon;
            
    public ImageSelector()
    {
        setTitle("Image Viewer");
        setLayout(new FlowLayout());
        setJMenuBarAndMenuBarItems();
        setJFileChooser();
        setJLabel();
        setAction();
        setSize(700,500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
  
    void setJMenuBarAndMenuBarItems()
    {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("File");
        open = new JMenuItem("Open");
        menu1.add(open);
        menuBar.add(menu1);
        setJMenuBar(menuBar);
    }
    
    void setJLabel()
    {
        jl = new JLabel();
        add(jl);
    }
    
    void setJFileChooser()
    {
        fc = new JFileChooser();
        fc.setMultiSelectionEnabled(true);
    }
  
    void setAction()
    {
        open.addActionListener(this);
    }
  
    public void actionPerformed(ActionEvent eve)
    {
        int getfile;
        if(eve.getSource() == open)
        {
            getfile = fc.showOpenDialog(rootPane);
            
            if(getfile==JFileChooser.APPROVE_OPTION)
            {
            	img = fc.getSelectedFiles();
            	BufferedImage bufferedImage;
            	for(File picture:img)
            	{
            		try {
						bufferedImage = ImageIO.read(picture);
						new Image();
						BufferedImage nImage = Image.imageDeskew(bufferedImage);
						
						//Image img = new Image();
						
						/*BufferedImage thumbnail = Thumbnails.of(nImage)
								.size(300,300)
								.watermark(Position.BOTTOM_RIGHT,watermarkImage,0.5f)
								.asBufferdImage();*/
	            		JLabel JLabelPicture = new JLabel(new ImageIcon(nImage));
	            		
	            		add(JLabelPicture);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

            	}
            	repaint();
            	revalidate();
            	/*BufferedImage bufferedImage;
				try {
					for(int i =0; i<=img.length;i++)
					{
						
						bufferedImage = ImageIO.read(new(img[i]));
						new Image();
						BufferedImage nImage = Image.imageDeskew(bufferedImage);
						imgIcon = new ImageIcon(nImage);
						jl.setIcon(imgIcon);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
            }*/
            }
        }
    }
}

