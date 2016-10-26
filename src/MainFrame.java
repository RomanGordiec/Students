import javax.swing.*;

/**
 * Created by Пользователь on 27.10.2016.
 */
public class MainFrame extends JFrame
{
    public MainFrame()
    {
        setBounds(100,100,300,200);
    }

    public static void main(String...args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                MainFrame mf = new MainFrame();
                mf.setDefaultCloseOperation(EXIT_ON_CLOSE);
                mf.setVisible(true);
            }
        });
    }
}
