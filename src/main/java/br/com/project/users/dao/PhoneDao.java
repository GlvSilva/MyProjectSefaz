package br.com.project.users.dao;

import br.com.project.users.entity.Phone;
import br.com.project.users.entity.Users;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PhoneDao {

    public EntityManager getEntityManager(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("users");
        return emf.createEntityManager();
    }

    public Phone savePhone(Phone phone) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            if (phone.getId() == null) {
                em.persist(phone); //cria usuario novo
            } else {
                if (!em.contains(phone)) {
                    if (em.find(Users.class, phone.getId()) == null) {
                        throw new Exception("Erro ao atualizar o Telefone!"); // excessao caso id nao seja encontrado na base
                    }
                }
            }   em.getTransaction().commit();
        } finally {
            em.close();
        }
        return phone;
    }

    public void removePhone(Long id) {
        EntityManager em = getEntityManager();
        Phone phone = em.find(Phone.class, id);
        try {
            em.getTransaction().begin();
            em.remove(phone); // Deleta telefone
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Phone findById(Long id) {
        EntityManager em = getEntityManager();
        Phone phone = null;
        try {
            em.getTransaction().begin();
            phone = em.find(Phone.class, id);
        } finally {
            em.close();
        }
        return phone;
    }

    public List<Phone> getAllUsers() {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            return em.createQuery("FROM" + Phone.class.getName()).getResultList();
        } finally {
            em.close();
        }
    }
}
