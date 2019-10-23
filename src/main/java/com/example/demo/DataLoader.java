package com.example.demo;


import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void run(String... strings) throws Exception {
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));
        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        User user = new User("mark@user.com", "password", "Mark", "User", true, "mark", "user");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new User("admin@admin.com", "password", "Admin", "User", true, "admin", "administrator");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);


       /* System.out.println("Sending Email...");

        try {
            sendEmail();
//            sendEmailWithAttachment();

        } catch (Exception e) {
            e.printStackTrace();
        *//*} catch (IOException e) {
            e.printStackTrace();*//*
        }
//        sendEmail();

        System.out.println("Done");
    }

    void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("skbijukchhe@gmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);

    }

    void sendEmailWithAttachment() throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo("1@gmail.com");

        helper.setSubject("Testing from Spring Boot");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1>Check attachment for image!</h1>", true);

        //FileSystemResource file = new FileSystemResource(new File("classpath:android.png"));

        //Resource resource = new ClassPathResource("android.png");
        //InputStream input = resource.getInputStream();

        //ResourceUtils.getFile("classpath:android.png");

        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

        javaMailSender.send(msg);

    }*/
    }


    public void loadFromFile(){
       //File file = new File("\\resources\\ Magneto_Megafood.txt");

        try {
            FileInputStream fis = new FileInputStream("src/main/resources/Magneto_Megafood.txt");
            String data = IOUtils.toString(fis, "UTF-8");
            ArrayList<String> dataLines = new ArrayList(Arrays.asList(data.split("\n")));
            for (String line : dataLines) {
                String[] product_data = line.split("\t");
                boolean active = Boolean.parseBoolean(product_data[4]);
                Product product = new Product(product_data[0], product_data[1], product_data[2],
                        product_data[3], active);        //name, description, price, URL, active
            }
        }catch(Exception IOException){
            System.out.println("IO Exception Found");
        }
    }
}
