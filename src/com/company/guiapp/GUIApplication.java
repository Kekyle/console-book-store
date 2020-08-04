package com.company.guiapp;

import com.company.Application;
import com.company.guiapp.action.AuthorizationAction;
import com.company.guiapp.action.RegistrationAction;
import com.company.repository.*;
import com.company.repository.file.*;
import com.company.service.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GUIApplication implements Application {

    private Properties properties = new Properties();

    {
        try {
            properties.load(new FileReader(System.getenv("CONFIGURATION_FILE_PATH")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File authorFile = new File(properties.getProperty("file.author"));
    private File bookFile = new File(properties.getProperty("file.book"));
    private File addressFile = new File(properties.getProperty("file.addresses"));
    private File storeFile = new File(properties.getProperty("file.stores"));
    private File userFile = new File(properties.getProperty("file.users"));
    private File roleFile = new File(properties.getProperty("file.role"));
    private File orderFile = new File(properties.getProperty("file.orders"));
    private File statusFile = new File(properties.getProperty("file.status"));

    private AuthorRepository authorRepository = new FileAuthorRepository(authorFile);
    private BookRepository bookRepository = new FileBookRepository(bookFile);
    private AddressRepository addressRepository = new FileAddressRepository(addressFile);
    private StoreRepository storeRepository = new FileStoreRepository(storeFile);
    private UserRepository userRepository = new FileUserRepository(userFile, roleFile);
    private OrderRepository orderRepository = new FileOrderRepository(orderFile, userFile, roleFile, addressFile, storeFile, authorFile, bookFile, statusFile);

    private AuthorService authorService = new AuthorServiceImpl(authorRepository);
    private BookService bookService = new BookServiceImpl(bookRepository, authorRepository);
    private AddressService addressService = new AddressServiceImpl(addressRepository);
    private StoreService storeService = new StoreServiceImpl(storeRepository);
    private UserService userService = new UserServiceImpl(userRepository);
    private  OrderService orderService = new OrderServiceImpl(orderRepository);


    @Override
    public void start() {
        JFrame jFrame = new JFrame();
        JPanel jPanel = new JPanel(new GridLayout(5, 1));

//        JMenuBar jMenuBar = new JMenuBar();
//        jMenuBar.add(new JMenu("Menu"));
//        jMenuBar.add(new JMenu("About"));

        JTextField login = new JTextField(20);
        JTextField password = new JPasswordField(20);
        JButton registration = new JButton("Registration");
        JButton authorization = new JButton("Authorization");

        jPanel.add(login);
        jPanel.add(password);
        jPanel.add(authorization);
        jPanel.add(registration);

        registration.addActionListener(new RegistrationAction(userService, login.getText(), password.getText()));
        authorization.addActionListener(new AuthorizationAction(userService, login.getText(), password.getText()));

//        jPanel.add(jMenuBar);

        jFrame.setContentPane(jPanel);
        jFrame.setVisible(true);
        jFrame.setSize(600, 800);
        jFrame.setTitle("Book Store");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

//    private static JTextArea jTextArea = new JTextArea();
//    private static JTextField jTextField2 = new JTextField(20);
//    private static JTextField jTextField = new JTextField(20);
//
//    @Override
//    public void start() {
//        JFrame jFrame = new JFrame();
//        JPanel jPanel = new JPanel();
//
//        JButton jButton = new JButton("Calc");
//
//        jButton.addActionListener(new CalcAction());
//
//        jPanel.add(jTextField);
//        jPanel.add(jButton);
//        jPanel.add(jTextField2);
//        jPanel.add(jTextArea);
//
//        jFrame.setContentPane(jPanel);
//        jFrame.setVisible(true);
//        jFrame.setSize(600, 800);
//        jFrame.setTitle("Book Store");
//        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//    }
//
//    public static class CalcAction implements ActionListener{
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            jTextArea.setText("Result" + (Integer.parseInt(jTextField.getText()) + Integer.parseInt(jTextField2.getText())));
//        }
//    }
}
