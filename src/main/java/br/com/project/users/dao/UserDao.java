package br.com.project.users.dao;

import br.com.project.users.entity.Users;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class UserDao {
   public EntityManager getEntityManager(){
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("users");
       return emf.createEntityManager();
   }

   public Users saveUsers(Users users) throws Exception {
       EntityManager em = getEntityManager();
       try {
           em.getTransaction().begin();
           if (users.getId() == null) {
               em.persist(users); //cria usuario novo
           } else {
               if (!em.contains(users)) {
                   if (em.find(Users.class, users.getId()) == null) {
                       throw new Exception("Erro ao atualizar o usuário - Usuário não existente!"); // excessao caso id nao seja encontrado na base
                   }
               }
           }   em.getTransaction().commit();
       } finally {
         em.close();
       }
    return users;
   }

   public void removeUser(Long id) {
       EntityManager em = getEntityManager();
       Users users = em.find(Users.class, id);
       try {
           em.getTransaction().begin();
           em.remove(users); // Deleta usuario
           em.getTransaction().commit();
       } finally {
           em.close();
       }
   }

   public Users findById(Long id) {
       EntityManager em = getEntityManager();
       Users users = null;
       try {
           em.getTransaction().begin();
           users = em.find(Users.class, id);
       } finally {
           em.close();
       }
       return users;
   }

   public List<Users> getAllUsers() {
       EntityManager em = getEntityManager();
       try {
           em.getTransaction().begin();
           return em.createQuery("FROM" + Users.class.getName()).getResultList();
       } finally {
           em.close();
       }
   }
}
