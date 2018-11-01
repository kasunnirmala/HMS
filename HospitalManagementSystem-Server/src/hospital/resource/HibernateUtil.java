/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.resource;

import hospital.entity.Appointment;
import hospital.entity.AppointmentPayment;
import hospital.entity.Cashier;
import hospital.entity.Doctor;
import hospital.entity.Nurse;
import hospital.entity.OtherServices;
import hospital.entity.Patient;
import hospital.entity.Prescription;
import hospital.entity.PrescriptionDetails;
import hospital.entity.Room;
import hospital.entity.RoomReserve;
import hospital.entity.RoomType;
import hospital.entity.Specialization;
import hospital.entity.Treatment;
import java.io.File;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Kasun
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    private static StandardServiceRegistry registry;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.

            // (1) 
            File hibernateProperties = new File("settings/hibernate.properties");
            registry = new StandardServiceRegistryBuilder().loadProperties(hibernateProperties).build();

            // (2)
            sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(Specialization.class)
                    .addAnnotatedClass(Doctor.class)
                    .addAnnotatedClass(RoomType.class)
                    .addAnnotatedClass(Room.class)
                    .addAnnotatedClass(Nurse.class)
                    .addAnnotatedClass(Cashier.class)
                    .addAnnotatedClass(Patient.class)
                    .addAnnotatedClass(Prescription.class)
                    .addAnnotatedClass(PrescriptionDetails.class)
                    .addAnnotatedClass(Appointment.class)
                    .addAnnotatedClass(RoomReserve.class)
                    .addAnnotatedClass(Treatment.class)
                    .addAnnotatedClass(OtherServices.class)
                    .addAnnotatedClass(AppointmentPayment.class)
                    .buildMetadata().buildSessionFactory();

        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);

            StandardServiceRegistryBuilder.destroy(registry);

            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
