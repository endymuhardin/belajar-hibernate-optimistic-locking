package com.muhardin.endy.belajar.hibernate;

import com.muhardin.endy.belajar.hibernate.dao.CustomerDao;
import com.muhardin.endy.belajar.hibernate.entity.Customer;
import java.util.Random;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws Exception {
        System.out.println( "Hello World!" );
        
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        
        // flyway migration, untuk mengontrol kondisi awal database
        DataSource ds = ctx.getBean(DataSource.class);
        Flyway flyway = new Flyway();
        flyway.setDataSource(ds);
        flyway.setInitOnMigrate(true);
        flyway.migrate();
        
        
        CustomerDao dao = ctx.getBean(CustomerDao.class);
        Customer c = dao.findById(100L);
        
        System.out.println("Customer Name : "+c.getName());
        System.out.println("Version : "+c.getVersion());
        
        Integer delay = new Random().nextInt(10);
        
        if(args.length > 0){
            delay = Integer.parseInt(args[0]);
        }
        
        System.out.println("Delay "+delay+" secs before update value");
        Thread.sleep(delay * 1000);
        
        String newValue = "Ifnu Bima";
        
        if(args.length > 1){
            newValue = args[1];
        }
        
        System.out.println("Update customer name to "+newValue);
        
        c.setName(newValue);
        dao.save(c);
        
        System.out.println("New Version : "+c.getVersion());
        
    }
}
