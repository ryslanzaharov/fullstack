package repository;

import model.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.PaymentService;
import utils.HibernateSessionFactory;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PaymentServiceImpl implements PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

//    private final CopyOnWriteArrayList<Payment> payments = new CopyOnWriteArrayList<>();
//    {
//        Payment payment = new Payment();
//        payment.setValue(new BigDecimal(100.0));
//        payments.add(payment);
//    }


    @Override
    public List<Payment> findAll() throws RemoteException {
        List<Payment> payments = new ArrayList<>();
        final Session session = HibernateSessionFactory.getSessionFactory().openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            payments = session.createQuery("from Payment ").list();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            transaction.rollback();
        } finally {
            transaction.commit();
            session.close();
        }
        return payments;
    }

    @Override
    public Payment create(Payment payment) throws RemoteException {
        final Session session = HibernateSessionFactory.getSessionFactory().openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            session.save(payment);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            transaction.rollback();
        } finally {
            transaction.commit();
            session.close();
        }
        return payment;
    }
}
