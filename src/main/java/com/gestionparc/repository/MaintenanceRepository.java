package com.gestionparc.repository;

import com.gestionparc.model.Maintenance;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.gestionparc.util.HibernateUtil;
import java.util.List;

/**
 * Repository pour Maintenance
 */
public class MaintenanceRepository extends BaseRepository<Maintenance, Integer> {

    public MaintenanceRepository() {
        super(Maintenance.class);
    }

    /**
     * Trouve les maintenances d'un véhicule
     */
    public List<Maintenance> findByVehicleId(Integer vehicleId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Maintenance> query = session.createQuery(
                "FROM Maintenance WHERE vehicule.idVehicule = :vehicleId ORDER BY dateDebut DESC", 
                Maintenance.class);
            query.setParameter("vehicleId", vehicleId);
            return query.list();
        } finally {
            session.close();
        }
    }

    /**
     * Trouve les maintenances par type
     */
    public List<Maintenance> findByType(String type) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Maintenance> query = session.createQuery(
                "FROM Maintenance WHERE typeMaintenance = :type", Maintenance.class);
            query.setParameter("type", type);
            return query.list();
        } finally {
            session.close();
        }
    }
}
