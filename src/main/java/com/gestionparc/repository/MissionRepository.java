package com.gestionparc.repository;

import com.gestionparc.model.Mission;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.gestionparc.util.HibernateUtil;
import java.util.List;

/**
 * Repository pour Mission
 */
public class MissionRepository extends BaseRepository<Mission, Integer> {

    public MissionRepository() {
        super(Mission.class);
    }

    /**
     * Trouve les missions par statut
     */
    public List<Mission> findByStatut(String statut) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Mission> query = session.createQuery(
                "FROM Mission WHERE statut = :statut", Mission.class);
            query.setParameter("statut", statut);
            return query.list();
        } finally {
            session.close();
        }
    }

    /**
     * Trouve les missions d'un chauffeur
     */
    public List<Mission> findByChauffeurId(Integer chauffeurId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Mission> query = session.createQuery(
                "FROM Mission WHERE chauffeur.idUtilisateur = :chauffeurId", Mission.class);
            query.setParameter("chauffeurId", chauffeurId);
            return query.list();
        } finally {
            session.close();
        }
    }

    /**
     * Trouve les missions d'un véhicule
     */
    public List<Mission> findByVehicleId(Integer vehicleId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Mission> query = session.createQuery(
                "FROM Mission WHERE vehicule.idVehicule = :vehicleId", Mission.class);
            query.setParameter("vehicleId", vehicleId);
            return query.list();
        } finally {
            session.close();
        }
    }
}
